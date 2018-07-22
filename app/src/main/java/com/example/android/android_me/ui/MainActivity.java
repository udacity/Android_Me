/*
* Copyright (C) 2017 The Android Open Source Project
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*  	http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

package com.example.android.android_me.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.android.android_me.R;
import com.example.android.android_me.data.AndroidImageAssets;

// This activity is responsible for displaying the master list of all images
// Implement the MasterListFragment callback, OnImageClickListener
public class MainActivity extends AppCompatActivity implements MasterListFragment.OnImageClickListener{

    // Variables to store the values for the list index of the selected images
    // The default value will be index = 0
    private int headIndex;
    private int bodyIndex;
    private int legIndex;

    private Bundle b;

    // COMPLETED (3) Create a variable to track whether to display a two-pane or single-pane UI
        // A single-pane display refers to phone screens, and two-pane to larger tablet screens
    private boolean twoPaneUI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // create default bundle with starting positions
        b = createBundle(0, 0, 0);

        // COMPLETED (4) If you are making a two-pane display, add new BodyPartFragments to create an initial Android-Me image
        twoPaneUI = findViewById(R.id.android_me_linear_layout) != null;

        if (twoPaneUI) {
            // create fragments for two-pane layout
            populateBodyPartIndexes();

            // Also, for the two-pane display, get rid of the "Next" button in the master list fragment
            hideNextButton();
        }
    }

    // Define the behavior for onImageSelected
    public void onImageSelected(int position) {
        // Based on where a user has clicked, store the selected list index for the head, body, and leg BodyPartFragments

        // bodyPartNumber will be = 0 for the head fragment, 1 for the body, and 2 for the leg fragment
        // Dividing by 12 gives us these integer values because each list of images resources has a size of 12
        int bodyPartNumber = position / 12;

        // Store the correct list index no matter where in the image list has been clicked
        // This ensures that the index will always be a value between 0-11
        int listIndex = position - 12*bodyPartNumber;

        // Set the currently displayed item for the correct body part fragment
        switch(bodyPartNumber) {
            case 0: headIndex = listIndex;
                break;
            case 1: bodyIndex = listIndex;
                break;
            case 2: legIndex = listIndex;
                break;
            default: break;
        }

        // Update the bundle with the latest positions
        b = createBundle(headIndex, bodyIndex, legIndex);

        // COMPLETED (5) Handle the two-pane case and replace existing fragments right when a new image is selected from the master list
        // The two-pane case will not need a Bundle or Intent since a new activity will not be started;
        // This is all happening in this MainActivity and one fragment will be replaced at a time
        if (twoPaneUI) {
            updateBodyPartIndexes();
        } else {
            prepareBodyPartIntent();
        }
    }

    private void hideNextButton() {
        Button nextButton = (Button) findViewById(R.id.next_button);
        nextButton.setVisibility(View.GONE);
    }

    private Bundle createBundle(int headIndex, int bodyIndex, int legIndex) {
        Bundle b = new Bundle();
        b.putInt("headIndex", headIndex);
        b.putInt("bodyIndex", bodyIndex);
        b.putInt("legIndex", legIndex);
        return b;
    }


    private void populateBodyPartIndexes() {
        // Create a new head BodyPartFragment
        BodyPartFragment headFragment = new BodyPartFragment();

        // Set the list of image id's for the head fragment and set the position to the second image in the list
        headFragment.setImageIds(AndroidImageAssets.getHeads());

        // Get the correct index to access in the array of head images from the intent
        headFragment.setListIndex(b.getInt("headIndex"));

        // Add the fragment to its container using a FragmentManager and a Transaction
        FragmentManager fragmentManager = getSupportFragmentManager();

        fragmentManager.beginTransaction()
                .add(R.id.head_container, headFragment)
                .commit();

        // Create and display the body and leg BodyPartFragments
        BodyPartFragment bodyFragment = new BodyPartFragment();
        bodyFragment.setImageIds(AndroidImageAssets.getBodies());
        bodyFragment.setListIndex(b.getInt("bodyIndex"));

        fragmentManager.beginTransaction()
                .add(R.id.body_container, bodyFragment)
                .commit();

        BodyPartFragment legFragment = new BodyPartFragment();
        legFragment.setImageIds(AndroidImageAssets.getLegs());
        legFragment.setListIndex(b.getInt("legIndex"));

        fragmentManager.beginTransaction()
                .add(R.id.leg_container, legFragment)
                .commit();
    }

    private void updateBodyPartIndexes() {
        // Create a new head BodyPartFragment
        BodyPartFragment headFragment = new BodyPartFragment();

        // Set the list of image id's for the head fragment and set the position to the second image in the list
        headFragment.setImageIds(AndroidImageAssets.getHeads());

        // Get the correct index to access in the array of head images from the intent
        headFragment.setListIndex(b.getInt("headIndex"));

        // Add the fragment to its container using a FragmentManager and a Transaction
        FragmentManager fragmentManager = getSupportFragmentManager();

        fragmentManager.beginTransaction()
                .replace(R.id.head_container, headFragment)
                .commit();

        // Create and display the body and leg BodyPartFragments
        BodyPartFragment bodyFragment = new BodyPartFragment();
        bodyFragment.setImageIds(AndroidImageAssets.getBodies());
        bodyFragment.setListIndex(b.getInt("bodyIndex"));

        fragmentManager.beginTransaction()
                .replace(R.id.body_container, bodyFragment)
                .commit();

        BodyPartFragment legFragment = new BodyPartFragment();
        legFragment.setImageIds(AndroidImageAssets.getLegs());
        legFragment.setListIndex(b.getInt("legIndex"));

        fragmentManager.beginTransaction()
                .replace(R.id.leg_container, legFragment)
                .commit();
    }

    private void prepareBodyPartIntent() {
        // Attach the Bundle to an intent
        final Intent intent = new Intent(this, AndroidMeActivity.class);
        intent.putExtras(b);

        // The "Next" button launches a new AndroidMeActivity
        Button nextButton = (Button) findViewById(R.id.next_button);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent);
            }
        });
    }
}
