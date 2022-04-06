package com.trempel.core_ui.exceptions

import okhttp3.*
import java.io.InterruptedIOException
import java.net.UnknownHostException

class ErrorInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        return runCatching {
            chain.proceed(chain.request())
        }.onSuccess { response ->
            response.takeIf { it.isSuccessful } ?: throw TrempelException.Service
        }.onFailure {
            throw when (it) {
                is InterruptedIOException -> TrempelException.Network
                is UnknownHostException -> TrempelException.Network
                else -> TrempelException.Service
            }
        }.getOrThrow()
    }
}