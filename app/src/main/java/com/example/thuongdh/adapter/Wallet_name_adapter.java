package com.example.thuongdh.adapter;

import com.example.thuongdh.model.Wallet_name;

import java.util.List;

import android.app.Activity;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.widget.ArrayAdapter;

/**
 * Created by thuongdh on 17/10/2017.
 */

public class Wallet_name_adapter extends ArrayAdapter<Wallet_name> {
    Activity context;
    int resource;
    List<Wallet_name> objects;
    public Wallet_name_adapter(@NonNull Activity context, @LayoutRes int resource, @NonNull List<Wallet_name> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.objects = objects;
    }
}
