package com.rodrigo.tigerspiketest.network

interface ResponseListener<in T> {
    fun onSuccess(response: T)
    fun onFailure()
}