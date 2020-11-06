package com.timotiushanselkenny.uts.adapter;

import java.util.ArrayList;
import java.util.Random;

import io.bloco.faker.Faker;

public class Drink {
    private String mName;
    private int mPrice;

    public Drink(String name, int price) {
        mName = name;
        mPrice=price;
    }
    public String getName() {
        return mName;
    }
    public String getPrice() {
        return String.valueOf(mPrice);
    }
    private static int lastContactId = 0;

    public static ArrayList<Drink> createDrinkList(int numCharacter) {
        ArrayList<Drink> drinks = new ArrayList<Drink>();
        final int min = 1000;
        final int max = 20000;
        Faker faker = new Faker();
        Random random = new Random();
        for (int i = 1; i <= numCharacter; i++) {
            drinks.add(new Drink("Lemon Juice variant : "+i, random.nextInt((max-min)+1)+min));
        }
        return drinks;
    }
}
