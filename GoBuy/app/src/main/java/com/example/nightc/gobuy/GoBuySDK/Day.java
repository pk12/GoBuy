package com.example.nightc.gobuy.GoBuySDK;

import com.example.nightc.gobuy.GoBuySDK.UserClasses.Expense;
import com.example.nightc.gobuy.GoBuySDK.UserClasses.Income;

import org.joda.time.LocalDate;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Oppai on 6/27/2017.
 */

/* will add a machine learning implementation to find out which variable bills(By getting the name) are created,how often etc to improve Estimated Date calculation
 Also,Variable BillsAPI Subclasses may be needed to check how often each subclass appears,and not by name as it is too innacurate
 */

public class Day {
    private LocalDate date; //May have to change the Data Type
    private ArrayList<Expense> spontaneousExpenses; //to be sent to user Data
    private ArrayList<Income> spontaneousIncomes;

    //output
    private double MoneyLeftToSpend;
    private double MoneyToSave;
    private double moneySaved;


    public Day(double moneyLeftToSpend, double moneytosave) {
        spontaneousExpenses = new ArrayList<>();
        spontaneousIncomes = new ArrayList<>();
        MoneyToSave = moneytosave;
        this.date = new LocalDate();
        MoneyLeftToSpend = moneyLeftToSpend;
    }

    public HashMap toHashMap(){
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("Expenses", spontaneousExpenses);
        hashMap.put("Incomes", spontaneousIncomes);
        hashMap.put("MoneyLeftToSpend", MoneyLeftToSpend);
        hashMap.put("MoneyToSave", MoneyToSave);
        hashMap.put("moneySaved", moneySaved);
        return hashMap;
    }
    //Getters

    public LocalDate getDate() {
        return date;
    }

    public ArrayList<Expense> getSpontaneousExpenses() {
        return spontaneousExpenses;
    }

    public ArrayList<Income> getSpontaneousIncomes() {
        return spontaneousIncomes;
    }

    public double getMoneyLeftToSpend() {
        return MoneyLeftToSpend;
    }

    public double getMoneyToSave() {
        return MoneyToSave;
    }

    public double getMoneySaved() {
        return moneySaved;
    }
}
