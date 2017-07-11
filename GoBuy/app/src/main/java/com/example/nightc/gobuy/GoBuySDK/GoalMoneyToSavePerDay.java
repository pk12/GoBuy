package com.example.nightc.gobuy.GoBuySDK;

import org.joda.time.Period;
import org.joda.time.PeriodType;

import java.util.ArrayList;

/**
 * Created by Oppai on 7/2/2017.
 */

public class GoalMoneyToSavePerDay {
    private double MoneyToSavePerDay; //Money you have to save each day to get the item on the wanted Date


    //Calculates the money that the user has to save per day given the days that he
    //has left,until he reaches the DateWanted
    public GoalMoneyToSavePerDay(GoalDates Dates, ArrayList<Item> GoalItems) {
        ChangeMoneyToSavePerDay(Dates,GoalItems); //uses the method bellow
    }


    //In case the user changes the date wanted,it recalculates the money
    public void ChangeMoneyToSavePerDay(GoalDates Dates,ArrayList<Item> GoalItems){
        Period p = new Period(Dates.getDateCreated(),Dates.getDateWanted(), PeriodType.yearMonthDay());
        double Sum = 0;
        for (Item item : GoalItems){
            Sum += item.getPrice();
        }
        MoneyToSavePerDay = Sum / p.getDays();
    }



    //Getters

    public double getMoneyToSavePerDay() {
        return MoneyToSavePerDay;
    }
}
