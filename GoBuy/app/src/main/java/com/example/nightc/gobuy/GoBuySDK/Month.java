package com.example.nightc.gobuy.GoBuySDK;

import org.joda.time.MonthDay;

import java.util.ArrayList;

/**
 * Created by Oppai on 6/27/2017.
 */

/* will add a machine learning implementation to find out which variable bills(By getting the name) are created,how often etc to improve Estimated Date calculation
 Also,Variable Bill Subclasses may be needed to check how often each subclass appears,and not by name as it is too innacurate
 */

public class Month {
    private ArrayList<VariableBill> MoneySpent;
    private ArrayList<VariableIncome> MoneyGot;
    private MonthDay month; //May have to change the Data Type
    private double MoneyLeftToSpend;
    private double MoneyToSave;


    public Month(double moneyLeftToSpend, double moneytosave) {
        MoneyToSave = moneytosave;
        MoneySpent = new ArrayList<VariableBill>();
        MoneyGot = new ArrayList<VariableIncome>();
        this.month = new MonthDay();
        MoneyLeftToSpend = moneyLeftToSpend;
    }
}
