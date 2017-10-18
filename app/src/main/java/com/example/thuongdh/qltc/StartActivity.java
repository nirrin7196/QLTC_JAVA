package com.example.thuongdh.qltc;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class StartActivity extends AppCompatActivity {
    public String Database_name = "QuanLyThuChiDb.sqlite";
    public static final String DB_path = "/databases/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        processCopy();
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.container, new fragment()).commit();

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
