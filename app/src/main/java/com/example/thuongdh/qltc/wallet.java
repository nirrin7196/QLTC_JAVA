package com.example.thuongdh.qltc;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

public class wallet extends AppCompatActivity {
    ListView lvList;
    Button btnBack, btnAdd, btnInfo, btnDel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet);
        setID();
        setEvent();
    }

    private void setID() {
        lvList = (ListView) findViewById(R.id.wllvDanhSach);
        btnAdd = (Button) findViewById(R.id.wlbtnAdd);
        btnBack = (Button)findViewById(R.id.wlbtnBack);
        btnDel = (Button)findViewById(R.id.wlbtnDelete);
    }

    private void setEvent() {
        EventButton();
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
