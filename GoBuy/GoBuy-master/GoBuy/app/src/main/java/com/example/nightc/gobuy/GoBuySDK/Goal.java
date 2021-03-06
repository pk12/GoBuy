package com.example.nightc.gobuy.GoBuySDK;

import org.joda.time.Days;
import org.joda.time.LocalDate;

import java.util.HashMap;

/**
 * Created by nightc on 6/25/17.
 */

/* the calculations will dynamically change when the product price needs less than a day to buy */
public class Goal {
    private String UserID;
    private int GoalID;
    private Item GoalItem;
    private int Progress;
    private IncomesAPI steadyIncomes;
    private BillsAPI SteadyExpenses;
    private GoalDates Dates; //API for all the Goal dates that we have to use
    private double MoneySaved; //API for the money that have been saved for the goal
    private double MoneyToSavePerDay; //a class that calculates the money to be saved each day  //Desription: Money you save each day and have available to spend

    private Day day;


    public Goal(Item goalItem, IncomesAPI incomes, BillsAPI Expenses, LocalDate dateWanted, double moneySaved, int GoalID, String UserID) {
        this.steadyIncomes = incomes; //the system will fetch the stable Incomes from the UserData
        this.SteadyExpenses = Expenses; //so that the user wont need to add them again,making the proccess easier
        this.GoalItem = goalItem;
        Dates = new GoalDates(GoalID,UserID,dateWanted,new LocalDate());
        MoneySaved = moneySaved;
        this.GoalID = GoalID;
        this.UserID = UserID;
        this.Progress = 0;

       // MoneyToSavePerDay = new GoalMoneyToSavePerDay(Dates,GoalItems); //May change it to Dates.get and Items.get
       // CalMoneySavedPerMonth();  //Comment because i create the demo data for the TabbedFragment
       // startNewDay();

    }



    public boolean Achievable(){
        if (Days.daysBetween(Dates.getExpectedDate(),Dates.getDateWanted()).getDays() >= 0)
            return true;
        return false;
    }



    //WARNING!!! THIS SECTION MAY BE BETTER IF IT WAS ADDED INTO THE USER DATA(maybe not)


    //Calculates the money the user Saves each month
    //WARNING!!! this has to be converted to daily
    //maybe we should prompt the user to insert his Annual IncomesAPI and then we divide it
    //by the Working Days of each year to calculate the daily income
    //ELSE we ask for the monthly IncomesAPI and divide it by the working days(or all the days of the month since the next method calculates even the non working days)
    //to calculate the daily income
    public void CalMoneySavedPerMonth(){
        MoneyToSavePerDay = (steadyIncomes.getTotalIncome() - SteadyExpenses.getTotalAmount());
    }



    //Starts a new day each time it changes,to reset the daily tracking status
    public void startNewDay(){
        day = new Day(MoneyToSavePerDay - MoneyToSavePerDay, MoneyToSavePerDay);
    }


    public HashMap toHashMap(){
        HashMap hashMap = new HashMap();
        hashMap.put("UserID",this.UserID);
        hashMap.put("Progress", this.Progress);
        hashMap.put("MoneySaved", this.MoneySaved);
        hashMap.put("MoneyToSavePerDay", this.MoneyToSavePerDay);

        return hashMap;

    }

    //Getters

    public String getUserID() {
        return UserID;
    }

    public int getGoalID() {
        return GoalID;
    }

    public Item getGoalItem() {
        return GoalItem;
    }

    public IncomesAPI getSteadyIncomes() {
        return steadyIncomes;
    }

    public BillsAPI getSteadyExpenses() {
        return SteadyExpenses;
    }

    public GoalDates getDates() {
        return Dates;
    }

    public double getMoneySaved() {
        return MoneySaved;
    }

    public double getMoneyToSavePerDay() {
        return MoneyToSavePerDay;
    }


    public Day getDay() {
        return day;
    }

    //Setters

    public void setUserID(String userID) {
        UserID = userID;
    }

    public void setGoalID(int goalID) {
        GoalID = goalID;
    }

    public void setGoalItem(Item goalItem) {
        GoalItem = goalItem;
    }

    public void setSteadyIncomes(IncomesAPI steadyIncomes) {
        this.steadyIncomes = steadyIncomes;
    }

    public void setSteadyExpenses(BillsAPI steadyExpenses) {
        SteadyExpenses = steadyExpenses;
    }

    public void setDates(GoalDates dates) {
        Dates = dates;
    }

    public void setMoneySaved(double moneySaved) {
        MoneySaved = moneySaved;
    }

    public void setMoneyToSavePerDay(double moneyToSavePerDay) {
        MoneyToSavePerDay = moneyToSavePerDay;
    }


    public void setDay(Day day) {
        this.day = day;
    }
}
