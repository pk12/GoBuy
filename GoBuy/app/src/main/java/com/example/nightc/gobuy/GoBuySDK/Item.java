package com.example.nightc.gobuy.GoBuySDK;

import android.media.Image;

/**
 * Created by nightc on 6/25/17.
 */

public class Item {
    private String Name;
    private String Category; //to be pulled by userdata class // May be hash map of category name and icon
    private double Price;
    private Image icon;


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

    public Image getIcon() {
        return icon;
    }
}
