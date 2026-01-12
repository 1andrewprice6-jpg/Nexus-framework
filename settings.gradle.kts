pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven("https://jitpack.io")
    }
}

rootProject.name = "Nexus-framework"
include(":app")
include(":core")
include(":modules:pattern-analyzer")
include(":modules:apk-inspector")
include(":modules:firmware-verifier")