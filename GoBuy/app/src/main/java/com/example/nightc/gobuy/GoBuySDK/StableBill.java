package com.example.nightc.gobuy.GoBuySDK;

/**
 * Created by Oppai on 6/27/2017.
 */

public class StableBill extends Bill {
    private double maxAmountPaid;
    private double leastAmountPaid;

    public StableBill( String name, double amountPerMonth) {
        super(name, amountPerMonth);
    }
}
