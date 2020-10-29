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
import utils.ScreenState
import kotlin.test.Test

internal class MapperTest {

    @Test
    fun `map resource to another resource`() {
        val originalResource: Resource<Unit, Unit> = Resource.Loading
        with(originalResource.map({}, {})) {
            this.shouldBeTypeOf<Resource.Loading>()
            this shouldBe Resource.Loading
        }

        val originalResource2: Resource<String, Unit> = Resource.Success(SUCCESS_DATA)

        with(originalResource2.map(success = { SUCCESS_DATA_2 }, error = { Unit })) {
            this.shouldBeTypeOf<Resource.Success<String>>()
            data shouldBe SUCCESS_DATA_2
        }

        val originalResource3: Resource<Unit, String> = Resource.Error(ERROR_DATA)
        with(originalResource3.map(success = { Unit }, error = { ERROR_DATA_2 })) {
            this.shouldBeTypeOf<Resource.Error<String>>()
            error shouldBe ERROR_DATA_2
        }
    }

    @Test
    fun `map resource to a concrete sealed class`() {
        val resourceLoading: Resource<Unit, Unit> = Resource.Loading
        val screenStateLoading: ScreenState = resourceLoading.map(
            loading = { ScreenState.Loading },
            success = { ScreenState.Success(SUCCESS_DATA) },
            error = { ScreenState.Error(ERROR_DATA) }
        )
        screenStateLoading.shouldBeTypeOf<ScreenState.Loading>()
        screenStateLoading shouldBe ScreenState.Loading

        val resourceSuccess: Resource<String, Unit> = Resource.Success(SUCCESS_DATA)
        val screenStateSuccess: ScreenState = resourceSuccess.map(
            loading = { ScreenState.Loading },
            success = { ScreenState.Success(SUCCESS_DATA) },
            error = { ScreenState.Error(ERROR_DATA) }
        )
        screenStateSuccess.shouldBeTypeOf<ScreenState.Success>()
        screenStateSuccess.data shouldBe SUCCESS_DATA

        val resourceError: Resource<Unit, String> = Resource.Error(ERROR_DATA)
        val screenStateError: ScreenState = resourceError.map(
            loading = { ScreenState.Loading },
            success = { ScreenState.Success(SUCCESS_DATA) },
            error = { ScreenState.Error(ERROR_DATA) }
        )
        screenStateError.shouldBeTypeOf<ScreenState.Error>()
        screenStateError.error shouldBe ERROR_DATA
    }

    @Test
    fun `map only success branch`() {
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
    fun `map only error branch`() {
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
