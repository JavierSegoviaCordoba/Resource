package utils

internal class DataAndCounters {
    var loadingCount = 0
    var noLoadingCount = 0
    var successData = NO_SUCCESS_DATA
    var successCount = 0
    var noSuccessCount = 0
    var errorData = NO_ERROR_DATA
    var errorCount = 0
    var noErrorCount = 0

    internal fun clearAll() {
        loadingCount = 0
        noLoadingCount = 0
        successData = NO_SUCCESS_DATA
        successCount = 0
        noSuccessCount = 0
        errorData = NO_ERROR_DATA
        errorCount = 0
        noErrorCount = 0
        noErrorCount = 0
    }

    companion object {
        const val SUCCESS_DATA = "SUCCESS_DATA"
        const val NO_SUCCESS_DATA = "NO_SUCCESS_DATA"
        const val SUCCESS_DATA_2 = "NO_SUCCESS_DATA_2"
        const val ERROR_DATA = "ERROR_DATA"
        const val NO_ERROR_DATA = "NO_ERROR_DATA"
        const val ERROR_DATA_2 = "NO_ERROR_DATA_"
    }
}
