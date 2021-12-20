package com.example.trempel.network

internal interface Map<T, R> {
    fun map(input: T): R
}