import app.cash.turbine.test
import com.javiersc.resources.resource.Resource
import com.javiersc.resources.resource.extensions.asErrorFlow
import com.javiersc.resources.resource.extensions.asSuccessFlow
import com.javiersc.resources.resource.extensions.resource.asFlow
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeTypeOf
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOf
import utils.runBlocking
import kotlin.test.Test

class FlowTest {

    private val data: String = "Success"
    private val error: String = "Error"

    @Test
    fun `emit Resource as success Flow`() = runBlocking {
        data.asSuccessFlow().collect { resourceSuccess -> resourceSuccess.data shouldBe data }
    }

    @Test
    fun `emit Resource as error Flow`() = runBlocking {
        error.asErrorFlow().collect { resourceError -> resourceError.error shouldBe error }
    }

    @Test
    fun `emit Resource as Flow`() = runBlocking {
        val resourceS: Resource<String, Unit> = Resource.Success(data)
        resourceS.asFlow().collect { resourceSuccess ->
            resourceSuccess.shouldBeTypeOf<Resource.Success<String>>()
            resourceSuccess.data shouldBe data
        }

        val resourceE: Resource<Unit, String> = Resource.Error(error)
        resourceE.asFlow().collect { errorResource ->
            errorResource.shouldBeTypeOf<Resource.Error<String>>()
            errorResource.error shouldBe error
        }
    }

    @Test
    fun `emit multiple Resources`() = runBlocking {
        val resourceFlow: Flow<Resource<String, String>> = flowOf(
            Resource.Loading,
            Resource.Error(error),
            Resource.Loading,
            Resource.Success(data),
        )
        resourceFlow.test {
            Resource.Loading shouldBe expectItem()
            Resource.Error(error) shouldBe expectItem()
            Resource.Loading shouldBe expectItem()
            Resource.Success(data) shouldBe expectItem()
            expectComplete()
        }
    }
}
