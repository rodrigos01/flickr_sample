package com.rodrigo.flickrsample.viewmodel.util

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton

/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * From: https://github.com/googlesamples/android-architecture-components
 */
@Singleton
class ViewModelFactory @Inject constructor(val creators: Map<@JvmSuppressWildcards Class<out ViewModel>, @JvmSuppressWildcards Provider<@JvmSuppressWildcards ViewModel>>) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val creator = creators[modelClass] ?:
                creators.toList()
                        .find { modelClass.isAssignableFrom(it.first) }
                        ?.second ?:
                throw IllegalArgumentException("unknown model class " + modelClass)
        try {
            return creator.get() as T
        } catch (e: Exception) {
            throw RuntimeException(e)
        }

    }
}