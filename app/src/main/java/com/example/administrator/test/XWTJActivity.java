package com.example.administrator.test;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class XWTJActivity extends AppCompatActivity {
    String tagg="main activity";
    private EditText inputText;
    private Button loadFileButton;
    private Button saveFileButton;
    private String file_name="data";//"/data/data/data-storage-master/files/public.txt";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xwtj);
        Button xwqr=(Button)findViewById(R.id.xwqr);//从 xml中 建立button
        inputText = (EditText) findViewById(R.id.xwsr);
      /*  xwqr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {//建立 on click 事件

             /*   EditText edit = (EditText)findViewById(R.id.xwsr);
                String str = edit.getText().toString();
                */
            //    Intent intent_mainXWQR=new Intent(XWTJActivity.this,XWActivity.class);//显式Intent main to two ，用于activity跳转
                /*intent_mainXWQR.putExtra("abc_from",str);//发送数据到 Twoactivity
                startActivity(intent_mainXWQR);//启动intent

            }
        }); */
    }
    public void onSaveFileClick(View view) {
        Toast.makeText(this, "物品名称已保存", Toast.LENGTH_SHORT).show();
        String inputstring=inputText.getText().toString();
        Intent intent_mainXWQR=new Intent(XWTJActivity.this,XWActivity.class);
        FileOutputStream file_out=null;
        BufferedWriter file_writer=null;
        try{
            file_out=openFileOutput(file_name, Context.MODE_PRIVATE);
            file_writer=new BufferedWriter(new OutputStreamWriter(file_out));
            file_writer.write(inputstring);
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            try{
                if (file_writer!=null){
                    file_writer.close();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        startActivity(intent_mainXWQR);//启动intent
    }
}
