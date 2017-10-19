package com.example.thuongdh.adapter;

import com.example.thuongdh.model.Wallet_type;
import com.example.thuongdh.qltc.R;

import java.util.List;

import android.app.Activity;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

/**
 * Created by thuongdh on 17/10/2017.
 */

public class Wallet_type_adapter extends ArrayAdapter<Wallet_type> {
    Activity context;
    int resource;
    List<Wallet_type> object;
    public Wallet_type_adapter(@NonNull Activity context, @LayoutRes int resource, @NonNull List<Wallet_type> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.object = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = this.context.getLayoutInflater();
        View row = inflater.inflate(this.resource, null);
       // TextView tvName = row.findViewById(R.id.tv_sp_wallet);
        ImageView imIcon = row.findViewById(R.id.image_sp_wallet);

        Wallet_type wallet_type = this.object.get(position);
      //  tvName.setText(wallet_type.getNameType());

        if (wallet_type.getImageIconType()==2){
           imIcon.setImageResource(R.drawable.atm);
        }
        else if (wallet_type.getImageIconType() == 1)
        {
            imIcon.setImageResource(R.drawable.vi);
        }
        return row;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return super.getDropDownView(position, convertView, parent);
    }
}
