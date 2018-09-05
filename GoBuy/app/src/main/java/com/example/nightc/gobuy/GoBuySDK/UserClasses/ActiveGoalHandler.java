package com.example.nightc.gobuy.GoBuySDK.UserClasses;

import com.example.nightc.gobuy.GoBuySDK.Day;
import com.example.nightc.gobuy.GoBuySDK.Goal;
import com.example.nightc.gobuy.GoBuySDK.GoalComparator;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.joda.time.LocalDate;

import java.util.ArrayList;

public class ActiveGoalHandler {
    //Here all active activeGoals will be added in a list
    //The total goal amount will be calculated
    //and we will have a Day which accounts for all the active activeGoals
    //So the remaining Amount will be split based on the Date Wanted and the number of the activeGoals

    private ArrayList<Goal> activeGoals; //all the active activeGoals sorted based on the priority(Top to Bottom) the priority comparator will be the days between the creation of this goal and the DateWanted
    private double moneySaved = 0; //Must be added here
    private double moneyToSavePerDay = 5;
    private double moneyToSpendPerDay = 5; //Desription: Money you save each day and have available to spend
    private Day day;

    public ActiveGoalHandler(){
        this.activeGoals = new ArrayList<>();
        starNewDay();
    }


    public void starNewDay(){
        this.day = new Day(moneyToSpendPerDay,moneyToSavePerDay);

        //send to db
        final DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Days/" + FirebaseAuth.getInstance().getCurrentUser().getUid() + "/" + new LocalDate());

        //Checks if the day has already been created to avoid daily data overwrite on new login
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (!dataSnapshot.exists())
                    reference.setValue(ActiveGoalHandler.this.day.toHashMap());


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    public void endDay(){
        //Sort activeGoals before distribution in case some have changed
        activeGoals.sort(new GoalComparator());

        moneySaved = day.getMoneyToSave() + day.getMoneyLeftToSpend();
        if (moneySaved > 0 && day.getMoneyLeftToSpend() >= 0){
            //TODO: Distribute money to activeGoals
        }
        else if (moneySaved > 0 && day.getMoneyLeftToSpend() < 0){
            //TODO: Distribute money to activeGoals and recalculate moneyToSavePerDay and moneyToSpendPerDay based on how much behind we are
        }
    }

    //TODO: Make this function with the efficient way first
   //TO run each time a goal is activated on the Trigger
   //May make it to run for the specific goal activated to be more efficient
    public void calcMoneyToSavePerDay(){
        //Fetch totalIncome and totalExpenses from firebase

        //get all the active goals

        //check for each one the amount needed

        //Set the amount in the goal

        // += the moneytosaveperday variable
    }

    //Getters
    public ArrayList<Goal> getActiveGoals() {
        return activeGoals;
    }

    public double getMoneySaved() {
        return moneySaved;
    }

    public double getMoneyToSavePerDay() {
        return moneyToSavePerDay;
    }

    public double getMoneyToSpendPerDay() {
        return moneyToSpendPerDay;
    }

    public Day getDay() {
        return day;
    }

    //Setters
    public void setActiveGoals(ArrayList<Goal> activeGoals) {
        this.activeGoals = activeGoals;
    }

    public void setMoneySaved(double moneySaved) {
        this.moneySaved = moneySaved;
    }

    public void setMoneyToSavePerDay(double moneyToSavePerDay) {
        this.moneyToSavePerDay = moneyToSavePerDay;
    }

    public void setMoneyToSpendPerDay(double moneyToSpendPerDay) {
        this.moneyToSpendPerDay = moneyToSpendPerDay;
    }

    public void setDay(Day day) {
        this.day = day;
    }
}
