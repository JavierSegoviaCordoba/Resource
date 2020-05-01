import com.javiersc.resources.resource.Resource
import utils.DataAndCounters
import utils.foldForTest
import utils.folderForTest
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
            assertTrue { errorEmptyCount == 0 }
            assertTrue { noErrorCount == 2 }
            assertTrue { cacheData == DataAndCounters.CACHE_DATA }
            assertTrue { cacheCount == 2 }
            assertTrue { cacheEmptyCount == 0 }
            assertTrue { noCacheCount == 0 }
        }

        resource = Resource.Cache(DataAndCounters.CACHE_DATA_2)

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
            assertTrue { errorEmptyCount == 0 }
            assertTrue { noErrorCount == 5 }
            assertTrue { cacheData == DataAndCounters.CACHE_DATA_2 }
            assertTrue { cacheCount == 5 }
            assertTrue { cacheEmptyCount == 0 }
            assertTrue { noCacheCount == 0 }
        }
    }

    @Test
    fun `check callbacks when Resource is Cache and does not have data`() {
        var resource: Resource<String, String> = Resource.Cache(null)

        assertTrue { resource is Resource.Cache }
        assertTrue { resource.isCache }

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
            assertTrue { errorEmptyCount == 0 }
            assertTrue { noErrorCount == 2 }
            assertTrue { cacheData == DataAndCounters.NO_CACHE_DATA }
            assertTrue { cacheCount == 0 }
            assertTrue { cacheEmptyCount == 2 }
            assertTrue { noCacheCount == 0 }
        }

        resource = Resource.Cache(null)

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
            assertTrue { errorEmptyCount == 0 }
            assertTrue { noErrorCount == 5 }
            assertTrue { cacheData == DataAndCounters.NO_CACHE_DATA }
            assertTrue { cacheCount == 0 }
            assertTrue { cacheEmptyCount == 5 }
            assertTrue { noCacheCount == 0 }
        }
    }
}
