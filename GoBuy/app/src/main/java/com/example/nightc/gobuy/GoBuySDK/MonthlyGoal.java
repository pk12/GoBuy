package com.example.nightc.gobuy.GoBuySDK;

import org.joda.time.LocalDate;

import java.util.ArrayList;

/**
 * Created by Oppai on 6/27/2017.
 */

public class MonthlyGoal extends Goal {
    private double MoneyToSpend; //maybe to save per month
    private Month month; //will be setting the month to a new one every new month so a setter will bet needed


    public MonthlyGoal(Item goalItem, ArrayList<Income> incomes, double totalMonthlyIncome, double totalMonthlyBills, LocalDate dateWanted, double moneySaved) {
        super(goalItem, incomes, totalMonthlyIncome, totalMonthlyBills, dateWanted, moneySaved);
    }

    //Setters
    public void setMonth(Month month) {
        this.month = month;
    }


    //Getters
    public double getMoneyToSpend() {
        return MoneyToSpend;
    }

    public Month getMonth() {
        return month;
    }
}
