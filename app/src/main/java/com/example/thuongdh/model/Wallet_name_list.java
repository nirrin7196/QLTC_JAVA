package com.example.thuongdh.model;

import java.io.Serializable;

/**
 * Created by thuongdh on 19/10/2017.
 */

public class Wallet_name_list implements Serializable {
    private String Name;
    private int Money;
    private String Dv;

    public Wallet_name_list(String name, int money, String dv) {
        Name = name;
        Money = money;
        Dv = dv;
    }

    public Wallet_name_list() {
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

    public String getDv() {
        return Dv;
    }

    public void setDv(String dv) {
        Dv = dv;
    }
}
