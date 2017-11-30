package com.example.thuongdh.qltc;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class InformationActivity extends AppCompatActivity {

    ImageButton btnback;
    TextView tvtitle, tvdetail;
    SQLiteDatabase database;
    String db = "QuanLyThuChiDb.sqlite";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
        setID();
        setFont();
        database = openOrCreateDatabase(db,MODE_PRIVATE, null);
        settext();
        setBack();
    }

    private void setBack() {
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InformationActivity.this, StartActivity.class);
                startActivity(intent);
            }
        });
    }

    private void settext() {
        String lang = null;
        String text;
        Cursor c = database.query("SettingTb",null,null,null,null,null,null);
        if (c != null) {
            while (c.moveToNext()) {
                lang = c.getString(1);
            }
        }
        if (lang.trim().equals("English")){
            text = "Manage Money Application.\n";
            text += "ID: 14520927.\n";
            text += "Full Name: Duong Hoai Thuong.\n";

        }
        else
        {
            text = "Ung Dung Quan Ly Thu Chi.\n ";
            text += "MSSV: 14520927.\n";
            text += "Ho Ten: Duong Hoai Thuong.\n";
        }
        tvdetail.setText(text);
    }

    private void setFont() {
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/ft.ttf");
        tvtitle.setTypeface(typeface);
        tvdetail.setTypeface(typeface);
        btnback.setImageResource(R.drawable.back);
    }

    private void setID() {
        btnback = (ImageButton) findViewById(R.id.btnInfoBack);
        tvdetail = (TextView) findViewById(R.id.tvInfomationDetail);
        tvtitle = (TextView) findViewById(R.id.tvInfomationTitle);
    }
}
