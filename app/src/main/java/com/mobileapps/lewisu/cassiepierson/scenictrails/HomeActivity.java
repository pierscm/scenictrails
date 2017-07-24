package com.mobileapps.lewisu.cassiepierson.scenictrails;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity {

    /********
     * HomeActivity.java:
     *
     * Considered the home activity from which users can
     * decided whether to view previous runs, or go on/
     * create a new run.
     */

    Button newRunButton;
    Button prevRunButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        prevRunButton = (Button)findViewById(R.id.view_previous_runs_button);
        newRunButton = (Button)findViewById(R.id.start_new_run_button);
    }

    public void prevRunButtonClick(View v){
        Intent launchPreviousRunActivity = new Intent(this, PreviousRunActivity.class);
        startActivity(launchPreviousRunActivity);
    }

    public void newRunButtonClick(View v){
        Intent launchNewRunActivity = new Intent(this, NewRunActivity.class);
        startActivity(launchNewRunActivity);
    }
}
