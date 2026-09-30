package com.example.chemlabx.ui.chemtutor

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Psychology
import androidx.compose.material.icons.filled.WorkspacePremium
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.chemlabx.ai.ChatMessage
import com.example.chemlabx.ai.ChemTutorService
import com.example.chemlabx.data.LocalizationManager
import com.example.chemlabx.data.SubscriptionManager
import com.example.chemlabx.ui.components.ChemLabTopBar
import kotlinx.coroutines.launch

@Composable
fun ChemTutorScreen(
    onSearchClick: () -> Unit = {}
) {
    var studentLevel by remember { mutableStateOf("Class 9–10") }
    var inputText by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }
    var showSubscriptionDialog by remember { mutableStateOf(false) }

    val levels = remember { listOf("Beginner", "Middle School", "Class 9–10", "Class 11–12", "Undergraduate") }
    val starterPrompts = remember {
        listOf(
            "Why does sodium react with chlorine?",
            "What is an ionic bond?",
            "Why did this precipitate form?",
            "Why is water bent at 104.5°?",
            "Explain catalysts like I'm in class 10."
        )
    }

    val messages = remember {
        mutableStateListOf(
            ChatMessage(
                sender = "tutor",
                text = "Hello! I am **ChemTutor**, your AI Chemistry mentor in ChemLab X. Ask me about atomic models, bonding mechanisms, reaction driving forces, or lab observations! (3 free questions included, or upgrade to PRO for $3/month)"
            )
        )
    }

    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(messages.size) {
        listState.animateScrollToItem(messages.size - 1)
    }

    fun handleSend(textToSend: String) {
        val trimmed = textToSend.trim()
        if (trimmed.isBlank() || isLoading) return

        if (!SubscriptionManager.canAskQuestion()) {
            showSubscriptionDialog = true
            return
        }

        SubscriptionManager.recordQuestionAsked()
        messages.add(ChatMessage(sender = "user", text = trimmed))
        inputText = ""
        isLoading = true

        coroutineScope.launch {
            val reply = ChemTutorService.askTutor(trimmed, studentLevel)
            messages.add(ChatMessage(sender = "tutor", text = reply))
            isLoading = false
        }
    }

    Scaffold(
        topBar = {
            ChemLabTopBar(
                title = "ChemTutor AI",
                onSearchClick = onSearchClick
            )
        },
        containerColor = MaterialTheme.colorScheme.background
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            // Student Level Selector Row
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.surface)
                    .padding(horizontal = 16.dp, vertical = 6.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Level: ",
                    style = MaterialTheme.typography.labelSmall,
                    color = Color(0xFF94A3B8)
                )
                LazyRow(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                    items(levels) { lvl ->
                        FilterChip(
                            selected = studentLevel == lvl,
                            onClick = { studentLevel = lvl },
                            label = { Text(lvl, fontSize = 11.sp) },
                            colors = FilterChipDefaults.filterChipColors(
                                selectedContainerColor = MaterialTheme.colorScheme.primaryContainer,
                                selectedLabelColor = MaterialTheme.colorScheme.onPrimaryContainer
                            ),
                            modifier = Modifier.testTag("tutor_level_$lvl")
                        )
                    }
                }
            }

            // Subscription Quota & PRO Status Banner Bar
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF0F172A))
                    .padding(horizontal = 16.dp, vertical = 6.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (SubscriptionManager.isProSubscribed) {
                    Surface(
                        shape = RoundedCornerShape(12.dp),
                        color = Color(0xFF10B981).copy(alpha = 0.15f),
                        border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFF10B981).copy(alpha = 0.4f))
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                Icons.Default.WorkspacePremium,
                                contentDescription = null,
                                tint = Color(0xFF34D399),
                                modifier = Modifier.size(14.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = LocalizationManager.getString("subscription_active"),
                                fontSize = 11.sp,
                                color = Color(0xFF34D399),
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                } else {
                    val remaining = SubscriptionManager.remainingFreeQuestions()
                    Surface(
                        onClick = { showSubscriptionDialog = true },
                        shape = RoundedCornerShape(12.dp),
                        color = if (remaining > 0) Color(0xFF38BDF8).copy(alpha = 0.12f) else Color(0xFFEF4444).copy(alpha = 0.15f),
                        border = androidx.compose.foundation.BorderStroke(
                            1.dp,
                            if (remaining > 0) Color(0xFF38BDF8).copy(alpha = 0.35f) else Color(0xFFEF4444).copy(alpha = 0.4f)
                        ),
                        modifier = Modifier.testTag("free_quota_chip")
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                if (remaining > 0) Icons.Default.AutoAwesome else Icons.Default.Lock,
                                contentDescription = null,
                                tint = if (remaining > 0) Color(0xFF38BDF8) else Color(0xFFF87171),
                                modifier = Modifier.size(13.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = String.format(LocalizationManager.getString("chemtutor_free_badge"), remaining),
                                fontSize = 11.sp,
                                color = if (remaining > 0) Color(0xFF38BDF8) else Color(0xFFF87171),
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                    }
                }

                // Upgrade / Pro status button
                Surface(
                    onClick = { showSubscriptionDialog = true },
                    shape = RoundedCornerShape(12.dp),
                    color = Color(0xFFF59E0B).copy(alpha = 0.15f),
                    border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFF59E0B).copy(alpha = 0.4f)),
                    modifier = Modifier.testTag("open_subscription_button")
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            Icons.Default.WorkspacePremium,
                            contentDescription = null,
                            tint = Color(0xFFFBBF24),
                            modifier = Modifier.size(13.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = if (SubscriptionManager.isProSubscribed) "PRO Plan ($3/mo)" else "Upgrade ($3/mo)",
                            fontSize = 11.sp,
                            color = Color(0xFFFBBF24),
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }

            // Chat Messages Scroll
            LazyColumn(
                state = listState,
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 16.dp),
                contentPadding = PaddingValues(vertical = 12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(messages, key = { it.id }) { msg ->
                    ChatBubble(message = msg)
                }

                if (isLoading) {
                    item {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(start = 12.dp)
                        ) {
                            CircularProgressIndicator(
                                modifier = Modifier.size(18.dp),
                                strokeWidth = 2.dp,
                                color = MaterialTheme.colorScheme.primary
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "ChemTutor is analyzing chemical principles...",
                                style = MaterialTheme.typography.bodySmall,
                                color = Color(0xFF94A3B8)
                            )
                        }
                    }
                }
            }

            // Paywall Banner Card if user exhausted free questions
            if (!SubscriptionManager.isProSubscribed && SubscriptionManager.remainingFreeQuestions() == 0) {
                Surface(
                    onClick = { showSubscriptionDialog = true },
                    color = Color(0xFF1E293B),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 6.dp)
                        .testTag("paywall_banner_card"),
                    border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFF59E0B).copy(alpha = 0.6f))
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 10.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(32.dp)
                                .clip(CircleShape)
                                .background(Color(0xFFF59E0B).copy(alpha = 0.2f)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                Icons.Default.WorkspacePremium,
                                contentDescription = null,
                                tint = Color(0xFFFBBF24),
                                modifier = Modifier.size(18.dp)
                            )
                        }
                        Spacer(modifier = Modifier.width(10.dp))
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = LocalizationManager.getString("subscribe_banner_title"),
                                fontWeight = FontWeight.Bold,
                                fontSize = 13.sp,
                                color = Color.White
                            )
                            Text(
                                text = LocalizationManager.getString("subscribe_banner_desc"),
                                fontSize = 11.sp,
                                color = Color(0xFF94A3B8),
                                maxLines = 2
                            )
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        Surface(
                            color = Color(0xFF38BDF8),
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            Text(
                                text = "$3/mo",
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 5.dp),
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.Black
                            )
                        }
                    }
                }
            }

            // Quick Starter Chips
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 6.dp),
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                items(starterPrompts) { prompt ->
                    Surface(
                        onClick = { handleSend(prompt) },
                        shape = RoundedCornerShape(16.dp),
                        color = Color(0xFF1E293B)
                    ) {
                        Text(
                            text = prompt,
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                            style = MaterialTheme.typography.labelSmall,
                            color = Color(0xFF38BDF8)
                        )
                    }
                }
            }

            // Text Input Bar
            Surface(
                color = MaterialTheme.colorScheme.surface,
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp, vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    OutlinedTextField(
                        value = inputText,
                        onValueChange = { inputText = it },
                        modifier = Modifier
                            .weight(1f)
                            .testTag("chemtutor_input_field"),
                        placeholder = {
                            val hint = if (!SubscriptionManager.canAskQuestion()) {
                                "Free limit reached. Subscribe for $3 to ask..."
                            } else {
                                LocalizationManager.getString("type_question_hint")
                            }
                            Text(hint, fontSize = 13.sp)
                        },
                        maxLines = 3,
                        shape = RoundedCornerShape(20.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = MaterialTheme.colorScheme.primary,
                            unfocusedBorderColor = MaterialTheme.colorScheme.outline
                        )
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    IconButton(
                        onClick = { handleSend(inputText) },
                        enabled = inputText.isNotBlank() && !isLoading,
                        modifier = Modifier
                            .size(44.dp)
                            .clip(CircleShape)
                            .background(
                                if (inputText.isNotBlank() && !isLoading) {
                                    MaterialTheme.colorScheme.primary
                                } else {
                                    Color(0xFF334155)
                                }
                            )
                            .testTag("chemtutor_send_button")
                    ) {
                        Icon(
                            Icons.AutoMirrored.Filled.Send,
                            contentDescription = "Send",
                            tint = if (inputText.isNotBlank() && !isLoading) Color.Black else Color(0xFF64748B),
                            modifier = Modifier.size(18.dp)
                        )
                    }
                }
            }
        }
    }

    if (showSubscriptionDialog) {
        SubscriptionDialog(
            onDismiss = { showSubscriptionDialog = false },
            onSubscribed = { showSubscriptionDialog = false }
        )
    }
}

