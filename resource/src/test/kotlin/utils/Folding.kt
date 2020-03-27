package utils

import com.javiersc.resources.resource.Resource
import com.javiersc.resources.resource.extensions.fold

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