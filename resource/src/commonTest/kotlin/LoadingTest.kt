import com.javiersc.resources.resource.Resource
import utils.DataAndCounters
import utils.foldForTest
import utils.folderForTest
import utils.ifFolding
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertTrue

internal class LoadingTest {

    private val dataAndCounters = DataAndCounters()

    @BeforeTest
    fun `clear all data and counters`() = dataAndCounters.clearAll()

    @Test
    fun `check callbacks when Resource is Loading`() {
        val resource: Resource<String, String> = Resource.Loading

        assertTrue { resource is Resource.Loading }
        assertTrue { resource.isLoading }

        folderForTest(resource, dataAndCounters)
        foldForTest(resource, dataAndCounters)
        ifFolding(resource, dataAndCounters)

        @Suppress("MagicNumber")
        with(dataAndCounters) {
            assertTrue { loadingCount == 3 }
            assertTrue { noLoadingCount == 0 }
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
            assertTrue { cacheEmptyCount == 0 }
            assertTrue { noCacheCount == 3 }
        }
    }
}
