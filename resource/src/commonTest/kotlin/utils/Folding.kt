package utils

import com.javiersc.resources.resource.Resource
import com.javiersc.resources.resource.extensions.resource.fold
import com.javiersc.resources.resource.extensions.resource.folder
import com.javiersc.resources.resource.extensions.resource.ifError
import com.javiersc.resources.resource.extensions.resource.ifLoading
import com.javiersc.resources.resource.extensions.resource.ifNoError
import com.javiersc.resources.resource.extensions.resource.ifNoLoading
import com.javiersc.resources.resource.extensions.resource.ifNoSuccess
import com.javiersc.resources.resource.extensions.resource.ifSuccess

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
