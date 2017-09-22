package com.rodrigo.flickrsample.viewmodel

import android.arch.lifecycle.ViewModel
import android.databinding.ObservableArrayList
import android.databinding.ObservableBoolean
import com.rodrigo.flickrsample.data.ImageItem
import com.rodrigo.flickrsample.network.ResponseListener
import com.rodrigo.flickrsample.repository.ImageRepository
import javax.inject.Inject

class GalleryViewModel @Inject constructor(val imageRepository: ImageRepository) : ViewModel() {

    val loading = ObservableBoolean()
    val error = ObservableBoolean()

    val imageItems = ObservableArrayList<ImageItem>()

    var initialized = false

    var searchQuery: String? = null
        set(value) {
            field = value
            loadImages()
        }

    fun loadImages() {
        loading.set(true)
        imageRepository.fetchPhotos(searchQuery, listener)
    }

    val listener = object : ResponseListener<List<ImageItem>> {
        override fun onFailure() {
            loading.set(false)
            error.set(true)
        }

        override fun onSuccess(response: List<ImageItem>) {
            loading.set(false)
            imageItems.clear()
            imageItems.addAll(response)
        }
    }
}