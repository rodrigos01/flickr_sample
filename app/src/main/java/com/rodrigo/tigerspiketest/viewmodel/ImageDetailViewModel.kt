package com.rodrigo.tigerspiketest.viewmodel

import com.rodrigo.tigerspiketest.data.ImageItem
import com.rodrigo.tigerspiketest.view.util.ObservableDynamicField

class ImageDetailViewModel {

    var imageItem: ImageItem? = null
        set(value) {
            imageUrl.notifyChange()
        }

    val imageUrl = ObservableDynamicField {
        imageItem?.media?.medium?.replace("_m.jpg", "_h.jpg")
    }
}