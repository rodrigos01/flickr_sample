package com.rodrigo.tigerspiketest.di

import com.rodrigo.tigerspiketest.view.GalleryActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AppModule::class))
interface AppComponent {
    fun inject(galleryActivity: GalleryActivity)
}