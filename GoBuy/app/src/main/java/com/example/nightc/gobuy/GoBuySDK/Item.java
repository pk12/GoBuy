package com.example.nightc.gobuy.GoBuySDK;

/**
 * Created by nightc on 6/25/17.
 */

public class Item {

    private String UserID;
    private String Name;
    private String Category; //to be pulled by userdata class // May be hash map of category name and icon
    private double Price;
//    private Image icon;

    public Item(){

    }

    public Item(String userID, String name, String category, double price) {
        UserID = userID;
        Name = name;
        Category = category;
        Price = price;
    }

 //getters
    public String getUserID() {
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

    //setters

    public void setUserID(String userID) {
        UserID = userID;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public void setPrice(double price) {
        Price = price;
    }


//    public Image getIcon() {
//        return icon;
//    }
}
