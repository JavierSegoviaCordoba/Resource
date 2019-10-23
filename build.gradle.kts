plugins {
    id(Plugins.versions) version Versions.versions
    id(Plugins.detekt) version Versions.detekt
}

allprojects {
    apply(plugin = Plugins.detekt)

    detekt {
        toolVersion = Versions.detekt
        ignoreFailures = true
    }
}
