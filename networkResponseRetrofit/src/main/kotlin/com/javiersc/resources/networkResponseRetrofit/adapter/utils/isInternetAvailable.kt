package com.javiersc.resources.networkResponseRetrofit.adapter.utils

import java.io.IOException
import java.net.InetSocketAddress
import java.net.Socket

internal val isInternetAvailable: Boolean
    get() {
        return try {
            val timeoutMs = 2000
            val socket = Socket()
            val socketAddress = InetSocketAddress("8.8.8.8", 53)
            socket.connect(socketAddress, timeoutMs)
            socket.close()

            true
        } catch (e: IOException) {
            false
        }
    }
