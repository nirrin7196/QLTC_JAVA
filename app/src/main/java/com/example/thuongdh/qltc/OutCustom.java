package com.example.thuongdh.qltc;

import com.example.thuongdh.qltc.fragment_food.ValuesWithActivity;
import com.example.thuongdh.qltc.fragment_other.ValuesWithActivityOther;
import com.example.thuongdh.qltc.fragment_shopping.ValuesWithActivityShopping;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class OutCustom extends AppCompatActivity implements ValuesWithActivity, ValuesWithActivityShopping, ValuesWithActivityOther {
    TextView tv1, tv2, tv3;
    Button btnFood, btnShopping, btnOther, btnFinish, btnCancel;
    EditText edtDate;
    String Name, Type, date,reportDate;
    int index, money;
    int count = 0;
    Fragment fragment;
    ArrayList<String> arrAction;
    ArrayList<String> valuesToAddDatabase;
    SQLiteDatabase database;
    String db_name = "QuanLyThuChiDb.sqlite";
    ArrayAdapter<String> spadapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_out_custom);
        setID();
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        Date today = Calendar.getInstance().getTime();
        reportDate = df.format(today);
        setFont();
        database = openOrCreateDatabase(db_name, MODE_PRIVATE, null);
        readDataSpinner();
        setFragment();
        ReadData();
        SaveValues();
        CancelEvent();
    }

    private void CancelEvent() {
        btnCancel.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OutCustom.this, out_money.class);
                startActivity(intent);
            }
        });
    }

    private void ReadData() {
        Cursor c = database.query("MemoryActionTb", null,null,null,null,null,null);
        while (c.moveToNext())
        {
            index = c.getInt(0);
        }
    }

    private void SaveValues() {

        btnFinish.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                for (int i= 0; i< count; i++){
                    ContentValues v = new ContentValues();
                    String s = valuesToAddDatabase.get(i);
                    int k = s.indexOf(";");
                    Type = s.substring(0,k);
                    Name = s.substring(k+1, s.lastIndexOf(";"));
                    money = Integer.parseInt(s.substring(s.lastIndexOf(";")+1, s.length()));
                    v.put("ID", index++);
                    v.put("Name",Name);
                    v.put("Money", money);
                    v.put("DateUse",edtDate.getText().toString());
                    v.put("DateWrite", reportDate);
                    database.insert("MemoryActionTb",null,v);
                    Toast.makeText(OutCustom.this, "Done", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(OutCustom.this, out_money.class);
                    startActivity(intent);
                }
            }
        });

    }

    private void readDataSpinner() {
        arrAction = new ArrayList<String>();
        Cursor c = database.query("ActionListTb", null,null,null,null,null,null);
        while (c.moveToNext()){
            arrAction.add(c.getString(1));
        }
    }

    private void setFragment() {
        btnFood.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                tv2.setText(null);
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.add(R.id.frment, new fragment_food()).commit();
            }
        });
        btnShopping.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                tv2.setText(null);
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.add(R.id.frment, new fragment_shopping()).commit();
            }
        });
        btnOther.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                tv2.setText(null);
                FragmentManager fragmentManager = getFragmentManager();
                fragment_other fr = new fragment_other();
                Bundle bundle = new Bundle();
                bundle.putStringArrayList("key_spinner", arrAction);
                fr.setArguments(bundle);
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.add(R.id.frment, fr).commit();
            }
        });

    }

    private void setFont() {
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/ft.ttf");
        tv1.setTypeface(typeface);
        tv2.setTypeface(typeface);
        tv3.setTypeface(typeface);
        btnShopping.setTypeface(typeface);
        btnOther.setTypeface(typeface);
        btnFood.setTypeface(typeface);
        btnFinish.setTypeface(typeface);
        btnCancel.setTypeface(typeface);
        edtDate.setTypeface(typeface);
        edtDate.setText(reportDate);
    }

    private void setID() {
        tv3 = (TextView) findViewById(R.id.tvCustomDate);
        tv2 = (TextView) findViewById(R.id.tvCustomEmpty);
        tv1 = (TextView) findViewById(R.id.tvCustomTitle);
        edtDate = (EditText) findViewById(R.id.edtCustomDate);
        btnCancel = (Button) findViewById(R.id.btnCustomCancel);
        btnFinish = (Button) findViewById(R.id.btnCustomFinish);
        btnFood = (Button) findViewById(R.id.btnCustomFood);
        btnOther = (Button) findViewById(R.id.btnCustomOther);
        btnShopping = (Button) findViewById(R.id.btnCustomShopping);
        valuesToAddDatabase = new ArrayList<String>();
    }


    @Override
    public void SendToActivity(String key) {
        valuesToAddDatabase.add(key);
        count++;
    }
    @Override
   public void SendToActivityShopping(String key){
       valuesToAddDatabase.add(key);
       count++;
   }
    @Override
    public void SendToActivityOther(String key){
        valuesToAddDatabase.add(key);
        count++;
    }
}
