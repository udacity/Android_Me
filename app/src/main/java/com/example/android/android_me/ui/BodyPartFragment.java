package com.example.android.android_me.ui;

import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.android.android_me.R;
import com.example.android.android_me.data.AndroidImageAssets;

/**
 * Created by godaa on 12/06/2017.
 */

public class BodyPartFragment extends android.support.v4.app.Fragment {
    public BodyPartFragment() {
        super();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View root;
        root = inflater.inflate(R.layout.fragment_body_image, container, false);
        ImageView imageView = (ImageView) root.findViewById(R.id.body_part_image);
        imageView.setImageResource(AndroidImageAssets.getHeads().get(0));

        return root;
    }
}
