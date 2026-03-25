package com.nexus.framework.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.nexus.core.module.ModuleManager
import com.nexus.core.privilege.PrivilegeBroker
import com.nexus.core.privilege.PrivilegeType
import com.nexus.framework.ui.animations.ModuleReveal
import com.nexus.framework.ui.components.NexusModuleCard
import com.nexus.framework.ui.components.PrivilegeLevelBadge

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NexusMainScreen() {
    val modules = ModuleManager.modules
    // Note: In real usage, use State<PrivilegeType> to trigger recomposition on change
    val privilegeProvider = PrivilegeBroker.getBestProvider()
    val privilegeType = privilegeProvider.type
    val isElevated = privilegeType == PrivilegeType.ROOT || privilegeType == PrivilegeType.SHIZUKU

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Nexus Framework") },
                actions = {
                    PrivilegeLevelBadge(
                        level = privilegeType.name,
                        elevated = isElevated,
                        modifier = Modifier.padding(end = 8.dp)
                    )
                }
            )
        }
    ) { padding ->
        LazyColumn(contentPadding = padding) {
            itemsIndexed(modules) { index, module ->
                ModuleReveal(index = index) {
                    NexusModuleCard(
                        title = module.displayName,
                        status = module.description,
                        isActive = isElevated,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }
            }
        }
    }
}