package com.example.nightc.gobuy.GoBuySDK;

import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.joda.time.Period;
import org.joda.time.PeriodType;

/**
 * Created by nightc on 6/25/17.
 */

/* the calculations will dynamically change when the product price needs less than a day to buy */
public class Goal {

    private Item GoalItem;
    private Income incomes;
    private Bill Expenses;
    private LocalDate DateWanted;
    private LocalDate ExpectedDate;
    private LocalDate DateCreated;
    private double MoneySaved; //Money you have already collected
    private double MoneySavedPerMonth; //Money you save each day and have available to spend
    private double MoneyToSavePerMonth; //Money you have to save to get the itam on the wanted Date
    private Day day;

    public Goal(Item goalItem,Income incomes, Bill Expenses, LocalDate dateWanted, double moneySaved) {
        GoalItem = goalItem;
        this.incomes = incomes; //user will add his incomes on the Oncreate,and then they will be added here
        this.Expenses = Expenses;
        DateWanted = dateWanted;
        MoneySaved = moneySaved;
        DateCreated = new LocalDate();
        CalMoneySavedPerMonth();
        CalMoneyToSavePerMonth();
        startNewMonth();

    }



    public boolean Achievable(){
        if (Days.daysBetween(ExpectedDate,DateWanted).getDays() >= 0)
            return true;
        return false;
    }

    public void CalMoneySavedPerMonth(){
        MoneySavedPerMonth = (incomes.getMonthlyIncome() - Expenses.getAmountPerMonth());
    }

    public void CalMoneyToSavePerMonth(){
        Period p = new Period(DateCreated,DateWanted, PeriodType.yearMonthDay());

        MoneyToSavePerMonth = GoalItem.getPrice() / p.getMonths();
    }

    public void startNewMonth(){
        day = new Day(MoneySavedPerMonth - MoneyToSavePerMonth,MoneyToSavePerMonth);
    }













    //Getters
    public Item getGoalItem() {
        return GoalItem;
    }

    public Income getIncomes() {
        return incomes;
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
