import com.javiersc.resources.resource.Resource
import com.javiersc.resources.resource.extensions.asError
import com.javiersc.resources.resource.extensions.asSuccess
import com.javiersc.resources.resource.extensions.resource.map
import com.javiersc.resources.resource.extensions.resource.mapError
import com.javiersc.resources.resource.extensions.resource.mapSuccess
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeTypeOf
import utils.DataAndCounters.Companion.ERROR_DATA
import utils.DataAndCounters.Companion.ERROR_DATA_2
import utils.DataAndCounters.Companion.SUCCESS_DATA
import utils.DataAndCounters.Companion.SUCCESS_DATA_2
import kotlin.test.Test

internal class MapperTest {

    @Test
    fun `check mapper`() {
        val originalResource: Resource<String, Unit> = Resource.Success(SUCCESS_DATA)
        val finalResource: Resource<String, Unit> = originalResource.map(data = { SUCCESS_DATA_2 }, error = { Unit })
        with(finalResource) {
            this.shouldBeTypeOf<Resource.Success<String>>()
            data shouldBe SUCCESS_DATA_2
        }

        val originalResource2: Resource<Unit, String> = Resource.Error(ERROR_DATA)
        val finalResource2: Resource<Unit, String> = originalResource2.map(data = { Unit }, error = { ERROR_DATA_2 })
        with(finalResource2) {
            this.shouldBeTypeOf<Resource.Error<String>>()
            error shouldBe ERROR_DATA_2
        }
    }

    @Test
    fun `check success mapper`() {
        val originalResource: Resource<String, Unit> = SUCCESS_DATA.asSuccess()
        with(originalResource) {
            this.shouldBeTypeOf<Resource.Success<String>>()
            data shouldBe SUCCESS_DATA
        }

        val finalResource = originalResource.mapSuccess { SUCCESS_DATA_2 }
        with(finalResource) {
            this.shouldBeTypeOf<Resource.Success<String>>()
            data shouldBe SUCCESS_DATA_2
        }
    }

    @Test
    fun `check error mapper`() {
        val originalResource: Resource<Unit, String> = ERROR_DATA.asError()
        with(originalResource) {
            this.shouldBeTypeOf<Resource.Error<String>>()
            error shouldBe ERROR_DATA
        }

        val finalResource = originalResource.mapError { ERROR_DATA_2 }
        with(finalResource) {
            this.shouldBeTypeOf<Resource.Error<String>>()
            error shouldBe ERROR_DATA_2
        }
    }
}
