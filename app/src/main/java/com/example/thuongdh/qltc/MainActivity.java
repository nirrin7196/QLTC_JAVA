package com.example.thuongdh.qltc;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Locale;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView im;
    String lang;
    SQLiteDatabase database = null;
    public String Database_name = "QuanLyThuChiDb.sqlite";
    public static final String DB_path = "/databases/";
    String DB_name = "QuanLyThuChiDb.sqlite";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        im = (ImageView) findViewById(R.id.imvWelcome);
        im.setImageResource(R.drawable.bg_app);
        processCopy();
        database = openOrCreateDatabase(DB_name, MODE_PRIVATE, null);
        lang = "English";
        Cursor c = database.query("SettingTb",null,null,null,null,null,null);
        if (c != null) {
            while (c.moveToNext()) {
                lang = c.getString(1);
            }
        }
        //   Toast.makeText(Add.this, lang, Toast.LENGTH_SHORT).show();
        if (lang.trim().equals("English")){
            Locale l = new Locale("en");
            Resources res = getResources();
            DisplayMetrics dm = res.getDisplayMetrics();
            Configuration conf = res.getConfiguration();
            conf.locale = l;
            res.updateConfiguration(conf, dm);
        }
        else {
            Locale l = new Locale("vi");
            Resources res = getResources();
            DisplayMetrics dm = res.getDisplayMetrics();
            Configuration conf = res.getConfiguration();
            conf.locale = l;
            res.updateConfiguration(conf, dm);
        }
        changeScreen();
    }

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
   /* protected void onPause()
    {
        super.onPause();
        finish();
    }*/
}
