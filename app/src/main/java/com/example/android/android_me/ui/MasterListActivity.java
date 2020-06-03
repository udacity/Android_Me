package com.example.android.android_me.ui;

import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.android.android_me.R;
import com.example.android.android_me.data.AndroidImageAssets;

public class MasterListActivity extends AppCompatActivity implements MasterListFragment.IOnGridImageClickListener {

    Context context = this;
    boolean tabMode = false;
    private static final String TAG = "MasterListActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_master_list);

        if (savedInstanceState == null) {
            MasterListFragment frag = new MasterListFragment();
            frag.createMasterListFragment(this, AndroidImageAssets.getAll());
            tabMode = (null != findViewById(R.id.tablet_frame_grid_view_lay));
            if (tabMode) {
                getSupportFragmentManager().beginTransaction().add(R.id.tablet_frame_grid_view_lay, frag).commit();

                BodyPartFragment headFragment = new BodyPartFragment();
                BodyPartFragment bodyFragment = new BodyPartFragment();
                BodyPartFragment legsFragment = new BodyPartFragment();

                headFragment.setImageList(AndroidImageAssets.getHeads());
                headFragment.setImageIndex(getIntent().getIntExtra("head", 0));
                FragmentManager fragmentManager = getSupportFragmentManager();

                Log.d(TAG, "fragment? " + headFragment.isAdded());

                fragmentManager.beginTransaction()
                        .add(R.id.fragment_container_head, headFragment)
                        .commit();

                bodyFragment.setImageList(AndroidImageAssets.getBodies());
                bodyFragment.setImageIndex(getIntent().getIntExtra("body", 0));
                fragmentManager.beginTransaction()
                        .add(R.id.fragment_container_body, bodyFragment)
                        .commit();

                legsFragment.setImageList(AndroidImageAssets.getLegs());
                legsFragment.setImageIndex(getIntent().getIntExtra("leg", 0));
                fragmentManager.beginTransaction()
                        .add(R.id.fragment_container_legs, legsFragment)
                        .commit();
            } else {

                getSupportFragmentManager().beginTransaction().add(R.id.master_list_activity_layout, frag).commit();
            }
        }
    }

    private int headIndex = 0;
    private int bodyIndex = 0;
    private int legIndex = 0;

    @Override
    public void onImageSelected(int position) {
        int group = position / 12;
        int index = position % 12;
        if (tabMode) {

        }
        switch (group) {
            case 0:
                headIndex = index;
                if (tabMode) {
                    BodyPartFragment newFrag = new BodyPartFragment();
                    newFrag.setImageList(AndroidImageAssets.getHeads());
                    newFrag.setImageIndex(index);
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container_head, newFrag)
                            .commit();
                }
                break;
            case 1:
                bodyIndex = index;
                if (tabMode) {
                    BodyPartFragment newFrag = new BodyPartFragment();
                    newFrag.setImageList(AndroidImageAssets.getBodies());
                    newFrag.setImageIndex(index);
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container_body, newFrag)
                            .commit();
                }
                break;
            case 2:
                legIndex = index;
                if (tabMode) {
                    BodyPartFragment newFrag = new BodyPartFragment();
                    newFrag.setImageList(AndroidImageAssets.getLegs());
                    newFrag.setImageIndex(index);
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container_legs, newFrag)
                            .commit();
                }
                break;
            default:
        }
//        Toast.makeText(context, group+" "+index+" Grid item clicked {"+headIndex+","+bodyIndex+","+legIndex+"}", Toast.LENGTH_SHORT).show();
    }

    public void launchAndroidMe(View view) {
        Intent intent = new Intent(context, AndroidMeActivity.class);
        intent.putExtra("head", headIndex);
        intent.putExtra("body", bodyIndex);
        intent.putExtra("leg", legIndex);
        startActivity(intent);
//        Toast.makeText(context, "Starting activity... {"+headIndex+","+bodyIndex+","+legIndex+"}", Toast.LENGTH_SHORT).show();
    }
}