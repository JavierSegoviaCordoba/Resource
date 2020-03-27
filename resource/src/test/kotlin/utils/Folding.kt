package utils

import com.javiersc.resources.resource.Resource
import com.javiersc.resources.resource.extensions.*

internal fun folding(resource: Resource<String, String>, dataAndCounters: DataAndCounters) {
    with(dataAndCounters) {
        resource.fold {
            loading { loadingCount++ }
            noLoading { noLoadingCount++ }
            success { successData = it; successCount++ }
            successEmpty { successEmptyCount++ }
            noSuccess { noSuccessCount++ }
            error { errorData = it; errorCount++ }
            errorEmpty { errorEmptyCount++ }
            noError { noErrorCount++ }
            cache { cacheData = it; cacheCount++ }
            cacheEmpty { cacheEmptyCount++ }
            noCache { noCacheCount++ }
        }
    }
}

internal fun ifFolding(resource: Resource<String, String>, dataAndCounters: DataAndCounters) {
    with(resource) {
        with(dataAndCounters) {
            ifLoading { loadingCount++ }
            ifNoLoading { noLoadingCount++ }
            ifSuccess { successData = it; successCount++ }
            ifSuccessEmpty { successEmptyCount++ }
            ifNoSuccess { noSuccessCount++ }
            ifError { errorData = it; errorCount++ }
            ifErrorEmpty { errorEmptyCount++ }
            ifNoError { noErrorCount++ }
            ifCache { cacheData = it; cacheCount++ }
            ifCacheEmpty { cacheEmptyCount++ }
            ifNoCache { noCacheCount++ }
        }
    }
}