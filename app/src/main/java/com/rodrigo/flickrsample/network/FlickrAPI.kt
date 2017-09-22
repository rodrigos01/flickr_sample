package com.rodrigo.flickrsample.network

import com.rodrigo.flickrsample.network.response.PublicPhotosResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface FlickrAPI {
    @GET("photos_public.gne?format=json&nojsoncallback=1")
    fun getPublicPhotos(
            @Query("format") format: String = "json",
            @Query("nojsoncallback") noJSONCallback: Int = 1,
            @Query("tagmode") tagMode: String = "any",
            @Query("tags") tags: String? = null
    ): Call<PublicPhotosResponse>
}