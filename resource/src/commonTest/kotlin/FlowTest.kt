import app.cash.turbine.test
import com.javiersc.resource.Resource
import com.javiersc.resource.extensions.asError
import com.javiersc.resource.extensions.asErrorFlow
import com.javiersc.resource.extensions.asSuccess
import com.javiersc.resource.extensions.asSuccessFlow
import com.javiersc.resource.extensions.map
import com.javiersc.resource.extensions.resource.asFlow
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeTypeOf
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOf
import utils.ScreenState
import utils.runBlocking
import kotlin.test.Test

class FlowTest {

    private val data: String = "Success"
    private val error: String = "Error"

    @Test
    fun `emit multiple Resource and map them to multiple ScreenState`() = runBlocking {
        val resourceFlow: Flow<Resource<String, String>> = flowOf(Resource.Loading, data.asSuccess(), error.asError())
        val screenStateFlow: Flow<ScreenState> = resourceFlow.map(
            loading = { ScreenState.Loading },
            success = { data -> ScreenState.Success(data) },
            error = { error -> ScreenState.Error(error) }
        )
        screenStateFlow.test {
            expectItem() shouldBe ScreenState.Loading
            with(expectItem()) {
                shouldBeTypeOf<ScreenState.Success>()
                this.data shouldBe data
            }
            with(expectItem()) {
                shouldBeTypeOf<ScreenState.Error>()
                this.error shouldBe error
            }
            expectComplete()
        }
    }

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
            expectItem() shouldBe Resource.Loading
            expectItem() shouldBe Resource.Error(error)
            expectItem() shouldBe Resource.Loading
            expectItem() shouldBe Resource.Success(data)
            expectComplete()
        }
    }
}
