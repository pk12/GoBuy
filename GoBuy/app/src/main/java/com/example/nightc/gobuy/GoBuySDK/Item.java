package com.example.nightc.gobuy.GoBuySDK;

/**
 * Created by nightc on 6/25/17.
 */

public class Item {
    private String Name;
    private String Category; //to be pulled by userdata class
    private double Price;


    public Item(String name, double price, String category) {
        Name = name;
        Price = price;
        Category = category;
    }


    public String getName() {
        return Name;
    }

    public double getPrice() {
        return Price;
    }

}
