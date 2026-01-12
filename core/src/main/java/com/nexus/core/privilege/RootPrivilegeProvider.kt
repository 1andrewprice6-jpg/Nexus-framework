package com.nexus.core.privilege

import com.topjohnwu.superuser.Shell
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RootPrivilegeProvider : PrivilegeProvider {
    override val type = PrivilegeType.ROOT
    
    override fun isAvailable() = Shell.rootAccess()

    override suspend fun executeCommand(command: String): CommandResult = withContext(Dispatchers.IO) {
        val result = Shell.cmd(command).exec()
        CommandResult(result.isSuccess, result.out.joinToString("\n"), result.err.joinToString("\n"))
    }
}