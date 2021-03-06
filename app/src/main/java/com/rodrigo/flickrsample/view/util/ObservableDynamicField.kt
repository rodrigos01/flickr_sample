package com.rodrigo.flickrsample.view.util

import android.databinding.ObservableField

class ObservableDynamicField<T>(val valueUpdater: () -> T) : ObservableField<T>() {
    override fun get(): T {
        return valueUpdater()
    }
}