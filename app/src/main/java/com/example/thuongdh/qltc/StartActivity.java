package com.example.thuongdh.qltc;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.database.sqlite.SQLiteDatabase;
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
        processCopy();

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



    SQLiteDatabase database = null;
    public void processCopy(){
        File dbFile = getDatabasePath(Database_name);
        if (!dbFile.exists()){
            try {
                CopyDataFromAsset();
            } catch (Exception ex)
            {

            }
        }
    }

    private void CopyDataFromAsset() {
        try {
            InputStream myinput;
            myinput = getAssets().open(Database_name);
            String outfilename = getDatabasePath();
            File f = new File(getApplicationInfo().dataDir + DB_path);
            if (!f.exists()){
                f.mkdir();
            }
            OutputStream myoutput = new FileOutputStream(outfilename);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = myinput.read(buffer)) >0)
            {
                myoutput.write(buffer,0,length);
            }
            myoutput.flush();
            myinput.close();
            myoutput.close();
        }catch (IOException e){

        }
    }

    private String getDatabasePath() {
        return getApplicationInfo().dataDir + DB_path + Database_name;
    }
}
