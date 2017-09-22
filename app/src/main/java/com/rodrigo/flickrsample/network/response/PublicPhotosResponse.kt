package com.rodrigo.flickrsample.network.response

import com.rodrigo.flickrsample.data.ImageItem

data class PublicPhotosResponse(
        val items: List<ImageItem>?
)