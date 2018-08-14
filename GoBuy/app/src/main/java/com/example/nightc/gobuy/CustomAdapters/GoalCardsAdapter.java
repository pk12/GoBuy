package com.example.nightc.gobuy.CustomAdapters;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nightc.gobuy.Activities.Bottom_Tabs_Activity;
import com.example.nightc.gobuy.GoBuySDK.Goal;
import com.example.nightc.gobuy.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;


/**
 * Created by Oppai on 7/14/2017.
 */

//A custom adapter to populate the recycler view content
public class GoalCardsAdapter extends RecyclerView.Adapter<GoalCardsAdapter.GoalViewHolder> {

    private ArrayList<Goal> goals;
    private ArrayList<Goal> activeGoals;



    public GoalCardsAdapter(ArrayList<Goal> goals) {
        this.goals = goals;
        this.activeGoals = new ArrayList<>();
    }


    //creates the viewholder which holds the contents of the cardview layout
    @Override
    public GoalViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_goals_tab,parent,false);
        return new GoalViewHolder(itemView);

    }

    //populate the Viewholder with each goal Instance
    @Override
    public void onBindViewHolder(GoalViewHolder holder, final int position) {
        final Goal goal = goals.get(position);
        holder.DateWanted.setText(goal.getDateWanted().toString());
        holder.ItemName.setText(goal.getGoalItem().getName());
        holder.switchCompat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    //Goal is Activated
                    activeGoals.add(goal);
                    //Update the DB
                    DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Goals/" + FirebaseAuth.getInstance().getCurrentUser().getUid() + "/" + goal.getGoalID() + "/IsActive");
                    reference.setValue(true);
                    //Add the Goal to the GoalHandler
                    Bottom_Tabs_Activity.goalHandler.getActiveGoals().add(goal);
                }
                else {
                    //Goal is deactivated
                    activeGoals.remove(goal);
                    //Update the db
                    DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Goals/" + FirebaseAuth.getInstance().getCurrentUser().getUid() + "/" + goal.getGoalID() + "/IsActive");
                    reference.setValue(false);
                    //Remove the Goal from the GoalHandler
                    Bottom_Tabs_Activity.goalHandler.getActiveGoals().remove(goal);
                }

            }
        });

        if (goal.isActive())
            holder.switchCompat.setChecked(true);
        else
            holder.switchCompat.setChecked(false);

    }


    @Override
    public int getItemCount() {
        return goals.size();
    }

    //getter


    public ArrayList<Goal> getActiveGoals() {
        return activeGoals;
    }

    //A viewholder Subclass of the adapter to show the content of the view(CardView Views)
    public class GoalViewHolder extends RecyclerView.ViewHolder {
        private ImageView ItemIcon;
        private TextView ItemName;
        private TextView DateWanted;
        private SwitchCompat switchCompat;

        public GoalViewHolder(View itemView) {
            super(itemView);
            ItemIcon = (ImageView) itemView.findViewById(R.id.GoalImageView);
            ItemName = (TextView) itemView.findViewById(R.id.GoalNameText);
            DateWanted = (TextView) itemView.findViewById(R.id.DateWantedText);
            switchCompat = (SwitchCompat) itemView.findViewById(R.id.goalSwitch);
        }
    }
}
