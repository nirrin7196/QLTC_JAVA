package com.example.thuongdh.qltc;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class StartActivity extends AppCompatActivity {
    public String Database_name = "QuanLyThuChiDb.sqlite";
    public static final String DB_path = "/databases/";
    ImageButton btn;
    Fragment fragment;
    int check = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);


        btn = (ImageButton) findViewById(R.id.btnstart);
        btn.setImageResource(R.drawable.icon);
        btn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (check % 2 ==0){
                    FragmentManager fragmentManager = getFragmentManager();
                    FragmentTransaction transaction = fragmentManager.beginTransaction();
                    transaction.add(R.id.container, new fragment()).commit();

                    check++;
                }
                else
                {
                    FragmentManager fragmentManager = getFragmentManager();
                    FragmentTransaction transaction = fragmentManager.beginTransaction();
                    transaction.remove(new fragment()).commit();
                    check++;
                }



            }
        });


    }




}
