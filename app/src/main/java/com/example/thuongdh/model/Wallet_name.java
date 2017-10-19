package com.example.thuongdh.model;

import java.io.Serializable;

/**
 * Created by thuongdh on 17/10/2017.
 */

public class Wallet_name implements Serializable {
    private String Name;
    private int Money;
    public Wallet_name(String name, int money) {
        Name = name;
        Money = money;

    }



    public Wallet_name() {
    }



    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getMoney() {
        return Money;
    }

    public void setMoney(int money) {
        Money = money;
    }


}
