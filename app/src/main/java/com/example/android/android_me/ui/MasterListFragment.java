package com.example.android.android_me.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.example.android.android_me.R;
import com.example.android.android_me.data.AndroidImageAssets;

/**
 * Created by vsaya on 5/7/17.
 */

public class MasterListFragment extends Fragment {
    private GridView gridView;
    private MasterListAdapter masterListAdapter;

    public MasterListFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_master_list, container, false);
        gridView = (GridView) rootView.findViewById(R.id.image_grid_view);
        masterListAdapter = new MasterListAdapter(getActivity(), AndroidImageAssets.getAll());
        gridView.setAdapter(masterListAdapter);
        return rootView;
    }
}
