package com.example.chemlabx.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.Science
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.chemlabx.data.AppLanguage
import com.example.chemlabx.data.LocalizationManager

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChemLabTopBar(
    title: String = LocalizationManager.getString("app_title"),
    onSearchClick: () -> Unit = {},
    showSearch: Boolean = true
) {
    var langMenuExpanded by remember { mutableStateOf(false) }

    TopAppBar(
        title = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(32.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.primaryContainer),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Science,
                        contentDescription = "ChemLab Logo",
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(20.dp)
                    )
                }
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 0.5.sp
                    ),
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        },
        actions = {
            if (showSearch) {
                IconButton(
                    onClick = onSearchClick,
                    modifier = Modifier.testTag("topbar_search_button")
                ) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search chemistry",
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            }

            // Language Selector button
            Box {
                Surface(
                    onClick = { langMenuExpanded = true },
                    shape = RoundedCornerShape(16.dp),
                    color = MaterialTheme.colorScheme.surfaceVariant,
                    modifier = Modifier
                        .padding(end = 8.dp)
                        .testTag("language_selector_button")
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 6.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.Language,
                            contentDescription = "Language",
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = LocalizationManager.currentLanguage.code.uppercase(),
                            style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }

                DropdownMenu(
                    expanded = langMenuExpanded,
                    onDismissRequest = { langMenuExpanded = false }
                ) {
                    AppLanguage.entries.forEach { lang ->
                        DropdownMenuItem(
                            text = {
                                Text(
                                    "${lang.nativeName} (${lang.displayName})",
                                    fontWeight = if (LocalizationManager.currentLanguage == lang) FontWeight.Bold else FontWeight.Normal
                                )
                            },
                            onClick = {
                                LocalizationManager.currentLanguage = lang
                                langMenuExpanded = false
                            },
                            modifier = Modifier.testTag("lang_option_${lang.code}")
                        )
                    }
                }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.95f),
            titleContentColor = MaterialTheme.colorScheme.onSurface
        )
    )
}
