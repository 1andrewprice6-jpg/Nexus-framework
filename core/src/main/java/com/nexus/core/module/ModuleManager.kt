package com.nexus.core.module

object ModuleManager {
    private val _modules = mutableListOf<NexusModule>()
    val modules: List<NexusModule> get() = _modules.toList()

    fun register(module: NexusModule) {
        if (_modules.none { it.id == module.id }) {
            _modules.add(module)
        }
    }
}