import com.javiersc.resources.resource.Resource
import utils.DataAndCounters
import utils.folding
import utils.ifFolding
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertTrue

@Suppress("MagicNumber")
internal class ErrorTest {

    private val dataAndCounters = DataAndCounters()

    @BeforeTest
    fun `clear all data and counters`() = dataAndCounters.clearAll()

    @Test
    fun `check callbacks when Resource is Error and has error data`() {
        var resource: Resource<String, String> = Resource.Error(DataAndCounters.ERROR_DATA)

        assertTrue { resource is Resource.Error }
        assertTrue { resource.isError }

        folding(resource, dataAndCounters)

        with(dataAndCounters) {
            assertTrue { loadingCount == 0 }
            assertTrue { noLoadingCount == 1 }
            assertTrue { successData == DataAndCounters.NO_SUCCESS_DATA }
            assertTrue { successCount == 0 }
            assertTrue { successEmptyCount == 0 }
            assertTrue { noSuccessCount == 1 }
            assertTrue { errorData == DataAndCounters.ERROR_DATA }
            assertTrue { errorCount == 1 }
            assertTrue { errorEmptyCount == 0 }
            assertTrue { noErrorCount == 0 }
            assertTrue { cacheData == DataAndCounters.NO_CACHE_DATA }
            assertTrue { cacheCount == 0 }
            assertTrue { cacheEmptyCount == 0 }
            assertTrue { noCacheCount == 1 }
        }
        resource = Resource.Error(DataAndCounters.ERROR_DATA_2)

        folding(resource, dataAndCounters)
        ifFolding(resource, dataAndCounters)

        with(dataAndCounters) {
            assertTrue { loadingCount == 0 }
            assertTrue { noLoadingCount == 3 }
            assertTrue { successData == DataAndCounters.NO_SUCCESS_DATA }
            assertTrue { successCount == 0 }
            assertTrue { successEmptyCount == 0 }
            assertTrue { noSuccessCount == 3 }
            assertTrue { errorData == DataAndCounters.ERROR_DATA_2 }
            assertTrue { errorCount == 3 }
            assertTrue { errorEmptyCount == 0 }
            assertTrue { noErrorCount == 0 }
            assertTrue { cacheData == DataAndCounters.NO_CACHE_DATA }
            assertTrue { cacheCount == 0 }
            assertTrue { cacheEmptyCount == 0 }
            assertTrue { noCacheCount == 3 }
        }
    }

    @Test
    fun `check callbacks when Resource is Error and does not have error data`() {
        var resource: Resource<String, String> = Resource.Error(null)

        assertTrue { resource is Resource.Error }
        assertTrue { resource.isError }

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
            assertTrue { errorEmptyCount == 1 }
            assertTrue { noErrorCount == 0 }
            assertTrue { cacheData == DataAndCounters.NO_CACHE_DATA }
            assertTrue { cacheCount == 0 }
            assertTrue { cacheEmptyCount == 0 }
            assertTrue { noCacheCount == 1 }
        }

        resource = Resource.Error(null)

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
            assertTrue { errorEmptyCount == 3 }
            assertTrue { noErrorCount == 0 }
            assertTrue { cacheData == DataAndCounters.NO_CACHE_DATA }
            assertTrue { cacheCount == 0 }
            assertTrue { cacheEmptyCount == 0 }
            assertTrue { noCacheCount == 3 }
        }
    }
}
