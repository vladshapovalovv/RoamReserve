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
    }
}

rootProject.name = "RoamReserve"
include(":app")
include(":feature:hotel:overview")
include(":feature:hotel:room")
include(":feature:hotel:booking")
include(":component:designsystem")
include(":util:formatter")
