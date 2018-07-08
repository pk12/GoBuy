package com.example.nightc.gobuy.GoBuySDK.UserClasses;

/**
 * Created by Oppai on 7/2/2017.
 */

public class Credentials {

    private String Username;
    private String Password;

    public Credentials(String username, String password) {
        Username = username;
        Password = password;
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