@Composable
private fun ChatBubble(message: ChatMessage) {
    val isUser = message.sender == "user"

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = if (isUser) Arrangement.End else Arrangement.Start
    ) {
        if (!isUser) {
            Box(
                modifier = Modifier
                    .size(28.dp)
                    .clip(CircleShape)
                    .background(Color(0xFF8B5CF6).copy(alpha = 0.3f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    Icons.Default.AutoAwesome,
                    contentDescription = "AI",
                    tint = Color(0xFFA78BFA),
                    modifier = Modifier.size(16.dp)
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
        }

        Surface(
            shape = RoundedCornerShape(
                topStart = 16.dp,
                topEnd = 16.dp,
                bottomStart = if (isUser) 16.dp else 4.dp,
                bottomEnd = if (isUser) 4.dp else 16.dp
            ),
            color = if (isUser) MaterialTheme.colorScheme.primaryContainer else Color(0xFF1E293B),
            modifier = Modifier.widthIn(max = 280.dp)
        ) {
            Text(
                text = message.text,
                modifier = Modifier.padding(12.dp),
                style = MaterialTheme.typography.bodyMedium,
                color = if (isUser) MaterialTheme.colorScheme.onPrimaryContainer else Color(0xFFF1F5F9),
                lineHeight = 20.sp
            )
        }
    }
}
