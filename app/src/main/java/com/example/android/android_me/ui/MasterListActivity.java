package com.example.android.android_me.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.example.android.android_me.R;
import com.example.android.android_me.data.AndroidImageAssets;

public class MasterListActivity extends AppCompatActivity implements MasterListFragment.IOnGridImageClickListener {

    Context context = this;

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

    private int headIndex = 0;
    private int bodyIndex = 0;
    private int legIndex = 0;
    @Override
    public void onImageSelected(int position) {
        int group = position/12;
        int index = position % 12;
        switch (group){
            case 0:
                headIndex = index;
                break;
            case 1:
                bodyIndex = index;
                break;
            case 2:
                legIndex = index;
                break;
            default:
        }
//        Toast.makeText(context, group+" "+index+" Grid item clicked {"+headIndex+","+bodyIndex+","+legIndex+"}", Toast.LENGTH_SHORT).show();
    }

    public void launchAndroidMe(View view)
    {
        Intent intent = new Intent(context, AndroidMeActivity.class);
        intent.putExtra("head",headIndex);
        intent.putExtra("body",bodyIndex);
        intent.putExtra("leg",legIndex);
        startActivity(intent);
//        Toast.makeText(context, "Starting activity... {"+headIndex+","+bodyIndex+","+legIndex+"}", Toast.LENGTH_SHORT).show();
    }
}