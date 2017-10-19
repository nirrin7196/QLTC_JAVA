package com.example.thuongdh.qltc;

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
import android.widget.Spinner;
import android.widget.Toast;

public class InMoney extends AppCompatActivity {
    Button btnAddOne, btnAddMany;
    EditText edtMoney;
    Spinner spDonVi, spVi;
    ImageButton imbtnBack;
    ArrayList<String> arrayListDonVi, arrayListName;
    ArrayAdapter<String> adapterDonVi, adapterName;
    SQLiteDatabase database;
    int id;
    public String Database_name = "QuanLyThuChiDb.sqlite";
    public static final String DB_path = "/databases/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_money);
        database = openOrCreateDatabase(Database_name, MODE_PRIVATE, null);
        setId();
        show2spinner();
        setEvent();
    }

    private void show2spinner() {
        showSpinnerDV();
        showSpinnerName();
    }

    private void showSpinnerDV() {
        arrayListDonVi = new ArrayList<>();
        arrayListDonVi.add("VND");
        arrayListDonVi.add("USD");
        adapterDonVi = new ArrayAdapter<String>(
                InMoney.this,
                android.R.layout.simple_spinner_dropdown_item,
                arrayListDonVi
        );
        adapterDonVi.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spDonVi.setAdapter(adapterDonVi);
    }

    private void showSpinnerName() {
        readDataBase();
    }

    private void readDataBase() {

        arrayListName = new ArrayList<>();
        Cursor cursor =  database.query("NameListTb", null, null, null, null, null,null);
        while (cursor.moveToNext())
        {
            String name = cursor.getString(1);
            arrayListName.add(name);
        }
        cursor.close();

        adapterName = new ArrayAdapter<String>(
                InMoney.this,
                android.R.layout.simple_spinner_dropdown_item,
                arrayListName);
        adapterName.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spVi.setAdapter(adapterName);
    }

    private void setEvent() {
        imbtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InMoney.this, StartActivity.class);
                startActivity(intent);
            }
        });
        btnAddOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = (String) spVi.getSelectedItem();
                String dv = (String) spDonVi.getSelectedItem();
                int money = Integer.parseInt(edtMoney.getText().toString());
                Cursor cursor = database.rawQuery("select * from NameListTb where TRIM(Name) = '" + name.trim() +"'",null);
                cursor.moveToFirst();
                int old = cursor.getInt(2);
                money += old;
                ContentValues values = new ContentValues();
                values.put("Money", money);
                values.put("DV", dv);
                database.update("NameListTb", values, "Name=?", new String[] {name});
                Toast.makeText(InMoney.this, "Da cap nhat", Toast.LENGTH_LONG).show();
            }
        });
        btnAddMany.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InMoney.this, InMoneyMany.class);
                startActivity(intent);
            }
        });
    }

    private void setId() {
        btnAddMany = (Button) findViewById(R.id.btnAddManyInMoney);
        btnAddOne = (Button) findViewById(R.id.btnAddOneInMoney);
        edtMoney = (EditText) findViewById(R.id.edtSoTienInMoney);
        spDonVi = (Spinner) findViewById(R.id.spDonViInMoney);
        spVi = (Spinner) findViewById(R.id.spViTienInMoney);
        imbtnBack = (ImageButton) findViewById(R.id.btnBackInMoney);
        imbtnBack.setImageResource(R.drawable.back);
    }
}
