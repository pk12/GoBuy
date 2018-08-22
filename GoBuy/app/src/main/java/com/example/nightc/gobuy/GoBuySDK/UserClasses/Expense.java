package com.example.nightc.gobuy.GoBuySDK.UserClasses;

import java.util.HashMap;

/**
 * Created by Oppai on 7/2/2017.
 */

public class Expense {
    private String name; //we will add some categories and the user will have to choose one of them
    private double amount;


    public Expense(String type, double amount) {
        this.name = type;
        this.amount = amount;
    }

    public Expense() {

    }

    public void hashMapToExpense(HashMap hashMap){
        name = (String) hashMap.get("name");
        amount = Long.valueOf((Long) hashMap.get("amount"));
    }

    //Getters
    public String getName() {
        return name;
    }

    public double getAmount() {
        return amount;
    }

    public String getTypes() {
        return name;
    }

    //setters


    public void setTypes(String types) {
        this.name = types;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
