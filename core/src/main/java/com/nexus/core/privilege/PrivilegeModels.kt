package com.nexus.core.privilege

enum class PrivilegeType {
    ROOT, SHIZUKU, ADB, NONE
}

data class CommandResult(
    val success: Boolean,
    val output: String,
    val error: String
)

interface PrivilegeProvider {
    val type: PrivilegeType
    fun isAvailable(): Boolean
    suspend fun executeCommand(command: String): CommandResult
}