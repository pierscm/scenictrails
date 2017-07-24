package com.mobileapps.lewisu.cassiepierson.scenictrails;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;

public class FinalizeNewTrailActivity extends AppCompatActivity {

    /***
     *
     * FinalizeNewTrailActivity.java
     *
     * Information from end of create new trail must be passed in a bundle
     * to this activity.
     * This activity is responsible for gathering other information for the
     * trail including name, description, and rating.
     *
     * Upon submitting, a previousRunItem and new trailItem will be
     * added to respective recyclerview lists.
     */

    /***ToDo: Instead of recylerview list, this should be updated to database.
     * Do this after configuring user sign-in with firebase.
     * Should be able to set rules and database for this platform.
     */

    //ToDo: get distance and time to complete. These should be passed through to the updated PrevRunItem.

    //ToDO: Add picture button? Or should this be left up to TrailViewActivity only?



    private EditText trailNameET;
    private EditText trailDescET;
    private TextView distance;
    private String timeToComplete;
    private RatingBar trailRatingBar;
    private Button finalizeTrailButton;
    private Button addPictureButton;

    private TrailList trailList;
    private PrevRunList prevRunList;
    private ArrayList coords;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finalize_new_trail);

        trailNameET = (EditText)findViewById(R.id.enter_trail_name_et);
        trailDescET = (EditText)findViewById(R.id.enter_trail_desc_et);
        distance = (TextView)findViewById(R.id.distance_run_tv);
        trailRatingBar = (RatingBar)findViewById(R.id.trail_rating_bar);

        addPictureButton = (Button)findViewById(R.id.add_pic_button);
        finalizeTrailButton = (Button)findViewById(R.id.submit_new_trail_button);

        Bundle extras = getIntent().getExtras();


        trailList = TrailList.get();
        trailList.getTrailItems();

        prevRunList = PrevRunList.get();
        final Date toDate = new Date();

        coords = (ArrayList)extras.getSerializable("coordinates");


        finalizeTrailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                prevRunList.addRun(trailNameET.getText().toString(), .5, toDate.toString());
                trailList.addTrail(trailNameET.getText().toString(), coords, trailDescET.getText().toString(), 1.2);
                Toast.makeText(getApplicationContext(),getString(R.string.run_saved), Toast.LENGTH_SHORT).show();


                finish();
            }
        });

    }
}
