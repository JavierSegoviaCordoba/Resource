import com.javiersc.resources.resource.Resource
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import utils.DataAndCounters
import utils.equals
import utils.folding
import utils.ifFolding

class SuccessTest {

    private val dataAndCounters = DataAndCounters()

    @BeforeEach
    fun `clear all data and counters`() = dataAndCounters.clearAll()

    @Test
    fun `check callbacks when Resource is Success and has data`() {
        var resource: Resource<String, String> = Resource.Success("SUCCESS_DATA")

        assert(resource is Resource.Success)
        assert(resource.isSuccess)

        folding(resource, dataAndCounters)

        with(dataAndCounters) {
            loadingCount equals 0
            noLoadingCount equals 1
            successData equals "SUCCESS_DATA"
            successCount equals 1
            successEmptyCount equals 0
            noSuccessCount equals 0
            errorData equals "NO_ERROR_DATA"
            errorCount equals 0
            errorEmptyCount equals 0
            noErrorCount equals 1
            cacheData equals "NO_CACHE_DATA"
            cacheCount equals 0
            cacheEmptyCount equals 0
            noCacheCount equals 1
        }

        resource = Resource.Success("SUCCESS_DATA_2")

        folding(resource, dataAndCounters)
        ifFolding(resource, dataAndCounters)

        with(dataAndCounters) {
            loadingCount equals 0
            noLoadingCount equals 3
            successData equals "SUCCESS_DATA_2"
            successCount equals 3
            successEmptyCount equals 0
            noSuccessCount equals 0
            errorData equals "NO_ERROR_DATA"
            errorCount equals 0
            errorEmptyCount equals 0
            noErrorCount equals 3
            cacheData equals "NO_CACHE_DATA"
            cacheCount equals 0
            cacheEmptyCount equals 0
            noCacheCount equals 3
        }
    }

    @Test
    fun `check callbacks when Resource is Success and does not have data`() {
        var resource: Resource<String, String> = Resource.Success(null)

        assert(resource is Resource.Success)
        assert(resource.isSuccess)

        folding(resource, dataAndCounters)

        with(dataAndCounters) {
            loadingCount equals 0
            noLoadingCount equals 1
            successData equals "NO_SUCCESS_DATA"
            successCount equals 0
            successEmptyCount equals 1
            noSuccessCount equals 0
            errorData equals "NO_ERROR_DATA"
            errorCount equals 0
            errorEmptyCount equals 0
            noErrorCount equals 1
            cacheData equals "NO_CACHE_DATA"
            cacheCount equals 0
            cacheEmptyCount equals 0
            noCacheCount equals 1
        }

        resource = Resource.Success(null)

        ifFolding(resource, dataAndCounters)
        folding(resource, dataAndCounters)

        with(dataAndCounters) {
            loadingCount equals 0
            noLoadingCount equals 3
            successData equals "NO_SUCCESS_DATA"
            successCount equals 0
            successEmptyCount equals 3
            noSuccessCount equals 0
            errorData equals "NO_ERROR_DATA"
            errorCount equals 0
            errorEmptyCount equals 0
            noErrorCount equals 3
            cacheData equals "NO_CACHE_DATA"
            cacheCount equals 0
            cacheEmptyCount equals 0
            noCacheCount equals 3
        }
    }
}
