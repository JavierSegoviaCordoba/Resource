import com.javiersc.resources.resource.Resource
import utils.DataAndCounters
import utils.foldForTest
import utils.folderForTest
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

        folderForTest(resource, dataAndCounters)
        foldForTest(resource, dataAndCounters)

        with(dataAndCounters) {
            assertTrue { loadingCount == 0 }
            assertTrue { noLoadingCount == 2 }
            assertTrue { successData == DataAndCounters.NO_SUCCESS_DATA }
            assertTrue { successCount == 0 }
            assertTrue { successEmptyCount == 0 }
            assertTrue { noSuccessCount == 2 }
            assertTrue { errorData == DataAndCounters.ERROR_DATA }
            assertTrue { errorCount == 2 }
            assertTrue { errorEmptyCount == 0 }
            assertTrue { noErrorCount == 0 }
            assertTrue { cacheData == DataAndCounters.NO_CACHE_DATA }
            assertTrue { cacheCount == 0 }
            assertTrue { cacheEmptyCount == 0 }
            assertTrue { noCacheCount == 2 }
        }
        resource = Resource.Error(DataAndCounters.ERROR_DATA_2)

        folderForTest(resource, dataAndCounters)
        foldForTest(resource, dataAndCounters)
        ifFolding(resource, dataAndCounters)

        with(dataAndCounters) {
            assertTrue { loadingCount == 0 }
            assertTrue { noLoadingCount == 5 }
            assertTrue { successData == DataAndCounters.NO_SUCCESS_DATA }
            assertTrue { successCount == 0 }
            assertTrue { successEmptyCount == 0 }
            assertTrue { noSuccessCount == 5 }
            assertTrue { errorData == DataAndCounters.ERROR_DATA_2 }
            assertTrue { errorCount == 5 }
            assertTrue { errorEmptyCount == 0 }
            assertTrue { noErrorCount == 0 }
            assertTrue { cacheData == DataAndCounters.NO_CACHE_DATA }
            assertTrue { cacheCount == 0 }
            assertTrue { cacheEmptyCount == 0 }
            assertTrue { noCacheCount == 5 }
        }
    }

    @Test
    fun `check callbacks when Resource is Error and does not have error data`() {
        var resource: Resource<String, String> = Resource.Error(null)

        assertTrue { resource is Resource.Error }
        assertTrue { resource.isError }

        folderForTest(resource, dataAndCounters)
        foldForTest(resource, dataAndCounters)

        with(dataAndCounters) {
            assertTrue { loadingCount == 0 }
            assertTrue { noLoadingCount == 2 }
            assertTrue { successData == DataAndCounters.NO_SUCCESS_DATA }
            assertTrue { successCount == 0 }
            assertTrue { successEmptyCount == 0 }
            assertTrue { noSuccessCount == 2 }
            assertTrue { errorData == DataAndCounters.NO_ERROR_DATA }
            assertTrue { errorCount == 0 }
            assertTrue { errorEmptyCount == 2 }
            assertTrue { noErrorCount == 0 }
            assertTrue { cacheData == DataAndCounters.NO_CACHE_DATA }
            assertTrue { cacheCount == 0 }
            assertTrue { cacheEmptyCount == 0 }
            assertTrue { noCacheCount == 2 }
        }

        resource = Resource.Error(null)

        folderForTest(resource, dataAndCounters)
        foldForTest(resource, dataAndCounters)
        ifFolding(resource, dataAndCounters)

        with(dataAndCounters) {
            assertTrue { loadingCount == 0 }
            assertTrue { noLoadingCount == 5 }
            assertTrue { successData == DataAndCounters.NO_SUCCESS_DATA }
            assertTrue { successCount == 0 }
            assertTrue { successEmptyCount == 0 }
            assertTrue { noSuccessCount == 5 }
            assertTrue { errorData == DataAndCounters.NO_ERROR_DATA }
            assertTrue { errorCount == 0 }
            assertTrue { errorEmptyCount == 5 }
            assertTrue { noErrorCount == 0 }
            assertTrue { cacheData == DataAndCounters.NO_CACHE_DATA }
            assertTrue { cacheCount == 0 }
            assertTrue { cacheEmptyCount == 0 }
            assertTrue { noCacheCount == 5 }
        }
    }
}
