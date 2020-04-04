import com.javiersc.resources.resource.Resource
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import utils.DataAndCounters
import utils.equals
import utils.folding
import utils.ifFolding

class CacheTest {

    private val dataAndCounters = DataAndCounters()

    @BeforeEach
    fun `clear all data and counters`() = dataAndCounters.clearAll()

    @Test
    fun `check callbacks when Resource is Cache and has data`() {
        var resource: Resource<String, String> = Resource.Cache("CACHE_DATA")

        assert(resource is Resource.Cache)

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
            errorEmptyCount equals 0
            noErrorCount equals 1
            cacheData equals "CACHE_DATA"
            cacheCount equals 1
            cacheEmptyCount equals 0
            noCacheCount equals 0
        }

        resource = Resource.Cache("CACHE_DATA_2")

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
            errorEmptyCount equals 0
            noErrorCount equals 3
            cacheData equals "CACHE_DATA_2"
            cacheCount equals 3
            cacheEmptyCount equals 0
            noCacheCount equals 0
        }
    }

    @Test
    fun `check callbacks when Resource is Cache and does not have data`() {
        var resource: Resource<String, String> = Resource.Cache(null)

        assert(resource is Resource.Cache)

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
            errorEmptyCount equals 0
            noErrorCount equals 1
            cacheData equals "NO_CACHE_DATA"
            cacheCount equals 0
            cacheEmptyCount equals 1
            noCacheCount equals 0
        }

        resource = Resource.Cache(null)

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
            errorEmptyCount equals 0
            noErrorCount equals 3
            cacheData equals "NO_CACHE_DATA"
            cacheCount equals 0
            cacheEmptyCount equals 3
            noCacheCount equals 0
        }
    }
}
