import com.javiersc.resources.resource.Resource
import com.javiersc.resources.resource.extensions.asSuccess
import com.javiersc.resources.resource.extensions.combine
import com.javiersc.resources.resource.extensions.combineTransform
import com.javiersc.resources.resource.extensions.resource.combine
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import utils.DataAndCounters.Companion.SUCCESS_DATA
import utils.DataAndCounters.Companion.SUCCESS_DATA_2
import utils.runBlocking
import kotlin.test.Test
import kotlin.test.assertTrue

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

        assertTrue { resourceC is Resource.Success && resourceC.data == "$SUCCESS_DATA $SUCCESS_DATA_2" }
    }

    @Test
    fun `combine two success resources into one`() {
        val resourceA: Resource<String, Unit> = SUCCESS_DATA.asSuccess()
        val resourceB: Resource<String, Unit> = SUCCESS_DATA_2.asSuccess()

        val resourceC = resourceA.combine(resource = resourceB) { a, b -> "$a $b" }

        assertTrue { resourceC is Resource.Success && resourceC.data == "$SUCCESS_DATA $SUCCESS_DATA_2" }
    }

    @Test
    fun `combine two success resource flows with same error type into one`() = runBlocking {
        val flowA: Flow<Resource<String, Unit>> = flow { emit(SUCCESS_DATA.asSuccess()) }
        val flowB: Flow<Resource<String, Unit>> = flow { emit(SUCCESS_DATA_2.asSuccess()) }

        val flowC = flowA.combine(flowB) { a, b -> "$a $b" }

        flowC.collect { resourceC ->
            assertTrue { resourceC is Resource.Success && resourceC.data == "$SUCCESS_DATA $SUCCESS_DATA_2" }
        }
    }

    @Test
    fun `combine and transform two success resource flows with same error type into one`() = runBlocking {
        val flowA: Flow<Resource<String, Unit>> = flow { emit(SUCCESS_DATA.asSuccess()) }
        val flowB: Flow<Resource<String, Unit>> = flow { emit(SUCCESS_DATA_2.asSuccess()) }

        fun `flow which emit A and B string concatenated`(a: String, b: String): Flow<String> = flow {
            emit("$a $b")
        }

        val flowC = flowA.combineTransform(flowB) { a, b ->
            `flow which emit A and B string concatenated`(a, b)
        }

        flowC.collect { assertTrue { it == "$SUCCESS_DATA $SUCCESS_DATA_2" } }
    }
}
