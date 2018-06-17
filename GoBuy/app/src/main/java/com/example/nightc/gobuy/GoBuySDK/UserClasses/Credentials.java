package com.example.nightc.gobuy.GoBuySDK.UserClasses;

/**
 * Created by Oppai on 7/2/2017.
 */

public class Credentials {

    private String Username;
    private String Password;
    private int UserID;

    public Credentials(String username, String password, int userID) {
        Username = username;
        Password = password;
        UserID = userID;
    }

    //getters
    public String getUsername() {
        return Username;
    }

    public String getPassword() {
        return Password;
    }

    //setters
    public void setUsername(String username) {
        Username = username;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
