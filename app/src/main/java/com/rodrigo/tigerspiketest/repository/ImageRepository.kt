package com.rodrigo.tigerspiketest.repository

import com.rodrigo.tigerspiketest.data.ImageItem
import com.rodrigo.tigerspiketest.network.FlickrAPI
import com.rodrigo.tigerspiketest.network.response.PublicPhotosResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ImageRepository(val api: FlickrAPI) {

    inline fun fetchPhotos(crossinline success: (List<ImageItem>) -> Unit, crossinline failure: () -> Unit) {
        val call = api.getPublicPhotos()
        call.enqueue(object : Callback<PublicPhotosResponse> {
            override fun onResponse(call: Call<PublicPhotosResponse>?, response: Response<PublicPhotosResponse>?) {
                val items = response?.body()?.items
                if (items != null) {
                    success(items)
                } else {
                    failure()
                }
            }

            override fun onFailure(call: Call<PublicPhotosResponse>?, t: Throwable?) {
                failure()
            }
        })
    }
}