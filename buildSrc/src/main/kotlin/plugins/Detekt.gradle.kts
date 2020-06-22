import io.gitlab.arturbosch.detekt.Detekt

plugins {
    id("io.gitlab.arturbosch.detekt")
}

dependencies {
    detektPlugins(Dependencies.detektFormatting)
}

val detekt by tasks.named<Detekt>("detekt") {
    parallel = true
    ignoreFailures = true
    autoCorrect = true
    buildUponDefaultConfig = true
    setSource(files(projectDir))
    exclude("**/build/**")

    reports {
        html {
            enabled = true
            destination = file("$buildDir/reports/detekt/detekt.html")
        }
        txt { enabled = false }
        xml { enabled = false }
    }
}
