package com.example.administrator.test;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button jc=(Button)findViewById(R.id.jc);//从 xml中 建立button
        jc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {//建立 on click 事件

                String abc="sdsasdsa";
                Intent intent_mainJC=new Intent(MainActivity.this,JCActivity.class);//显式Intent main to two ，用于activity跳转
                intent_mainJC.putExtra("abc_from",abc);//发送数据到 Twoactivity
                startActivity(intent_mainJC);//启动intent

            }
        });
        Button xw=(Button)findViewById(R.id.xw);//从 xml中 建立button
        xw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {//建立 on click 事件

                String abc="sdsasdsa";
                Intent intent_mainXW=new Intent(MainActivity.this,XWActivity.class);//显式Intent main to two ，用于activity跳转
                intent_mainXW.putExtra("abc_from",abc);//发送数据到 Twoactivity
                startActivity(intent_mainXW);//启动intent

            }
        });
        Button fd=(Button)findViewById(R.id.fd);//从 xml中 建立button
        fd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {//建立 on click 事件

                String abc="sdsasdsa";
                Intent intent_mainFD=new Intent(MainActivity.this,FDActivity.class);//显式Intent main to two ，用于activity跳转
                intent_mainFD.putExtra("abc_from",abc);//发送数据到 Twoactivity
                startActivity(intent_mainFD);//启动intent

            }
        });
        Button bw=(Button)findViewById(R.id.bw);//从 xml中 建立button
        bw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {//建立 on click 事件

                String abc="sdsasdsa";
                Intent intent_mainFD=new Intent(MainActivity.this,BWActivity.class);//显式Intent main to two ，用于activity跳转
                intent_mainFD.putExtra("abc_from",abc);//发送数据到 Twoactivity
                startActivity(intent_mainFD);//启动intent

            }
        });
    }
}