package com.example.android.android_me.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.android.android_me.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class BodyPartFragment extends Fragment {

    public static final String TAG = "BodyPartFragment";

    private static final String BUNDLE_TAG_IMG_PTR = "img_ptr";
    private static final String BUNDLE_TAG_IMG_RES_IDs = "img_res_ids";

    private List<Integer> mImgResIds;
    private int mImgPtr;

    private boolean newly = true;

    public BodyPartFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if(newly){
            newly = false;
        }
        View rootView = inflater.inflate(R.layout.fragment_body_part, container, false);
        final ImageView img = (ImageView)rootView.findViewById(R.id.frame_layout_img);

        Log.d(TAG, "onCreateView.. "+savedInstanceState);

        if(savedInstanceState != null) {
            mImgPtr = savedInstanceState.getInt(BUNDLE_TAG_IMG_PTR);
            mImgResIds = savedInstanceState.getIntegerArrayList(BUNDLE_TAG_IMG_RES_IDs);
        }

        if (mImgResIds != null) {
            img.setImageResource(mImgResIds.get(mImgPtr));
        } else {
            Log.d(TAG, "Image resources are not set! Pls check setImageList api");
        }
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mImgPtr = (mImgPtr+1)%mImgResIds.size();
                img.setImageResource(mImgResIds.get(mImgPtr));
                Log.d(TAG, "onClickListener.. "+mImgPtr);
            }
        });
        return rootView;
    }

    public void setImageList(List<Integer> imageIds){
        mImgResIds = imageIds;
    }

    public void setImageIndex(int index){
        mImgPtr = index;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        Log.d(TAG, "onSaveInstanceState.. Ptr: "+mImgPtr);
        outState.putInt(BUNDLE_TAG_IMG_PTR, mImgPtr);
        outState.putIntegerArrayList(BUNDLE_TAG_IMG_RES_IDs, (ArrayList<Integer>)mImgResIds);
        //super.onSaveInstanceState(outState);
    }
}
