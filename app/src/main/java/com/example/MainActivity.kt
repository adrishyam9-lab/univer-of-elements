package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.chemlabx.ui.chemtutor.ChemTutorScreen
import com.example.chemlabx.ui.components.ChemLabBottomNav
import com.example.chemlabx.ui.experiments.ExperimentDetailScreen
import com.example.chemlabx.ui.experiments.ExperimentLibraryScreen
import com.example.chemlabx.ui.home.HomeScreen
import com.example.chemlabx.ui.lab.ReactionSimulatorScreen
import com.example.chemlabx.ui.lab.VirtualLabScreen
import com.example.chemlabx.ui.molecules.MoleculeDetailScreen
import com.example.chemlabx.ui.molecules.MoleculeExplorerScreen
import com.example.chemlabx.ui.navigation.Screen
import com.example.chemlabx.ui.periodictable.ElementDetailScreen
import com.example.chemlabx.ui.periodictable.PeriodicTableScreen
import com.example.chemlabx.ui.organic.FunctionalGroupDetailScreen
import com.example.chemlabx.ui.organic.OrganicChemistryDashboardScreen
import com.example.chemlabx.ui.organic.OrganicReactionDetailScreen
import com.example.chemlabx.ui.progress.ProgressScreen
import com.example.chemlabx.ui.quiz.QuizScreen
import com.example.chemlabx.ui.search.ChemistrySearchScreen
import com.example.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        com.example.chemlabx.data.SubscriptionManager.init(this)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                ChemLabApp()
            }
        }
    }
}

@Composable
fun ChemLabApp() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val showBottomNav = currentRoute in listOf(
        Screen.Home.route,
        Screen.PeriodicTable.route,
        Screen.VirtualLab.route,
        Screen.MoleculeExplorer.route,
        Screen.ChemTutor.route
    )

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            if (showBottomNav) {
                ChemLabBottomNav(
                    currentRoute = currentRoute,
                    onNavigate = { route ->
                        navController.navigate(route) {
                            popUpTo(Screen.Home.route) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.route) {
                HomeScreen(
                    onNavigate = { route -> navController.navigate(route) },
                    onSearchClick = { navController.navigate(Screen.Search.route) }
                )
            }

            composable(Screen.PeriodicTable.route) {
                PeriodicTableScreen(
                    onElementClick = { atomicNumber ->
                        navController.navigate(Screen.ElementDetail.createRoute(atomicNumber))
                    },
                    onSearchClick = { navController.navigate(Screen.Search.route) }
                )
            }

            composable(
                route = Screen.ElementDetail.route,
                arguments = listOf(navArgument("atomicNumber") { type = NavType.IntType })
            ) { backStackEntry ->
                val atomicNumber = backStackEntry.arguments?.getInt("atomicNumber") ?: 1
                ElementDetailScreen(
                    atomicNumber = atomicNumber,
                    onBackClick = { navController.popBackStack() },
                    onOpenInLabClick = { symbol ->
                        navController.navigate(Screen.VirtualLab.route)
                    }
                )
            }

            composable(Screen.MoleculeExplorer.route) {
                MoleculeExplorerScreen(
                    onCompoundClick = { compoundId ->
                        navController.navigate(Screen.MoleculeDetail.createRoute(compoundId))
                    },
                    onSearchClick = { navController.navigate(Screen.Search.route) }
                )
            }

            composable(
                route = Screen.MoleculeDetail.route,
                arguments = listOf(navArgument("compoundId") { type = NavType.StringType })
            ) { backStackEntry ->
                val compoundId = backStackEntry.arguments?.getString("compoundId") ?: "water"
                MoleculeDetailScreen(
                    compoundId = compoundId,
                    onBackClick = { navController.popBackStack() }
                )
            }

            composable(Screen.VirtualLab.route) {
                VirtualLabScreen(
                    onSearchClick = { navController.navigate(Screen.Search.route) },
                    onViewReactionDetail = { reactionId ->
                        navController.navigate(Screen.ReactionSimulator.route)
                    }
                )
            }

            composable(Screen.ReactionSimulator.route) {
                ReactionSimulatorScreen(
                    onSearchClick = { navController.navigate(Screen.Search.route) }
                )
            }

            composable(Screen.ExperimentLibrary.route) {
                ExperimentLibraryScreen(
                    onExperimentClick = { expId ->
                        navController.navigate(Screen.ExperimentDetail.createRoute(expId))
                    },
                    onSearchClick = { navController.navigate(Screen.Search.route) }
                )
            }

            composable(
                route = Screen.ExperimentDetail.route,
                arguments = listOf(navArgument("experimentId") { type = NavType.StringType })
            ) { backStackEntry ->
                val expId = backStackEntry.arguments?.getString("experimentId") ?: "exp_acid_base_titration"
                ExperimentDetailScreen(
                    experimentId = expId,
                    onBackClick = { navController.popBackStack() }
                )
            }

            composable(Screen.Quiz.route) {
                QuizScreen(
                    onSearchClick = { navController.navigate(Screen.Search.route) }
                )
            }

            composable(Screen.ChemTutor.route) {
                ChemTutorScreen(
                    onSearchClick = { navController.navigate(Screen.Search.route) }
                )
            }

            composable(Screen.Progress.route) {
                ProgressScreen(
                    onSearchClick = { navController.navigate(Screen.Search.route) }
                )
            }

            composable(Screen.Search.route) {
                ChemistrySearchScreen(
                    onBackClick = { navController.popBackStack() },
                    onElementClick = { atomicNumber ->
                        navController.navigate(Screen.ElementDetail.createRoute(atomicNumber))
                    },
                    onCompoundClick = { compoundId ->
                        navController.navigate(Screen.MoleculeDetail.createRoute(compoundId))
                    },
                    onReactionClick = { reactionId ->
                        navController.navigate(Screen.ReactionSimulator.route)
                    },
                    onExperimentClick = { expId ->
                        navController.navigate(Screen.ExperimentDetail.createRoute(expId))
                    }
                )
            }

            composable(Screen.OrganicChemistry.route) {
                OrganicChemistryDashboardScreen(
                    onNavigateBack = { navController.popBackStack() },
                    onNavigateToGroup = { groupId ->
                        navController.navigate(Screen.FunctionalGroupDetail.createRoute(groupId))
                    },
                    onNavigateToReaction = { rxId ->
                        navController.navigate(Screen.OrganicReactionDetail.createRoute(rxId))
                    },
                    onSearchClick = { navController.navigate(Screen.Search.route) }
                )
            }

            composable(
                route = Screen.FunctionalGroupDetail.route,
                arguments = listOf(navArgument("groupId") { type = NavType.StringType })
            ) { backStackEntry ->
                val groupId = backStackEntry.arguments?.getString("groupId") ?: "alkanes"
                FunctionalGroupDetailScreen(
                    groupId = groupId,
                    onNavigateBack = { navController.popBackStack() },
                    onNavigateToReaction = { rxId ->
                        navController.navigate(Screen.OrganicReactionDetail.createRoute(rxId))
                    }
                )
            }

            composable(
                route = Screen.OrganicReactionDetail.route,
                arguments = listOf(navArgument("reactionId") { type = NavType.StringType })
            ) { backStackEntry ->
                val reactionId = backStackEntry.arguments?.getString("reactionId") ?: ""
                OrganicReactionDetailScreen(
                    reactionId = reactionId,
                    onNavigateBack = { navController.popBackStack() },
                    onNavigateToGroup = { groupId ->
                        navController.navigate(Screen.FunctionalGroupDetail.createRoute(groupId))
                    }
                )
            }
        }
    }
}
