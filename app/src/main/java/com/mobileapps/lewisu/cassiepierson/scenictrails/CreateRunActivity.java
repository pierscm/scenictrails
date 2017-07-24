package com.mobileapps.lewisu.cassiepierson.scenictrails;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.camera.CameraPosition;
import com.mapbox.mapboxsdk.constants.MyBearingTracking;
import com.mapbox.mapboxsdk.constants.MyLocationTracking;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.mapbox.services.android.telemetry.permissions.PermissionsListener;
import com.mapbox.services.android.telemetry.permissions.PermissionsManager;

import java.util.ArrayList;
import java.util.List;

/*********
 *
 * CreateRunActivity.java
 *
 * This activity makes use of the MapBox SDK to display and
 * track a user's current location.
 *
 * Location points are stored in an ArrayList of LatLng
 * coordinates.
 *
 * Coordinates and other details are sent to FinalizeNewTrailActivity
 * when a user has completed trail creation and clicked "Trail Complete"
 * button.
 *
 *********/

// ToDo: Pass through other details that could be useful. ie, distance, time to complete.


public class CreateRunActivity extends AppCompatActivity implements PermissionsListener, LocationListener{


    private MapView mapView;
    private MapboxMap map;
    private Button completeRunButton;

    private PermissionsManager permissionsManager;

    private ArrayList<LatLng> coordinates;
    protected LocationManager locationManager;

    protected LocationListener locationListener;

    private final static int ONE_MINUTE = 1000 * 60;



    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        Mapbox.getInstance(this, getString(R.string.map_box_key));

        setContentView(R.layout.activity_create_run);



        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        mapView = (MapView) findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);

        completeRunButton = (Button)findViewById(R.id.complete_run_button);
        completeRunButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getBaseContext(), FinalizeNewTrailActivity.class);
                intent.putExtra("coordinates", coordinates);

                intent.putExtra("distance", "DISTANCE HERE");

                startActivity(intent);
                finish();
            }
        });

        coordinates = new ArrayList<>();

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        try{
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, ONE_MINUTE, 0, this);
        }catch (SecurityException e){
            Log.i(getString(R.string.app_name), getString(R.string.cant_location));
            Toast.makeText(this,getString(R.string.cant_location),Toast.LENGTH_LONG).show();
        }




        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(MapboxMap mapboxMap) {
                map = mapboxMap;

                permissionsManager = new PermissionsManager(CreateRunActivity.this);
                if (!PermissionsManager.areLocationPermissionsGranted(CreateRunActivity.this)) {
                    permissionsManager.requestLocationPermissions(CreateRunActivity.this);
                } else {

                    CameraPosition position = new CameraPosition.Builder()
                            .zoom(100) // Sets the zoom
                            .bearing(180) // Rotate the camera
                            .tilt(30) // Set the camera tilt
                            .build();

                    mapboxMap.setCameraPosition(position);

                    enableLocationTracking();
                }
            }
        });


    }


    @Override
    public void onLocationChanged(final Location location) {

        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
        coordinates.add(latLng);
        Log.i("LAT, LONG", ""+location.getLatitude() + " " + location.getLongitude());
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    @Override
    public void onExplanationNeeded(List<String> permissionsToExplain) {
        Toast.makeText(this, R.string.location_permission,
                Toast.LENGTH_LONG).show();
    }



    @Override
    public void onPermissionResult(boolean granted) {

        if (granted) {
            enableLocationTracking();
        } else {
            Toast.makeText(this, R.string.perm_denied,
                    Toast.LENGTH_LONG).show();
            finish();
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        permissionsManager.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();

    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }


    private void enableLocationTracking() {

        map.setMyLocationEnabled(true);

        // Disable tracking dismiss on map gesture
        map.getTrackingSettings().setDismissAllTrackingOnGesture(false);

        // Enable location and bearing tracking
        map.getTrackingSettings().setMyLocationTrackingMode(MyLocationTracking.TRACKING_FOLLOW);
        map.getTrackingSettings().setMyBearingTrackingMode(MyBearingTracking.COMPASS);

    }



}
