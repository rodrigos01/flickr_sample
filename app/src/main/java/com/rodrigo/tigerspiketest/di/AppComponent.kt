package com.rodrigo.tigerspiketest.di

import com.rodrigo.tigerspiketest.view.GalleryActivity
import com.rodrigo.tigerspiketest.view.ImageDetailActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AppModule::class))
interface AppComponent {
    fun inject(galleryActivity: GalleryActivity)
    fun inject(imageDetailActivity: ImageDetailActivity)
}