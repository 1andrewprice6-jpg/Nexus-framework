package com.nexus.framework.ui.theme

import androidx.compose.runtime.Composable

@Composable
fun NexusTheme(content: @Composable () -> Unit) {
    // Add your theming logic here, or wrap with MaterialTheme if using Material Design:
    // MaterialTheme { content() }
    content()
}