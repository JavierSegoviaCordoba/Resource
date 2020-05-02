import com.javiersc.resources.resource.Resource
import com.javiersc.resources.resource.Resource.Cache
import com.javiersc.resources.resource.Resource.Error
import com.javiersc.resources.resource.Resource.Success
import com.javiersc.resources.resource.extensions.map
import com.javiersc.resources.resource.extensions.toResourceCache
import com.javiersc.resources.resource.extensions.toResourceError
import com.javiersc.resources.resource.extensions.toResourceSuccess
import utils.DataAndCounters.Companion.CACHE_DATA
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
            assertTrue { this is Success && data != null && data == SUCCESS_DATA_2 }
        }
    }

    @Test
    fun `check success mapper`() {
        val res: Resource<String, Unit> = SUCCESS_DATA.toResourceSuccess()
        with(res) {
            assertTrue { this is Success && data != null && data == SUCCESS_DATA }
        }
    }

    @Test
    fun `check error mapper`() {
        val res: Resource<Unit, String> = ERROR_DATA.toResourceError()
        with(res) { assertTrue { this is Error && error != null && error == ERROR_DATA } }
    }

    @Test
    fun `check cache mapper`() {
        val res: Resource<String, Unit> = CACHE_DATA.toResourceCache()
        with(res) { assertTrue { this is Cache && data != null && data == CACHE_DATA } }
    }
}
