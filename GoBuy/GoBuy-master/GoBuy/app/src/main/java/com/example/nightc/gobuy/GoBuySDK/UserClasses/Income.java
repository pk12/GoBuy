package com.example.nightc.gobuy.GoBuySDK.UserClasses;

import android.graphics.drawable.Drawable;

/**
 * Created by Oppai on 7/17/2017.
 */

public class Income {
    private int UserID;
    private int IncomeID;
    private String type;    //the type of the income,should crate an enum class
    private double Amount;  //the amount of the income
    private Drawable Typeicon; //for gui purposes


    public Income(String type, double amount, int IncomeID, int UserID) {
        this.type = type;
        Amount = amount;
        this.IncomeID = IncomeID;
        this.UserID = UserID;
    }



    //Getters
    public String getType() {
        return type;
    }

    public double getAmount() {
        return Amount;
    }

    public Drawable getTypeicon() {
        return Typeicon;
    }

    public int getIncomeID() {
        return IncomeID;
    }
    //setters


    public void setType(String type) {
        this.type = type;
    }

    public void setAmount(double amount) {
        Amount = amount;
    }

    public void setTypeicon(Drawable typeicon) {
        Typeicon = typeicon;
    }
}
