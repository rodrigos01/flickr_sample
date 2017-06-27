package com.rodrigo.tigerspiketest.viewmodel

import android.arch.lifecycle.ViewModel
import com.rodrigo.tigerspiketest.data.ImageItem
import com.rodrigo.tigerspiketest.util.format
import javax.inject.Inject

class ImageDetailViewModel @Inject constructor() : ViewModel() {

    var imageItem: ImageItem? = null

    val imageUrl
        get() = imageItem?.media?.medium?.replace("_m.jpg", "_h.jpg")

    val placeholder
        get() = imageItem?.media?.medium

    val title
        get() = imageItem?.title

    val link
        get() = imageItem?.link

    val dateTaken
        get() = imageItem?.dateTaken?.format(java.text.DateFormat.SHORT, java.text.DateFormat.SHORT)

    val datePublished
        get() = imageItem?.dateTaken?.format(java.text.DateFormat.SHORT, java.text.DateFormat.SHORT)

    val author
        get() = imageItem?.author?.let {
            val matcher = java.util.regex.Pattern.compile("\\(\"(.*)\"\\)").matcher(it)
            return@let if (matcher.find()) matcher.group(1) else null
        }
}