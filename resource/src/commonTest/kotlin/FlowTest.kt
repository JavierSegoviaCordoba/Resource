
import app.cash.turbine.test
import com.javiersc.resources.resource.Resource
import com.javiersc.resources.resource.extensions.asErrorFlow
import com.javiersc.resources.resource.extensions.asSuccessFlow
import com.javiersc.resources.resource.extensions.resource.asFlow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOf
import utils.runBlocking
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class FlowTest {

    private val data: String = "Success"
    private val error: String = "Error"

    @Test
    fun `emit Resource as success Flow`() = runBlocking {
        data.asSuccessFlow().collect { resourceSuccess -> assertTrue { resourceSuccess.data == data } }
    }

    @Test
    fun `emit Resource as error Flow`() = runBlocking {
        error.asErrorFlow().collect { resourceError -> assertTrue { resourceError.error == error } }
    }

    @Test
    fun `emit Resource as Flow`() = runBlocking {
        val resourceS: Resource<String, Unit> = Resource.Success(data)
        resourceS.asFlow().collect { resourceSuccess ->
            assertTrue { (resourceSuccess as Resource.Success).data == data }
        }

        val resourceE: Resource<Unit, String> = Resource.Error(error)
        resourceE.asFlow().collect { errorResource ->
            assertTrue { (errorResource as Resource.Error).error == error }
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
            assertEquals(Resource.Loading, expectItem())
            assertEquals(Resource.Error(error), expectItem())
            assertEquals(Resource.Loading, expectItem())
            assertEquals(Resource.Success(data), expectItem())
            expectComplete()
        }
    }
}
