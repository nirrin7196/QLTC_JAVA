package com.example.thuongdh.qltc;

import com.example.thuongdh.adapter.Wallet_name_adapter;
import com.example.thuongdh.model.Wallet_name;

import java.util.ArrayList;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

public class wallet extends AppCompatActivity {
    ListView lvList;
    SQLiteDatabase database;
    public String Database_name = "QuanLyThuChiDb.sqlite";
    ImageButton btnBack, btnAdd, btnInfo, btnDel;
    ArrayList<Wallet_name> arrayList;
    Wallet_name_adapter wallet_name_adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet);
        Opendatabae();
        setID();
        setEvent();
    }

    private void Opendatabae() {
        database = openOrCreateDatabase(Database_name, MODE_PRIVATE, null);
    }

    private void setID() {
        lvList = (ListView) findViewById(R.id.wllvDanhSach);
        btnAdd = (ImageButton) findViewById(R.id.wlbtnAdd);
        btnBack = (ImageButton) findViewById(R.id.wlbtnBack);
       // btnDel = (ImageButton) findViewById(R.id.wlbtnDelete);
        btnAdd.setImageResource(R.drawable.addname);
        btnBack.setImageResource(R.drawable.bk);

    }

    private void setEvent() {
        EventButton();
        ShowListView();
    }

    private void ShowListView() {

        arrayList = new ArrayList<>();
        Cursor cursor = database.query("NameListTb", null, null, null, null,null,null);
        // arrType.clear();
        arrayList.clear();
        while (cursor.moveToNext())
        {
            String name = cursor.getString(1);
            int money = cursor.getInt(2);
            arrayList.add(new Wallet_name(name,money));
            //    arrType.add(new Wallet_type(icon,name));
        }
        cursor.close();
        wallet_name_adapter = new Wallet_name_adapter(
                wallet.this,
                R.layout.item_lv_wallet,
                arrayList
        );
        lvList.setAdapter(wallet_name_adapter);
    }

    private void EventButton() {
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(wallet.this, StartActivity.class);
                startActivity(intent);
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 =new  Intent(wallet.this, Add.class);
                startActivity(intent2);
            }
        });
    }
    protected void onPause()
    {
        super.onPause();
        finish();
    }

}
