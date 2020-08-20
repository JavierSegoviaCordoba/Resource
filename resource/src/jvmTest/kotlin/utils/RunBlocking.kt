package utils

import kotlinx.coroutines.runBlocking

internal actual fun runBlocking(block: suspend () -> Unit) = runBlocking { block() }
