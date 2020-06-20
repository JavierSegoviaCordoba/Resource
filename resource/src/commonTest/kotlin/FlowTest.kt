import com.javiersc.resources.resource.Resource
import com.javiersc.resources.resource.extensions.asErrorFlow
import com.javiersc.resources.resource.extensions.asFlow
import com.javiersc.resources.resource.extensions.asSuccessFlow
import kotlinx.coroutines.flow.collect
import utils.runBlocking
import kotlin.test.Test
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
}
