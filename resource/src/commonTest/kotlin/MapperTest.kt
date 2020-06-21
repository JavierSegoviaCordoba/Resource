import com.javiersc.resources.resource.Resource
import com.javiersc.resources.resource.Resource.Error
import com.javiersc.resources.resource.Resource.Success
import com.javiersc.resources.resource.extensions.asError
import com.javiersc.resources.resource.extensions.asSuccess
import com.javiersc.resources.resource.extensions.map
import com.javiersc.resources.resource.extensions.mapError
import com.javiersc.resources.resource.extensions.mapSuccess
import utils.DataAndCounters.Companion.ERROR_DATA
import utils.DataAndCounters.Companion.ERROR_DATA_2
import utils.DataAndCounters.Companion.SUCCESS_DATA
import utils.DataAndCounters.Companion.SUCCESS_DATA_2
import kotlin.test.Test
import kotlin.test.assertTrue

internal class MapperTest {

    @Test
    fun `check mapper`() {
        val originalResource: Resource<String, Unit> = Success(SUCCESS_DATA)
        val finalResource: Resource<String, Unit> = originalResource.map(data = { SUCCESS_DATA_2 }, error = { Unit })
        with(finalResource) { assertTrue { this is Success && data == SUCCESS_DATA_2 } }

        val originalResource2: Resource<Unit, String> = Error(ERROR_DATA)
        val finalResource2: Resource<Unit, String> = originalResource2.map(data = { Unit }, error = { ERROR_DATA_2 })
        with(finalResource2) { assertTrue { this is Error && error == ERROR_DATA_2 } }
    }

    @Test
    fun `check success mapper`() {
        val originalResource: Resource<String, Unit> = SUCCESS_DATA.asSuccess()
        with(originalResource) { assertTrue { this is Success && data == SUCCESS_DATA } }

        val finalResource = originalResource.mapSuccess { SUCCESS_DATA_2 }
        with(finalResource) { assertTrue { this is Success && data == SUCCESS_DATA_2 } }
    }

    @Test
    fun `check error mapper`() {
        val originalResource: Resource<Unit, String> = ERROR_DATA.asError()
        with(originalResource) { assertTrue { this is Error && error == ERROR_DATA } }

        val finalResource = originalResource.mapError { ERROR_DATA_2 }
        with(finalResource) { assertTrue { this is Error && error == ERROR_DATA_2 } }
    }
}
