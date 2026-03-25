package com.nexus.framework

import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import rikka.shizuku.Shizuku
import com.nexus.framework.ui.NexusMainScreen
import com.nexus.framework.ui.theme.NexusFrameworkTheme

class MainActivity : ComponentActivity() {
    private val SHIZUKU_CODE = 1001

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        checkShizuku()
        setContent {
            NexusFrameworkTheme { NexusMainScreen() }
        }
    }

    private fun checkShizuku() {
        if (!Shizuku.isPreV11() && Shizuku.checkSelfPermission() != PackageManager.PERMISSION_GRANTED) {
            Shizuku.requestPermission(SHIZUKU_CODE)
        }
    }
}