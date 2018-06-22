package com.example.administrator.test;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import android.view.Menu;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;




public class XWActivity extends AppCompatActivity {
    String tagg="main activity";
    private String file_name="data";//"/data/data/data-storage-master/files/public.txt";


    BluetoothAdapter bAdapter;//声明蓝牙适配器
    ArrayList BleName = new ArrayList();//声明用户输入的蓝牙设备名称变量
    TextView showRssi;//声明textview用于显示信号强度信息

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xw);
        bAdapter = BluetoothAdapter.getDefaultAdapter();//获取蓝牙适配器
        //设置过滤器，过滤因远程蓝牙设备被找到而发送的广播 BluetoothDevice.ACTION_FOUND
        IntentFilter iFilter = new IntentFilter();
        iFilter.addAction(BluetoothDevice.ACTION_FOUND);
        //设置广播接收器和安装过滤器
        registerReceiver(new foundReceiver(), iFilter);
        //获取控件对象
        showRssi = (TextView) findViewById(R.id.showRssi);

        Button xwtj=(Button)findViewById(R.id.xwtj);//从 xml中 建立button
        xwtj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {//建立 on click 事件

                String abc="sdsasdsa";
                Intent intent_mainXWTJ=new Intent(XWActivity.this,XWTJActivity.class);//显式Intent main to two ，用于activity跳转
                intent_mainXWTJ.putExtra("abc_from",abc);//发送数据到 Twoactivity
                startActivity(intent_mainXWTJ);//启动intent


            }
        });



    }
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    /**
     * 打开蓝牙
     *
     * @param v
     */
    public void open(View v) {
        if (!bAdapter.isEnabled()) {
            bAdapter.enable();
            Toast.makeText(getApplicationContext(), "蓝牙打开成功", 0).show();
        } else {
            Toast.makeText(getApplicationContext(), "蓝牙已经打开", 0).show();
        }
    }



    /**
     * 搜索远程蓝牙设备，获取editview的值
     *
     * @param v
     */
    public void show(View v) {
        if (bAdapter.isEnabled()) {
            bAdapter.startDiscovery();
        } else {
            Toast.makeText(getApplicationContext(), "蓝牙未打开", 0).show();
            ;
        }
    }

    /**
     * 内部类：当找到一个远程蓝牙设备时执行的广播接收者
     *
     * @author Administrator
     */
    class foundReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            // TODO Auto-generated method stub
            Integer flag = 0;
            BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);//获取此时找到的远程设备对象
            for (int i = 1; i <= BleName.size(); i = i + 2) {
                BleName.set(i, 1000);
            }

            for (int i = 0; i < BleName.size(); i = i + 2) {
                if (BleName.get(i).toString().equals(device.getName())) {//判断远程设备是否与用户目标设备相同
                    short rssi = intent.getExtras().getShort(BluetoothDevice.EXTRA_RSSI);//获取额外rssi值
                    BleName.set(i+1, rssi);//显示rssi到控件上
                    flag = 1;
                    //break;
                }
            }
            if(flag == 1) {
                flag = 0;
            } else {
                BleName.add(device.getName());
                BleName.add(intent.getExtras().getShort(BluetoothDevice.EXTRA_RSSI));
            }
            bAdapter.cancelDiscovery();//关闭搜索
            showRssi.setText("");
            for (int i = 0; i < BleName.size(); i = i + 2) {
                showRssi.setText(showRssi.getText().toString() + BleName.get(i).toString() + ": " + BleName.get(i+1).toString() + "\n");
            }
        }
    }
    public void onLoadFileClick(View view) {
        Toast.makeText(this, "文件已加载", Toast.LENGTH_SHORT).show();
        FileInputStream in_file=null;
        BufferedReader file_reader=null;
        StringBuilder content =new StringBuilder();
        try{
            in_file=openFileInput(file_name);
            file_reader=new BufferedReader(new InputStreamReader(in_file));
            String line="";
            while ((line=file_reader.readLine())!=null){
                content.append(line);
            }
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if(file_reader!=null){
                try {
                    file_reader.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }

        String outputstring=content.toString();
        if(!TextUtils.isEmpty(outputstring)){
            showRssi.setText(outputstring);
           // showRssi.setSelection(outputstring.length());
        }

    }
}

