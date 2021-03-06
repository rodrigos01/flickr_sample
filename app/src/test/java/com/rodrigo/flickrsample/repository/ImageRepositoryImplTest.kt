package com.rodrigo.flickrsample.repository

import com.rodrigo.flickrsample.data.ImageItem
import com.rodrigo.flickrsample.network.FlickrAPI
import com.rodrigo.flickrsample.network.ResponseListener
import com.rodrigo.flickrsample.network.response.PublicPhotosResponse
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@RunWith(MockitoJUnitRunner::class)
class ImageRepositoryImplTest {

    @Mock
    lateinit var api: FlickrAPI
    @Mock
    lateinit var call: Call<PublicPhotosResponse>

    lateinit var imageRepository: ImageRepository

    @Before
    fun setup() {
        imageRepository = ImageRepositoryImpl(api)
        Mockito.`when`(api.getPublicPhotos()).thenReturn(call)
    }

    @Test
    fun shouldCallGetPublicPhotos() {
        imageRepository.fetchPhotos(object : ResponseListener<List<ImageItem>> {
            override fun onSuccess(response: List<ImageItem>) {
            }

            override fun onFailure() {
            }
        })
        Mockito.verify(api).getPublicPhotos()
    }

    @Test
    fun shouldCallSuccess() {

        val response = PublicPhotosResponse(ArrayList())
        Mockito.`when`(call.enqueue(Mockito.any())).then {
            it.getArgument<Callback<PublicPhotosResponse>?>(0)?.onResponse(
                    call,
                    Response.success(response)
            )
        }
        var successCalled = false
        imageRepository.fetchPhotos(object : ResponseListener<List<ImageItem>> {
            override fun onSuccess(response: List<ImageItem>) {
                successCalled = true
            }

            override fun onFailure() {

            }
        })
        Assert.assertTrue(successCalled)
    }

    @Test
    fun shouldCallFailureOnNullItemList() {

        val response = PublicPhotosResponse(null)
        Mockito.`when`(call.enqueue(Mockito.any())).then {
            it.getArgument<Callback<PublicPhotosResponse>?>(0)?.onResponse(
                    call,
                    Response.success(response)
            )
        }
        var failureCalled = false
        imageRepository.fetchPhotos(object : ResponseListener<List<ImageItem>> {
            override fun onSuccess(response: List<ImageItem>) {
            }

            override fun onFailure() {
                failureCalled = true
            }
        })
        Assert.assertTrue(failureCalled)
    }

    @Test
    fun shouldCallFailureOnNullResponseBody() {

        Mockito.`when`(call.enqueue(Mockito.any())).then {
            it.getArgument<Callback<PublicPhotosResponse>?>(0)?.onResponse(
                    call,
                    Response.success(null)
            )
        }
        var failureCalled = false
        imageRepository.fetchPhotos(object : ResponseListener<List<ImageItem>> {
            override fun onSuccess(response: List<ImageItem>) {
            }

            override fun onFailure() {
                failureCalled = true
            }
        })
        Assert.assertTrue(failureCalled)
    }

    @Test
    fun shouldCallFailureOnNullResponse() {

        Mockito.`when`(call.enqueue(Mockito.any())).then {
            it.getArgument<Callback<PublicPhotosResponse>?>(0)?.onResponse(
                    call,
                    Response.success(null)
            )
        }
        var failureCalled = false
        imageRepository.fetchPhotos(object : ResponseListener<List<ImageItem>> {
            override fun onSuccess(response: List<ImageItem>) {
            }

            override fun onFailure() {
                failureCalled = true
            }
        })
        Assert.assertTrue(failureCalled)
    }

    @Test
    fun shouldCallFailureOnFailure() {

        Mockito.`when`(call.enqueue(Mockito.any())).then {
            it.getArgument<Callback<PublicPhotosResponse>?>(0)?.onFailure(
                    call,
                    Exception()
            )
        }
        var failureCalled = false
        imageRepository.fetchPhotos(object : ResponseListener<List<ImageItem>> {
            override fun onSuccess(response: List<ImageItem>) {
            }

            override fun onFailure() {
                failureCalled = true
            }
        })
        Assert.assertTrue(failureCalled)
    }

}