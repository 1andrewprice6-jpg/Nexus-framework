package com.nexus.core.privilege

import android.content.pm.PackageManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import rikka.shizuku.Shizuku
import java.io.BufferedReader
import java.io.InputStreamReader

class ShizukuPrivilegeProvider : PrivilegeProvider {
    override val type = PrivilegeType.SHIZUKU

    override fun isAvailable(): Boolean {
        if (!Shizuku.pingBinder()) return false
        return try {
            Shizuku.checkSelfPermission() == PackageManager.PERMISSION_GRANTED
        } catch (e: Exception) { false }
    }

    override suspend fun executeCommand(command: String): CommandResult = withContext(Dispatchers.IO) {
        try {
            val process = Shizuku.newProcess(arrayOf("sh", "-c", command), null, null)
            val output = BufferedReader(InputStreamReader(process.inputStream)).readText()
            val error = BufferedReader(InputStreamReader(process.errorStream)).readText()
            val exitCode = process.waitFor()
            CommandResult(exitCode == 0, output.trim(), error.trim())
        } catch (e: Exception) {
            CommandResult(false, "", e.message ?: "Unknown Shizuku Error")
        }
    }
}