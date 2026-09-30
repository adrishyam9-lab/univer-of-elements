package com.example.chemlabx.data.model

enum class ElementCategory(val displayName: String, val colorHex: Long) {
    ALKALI_METAL("Alkali Metal", 0xFFEF4444),
    ALKALINE_EARTH("Alkaline Earth Metal", 0xFFF97316),
    TRANSITION_METAL("Transition Metal", 0xFFF59E0B),
    POST_TRANSITION("Post-Transition Metal", 0xFF10B981),
    METALLOID("Metalloid", 0xFF14B8A6),
    REACTIVE_NONMETAL("Reactive Nonmetal", 0xFF06B6D4),
    HALOGEN("Halogen", 0xFF3B82F6),
    NOBLE_GAS("Noble Gas", 0xFF8B5CF6),
    LANTHANIDE("Lanthanide", 0xFFEC4899),
    ACTINIDE("Actinide", 0xFFD946EF),
    UNKNOWN("Unknown Properties", 0xFF64748B);

    companion object {
        fun fromString(name: String): ElementCategory = entries.firstOrNull {
            it.name.equals(name, ignoreCase = true) || it.displayName.equals(name, ignoreCase = true)
        } ?: UNKNOWN
    }
}

enum class Phase(val label: String) {
    GAS("Gas"),
    LIQUID("Liquid"),
    SOLID("Solid"),
    UNKNOWN("Unknown")
}

data class Element(
    val number: Int,
    val symbol: String,
    val name: String,
    val atomicMass: Double,
    val category: ElementCategory,
    val period: Int,
    val group: Int?, // null for Lanthanides and Actinides
    val block: String, // s, p, d, f
    val electronConfiguration: String,
    val shells: List<Int>,
    val valenceElectrons: Int,
    val electronegativity: Double?,
    val atomicRadiusPm: Int?,
    val ionizationEnergyKjMol: Double?,
    val electronAffinityKjMol: Double?,
    val meltingPointC: Double?,
    val boilingPointC: Double?,
    val densityGcm3: Double?,
    val phase: Phase,
    val oxidationStates: String,
    val commonIons: String,
    val discoveredBy: String,
    val yearDiscovered: String,
    val isSynthetic: Boolean,
    val applications: List<String>,
    val safety: String,
    val interestingFact: String,
    val citationSource: String = "IUPAC Standard Atomic Weights (2022) / NIST PML",
    val standardReference: String = "IUPAC Commission on Isotopic Abundances and Atomic Weights (CIAAW)"
)
