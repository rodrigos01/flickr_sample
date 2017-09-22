package com.rodrigo.flickrsample.di

import com.rodrigo.flickrsample.view.GalleryActivity
import com.rodrigo.flickrsample.view.ImageDetailActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AppModule::class))
interface AppComponent {
    fun inject(galleryActivity: GalleryActivity)
    fun inject(imageDetailActivity: ImageDetailActivity)
}