package com.example.chemlabx.ui.navigation

sealed class Screen(val route: String) {
    data object Home : Screen("home")
    data object PeriodicTable : Screen("periodic_table")
    data object ElementDetail : Screen("element_detail/{atomicNumber}") {
        fun createRoute(atomicNumber: Int) = "element_detail/$atomicNumber"
    }
    data object MoleculeExplorer : Screen("molecule_explorer")
    data object MoleculeDetail : Screen("molecule_detail/{compoundId}") {
        fun createRoute(compoundId: String) = "molecule_detail/$compoundId"
    }
    data object VirtualLab : Screen("virtual_lab")
    data object ReactionSimulator : Screen("reaction_simulator")
    data object ExperimentLibrary : Screen("experiment_library")
    data object ExperimentDetail : Screen("experiment_detail/{experimentId}") {
        fun createRoute(experimentId: String) = "experiment_detail/$experimentId"
    }
    data object Quiz : Screen("quiz")
    data object ChemTutor : Screen("chemtutor")
    data object Progress : Screen("progress")
    data object Search : Screen("search")
    data object OrganicChemistry : Screen("organic_chemistry")
    data object FunctionalGroupDetail : Screen("functional_group_detail/{groupId}") {
        fun createRoute(groupId: String) = "functional_group_detail/$groupId"
    }
    data object OrganicReactionDetail : Screen("organic_reaction_detail/{reactionId}") {
        fun createRoute(reactionId: String) = "organic_reaction_detail/$reactionId"
    }
}

