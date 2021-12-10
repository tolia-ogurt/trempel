package com.example.trempel

internal interface ServiceCallback<T> {

    fun onSuccess(response: T)

    fun onFailure(t: Throwable)
}