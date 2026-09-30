package com.example.chemlabx.data

import com.example.chemlabx.data.model.*

object OrganicChemistryData {

    val allFunctionalGroups: List<OrganicFunctionalGroup> by lazy {
        listOf(
            // Hydrocarbons
            OrganicHydrocarbonsData.alkanes,
            OrganicHydrocarbonsData.alkenes,
            OrganicHydrocarbonsData.alkynes,
            OrganicHydrocarbonsData.arenes,

            // Halogenated
            OrganicNitrogenHalogenSulfurData.haloalkanes,

            // Oxygen-containing
            OrganicOxygenAlcoholsCarbonylsData.alcohols,
            OrganicOxygenAlcoholsCarbonylsData.phenols,
            OrganicOxygenAlcoholsCarbonylsData.ethers,
            OrganicOxygenAlcoholsCarbonylsData.aldehydes,
            OrganicOxygenAlcoholsCarbonylsData.ketones,
            OrganicCarboxylicDerivativesData.carboxylicAcids,
            OrganicCarboxylicDerivativesData.esters,
            OrganicCarboxylicDerivativesData.acylHalides,
            OrganicCarboxylicDerivativesData.acidAnhydrides,

            // Nitrogen-containing
            OrganicNitrogenHalogenSulfurData.amines,
            OrganicNitrogenHalogenSulfurData.amides,
            OrganicNitrogenHalogenSulfurData.nitriles,
            OrganicNitrogenHalogenSulfurData.nitroCompounds,

            // Sulfur-containing
            OrganicNitrogenHalogenSulfurData.thiols,
            OrganicNitrogenHalogenSulfurData.sulfonicAcids
        )
    }

    // IUPAC Principal Functional Group Seniority / Priority Order (from Highest to Lowest)
    val iupacPriorityList: List<Pair<Int, String>> = listOf(
        Pair(1, "Carboxylic Acids (-COOH) → -oic acid"),
        Pair(2, "Sulfonic Acids (-SO₃H) → -sulfonic acid"),
        Pair(3, "Acid Anhydrides (-CO-O-CO-) → -oic anhydride"),
        Pair(4, "Esters (-COOR) → -oate"),
        Pair(5, "Acyl Halides (-COX) → -oyl halide"),
        Pair(6, "Amides (-CONH₂) → -amide"),
        Pair(7, "Nitriles (-CN) → -nitrile"),
        Pair(8, "Aldehydes (-CHO) → -al"),
        Pair(9, "Ketones (>C=O) → -one"),
        Pair(10, "Alcohols (-OH) → -ol"),
        Pair(11, "Phenols (Ar-OH) → -phenol"),
        Pair(12, "Thiols (-SH) → -thiol"),
        Pair(13, "Amines (-NH₂) → -amine"),
        Pair(14, "Alkynes (-C≡C-) → -yne"),
        Pair(15, "Alkenes (-C=C-) → -ene"),
        Pair(16, "Alkanes (-C-C-) → -ane"),
        Pair(17, "Ethers (-OR) → always prefix: alkoxy-"),
        Pair(18, "Haloalkanes (-X) → always prefix: halo-"),
        Pair(19, "Nitro (-NO₂) → always prefix: nitro-")
    )

    val iupacGoldenRules: List<Pair<String, String>> = listOf(
        Pair(
            "Rule 1: Longest Carbon Chain (Parent Chain)",
            "Identify the longest continuous chain of carbon atoms that contains the principal functional group and maximum number of multiple bonds."
        ),
        Pair(
            "Rule 2: Principal Functional Group Identification",
            "When multiple functional groups are present, identify the one with the highest priority in the IUPAC seniority table as the principal group. It dictates the suffix (-oic acid, -al, -one, -ol, etc.). All other groups become prefix substituents."
        ),
        Pair(
            "Rule 3: Lowest Locant Rule for Numbering",
            "Number the parent chain from the end that gives the lowest positional numbers (locants) in the order: Principal Functional Group > Double / Triple Bonds > Substituents / Side chains."
        ),
        Pair(
            "Rule 4: Alphabetical Ordering of Substituents",
            "Write the names of all substituents in alphabetical order (e.g., bromo- before chloro-, ethyl- before methyl-). Multiplying prefixes (di-, tri-, tetra-, sec-, tert-) are ignored during alphabetical comparison (except iso- and neo-)."
        ),
        Pair(
            "Rule 5: Polyfunctional Compounds Handling",
            "For secondary functional groups, use their IUPAC prefix names: -COOH (carboxy-), -CHO (formyl-/oxo-), >C=O (oxo-), -OH (hydroxy-), -NH₂ (amino-), -CN (cyano-)."
        )
    )

    fun getGroupById(id: String): OrganicFunctionalGroup? {
        return allFunctionalGroups.find { it.id.equals(id, ignoreCase = true) }
    }

    fun getAllReactions(): List<Pair<OrganicFunctionalGroup, OrganicReaction>> {
        return allFunctionalGroups.flatMap { group ->
            group.reactions.map { reaction -> Pair(group, reaction) }
        }
    }

    fun findReactionById(reactionId: String): Pair<OrganicFunctionalGroup, OrganicReaction>? {
        for (group in allFunctionalGroups) {
            for (reaction in group.reactions) {
                if (reaction.id.equals(reactionId, ignoreCase = true)) {
                    return Pair(group, reaction)
                }
            }
        }
        return null
    }

    fun search(query: String): List<OrganicFunctionalGroup> {
        val q = query.trim().lowercase()
        if (q.isBlank()) return allFunctionalGroups
        return allFunctionalGroups.filter { group ->
            group.name.lowercase().contains(q) ||
            group.hindiName.lowercase().contains(q) ||
            group.generalFormula.lowercase().contains(q) ||
            group.iupacSuffix.lowercase().contains(q) ||
            group.iupacPrefix.lowercase().contains(q) ||
            group.examples.any { ex -> ex.iupacName.lowercase().contains(q) || ex.commonName.lowercase().contains(q) || ex.formula.lowercase().contains(q) } ||
            group.reactions.any { rx -> rx.name.lowercase().contains(q) || rx.equation.lowercase().contains(q) || rx.reagentsAndCatalyst.lowercase().contains(q) }
        }
    }
}
