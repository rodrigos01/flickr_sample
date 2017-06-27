package com.rodrigo.tigerspiketest.view;

import android.app.ActivityOptions;
import android.arch.lifecycle.LifecycleActivity;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;

import com.rodrigo.tigerspiketest.R;
import com.rodrigo.tigerspiketest.TigerSpikeApplication;
import com.rodrigo.tigerspiketest.data.ImageItem;
import com.rodrigo.tigerspiketest.databinding.ActivityGalleryBinding;
import com.rodrigo.tigerspiketest.viewmodel.GalleryViewModel;
import com.rodrigo.tigerspiketest.viewmodel.util.ViewModelFactory;

import javax.inject.Inject;

public class GalleryActivity extends LifecycleActivity {

    @Inject
    public ViewModelFactory viewModelFactory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ((TigerSpikeApplication) getApplication())
                .component.inject(this);

        ActivityGalleryBinding binding =
                DataBindingUtil.setContentView(this, R.layout.activity_gallery);

        GalleryViewModel galleryViewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(GalleryViewModel.class);

        binding.setViewModel(galleryViewModel);

        GridLayoutManager layoutManager = new GridLayoutManager(this, 3);
        binding.imageGrid.setLayoutManager(layoutManager);

        GalleryAdapter adapter = new GalleryAdapter(galleryViewModel.getImageItems());
        adapter.setOnImageClickedListener(onImageClickedListener);
        binding.imageGrid.setAdapter(adapter);

        if (!galleryViewModel.getInitialized()) {

            galleryViewModel.loadImages();
            galleryViewModel.setInitialized(true);
        }

    }

    private GalleryAdapter.OnImageClickedListener onImageClickedListener = new GalleryAdapter.OnImageClickedListener() {
        @Override
        public void onImageClicked(View view, ImageItem item) {

            Intent intent = new Intent(GalleryActivity.this, ImageDetailActivity.class);
            Bundle extras = new Bundle();
            extras.putParcelable(ImageDetailActivity.ARG_IMAGE_ITEM, item);
            intent.putExtras(extras);
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                ActivityOptions options = ActivityOptions
                        .makeSceneTransitionAnimation(GalleryActivity.this, view, item.getLink());
                startActivity(intent, options.toBundle());
            } else {
                startActivity(intent);
            }
        }
    };
}
