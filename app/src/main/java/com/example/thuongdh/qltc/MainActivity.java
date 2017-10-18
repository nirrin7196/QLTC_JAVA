package com.example.thuongdh.qltc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        changeScreen();
    }
    public void changeScreen()
    {
        Thread start = new Thread(){
            public void run()
            {
                try {
                    sleep(2000);
                } catch (Exception ex){

                }
                finally {
                    Intent change = new Intent(MainActivity.this, StartActivity.class);
                    startActivity(change);
                }
            }
        };
        start.start();
    }
    protected void onPause()
    {
        super.onPause();
        finish();
    }
}
