package com.example.nightc.gobuy.GoBuySDK;

import java.util.ArrayList;

/**
 * Created by nightc on 6/25/17.
 */

public class Income {

    protected Bill Tax; //May Change to Bill
    protected double MonthlyIncome; //Total monthly Income = Jobs.salary + extraIncomes.amount (from UserData)
    // protected ArrayList<Double> gifts; To be added to extraIncomes

    public Income(double monthlyIncome,String Name, Bill tax) {
        MonthlyIncome = monthlyIncome;
        //gifts = new ArrayList<Double>();
        Tax = tax;
    }


    public double getMonthlyIncome() {
        return MonthlyIncome;
    }

//    public ArrayList<Double> getGifts() {
//        return gifts;
//    }
}
