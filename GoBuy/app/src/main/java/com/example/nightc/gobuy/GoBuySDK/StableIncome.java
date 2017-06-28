package com.example.nightc.gobuy.GoBuySDK;

/**
 * Created by nightc on 6/25/17.
 */

public abstract class StableIncome extends Income {
    private Bill tax;

    public StableIncome(double monthlyIncome, String Name, double tax, Bill tax1) {
        super(monthlyIncome, Name, tax);
        this.tax = tax1;
    }
}
