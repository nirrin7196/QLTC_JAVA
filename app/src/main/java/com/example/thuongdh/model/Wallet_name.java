package com.example.thuongdh.model;

import java.io.Serializable;

import android.media.Image;

/**
 * Created by thuongdh on 17/10/2017.
 */

public class Wallet_name implements Serializable {
    private String Name;
    private double Money;
    private Image Icon;
    public Wallet_name(String name, double money, Image icon) {
        Name = name;
        Money = money;
        Icon = icon;
    }



    public Wallet_name() {
    }



    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public double getMoney() {
        return Money;
    }

    public void setMoney(double money) {
        Money = money;
    }

    public Image getIcon() {
        return Icon;
    }

    public void setIcon(Image icon) {
        Icon = icon;
    }
}
