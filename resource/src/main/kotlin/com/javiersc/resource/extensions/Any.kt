package com.javiersc.resource.extensions


private const val BRIGHT_YELLOW = "\u001B[93m"
private const val BRIGHT_RED = "\u001B[91m"

internal fun printlnWarning(message: String) = println("$BRIGHT_YELLOW$message")
internal fun printlnError(message: String) = println("$BRIGHT_RED$message")