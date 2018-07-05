package com.example.nightc.gobuy.GoBuySDK;

/**
 * Created by Oppai on 7/2/2017.
 */

public class GoalMoneySaved {
    private double MoneySaved; //Money you have already collected for the goal

    public GoalMoneySaved(double moneySaved) {
        MoneySaved = moneySaved;
    }


    //Adds money into the goal progress(usually at the end of each day)
    public void AddMoneySaved(double moneySaved){
        MoneySaved += moneySaved;
    }

    //Getters
    public double getMoneySaved() {
        return MoneySaved;
    }
}
