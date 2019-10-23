package com.javiersc.app.data.datasource.network.interceptors

import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level.BODY

object Interceptors {
    val httpLoggingInterceptor = HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
        override fun log(message: String) {
            when {
                message.contains("<-- END HTTP") -> printlnInfo("API-END: $message")
                message.contains("--> ") && !message.contains("--> END") -> printlnInfo("API-REQUEST: $message")
                message.contains("<-- ") -> printlnInfo("API-CODE: $message")
                message.contains("[") -> printlnInfo("API-BODY: $message")
                message.contains("{") -> printlnInfo("API-BODY: $message")
                message.contains("(") -> printlnInfo("API-BODY: $message")
            }
        }
    }).apply { level = BODY }
}

private const val BRIGHT_BLUE = "\u001B[94m"

private fun printlnInfo(message: String) = println("$BRIGHT_BLUE$message")