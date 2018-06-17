package com.example.nightc.gobuy.GoBuySDK;

import org.joda.time.LocalDate;

/**
 * Created by Oppai on 7/2/2017.
 */

public class GoalDates {
    private int GoalID;
    private int UserID;
    private LocalDate DateWanted; //the date which the User wants to have his goal completed
    private LocalDate ExpectedDate; //the date which our algorithms expect the goal to be completed
    private LocalDate DateCreated;  //the date which the Goal was created

    public GoalDates(int goalID, int userID, LocalDate dateWanted, LocalDate dateCreated) {
        GoalID = goalID;
        UserID = userID;
        DateWanted = dateWanted;
        DateCreated = dateCreated;
    }


    //Getters
    public LocalDate getDateWanted() {
        return DateWanted;
    }

    public LocalDate getExpectedDate() {
        return ExpectedDate;
    }

    public LocalDate getDateCreated() {
        return DateCreated;
    }

    public int getGoalID() {
        return GoalID;
    }

    public int getUserID() {
        return UserID;
    }

    //Setters
    public void setDateWanted(LocalDate dateWanted) {
        DateWanted = dateWanted;
    }

    public void setExpectedDate(LocalDate expectedDate) {
        ExpectedDate = expectedDate;
    }

    public void setDateCreated(LocalDate dateCreated) {
        DateCreated = dateCreated;
    }
}
