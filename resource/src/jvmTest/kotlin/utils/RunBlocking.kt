package utils

import kotlinx.coroutines.runBlocking

actual fun runBlocking(block: suspend () -> Unit) = runBlocking { block() }
