package com.rodrigo.tigerspiketest.di

import com.rodrigo.tigerspiketest.view.GalleryFragment
import com.rodrigo.tigerspiketest.view.ImageDetailFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AppModule::class))
interface AppComponent {
    fun inject(galleryFragment: GalleryFragment)
    fun inject(imageDetailFragment: ImageDetailFragment)
}