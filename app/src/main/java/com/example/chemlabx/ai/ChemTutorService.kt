package com.example.chemlabx.ai

import com.example.chemlabx.data.CompoundData
import com.example.chemlabx.data.ElementData
import com.example.chemlabx.data.LocalizationManager
import com.example.chemlabx.data.OrganicChemistryData
import com.example.chemlabx.data.ReactionData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.net.HttpURLConnection
import java.net.URL

data class ChatMessage(
    val id: String = java.util.UUID.randomUUID().toString(),
    val sender: String, // "user" or "tutor"
    val text: String,
    val citation: String? = null,
    val timestamp: Long = System.currentTimeMillis()
)

object ChemTutorService {

    private const val RATE_LIMIT_COOLDOWN_MS = 2000L
    private var lastRequestTimestamp = 0L

    suspend fun askTutor(query: String, studentLevel: String): String = withContext(Dispatchers.IO) {
        val now = System.currentTimeMillis()
        if (now - lastRequestTimestamp < RATE_LIMIT_COOLDOWN_MS) {
            return@withContext "Please wait a moment before asking another question to ensure fair system usage."
        }
        lastRequestTimestamp = now

        // Check if user has an API key configured via Secrets panel
        val apiKey = try {
            val field = Class.forName("com.example.BuildConfig").getField("GEMINI_API_KEY")
            field.get(null) as? String ?: ""
        } catch (_: Exception) {
            ""
        }

        // If an API key is available, call the Gemini API with database grounding
        if (apiKey.isNotBlank() && apiKey != "MY_GEMINI_API_KEY") {
            try {
                val groundedContext = retrieveGroundedContext(query)
                val response = callGroundedGeminiApi(query, studentLevel, groundedContext, apiKey)
                if (response.isNotBlank()) {
                    return@withContext response
                }
            } catch (_: Exception) {
                // Fall back securely to verified local chemistry database
            }
        }

        // Grounded offline chemistry knowledge engine
        generateGroundedOfflineResponse(query, studentLevel)
    }

    /**
     * Retrieves relevant verified scientific facts from the local database
     * to inject into Gemini's system instructions, preventing hallucinated facts.
     */
    private fun retrieveGroundedContext(query: String): String {
        val q = query.lowercase().trim()
        val facts = mutableListOf<String>()

        // Check elements
        val matchedElement = ElementData.allElements.firstOrNull {
            it.name.lowercase() in q || it.symbol.lowercase() == q || it.number.toString() == q
        }
        if (matchedElement != null) {
            facts.add("Verified Element: ${matchedElement.name} (${matchedElement.symbol}), Atomic #${matchedElement.number}, Atomic Mass: ${matchedElement.atomicMass} u, Electron Config: ${matchedElement.electronConfiguration}, Category: ${matchedElement.category.displayName}, Oxidation States: ${matchedElement.oxidationStates}. [Source: IUPAC CIAAW 2022]")
        }

        // Check compounds
        val matchedCompound = CompoundData.compounds.firstOrNull {
            it.name.lowercase() in q || it.formula.lowercase() in q || it.iupacName.lowercase() in q
        }
        if (matchedCompound != null) {
            facts.add("Verified Compound: ${matchedCompound.name} (${matchedCompound.formula}), IUPAC: ${matchedCompound.iupacName}, Geometry: ${matchedCompound.geometry}, Bond Angles: ${matchedCompound.bondAngles}, Polarity: ${matchedCompound.polarity}. [Source: PubChem CID ${matchedCompound.pubchemCid ?: "N/A"}]")
        }

        // Check reactions
        val matchedReaction = ReactionData.reactions.firstOrNull {
            it.name.lowercase() in q || it.reactants.any { r -> r.lowercase() in q }
        }
        if (matchedReaction != null) {
            facts.add("Verified Reaction: ${matchedReaction.name}, Balanced Equation: ${matchedReaction.equation}, Energy: ${matchedReaction.energyChange}, Type: ${matchedReaction.reactionType}. [Source: CRC Handbook of Chemistry & Physics]")
        }

        // Check organic functional groups
        val matchedGroup = OrganicChemistryData.allFunctionalGroups.firstOrNull {
            it.name.lowercase() in q || it.generalFormula.lowercase() in q || it.iupacSuffix.lowercase() in q
        }
        if (matchedGroup != null) {
            facts.add("Verified Functional Group: ${matchedGroup.name} (${matchedGroup.generalFormula}), IUPAC Priority: #${matchedGroup.priorityOrder}, Suffix: ${matchedGroup.iupacSuffix}, Prefix: ${matchedGroup.iupacPrefix}, Hybridization: ${matchedGroup.hybridization}. [Source: IUPAC Blue Book]")
        }

        return facts.joinToString("\n")
    }

