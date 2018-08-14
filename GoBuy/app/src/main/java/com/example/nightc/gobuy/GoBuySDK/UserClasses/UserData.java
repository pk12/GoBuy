package com.example.nightc.gobuy.GoBuySDK.UserClasses;

import java.util.HashMap;

/**
 * Created by Oppai on 7/2/2017.
 */

public class UserData {
    private String Area;
    private int age;
    private String gender;
    private double totalIncome;
    private double totalExpense;
    private HashMap<String, ShoppingInterests> shoppingInterests; //shopping interests are multiple
    private HashMap<String,StableIncome> Incomes; //An array of all the extra incomes because there may be multiple extra
    private HashMap<String,StableExpense> expenses; //An array of all the fixed expenses the user has
    private HashMap<String,Income> SpontaneousIncomes; //contains the Incomes the user creates on a goal Day
    private HashMap<String,Expense> spontaneousExpenses; //contains the Expense the user creates on a goal Day

    public UserData() {

    }

    public UserData(String area, int age, String gender, HashMap<String, ShoppingInterests> shoppingInterests,
                    HashMap<String, StableIncome> incomes, HashMap<String, StableExpense> expenses, double totalIncome, double totalExpense) {
        Area = area;
        this.age = age;
        this.gender = gender;
        this.shoppingInterests = shoppingInterests;
        this.Incomes = incomes;
        this.expenses = expenses;
        this.totalExpense = totalExpense;
        this.totalIncome = totalIncome;
    }


    public double CalculateBillAmount(){
        double amount = 0;
        for (HashMap.Entry expense : expenses.entrySet()){
            amount += ((Expense) expense.getValue()).getAmount();
        }
        return amount;
    }

    public double CalculateIncomeAmount(){
        double amount = 0;
        //to be added pay period calculations

        for (HashMap.Entry extraIncome : Incomes.entrySet()){
            amount += ((Income) extraIncome.getValue()).getAmount();
        }
        return amount;
    }


    //getters


    public String getArea() {
        return Area;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public HashMap<String,ShoppingInterests> getShoppingInterests() {
        return shoppingInterests;
    }

    public HashMap<String,StableIncome> getIncomes() {
        return Incomes;
    }

    public HashMap<String,StableExpense> getExpenses() {
        return expenses;
    }

    public HashMap<String,Income> getSpontaneousIncomes() {
        return SpontaneousIncomes;
    }

    public HashMap<String,Expense> getSpontaneousExpenses() {
        return spontaneousExpenses;
    }

    public double getTotalIncome() {
        return totalIncome;
    }

    public double getTotalExpense() {
        return totalExpense;
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



}
