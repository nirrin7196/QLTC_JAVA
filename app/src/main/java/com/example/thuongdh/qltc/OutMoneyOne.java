package com.example.thuongdh.qltc;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class OutMoneyOne extends AppCompatActivity {
    ImageButton imbtnback;
    TextView tvname;
    EditText edtMoney, edtDate;
    DatePicker datePicker;
    Button btnAdd, btnCancel;
    String name;
    Spinner spinner;
    int k;
    public String Database_name = "QuanLyThuChiDb.sqlite";
    ArrayList<String> arrayListName;
    ArrayAdapter<String> adapterName;
    SQLiteDatabase database;
    String reportDate;
    int index;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_out_money_one);
        Bundle bundle = getIntent().getExtras();
        k = bundle.getInt("Key");
        setID();
        SetName();
        setSpinner();
        setEventButton();
    }

    private void setEventButton() {
        btnCancel.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OutMoneyOne.this, out_money.class);
                startActivity(intent);
            }
        });

        btnAdd.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edtMoney.getText().toString().equals(""))
                {
                    Toast.makeText(OutMoneyOne.this, "Vui lòng nhập số tiền ", Toast.LENGTH_SHORT).show();
                }
                else {
                    UpdateNameListTb(); //tru tien trong vi
                    UpdateMemoryActionTb(); //bang thu chi
                }
            }
        });
    }

    private void UpdateNameListTb() {

            int moneyTru = Integer.parseInt(edtMoney.getText().toString());
            String spv = spinner.getSelectedItem().toString();
            Cursor cursor = database.rawQuery("select * from NameListTb where TRIM(Name) = '" + spv.trim() + "'", null);
            cursor.moveToFirst();
            int old = cursor.getInt(2);
            int money = old - moneyTru;
            ContentValues values = new ContentValues();
            values.put("Money", money);

            database.update("NameListTb", values, "Name=?", new String[]{spv});

    }

    private void UpdateMemoryActionTb() {
        Cursor c = database.query("MemoryActionTb", null,null,null,null,null,null);

        while (c.moveToNext())
        {
            index = c.getInt(0);
        }
        c.close();
        try {
            SaveData();
            Toast.makeText(OutMoneyOne.this, "Đã cập nhật  ", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(OutMoneyOne.this, out_money.class);
            startActivity(intent);
        }
        catch (Exception e) {

        }
    }

    private void SaveData() {
        int money = 0;
        if (edtMoney.getText().equals(null))
        {
            Toast.makeText(OutMoneyOne.this, "Vui lòng nhập số tiền ", Toast.LENGTH_SHORT).show();
        }
        else {
            ContentValues values = new ContentValues();
            money = Integer.parseInt(edtMoney.getText().toString());
            values.put("ID", index);
            values.put("Name", name);
            values.put("Money", money);
            values.put("DateUse",edtDate.getText().toString());
            values.put("DateWrite", reportDate);
            database.insert("MemoryActionTb", null, values);
        }
    }

    private void setSpinner() {
        readDataBase();
    }

    private void readDataBase() {
        arrayListName = new ArrayList<>();
        database = openOrCreateDatabase(Database_name, MODE_PRIVATE, null);
        Cursor cursor =  database.query("NameListTb", null, null, null, null, null,null);
        while (cursor.moveToNext())
        {
            String name = cursor.getString(1);
            arrayListName.add(name);
        }
        cursor.close();

        adapterName = new ArrayAdapter<String>(
                OutMoneyOne.this,
                android.R.layout.simple_spinner_dropdown_item,
                arrayListName);
        adapterName.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapterName);
    }

    private void SetName() {
        if (k ==0)
        {
            name = "Ăn uống";
        } else if (k == 1)
            {
                name = "Điện nước";
            }else if (k ==2)
            {
                name = "Thuê nhà";
            } else  if (k ==3)
            {
                name = "Mạng";
            } else if (k == 4)
            {
                name = "Truyền hình cáp";
            }
        tvname.setText(name);
    }

    private void setID() {
        imbtnback = (ImageButton) findViewById(R.id.imbtnBackOutOne);
        tvname = (TextView) findViewById(R.id.tvNameOutOne);
        edtMoney = (EditText) findViewById(R.id.edtSoTienOutOne);
      //  datePicker = (DatePicker) findViewById(R.id.dateNgayOutOne);
        btnAdd = (Button) findViewById(R.id.btnAddOutOne);
        btnCancel = (Button) findViewById(R.id.btnCancelOutOne);
        spinner = (Spinner) findViewById(R.id.spViTienOutOne);
        edtDate = (EditText) findViewById(R.id.edtDateOutMoney);
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        Date today = Calendar.getInstance().getTime();
        reportDate = df.format(today);
        edtDate.setText(reportDate);
    }
}
