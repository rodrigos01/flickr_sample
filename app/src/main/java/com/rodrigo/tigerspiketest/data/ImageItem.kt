package com.rodrigo.tigerspiketest.data

import com.google.gson.annotations.SerializedName
import java.util.*

data class ImageItem(
        val title: String? = null,
        val link: String? = null,
        val media: ImageMedia? = null,
        @SerializedName("date_taken")
        val dateTaken: Date? = null,
        val published: Date? = null,
        val author: String? = null,
        val tags: String? = null
) {
    data class ImageMedia(
            @SerializedName("m")
            val medium: String? = null
    )
}