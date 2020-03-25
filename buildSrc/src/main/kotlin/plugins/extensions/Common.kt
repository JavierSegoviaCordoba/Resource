package plugins.extensions

import org.gradle.api.Project
import java.util.*

internal val Project.localProperties
    get() = Properties().apply { load(rootProject.file("local.properties").inputStream()) }