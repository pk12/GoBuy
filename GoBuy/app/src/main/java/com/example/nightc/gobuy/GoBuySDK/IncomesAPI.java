package com.example.nightc.gobuy.GoBuySDK;

/**
 * Created by nightc on 6/25/17.
 */

public class IncomesAPI {

    private BillsAPI Tax; //May Change to BillsAPI
    private double TotalIncome; //Total monthly IncomesAPI = Jobs.salary + extraIncomes.amount (from UserData)
    // protected ArrayList<Double> gifts; To be added to extraIncomes

    public IncomesAPI(double totalIncome, BillsAPI tax) {
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
