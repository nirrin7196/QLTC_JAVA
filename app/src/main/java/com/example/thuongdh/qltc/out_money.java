package com.example.thuongdh.qltc;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class out_money extends AppCompatActivity {

    Button btnBack, btnAdd;
    ImageView imAn, imNha, imDien, imMua, imMang, imTv;
    ImageButton btnAn, btnNha, btnDien, btnMua, btnMang, btnTv;
    TextView tvname, tvtitle, tv1,tv2,tv3,tv4,tv5,tv6;
    int checkValue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_out_money);
        setID();
        setFont();
        setViewImage();
        setEvent();
    }

    private void setFont() {
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/ft.ttf");
        tvtitle.setTypeface(typeface);
        tvname.setTypeface(typeface);
        tv1.setTypeface(typeface);
        tv2.setTypeface(typeface);
        tv3.setTypeface(typeface);
        tv4.setTypeface(typeface);
        tv5.setTypeface(typeface);
        tv6.setTypeface(typeface);
    //    tvname.setTypeface(typeface);

    }

    private void setEvent() {
        btnNha.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(out_money.this, OutMoneyOne.class);
                checkValue = 2;
                intent.putExtra("Key", checkValue);
                startActivity(intent);
            }
        });
        btnDien.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(out_money.this, OutMoneyOne.class);
                checkValue = 1;
                intent.putExtra("Key", checkValue);
                startActivity(intent);
            }
        });
        btnAn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(out_money.this, OutMoneyOne.class);
                checkValue = 0;
                intent.putExtra("Key", checkValue);
                startActivity(intent);
            }
        });
        btnMang.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(out_money.this, OutMoneyOne.class);
                checkValue = 3;
                intent.putExtra("Key", checkValue);
                startActivity(intent);
            }
        });
        btnTv.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(out_money.this, OutMoneyOne.class);
                checkValue = 4;
                intent.putExtra("Key", checkValue);
                startActivity(intent);
            }
        });
        btnMua.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(out_money.this, Shopping.class);
                checkValue = 5;
                in.putExtra("Key", checkValue);
                startActivity(in);
            }
        });
        btnBack.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(out_money.this, StartActivity.class);
                startActivity(intent);
            }
        });
        btnAdd.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(out_money.this, Shopping.class);
                checkValue = 6;
                in.putExtra("Key", checkValue);
                startActivity(in);
            }
        });
    }

    private void setViewImage() {
        imAn.setImageResource(R.drawable.food);
        imTv.setImageResource(R.drawable.tv);
        imMang.setImageResource(R.drawable.internet);
        imNha.setImageResource(R.drawable.home);
        imDien.setImageResource(R.drawable.electric);
        imMua.setImageResource(R.drawable.shopping);

        btnTv.setImageResource(R.drawable.addname);
        btnMang.setImageResource(R.drawable.addname);
        btnMua.setImageResource(R.drawable.addname);
        btnAn.setImageResource(R.drawable.addname);
        btnDien.setImageResource(R.drawable.addname);
        btnNha.setImageResource(R.drawable.addname);
    }

    private void setID() {
        btnAdd = (Button) findViewById(R.id.btnThemnhieuOutmoney);
        btnBack = (Button) findViewById(R.id.btnTrolaiOutmoney);
        imAn = (ImageView) findViewById(R.id.imAnUong);
        btnAn = (ImageButton) findViewById(R.id.imbtnAnUong);
        imNha = (ImageView) findViewById(R.id.imThueNha);
        btnNha = (ImageButton) findViewById(R.id.imbtnThueNha);
        imDien = (ImageView) findViewById(R.id.imDienNuoc);
        btnDien = (ImageButton) findViewById(R.id.imbtnDienNuoc);
        imMua = (ImageView) findViewById(R.id.imMuaSam);
        btnMua = (ImageButton) findViewById(R.id.imbtnMuaSam);
        imMang = (ImageView) findViewById(R.id.imMang);
        btnMang = (ImageButton) findViewById(R.id.imbtnMang);
        imTv = (ImageView) findViewById(R.id.imTruyenHinh);
        btnTv = (ImageButton) findViewById(R.id.imbtnTruyenHinh);
        tv1 = (TextView) findViewById(R.id.tvOutAn);
        tv2 = (TextView) findViewById(R.id.tvOutDien);
        tv3 = (TextView) findViewById(R.id.tvOutMang);
        tv4 = (TextView) findViewById(R.id.tvOutMua);
        tv5 = (TextView) findViewById(R.id.tvOutName);
        tv6 = (TextView) findViewById(R.id.tvOutNha);
        tvname = (TextView) findViewById(R.id.tvOutTV);
        tvtitle = (TextView) findViewById(R.id.tvOuttitle);
    }
}
