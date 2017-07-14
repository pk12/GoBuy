package com.example.nightc.gobuy.GoBuySDK.UserClasses;

import java.util.ArrayList;

/**
 * Created by Oppai on 7/2/2017.
 */

public class Expenses {
    private ArrayList<String> types; //we will add some categories and the user will have to choose one of them
    private double amount;
    private String PeriodPaid; // the time period which the user pays the expense

    public Expenses(ArrayList<String> types, double amount) {
        this.types = types;
        this.amount = amount;
    }

    //Getters
    public ArrayList<String> getType() {
        return types;
    }

    public double getAmount() {
        return amount;
    }
}
