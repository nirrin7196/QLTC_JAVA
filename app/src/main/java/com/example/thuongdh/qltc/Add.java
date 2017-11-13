package com.example.thuongdh.qltc;

import com.example.thuongdh.adapter.Wallet_type_adapter;
import com.example.thuongdh.model.Wallet_type;

import java.util.ArrayList;
import java.util.Locale;

import android.content.ContentValues;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Add extends AppCompatActivity {
    EditText edtName, edtMoney;
    Spinner spType;
    Button btnSave, btnCancel;
    ArrayList<Wallet_type> arrType;
    TextView tvName, tvTitle, tvMoney;
    String Name;
    int Money =0;
    int id;
    String lang;
    Wallet_type_adapter adapter;
    SQLiteDatabase database;
    public String Database_name = "QuanLyThuChiDb.sqlite";
    public static final String DB_path = "/databases/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        database = openOrCreateDatabase(Database_name, MODE_PRIVATE, null);
        ChangeLanguage();
        setContentView(R.layout.activity_add);

        setID();
        setFont();


        setEvent();
    }

    private void ChangeLanguage() {
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
    }


    private void setFont() {
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/ft.ttf");
        tvTitle.setTypeface(typeface);
        tvName.setTypeface(typeface);
        tvMoney.setTypeface(typeface);
        edtMoney.setTypeface(typeface);
        edtName.setTypeface(typeface);
        btnCancel.setTypeface(typeface);
        btnSave.setTypeface(typeface);
    }

    private void setEvent() {
        CancelValue();

        //ReadDatabase();
       // ShowSpinner();
        GetValueFromScreen();
        SaveValue();

    }

    private void ShowSpinner() {
        adapter = new Wallet_type_adapter(Add.this, R.layout.sp_wallet_type, arrType);
        adapter.setDropDownViewResource( R.layout.sp_wallet_type);
        spType.setAdapter(adapter);
    }

    private void CancelValue() {
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Add.this, wallet.class);
                startActivity(intent);
            }
        });

    }

    private void SaveValue() {
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Save();
                    Toast.makeText(Add.this, "Đã thêm 1 ví tiền ", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(Add.this, wallet.class);
                    startActivity(intent);
                } catch (Exception e) {
                    Toast.makeText(Add.this, "Vui lòng nhập đầy đủ thông tin ", Toast.LENGTH_LONG).show();

                }
            }
        });
    }

    private void Save() {
       if(validate()){
            AddToDatabase();
       }else
       {
           Toast.makeText(Add.this, "Vui lòng nhập đầy đủ thông tin ", Toast.LENGTH_LONG).show();
       }

    }

    private void AddToDatabase() {
        ContentValues values = new ContentValues();
        values.put("ID_NameList", id);
        values.put("Name", edtName.getText().toString());
        values.put("Money", Integer.parseInt(edtMoney.getText().toString()));
        long r = database.insert("NameListTb", null,values);
        //Toast.makeText(Add.this, "r = " +Integer.parseInt(edtMoney.getText().toString()) + "id= " +id,Toast.LENGTH_SHORT).show();
    }

    private boolean validate() {
        if (Name == null && Money == 0){
            return  false;
        }
        return true;
    }


    private void GetValueFromScreen() {
        if (edtName.getText() != null)
        Name = edtName.getText().toString();
       // if (edtMoney.getText() != null)
        //Money = Integer.parseInt(edtMoney.getText().toString());
        Cursor cursor = database.query("NameListTb", null,null,null,null,null,null);
        while (cursor.moveToNext())
        {
            id  = cursor.getInt(0);
        }
        id ++;
        cursor.close();
    }

    private void ReadDatabase() {
        Cursor cursor = database.query("TypeTb", null, null, null, null,null,null);
       // arrType.clear();

        while (cursor.moveToNext())
        {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            int icon = cursor.getInt(2);
            Toast.makeText(Add.this, id + " " + name + " " + icon, Toast.LENGTH_LONG).show();
        //    arrType.add(new Wallet_type(icon,name));
        }
        cursor.close();
    }

    private void OpenDatabase() {
        database = openOrCreateDatabase(Database_name, MODE_PRIVATE, null);

    }

    private void setID() {
        edtMoney = (EditText) findViewById(R.id.edtAddSoTien);
        edtName = (EditText) findViewById(R.id.edtAddTenViTien);
       // spType = (Spinner) findViewById(R.id.spnAddBieuTuong);
        btnCancel = (Button) findViewById(R.id.btnAddCancel);
        btnSave = (Button) findViewById(R.id.btnAddSave);
        arrType = new ArrayList<Wallet_type>();
        tvMoney = (TextView) findViewById(R.id.tvAddSoTien);
        tvName = (TextView) findViewById(R.id.tvAddTenViTien);
        tvTitle = (TextView) findViewById(R.id.tvTitleViTien);
    }
}
