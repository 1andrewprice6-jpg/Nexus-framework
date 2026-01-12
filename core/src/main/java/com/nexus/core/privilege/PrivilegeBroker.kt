package com.nexus.core.privilege

object PrivilegeBroker {
    private val rootProvider = RootPrivilegeProvider()
    private val shizukuProvider = ShizukuPrivilegeProvider()
    
    fun getBestProvider(): PrivilegeProvider {
        return when {
            rootProvider.isAvailable() -> rootProvider
            shizukuProvider.isAvailable() -> shizukuProvider
            else -> object : PrivilegeProvider {
                override val type = PrivilegeType.NONE
                override fun isAvailable() = true
                override suspend fun executeCommand(cmd: String) = CommandResult(false, "", "No privilege available")
            }
        }
    }

    suspend fun execute(command: String): CommandResult {
        return getBestProvider().executeCommand(command)
    }
}