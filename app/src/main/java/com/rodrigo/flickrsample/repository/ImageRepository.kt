package com.rodrigo.flickrsample.repository

import com.rodrigo.flickrsample.data.ImageItem
import com.rodrigo.flickrsample.network.ResponseListener

interface ImageRepository {

    fun fetchPhotos(listener: ResponseListener<List<ImageItem>>?)

    fun fetchPhotos(tags: String?, listener: ResponseListener<List<ImageItem>>?)
}