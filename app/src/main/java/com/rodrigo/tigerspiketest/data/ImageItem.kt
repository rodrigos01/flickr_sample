package com.rodrigo.tigerspiketest.data

import com.google.gson.annotations.SerializedName
import java.util.*

data class ImageItem(
        val title: String?,
        val link: String?,
        val media: ImageMedia?,
        @SerializedName("date_taken")
        val dateTaken: Date?,
        val published: Date?,
        val author: String?,
        val tags: String?
) {
    data class ImageMedia(
            @SerializedName("m")
            val medium: String?
    )
}