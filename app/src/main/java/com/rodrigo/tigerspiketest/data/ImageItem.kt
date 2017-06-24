package com.rodrigo.tigerspiketest.data

import java.util.Date

data class ImageItem(
        val title: String?,
        val link: String?,
        val media: ImageMedia?,
        val dateTaken: Date?,
        val published: Date?,
        val author: String?,
        val tags: String?
) {
    data class ImageMedia(val medium: String?)
}