package com.example.nightc.gobuy.GoBuySDK;

/**
 * Created by Oppai on 6/26/2017.
 */

public class Bill {

    //private boolean paid;
    private String Name;
    private double AmountPerMonth;
    //May add Max amount and min amount to display worst and best case scenarios

    public Bill(String name, double amountPerMonth) {
       // this.paid = paid;
        Name = name;
        AmountPerMonth = amountPerMonth;
    }



    //Getters
    public double getAmountPerMonth() {
        return AmountPerMonth;
    }
}
