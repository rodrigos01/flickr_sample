package com.rodrigo.tigerspiketest.repository

import com.rodrigo.tigerspiketest.network.FlickrAPI
import com.rodrigo.tigerspiketest.network.response.PublicPhotosResponse
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Call

@RunWith(MockitoJUnitRunner::class)
class ImageRepositoryTest {

    @Mock
    lateinit var api: FlickrAPI
    @Mock
    lateinit var call: Call<PublicPhotosResponse>

    lateinit var imageRepository: ImageRepository

    @Before
    fun setup() {
        imageRepository = ImageRepository(api)
    }

    @Test
    fun shouldCallGetPublicPhotos() {

        Mockito.`when`(api.getPublicPhotos()).thenReturn(call)
        imageRepository.fetchPhotos({}, {})
        Mockito.verify(api).getPublicPhotos()
    }

}