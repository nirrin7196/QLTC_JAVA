package com.example.thuongdh.qltc;

import com.example.thuongdh.adapter.Shopping_adapter;
import com.example.thuongdh.model.Wallet_name_list;

import java.util.ArrayList;

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
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Shopping extends AppCompatActivity {
    ImageButton btnback, btnAddItem;
    TextView textView;
    EditText editTextName, editTextMoney;
    Spinner spinner;
    ListView lv;
    Button btnAdd, btnDel;
    ArrayList<String> arrayListName, saveValues;
    ArrayAdapter<String> adapterName;
    SQLiteDatabase database;
    Shopping_adapter adapterlist;
    public String Database_name = "QuanLyThuChiDb.sqlite";
    int count = 0;
    int index;
    int k;
    ArrayList<Wallet_name_list> arrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping);
        Bundle bundle = getIntent().getExtras();
        k = bundle.getInt("Key");
        setID();
        setSpinner();
        setEventButton();
    }

    private void setEventButton() {
        btnback.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Shopping.this, out_money.class);
                startActivity(intent);
            }
        });
        btnAddItem.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                SetItemToListView();
            }
        });
        btnAdd.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                SaveAllData();
            }
        });
        btnDel.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                DeleteItemInListView();
            }
        });
    }

    private void DeleteItemInListView() {
        arrayList = new ArrayList<Wallet_name_list>();
        adapterlist = new Shopping_adapter(Shopping.this,
                R.layout.item_lv_in_many_money,
                arrayList);
        lv.setAdapter(adapterlist);
        count = 0;
    }

    private void SaveAllData() {
        ContentValues value = new ContentValues();
        if (count > 0 && editTextMoney.getText().toString() != null && editTextName.getText().toString()!= null) {
            for (int i = 0; i < count; i++) {
                String s = saveValues.get(i);
                int k = s.indexOf(';');
                String name = s.substring(0, k);
                String m = s.substring(k + 1);
                int money = Integer.parseInt(m);
                String vi = spinner.getSelectedItem().toString();
                Cursor cursor = database.rawQuery("select * from NameListTb where TRIM(Name) = '" + vi.trim() + "'", null);
                cursor.moveToFirst();
                int old = cursor.getInt(2);
                money = old - money;
                value.put("Money", money);
                database.update("NameListTb", value, "Name=?", new String[]{name});
                Cursor c = database.query("MemoryActionTb", null, null, null, null, null, null);

                while (c.moveToNext()) {
                    index = c.getInt(0);
                }
                c.close();
                money = Integer.parseInt(editTextMoney.getText().toString());
                ContentValues values = new ContentValues();
                values.put("ID", index);
                values.put("Name", name);
                values.put("Money", money);
                database.insert("MemoryActionTb", null, values);
                Toast.makeText(Shopping.this, "Đã cập nhật", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Shopping.this, out_money.class);
                startActivity(intent);
            }
        }
            else if (editTextMoney.getText().toString().equals(""))
        {
            Toast.makeText(Shopping.this, "Vui lòng nhập số tiền ", Toast.LENGTH_LONG).show();
        }
        else if (editTextName.getText().toString().equals(""))
        {
            Toast.makeText(Shopping.this, "Vui lòng nhập tên  ", Toast.LENGTH_LONG).show();
        }

    }

    private void SetItemToListView() {
        String name = (String) spinner.getSelectedItem();
        String dv = "VND";
        try
        {
            int money = Integer.parseInt(editTextMoney.getText().toString());

            arrayList.add(new Wallet_name_list(name, money, dv));
            adapterlist = new Shopping_adapter(Shopping.this,
                    R.layout.item_listview_shopping,
                    arrayList);
            lv.setAdapter(adapterlist);
            saveValues.add(name + ";" + money);
            count++;
        } catch (Exception e) {
            Toast.makeText(Shopping.this, "Vui lòng nhập đầy đủ thông tin ", Toast.LENGTH_LONG).show();
        }
    }

    private void setSpinner() {
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
                Shopping.this,
                android.R.layout.simple_spinner_dropdown_item,
                arrayListName);
        adapterName.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapterName);
    }

    private void setID() {
        btnAdd = (Button) findViewById(R.id.btnAddShopping);
        btnAddItem = (ImageButton) findViewById(R.id.imbtnAddShopping);
        btnback = (ImageButton) findViewById(R.id.btnbackShopping);
        btnDel = (Button) findViewById(R.id.btnDelAllShopping);
        editTextName = (EditText) findViewById(R.id.edtNameShopping);
        editTextMoney = (EditText) findViewById(R.id.edtMoneyShopping);
        spinner = (Spinner) findViewById(R.id.spViTienShopping);
        lv  = (ListView) findViewById(R.id.lvShopping);
        btnAddItem.setImageResource(R.drawable.addname);
        btnback.setImageResource(R.drawable.bk);
        saveValues = new ArrayList<>();
        arrayList = new ArrayList<>();
        textView = (TextView) findViewById(R.id.tvTitle);
        if (k == 5) textView.setText("Mua sắm ");
        if (k == 6) textView.setText("Thêm nhiều khoảng chi ");
    }
}
