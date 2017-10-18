package com.example.thuongdh.model;

import java.io.Serializable;

/**
 * Created by thuongdh on 17/10/2017.
 */

public class Wallet_type implements Serializable {

    private int imageIconType;
    private String NameType;

    public Wallet_type( int imageIconType, String nameType) {

        this.imageIconType = imageIconType;
        NameType = nameType;
    }

    public Wallet_type() {
    }

    public int getImageIconType() {
        return imageIconType;
    }

    public void setImageIconType(int imageIconType) {
        this.imageIconType = imageIconType;
    }

    public String getNameType() {
        return NameType;
    }

    public void setNameType(String nameType) {
        NameType = nameType;
    }
}
