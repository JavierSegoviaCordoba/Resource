import com.javiersc.resources.resource.Resource
import com.javiersc.resources.resource.Resource.Error
import com.javiersc.resources.resource.Resource.Success
import com.javiersc.resources.resource.extensions.asError
import com.javiersc.resources.resource.extensions.asSuccess
import com.javiersc.resources.resource.extensions.map
import utils.DataAndCounters.Companion.ERROR_DATA
import utils.DataAndCounters.Companion.SUCCESS_DATA
import utils.DataAndCounters.Companion.SUCCESS_DATA_2
import kotlin.test.Test
import kotlin.test.assertTrue

internal class MapperTest {

    @Test
    fun `check success to success mapper`() {
        val originalResource: Resource<String, Unit> = Success(SUCCESS_DATA)
        val finalResource: Resource<String, Unit> =
            originalResource.map(data = { SUCCESS_DATA_2 }, error = { Unit })

        with(finalResource) {
            assertTrue { this is Success && data == SUCCESS_DATA_2 }
        }
    }

    @Test
    fun `check success mapper`() {
        val res: Resource<String, Unit> = SUCCESS_DATA.asSuccess()
        with(res) {
            assertTrue { this is Success && data == SUCCESS_DATA }
        }
    }

    @Test
    fun `check error mapper`() {
        val res: Resource<Unit, String> = ERROR_DATA.asError()
        with(res) { assertTrue { this is Error && error == ERROR_DATA } }
    }
}
