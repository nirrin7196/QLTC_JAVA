package com.example.thuongdh.qltc;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.content.Intent;

public class HelpActivity extends AppCompatActivity {

    TextView tvTile, tvDetail;
    ImageButton imbtn;
    String lang, text;
    SQLiteDatabase database;
    String db = "QuanLyThuChiDb.sqlite";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        setID();
        database = openOrCreateDatabase(db, MODE_PRIVATE,null);
        setFont();
        setEvent();
        getLanguage();
        setResource();
    }

    private void getLanguage() {
        Cursor c = database.query("SettingTb",null,null,null,null,null,null);
        if (c != null) {
            while (c.moveToNext()) {
                lang = c.getString(1);
            }
        }
    }

    private void setResource() {
        if (lang.trim().equals("English"))
        {
            text = "In MainMenu\n";
            text += "Click Wallet to see and manage your Wallet\n";
            text += "Click Add to add a new Money\n";
            text += "Click Spending to add new Spending\n";
            text += "Click Look up to Look up\n";
            text += "Click Setting to go to Your setting.\n";
            text +=  "Click Help to get Help.\n";
            text += "Click Infomation to get some our info.\n";
            text += "Click Feedback to give us your feedback.\n";
            text += "Thanks for your using my app.";

        }
        else {
            text = "Ở màn hình chính\n";
            text += "Chọn Ví tiền để xem và quản lý các ví tiền của bạn \n";
            text += "Chọn Thêm mới để tạo mới 1 khoảng thu.\n";
            text += "Chọn Chi để tạo mới 1 khoảng chi.\n";
            text += "Chọn Tra cứu để thực hiện tra cứu.\n";
            text += "Chọn Cài đặt để cài đặt.\n";
            text +=  "Chọn Giúp đở nếu bạn cần sự giúp đỡ.\n";
            text += "Chọn Thông tin để xem thông tin của chúng tôi.\n";
            text += "Chọn Phản hồi để gửi phản hồi cho chúng tôi.\n";
            text += "Cảm ơn bạn đã chọn ứng dụng của chúng tôi.";
        }
        tvDetail.setText(text);
    }

    private void setEvent() {
        imbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HelpActivity.this, StartActivity.class);
                startActivity(intent);
            }
        });
    }

    private void setFont() {
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/ft.ttf");
        tvTile.setTypeface(typeface);
        tvDetail.setTypeface(typeface);
        imbtn.setImageResource(R.drawable.back);
    }

    private void setID() {
        imbtn = (ImageButton) findViewById(R.id.imbtnHelpBack);
        tvDetail = (TextView) findViewById(R.id.tvHelpDetail);
        tvTile = (TextView) findViewById(R.id.tvHelpTitle);
    }
}
