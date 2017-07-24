package com.mobileapps.lewisu.cassiepierson.scenictrails;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.mapbox.mapboxsdk.geometry.LatLng;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/***
 *
 * TrailViewActvity.java:
 *
 * Gathers information from any given trail, specified by its UUID.
 *
 * Begin Run Button launches intent to Google Maps App
 * Passes through starting and ending LatLngs as well as
 * a couple of waypoints, to make similar to inital trail shape (Temp)
 *
 * addReviewButton Button launches ReviewTrailActivity.java
 *
 */

/***ToDO: Update with MapBox Navigation SDK when necessary features finalized
 * This should enable up to 25 waypoints instead of just a few,
 * giving routes that match better with the trails that are created.
 */

//ToDo: Implement addPicture button.

//ToDo: Update to set RatingBar properly.

public class TrailViewActivity extends AppCompatActivity {

    private TextView trailName;
    private TextView trailDescription;
    private RatingBar trailRatingBar;
    private Button addPictureButton;
    private Button beginRunButton;
    private Button addReviewButton;
    private ImageView trailMainImage;

    private TextView trailReviews;

    private TrailItem trailItem;

    private List reviews;
    private String reviewString = "";

    private PrevRunList prevRunList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trail_view);

        UUID trailID = (UUID)getIntent().getSerializableExtra("id");
        trailItem = TrailList.get().getTrailItem(trailID);

        trailName = (TextView) findViewById(R.id.trailview_trailname);
        trailDescription = (TextView) findViewById(R.id.trail_description_tv);
        trailRatingBar = (RatingBar) findViewById(R.id.trail_rating_bar);
        addPictureButton = (Button)findViewById(R.id.add_pic_button);
        beginRunButton = (Button) findViewById(R.id.begin_run_button);
        addReviewButton = (Button) findViewById(R.id.add_review_button);
        trailMainImage = (ImageView)findViewById(R.id.main_image);
        trailReviews = (TextView)findViewById(R.id.reviews_line);

        trailName.setText(trailItem.getTrailName());
        trailDescription.setText(trailItem.getDescription());

        //trailRatingBar.setRating(trailItem.getTrailAvgRating());
        beginRunButton.setOnClickListener(new OnNewRunClickListener());
        addReviewButton.setOnClickListener(new AddReviewClickListener());

        reviews = trailItem.getReviews();

        for(Object rev:reviews){
            reviewString += rev.toString() + "\n\n";

        }

        trailReviews.setText(reviewString);
    }

    private class OnNewRunClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {

            ArrayList<LatLng> coordinates = trailItem.getCoordinates();

            int length = coordinates.size() - 1;
            Double latStart = coordinates.get(0).getLatitude();
            Double longStart = coordinates.get(0).getLongitude();

            Double latMid1 = coordinates.get(length/2 - 1).getLatitude();
            Double longMid1 = coordinates.get(length/2 - 1).getLongitude();

            Double latMid2 = coordinates.get(length/2 + 1).getLatitude();
            Double longMid2 = coordinates.get(length/2 + 1).getLongitude();

            Double latDest = coordinates.get(length).getLatitude();
            Double longDest = coordinates.get(length).getLongitude();

            String tunnel = "%7C";

            Date toDate = new Date();

            //Since we are running this trail, we will add it to the prevRunList.
            prevRunList = PrevRunList.get();
            prevRunList.addRun(trailItem.getTrailName(), .5, toDate.toString());


            //Google limits the number of waypoints, so the trail may not be exactly the same
            //as the one that was created. Use a couple of middle points to make it as close as possible.
            Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                    Uri.parse(String.format("https://www.google.com/maps/dir/?api=1&origin=%f,%f&destination=%f,%f&travelmode=walking&waypoints=%f,%f%s%f,%f", latStart, longStart, latDest, longDest, latMid1, longMid1, tunnel, latMid1, longMid2)));
            intent.setPackage("com.google.android.apps.maps");
            startActivity(intent);
        }
    }

    private class AddReviewClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {

            Intent reviewIntent = new Intent(getBaseContext(), ReviewTrailActivity.class);
            reviewIntent.putExtra("UUID", trailItem.getTrailId());
            startActivity(reviewIntent);
        }
    }

}
