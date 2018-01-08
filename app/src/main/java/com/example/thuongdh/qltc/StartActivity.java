package com.example.thuongdh.qltc;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class StartActivity extends AppCompatActivity {
    public String Database_name = "QuanLyThuChiDb.sqlite";
    public static final String DB_path = "/databases/";
    ImageButton btn;
    int moneyTotal, spending, rest;
    TextView tvTitle, tv1,tv2,tv3, tvMoney, tvSpending, tvRest;
    Fragment fragment;
    SQLiteDatabase database;
    String db= "QuanLyThuChiDb.sqlite";
    int check = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        setId();
        setFont();
        eventButton();
        getData();
        setTextview();
    }

    private void setTextview() {
        tvMoney.setText(String.valueOf(moneyTotal));
        tvSpending.setText(String.valueOf(spending));
//        tvRest.setText(String.valueOf(rest));
    }

    private void getData() {
        database = openOrCreateDatabase(db, MODE_PRIVATE, null);
        moneyTotal = 0;
        spending = 0;
        rest= 0;
        Cursor cursor = database.query("MemoryActionTb", null,null,null,null,null,null);
        while (cursor.moveToNext()){
            spending += cursor.getDouble(2);
        }
        cursor.close();
        Cursor cursor1 = database.query("NameListTb",null,null,null,null,null,null);
        while (cursor1.moveToNext()){
            moneyTotal += cursor1.getInt(2);
        }
        cursor1.close();
        rest = moneyTotal - spending;
    }

    private void eventButton() {
        fragment = new fragment();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (check % 2 ==0){
                    FragmentManager fragmentManager = getFragmentManager();
                    FragmentTransaction transaction = fragmentManager.beginTransaction();
                    transaction.add(R.id.container, fragment).commit();

                    check++;
                }
                else
                {
                    FragmentManager fragmentManager = getFragmentManager();
                    FragmentTransaction transaction = fragmentManager.beginTransaction();
//                    transaction.remove(new fragment()).commit();
                    transaction.remove(fragment).commit();
                    check++;
                }



            }
        });

    }

    private void setFont() {
        btn.setImageResource(R.drawable.icon);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/ft.ttf");
        tvTitle.setTypeface(typeface);
    //    tvRest.setTypeface(typeface);
        tvSpending.setTypeface(typeface);
        tv1.setTypeface(typeface);
      //  tv2.setTypeface(typeface);
        tv3.setTypeface(typeface);
        tvMoney.setTypeface(typeface);
    }

    private void setId() {
        btn = (ImageButton) findViewById(R.id.btnstart);
        tv1 = findViewById(R.id.tvStartMoneyTotal);
     //   tv2 = findViewById(R.id.tvStartRest);
        tv3 = findViewById(R.id.tvTitleStart);
        tvTitle = findViewById(R.id.tvStartSpendingTotal);
        tvMoney = findViewById(R.id.tvStartMoneyTotalValues);
        tvSpending = findViewById(R.id.tvStartSpendingTotalValues);
      //  tvRest = findViewById(R.id.tvStartRestValues);
    }


}
