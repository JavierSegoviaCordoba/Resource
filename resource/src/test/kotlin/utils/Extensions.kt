package utils

import junit.framework.TestCase.assertEquals

internal infix fun Any.equals(actual: Any) = assertEquals(this, actual)

internal infix fun List<Any>.equals(actual: Any) = forEach { assertEquals(it, actual) }
