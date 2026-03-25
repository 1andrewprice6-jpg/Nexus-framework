package com.nexus.framework.ui.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nexus.framework.ui.theme.*

/** Cyberpunk holographic module card */
@Composable
fun NexusModuleCard(
    title: String,
    status: String,
    isActive: Boolean,
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit = {}
) {
    val glowAlpha by rememberInfiniteTransition(label = "glow").animateFloat(
        0.3f, 0.8f, infiniteRepeatable(tween(1500), RepeatMode.Reverse), "glow_alpha"
    )
    val borderColor = if (isActive) NexusCyan60 else NexusCyan20
    Card(
        modifier = modifier.fillMaxWidth().shadow(if (isActive) 16.dp else 4.dp, RoundedCornerShape(16.dp)),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        border = BorderStroke(if (isActive) 1.5.dp else 0.5.dp, borderColor.copy(if (isActive) glowAlpha else 0.4f))
    ) {
        Column(Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(Modifier.size(10.dp).clip(RoundedCornerShape(50))
                    .background(if (isActive) NexusGreen40 else NexusCyan20)
                    .graphicsLayer { alpha = if (isActive) glowAlpha else 0.5f })
                Spacer(Modifier.width(8.dp))
                Text(title, style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary
                ))
                Spacer(Modifier.weight(1f))
                Text(status, style = MaterialTheme.typography.labelSmall.copy(
                    color = if (isActive) NexusGreen40 else MaterialTheme.colorScheme.onSurface.copy(0.5f)
                ))
            }
            if (content != {}) {
                Spacer(Modifier.height(12.dp))
                content()
            }
        }
    }
}

/** Terminal-style text display */
@Composable
fun TerminalText(text: String, modifier: Modifier = Modifier, color: Color = NexusCyan60) {
    Text(
        text = text,
        style = MaterialTheme.typography.bodySmall.copy(
            fontFamily = FontFamily.Monospace,
            color = color,
            lineHeight = 20.sp
        ),
        modifier = modifier.clip(RoundedCornerShape(8.dp))
            .background(DarkNexusSurface)
            .padding(12.dp)
    )
}

/** Holographic scan progress bar */
@Composable
fun NexusScanBar(progress: Float, modifier: Modifier = Modifier) {
    val animProgress by animateFloatAsState(progress, tween(800, easing = FastOutSlowInEasing), label = "scan")
    Column(modifier = modifier) {
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text("SCAN", style = MaterialTheme.typography.labelSmall.copy(
                fontFamily = FontFamily.Monospace, color = NexusCyan60
            ))
            Text("${(animProgress * 100).toInt()}%", style = MaterialTheme.typography.labelSmall.copy(
                fontFamily = FontFamily.Monospace, color = NexusCyan60
            ))
        }
        Spacer(Modifier.height(4.dp))
        Box(Modifier.fillMaxWidth().height(8.dp).clip(RoundedCornerShape(4.dp)).background(NexusCyan20.copy(0.3f))) {
            Box(Modifier.fillMaxWidth(animProgress).fillMaxHeight()
                .clip(RoundedCornerShape(4.dp))
                .background(Brush.horizontalGradient(listOf(NexusCyan40, MatrixGreen.copy(0.8f)))))
        }
    }
}

/** Privilege level indicator */
@Composable
fun PrivilegeLevelBadge(level: String, elevated: Boolean, modifier: Modifier = Modifier) {
    val color = if (elevated) NexusRed80 else NexusCyan60
    Surface(modifier = modifier.clip(RoundedCornerShape(8.dp)), color = color.copy(0.15f)) {
        Row(Modifier.padding(horizontal = 10.dp, vertical = 5.dp), verticalAlignment = Alignment.CenterVertically) {
            Text(if (elevated) "⚡" else "🔒", fontSize = 12.sp)
            Spacer(Modifier.width(4.dp))
            Text(level, style = MaterialTheme.typography.labelMedium.copy(
                fontFamily = FontFamily.Monospace, color = color, fontWeight = FontWeight.Bold
            ))
        }
    }
}
