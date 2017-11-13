package com.example.thuongdh.qltc;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

public class SettingActivity extends AppCompatActivity {

    TextView tv1, tv2,tv3,tv4,tv5,tv6,tv7;
    Button btnSave, btnCancel;
    ArrayAdapter<String> adapter;
    Spinner spVi, spTime, spUnit, spLanguage;
    Switch swNotifaction;
    SQLiteDatabase database;
    String Database_name = "QuanLyThuChiDb.sqlite";
    String lang="English", wallet, time,unit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        database = openOrCreateDatabase(Database_name, MODE_PRIVATE, null);
        setId();
        addFont();
        showData();
        spLanguage.setOnItemSelectedListener(new EventSelect());
        spVi.setOnItemSelectedListener(new EventSelectWallet());
        spTime.setOnItemSelectedListener(new EventSelectTime());
        spUnit.setOnItemSelectedListener(new EventSelectUnit());
        SaveEvent();
        CancelEvent();
    }

    private void CancelEvent() {
        btnCancel.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SettingActivity.this, StartActivity.class);
                startActivity(i);
            }
        });
    }

    private void SaveEvent() {

        btnSave.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                ContentValues values = new ContentValues();
                int id=0,notice;
                Cursor cursor = database.query("SettingTb", null,null,null,null,null,null);
                while (cursor.moveToNext()) {
                    id = cursor.getInt(0);
                }
                id++;
                values.put("ID", id);
                values.put("Language", lang);
                if (swNotifaction.isChecked()) notice =1; else notice =0;
                values.put("Notifacation", notice);
                values.put("Unit", unit);
                values.put("Wallet",wallet);
                values.put("Time", time);
                long r = database.insert("SettingTb", null,values);
                Intent i = new Intent(SettingActivity.this, MainActivity.class);
                startActivity(i);
            }
        });

    }

    private void showData() {
        showSpinnerLanguage();
        showSpinnerVi();
        showSpinnerTime();
        showSpinnerUnit();
    }

    private void showSpinnerUnit() {
        ArrayList<String> arr = new ArrayList<>();
        arr.add("VND");
        arr.add("USD");
        adapter = new ArrayAdapter<String>(SettingActivity.this,
                android.R.layout.simple_spinner_dropdown_item,
                arr);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spUnit.setAdapter(adapter);

    }

    private void showSpinnerTime() {
        ArrayList<String> arr = new ArrayList<>();
        if (lang=="English")
        {
            arr.add("All");
            arr.add("Month");
            arr.add("Quarter");
            arr.add("Year");
        }
        else
        {
            arr.add("Tất cả");
            arr.add("Tháng");
            arr.add("Quý");
            arr.add("Năm");
        }
        adapter = new ArrayAdapter<String>(
                SettingActivity.this,
                android.R.layout.simple_spinner_dropdown_item,
                arr
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spTime.setAdapter(adapter);

    }

    private void showSpinnerVi() {
        ArrayList<String> arr = new ArrayList<>();
        arr.add("All");
        Cursor cursor =  database.query("NameListTb", null, null, null, null, null,null);
        while (cursor.moveToNext())
        {
            String name = cursor.getString(1);
            arr.add(name);
        }
        cursor.close();

        adapter = new ArrayAdapter<String>(
                SettingActivity.this,
                android.R.layout.simple_spinner_dropdown_item,
                arr);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spVi.setAdapter(adapter);


    }

    private void showSpinnerLanguage() {

        ArrayList<String> arr = new ArrayList<>();
        arr.add("English");
        arr.add("Vietnamese");
        adapter = new ArrayAdapter<String>(
                SettingActivity.this,
                android.R.layout.simple_spinner_dropdown_item,
                arr
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spLanguage.setAdapter(adapter);



    }

    private void addFont() {
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/ft.ttf");
        tv1.setTypeface(typeface);
        tv2.setTypeface(typeface);
        tv3.setTypeface(typeface);
        tv4.setTypeface(typeface);
        tv5.setTypeface(typeface);
        tv6.setTypeface(typeface);
        tv7.setTypeface(typeface);
        btnCancel.setTypeface(typeface);
        btnSave.setTypeface(typeface);

    }

    private void setId() {
        tv1 = (TextView) findViewById(R.id.tvSetting);
        tv2 = (TextView) findViewById(R.id.tvSetting_Language);
        tv3 = (TextView) findViewById(R.id.tvSetting_pa);
        tv4 = (TextView) findViewById(R.id.tvSettingNotification);
        tv5 = (TextView) findViewById(R.id.tvSettingViTien);
        tv6 = (TextView) findViewById(R.id.tvSettingUnit);
        tv7 = (TextView) findViewById(R.id.tvSettingThoiGian);
        btnCancel = (Button) findViewById(R.id.btnSettingCancel);
        btnSave = (Button) findViewById(R.id.btnSettingSave);
        spLanguage = (Spinner) findViewById(R.id.spSetting_Language);
        spTime = (Spinner) findViewById(R.id.spSettingThoiGian);
        spVi = (Spinner) findViewById(R.id.spSettingViTien);
        spUnit = (Spinner) findViewById(R.id.spSettingUnit);
        swNotifaction = (Switch) findViewById(R.id.swSettingNotifacation);
    }


    private class EventSelect implements android.widget.AdapterView.OnItemSelectedListener {
        public void onItemSelected(AdapterView<?> arg0,
                                   View arg1,
                                   int arg2,
                                   long arg3) {
            lang= spLanguage.getSelectedItem().toString();
        }
        public void onNothingSelected(AdapterView<?> arg0) {
            lang = "English";
        }
    }

    private class EventSelectWallet implements AdapterView.OnItemSelectedListener {
        public void onItemSelected(AdapterView<?> arg0,
                                   View arg1,
                                   int arg2,
                                   long arg3) {
            wallet= spVi.getSelectedItem().toString();
        }
        public void onNothingSelected(AdapterView<?> arg0) {
            wallet = "All";
        }
    }

    private class EventSelectTime implements AdapterView.OnItemSelectedListener {
        public void onItemSelected(AdapterView<?> arg0,
                                   View arg1,
                                   int arg2,
                                   long arg3) {
            time= spTime.getSelectedItem().toString();
        }
        public void onNothingSelected(AdapterView<?> arg0) {
            time = "All";
        }
    }

    private class EventSelectUnit implements AdapterView.OnItemSelectedListener {
        public void onItemSelected(AdapterView<?> arg0,
                                   View arg1,
                                   int arg2,
                                   long arg3) {
            unit= spUnit.getSelectedItem().toString();
        }
        public void onNothingSelected(AdapterView<?> arg0) {
            unit = "VND";
        }
    }
}
