package com.rodrigo.tigerspiketest.view;

import android.arch.lifecycle.LifecycleActivity;
import android.os.Bundle;

import com.rodrigo.tigerspiketest.R;

public class GalleryActivity extends LifecycleActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, new GalleryFragment())
                .commit();

    }
}
