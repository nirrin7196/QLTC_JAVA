package com.example.thuongdh.model;

import java.io.Serializable;

/**
 * Created by thuongdh on 12/11/2017.
 */

public class out_money_model implements Serializable {
    int id;
    String name;
    int money;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public out_money_model() {
    }

    public out_money_model(int id, String name, int money) {
        this.id = id;
        this.name = name;
        this.money = money;
    }
}
