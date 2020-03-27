import com.javiersc.resources.resource.Resource
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import utils.DataAndCounters
import utils.equals
import utils.folding
import utils.ifFolding

class ErrorTest {

    private val dataAndCounters = DataAndCounters()

    @BeforeEach
    fun `clear all data and counters`() = dataAndCounters.clearAll()

    @Test
    fun `check callbacks when Resource is Error and has error data`() {
        var resource: Resource<String, String> = Resource.Error("ERROR_DATA")

        assert(resource is Resource.Error)

        folding(resource, dataAndCounters)

        with(dataAndCounters) {
            loadingCount equals 0
            noLoadingCount equals 1
            successData equals "NO_SUCCESS_DATA"
            successCount equals 0
            successEmptyCount equals 0
            noSuccessCount equals 1
            errorData equals "ERROR_DATA"
            errorCount equals 1
            errorEmptyCount equals 0
            noErrorCount equals 0
            cacheData equals "NO_CACHE_DATA"
            cacheCount equals 0
            cacheEmptyCount equals 0
            noCacheCount equals 1
        }
        resource = Resource.Error("ERROR_DATA_2")

        folding(resource, dataAndCounters)
        ifFolding(resource, dataAndCounters)

        with(dataAndCounters) {
            loadingCount equals 0
            noLoadingCount equals 3
            successData equals "NO_SUCCESS_DATA"
            successCount equals 0
            successEmptyCount equals 0
            noSuccessCount equals 3
            errorData equals "ERROR_DATA_2"
            errorCount equals 3
            errorEmptyCount equals 0
            noErrorCount equals 0
            cacheData equals "NO_CACHE_DATA"
            cacheCount equals 0
            cacheEmptyCount equals 0
            noCacheCount equals 3
        }
    }

    @Test
    fun `check callbacks when Resource is Error and does not have error data`() {
        var resource: Resource<String, String> = Resource.Error(null)

        assert(resource is Resource.Error)

        folding(resource, dataAndCounters)

        with(dataAndCounters) {
            loadingCount equals 0
            noLoadingCount equals 1
            successData equals "NO_SUCCESS_DATA"
            successCount equals 0
            successEmptyCount equals 0
            noSuccessCount equals 1
            errorData equals "NO_ERROR_DATA"
            errorCount equals 0
            errorEmptyCount equals 1
            noErrorCount equals 0
            cacheData equals "NO_CACHE_DATA"
            cacheCount equals 0
            cacheEmptyCount equals 0
            noCacheCount equals 1
        }

        resource = Resource.Error(null)

        folding(resource, dataAndCounters)
        ifFolding(resource, dataAndCounters)

        with(dataAndCounters) {
            loadingCount equals 0
            noLoadingCount equals 3
            successData equals "NO_SUCCESS_DATA"
            successCount equals 0
            successEmptyCount equals 0
            noSuccessCount equals 3
            errorData equals "NO_ERROR_DATA"
            errorCount equals 0
            errorEmptyCount equals 3
            noErrorCount equals 0
            cacheData equals "NO_CACHE_DATA"
            cacheCount equals 0
            cacheEmptyCount equals 0
            noCacheCount equals 3
        }
    }
}