package com.example.nightc.gobuy.GoBuySDK;

/**
 * Created by nightc on 6/25/17.
 */

public class VariableIncome extends Income {

    private double AnnualSalary;

    //in case of variable income,the message will prompt for Annual Salary
    public VariableIncome(double AnnualIncome, String Name, double tax) {
        super(AnnualIncome, Name, tax);
    }






    public double GetMonthlyIncome() {
        return AnnualSalary/12;
    }
}
