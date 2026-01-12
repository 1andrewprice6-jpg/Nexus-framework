package com.nexus.core.module

import androidx.compose.runtime.Composable

interface NexusModule {
    val id: String
    val displayName: String
    val description: String
    val iconRes: Int

    @Composable
    fun Content()

    suspend fun runAnalysis(): ModuleStatus
}

sealed class ModuleStatus {
    object Idle : ModuleStatus()
    object Running : ModuleStatus()
    data class Secure(val message: String) : ModuleStatus()
    data class Vulnerable(val message: String) : ModuleStatus()
}