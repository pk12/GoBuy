package com.example.nightc.gobuy.GoBuySDK;

import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.joda.time.Period;
import org.joda.time.PeriodType;

import java.util.ArrayList;

/**
 * Created by nightc on 6/25/17.
 */

/* the calculations will dynamically change when the product price needs less than a month to buy */
public class Goal {

    private Item GoalItem;
    private ArrayList<Income> incomes;
    private ArrayList<Bill> bills;
    private double TotalMonthlyIncome;
    private double TotalMonthlyBills;
    private LocalDate DateWanted;
    private LocalDate ExpectedDate;
    private LocalDate DateCreated;
    private double MoneySaved; //Money you have collected
    private double MoneySavedPerMonth; //Money you save each month and have available to spend
    private double MoneyToSavePerMonth; //Money you have to save to get the itam on the wanted Date
    private Month month;

    public Goal(Item goalItem, ArrayList<Income> incomes,ArrayList<Bill> bills, LocalDate dateWanted, double moneySaved) {
        GoalItem = goalItem;
        this.incomes = incomes; //user will add his incomes on the Oncreate,and then they will be added here
        this.bills = bills;
        DateWanted = dateWanted;
        MoneySaved = moneySaved;
        DateCreated = new LocalDate();
        CalMoneySavedPerMonth();
        CalMoneyToSavePerMonth();
        startNewMonth();

    }

    public void EstimatedDate(){
        int Days = (int) ( TotalMonthlyIncome - TotalMonthlyBills); // if <0 then suggest other ways
    }

    public boolean Achievable(){
        if (Days.daysBetween(ExpectedDate,DateWanted).getDays() >= 0)
            return true;
        return false;
    }

    public void CalMoneySavedPerMonth(){
        MoneySavedPerMonth = (TotalMonthlyIncome - TotalMonthlyBills);
    }

    public void CalMoneyToSavePerMonth(){
        Period p = new Period(DateCreated,DateWanted, PeriodType.yearMonthDay());

        MoneyToSavePerMonth = GoalItem.getPrice() / p.getMonths();
    }

    public void startNewMonth(){
        month = new Month(MoneySavedPerMonth - MoneyToSavePerMonth,MoneyToSavePerMonth);
    }













    //Getters
    public Item getGoalItem() {
        return GoalItem;
    }

    public ArrayList<Income> getIncomes() {
        return incomes;
    }

    public double getTotalMonthlyIncome() {
        return TotalMonthlyIncome;
    }

    public double getTotalMonthlyBills() {
        return TotalMonthlyBills;
    }

    public LocalDate getDateWanted() {
        return DateWanted;
    }

    public LocalDate getExpectedDate() {
        return ExpectedDate;
    }

    public double getMoneySaved() {
        return MoneySaved;
    }
}
