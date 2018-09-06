package com.example.nightc.gobuy.GoBuySDK;

import com.example.nightc.gobuy.GoBuySDK.UserClasses.Income;

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
    private double steadyIncome;
    private double moneyToSave; //Money needed exclusively for this goal
    private double SteadyExpense;
    private double moneySavedForGoal; //at the end of the day
    private LocalDate DateWanted; //the date which the User wants to have his goal completed
    private LocalDate ExpectedDate; //the date which our algorithms expect the goal to be completed
    private LocalDate DateCreated;  //the date which the Goal was created
    private boolean IsActive;


    public Goal(){

    }

    public Goal(Item goalItem, double incomes, double Expenses, LocalDate dateWanted, int goalID) {
        this.steadyIncome = incomes; //the system will fetch the stable Incomes from the UserData
        this.SteadyExpense = Expenses; //so that the user wont need to add them again,making the proccess easier
        this.GoalItem = goalItem;
        this.DateWanted = dateWanted;
        this.DateCreated = new LocalDate();
        this.Progress = 0;
        this.IsActive = false;
        this.goalID = goalID;


    }



    public boolean isAchievable(){
        if (Days.daysBetween(this.ExpectedDate,this.DateWanted).getDays() >= 0)
            return true;
        return false;
    }

    public void calculateMoneyToSave(Income income){
        //hello
    }



    //WARNING!!! THIS SECTION MAY BE BETTER IF IT WAS ADDED INTO THE USER DATA(maybe not)


    //Calculates the money the user Saves each month
    //WARNING!!! this has to be converted to daily
    //maybe we should prompt the user to insert his Annual IncomesAPI and then we divide it
    //by the Working Days of each year to calculate the daily income
    //ELSE we ask for the monthly IncomesAPI and divide it by the working days(or all the days of the month since the next method calculates even the non working days)
    //to calculate the daily income




    public HashMap toHashMap(){
        HashMap hashMap = new HashMap();
        hashMap.put("DateWanted", this.DateWanted.toString());
        hashMap.put("DateCreated", this.DateCreated.toString());
        hashMap.put("SteadyExpense", this.SteadyExpense);
        hashMap.put("SteadyIncome", this.steadyIncome);
        hashMap.put("Progress", this.Progress);
        hashMap.put("GoalItem", this.GoalItem);
        hashMap.put("IsActive", this.IsActive);
        hashMap.put("GoalID", this.goalID);

        return hashMap;

    }

    public void HashMapToGoal(HashMap hashMap){
        this.DateWanted = new LocalDate(hashMap.get("DateWanted"));
        this.DateCreated = new LocalDate(hashMap.get("DateCreated"));
        this.SteadyExpense = Long.valueOf((Long) hashMap.get("SteadyExpense")).doubleValue();
        this.steadyIncome = Long.valueOf((Long) hashMap.get("SteadyIncome")).doubleValue();
        this.Progress = Long.valueOf((Long) hashMap.get("Progress")).intValue();
        this.IsActive = (boolean) hashMap.get("IsActive");
        this.goalID = Long.valueOf((Long) hashMap.get("GoalID")).intValue();
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


    public Item getGoalItem() {
        return GoalItem;
    }

    public double getSteadyIncome() {
        return steadyIncome;
    }

    public double getSteadyExpense() {
        return SteadyExpense;
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

    public LocalDate getDateCreated() {
        return DateCreated;
    }

    public int getGoalID() {
        return goalID;
    }

    public boolean isActive() {
        return IsActive;
    }
    //Setters

    public void setGoalItem(Item goalItem) {
        GoalItem = goalItem;
    }

    public void setSteadyIncome(double steadyIncome) {
        this.steadyIncome = steadyIncome;
    }

    public void setSteadyExpense(double steadyExpense) {
        SteadyExpense = steadyExpense;
    }

    public void setMoneyToSave(double moneyToSave) {
        this.moneyToSave = moneyToSave;
    }
}
