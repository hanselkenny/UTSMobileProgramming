package com.timotiushanselkenny.uts.database;

import java.util.ArrayList;
import java.util.Random;

public class DrinkTransaction {
    private int Id;
    private String mName;
    private int mPrice;
    private int mQuantity;
    public DrinkTransaction(String name, int price,int quantity) {
        mName = name;
        mPrice=price;
        mQuantity=quantity;
    }
    public DrinkTransaction() {
    }
    public DrinkTransaction(int Id,String name, int price,int quantity) {
        this.Id=Id;
        mName = name;
        mPrice=price;
        mQuantity=quantity;
    }
    public String getName() {
        return mName;
    }
    public String getPrice() {
        return String.valueOf(mPrice);
    }
    public String getQuantity() {
        return String.valueOf(mQuantity);
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public void setmPrice(int mPrice) {
        this.mPrice = mPrice;
    }

    public void setmQuantity(int mQuantity) {
        this.mQuantity = mQuantity;
    }
}
