package com.example.nightc.gobuy.GoBuySDK;

import org.joda.time.Days;
import org.joda.time.LocalDate;

import java.util.ArrayList;

/**
 * Created by nightc on 6/25/17.
 */

/* the calculations will dynamically change when the product price needs less than a day to buy */
public class Goal {

    private ArrayList<Item> GoalItems;
    private IncomesAPI steadyIncomes;
    private BillsAPI SteadyExpenses;
    private GoalDates Dates; //API for all the Goal dates that we have to use
    private GoalMoneySaved MoneySaved; //API for the money that have been saved for the goal
    private GoalMoneyToSavePerDay MoneyToSavePerDay; //a class that calculates the money to be saved each day
    private double MoneySavedPerMonth; //WARNING!!! TO BE CONVERTED TO DAILY,Desription: Money you save each day and have available to spend
    private Day day;

    public Goal(Item goalItem, IncomesAPI incomes, BillsAPI Expenses, LocalDate dateWanted, double moneySaved) {
        GoalItems = new ArrayList<Item>();
        GoalItems.add(goalItem);
        this.steadyIncomes = incomes; //the system will fetch the stable Incomes from the UserData
        this.SteadyExpenses = Expenses; //so that the user wont need to add them again,making the proccess easier
        Dates = new GoalDates(dateWanted);
        MoneySaved = new GoalMoneySaved(moneySaved);
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
        MoneySavedPerMonth = (steadyIncomes.getTotalIncome() - SteadyExpenses.getTotalAmount());
    }



    //Starts a new day each time it changes,to reset the daily tracking status
    public void startNewDay(){
        day = new Day(MoneySavedPerMonth - MoneyToSavePerDay.getMoneyToSavePerDay(),MoneyToSavePerDay.getMoneyToSavePerDay());
    }




    //Getters
    public IncomesAPI getIncomes() {
        return steadyIncomes;
    }

    public BillsAPI getExpenses() {
        return SteadyExpenses;
    }

    public ArrayList<Item> getGoalItems() {
        return GoalItems;
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

    public GoalMoneySaved getMoneySaved() {
        return MoneySaved;
    }

    public GoalMoneyToSavePerDay getMoneyToSavePerDay() {
        return MoneyToSavePerDay;
    }

    public double getMoneySavedPerMonth() {
        return MoneySavedPerMonth;
    }

    public Day getDay() {
        return day;
    }
}
