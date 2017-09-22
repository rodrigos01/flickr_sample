package com.rodrigo.flickrsample.view;

import android.arch.lifecycle.LifecycleActivity;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.rodrigo.flickrsample.FlickrSampleApplication;
import com.rodrigo.flickrsample.R;
import com.rodrigo.flickrsample.data.ImageItem;
import com.rodrigo.flickrsample.databinding.ActivityImageDetailBinding;
import com.rodrigo.flickrsample.viewmodel.ImageDetailViewModel;
import com.rodrigo.flickrsample.viewmodel.util.ViewModelFactory;

import javax.inject.Inject;

public class ImageDetailActivity extends LifecycleActivity {

    @Inject
    public ViewModelFactory viewModelFactory;

    public static final String ARG_IMAGE_ITEM = "arg_imageitem";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportPostponeEnterTransition();

        ((FlickrSampleApplication) getApplication())
                .component.inject(this);

        ActivityImageDetailBinding binding =
                DataBindingUtil.setContentView(this, R.layout.activity_image_detail);
        binding.setThumbnailListener(new ThumbnailRequestListener());

        ImageDetailViewModel viewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(ImageDetailViewModel.class);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            ImageItem imageItem = extras.getParcelable(ARG_IMAGE_ITEM);
            viewModel.setImageItem(imageItem);
        }
        binding.setViewModel(viewModel);
    }

    public class ThumbnailRequestListener implements RequestListener<String, GlideDrawable> {

        @Override
        public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
            supportStartPostponedEnterTransition();
            return false;
        }

        @Override
        public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
            supportStartPostponedEnterTransition();
            return false;
        }
    }
}
