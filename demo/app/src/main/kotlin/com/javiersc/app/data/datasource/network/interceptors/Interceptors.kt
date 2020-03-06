package com.javiersc.app.data.datasource.network.interceptors

import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level.BODY

object Interceptors {
    val httpLoggingInterceptor = HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
        override fun log(message: String) {
            when {
                message.contains("<-- END HTTP") -> printlnInfo("API-END: $message")
                message.contains("--> ") && !message.contains("--> END") -> printlnInfo("API-REQUEST: $message")
                message.contains("<-- ") -> printlnInfo("API-STATUS: $message")
                message.contains("[") -> printlnInfo("API-BODY: $message")
                message.contains("{") -> printlnInfo("API-BODY: $message")
                message.contains("(") -> printlnInfo("API-BODY: $message")
            }
        }
    }).apply { level = BODY }
}

private const val RESET = "\u001B[0m"
private const val CYAN = "\u001B[36m"

private fun printlnInfo(message: String) = println("$CYAN$message$RESET")