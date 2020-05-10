import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun String.generateVersion(isRelease: Boolean): String {
    return "$this${if (isRelease) "" else ".$YYYY_MM_DD_HH_mm_ss-SNAPSHOT"}"
}

private val YYYY_MM_DD_HH_mm_ss: String
    get() = SimpleDateFormat("yyyyMMddHHmmss", Locale.US).format(Date())
