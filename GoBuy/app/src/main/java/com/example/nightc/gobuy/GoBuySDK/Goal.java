package com.example.nightc.gobuy.GoBuySDK;

import com.google.firebase.database.FirebaseDatabase;

import org.joda.time.Days;
import org.joda.time.LocalDate;

import java.util.HashMap;

/**
 * Created by nightc on 6/25/17.
 */

/* the calculations will dynamically change when the product price needs less than a day to buy */
public class Goal {
    private int goalID;
    private Item GoalItem;
    private int Progress;
    private double moneyToSave; //Money needed exclusively for this goal
    private double moneySavedForGoal; //at the end of the day
    private LocalDate DateWanted; //the date which the User wants to have his goal completed
    private LocalDate ExpectedDate; //the date which our algorithms expect the goal to be completed
    private LocalDate dateActivated;  //the date which the Goal was created
    private boolean IsActive;


    public Goal(){

    }

    public Goal(Item goalItem,LocalDate dateWanted) {
        this.GoalItem = goalItem;
        this.DateWanted = dateWanted;
        this.Progress = 0;
        this.IsActive = false;
        this.moneySavedForGoal = 0;


    }



    public boolean isAchievable(){
        if (Days.daysBetween(this.ExpectedDate,this.DateWanted).getDays() >= 0)
            return true;
        return false;
    }

    public double calculateMoneyToSave(){
        //get price
        double price = GoalItem.getPrice();
        //Remove the amount needed because of the already saved money
        price -= moneySavedForGoal;
        //Calculate moneyToSavePerDay
        Days daysLeft = Days.daysBetween(this.dateActivated, this.DateWanted);
        this.moneyToSave = Math.ceil(price / daysLeft.getDays());
        //upload to DB for ReFetch from the RecyclerView
        FirebaseDatabase.getInstance().getReference("Goals/" + GoalItem.getUserID() + "/" + GoalItem.getName() + "/moneyToSave/").setValue(this.moneyToSave);
        return this.moneyToSave;

    }




    public HashMap toHashMap(){
        HashMap hashMap = new HashMap();
        hashMap.put("DateWanted", this.DateWanted.toString());
        if (dateActivated != null)
            hashMap.put("dateActivated", this.dateActivated.toString());
        hashMap.put("moneyToSave", moneyToSave);
        hashMap.put("Progress", this.Progress);
        hashMap.put("GoalItem", this.GoalItem);
        hashMap.put("IsActive", this.IsActive);

        return hashMap;

    }

    public void HashMapToGoal(HashMap hashMap){
        this.moneyToSave = new Long((Long) hashMap.get("moneyToSave")).doubleValue();
        this.DateWanted = new LocalDate(hashMap.get("DateWanted"));
        this.dateActivated = new LocalDate(hashMap.get("dateActivated"));
        this.Progress = Long.valueOf((Long) hashMap.get("Progress")).intValue();
        this.IsActive = (boolean) hashMap.get("IsActive");
        HashMap hashMap1 = (HashMap) hashMap.get("GoalItem");
        this.GoalItem = new Item();
        this.GoalItem.setCategory((String) hashMap1.get("category"));
        this.GoalItem.setName((String) hashMap1.get("name"));
        if (hashMap1.get("price").toString().contains(".")){
            this.GoalItem.setPrice((Double) hashMap1.get("price"));
        }
        else {
            this.GoalItem.setPrice((Long.valueOf((Long) hashMap1.get("price")).doubleValue()));
        }
        this.GoalItem.setUserID((String) hashMap1.get("userID"));
    }

    //Getters


    public double getMoneyToSave() {
        return moneyToSave;
    }

    public Item getGoalItem() {
        return GoalItem;
    }

    public int getProgress() {
        return Progress;
    }

    public LocalDate getDateWanted() {
        return DateWanted;
    }

    public LocalDate getExpectedDate() {
        return ExpectedDate;
    }

    public LocalDate getDateActivated() {
        return dateActivated;
    }

    public int getGoalID() {
        return goalID;
    }

    public boolean isActive() {
        return IsActive;
    }
    //Setters

    public void setDateActivated(LocalDate dateActivated) {
        this.dateActivated = dateActivated;
    }

    public void setGoalItem(Item goalItem) {
        GoalItem = goalItem;
    }


    public void setMoneyToSave(double moneyToSave) {
        this.moneyToSave = moneyToSave;
    }
}
