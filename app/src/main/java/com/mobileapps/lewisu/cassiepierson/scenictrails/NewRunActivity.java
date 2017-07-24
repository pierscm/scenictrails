package com.mobileapps.lewisu.cassiepierson.scenictrails;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class NewRunActivity extends AppCompatActivity {


    /*******
     *
     * NewRunActivity.java:
     *
     * This activity displays all TrailItems that have been created
     * and are available for users to view in greater detail
     * through (TrailViewActivity.java)
     *
     * Alternatively, createANewTrailButton enables a user to
     * launch CreateRunActivity.java
     *
     */

    /***
     * ToDo: At some point, it may be beneficial to get LatLng and sort RecyclerViewItems by distance / closest trail.
     *
     * Update list_trail_item to include distance?
     *
     * Update TrailItem.java to feature starting point,
     * this should be possible via MapBox's geocoding?
     */

    private Button createANewTrailButton;

    private RecyclerView recyclerView;
    private TrailList trailList;
    private List<TrailItem> trailItems;

    private TrailHolder trailHolder;
    private TrailAdapter trailAdapter;

    public NewRunActivity(){
        }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_run);

        createANewTrailButton = (Button) findViewById(R.id.create_new_trail_button);

        recyclerView = (RecyclerView)findViewById(R.id.trail_recyclerview);

        trailList = TrailList.get();
        trailItems = trailList.getTrailItems();

        trailAdapter = new TrailAdapter(trailItems);
        recyclerView.setAdapter(trailAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    private class TrailHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView trailNameTv;
        private TextView descriptionTv;
        private TrailItem trailItem;
        private ImageView trailMainImage;

        public TrailHolder(View itemView){
            super(itemView);

            trailNameTv = (TextView)itemView.findViewById(R.id.trail_name_tv_from_new_run);
            descriptionTv = (TextView)itemView.findViewById(R.id.description_of_trail_tv_new_list);
            trailMainImage = (ImageView)itemView.findViewById(R.id.trail_list_image);

            itemView.setOnClickListener(this);
        }

        public void bindTrailItem(TrailItem trailItem){

            this.trailItem = trailItem;

            trailNameTv.setText(trailItem.getTrailName());
            trailMainImage.setImageResource(R.drawable.ales_krivec_2892);
            descriptionTv.setText(trailItem.getDescription());
        }

        @Override
        public void onClick(View v) {

            Intent intent = new Intent(getBaseContext(), TrailViewActivity.class);
            intent.putExtra("id", trailItem.getTrailId());
            startActivity(intent);
        }
    }

    private class TrailAdapter extends  RecyclerView.Adapter<TrailHolder>{

        private List<TrailItem> trailItemList;

        public TrailAdapter(List<TrailItem> trailItems) {
            this.trailItemList = trailItems;
        }

        @Override
        public TrailHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            View view = layoutInflater.inflate(R.layout.list_trail_item, null); //??

            trailHolder = new TrailHolder(view);
            return trailHolder;
        }

        @Override
        public void onBindViewHolder(TrailHolder holder, int position) {


            TrailItem trailItem = trailItemList.get(position);
            trailHolder.bindTrailItem(trailItem);

        }

        @Override
        public int getItemCount() {
            return trailItemList.size();
        }
    }

    public void onCreateNewTrailButtonClick(View v){
        Intent launchNewRunActivity = new Intent(this, CreateRunActivity.class);
        startActivity(launchNewRunActivity);
    }

    @Override
    protected void onResume() {
        super.onResume();
        trailAdapter.notifyDataSetChanged();
    }
}
