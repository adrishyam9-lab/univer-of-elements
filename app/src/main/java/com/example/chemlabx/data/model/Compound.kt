package com.example.chemlabx.data.model

enum class BondType(val label: String) {
    COVALENT_NONPOLAR("Non-polar Covalent"),
    COVALENT_POLAR("Polar Covalent"),
    IONIC("Ionic"),
    COORDINATE("Coordinate (Dative)"),
    METALLIC("Metallic")
}

data class Atom3D(
    val elementSymbol: String,
    val x: Float, // relative 3D coordinates
    val y: Float,
    val z: Float,
    val colorHex: Long,
    val radiusRatio: Float = 1.0f
)

data class ChemicalBond(
    val fromAtomIndex: Int,
    val toAtomIndex: Int,
    val order: Int = 1, // 1 = single, 2 = double, 3 = triple
    val bondType: BondType = BondType.COVALENT_POLAR,
    val lengthPm: Int? = null
)

data class Compound(
    val id: String,
    val name: String,
    val iupacName: String,
    val formula: String,
    val category: String, // Inorganic, Organic, Acid, Base, Salt, Oxide
    val molarMass: Double,
    val geometry: String,
    val bondAngles: String,
    val polarity: String,
    val functionalGroups: List<String>,
    val atoms: List<Atom3D>,
    val bonds: List<ChemicalBond>,
    val lewisNotes: String,
    val description: String,
    val safetyInfo: String,
    val bondExplanation: String,
    val pubchemCid: Int? = null,
    val casNumber: String? = null,
    val citationSource: String = "PubChem CID / NIST Chemistry WebBook SRD 69",
    val isDangerousSimulationOnly: Boolean = false,
    val safetyHazards: List<String> = emptyList()
)
