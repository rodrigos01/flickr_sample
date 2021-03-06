package com.rodrigo.flickrsample.repository

import com.rodrigo.flickrsample.data.ImageItem
import com.rodrigo.flickrsample.network.FlickrAPI
import com.rodrigo.flickrsample.network.ResponseListener
import com.rodrigo.flickrsample.network.response.PublicPhotosResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ImageRepositoryImpl(val api: FlickrAPI) : ImageRepository {

    override fun fetchPhotos(listener: ResponseListener<List<ImageItem>>?) {
        fetchPhotos(null, listener)
    }

    override fun fetchPhotos(tags: String?, listener: ResponseListener<List<ImageItem>>?) {
        val call = api.getPublicPhotos(tags = tags)
        call.enqueue(object : Callback<PublicPhotosResponse> {
            override fun onResponse(call: Call<PublicPhotosResponse>?, response: Response<PublicPhotosResponse>?) {
                val items = response?.body()?.items
                if (items != null) {
                    listener?.onSuccess(items)
                } else {
                    listener?.onFailure()
                }
            }

            override fun onFailure(call: Call<PublicPhotosResponse>?, t: Throwable?) {
                listener?.onFailure()
            }
        })
    }
}