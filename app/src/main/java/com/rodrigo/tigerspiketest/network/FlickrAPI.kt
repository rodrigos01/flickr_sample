package com.rodrigo.tigerspiketest.network

import com.rodrigo.tigerspiketest.network.response.PublicPhotosResponse
import retrofit2.Call
import retrofit2.http.GET

interface FlickrAPI {
    @GET("photos_public.gne?format=json&nojsoncallback=1")
    fun getPublicPhotos(): Call<PublicPhotosResponse>
}