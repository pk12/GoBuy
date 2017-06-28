package com.example.nightc.gobuy.GoBuySDK;

import java.util.ArrayList;

/**
 * Created by nightc on 6/25/17.
 */

public abstract class Income {

    protected double Tax; //May Change to Bill
    protected String Name;
    protected double MonthlyIncome;
    protected ArrayList<Double> gifts;

    public Income(double monthlyIncome,String Name, double tax) {
        this.Name = Name;
        MonthlyIncome = monthlyIncome;
        gifts = new ArrayList<Double>();
        Tax = tax;
    }

    public String getName() {
        return Name;
    }

    public double getMonthlyIncome() {
        return MonthlyIncome;
    }

    public ArrayList<Double> getGifts() {
        return gifts;
    }
}
