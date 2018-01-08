package com.example.thuongdh.qltc;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by thuongdh on 16/10/2017.
 */


public class fragment extends Fragment {
    private View myv;
    Button btnThu, btnTraCuu, btnCaiDat, btnViTien, btnGiupDo, btnThongTin, btnPhanHoi, btnChi;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        myv = inflater.inflate(R.layout.fragment_menu, container,false);
        btnThongTin = myv.findViewById(R.id.frbtnThongTin);
        btnCaiDat = myv.findViewById(R.id.frbtnCaiDat);
        btnGiupDo = myv.findViewById(R.id.frbtnGiupDo);
       // btnPhanHoi = myv.findViewById(R.id.frbtnPhanHoi);
        btnTraCuu = myv.findViewById(R.id.frbtnTraCuu);
        btnThu = myv.findViewById(R.id.frbtnThu);
        btnViTien = myv.findViewById(R.id.frbtnViTien);
        btnChi = myv.findViewById(R.id.frbtnChi);
        //setFont();
        setEvent();
        return myv;
    }

    /*private void setFont() {
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/ft.ttf");
        btnCaiDat.setTypeface(typeface);
        btnChi.setTypeface(typeface);
        btnGiupDo.setTypeface(typeface);
        btnPhanHoi.setTypeface(typeface);
        btnThongTin.setTypeface(typeface);
        btnThu.setTypeface(typeface);
        btnViTien.setTypeface(typeface);
        btnTraCuu.setTypeface(typeface);
    }*/
    private void setEvent() {
        btnCaiDat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), SettingActivity.class);
                startActivity(intent);
            }
        });
        btnViTien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), wallet.class);
                startActivity(intent);
            }
        });
        btnThu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), InMoney.class);
                startActivity(intent);
            }
        });
        btnChi.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), out_money.class);
                startActivity(intent);
            }
        });
        btnGiupDo.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), HelpActivity.class);
                startActivity(intent);
            }
        });
        btnThongTin.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), InformationActivity.class);
                startActivity(intent);
            }
        });
        btnTraCuu.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ChartActivity.class);
                startActivity(intent);
            }
        });
    }


}
