package com.example.nightc.gobuy.GoBuySDK.UserClasses;

import java.util.ArrayList;

/**
 * Created by Oppai on 7/2/2017.
 */

public class UserData {
    private String Area;
    private short age;
    private String gender;
    private ArrayList<ShoppingInterests> shoppingInterests; //shopping interests are multiple
    private Job mainJob;
    private ArrayList<ExtraIncome> extraIncomes; //An array of all the extra incomes because there may be multiple extra
    private ArrayList<Expenses> expenses; //An array of all the fixed expenses the user has
    private ArrayList<ExtraIncome> SpontaneousIncomes; //contains the Incomes the user creates on a goal Day
    private ArrayList<Expenses> SpontaneousExpenses; //contains the Expenses the user creates on a goal Day


    public UserData(String area, short age, String gender, ArrayList<ShoppingInterests> shoppingInterests,
                    Job mainJob, ArrayList<ExtraIncome> extraIncomes, ArrayList<Expenses> expenses) {
        Area = area;
        this.age = age;
        this.gender = gender;
        this.shoppingInterests = shoppingInterests;
        this.mainJob = mainJob;
        this.extraIncomes = extraIncomes;
        this.expenses = expenses;
    }


    public double CalculateBillAmount(){
        double amount = 0;
        for (Expenses expense : expenses){
            amount += expense.getAmount();
        }
        return amount;
    }

    public double CalculateIncomeAmount(){
        double amount = mainJob.getSalary();
        for (ExtraIncome extraIncome : extraIncomes){
            amount += extraIncome.getAmount();
        }
        return amount;
    }


    //getters

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

    public ArrayList<ExtraIncome> getExtraIncomes() {
        return extraIncomes;
    }

    public ArrayList<Expenses> getExpenses() {
        return expenses;
    }

    public ArrayList<ExtraIncome> getSpontaneousIncomes() {
        return SpontaneousIncomes;
    }

    public ArrayList<Expenses> getSpontaneousExpenses() {
        return SpontaneousExpenses;
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
