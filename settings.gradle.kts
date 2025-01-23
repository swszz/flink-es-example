rootProject.apply {
    this.name = "example"
}

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

include(
    "indexer",
    "ats",
    "searchengine"
)

pluginManagement {
    repositories {
        gradlePluginPortal()
    }
}