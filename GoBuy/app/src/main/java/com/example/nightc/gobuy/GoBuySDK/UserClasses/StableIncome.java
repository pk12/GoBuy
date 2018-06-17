package com.example.nightc.gobuy.GoBuySDK.UserClasses;

/**
 * Created by Oppai on 7/17/2017.
 */

public class StableIncome extends Income {
    private String PayPeriod;

    public StableIncome(String type, double amount,int IncomeID,int UserID, String payPeriod) {
        super(type, amount, IncomeID, UserID);
        PayPeriod = payPeriod;
    }






    //getters


    public String getPayPeriod() {
        return PayPeriod;
    }

    //setters

    public void setPayPeriod(String payPeriod) {
        PayPeriod = payPeriod;
    }
}
