package plugins.extensions

import org.gradle.api.Project
import java.util.*

internal val Project.localProperties: Properties?
    get() {
        return try {
            Properties().apply { load(rootProject.file("local.properties").inputStream()) }
        } catch (e: Throwable) {
            null
        }
    }
