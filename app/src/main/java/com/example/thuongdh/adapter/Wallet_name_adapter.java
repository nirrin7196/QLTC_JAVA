package com.example.thuongdh.adapter;

import com.example.thuongdh.model.Wallet_name;
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
import android.widget.TextView;

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

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = this.context.getLayoutInflater();
        View row = inflater.inflate(this.resource, null);

        ImageView img = row.findViewById(R.id.item_im_lv);
        TextView txtName = row.findViewById(R.id.item_tvName);
        TextView txtMoney = row.findViewById(R.id.item_tvMoney);
       // ImageButton imgbtn = row.findViewById(R.id.item_btn_delete);

        final Wallet_name wallet_name = this.objects.get(position);
        img.setImageResource(R.drawable.atm);
        txtName.setText(wallet_name.getName());
        txtMoney.setText("So tien con lai: "+ Integer.toString(wallet_name.getMoney()));
        //imgbtn.setImageResource(R.drawable.delete);

      /*  imgbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                XuLiDelete(wallet_name);
            }
        });*/
        return row;
    }

    private void XuLiDelete(Wallet_name wallet_name) {
        
    }
}
