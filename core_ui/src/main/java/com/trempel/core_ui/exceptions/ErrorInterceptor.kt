package com.trempel.core_ui.exceptions

import okhttp3.Interceptor
import okhttp3.Response
import java.io.InterruptedIOException
import java.net.UnknownHostException

class ErrorInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        return runCatching {
            chain.proceed(chain.request())
        }.onFailure {
            throw when (it) {
                is InterruptedIOException -> TrempelException.Network
                is UnknownHostException -> TrempelException.Network
                else -> TrempelException.Service
            }
        }.onSuccess { response ->
            response.takeIf { it.isSuccessful } ?: throw TrempelException.Service
        }.getOrThrow()
    }
}
