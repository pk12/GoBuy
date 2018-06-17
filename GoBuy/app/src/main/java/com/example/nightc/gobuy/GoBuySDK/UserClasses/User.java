package com.example.nightc.gobuy.GoBuySDK.UserClasses;

import com.example.nightc.gobuy.GoBuySDK.Goal;

import java.util.ArrayList;

/**
 * Created by Oppai on 7/2/2017.
 */

public class User {
    private long UserID;
    private Credentials credentials; //a class containg the User's credentials
    private UserData userData;  //a class containing the User's Data
    private ArrayList<Goal> goals; //An array keeping all the active Goals the user has

    public User(Credentials credentials, UserData userData, Goal goal, long UserID) {
        this.credentials = credentials;
        this.userData = userData;
        this.UserID = UserID;

        //creates the array list with the goals
        //sets up the first goal(Expense and Incomes and then adds it into the list
        goals = new ArrayList<Goal>();
        setGoalData(goal);
        addGoal(goal);
    }

    //inserts the User IncomesAPI and Expense Amount
    //it was the only correct way i could think of
    public void setGoalData(Goal goal){
        goal.getIncomes().setTotalIncome(userData.CalculateIncomeAmount());
        goal.getExpenses().setTotalAmount(userData.CalculateBillAmount());
    }

    public void addGoal(Goal goal){
        goal.getExpenses().setTotalAmount(userData.CalculateBillAmount());
        goal.getIncomes().setTotalIncome(userData.CalculateIncomeAmount());
        goals.add(goal);
    }


    //Getters

    public Credentials getCredentials() {
        return credentials;
    }

    public UserData getUserData() {
        return userData;
    }

    public ArrayList<Goal> getGoals() {
        return goals;
    }

    public long getUserID() {
        return UserID;
    }

    //Setters

    public void setCredentials(Credentials credentials) {
        this.credentials = credentials;
    }

    public void setUserData(UserData userData) {
        this.userData = userData;
    }

    public void setGoals(ArrayList<Goal> goals) {
        this.goals = goals;
    }

}
