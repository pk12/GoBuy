package com.example.nightc.gobuy.GoBuySDK.UserClasses;

import android.widget.TextView;

import com.example.nightc.gobuy.Activities.Bottom_Tabs_Activity;
import com.example.nightc.gobuy.GoBuySDK.Day;
import com.example.nightc.gobuy.GoBuySDK.Goal;
import com.example.nightc.gobuy.GoBuySDK.GoalComparator;
import com.example.nightc.gobuy.R;
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
    private double moneyToSavePerDay;
    private double moneyToSpendPerDay; //Desription: Money you save each day and have available to spend
    private double dailyIncomes;
    private double dailyExpenses;
    private Bottom_Tabs_Activity bottom_tabs_activity; //To be able to get the Support Action bar
    private Day day;

    public ActiveGoalHandler(Bottom_Tabs_Activity bottom_tabs_activity){
        this.activeGoals = new ArrayList<>();
        this.moneyToSavePerDay = 0;
        this.moneyToSpendPerDay = 0;
        this.bottom_tabs_activity = bottom_tabs_activity;
        starNewDay();
        getDailyValues();
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


    //Get Daily incomes and Expenses
    public void getDailyValues() {
        final double[] dailyIncome = new double[1];
        final double[] dailyExpenses = new double[1];
        //Get dailyIncomes
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users/" + FirebaseAuth.getInstance().getCurrentUser().getUid() + "/UserData/");
        reference.child("/totalDailyIncome/").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                dailyIncome[0] = new Long((Long) dataSnapshot.getValue()).doubleValue();
                ActiveGoalHandler.this.dailyIncomes = dailyIncome[0];
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        //Get dailyExpenses
        reference.child("totalDailyExpense").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                dailyExpenses[0] = new Long((Long) dataSnapshot.getValue()).doubleValue();
                ActiveGoalHandler.this.dailyExpenses = dailyExpenses[0];

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



    }

    public void calculateMoneyToSpend(){
        this.moneyToSpendPerDay = this.dailyIncomes - this.moneyToSavePerDay - this.dailyExpenses;
    }


    public void incrementMoneyToSave(double amount){
        this.moneyToSavePerDay += amount;
        FirebaseDatabase.getInstance().getReference("Days/" + FirebaseAuth.getInstance().getCurrentUser().getUid() + "/" + day.getDate() + "/" + "MoneyToSave/").setValue(this.moneyToSavePerDay);
        //TODO: add a textview on the bottom tabs activity toolbar and insert the information there
        TextView textView = (TextView) bottom_tabs_activity.findViewById(R.id.save_today_textview);
        textView.setText(Double.toString(moneyToSavePerDay));


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
