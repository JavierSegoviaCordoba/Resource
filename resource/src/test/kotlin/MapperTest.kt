import com.javiersc.resources.resource.Resource
import com.javiersc.resources.resource.extensions.toResourceCache
import com.javiersc.resources.resource.extensions.toResourceError
import com.javiersc.resources.resource.extensions.toResourceSuccess
import org.junit.jupiter.api.Test

class MapperTest {

    @Test
    fun `check success mapper`() {
        val value = 1
        val resource: Resource<Int, Unit> = value.toResourceSuccess()

        assert(resource is Resource.Success && resource.data != null && resource.data == 1)
    }

    @Test
    fun `check error mapper`() {
        val value = 404
        val resource: Resource<Unit, Int> = value.toResourceError()

        assert(resource is Resource.Error && resource.error != null && resource.error == 404)
    }

    @Test
    fun `check cache mapper`() {
        val value = "MyCache"
        val resource: Resource<String, Unit> = value.toResourceCache()

        assert(resource is Resource.Cache && resource.data != null && resource.data == "MyCache")
    }
}