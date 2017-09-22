package com.rodrigo.flickrsample.viewmodel

import com.rodrigo.flickrsample.data.ImageItem
import com.rodrigo.flickrsample.network.ResponseListener
import com.rodrigo.flickrsample.repository.ImageRepository
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class GalleryViewModelTest {

    @Mock
    lateinit var repository: ImageRepository

    lateinit var galleryViewModel: GalleryViewModel

    @Before
    fun setup() {
        galleryViewModel = GalleryViewModel(repository)
    }

    @Test
    fun shouldStartLoading() {
        galleryViewModel.loadImages()
        assertTrue(galleryViewModel.loading.get())
    }

    @Test
    fun shouldHaveImageItems() {
        val response = listOf<ImageItem>()
        whenLoadImages { responseListener ->
            responseListener.onSuccess(response)
        }
        galleryViewModel.loadImages()
        assertTrue(galleryViewModel.imageItems.containsAll(response))
    }

    @Test
    fun shouldFinishLoadingOnSuccess() {
        val response = ArrayList<ImageItem>()
        whenLoadImages { responseListener ->
            responseListener.onSuccess(response)
        }
        galleryViewModel.loadImages()
        assertFalse(galleryViewModel.loading.get())
    }

    @Test
    fun shouldHaveError() {
        whenLoadImages { responseListener ->
            responseListener.onFailure()
        }
        galleryViewModel.loadImages()
        assertTrue(galleryViewModel.error.get())
    }

    @Test
    fun shouldFinishLoadingOnFailure() {
        whenLoadImages { responseListener ->
            responseListener.onFailure()
        }
        galleryViewModel.loadImages()
        assertFalse(galleryViewModel.loading.get())
    }

    @Test
    fun shouldSearchByTag() {
        galleryViewModel.searchQuery = "dogs"
        Mockito.verify(repository).fetchPhotos(Mockito.eq("dogs"), Mockito.any())
    }

    private inline fun whenLoadImages(crossinline body: (ResponseListener<List<ImageItem>>) -> Unit) {
        Mockito.`when`(repository.fetchPhotos(Mockito.eq(null), Mockito.any())).then {
            body(it.getArgument<ResponseListener<List<ImageItem>>>(1))
        }
    }

}