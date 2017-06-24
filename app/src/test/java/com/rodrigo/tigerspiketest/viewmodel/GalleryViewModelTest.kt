package com.rodrigo.tigerspiketest.viewmodel

import com.rodrigo.tigerspiketest.data.ImageItem
import com.rodrigo.tigerspiketest.network.ResponseListener
import com.rodrigo.tigerspiketest.repository.ImageRepository
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
        Mockito.`when`(repository.fetchPhotos(Mockito.any())).then {
            it.getArgument<ResponseListener<List<ImageItem>>>(0).onSuccess(response)
        }
        galleryViewModel.loadImages()
        assertTrue(galleryViewModel.imageItems.containsAll(response))
    }

    @Test
    fun shouldFinishLoadingOnSuccess() {
        val response = ArrayList<ImageItem>()
        Mockito.`when`(repository.fetchPhotos(Mockito.any())).then {
            it.getArgument<ResponseListener<List<ImageItem>>>(0).onSuccess(response)
        }
        galleryViewModel.loadImages()
        assertFalse(galleryViewModel.loading.get())
    }

    @Test
    fun shouldHaveError() {
        Mockito.`when`(repository.fetchPhotos(Mockito.any())).then {
            it.getArgument<ResponseListener<List<ImageItem>>>(0).onFailure()
        }
        galleryViewModel.loadImages()
        assertTrue(galleryViewModel.error.get())
    }

    @Test
    fun shouldFinishLoadingOnFailure() {
        Mockito.`when`(repository.fetchPhotos(Mockito.any())).then {
            it.getArgument<ResponseListener<List<ImageItem>>>(0).onFailure()
        }
        galleryViewModel.loadImages()
        assertFalse(galleryViewModel.loading.get())
    }

}