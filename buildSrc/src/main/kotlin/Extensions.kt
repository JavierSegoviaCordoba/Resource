fun String.generateVersion(isRelease: Boolean): String {
    return "$this${if (isRelease) "" else ".${System.currentTimeMillis()}-SNAPSHOT"}"
}
