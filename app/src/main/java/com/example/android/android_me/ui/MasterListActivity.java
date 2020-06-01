package com.example.android.android_me.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.android.android_me.R;
import com.example.android.android_me.data.AndroidImageAssets;

public class MasterListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_master_list);

        if(savedInstanceState == null){
            MasterListFragment frag = new MasterListFragment();
            frag.createMasterListFragment(this, AndroidImageAssets.getAll());
            getSupportFragmentManager().beginTransaction().add(R.id.master_list_activity_layout, frag).commit();
        }
    }
}