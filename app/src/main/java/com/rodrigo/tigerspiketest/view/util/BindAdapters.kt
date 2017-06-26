package com.rodrigo.tigerspiketest.view.util

import android.databinding.BindingAdapter
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

@BindingAdapter("visible")
fun booleanVisibility(view: View, visible: Boolean) {
    if (visible) {
        view.visibility = View.VISIBLE
    } else {
        view.visibility = View.GONE
    }
}

@BindingAdapter("imageUrl")
fun loadImage(view: ImageView, url: String?) {
    url?.let {
        Glide.with(view.context)
                .load(url)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(view)
    }
}

@BindingAdapter("imageUrl", "thumbnail")
fun loadImage(view: ImageView, url: String?, thumbnail: String?) {

    val thumbnailRequest = thumbnail?.let {
        Glide.with(view.context)
                .load(thumbnail)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
    }

    url?.let {
        Glide.with(view.context)
                .load(url)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .dontAnimate()
                .thumbnail(thumbnailRequest)
                .into(view)
    }
}

@BindingAdapter("bind:textResId", "bind:textArgs")
fun setText(view: TextView, resId: Int?, arg: String?) {
    if (resId == null || arg == null) {
        return
    }
    val context = view.context

    val string = context.getString(resId, arg)
    view.text = string
}
