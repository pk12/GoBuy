package com.example.nightc.gobuy.GoBuySDK;

/**
 * Created by nightc on 6/25/17.
 */

public class Income {

    private Bill Tax; //May Change to Bill
    private double TotalIncome; //Total monthly Income = Jobs.salary + extraIncomes.amount (from UserData)
    // protected ArrayList<Double> gifts; To be added to extraIncomes

    public Income(double totalIncome, Bill tax) {
        TotalIncome = 0;
        //gifts = new ArrayList<Double>();
        Tax = tax;
    }


    //getters
    public double getTotalIncome() {
        return TotalIncome;
    }


    //setters
    public void setTotalIncome(double totalIncome) {
        TotalIncome = totalIncome;
    }
}
