package com.nexus.framework

import android.app.Application
import android.util.Log
import rikka.shizuku.Shizuku
import com.nexus.core.module.ModuleManager
import com.nexus.modules.pattern.PatternLockModule
import com.nexus.modules.apk.ApkSignatureModule
import com.nexus.modules.firmware.FirmwareVerifierModule

class NexusApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        
        Shizuku.addBinderReceivedListener { Log.i("Nexus", "Shizuku Binder Received") }
        Shizuku.addBinderDeadListener { Log.w("Nexus", "Shizuku Binder Dead") }
        
        // Register Modules
        ModuleManager.register(PatternLockModule())
        ModuleManager.register(ApkSignatureModule())
        ModuleManager.register(FirmwareVerifierModule())
    }
}