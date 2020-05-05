fun String.generateVersion(isRelease: String): String {
    return "$this${if (isRelease.toBoolean()) "" else ".${System.currentTimeMillis()}-SNAPSHOT"}"
}
