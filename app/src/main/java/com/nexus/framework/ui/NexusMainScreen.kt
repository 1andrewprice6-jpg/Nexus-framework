package com.nexus.framework.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.nexus.core.module.ModuleManager
import com.nexus.core.module.NexusModule
import com.nexus.core.privilege.PrivilegeBroker

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NexusMainScreen() {
    val modules = ModuleManager.modules
    // Note: In real usage, use State<PrivilegeType> to trigger recomposition on change
    val privilegeType = PrivilegeBroker.getBestProvider().type

    Scaffold(
        topBar = { 
            TopAppBar(
                title = { Text("Nexus Framework") },
                actions = { Text("Mode: $privilegeType", modifier = Modifier.padding(end = 16.dp)) }
            ) 
        }
    ) { padding ->
        LazyColumn(contentPadding = padding) {
            items(modules) { module ->
                ModuleCard(module)
            }
        }
    }
}

@Composable
fun ModuleCard(module: NexusModule) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(8.dp).clickable { },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(module.displayName, style = MaterialTheme.typography.titleMedium)
            Text(module.description, style = MaterialTheme.typography.bodyMedium)
        }
    }
}