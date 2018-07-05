package com.example.nightc.gobuy.GoBuySDK;

/**
 * Created by Oppai on 6/26/2017.
 */

public class BillsAPI {

    //private boolean paid;
    private double TotalAmount;
    //May add Max amount and min amount to display worst and best case scenarios

    public BillsAPI( double totalAmount) {

        TotalAmount = totalAmount;
    }



    //Getters
    public double getTotalAmount() {
        return TotalAmount;
    }

    //Setters

    public void setTotalAmount(double totalAmount) {
        TotalAmount = totalAmount;
    }
}
