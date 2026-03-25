package com.nexus.framework.ui.animations

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.runtime.*

val nexusEnterTransition: EnterTransition = fadeIn(tween(250)) + expandVertically(spring(Spring.DampingRatioMediumBouncy))
val nexusExitTransition: ExitTransition = fadeOut(tween(200)) + shrinkVertically(tween(200))

@Composable
fun ScanLineProgress(): Float {
    val t = rememberInfiniteTransition(label = "scan")
    return t.animateFloat(0f, 1f, infiniteRepeatable(tween(1500, easing = LinearEasing)), "scan_pos").value
}

@Composable
fun NexusGlowPulse(): Float {
    val t = rememberInfiniteTransition(label = "glow")
    return t.animateFloat(0.4f, 1f, infiniteRepeatable(tween(1200), RepeatMode.Reverse), "glow").value
}

@Composable
fun ModuleReveal(index: Int, modifier: androidx.compose.ui.Modifier = androidx.compose.ui.Modifier, content: @Composable () -> Unit) {
    var visible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { kotlinx.coroutines.delay(index * 80L); visible = true }
    AnimatedVisibility(
        visible = visible,
        enter = fadeIn(tween(350)) + slideInHorizontally(tween(350)) { -it / 3 },
        modifier = modifier
    ) { content() }
}

@Composable
fun StatusTransition(visible: Boolean, modifier: androidx.compose.ui.Modifier = androidx.compose.ui.Modifier, content: @Composable AnimatedVisibilityScope.() -> Unit) {
    AnimatedVisibility(
        visible = visible,
        enter = expandHorizontally(spring(Spring.DampingRatioLowBouncy)) + fadeIn(tween(200)),
        exit = shrinkHorizontally(tween(200)) + fadeOut(tween(200)),
        modifier = modifier, content = content
    )
}
