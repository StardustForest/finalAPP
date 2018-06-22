package com.example.administrator.test;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FDActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fd);
        Button fdtj=(Button)findViewById(R.id.fdtj);//从 xml中 建立button
        fdtj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {//建立 on click 事件

                String abc="sdsasdsa";
                Intent intent_mainFDTJ=new Intent(FDActivity.this,FDTJActivity.class);//显式Intent main to two ，用于activity跳转
                intent_mainFDTJ.putExtra("abc_from",abc);//发送数据到 Twoactivity
                startActivity(intent_mainFDTJ);//启动intent

            }
        });
    }
}
