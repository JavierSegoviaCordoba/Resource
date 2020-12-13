import com.javiersc.resource.Resource
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeTypeOf
import utils.DataAndCounters
import utils.foldForTest
import utils.folderForTest
import utils.ifFolding
import kotlin.test.BeforeTest
import kotlin.test.Test

internal class LoadingTest {

    private val dataAndCounters = DataAndCounters()

    @BeforeTest
    fun `clear all data and counters`() = dataAndCounters.clearAll()

    @Test
    fun `check callbacks when Resource is Loading`() {
        val resource: Resource<String, String> = Resource.Loading

        resource.shouldBeTypeOf<Resource.Loading>()
        resource.isLoading.shouldBeTrue()

        folderForTest(resource, dataAndCounters)
        foldForTest(resource, dataAndCounters)
        ifFolding(resource, dataAndCounters)

        @Suppress("MagicNumber")
        with(dataAndCounters) {
            loadingCount shouldBe 3
            noLoadingCount shouldBe 0
            successData shouldBe DataAndCounters.NO_SUCCESS_DATA
            successCount shouldBe 0
            noSuccessCount shouldBe 3
            errorData shouldBe DataAndCounters.NO_ERROR_DATA
            errorCount shouldBe 0
            noErrorCount shouldBe 3
        }
    }
}
