package utils

internal class DataAndCounters {
    var loadingCount = 0
    var noLoadingCount = 0
    var successData = "NO_SUCCESS_DATA"
    var successCount = 0
    var successEmptyCount = 0
    var noSuccessCount = 0
    var errorData = "NO_ERROR_DATA"
    var errorCount = 0
    var errorEmptyCount = 0
    var noErrorCount = 0
    var cacheData = "NO_CACHE_DATA"
    var cacheCount = 0
    var cacheEmptyCount = 0
    var noCacheCount = 0

    internal fun clearAll() {
        loadingCount = 0
        noLoadingCount = 0
        successData = "NO_SUCCESS_DATA"
        successCount = 0
        successEmptyCount = 0
        noSuccessCount = 0
        errorData = "NO_ERROR_DATA"
        errorCount = 0
        errorEmptyCount = 0
        noErrorCount = 0
        noErrorCount = 0
        cacheData = "NO_CACHE_DATA"
        cacheCount = 0
        cacheEmptyCount = 0
        noCacheCount = 0
    }
}