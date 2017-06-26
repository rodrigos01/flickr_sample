package com.rodrigo.tigerspiketest.viewmodel

import com.rodrigo.tigerspiketest.data.ImageItem
import org.junit.Assert
import org.junit.Test

class ImageDetailVIewModelTest {

    val imageDetailViewModel = ImageDetailViewModel()

    @Test
    fun shouldReturnLargeImageVersion() {
        val imageItem = ImageItem(media = ImageItem.ImageMedia(medium = "image_m.jpg"))
        imageDetailViewModel.imageItem = imageItem
        Assert.assertEquals("image_h.jpg", imageDetailViewModel.imageUrl)
    }
}