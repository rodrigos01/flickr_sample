package com.rodrigo.tigerspiketest.network.response

import com.rodrigo.tigerspiketest.data.ImageItem

data class PublicPhotosResponse(
        val items: List<ImageItem>?
)