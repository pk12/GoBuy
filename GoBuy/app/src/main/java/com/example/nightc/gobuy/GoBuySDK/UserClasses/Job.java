package com.example.nightc.gobuy.GoBuySDK.UserClasses;

/**
 * Created by Oppai on 7/2/2017.
 */

public class Job {
    private String Type;    //we will add some types and the user will have to choose one of them
    private double Salary;
    private String PeriodGot;  //the time period which he gets the money

    public Job(String type, double salary, String periodGot) {
        Type = type;
        Salary = salary;
        PeriodGot = periodGot;
    }

    //Getters
    public String getType() {
        return Type;
    }

    public double getSalary() {
        return Salary;
    }
}
