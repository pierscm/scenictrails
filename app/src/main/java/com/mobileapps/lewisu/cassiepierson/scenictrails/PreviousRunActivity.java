package com.mobileapps.lewisu.cassiepierson.scenictrails;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class PreviousRunActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private PrevRunList prevRunList;
    private List<PrevRunItem> runItems;
    private PreviousRunHolder prevRunHolder;
    private PreviousRunAdapter runAdapter;


    /****
     *
     * PreviousRunActivity.java
     *
     * Displays all of a user's previousRunItems through
     * recyclerview. Includes information about date,
     * trail name.
     *
     */

    //ToDo: Update to include distance & time.

    public PreviousRunActivity() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_previous_run);

        recyclerView = (RecyclerView)findViewById(R.id.recycler_view);

        prevRunList = PrevRunList.get();
        runItems = prevRunList.getPrevRuns();


        runAdapter = new PreviousRunAdapter(runItems);
        recyclerView.setAdapter(runAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private class PreviousRunHolder extends RecyclerView.ViewHolder{

        private TextView trailTitleTextView;
        private TextView distanceRunTexView;
        private TextView dateTextView;
        private PrevRunItem prevRunItem;


        public PreviousRunHolder(View itemView) {
            super(itemView);

            trailTitleTextView = (TextView)itemView.findViewById(R.id.trail_name_tv);
            distanceRunTexView = (TextView)itemView.findViewById(R.id.distance_run_tv);
            dateTextView = (TextView)itemView.findViewById(R.id.date_run);

        }

        public void bindPrevRunItem(PrevRunItem runItem){
            this.prevRunItem = runItem;
            trailTitleTextView.setText(prevRunItem.getTrailName());
            distanceRunTexView.setText(""+ prevRunItem.getDistanceRun() + " miles run");
            dateTextView.setText(prevRunItem.getDate());
        }
    }

    private class PreviousRunAdapter extends RecyclerView.Adapter<PreviousRunHolder>{

        private List<PrevRunItem> previousRuns;

        public PreviousRunAdapter(List<PrevRunItem> previousRuns) {
            this.previousRuns = previousRuns;
        }

        @Override
        public PreviousRunHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            View view = layoutInflater.inflate(R.layout.list_prev_run_item, null);

            prevRunHolder = new PreviousRunHolder(view);
            return prevRunHolder;
        }

        @Override
        public void onBindViewHolder(PreviousRunHolder holder, int position) {

            PrevRunItem prevRunItem = previousRuns.get(position);
            prevRunHolder.bindPrevRunItem(prevRunItem);

        }

        @Override
        public int getItemCount() {
            return previousRuns.size();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        runAdapter.notifyDataSetChanged();
    }
}
