package com.example.thuongdh.qltc;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class addNewAction extends AppCompatActivity {
    TextView tv1, tv2;
    Button btnSave, btnCancel;
    EditText edtName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_action);
        setId();
        setfont();
        setEventSave();
        setEventCancel();
    }

    private void setEventCancel() {
        btnCancel.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent t = new Intent(addNewAction.this, out_money.class);
                startActivity(t);
            }
        });
    }

    private void setEventSave() {
        btnSave.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edtName.getText() == null)
                {
                    Toast.makeText(addNewAction.this, getText(R.string.Please_completed_information).toString(), Toast.LENGTH_SHORT).show();
                }
                else
                {
                    SQLiteDatabase database;
                    int id =0;
                    String DB_name = "QuanLyThuChiDb.sqlite";
                    database = openOrCreateDatabase(DB_name, MODE_PRIVATE, null);
                    ContentValues v = new ContentValues();
                    Cursor c = database.query("ActionListTb",null,null,null,null,null,null);
                    while (c.moveToNext()){
                        id = c.getInt(0);
                    }
                    id++;
                    v.put("ID_action", id);
                    v.put("Name", edtName.getText().toString());
                    database.insert("ActionListTb", null,v);
                    Intent i = new Intent(addNewAction.this, out_money.class);
                    startActivity(i);
                }
            }
        });
    }

    private void setfont() {
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/ft.ttf");
        tv1.setTypeface(typeface);
        tv2.setTypeface(typeface);
        btnSave.setTypeface(typeface);
        btnCancel.setTypeface(typeface);
        edtName.setTypeface(typeface);
    }

    private void setId() {
        tv1 = (TextView) findViewById(R.id.frtvNewSpending);
        tv2 = (TextView) findViewById(R.id.frtvName);
        btnCancel = (Button) findViewById(R.id.frbtnCancel);
        btnSave = (Button) findViewById(R.id.frbtnAdd);
        edtName = (EditText) findViewById(R.id.fredtName);
    }
}
