package com.example.nightc.gobuy.GoBuySDK.UserClasses;

/**
 * Created by Oppai on 7/17/2017.
 */

public class Income {
    private String Name;    //the Name of the income,should crate an enum class
    private double Amount;  //the amount of the income


    public Income(String Name, double amount) {
        this.Name = Name;
        Amount = amount;
    }



    //Getters
    public String getName() {
        return Name;
    }

    public double getAmount() {
        return Amount;
    }

    //setters


    public void setName(String name) {
        this.Name = name;
    }

    public void setAmount(double amount) {
        Amount = amount;
    }

}
