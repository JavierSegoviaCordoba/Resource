package plugins.extensions

import org.gradle.api.Project
import java.io.FileNotFoundException
import java.util.Properties

internal val Project.localProperties: Properties?
    get() {
        return try {
            Properties().apply { load(rootProject.file("local.properties").inputStream()) }
        } catch (e: FileNotFoundException) {
            println(e)
            null
        }
    }
