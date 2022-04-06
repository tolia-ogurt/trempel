package com.trempel.core_ui.exceptions

import retrofit2.HttpException
import java.io.InterruptedIOException
import java.net.HttpURLConnection
import java.net.UnknownHostException

fun Throwable.toTrempelException(): TrempelException {
    return when (this) {
        is InterruptedIOException -> TrempelException.Network
        is UnknownHostException -> TrempelException.Network
        is HttpException -> {
            when (code()) {
                HttpURLConnection.HTTP_CLIENT_TIMEOUT -> TrempelException.Network
                else -> TrempelException.Service
            }
        }
        else -> TrempelException.Service
    }
}