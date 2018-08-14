package com.example.nightc.gobuy.GoBuySDK;

import java.util.Comparator;

public class GoalComparator implements Comparator<Goal> {
    @Override
    public int compare(Goal o1, Goal o2) {
        //returns the dates from the earliest to the latest
        return o1.getDateWanted().compareTo(o2.getDateWanted());

    }
}
