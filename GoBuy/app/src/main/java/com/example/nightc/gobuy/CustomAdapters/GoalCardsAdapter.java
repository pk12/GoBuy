package com.example.nightc.gobuy.CustomAdapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.example.nightc.gobuy.Activities.Bottom_Tabs_Activity;
import com.example.nightc.gobuy.GoBuySDK.Goal;
import com.example.nightc.gobuy.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.joda.time.LocalDate;

import java.util.ArrayList;


/**
 * Created by Oppai on 7/14/2017.
 */

//A custom adapter to populate the recycler view content
public class GoalCardsAdapter extends RecyclerView.Adapter<GoalCardsAdapter.GoalViewHolder> {

    private ArrayList<Goal> goals;
    private Context context;



    public GoalCardsAdapter(ArrayList<Goal> goals, Context context) {
        this.goals = goals;
        this.context = context;
    }


    //creates the viewholder which holds the contents of the cardview layout
    @Override
    public GoalViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_goals_tab,parent,false);
        return new GoalViewHolder(itemView);

    }

    //populate the Viewholder with each goal Instance
    @Override
    public void onBindViewHolder(final GoalViewHolder holder, int position) {
        final Goal goal = goals.get(position);
        holder.DateWanted.setText(goal.getDateWanted().toString());
        holder.ItemName.setText(goal.getGoalItem().getName());

        //Handle Trigger
        //check if it is user touched
        final boolean[] isTouched = new boolean[1];
        holder.switchCompat.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                isTouched[0] = true;
                return false;
            }
        });

        holder.switchCompat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked && isTouched[0]){
                    //Goal is Activated
                    goal.setDateActivated(new LocalDate());
                    //Calculate how much we have to save per day on the goal and add it to the total amount to save on the goal handler
                    Bottom_Tabs_Activity.goalHandler.incrementMoneyToSave(goal.calculateMoneyToSave());
                    //Update the DB
                    DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Goals/" + FirebaseAuth.getInstance().getCurrentUser().getUid() + "/" + goal.getGoalItem().getName() + "/IsActive");
                    reference.setValue(true);
                    //Add the Goal to the GoalHandler
                    Bottom_Tabs_Activity.goalHandler.getActiveGoals().add(goal);
                    //ReCalculate moneyToSpend
                    Bottom_Tabs_Activity.goalHandler.calculateMoneyToSpend();
                }
                else if (!isChecked && isTouched[0]){
                    //Goal is deactivated
                    //Update the db
                    DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Goals/" + FirebaseAuth.getInstance().getCurrentUser().getUid() + "/" + goal.getGoalItem().getName() + "/IsActive");
                    reference.setValue(false);
                    //Remove the amount from totalMoneyToSave
                    //NOTE: we use - to remove the amount from the variable
                    Bottom_Tabs_Activity.goalHandler.incrementMoneyToSave(- goal.getMoneyToSave());
                    //Remove the Goal from the GoalHandler
                    Bottom_Tabs_Activity.goalHandler.getActiveGoals().remove(goal);
                    //ReCalculate moneyToSpend
                    Bottom_Tabs_Activity.goalHandler.calculateMoneyToSpend();

                }

            }
        });


        if (goal.isActive())
            holder.switchCompat.setChecked(true);
        else
            holder.switchCompat.setChecked(false);

        //Handle options button
        final Button button = holder.optionsButton;
        holder.optionsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(context, button);

                popupMenu.inflate(R.menu.recycler_view_context_menu);

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.Delete:

                                //Remove from handler
                                //toggles false so that if it is active then it is removed from the active list on the switchCompat listener
                                holder.switchCompat.setChecked(false);
                                //Remove form Firebase
                                FirebaseDatabase.getInstance().getReference("Goals/" + FirebaseAuth.getInstance().getCurrentUser().getUid() + "/" + goal.getGoalItem().getName()).removeValue();

                                goals.remove(holder.getAdapterPosition());
                                notifyItemRemoved(holder.getAdapterPosition());
                                return true;
                        }

                        return false;
                    }
                });
                popupMenu.show();
            }
        });
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
        private SwitchCompat switchCompat;
        private Button optionsButton;

        public GoalViewHolder(View itemView) {
            super(itemView);
            ItemIcon = (ImageView) itemView.findViewById(R.id.GoalImageView);
            ItemName = (TextView) itemView.findViewById(R.id.GoalNameText);
            DateWanted = (TextView) itemView.findViewById(R.id.DateWantedText);
            switchCompat = (SwitchCompat) itemView.findViewById(R.id.goalSwitch);
            optionsButton = (Button) itemView.findViewById(R.id.OptionsButton);
        }


    }
}
