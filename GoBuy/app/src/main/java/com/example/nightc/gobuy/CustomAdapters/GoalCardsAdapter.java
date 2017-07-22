package com.example.nightc.gobuy.CustomAdapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nightc.gobuy.GoBuySDK.Goal;
import com.example.nightc.gobuy.R;

import java.util.ArrayList;


/**
 * Created by Oppai on 7/14/2017.
 */

//A custom adapter to populate the recycler view content
public class GoalCardsAdapter extends RecyclerView.Adapter<GoalCardsAdapter.GoalViewHolder> {

    private ArrayList<Goal> goals;



    public GoalCardsAdapter(ArrayList<Goal> goals) {
        this.goals = goals;
    }


    //creates the viewholder which holds the contents of the cardview layout
    @Override
    public GoalViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_goals_tab,parent,false);
        return new GoalViewHolder(itemView);

    }

    //populate the Viewholder with each goal Instance
    @Override
    public void onBindViewHolder(GoalViewHolder holder, int position) {
        Goal goal = goals.get(position);
        holder.DateWanted.setText(goal.getDates().getDateWanted().toString());
        holder.ItemName.setText(goal.getGoalItems().get(0).getName());

    }


    @Override
    public int getItemCount() {
        return goals.size();
    }









    //A viewholder Subclass of the adapter to show the content of the view(CardView Views)
    public class GoalViewHolder extends RecyclerView.ViewHolder {
        private ImageView ItemIcon;
        private TextView ItemName;
        private TextView DateWanted;

        public GoalViewHolder(View itemView) {
            super(itemView);
            ItemIcon = (ImageView) itemView.findViewById(R.id.GoalImageView);
            ItemName = (TextView) itemView.findViewById(R.id.GoalNameText);
            DateWanted = (TextView) itemView.findViewById(R.id.DateWantedText);
        }
    }
}
