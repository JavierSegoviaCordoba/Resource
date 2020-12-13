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

@Suppress("MagicNumber")
internal class ErrorTest {

    private val dataAndCounters = DataAndCounters()

    @BeforeTest
    fun `clear all data and counters`() = dataAndCounters.clearAll()

    @Test
    fun `check callbacks when Resource is Error and has error data`() {
        var resource: Resource<String, String> = Resource.Error(DataAndCounters.ERROR_DATA)

        resource.shouldBeTypeOf<Resource.Error<String>>()
        resource.isError.shouldBeTrue()

        folderForTest(resource, dataAndCounters)
        foldForTest(resource, dataAndCounters)

        with(dataAndCounters) {
            loadingCount shouldBe 0
            noLoadingCount shouldBe 2
            successData shouldBe DataAndCounters.NO_SUCCESS_DATA
            successCount shouldBe 0
            noSuccessCount shouldBe 2
            errorData shouldBe DataAndCounters.ERROR_DATA
            errorCount shouldBe 2
            noErrorCount shouldBe 0
        }
        resource = Resource.Error(DataAndCounters.ERROR_DATA_2)

        folderForTest(resource, dataAndCounters)
        foldForTest(resource, dataAndCounters)
        ifFolding(resource, dataAndCounters)

        with(dataAndCounters) {
            loadingCount shouldBe 0
            noLoadingCount shouldBe 5
            successData shouldBe DataAndCounters.NO_SUCCESS_DATA
            successCount shouldBe 0
            noSuccessCount shouldBe 5
            errorData shouldBe DataAndCounters.ERROR_DATA_2
            errorCount shouldBe 5
            noErrorCount shouldBe 0
        }
    }
}
