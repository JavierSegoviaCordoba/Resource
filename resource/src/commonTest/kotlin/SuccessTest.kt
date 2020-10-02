import com.javiersc.resources.resource.Resource
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
internal class SuccessTest {

    private val dataAndCounters = DataAndCounters()

    @BeforeTest
    fun `clear all data and counters`() = dataAndCounters.clearAll()

    @Test
    fun `check callbacks when Resource is Success and has data`() {
        val dataAndCounters = DataAndCounters()
        var resource: Resource<String, String> = Resource.Success(DataAndCounters.SUCCESS_DATA)

        resource.shouldBeTypeOf<Resource.Success<String>>()
        resource.isSuccess.shouldBeTrue()

        folderForTest(resource, dataAndCounters)
        foldForTest(resource, dataAndCounters)

        with(dataAndCounters) {
            loadingCount shouldBe 0
            noLoadingCount shouldBe 2
            successData shouldBe DataAndCounters.SUCCESS_DATA
            successCount shouldBe 2
            noSuccessCount shouldBe 0
            errorData shouldBe DataAndCounters.NO_ERROR_DATA
            errorCount shouldBe 0
            noErrorCount shouldBe 2
        }

        resource = Resource.Success(DataAndCounters.SUCCESS_DATA_2)

        folderForTest(resource, dataAndCounters)
        foldForTest(resource, dataAndCounters)
        ifFolding(resource, dataAndCounters)

        with(dataAndCounters) {
            loadingCount shouldBe 0
            noLoadingCount shouldBe 5
            successData shouldBe DataAndCounters.SUCCESS_DATA_2
            successCount shouldBe 5
            noSuccessCount shouldBe 0
            errorData shouldBe DataAndCounters.NO_ERROR_DATA
            errorCount shouldBe 0
            noErrorCount shouldBe 5
        }
    }
}
