
plugins {
    id("io.gitlab.arturbosch.detekt")
}

dependencies {
    detektPlugins(Dependencies.detektFormatting)
}

detekt {
    ignoreFailures = true
    autoCorrect = true

    val paths = File("$projectDir/src")
        .walkBottomUp()
        .maxDepth(1)
        .toMutableList()
        .apply { if (size > 0) removeAt(this.size - 1) }
        .toList()

    input = files(paths)

    reports {
        html {
            enabled = true
            destination = file("$buildDir/reports/detekt/detekt.html")
        }
        txt {
            enabled = false
        }
        xml {
            enabled = false
        }
    }
}
