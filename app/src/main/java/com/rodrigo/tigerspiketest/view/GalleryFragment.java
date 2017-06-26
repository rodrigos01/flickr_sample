package com.rodrigo.tigerspiketest.view;


import android.arch.lifecycle.LifecycleFragment;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rodrigo.tigerspiketest.R;
import com.rodrigo.tigerspiketest.TigerSpikeApplication;
import com.rodrigo.tigerspiketest.data.ImageItem;
import com.rodrigo.tigerspiketest.databinding.FragmentGalleryBinding;
import com.rodrigo.tigerspiketest.viewmodel.GalleryViewModel;
import com.rodrigo.tigerspiketest.viewmodel.util.ViewModelFactory;

import javax.inject.Inject;

public class GalleryFragment extends LifecycleFragment {

    @Inject
    public ViewModelFactory viewModelFactory;

    public GalleryFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TigerSpikeApplication application = (TigerSpikeApplication) getActivity().getApplication();
        application.component.inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        FragmentGalleryBinding binding =
                DataBindingUtil.inflate(inflater, R.layout.fragment_gallery, container, false);

        GalleryViewModel galleryViewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(GalleryViewModel.class);

        binding.setViewModel(galleryViewModel);

        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 3);
        binding.imageGrid.setLayoutManager(layoutManager);

        GalleryAdapter adapter = new GalleryAdapter(galleryViewModel.getImageItems());
        adapter.setOnImageClickedListener(onImageClickedListener);
        binding.imageGrid.setAdapter(adapter);

        galleryViewModel.loadImages();

        return binding.getRoot();
    }

    private GalleryAdapter.OnImageClickedListener onImageClickedListener = new GalleryAdapter.OnImageClickedListener() {
        @Override
        public void onImageClicked(ImageItem item) {
            ImageDetailFragment imageDetailFragment = new ImageDetailFragment();

            Bundle args = new Bundle();
            args.putParcelable(ImageDetailFragment.ARG_IMAGE_ITEM, item);
            imageDetailFragment.setArguments(args);

            getActivity().getSupportFragmentManager().beginTransaction()
                    .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out, android.R.anim.fade_in, android.R.anim.fade_out)
                    .replace(R.id.fragment_container, imageDetailFragment)
                    .addToBackStack("details")
                    .commit();
        }
    };

}
