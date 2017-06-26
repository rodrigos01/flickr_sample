package com.rodrigo.tigerspiketest.viewmodel

import com.rodrigo.tigerspiketest.data.ImageItem

class ImageDetailViewModel {

    var imageItem: ImageItem? = null

    val imageUrl
        get() = imageItem?.media?.medium?.replace("_m.jpg", "_h.jpg")
}