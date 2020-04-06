import com.javiersc.resources.resource.Resource
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import utils.DataAndCounters
import utils.equals
import utils.folding
import utils.ifFolding

internal class LoadingTest {

    private val dataAndCounters = DataAndCounters()

    @BeforeEach
    fun `clear all data and counters`() = dataAndCounters.clearAll()

    @Test
    fun `check callbacks when Resource is Loading`() {
        val resource: Resource<String, String> = Resource.Loading

        assert(resource is Resource.Loading)
        assert(resource.isLoading)

        folding(resource, dataAndCounters)
        ifFolding(resource, dataAndCounters)

        with(dataAndCounters) {
            loadingCount equals 2
            noLoadingCount equals 0
            successData equals DataAndCounters.NO_SUCCESS_DATA
            successCount equals 0
            successEmptyCount equals 0
            noSuccessCount equals 2
            errorData equals DataAndCounters.NO_ERROR_DATA
            errorCount equals 0
            errorEmptyCount equals 0
            noErrorCount equals 2
            cacheData equals DataAndCounters.NO_CACHE_DATA
            cacheCount equals 0
            cacheEmptyCount equals 0
            noCacheCount equals 2
        }
    }
}
