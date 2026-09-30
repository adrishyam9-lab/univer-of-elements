package com.example.chemlabx.data.model

enum class OrganicCategory(val displayName: String, val colorHex: Long) {
    HYDROCARBON("Hydrocarbons", 0xFF10B981),
    OXYGEN_CONTAINING("Oxygen-Containing", 0xFF3B82F6),
    NITROGEN_CONTAINING("Nitrogen-Containing", 0xFF8B5CF6),
    HALOGENATED("Halogenated", 0xFFF59E0B),
    SULFUR_CONTAINING("Sulfur-Containing", 0xFFEC4899)
}

enum class OrganicReactionType(val displayName: String, val badgeColorHex: Long) {
    PREPARATION("Preparation / Synthesis", 0xFF10B981),
    CHEMICAL_PROPERTY("Chemical Reaction", 0xFF3B82F6),
    NAMED_REACTION("Named Reaction", 0xFF8B5CF6),
    TEST_IDENTIFICATION("Identification Test", 0xFFF59E0B)
}

data class OrganicExample(
    val iupacName: String,
    val commonName: String,
    val formula: String,
    val condensedFormula: String,
    val structure: String,
    val description: String
)

data class OrganicTest(
    val testName: String,
    val reagent: String,
    val observation: String,
    val explanation: String,
    val chemicalEquation: String
)

data class OrganicReaction(
    val id: String,
    val name: String,
    val reactionType: OrganicReactionType,
    val equation: String,
    val reactants: String,
    val products: String,
    val reagentsAndCatalyst: String,
    val conditions: String,
    val mechanismType: String,
    val mechanismSteps: List<String>,
    val keyObservation: String,
    val importance: String
)

data class OrganicFunctionalGroup(
    val id: String,
    val name: String,
    val hindiName: String,
    val category: OrganicCategory,
    val generalFormula: String,
    val condensedStructure: String,
    val visualStructure2D: String,
    val iupacSuffix: String,
    val iupacPrefix: String,
    val priorityOrder: Int, // 1 is highest priority in IUPAC seniority table
    val hybridization: String,
    val bondAngle: String,
    val polarity: String,
    val boilingPointTrend: String,
    val solubility: String,
    val acidityBasicity: String,
    val description: String,
    val iupacNamingRules: List<String>,
    val examples: List<OrganicExample>,
    val reactions: List<OrganicReaction>,
    val identificationTests: List<OrganicTest>
)
