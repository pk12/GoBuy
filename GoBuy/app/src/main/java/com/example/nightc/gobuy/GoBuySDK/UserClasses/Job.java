package com.example.nightc.gobuy.GoBuySDK.UserClasses;

/**
 * Created by Oppai on 7/2/2017.
 */

public class Job {
    private String Type;    //we will add some types and the user will have to choose one of them
    private double Salary;

    public Job(String type, double salary) {
        Type = type;
        Salary = salary;
    }

    //Getters
    public String getType() {
        return Type;
    }

    public double getSalary() {
        return Salary;
    }
}
