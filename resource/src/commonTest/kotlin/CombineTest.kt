import com.javiersc.resource.Resource
import com.javiersc.resource.extensions.asSuccess
import com.javiersc.resource.extensions.combine
import com.javiersc.resource.extensions.combineTransform
import com.javiersc.resource.extensions.resource.combine
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeTypeOf
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import utils.DataAndCounters.Companion.SUCCESS_DATA
import utils.DataAndCounters.Companion.SUCCESS_DATA_2
import utils.runBlocking
import kotlin.test.Test

internal class CombineTest {

    @Test
    fun `combine two success resources into one with default value`() {
        val resourceA: Resource<String, Unit> = SUCCESS_DATA.asSuccess()
        val resourceB: Resource<String, Unit> = SUCCESS_DATA_2.asSuccess()

        val resourceC = resourceA.combine(
            resource = resourceB,
            success = { a, b -> "$a $b" },
            defaultError = Unit
        )

        resourceC.shouldBeTypeOf<Resource.Success<String>>()
        resourceC.data shouldBe "$SUCCESS_DATA $SUCCESS_DATA_2"
    }

    @Test
    fun `combine two success resources into one`() {
        val resourceA: Resource<String, Unit> = SUCCESS_DATA.asSuccess()
        val resourceB: Resource<String, Unit> = SUCCESS_DATA_2.asSuccess()

        val resourceC = resourceA.combine(resource = resourceB) { a, b -> "$a $b" }

        resourceC.shouldBeTypeOf<Resource.Success<String>>()
        resourceC.data shouldBe "$SUCCESS_DATA $SUCCESS_DATA_2"
    }

    @Test
    fun `combine two success resource flows with same error type into one`() = runBlocking {
        val flowA: Flow<Resource<String, Unit>> = flow { emit(SUCCESS_DATA.asSuccess()) }
        val flowB: Flow<Resource<String, Unit>> = flow { emit(SUCCESS_DATA_2.asSuccess()) }

        val flowC = flowA.combine(flowB) { a, b -> "$a $b" }

        flowC.collect { resourceC ->
            resourceC.shouldBeTypeOf<Resource.Success<String>>()
            resourceC.data shouldBe "$SUCCESS_DATA $SUCCESS_DATA_2"
        }
    }

    @Test
    fun `combine and transform two success resource flows with same error type into one`() = runBlocking {
        val flowA: Flow<Resource<String, Unit>> = flow { emit(SUCCESS_DATA.asSuccess()) }
        val flowB: Flow<Resource<String, Unit>> = flow { emit(SUCCESS_DATA_2.asSuccess()) }

        val flowC = flowA.combineTransform(flowB) { a, b -> flow { emit("$a $b") } }

        flowC.collect { it shouldBe "$SUCCESS_DATA $SUCCESS_DATA_2" }
    }
}
