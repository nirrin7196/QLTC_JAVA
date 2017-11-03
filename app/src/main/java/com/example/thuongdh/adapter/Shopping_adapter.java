package com.example.thuongdh.adapter;

import com.example.thuongdh.model.Wallet_name_list;
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
 * Created by thuongdh on 19/10/2017.
 */

public class Shopping_adapter extends ArrayAdapter<Wallet_name_list> {

    Activity context;
    int resource;
    List<Wallet_name_list> object;
    public Shopping_adapter(@NonNull Activity context, @LayoutRes int resource, @NonNull List<Wallet_name_list> objects) {
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

        TextView textView = row.findViewById(R.id.tv_item_lvSp);
        //ImageButton imageButton = row.findViewById(R.id.imbtnDel_lvSp);
        ImageView imageView = row.findViewById(R.id.im_item_lvSp);

        Wallet_name_list w = this.object.get(position);

        imageView.setImageResource(R.drawable.addname);
       // imageButton.setImageResource(delete);
        textView.setText("Tên sản phẩm: "+ w.getName() + " Số tiền  " + w.getMoney() + " " + w.getDv());
        /*imageButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteItems();
            }
        });*/
        return row;
    }

    private void deleteItems() {

    }
}
