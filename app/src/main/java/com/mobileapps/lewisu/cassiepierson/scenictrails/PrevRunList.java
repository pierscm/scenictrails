package com.mobileapps.lewisu.cassiepierson.scenictrails;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * PrevRunList.java:
 *
 * Static list that can be accessed to provide
 * all previousRuns for any given user.
 * Method to add New Run.
 *
 */

/***ToDo: At some point, this should be updated to pull required info about previous trail
 * runs from firebase database.
 */

public class PrevRunList {

    private static PrevRunList prevRunList;
    private ArrayList<PrevRunItem>  prevRunItems;

    public static PrevRunList get(){
        if (prevRunList == null){
            prevRunList = new PrevRunList();
        }
        return  prevRunList;
    }

    private PrevRunList(){
        prevRunItems = new ArrayList<>();
        prevRunItems.add(new PrevRunItem("Mchenry Park", 2.5,  "6/13/2017"));
        prevRunItems.add(new PrevRunItem("Valpo Art Exhibit", 3.0,  "6/16/2017"));
        prevRunItems.add(new PrevRunItem("Odie Forest Preserve", 1.0,  "6/18/2017"));
        prevRunItems.add(new PrevRunItem("Logan Square Jog", 4.2,  "6/20/2017"));
    }

    public List getPrevRuns(){
        return prevRunItems;
    }


    public void addRun(String trailName, Double distance, String date ){
        prevRunItems.add(new PrevRunItem(trailName, distance, date));
    }
}
