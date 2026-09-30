package com.example.chemlabx.data.model

data class ReactionStage(
    val title: String,
    val description: String,
    val atomicDescription: String,
    val visualState: String // e.g. "APPROACH", "COLLISION_TRANSITION", "BOND_REORGANIZATION", "PRODUCTS_STABILIZED"
)

data class Reaction(
    val id: String,
    val name: String,
    val equation: String,
    val reactants: List<String>, // e.g. ["HCl", "NaOH"]
    val products: List<String>, // e.g. ["NaCl", "H2O"]
    val reactionType: String,
    val observableChanges: String,
    val energyChange: String, // e.g. "Exothermic (ΔH = -57.3 kJ/mol)"
    val conditions: String,
    val safetyWarning: String,
    val whyItHappens: String,
    val atomicLevel: String,
    val macroscopicLevel: String,
    val stages: List<ReactionStage>,
    val citationSource: String = "CRC Handbook of Chemistry and Physics / NIST WebBook",
    val isBalanced: Boolean = true,
    val isDangerousSimulationOnly: Boolean = false,
    val simulationWarningNotice: String? = null
)
