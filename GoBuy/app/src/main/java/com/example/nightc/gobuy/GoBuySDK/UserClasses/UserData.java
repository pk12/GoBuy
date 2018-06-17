package com.example.nightc.gobuy.GoBuySDK.UserClasses;

import java.util.ArrayList;

/**
 * Created by Oppai on 7/2/2017.
 */

public class UserData {
    private int UserID;
    private String Area;
    private short age;
    private String gender;
    private ArrayList<ShoppingInterests> shoppingInterests; //shopping interests are multiple
    private Job mainJob;
    private ArrayList<StableIncome> Incomes; //An array of all the extra incomes because there may be multiple extra
    private ArrayList<StableExpense> expenses; //An array of all the fixed expenses the user has
    private ArrayList<SpontaneousIncome> SpontaneousIncomes; //contains the Incomes the user creates on a goal Day
    private ArrayList<SpontaeousExpense> spontaneousExpenses; //contains the Expense the user creates on a goal Day


    public UserData(String area, short age, String gender, ArrayList<ShoppingInterests> shoppingInterests,
                    Job mainJob, ArrayList<StableIncome> extraIncomes, ArrayList<StableExpense> expenses, int UserID) {
        Area = area;
        this.age = age;
        this.gender = gender;
        this.shoppingInterests = shoppingInterests;
        this.mainJob = mainJob;
        this.Incomes = extraIncomes;
        this.expenses = expenses;
        this.UserID = UserID;
    }


    public double CalculateBillAmount(){
        double amount = 0;
        for (Expense expense : expenses){
            amount += expense.getAmount();
        }
        return amount;
    }

    public double CalculateIncomeAmount(){
        double amount = mainJob.getAmount();
        //to be added pay period calculations

        for (StableIncome extraIncome : Incomes){
            amount += extraIncome.getAmount();
        }
        return amount;
    }


    //getters


    public int getUserID() {
        return UserID;
    }

    public String getArea() {
        return Area;
    }

    public short getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public ArrayList<ShoppingInterests> getShoppingInterests() {
        return shoppingInterests;
    }

    public Job getMainJob() {
        return mainJob;
    }

    public ArrayList<StableIncome> getExtraIncomes() {
        return Incomes;
    }

    public ArrayList<StableExpense> getExpenses() {
        return expenses;
    }

    public ArrayList<SpontaneousIncome> getSpontaneousIncomes() {
        return SpontaneousIncomes;
    }

    public ArrayList<SpontaeousExpense> getSpontaneousExpenses() {
        return spontaneousExpenses;
    }

    //setters

    public void setArea(String area) {
        Area = area;
    }

    public void setAge(short age) {
        this.age = age;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setMainJob(Job mainJob) {
        this.mainJob = mainJob;
    }
}
