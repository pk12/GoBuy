package com.example.nightc.gobuy.GoBuySDK;

import android.media.Image;

/**
 * Created by nightc on 6/25/17.
 */

public class Item {

    private int GoalID;
    private int UserID;
    private String Name;
    private String Category; //to be pulled by userdata class // May be hash map of category name and icon
    private double Price;
    private Image icon;


    public Item(int goalID, int userID, String name, String category, double price) {
        GoalID = goalID;
        UserID = userID;
        Name = name;
        Category = category;
        Price = price;
    }

    public int getGoalID() {
        return GoalID;
    }

    public int getUserID() {
        return UserID;
    }

    public String getCategory() {
        return Category;
    }

    public String getName() {
        return Name;
    }

    public double getPrice() {
        return Price;
    }

    public Image getIcon() {
        return icon;
    }
}
