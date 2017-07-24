package com.mobileapps.lewisu.cassiepierson.scenictrails;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;

import java.util.UUID;

/*****
 *
 * ReviewTrailActivity.class:
 *
 * Launched from TrailViewActivity.java,
 * enables users to add a float rating
 * and text review for any given trail in the
 * database.
 *
 */

//ToDo: Update to display username with review when FBDB enabled.

public class ReviewTrailActivity extends AppCompatActivity {

    private EditText addReviewEt;
    private Button submitButton;
    private RatingBar ratingBar;
    private TrailItem trailItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_trail);

        addReviewEt = (EditText)findViewById(R.id.add_review_et);
        submitButton = (Button)findViewById(R.id.sub_rating_button);
        ratingBar = (RatingBar)findViewById(R.id.trail_rating_bar);

        UUID trailID = (UUID)getIntent().getSerializableExtra("UUID");
        trailItem = TrailList.get().getTrailItem(trailID);

        submitButton.setOnClickListener(new OnSubmitClickListener());
    }

    private class OnSubmitClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            trailItem.addTrailReview(addReviewEt.getText().toString(), ratingBar.getRating());
            finish();
        }
    }
}
