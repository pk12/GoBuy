package com.example.nightc.gobuy.GoBuySDK.UserClasses;

/**
 * Created by Oppai on 7/2/2017.
 */

public class Expense {
    private String type; //we will add some categories and the user will have to choose one of them
    private double amount;


    public Expense(String type, double amount) {
        this.type = type;
        this.amount = amount;
    }

    //Getters
    public String getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }

    public String getTypes() {
        return type;
    }


    //setters


    public void setTypes(String types) {
        this.type = types;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
