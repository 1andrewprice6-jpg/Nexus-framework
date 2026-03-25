package com.nexus.framework

import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import rikka.shizuku.Shizuku
import com.nexus.framework.ui.NexusMainScreen
import com.nexus.framework.ui.theme.NexusFrameworkTheme

class MainActivity : ComponentActivity(), Shizuku.OnRequestPermissionResultListener {
    private val SHIZUKU_CODE = 1001

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Shizuku.addRequestPermissionResultListener(this)
        checkShizuku()
        setContent {
            NexusFrameworkTheme { NexusMainScreen() }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Shizuku.removeRequestPermissionResultListener(this)
    }

    override fun onRequestPermissionResult(requestCode: Int, grantResult: Int) {
        if (requestCode == SHIZUKU_CODE) {
            if (grantResult == PackageManager.PERMISSION_GRANTED) {
                Log.i("Nexus", "Shizuku permission granted")
            } else {
                Log.w("Nexus", "Shizuku permission denied")
            }
        }
    }

    private fun checkShizuku() {
        if (!Shizuku.isPreV11() && Shizuku.checkSelfPermission() != PackageManager.PERMISSION_GRANTED) {
            Shizuku.requestPermission(SHIZUKU_CODE)
        }
    }
}