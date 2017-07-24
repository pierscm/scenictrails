package com.mobileapps.lewisu.cassiepierson.scenictrails;

import android.widget.ImageView;

import com.mapbox.mapboxsdk.geometry.LatLng;

import java.util.ArrayList;
import java.util.UUID;

/**
 * TrailItem.java:
 *
 * Model class for all TrailItems
 *
 */

/***
 * ToDo: Create addPicture method, store in FB DB.
 */

/***ToDO: Add method to get trailAvgRating that updates every time
 * TrailViewActivity.java is accessed and populated with TrailItem
 * info.
 */

public class TrailItem {

    private String trailName;
    private UUID trailId;
    private ArrayList<LatLng> coordinates;
    private String description;
    private float trailAvgRating;
    private ArrayList<ImageView> pictures;
    private ArrayList<String> reviews;
    private double trailLength;

    private int raterCount;

    // TrailItem for Testing Purposes. Allows me to have some default values to work with.

    public TrailItem(String trailName, String description) {

        this.trailName = trailName;
        this.description = description;
        this.trailAvgRating = (float)3.5; // temp value

        this.reviews = new ArrayList<>();
        reviews.add("A really nice, easy run.");
        reviews.add("I walked it, but enjoyed all of the sights.");
        reviews.add("Not too bad. Found a nice cafe called Gatz at the end.");
        reviews.add("Really nice in the summer. Lots of rose bushes.");
        reviews.add("Not a whole lot to see, kinda dull.");
        reviews.add("A lot of fun. The architecture in this area is neat.");

        trailId = UUID.randomUUID();

        this.coordinates = new ArrayList<>();
        coordinates.add(new LatLng(38.9275, -77.0202));
        coordinates.add(new LatLng(38.9276, -77.0201));
        coordinates.add(new LatLng(38.9277, -77.02));
        coordinates.add(new LatLng(38.9278, -77.0198));
        coordinates.add(new LatLng(38.9278, -77.0196));
        coordinates.add(new LatLng(38.9279, -77.0187));
        coordinates.add(new LatLng(38.9279, -77.018));
        coordinates.add(new LatLng(38.9273, -77.0165));
        coordinates.add(new LatLng(38.9264, -77.0148));
        coordinates.add(new LatLng(38.9263, -77.0128));
        coordinates.add(new LatLng(38.9246, -77.0123));
        coordinates.add(new LatLng(38.9232, -77.0123));
        coordinates.add(new LatLng(38.9222, -77.0123));
        coordinates.add(new LatLng(38.9204, -77.0122));
        coordinates.add(new LatLng(38.9024, -77.0141));
        coordinates.add(new LatLng(38.9204, -77.0177));
        coordinates.add(new LatLng(38.92, -77.0216));
    }

    public TrailItem(String trailName, ArrayList<LatLng> coordinates, String description, double trailLength) {

        this.trailName = trailName;
        this.coordinates = coordinates;
        this.description = description;
        this.trailLength = trailLength;

        this.reviews = new ArrayList<>();
        trailId = UUID.randomUUID();

        this.raterCount = 0;
    }

    public TrailItem(String trailName, ArrayList<LatLng> coordinates, String description) {
        this.trailName = trailName;
        this.coordinates = coordinates;
        this.description = description;

        trailId = UUID.randomUUID();

        this.raterCount = 0;
    }

    public double getTrailLength() {
        return trailLength;
    }

    public void setTrailLength(double trailLength) {
        this.trailLength = trailLength;
    }

    public UUID getTrailId() {
        return trailId;
    }

    public String getTrailName() {
        return trailName;
    }

    public void setTrailName(String trailName) {
        this.trailName = trailName;
    }

    public ArrayList<LatLng> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(ArrayList<LatLng> coordinates) {
        this.coordinates = coordinates;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getTrailAvgRating() {
        return trailAvgRating;
    }

    public void setTrailAvgRating(float trailAvgRating) {
        this.trailAvgRating = trailAvgRating;
    }

    public ArrayList<ImageView> getPictures() {
        return pictures;
    }

    public void setPictures(ArrayList<ImageView> pictures) {
        this.pictures = pictures;
    }

    public ArrayList<String> getReviews() {
        return reviews;
    }

    public void setReviews(ArrayList<String> reviews) {
        this.reviews = reviews;
    }

    public void addTrailReview(String review, float rating){
        if (this.reviews == null){
            this.reviews = new ArrayList<>();
        }
        this.reviews.add(review);

        raterCount++;

        trailAvgRating = trailAvgRating + rating / raterCount;
    }
}
