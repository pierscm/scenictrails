package com.mobileapps.lewisu.cassiepierson.scenictrails;

import com.mapbox.mapboxsdk.geometry.LatLng;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * TrailList.java:
 *
 * Static List, info for all trails
 * to be put into RecyclerView.
 *
 */

/***ToDo: At some point, this should be updated to pull required info about trailItems
 * runs from firebase database.
 */

public class TrailList {

    private static TrailList trailList;
    private ArrayList<TrailItem> trailItems;

    public static TrailList get(){
        if (trailList == null){
            trailList = new TrailList();
        }
        return trailList;
    }

    private TrailList(){
        trailItems = new ArrayList<>();
        trailItems.add(new TrailItem("McHenry Park", "An easy 2 mile jog with some hills."));
        trailItems.add(new TrailItem("Odie Forest Preserve", "A nice wooded trail, dirt paths."));
        trailItems.add(new TrailItem("Valpo Art Exhibit","Three miles around some fun statues in north Valpo."));
        trailItems.add(new TrailItem("Andersonville Loop", "A 2.3 mile loop near some fun shops in Andersonville"));
        trailItems.add(new TrailItem("Bodie Lake, Streamwood", "Four miles past Bodie Lake through a forest preserve."));
        trailItems.add(new TrailItem("Bartlett Train Tracks", "Short sidewalk jog through downtown Bartlett."));
        trailItems.add(new TrailItem("Avondale Triangle", "2 loops through each triangle intersection."));
        trailItems.add(new TrailItem("Schaumburg Square", "Gazeebos, Lakes, Statues, Fun Shops. 2 miles."));

    }

    public List getTrailItems(){
        return trailItems;
    }

    public TrailItem getTrailItem(UUID id){
        TrailItem trailItem = null;

        for(TrailItem ti:trailItems){
            if(ti.getTrailId().equals(id)){
                trailItem = ti;
            }
        }
        return trailItem;
    }

    public void addTrail(String trailName, ArrayList<LatLng>coordinates, String description, double trailLength){
        trailItems.add(new TrailItem(trailName, coordinates, description, trailLength));
    }
}
