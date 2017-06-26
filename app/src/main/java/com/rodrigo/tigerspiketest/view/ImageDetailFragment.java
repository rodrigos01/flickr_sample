package com.rodrigo.tigerspiketest.view;


import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rodrigo.tigerspiketest.R;
import com.rodrigo.tigerspiketest.TigerSpikeApplication;
import com.rodrigo.tigerspiketest.data.ImageItem;
import com.rodrigo.tigerspiketest.databinding.FragmentImageDetailBinding;
import com.rodrigo.tigerspiketest.viewmodel.ImageDetailViewModel;
import com.rodrigo.tigerspiketest.viewmodel.util.ViewModelFactory;

import javax.inject.Inject;

/**
 * A simple {@link Fragment} subclass.
 */
public class ImageDetailFragment extends Fragment {

    @Inject
    public ViewModelFactory viewModelFactory;

    public static final String ARG_IMAGE_ITEM = "arg_imageitem";

    public ImageDetailFragment() {
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


        postponeEnterTransition();

        FragmentImageDetailBinding binding =
                DataBindingUtil.inflate(inflater, R.layout.fragment_image_detail, container, false);

        ImageDetailViewModel viewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(ImageDetailViewModel.class);

        if (getArguments() != null) {
            ImageItem item = getArguments().getParcelable(ARG_IMAGE_ITEM);
            viewModel.setImageItem(item);
        }
        binding.setViewModel(viewModel);

        return binding.getRoot();
    }

}