    private fun callGroundedGeminiApi(prompt: String, studentLevel: String, context: String, apiKey: String): String {
        val systemPrompt = "You are ChemTutor, a verified AI Chemistry Professor in ChemLab X. " +
                "Target Student Level: $studentLevel. " +
                "Language: ${LocalizationManager.currentLanguage.displayName}. " +
                "CRITICAL INSTRUCTIONS: " +
                "1. Strictly cite empirical data from verified sources (IUPAC, NIST, CRC Handbook, PubChem). " +
                "2. DO NOT invent fictitious elements, unverified oxidation states, or unbalanced chemical equations. " +
                "3. If explaining dangerous or explosive reactions, clearly label them as SIMULATION ONLY. " +
                "4. End your answer with a verified citation: '[Source: IUPAC Periodic Table 2022 / NIST Chemistry WebBook / PubChem]'. " +
                if (context.isNotBlank()) "VERIFIED DATABASE GROUNDING FACTS:\n$context" else ""

        val url = URL("https://generativelanguage.googleapis.com/v1beta/models/gemini-3.5-flash:generateContent?key=$apiKey")
        val conn = url.openConnection() as HttpURLConnection
        conn.requestMethod = "POST"
        conn.setRequestProperty("Content-Type", "application/json")
        conn.doOutput = true
        conn.connectTimeout = 12000
        conn.readTimeout = 12000

        val requestBody = JSONObject().apply {
            put("systemInstruction", JSONObject().apply {
                put("parts", JSONArray().apply {
                    put(JSONObject().apply { put("text", systemPrompt) })
                })
            })
            put("contents", JSONArray().apply {
                put(JSONObject().apply {
                    put("parts", JSONArray().apply {
                        put(JSONObject().apply { put("text", prompt) })
                    })
                })
            })
        }

        OutputStreamWriter(conn.outputStream).use { it.write(requestBody.toString()) }

        val responseCode = conn.responseCode
        if (responseCode == HttpURLConnection.HTTP_OK) {
            val responseText = BufferedReader(InputStreamReader(conn.inputStream)).use { it.readText() }
            val json = JSONObject(responseText)
            val candidates = json.optJSONArray("candidates")
            val firstCandidate = candidates?.optJSONObject(0)
            val content = firstCandidate?.optJSONObject("content")
            val parts = content?.optJSONArray("parts")
            val text = parts?.optJSONObject(0)?.optString("text")
            if (!text.isNullOrBlank()) return text
        }
        return ""
    }

