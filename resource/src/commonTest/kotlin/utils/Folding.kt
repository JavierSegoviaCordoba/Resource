package utils

import com.javiersc.resources.resource.Resource
import com.javiersc.resources.resource.extensions.fold
import com.javiersc.resources.resource.extensions.folder
import com.javiersc.resources.resource.extensions.ifError
import com.javiersc.resources.resource.extensions.ifLoading
import com.javiersc.resources.resource.extensions.ifNoError
import com.javiersc.resources.resource.extensions.ifNoLoading
import com.javiersc.resources.resource.extensions.ifNoSuccess
import com.javiersc.resources.resource.extensions.ifSuccess

internal fun folderForTest(resource: Resource<String, String>, dataAndCounters: DataAndCounters) {
    with(dataAndCounters) {
        resource.folder {
            loading { loadingCount++ }
            noLoading { noLoadingCount++ }
            success { successData = it; successCount++ }
            noSuccess { noSuccessCount++ }
            error { errorData = it; errorCount++ }
            noError { noErrorCount++ }
        }
    }
}

internal fun foldForTest(resource: Resource<String, String>, dataAndCounters: DataAndCounters) {
    with(dataAndCounters) {
        resource.fold(
            loading = { loadingCount++ },
            noLoading = { noLoadingCount++ },
            success = { successData = it; successCount++ },
            noSuccess = { noSuccessCount++ },
            error = { errorData = it; errorCount++ },
            noError = { noErrorCount++ },
        )
    }
}

internal fun ifFolding(resource: Resource<String, String>, dataAndCounters: DataAndCounters) {
    with(resource) {
        with(dataAndCounters) {
            ifLoading { loadingCount++ }
            ifNoLoading { noLoadingCount++ }
            ifSuccess { successData = it; successCount++ }
            ifNoSuccess { noSuccessCount++ }
            ifError { errorData = it; errorCount++ }
            ifNoError { noErrorCount++ }
        }
    }
}
