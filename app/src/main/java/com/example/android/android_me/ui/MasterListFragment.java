package com.example.android.android_me.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.example.android.android_me.R;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MasterListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MasterListFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private int mImgPtr;

    MasterListAdapter masterListAdapter;

    public MasterListFragment() {
        // Required empty public constructor
    }

    public void createMasterListFragment(Context ctx, List<Integer> imgResIds){
        masterListAdapter = new MasterListAdapter(ctx, imgResIds);
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MasterListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MasterListFragment newInstance(String param1, String param2) {
        MasterListFragment fragment = new MasterListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_master_list, container, false);
        if(savedInstanceState == null) {
            GridView gridView = (GridView) rootView.findViewById(R.id.fragmen_grid_view);
            gridView.setAdapter(masterListAdapter);
        }
        return rootView;
    }
}