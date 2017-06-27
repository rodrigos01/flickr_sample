package com.rodrigo.tigerspiketest.repository

import com.rodrigo.tigerspiketest.data.ImageItem
import com.rodrigo.tigerspiketest.network.ResponseListener

interface ImageRepository {

    fun fetchPhotos(listener: ResponseListener<List<ImageItem>>?)

    fun fetchPhotos(tags: String?, listener: ResponseListener<List<ImageItem>>?)
}