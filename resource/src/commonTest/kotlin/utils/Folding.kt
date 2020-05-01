package utils

import com.javiersc.resources.resource.Resource
import com.javiersc.resources.resource.extensions.fold
import com.javiersc.resources.resource.extensions.folder
import com.javiersc.resources.resource.extensions.ifCache
import com.javiersc.resources.resource.extensions.ifCacheEmpty
import com.javiersc.resources.resource.extensions.ifError
import com.javiersc.resources.resource.extensions.ifErrorEmpty
import com.javiersc.resources.resource.extensions.ifLoading
import com.javiersc.resources.resource.extensions.ifNoCache
import com.javiersc.resources.resource.extensions.ifNoError
import com.javiersc.resources.resource.extensions.ifNoLoading
import com.javiersc.resources.resource.extensions.ifNoSuccess
import com.javiersc.resources.resource.extensions.ifSuccess
import com.javiersc.resources.resource.extensions.ifSuccessEmpty

internal fun folderForTest(resource: Resource<String, String>, dataAndCounters: DataAndCounters) {
    with(dataAndCounters) {
        resource.folder {
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

internal fun foldForTest(resource: Resource<String, String>, dataAndCounters: DataAndCounters) {
    with(dataAndCounters) {
        resource.fold(
            loading = { loadingCount++ },
            noLoading = { noLoadingCount++ },
            success = { successData = it; successCount++ },
            successEmpty = { successEmptyCount++ },
            noSuccess = { noSuccessCount++ },
            error = { errorData = it; errorCount++ },
            errorEmpty = { errorEmptyCount++ },
            noError = { noErrorCount++ },
            cache = { cacheData = it; cacheCount++ },
            cacheEmpty = { cacheEmptyCount++ },
            noCache = { noCacheCount++ },
        )
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
