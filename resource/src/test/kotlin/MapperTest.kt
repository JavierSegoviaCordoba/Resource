import com.javiersc.resources.resource.Resource
import com.javiersc.resources.resource.Resource.Cache
import com.javiersc.resources.resource.Resource.Error
import com.javiersc.resources.resource.Resource.Success
import com.javiersc.resources.resource.extensions.map
import com.javiersc.resources.resource.extensions.toResourceCache
import com.javiersc.resources.resource.extensions.toResourceError
import com.javiersc.resources.resource.extensions.toResourceSuccess
import org.junit.jupiter.api.Test
import utils.DataAndCounters.Companion.CACHE_DATA
import utils.DataAndCounters.Companion.ERROR_DATA
import utils.DataAndCounters.Companion.SUCCESS_DATA
import utils.DataAndCounters.Companion.SUCCESS_DATA_2

internal class MapperTest {

    @Test
    fun `check success to success mapper`() {
        val value: Resource<String, Unit> = Success(SUCCESS_DATA)
        val resource: Resource<String, Unit> =
            value.map(data = { SUCCESS_DATA_2 }, error = { Unit })

        with(resource) { assert(this is Success && data != null && data == SUCCESS_DATA_2) }
    }

    @Test
    fun `check success mapper`() {
        val resource: Resource<String, Unit> = SUCCESS_DATA.toResourceSuccess()
        with(resource) { assert(this is Success && data != null && data == SUCCESS_DATA) }
    }

    @Test
    fun `check error mapper`() {
        val resource: Resource<Unit, String> = ERROR_DATA.toResourceError()
        with(resource) { assert(this is Error && error != null && error == ERROR_DATA) }
    }

    @Test
    fun `check cache mapper`() {
        val resource: Resource<String, Unit> = CACHE_DATA.toResourceCache()
        with(resource) { assert(this is Cache && data != null && data == CACHE_DATA) }
    }
}
