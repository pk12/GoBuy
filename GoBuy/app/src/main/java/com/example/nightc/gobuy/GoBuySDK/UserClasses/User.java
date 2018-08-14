package com.example.nightc.gobuy.GoBuySDK.UserClasses;

import com.example.nightc.gobuy.GoBuySDK.Goal;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Oppai on 7/2/2017.
 */

public class User {
    private String UserID;
    private Credentials credentials; //a class contains the User's credentials if the users uses manual login
    private UserData userData;  //a class containing the User's Data
    private ArrayList<Goal> goals; //An array keeping all the active Goals the user has
    private boolean isCustomUser;

    public User() {

    }

    public User(Credentials credentials, UserData userData, String UserID, boolean isCustomUser) {
        this.credentials = credentials;
        this.userData = userData;
        this.UserID = UserID;
        this.isCustomUser = isCustomUser;

        //creates the array list with the goals
        goals = new ArrayList<Goal>();

    }

    //inserts the User IncomesAPI and Expense Amount
    //it was the only correct way i could think of


    public HashMap toHashMap(){
        HashMap hashMap = new HashMap();

        hashMap.put("UID", this.UserID);
        if (!this.isCustomUser == true){
            hashMap.put("Credentials", this.credentials);
        }
        hashMap.put("UserData", this.userData);


        return hashMap;
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

    public String getUserID() {
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
