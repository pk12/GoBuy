package com.example.nightc.gobuy.GoBuySDK.UserClasses;

import android.graphics.drawable.Drawable;

/**
 * Created by Oppai on 7/2/2017.
 */

public class ExtraIncome {
    private String type;    //we will add some categories and the user will have to choose one of them
    private double Amount;
    private Drawable icon; //for gui purposes


    public ExtraIncome(String type, double amount) {
        this.type = type;
        Amount = amount;
    }

    //Getters

    public String getType() {
        return type;
    }

    public double getAmount() {
        return Amount;
    }
}
