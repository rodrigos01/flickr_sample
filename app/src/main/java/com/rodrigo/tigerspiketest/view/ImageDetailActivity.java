package com.rodrigo.tigerspiketest.view;

import android.arch.lifecycle.LifecycleActivity;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.rodrigo.tigerspiketest.R;
import com.rodrigo.tigerspiketest.TigerSpikeApplication;
import com.rodrigo.tigerspiketest.data.ImageItem;
import com.rodrigo.tigerspiketest.databinding.ActivityImageDetailBinding;
import com.rodrigo.tigerspiketest.viewmodel.ImageDetailViewModel;
import com.rodrigo.tigerspiketest.viewmodel.util.ViewModelFactory;

import javax.inject.Inject;

public class ImageDetailActivity extends LifecycleActivity {

    @Inject
    public ViewModelFactory viewModelFactory;

    public static final String ARG_IMAGE_ITEM = "arg_imageitem";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportPostponeEnterTransition();

        ((TigerSpikeApplication) getApplication())
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