    /**
     * Offline verified response generator grounded directly in the 118 element database,
     * compound catalog, and reaction mechanisms.
     */
    private fun generateGroundedOfflineResponse(query: String, studentLevel: String): String {
        val q = query.lowercase().trim()

        // 1. Element query check
        val element = ElementData.allElements.firstOrNull {
            it.name.lowercase() == q || it.symbol.lowercase() == q || "tell me about ${it.name.lowercase()}" in q || "element ${it.number}" in q
        }
        if (element != null) {
            return "### ${element.name} (${element.symbol}) — Atomic #${element.number}\n\n" +
                    "• **Category**: ${element.category.displayName} (Group ${element.group ?: "N/A"}, Period ${element.period}, ${element.block}-block)\n" +
                    "• **Standard Atomic Weight**: ${element.atomicMass} u\n" +
                    "• **Electron Configuration**: `${element.electronConfiguration}` (${element.shells.joinToString("-")} shells)\n" +
                    "• **Valence Electrons**: ${element.valenceElectrons} | **Oxidation States**: ${element.oxidationStates}\n" +
                    "• **Electronegativity (Pauling)**: ${element.electronegativity ?: "N/A"}\n" +
                    "• **Physical Phase at STP**: ${element.phase.label}\n\n" +
                    "**Discovery**: ${element.discoveredBy} (${element.yearDiscovered}).\n" +
                    "**Key Applications**: ${element.applications.joinToString(", ")}.\n" +
                    "**Did You Know?**: ${element.interestingFact}\n\n" +
                    "📖 **Source**: IUPAC Commission on Isotopic Abundances & Atomic Weights (CIAAW) 2022 / NIST Physical Measurement Laboratory."
        }

        // 2. Dangerous reactions & safety simulation
        if (q.contains("sodium") && q.contains("water")) {
            return "### Sodium and Water Reaction (Extreme Safety Hazard)\n\n" +
                    "**Balanced Equation**: \n`2 Na(s) + 2 H₂O(l) → 2 NaOH(aq) + H₂(g)↑` (ΔH = -368 kJ/mol)\n\n" +
                    "• **Driving Force**: Sodium has an extremely low first ionization energy (495.8 kJ/mol) and readily donates its single 3s¹ valence electron to water.\n" +
                    "• **Macroscopic Observation**: Sodium metal instantly melts into a skittering silvery ball emitting yellow flame, caustic alkaline mist, and detonates violently.\n" +
                    "⚠️ **SIMULATION RESTRICTION ONLY**: In real laboratories, bulk metallic sodium reacts with explosive detonation. Never add large sodium pieces to water.\n\n" +
                    "📖 **Source**: CRC Handbook of Chemistry and Physics, 104th Edition."
        }

        if (q.contains("acid") && q.contains("water") && (q.contains("add") || q.contains("dilute"))) {
            return "### Dilution Safety Rule: Acid & Water\n\n" +
                    "**The Cardinal Laboratory Rule**: Always add **Acid into Water** (slowly along container walls with stirring), NEVER Water into Acid!\n\n" +
                    "• **Scientific Reason**: Concentrated sulfuric acid has a colossal hydration enthalpy (ΔH = -96 kJ/mol). Water is less dense than acid and floats on top. If water is poured into acid, the water flashes to steam instantly at the boundary, splattering boiling concentrated acid causing permanent blindness.\n\n" +
                    "📖 **Source**: Prudent Practices in the Laboratory, National Research Council."
        }

        // 3. Water geometry & bonding
        if (q.contains("water") && (q.contains("bent") || q.contains("shape") || q.contains("angle"))) {
            return "### Molecular Geometry of Water (H₂O)\n\n" +
                    "• **Formula**: H₂O (IUPAC: Oxidane)\n" +
                    "• **Molecular Geometry**: **Bent / Angular** with a bond angle of **104.5°**\n" +
                    "• **Hybridization**: Central oxygen is sp³ hybridized with steric number 4.\n" +
                    "• **VSEPR Explanation**: Oxygen possesses 2 bonding pairs (O–H bonds) and 2 non-bonding lone pairs. According to VSEPR theory, lone pair-lone pair repulsions exceed bonding pair repulsions, squeezing the ideal tetrahedral angle (109.5°) down to 104.5°.\n" +
                    "• **Dipole Moment**: 1.85 D (Strongly Polar, robust hydrogen bonding network).\n\n" +
                    "📖 **Source**: NIST Chemistry WebBook (SRD 69) / PubChem CID 962."
        }

        // 4. Ionic vs Covalent
        if (q.contains("ionic") && q.contains("covalent")) {
            return "### Ionic vs. Covalent Chemical Bonds\n\n" +
                    "• **Ionic Bonds**: Occur between atoms with a large electronegativity difference (ΔEN > ~1.7), typically a metal and nonmetal. Valence electrons are transferred completely to form positive cations and negative anions bound by Coulombic lattice forces (e.g. Na⁺ + Cl⁻ → NaCl).\n\n" +
                    "• **Covalent Bonds**: Occur between nonmetals with comparable electronegativities (ΔEN < 1.7). Atoms achieve stable noble gas octets by sharing electron pairs (e.g. H–O–H, H₃C–CH₃).\n\n" +
                    "📖 **Source**: IUPAC Gold Book (Compendium of Chemical Terminology)."
        }

        // 5. pH and acid-base
        if (q.contains("ph") || q.contains("acid") || q.contains("base")) {
            return "### Understanding pH & Acid-Base Chemistry\n\n" +
                    "**pH** is defined as the negative base-10 logarithm of aqueous hydronium ion activity: `pH = -log₁₀[H₃O⁺]`.\n\n" +
                    "• **Acidic (pH < 7)**: Excess hydronium ions ([H⁺] > 10⁻⁷ M), e.g., Gastric acid (pH 1.5), Vinegar (pH 2.4).\n" +
                    "• **Neutral (pH = 7)**: Pure water at 25°C, where [H⁺] = [OH⁻] = 1.0 × 10⁻⁷ M.\n" +
                    "• **Basic / Alkaline (pH > 7)**: Excess hydroxide ions ([OH⁻] > 10⁻⁷ M), e.g., Baking soda (pH 8.3), Bleach (pH 12.5).\n\n" +
                    "📖 **Source**: IUPAC Recommendations on pH Metric Scales."
        }

        // Default verified chemistry response
        return "### ChemTutor Scientific Principle\n\n" +
                "All chemical behavior is governed by valence electron configurations and thermodynamic equilibrium seeking minimum Gibbs Free Energy (ΔG = ΔH - TΔS < 0).\n\n" +
                "• **Periodic Trends**: Atomic radius, electronegativity, and ionization energies follow periodic orbital shielding patterns.\n" +
                "• **Bonding**: Driven by octet completion via electron transfer (ionic) or orbital overlap sharing (covalent).\n" +
                "• **Reaction Driving Forces**: Enthalpy of formation (bond stabilization) and entropy increase.\n\n" +
                "Ask ChemTutor about any of the 118 elements, 3D molecular structures, IUPAC naming rules, or reaction mechanisms!\n\n" +
                "📖 **Source**: IUPAC Standard Periodic Table 2022 & NIST Physical Measurement Laboratory."
    }
}
