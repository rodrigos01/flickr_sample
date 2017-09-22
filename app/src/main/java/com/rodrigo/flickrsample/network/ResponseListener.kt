package com.rodrigo.flickrsample.network

interface ResponseListener<in T> {
    fun onSuccess(response: T)
    fun onFailure()
}