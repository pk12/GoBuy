package com.example.nightc.gobuy.GoBuySDK;

/**
 * Created by nightc on 6/25/17.
 */

public class Income {

    private Double Tax; //May Change to BillsAPI
    private double TotalIncome; //Total monthly IncomesAPI = Jobs.salary + extraIncomes.amount (from UserData)
    // protected ArrayList<Double> gifts; To be added to extraIncomes

    public Income(double totalIncome, double tax) {
        TotalIncome = totalIncome;
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
