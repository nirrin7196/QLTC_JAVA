package com.example.thuongdh.qltc;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;

import java.util.ArrayList;
import java.util.Calendar;

public class ChartActivity extends AppCompatActivity {

    TextView tv1, tv2, tv3, tv4, tv5;
    ImageButton btnback;
    Spinner spinner;
    EditText edtFrom, edtTo;
    PieChart chart;
    SQLiteDatabase database;
    String db = "QuanLyThuChiDb.sqlite";
    String defaultTime, defaultWallet;
    ArrayList<String> valuesString;
    ArrayList<Float> values;
    int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);

        setId();
        setFont();
        getData();
        setDefaultChart();
        setEvent();

    }

    private void setDefaultChart() {
        chart.setRotationEnabled(false);
        chart.setUsePercentValues(true);
        chart.setHoleRadius(25f);
        chart.setTransparentCircleAlpha(0);
        addDataSet(chart);
    }

    private void addDataSet(PieChart chart) {

        ArrayList<PieEntry> yvalues = new ArrayList<PieEntry>();

        for (int i = 0; i < values.size(); i++)
            yvalues.add(new PieEntry(values.get(i), valuesString.get(i)));
        PieDataSet dataSet = new PieDataSet(yvalues, "");
        PieData data = new PieData(dataSet);
        data.setValueFormatter(new PercentFormatter());
        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(Color.GRAY);
        colors.add(Color.BLUE);
        colors.add(Color.RED);
        colors.add(Color.GREEN);
        colors.add(Color.CYAN);
        colors.add(Color.YELLOW);
        colors.add(Color.MAGENTA);
        dataSet.setColors(colors);

        Legend legend = chart.getLegend();
        legend.setForm(Legend.LegendForm.CIRCLE);
        legend.setPosition(Legend.LegendPosition.LEFT_OF_CHART);
        chart.setData(data);
    }

    private void setEvent() {
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChartActivity.this, StartActivity.class);
                startActivity(intent);
            }
        });
    }

    private void getData() {
        database = openOrCreateDatabase(db, MODE_PRIVATE, null);
        getSetting();
        setSpinner();
        getDatadefault();
    }

    private void getDatadefault() {
        valuesString = new ArrayList<String>();
        values = new ArrayList<Float>();
        String sql = "select sum(Money) , Name from MemoryActionTb ";
        Calendar c = Calendar.getInstance();

        switch (defaultTime) {
            case "All": {
                sql += "group by Name";
                break;
            }
            case "Month": {
                sql += " where strftime('%m', DateUse) = ";
                sql += (c.MONTH);
                sql += " group by Name";

                break;
            }
            case "Year": {
                sql += " where strftime('%Y', DateUse) = '";
                sql += (c.get(Calendar.YEAR));
                sql += "' group by Name";
                break;
            }
            default: { //quarter
                switch (c.MONTH % 3) {
                    case 0: {
                        sql += "where strftime('%m', DateUse) <= ";
                        sql += String.valueOf(c.MONTH);
                        sql += " and strftime('%m', DateUse) >= ";
                        sql += String.valueOf(c.MONTH - 2);
                        sql += " group by Name";
                        break;
                    }
                    case 2: {
                        sql += "where strftime('%m', DateUse) <= ";
                        sql += String.valueOf(c.MONTH);
                        sql += " and strftime('%m', DateUse) >= ";
                        sql += String.valueOf(c.MONTH - 1);
                        sql += " group by Name";
                        break;
                    }
                    default: {
                        sql += " where strftime('%m', DateUse) = ";
                        sql += String.valueOf(c.MONTH);
                        sql += " group by Name";
                        break;
                    }
                }
                break;
            }
        }

      //  Cursor c1 = database.rawQuery("select * from MemoryActionTb where strftime('%Y', dateuse) = '2017'", null);
       // c1.moveToFirst();
       //   Toast.makeText(ChartActivity.this, c1.getInt(0) + ". " + c1.getString(1) + "1 " + c1.getInt(2) + ". " + c1.getString(3),Toast.LENGTH_LONG).show();

        Cursor cursor = database.rawQuery(sql, null);
        while (cursor.moveToNext()) {
            values.add(cursor.getFloat(0));
            valuesString.add(cursor.getString(1));
        }
        cursor.close();
    }

    private void setSpinner() {
//        ArrayList<String> arrayList = new ArrayList<String>();
//        arrayList.add(defaultWallet.trim());
//        Cursor cursor = database.query("NameListTb", null, null, null, null, null, null);
//        while (cursor.moveToNext()) {
//            if (!cursor.getString(1).toString().trim().equals(defaultWallet.trim())) {
//                arrayList.add(cursor.getString(1).trim());
//            } else arrayList.add("All");
//        }
//        cursor.close();
//        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(ChartActivity.this,
//                android.R.layout.simple_spinner_dropdown_item,
//                arrayList);
//        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinner.setAdapter(arrayAdapter);
    }

    private void getSetting() {
        defaultTime = "All";
        defaultWallet = "All";
        Cursor cursor = database.query("SettingTb", null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            defaultWallet = cursor.getString(4);
            defaultTime = cursor.getString(5);
        }
        cursor.close();
    }

    private void setFont() {
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/ft.ttf");
    //    tv5.setTypeface(typeface);
     //   tv2.setTypeface(typeface);
    //    tv1.setTypeface(typeface);
        tv3.setTypeface(typeface);
    //    tv4.setTypeface(typeface);
   //     edtTo.setTypeface(typeface);
    //    edtFrom.setTypeface(typeface);
        btnback.setImageResource(R.drawable.bk);
    }

    private void setId() {
       // tv1 = findViewById(R.id.tvChartConditon);
      //  tv2 = findViewById(R.id.tvChartTime);
        tv3 = findViewById(R.id.tvChartTitle);
      //  tv4 = findViewById(R.id.tvChartTo);
      //  tv5 = findViewById(R.id.tvChartType);
        btnback = findViewById(R.id.btnChartBack);
      //  spinner = findViewById(R.id.spChart);
      //  edtFrom = findViewById(R.id.edtChartFrom);
      //  edtTo = findViewById(R.id.edtChartTo);
        chart = findViewById(R.id.idChart);
    }
}
