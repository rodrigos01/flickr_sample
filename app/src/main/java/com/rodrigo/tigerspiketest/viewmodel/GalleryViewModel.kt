package com.rodrigo.tigerspiketest.viewmodel

import android.arch.lifecycle.ViewModel
import android.databinding.ObservableArrayList
import android.databinding.ObservableBoolean
import com.rodrigo.tigerspiketest.data.ImageItem
import com.rodrigo.tigerspiketest.network.ResponseListener
import com.rodrigo.tigerspiketest.repository.ImageRepository
import javax.inject.Inject

class GalleryViewModel @Inject constructor(val imageRepository: ImageRepository) : ViewModel() {

    val loading = ObservableBoolean()
    val error = ObservableBoolean()

    val imageItems = ObservableArrayList<ImageItem>()

    var initialized = false

    fun loadImages() {
        loading.set(true)
        imageRepository.fetchPhotos(listener)
    }

    val listener = object : ResponseListener<List<ImageItem>> {
        override fun onFailure() {
            loading.set(false)
            error.set(true)
        }

        override fun onSuccess(response: List<ImageItem>) {
            loading.set(false)
            imageItems.addAll(response)
        }
    }
}