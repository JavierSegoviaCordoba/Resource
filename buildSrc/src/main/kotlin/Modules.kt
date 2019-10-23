object Modules {

    const val buildSrc = ":buildSrc"
    const val resource = ":resource"

    object Demo {
        const val app = ":demo:app"
        const val backend = ":demo:backend"
    }

    val all = listOf(buildSrc, resource, Demo.app, Demo.backend)

}