package utils

import junit.framework.TestCase.assertEquals

infix fun Any.equals(actual: Any) = assertEquals(this, actual)

infix fun List<Any>.equals(actual: Any) = forEach { assertEquals(it, actual) }
