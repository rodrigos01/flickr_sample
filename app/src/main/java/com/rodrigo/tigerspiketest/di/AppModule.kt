package com.rodrigo.tigerspiketest.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.rodrigo.tigerspiketest.network.FlickrAPI
import com.rodrigo.tigerspiketest.repository.ImageRepository
import com.rodrigo.tigerspiketest.repository.ImageRepositoryImpl
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module(includes = arrayOf(ViewModelModule::class))
class AppModule {

    @Provides
    fun providesOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build()
    }

    @Provides
    fun providesGson(): Gson {
        return GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create()
    }

    @Provides
    fun providesApi(gson: Gson, okHttpClient: OkHttpClient): FlickrAPI {
        return Retrofit.Builder()
                .baseUrl("https://api.flickr.com/services/feeds/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .build()
                .create(FlickrAPI::class.java)
    }

    @Provides
    fun providesImageRepository(api: FlickrAPI): ImageRepository {
        return ImageRepositoryImpl(api)
    }
}