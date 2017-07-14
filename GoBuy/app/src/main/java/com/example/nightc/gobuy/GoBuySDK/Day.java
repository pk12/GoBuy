package com.example.nightc.gobuy.GoBuySDK;

import org.joda.time.MonthDay;

/**
 * Created by Oppai on 6/27/2017.
 */

/* will add a machine learning implementation to find out which variable bills(By getting the name) are created,how often etc to improve Estimated Date calculation
 Also,Variable Bill Subclasses may be needed to check how often each subclass appears,and not by name as it is too innacurate
 */

public class Day {
    private MonthDay Day; //May have to change the Data Type
    //private ArrayList<SpontaneousExpense> dailyExpenses for an expense that does not have time period

    //output
    private double MoneyLeftToSpend;
    private double MoneyToSave;


    public Day(double moneyLeftToSpend, double moneytosave) {
        MoneyToSave = moneytosave;
        this.Day = new MonthDay();
        MoneyLeftToSpend = moneyLeftToSpend;
    }
}
