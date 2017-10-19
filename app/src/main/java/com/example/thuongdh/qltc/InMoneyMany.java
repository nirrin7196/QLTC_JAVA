package com.example.thuongdh.qltc;

import com.example.thuongdh.adapter.listview_inManyMoney_adapter;
import com.example.thuongdh.model.Wallet_name_list;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

public class InMoneyMany extends AppCompatActivity {
    Button btnHoanTat, btnDel;
    ImageButton imbtnBack, imgbtnAdd;
    Spinner spName, spDV;
    EditText edtMoney;
    ListView listView;
    int index = 0;
    ArrayList<String> arrDv, arrName, saveValues;
    ArrayAdapter<String> adapterDv, adapterName;
    ArrayList<Wallet_name_list> arrayList;
    listview_inManyMoney_adapter adapterlist;
    SQLiteDatabase database;
    public String Database_name = "QuanLyThuChiDb.sqlite";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_money_many);
        setID();
        database = openOrCreateDatabase(Database_name, MODE_PRIVATE, null);
        ShowSpinner();
        ShowListView();
        eventButton();
    }

    private void eventButton() {
        btnHoanTat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContentValues value = new ContentValues();
                if (index > 0)
                    for (int i = 0; i < index; i++) {
                        String s = saveValues.get(i);
                        int k = s.indexOf(';');
                        String name = s.substring(0, k);
                        String m = s.substring(k + 1);
                        int money = Integer.parseInt(m);
                        Cursor cursor = database.rawQuery("select * from NameListTb where TRIM(Name) = '" + name.trim() + "'", null);
                        cursor.moveToFirst();
                        int old = cursor.getInt(2);
                        money += old;
                        value.put("Money", money);
                        database.update("NameListTb", value, "Name=?", new String[]{name});
                        Toast.makeText(InMoneyMany.this, "Da cap nhat", Toast.LENGTH_LONG).show();
                    }
            }
        });

        imbtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(InMoneyMany.this, StartActivity.class);
                startActivity(in);
            }
        });

        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                arrayList = new ArrayList<Wallet_name_list>();
                adapterlist = new listview_inManyMoney_adapter(InMoneyMany.this,
                        R.layout.item_lv_in_many_money,
                        arrayList);
                listView.setAdapter(adapterlist);
                index = 0;
            }
        });
    }

    private void ShowListView() {
        imgbtnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = (String) spName.getSelectedItem();
                String dv = (String) spDV.getSelectedItem();
                try
                {
                    int money = Integer.parseInt(edtMoney.getText().toString());

                    arrayList.add(new Wallet_name_list(name, money, dv));
                    adapterlist = new listview_inManyMoney_adapter(InMoneyMany.this,
                            R.layout.item_lv_in_many_money,
                            arrayList);
                    listView.setAdapter(adapterlist);
                    saveValues.add(name + ";" + money);
                    index++;
                } catch (Exception e) {
                    Toast.makeText(InMoneyMany.this, "Vui long nhap day du thong tin", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void ShowSpinner() {
        showSpName();
        showSpDV();
    }

    private void showSpDV() {
        arrDv = new ArrayList<>();
        arrDv.add("VND");
        arrDv.add("USD");
        adapterDv = new ArrayAdapter<String>(
                InMoneyMany.this,
                android.R.layout.simple_spinner_dropdown_item,
                arrDv
        );
        adapterDv.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spDV.setAdapter(adapterDv);
    }

    private void showSpName() {
        arrName = new ArrayList<>();
        Cursor cursor = database.query("NameListTb", null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            String name = cursor.getString(1);
            arrName.add(name);
        }
        cursor.close();
        adapterName = new ArrayAdapter<String>(
                InMoneyMany.this,
                android.R.layout.simple_spinner_dropdown_item,
                arrName
        );
        adapterName.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spName.setAdapter(adapterName);
    }

    private void setID() {
        saveValues = new ArrayList<String>();
        arrayList = new ArrayList<Wallet_name_list>();
        btnHoanTat = (Button) findViewById(R.id.btnHoanTatInMonyMany);
        btnDel = (Button) findViewById(R.id.btnXoaInMoneyMany);
        imbtnBack = (ImageButton) findViewById(R.id.imbtnBackInMoneyMany);
        imgbtnAdd = (ImageButton) findViewById(R.id.imbtnAddInMoneyMany);
        spDV = (Spinner) findViewById(R.id.spDVInMoneyMany);
        spName = (Spinner) findViewById(R.id.spNameInManyMoney);
        edtMoney = (EditText) findViewById(R.id.edtSoTienInMoneyMany);
        listView = (ListView) findViewById(R.id.lvInMonyMany);
        imbtnBack.setImageResource(R.drawable.back);
        imgbtnAdd.setImageResource(R.drawable.addname);
    }
}
