package com.example.chemlabx.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.GridOn
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Science
import androidx.compose.material.icons.filled.Stream
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.sp
import com.example.chemlabx.data.LocalizationManager
import com.example.chemlabx.ui.navigation.Screen

@Composable
fun ChemLabBottomNav(
    currentRoute: String?,
    onNavigate: (String) -> Unit
) {
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.surface,
        contentColor = MaterialTheme.colorScheme.onSurface
    ) {
        val items = listOf(
            Triple(Screen.Home.route, Icons.Default.Home, LocalizationManager.getString("nav_home")),
            Triple(Screen.PeriodicTable.route, Icons.Default.GridOn, LocalizationManager.getString("nav_periodic")),
            Triple(Screen.VirtualLab.route, Icons.Default.Science, LocalizationManager.getString("nav_lab")),
            Triple(Screen.MoleculeExplorer.route, Icons.Default.Stream, LocalizationManager.getString("nav_molecules")),
            Triple(Screen.ChemTutor.route, Icons.Default.AutoAwesome, "ChemTutor")
        )

        items.forEach { (route, icon, label) ->
            val isSelected = currentRoute == route
            NavigationBarItem(
                selected = isSelected,
                onClick = { onNavigate(route) },
                icon = { Icon(icon, contentDescription = label) },
                label = { Text(label, fontSize = 11.sp, maxLines = 1) },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.primary,
                    selectedTextColor = MaterialTheme.colorScheme.primary,
                    indicatorColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.4f),
                    unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant
                ),
                modifier = Modifier.testTag("nav_item_$route")
            )
        }
    }
}
