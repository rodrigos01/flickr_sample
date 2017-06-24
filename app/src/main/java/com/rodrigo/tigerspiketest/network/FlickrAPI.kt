package com.rodrigo.tigerspiketest.network

import com.rodrigo.tigerspiketest.network.response.PublicPhotosResponse
import retrofit2.Call
import retrofit2.http.GET

interface FlickrAPI {
    @GET("photos_public.gne?format=json")
    fun getPublicPhotos(): Call<PublicPhotosResponse>
}