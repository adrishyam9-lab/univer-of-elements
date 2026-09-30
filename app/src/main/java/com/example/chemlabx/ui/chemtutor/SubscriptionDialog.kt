package com.example.chemlabx.ui.chemtutor

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.WorkspacePremium
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.chemlabx.data.LocalizationManager
import com.example.chemlabx.data.SubscriptionManager

@Composable
fun SubscriptionDialog(
    onDismiss: () -> Unit,
    onSubscribed: () -> Unit = {}
) {
    var isSuccess by remember { mutableStateOf(false) }

    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth(0.92f)
                .padding(vertical = 16.dp),
            shape = RoundedCornerShape(24.dp),
            color = Color(0xFF0F172A),
            tonalElevation = 8.dp,
            border = androidx.compose.foundation.BorderStroke(
                1.5.dp,
                Brush.verticalGradient(
                    listOf(
                        Color(0xFF38BDF8),
                        Color(0xFF818CF8),
                        Color(0xFF1E293B)
                    )
                )
            )
        ) {
            Column(
                modifier = Modifier
                    .padding(20.dp)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Top close button
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    IconButton(
                        onClick = onDismiss,
                        modifier = Modifier.size(32.dp)
                    ) {
                        Icon(
                            Icons.Default.Close,
                            contentDescription = "Close",
                            tint = Color(0xFF94A3B8)
                        )
                    }
                }

                if (isSuccess) {
                    // Success View
                    Box(
                        modifier = Modifier
                            .size(72.dp)
                            .clip(CircleShape)
                            .background(Color(0xFF10B981).copy(alpha = 0.2f)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            Icons.Default.Check,
                            contentDescription = "Success",
                            tint = Color(0xFF34D399),
                            modifier = Modifier.size(40.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "ChemTutor PRO Activated!",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = LocalizationManager.getString("subscription_success_message"),
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color(0xFF94A3B8),
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    Button(
                        onClick = {
                            onSubscribed()
                            onDismiss()
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("subscription_continue_button"),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.primary,
                            contentColor = Color.Black
                        ),
                        shape = RoundedCornerShape(14.dp)
                    ) {
                        Text("Start Exploring Chemistry", fontWeight = FontWeight.Bold)
                    }
                } else {
                    // Subscription Plan Details
                    Box(
                        modifier = Modifier
                            .size(60.dp)
                            .clip(CircleShape)
                            .background(
                                Brush.linearGradient(
                                    listOf(Color(0xFF38BDF8), Color(0xFFA855F7))
                                )
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            Icons.Default.WorkspacePremium,
                            contentDescription = "PRO",
                            tint = Color.White,
                            modifier = Modifier.size(36.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = LocalizationManager.getString("chemtutor_pro_title"),
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = "Unlock infinite chemistry reasoning & unlimited AI tutor sessions",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color(0xFF94A3B8),
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Price Card
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(containerColor = Color(0xFF1E293B)),
                        shape = RoundedCornerShape(16.dp),
                        border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFF38BDF8).copy(alpha = 0.4f))
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Row(
                                verticalAlignment = Alignment.Bottom,
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Text(
                                    text = "$3",
                                    fontSize = 40.sp,
                                    fontWeight = FontWeight.ExtraBold,
                                    color = Color(0xFF38BDF8)
                                )
                                Text(
                                    text = " / month",
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Medium,
                                    color = Color(0xFFCBD5E1),
                                    modifier = Modifier.padding(bottom = 6.dp, start = 4.dp)
                                )
                            }
                            Text(
                                text = "Cancel anytime • Renews monthly",
                                style = MaterialTheme.typography.labelSmall,
                                color = Color(0xFF64748B)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Perks List
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        SubscriptionPerkItem(LocalizationManager.getString("subscription_perks_1"))
                        SubscriptionPerkItem(LocalizationManager.getString("subscription_perks_2"))
                        SubscriptionPerkItem(LocalizationManager.getString("subscription_perks_3"))
                        SubscriptionPerkItem(LocalizationManager.getString("subscription_perks_4"))
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    // Subscribe Button
                    Button(
                        onClick = {
                            SubscriptionManager.subscribePro()
                            isSuccess = true
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                            .testTag("subscribe_button"),
                        shape = RoundedCornerShape(14.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF38BDF8),
                            contentColor = Color.Black
                        )
                    ) {
                        Icon(
                            Icons.Default.AutoAwesome,
                            contentDescription = null,
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = LocalizationManager.getString("subscribe_cta_button"),
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    // Secondary actions: Dismiss or Test Reset
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        TextButton(
                            onClick = {
                                SubscriptionManager.resetForTesting()
                                onDismiss()
                            }
                        ) {
                            Text(
                                text = LocalizationManager.getString("reset_test_quota"),
                                style = MaterialTheme.typography.labelSmall,
                                color = Color(0xFF64748B)
                            )
                        }

                        TextButton(onClick = onDismiss) {
                            Text(
                                text = LocalizationManager.getString("cancel_or_restore"),
                                style = MaterialTheme.typography.labelSmall,
                                color = Color(0xFF94A3B8)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun SubscriptionPerkItem(text: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(20.dp)
                .clip(CircleShape)
                .background(Color(0xFF38BDF8).copy(alpha = 0.2f)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                Icons.Default.Check,
                contentDescription = null,
                tint = Color(0xFF38BDF8),
                modifier = Modifier.size(12.dp)
            )
        }
        Spacer(modifier = Modifier.width(10.dp))
        Text(
            text = text,
            style = MaterialTheme.typography.bodySmall,
            color = Color(0xFFE2E8F0),
            fontSize = 12.sp
        )
    }
}
