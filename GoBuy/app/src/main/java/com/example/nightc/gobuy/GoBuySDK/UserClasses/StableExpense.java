package com.example.nightc.gobuy.GoBuySDK.UserClasses;

/**
 * Created by Oppai on 7/17/2017.
 */

//An expense subclass that is a stable expense like a subscription
public class StableExpense extends Expense {
    private String PeriodPaid;

    public StableExpense(String type, double amount,int ExpenseID ,int UserID, String periodPaid) {
        super(type, amount, ExpenseID, UserID);
        PeriodPaid = periodPaid;
    }

    //getters

    public String getPeriodPaid() {
        return PeriodPaid;
    }

    //setters

    public void setPeriodPaid(String periodPaid) {
        PeriodPaid = periodPaid;
    }
}
