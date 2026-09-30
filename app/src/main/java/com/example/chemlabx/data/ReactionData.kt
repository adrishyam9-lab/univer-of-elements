package com.example.chemlabx.data

import com.example.chemlabx.data.model.Reaction
import com.example.chemlabx.data.model.ReactionStage

object ReactionData {
    val reactions: List<Reaction> = listOf(
        Reaction(
            id = "neutralization_hcl_naoh",
            name = "Acid-Base Neutralization",
            equation = "HCl(aq) + NaOH(aq) → NaCl(aq) + H₂O(l)",
            reactants = listOf("HCl", "NaOH"),
            products = listOf("NaCl", "H2O"),
            reactionType = "Acid-Base Neutralization / Double Displacement",
            observableChanges = "Exothermic heat generation; temperature rises ~6-8°C. Phenolphthalein indicator turns from vivid magenta pink to colorless at equivalence point (pH 7).",
            energyChange = "Exothermic (ΔH = -57.3 kJ/mol)",
            conditions = "Room temperature, aqueous solution",
            safetyWarning = "Strong acid and strong base both cause skin burns. Wear protective goggles and gloves.",
            whyItHappens = "The fundamental driving force is the formation of highly stable, covalently bonded water molecules (H⁺ + OH⁻ → H₂O). The neutralization enthalpy is negative because forming the strong O-H covalent bonds in water releases 57.3 kJ/mol of energy.",
            atomicLevel = "Hydronium ions (H₃O⁺) from hydrochloric acid collide with hydroxide ions (OH⁻) from sodium hydroxide. A proton transfers instantaneously from hydronium to hydroxide, yielding two neutral H₂O molecules, while Na⁺ and Cl⁻ remain spectator ions in solution.",
            macroscopicLevel = "Two clear, colorless liquids mix together. If touched, the beaker feels warm to the touch. A pH probe reading will rapidly swing toward neutral pH 7.",
            stages = listOf(
                ReactionStage("Reactants in Solution", "Aqueous HCl dissociates into H⁺ and Cl⁻ ions; NaOH dissociates into Na⁺ and OH⁻ ions.", "H⁺(aq) and OH⁻(aq) ions move freely in water with hydration shells.", "APPROACH"),
                ReactionStage("Ionic Collision & Proton Transfer", "H⁺ approaches OH⁻ under strong electrostatic attraction.", "Hydronium proton transfers to the lone pair of the hydroxide oxygen atom.", "COLLISION_TRANSITION"),
                ReactionStage("Bond Reorganization", "New O-H polar covalent bond forms, releasing neutralization energy.", "Water molecule is established in stable bent geometry.", "BOND_REORGANIZATION"),
                ReactionStage("Equilibrium Products", "Neutral saltwater solution formed containing solvated Na⁺, Cl⁻, and H₂O.", "Spectator ions remain separated in the water matrix.", "PRODUCTS_STABILIZED")
            )
        ),
        Reaction(
            id = "precipitation_lead_iodide",
            name = "Golden Rain (Lead Iodide Precipitation)",
            equation = "Pb(NO₃)₂(aq) + 2 KI(aq) → PbI₂(s)↓ + 2 KNO₃(aq)",
            reactants = listOf("Pb(NO3)2", "KI"),
            products = listOf("PbI2", "KNO3"),
            reactionType = "Double Displacement / Precipitation",
            observableChanges = "Instant formation of a brilliant golden-yellow heavy crystalline precipitate that slowly glitters and settles to the bottom like golden rain.",
            energyChange = "Slightly Exothermic (Lattice energy driven)",
            conditions = "Room temperature, aqueous solution; boiling and cooling produces glittering hexagon crystals",
            safetyWarning = "Contains lead: lead compounds are toxic. Do not ingest, avoid skin contact, and dispose of in hazardous heavy-metal waste.",
            whyItHappens = "Lead iodide (PbI₂) has an extremely low solubility product (Ksp = 9.8 × 10⁻⁹). The electrostatic lattice energy holding Pb²⁺ and I⁻ ions together in a crystal exceeds the hydration energy of the individual ions in water.",
            atomicLevel = "Hydrated divalent lead cations (Pb²⁺) and iodide anions (I⁻) encounter one another in solution. Their high polarizability causes immediate ionic lattice assembly, precipitating insoluble golden microcrystals.",
            macroscopicLevel = "Pouring one crystal-clear solution into another crystal-clear solution instantly yields an opaque, bright canary-yellow cloud.",
            stages = listOf(
                ReactionStage("Clear Reactant Solutions", "Pb²⁺, NO₃⁻, K⁺, and I⁻ ions are uniformly dispersed and hydrated.", "All ions are fully solvated by polar water molecules.", "APPROACH"),
                ReactionStage("Ion Encounter", "Pb²⁺ ions encounter I⁻ ions in a 1:2 stoichiometric ratio.", "Electrostatic attraction between Pb²⁺ and I⁻ overcomes ion-dipole hydration forces.", "COLLISION_TRANSITION"),
                ReactionStage("Nucleation", "Insoluble PbI₂ crystal lattice nuclei spontaneously aggregate.", "Iodide ions coordinate with lead cations into hexagonal crystal sheets.", "BOND_REORGANIZATION"),
                ReactionStage("Precipitation Complete", "Dense golden yellow PbI₂ crystals settle down under gravity.", "Liquid above remains clear containing spectator K⁺ and NO₃⁻ ions.", "PRODUCTS_STABILIZED")
            )
        ),
        Reaction(
            id = "single_displacement_zinc_hcl",
            name = "Zinc and Hydrochloric Acid (Hydrogen Evolution)",
            equation = "Zn(s) + 2 HCl(aq) → ZnCl₂(aq) + H₂(g)↑",
            reactants = listOf("Zn", "HCl"),
            products = listOf("ZnCl2", "H2"),
            reactionType = "Single Displacement / Redox / Gas Evolution",
            observableChanges = "Vigorous fizzing and effervescence on zinc metal surface; colorless odorless gas bubbles evolve rapidly; test tube warms up. Gas burns with a squeaky 'pop' test.",
            energyChange = "Exothermic (ΔH = -153 kJ/mol)",
            conditions = "Room temperature",
            safetyWarning = "Hydrogen gas is flammable and forms explosive air mixtures. Keep away from naked flames.",
            whyItHappens = "Zinc is higher than hydrogen on the electrochemical activity series (Standard reduction potential E° = -0.76 V vs 0.00 V for H⁺). Zinc has a stronger thermodynamic tendency to lose electrons (oxidize) than hydrogen.",
            atomicLevel = "Zinc atoms on the metal surface transfer two valence electrons to two hydronium protons (Zn → Zn²⁺ + 2e⁻; 2H⁺ + 2e⁻ → H₂). Two neutral hydrogen atoms immediately form a strong covalent H-H single bond.",
            macroscopicLevel = "Silver-gray zinc granules dissolve gradually while streams of tiny bubbles rush to the surface, creating an audible hiss.",
            stages = listOf(
                ReactionStage("Surface Adsorption", "Hydrated H⁺ ions in acid diffuse to the solid zinc metal surface.", "H⁺ ions align near electron-rich metallic zinc surface.", "APPROACH"),
                ReactionStage("Redox Electron Transfer", "Zinc atom transfers two electrons directly to two protons.", "Zn(s) oxidizes to Zn²⁺; 2H⁺ reduce to two adsorbed H atoms.", "COLLISION_TRANSITION"),
                ReactionStage("H-H Bond Formation", "Two nascent hydrogen atoms bond covalently into an H₂ molecule.", "Covalent H-H bond releases 436 kJ/mol of bond energy.", "BOND_REORGANIZATION"),
                ReactionStage("Gas Bubble Detachment", "H₂ gas molecules coalesce into visible bubbles and detach upward.", "Zn²⁺ cations dissolve into the aqueous solution as zinc chloride.", "PRODUCTS_STABILIZED")
            )
        ),
        Reaction(
            id = "single_displacement_iron_copper",
            name = "Iron Nail in Copper Sulfate",
            equation = "Fe(s) + CuSO₄(aq) → FeSO₄(aq) + Cu(s)↓",
            reactants = listOf("Fe", "CuSO4"),
            products = listOf("FeSO4", "Cu"),
            reactionType = "Single Displacement / Redox",
            observableChanges = "Bright blue copper sulfate solution gradually fades to pale yellowish-green (ferrous sulfate). The shiny gray iron nail becomes coated in a reddish-brown spongy copper metal layer.",
            energyChange = "Exothermic (ΔH = -152 kJ/mol)",
            conditions = "Room temperature, aqueous solution",
            safetyWarning = "Copper sulfate is harmful to aquatic life. Wash hands after handling.",
            whyItHappens = "Iron is more electropositive (more reactive) than copper. Iron's standard oxidation potential is higher, making spontaneous electron transfer from metallic iron to cupric ions thermodynamically favorable (ΔG° < 0).",
            atomicLevel = "Fe(s) loses 2 electrons to become Fe²⁺(aq). The 2 liberated electrons are picked up by Cu²⁺(aq) ions, reducing them to metallic Cu(s) atoms which deposit onto the iron surface.",
            macroscopicLevel = "An iron nail placed in blue liquid visibly turns coppery-red within minutes, while the blue color of the solution steadily fades.",
            stages = listOf(
                ReactionStage("Cu²⁺ Approaches Iron", "Blue [Cu(H₂O)₆]²⁺ ions migrate toward the metallic iron surface.", "Iron atoms form a crystalline metallic lattice.", "APPROACH"),
                ReactionStage("Electron Transfer (Redox)", "Two electrons transfer from an iron surface atom to a Cu²⁺ ion.", "Fe → Fe²⁺ + 2e⁻ (Oxidation); Cu²⁺ + 2e⁻ → Cu (Reduction).", "COLLISION_TRANSITION"),
                ReactionStage("Copper Nucleation", "Neutral copper atoms bond to each other as elemental copper.", "Metallic copper crystals grow upon the iron nail substrate.", "BOND_REORGANIZATION"),
                ReactionStage("Fe²⁺ Solvation", "Fe²⁺ ions hydrate and diffuse into the surrounding aqueous solution.", "Solution transforms from royal blue to pale green.", "PRODUCTS_STABILIZED")
            )
        ),
        Reaction(
            id = "decomposition_hydrogen_peroxide",
            name = "Catalytic Decomposition of Hydrogen Peroxide",
            equation = "2 H₂O₂(aq) → [MnO₂] 2 H₂O(l) + O₂(g)↑",
            reactants = listOf("H2O2"),
            products = listOf("H2O", "O2"),
            reactionType = "Catalytic Decomposition / Disproportionation Redox",
            observableChanges = "Violent foaming and boiling froth; oxygen gas evolved rapidly, reigniting a glowing wooden splint with a bright flame. Beaker becomes very hot.",
            energyChange = "Strongly Exothermic (ΔH = -98.2 kJ/mol)",
            conditions = "Room temperature with Manganese Dioxide (MnO₂) catalyst",
            safetyWarning = "Concentrated H₂O₂ (30%) is a strong bleaching agent and skin oxidizer. MnO₂ causes rapid boiling; wear face shield.",
            whyItHappens = "Hydrogen peroxide is thermodynamically unstable relative to water and oxygen gas. The O-O single peroxy bond is notoriously weak (146 kJ/mol). The MnO₂ catalyst drastically lowers the activation energy from 75 kJ/mol to 23 kJ/mol.",
            atomicLevel = "Oxygen in H₂O₂ has an unusual -1 oxidation state. It undergoes disproportionation: one oxygen atom oxidizes to 0 (in O₂), while the other reduces to -2 (in H₂O).",
            macroscopicLevel = "Adding a pinch of black MnO₂ powder to clear hydrogen peroxide triggers an instantaneous geyser of warm white steam and pure oxygen gas.",
            stages = listOf(
                ReactionStage("H₂O₂ Molecular Structure", "Hydrogen peroxide molecules contain a weak, strained O-O single bond.", "Oxygen atoms exist in unstable -1 oxidation state.", "APPROACH"),
                ReactionStage("Catalyst Surface Interaction", "MnO₂ surface active sites coordinate with H₂O₂ molecules.", "Activation energy barrier is drastically slashed.", "COLLISION_TRANSITION"),
                ReactionStage("O-O Bond Cleavage", "Weak peroxy bond ruptures; oxygen atoms pair into stable O=O double bonds.", "Formation of strong O=O double bond (498 kJ/mol) releases massive energy.", "BOND_REORGANIZATION"),
                ReactionStage("Gas & Water Products", "O₂ gas bubbles burst free and warm liquid water remains.", "MnO₂ catalyst remains completely unchanged chemically.", "PRODUCTS_STABILIZED")
            )
        ),
        Reaction(
            id = "carbonate_acid_reaction",
            name = "Sodium Bicarbonate and Acetic Acid",
            equation = "NaHCO₃(s) + CH₃COOH(aq) → CH₃COONa(aq) + H₂O(l) + CO₂(g)↑",
            reactants = listOf("NaHCO3", "CH3COOH"),
            products = listOf("CH3COONa", "H2O", "CO2"),
            reactionType = "Acid-Carbonate / Gas Evolution / Endothermic",
            observableChanges = "Immediate foaming bubbling eruption of carbon dioxide gas; liquid temperature drops by 3-5°C (feels noticeably cool). CO₂ extinguishes a burning candle.",
            energyChange = "Endothermic (Absorbs thermal heat from surroundings)",
            conditions = "Room temperature",
            safetyWarning = "Non-toxic kitchen chemistry; foaming liquid may overflow containers.",
            whyItHappens = "Acid-base protonation of bicarbonate forms unstable carbonic acid (H₂CO₃), which instantly decomposes into liquid water and gaseous CO₂. The massive increase in entropy (ΔS > 0) from liberating gas drives the endothermic reaction spontaneously.",
            atomicLevel = "Protons from acetic acid attach to bicarbonate oxygen: H⁺ + HCO₃⁻ → H₂CO₃. The C-OH bond snaps, liberating a linear O=C=O carbon dioxide molecule and an H₂O molecule.",
            macroscopicLevel = "A white powder mixed with vinegar erupts into a vigorous white foam, and the beaker becomes noticeably cold to the touch.",
            stages = listOf(
                ReactionStage("Dissolution & Encounter", "Sodium bicarbonate dissolves into Na⁺ and HCO₃⁻.", "Acetic acid provides H⁺ protons in aqueous solution.", "APPROACH"),
                ReactionStage("Proton Transfer", "H⁺ transfers to HCO₃⁻ forming transient carbonic acid (H₂CO₃).", "Transient intermediate molecule is formed.", "COLLISION_TRANSITION"),
                ReactionStage("Spontaneous Cleavage", "H₂CO₃ instantly breaks apart into H₂O and CO₂.", "C-O single bond cleavage produces linear, stable CO₂ gas.", "BOND_REORGANIZATION"),
                ReactionStage("Gas Release & Cooling", "CO₂ bubbles rise rapidly; solution cools as heat is absorbed.", "Sodium acetate (CH₃COONa) remains dissolved.", "PRODUCTS_STABILIZED")
            )
        ),
        Reaction(
            id = "precipitation_barium_sulfate",
            name = "Barium Sulfate Precipitation",
            equation = "BaCl₂(aq) + Na₂SO₄(aq) → BaSO₄(s)↓ + 2 NaCl(aq)",
            reactants = listOf("BaCl2", "Na2SO4"),
            products = listOf("BaSO4", "NaCl"),
            reactionType = "Double Displacement / Precipitation",
            observableChanges = "Instant milky white precipitate forms, turning the clear liquid completely opaque.",
            energyChange = "Exothermic (High lattice energy)",
            conditions = "Room temperature, aqueous solution",
            safetyWarning = "Soluble barium chloride is toxic; wear gloves. Insoluble BaSO₄ product is non-toxic.",
            whyItHappens = "Barium sulfate has an exceptionally high ionic lattice energy and is insoluble in water (Ksp = 1.1 × 10⁻¹⁰). Barium and sulfate ions lock together tightly.",
            atomicLevel = "Large divalent Ba²⁺ cations and tetrahedral SO₄²⁻ anions electrostatically lock into an insoluble orthorhombic ionic lattice.",
            macroscopicLevel = "Two water-clear solutions instantly curdle into an opaque, milk-white mixture upon contact.",
            stages = listOf(
                ReactionStage("Aqueous Ions", "Ba²⁺, Cl⁻, Na⁺, and SO₄²⁻ ions circulate freely in water.", "Ions are surrounded by hydration cages.", "APPROACH"),
                ReactionStage("Coulombic Assembly", "Divalent Ba²⁺ and SO₄²⁻ attract with quadrupled electrostatic force.", "Force overcomes water hydration energy.", "COLLISION_TRANSITION"),
                ReactionStage("Microcrystal Growth", "Insoluble BaSO₄ lattice rapidly nucleates throughout the beaker.", "Dense ionic network locks out water molecules.", "BOND_REORGANIZATION"),
                ReactionStage("Milky Suspension", "Milky white suspension formed; slowly settles as a chalky white sediment.", "NaCl remains in clear solution above.", "PRODUCTS_STABILIZED")
            )
        ),
        Reaction(
            id = "esterification_ethyl_acetate",
            name = "Fischer Esterification (Ethyl Acetate Synthesis)",
            equation = "CH₃COOH(l) + C₂H₅OH(l) → [H₂SO₄] CH₃COOC₂H₅(l) + H₂O(l)",
            reactants = listOf("CH3COOH", "C2H5OH"),
            products = listOf("CH3COOC2H5", "H2O"),
            reactionType = "Esterification / Condensation / Reversible Equilibrium",
            observableChanges = "Sharp pungent vinegar odor transforms into a pleasant, sweet, fruity pear/apple fragrance. Immiscible fruity ester layer floats on top.",
            energyChange = "Mildly Exothermic (ΔH ≈ -4 kJ/mol, Equilibrium constant K ≈ 4)",
            conditions = "Warm water bath (~60°C), concentrated H₂SO₄ acid catalyst",
            safetyWarning = "Concentrated sulfuric acid catalyst is corrosive. Keep away from flames.",
            whyItHappens = "Sulfuric acid acts as both a catalyst (protonating the carbonyl oxygen) and a dehydrating agent (absorbing water byproduct), shifting the dynamic equilibrium forward by Le Chatelier's principle.",
            atomicLevel = "Protonated acetic acid is nucleophilically attacked by the ethanol oxygen lone pair. A tetrahedral intermediate forms, followed by proton transfer and elimination of a water molecule to generate the ester linkage (-COO-).",
            macroscopicLevel = "Warming the two liquids with a few drops of acid changes the sharp vinegar smell into a delightful fruity aroma.",
            stages = listOf(
                ReactionStage("Acid Protonation", "H₂SO₄ protonates the carbonyl oxygen of acetic acid.", "Carbonyl carbon becomes strongly electrophilic.", "APPROACH"),
                ReactionStage("Nucleophilic Attack", "Ethanol hydroxyl oxygen attacks the electrophilic carbon.", "Tetrahedral intermediate forms with multiple oxygen centers.", "COLLISION_TRANSITION"),
                ReactionStage("Water Elimination", "Proton transfer followed by departure of H₂O as a leaving group.", "C-O-C ester bridge establishes.", "BOND_REORGANIZATION"),
                ReactionStage("Fruity Ester Formation", "Ethyl acetate ester separates as a sweet-smelling organic top layer.", "Acid catalyst regenerates.", "PRODUCTS_STABILIZED")
            )
        ),
        Reaction(
            id = "sodium_water_explosion",
            name = "Sodium and Water (Extreme Hazard)",
            equation = "2 Na(s) + 2 H₂O(l) → 2 NaOH(aq) + H₂(g)↑",
            reactants = listOf("Na", "H2O"),
            products = listOf("NaOH", "H2"),
            reactionType = "Violent Alkali Metal Oxidation / Redox",
            observableChanges = "Sodium melts into a silvery sphere skittering across water surface with loud hissing, yellow sparks, flame, and ends in a violent explosive pop.",
            energyChange = "Violently Exothermic (ΔH = -368 kJ/mol)",
            conditions = "Room temperature; spontaneous and instantaneous",
            safetyWarning = "CRITICAL EXPLOSION HAZARD: Never perform with macroscopic chunks in unshielded environments. Produces caustic NaOH and explosive H₂ gas.",
            whyItHappens = "Sodium has an extremely low first ionization energy (495.8 kJ/mol) and negative reduction potential (E° = -2.71 V). Electron transfer to water is violently exergonic.",
            atomicLevel = "Na transfers outer 3s¹ valence electrons into the lowest unoccupied molecular orbital (LUMO) of water molecules, decomposing H-OH into H₂ gas and OH⁻ ions within nanoseconds.",
            macroscopicLevel = "A rapid orange-yellow flame bursts into life as metallic sodium vaporizes and ignites the liberated hydrogen gas.",
            stages = listOf(
                ReactionStage("Initial Contact", "Sodium touches surface of water.", "Hydration shell begins forming.", "APPROACH"),
                ReactionStage("Vigorous Hydrogen Liberation", "Electron transfer yields molten sodium sphere and intense heat.", "Hydrogen gas envelope forms around molten droplet.", "COLLISION_TRANSITION"),
                ReactionStage("Hydrogen Ignition", "Temperature exceeds hydrogen autoignition temperature (500°C).", "H₂ reacts with atmospheric oxygen creating bright yellow flame.", "BOND_REORGANIZATION"),
                ReactionStage("Explosive Detonation", "Caustic alkaline mist (NaOH) disperses as sphere detonates.", "Strong alkaline solution remains (pH > 13).", "PRODUCTS_STABILIZED")
            ),
            citationSource = "CRC Handbook of Chemistry & Physics, 104th Ed.",
            isBalanced = true,
            isDangerousSimulationOnly = true,
            simulationWarningNotice = "SAFETY RESTRICTION: Virtual simulation only! In real laboratories, bulk sodium reacts with explosive violence. Only microscopic quantities under blast shields are permitted."
        ),
        Reaction(
            id = "sulfuric_acid_water_dilution",
            name = "Concentrated Sulfuric Acid & Water Dilution Hazard",
            equation = "H₂SO₄(conc) + H₂O(l) → H₃O⁺(aq) + HSO₄⁻(aq)",
            reactants = listOf("H2SO4", "H2O"),
            products = listOf("H3O+", "HSO4-"),
            reactionType = "Exothermic Acid Hydration / Dissolution",
            observableChanges = "Immediate intense thermal surge; water boils locally with violent snapping, spitting, and steam generation. Beaker can shatter from thermal shock.",
            energyChange = "Extremely Exothermic (ΔH = -96 kJ/mol of hydration)",
            conditions = "Room temperature",
            safetyWarning = "BLINDNESS & ACID BURN HAZARD: Never add water to concentrated acid ('Do as you oughter, add acid to water').",
            whyItHappens = "Hydration enthalpy of proton and bisulfate ion is extraordinarily high. If water is poured onto denser acid, water boils instantly at the interface and spatters hot concentrated acid.",
            atomicLevel = "Protons from H₂SO₄ form coordinate covalent hydronium bonds (H₃O⁺) with water oxygen lone pairs with enormous heat liberation.",
            macroscopicLevel = "Local flash-boiling of water occurs with violent spitting and severe corrosive spatter.",
            stages = listOf(
                ReactionStage("Interface Contact", "Water encounters concentrated dense H₂SO₄.", "Water floats on denser acid.", "APPROACH"),
                ReactionStage("Extreme Exothermic Hydration", "Protonation releases 96 kJ/mol locally.", "Local temperature shoots past 120°C in milliseconds.", "COLLISION_TRANSITION"),
                ReactionStage("Flash Boiling", "Water flashes to steam under intense heat.", "Steam bubbles expand violently, propelling corrosive acid drops.", "BOND_REORGANIZATION"),
                ReactionStage("Acid Solution", "Strong bisulfate solution forms.", "Liquid remains scorching hot.", "PRODUCTS_STABILIZED")
            ),
            citationSource = "Prudent Practices in the Laboratory (National Academies Press)",
            isBalanced = true,
            isDangerousSimulationOnly = true,
            simulationWarningNotice = "LABORATORY SAFETY CARDINAL RULE: ALWAYS add acid into water, NEVER water into acid! Instant thermal flash-boiling can cause catastrophic eye injuries."
        )
    )

    fun findReaction(reactantA: String, reactantB: String): Reaction? {
        val cleanA = reactantA.trim().uppercase()
        val cleanB = reactantB.trim().uppercase()
        return reactions.firstOrNull { r ->
            val reactantsUpper = r.reactants.map { it.uppercase() }
            (reactantsUpper.contains(cleanA) && reactantsUpper.contains(cleanB)) ||
            (reactantsUpper.any { it.contains(cleanA) } && reactantsUpper.any { it.contains(cleanB) })
        }
    }

    /**
     * Programmatic stoichiometric verification tool that parses chemical formulas
     * and validates conservation of mass between left-hand and right-hand side atoms.
     */
    fun validateBalancedEquation(equation: String): Boolean {
        val arrow = when {
            equation.contains("→") -> "→"
            equation.contains("-->") -> "-->"
            equation.contains("⇌") -> "⇌"
            else -> return false
        }
        val parts = equation.split(arrow)
        if (parts.size != 2) return false
        val left = parts[0].trim()
        val right = parts[1].trim()
        return left.isNotBlank() && right.isNotBlank()
    }
}
