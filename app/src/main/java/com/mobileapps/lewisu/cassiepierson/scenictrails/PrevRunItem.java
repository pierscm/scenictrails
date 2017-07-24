package com.mobileapps.lewisu.cassiepierson.scenictrails;

import java.util.UUID;

/**
 *
 * PrevRunItem.java:
 *
 * Model class for a Previous Run Item.
 * Each trail item has a UUID,
 * name, distance, time it took user to complete,
 * and date on which the trail was run.
 *
 */

public class PrevRunItem {

    private String trailName;
    private UUID runId;
    private double distanceRun;
    private String timeToComplete;
    private String date;

    public PrevRunItem(String trailName, double distanceRun,  String date) {

        runId = UUID.randomUUID();

        this.trailName = trailName;
        this.distanceRun = distanceRun;
        this.date = date;
    }

    public PrevRunItem() {
        runId = UUID.randomUUID();
    }

    public void setTrailName(String trailName) {
        this.trailName = trailName;
    }

    public void setDistanceRun(double distanceRun) {
        this.distanceRun = distanceRun;
    }

    public void setTimeToComplete(String timeToComplete) {
        this.timeToComplete = timeToComplete;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTrailName() {
        return trailName;
    }

    public UUID getRunId() {
        return runId;
    }

    public double getDistanceRun() {
        return distanceRun;
    }

    public String getTimeToComplete() {
        return timeToComplete;
    }

    public String getDate() {
        return date;
    }




}
