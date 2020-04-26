import com.javiersc.resources.resource.Resource
import utils.DataAndCounters
import utils.folding
import utils.ifFolding
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertTrue

@Suppress("MagicNumber")
internal class CacheTest {

    private val dataAndCounters = DataAndCounters()

    @BeforeTest
    fun `clear all data and counters`() = dataAndCounters.clearAll()

    @Test
    fun `check callbacks when Resource is Cache and has data`() {
        var resource: Resource<String, String> = Resource.Cache(DataAndCounters.CACHE_DATA)

        assertTrue { resource is Resource.Cache }
        assertTrue { resource.isCache }

        folding(resource, dataAndCounters)

        with(dataAndCounters) {
            assertTrue { loadingCount == 0 }
            assertTrue { noLoadingCount == 1 }
            assertTrue { successData == DataAndCounters.NO_SUCCESS_DATA }
            assertTrue { successCount == 0 }
            assertTrue { successEmptyCount == 0 }
            assertTrue { noSuccessCount == 1 }
            assertTrue { errorData == DataAndCounters.NO_ERROR_DATA }
            assertTrue { errorCount == 0 }
            assertTrue { errorEmptyCount == 0 }
            assertTrue { noErrorCount == 1 }
            assertTrue { cacheData == DataAndCounters.CACHE_DATA }
            assertTrue { cacheCount == 1 }
            assertTrue { cacheEmptyCount == 0 }
            assertTrue { noCacheCount == 0 }
        }

        resource = Resource.Cache(DataAndCounters.CACHE_DATA_2)

        folding(resource, dataAndCounters)
        ifFolding(resource, dataAndCounters)

        with(dataAndCounters) {
            assertTrue { loadingCount == 0 }
            assertTrue { noLoadingCount == 3 }
            assertTrue { successData == DataAndCounters.NO_SUCCESS_DATA }
            assertTrue { successCount == 0 }
            assertTrue { successEmptyCount == 0 }
            assertTrue { noSuccessCount == 3 }
            assertTrue { errorData == DataAndCounters.NO_ERROR_DATA }
            assertTrue { errorCount == 0 }
            assertTrue { errorEmptyCount == 0 }
            assertTrue { noErrorCount == 3 }
            assertTrue { cacheData == DataAndCounters.CACHE_DATA_2 }
            assertTrue { cacheCount == 3 }
            assertTrue { cacheEmptyCount == 0 }
            assertTrue { noCacheCount == 0 }
        }
    }

    @Test
    fun `check callbacks when Resource is Cache and does not have data`() {
        var resource: Resource<String, String> = Resource.Cache(null)

        assertTrue { resource is Resource.Cache }
        assertTrue { resource.isCache }

        folding(resource, dataAndCounters)

        with(dataAndCounters) {
            assertTrue { loadingCount == 0 }
            assertTrue { noLoadingCount == 1 }
            assertTrue { successData == DataAndCounters.NO_SUCCESS_DATA }
            assertTrue { successCount == 0 }
            assertTrue { successEmptyCount == 0 }
            assertTrue { noSuccessCount == 1 }
            assertTrue { errorData == DataAndCounters.NO_ERROR_DATA }
            assertTrue { errorCount == 0 }
            assertTrue { errorEmptyCount == 0 }
            assertTrue { noErrorCount == 1 }
            assertTrue { cacheData == DataAndCounters.NO_CACHE_DATA }
            assertTrue { cacheCount == 0 }
            assertTrue { cacheEmptyCount == 1 }
            assertTrue { noCacheCount == 0 }
        }

        resource = Resource.Cache(null)

        folding(resource, dataAndCounters)
        ifFolding(resource, dataAndCounters)

        with(dataAndCounters) {
            assertTrue { loadingCount == 0 }
            assertTrue { noLoadingCount == 3 }
            assertTrue { successData == DataAndCounters.NO_SUCCESS_DATA }
            assertTrue { successCount == 0 }
            assertTrue { successEmptyCount == 0 }
            assertTrue { noSuccessCount == 3 }
            assertTrue { errorData == DataAndCounters.NO_ERROR_DATA }
            assertTrue { errorCount == 0 }
            assertTrue { errorEmptyCount == 0 }
            assertTrue { noErrorCount == 3 }
            assertTrue { cacheData == DataAndCounters.NO_CACHE_DATA }
            assertTrue { cacheCount == 0 }
            assertTrue { cacheEmptyCount == 3 }
            assertTrue { noCacheCount == 0 }
        }
    }
}
