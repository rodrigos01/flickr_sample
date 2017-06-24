package com.rodrigo.tigerspiketest.view;

import android.arch.lifecycle.LifecycleActivity;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;

import com.rodrigo.tigerspiketest.R;
import com.rodrigo.tigerspiketest.TigerSpikeApplication;
import com.rodrigo.tigerspiketest.databinding.ActivityGalleryBinding;
import com.rodrigo.tigerspiketest.viewmodel.GalleryViewModel;
import com.rodrigo.tigerspiketest.viewmodel.util.ViewModelFactory;

import javax.inject.Inject;

public class GalleryActivity extends LifecycleActivity {

    @Inject
    public ViewModelFactory viewModelFactory;

    private GalleryViewModel galleryViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityGalleryBinding binding = DataBindingUtil
                .setContentView(this, R.layout.activity_gallery);

        TigerSpikeApplication application = (TigerSpikeApplication) getApplication();
        application.component.inject(this);

        galleryViewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(GalleryViewModel.class);

        binding.setViewModel(galleryViewModel);

        GridLayoutManager layoutManager = new GridLayoutManager(this, 3);
        binding.imageGrid.setLayoutManager(layoutManager);

        GalleryAdapter adapter = new GalleryAdapter(galleryViewModel.getImageItems());
        binding.imageGrid.setAdapter(adapter);

        galleryViewModel.loadImages();

    }
}
