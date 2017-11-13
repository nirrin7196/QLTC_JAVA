package com.example.thuongdh.adapter;

import com.example.thuongdh.model.out_money_model;
import com.example.thuongdh.qltc.R;

import java.util.List;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by thuongdh on 12/11/2017.
 */

public class outCustomAdapter extends ArrayAdapter<out_money_model> {
    Activity context;
    int resource;
    List<out_money_model> objects;
    public outCustomAdapter(@NonNull Activity context, int resource, @NonNull List<out_money_model> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.objects = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = this.context.getLayoutInflater();
        View row = inflater.inflate(this.resource, null);
        TextView tv = row.findViewById(R.id.tvlvcustom);

        out_money_model out = this.objects.get(position);
        tv.setText(""+out.getId() + "." + out.getName() + ": "+out.getMoney());
        return row;
    }
}
