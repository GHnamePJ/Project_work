package com.example.login;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;

public class Filedata {
    //保存数据（写入数据）
    public static boolean Writedata(Context context,String username,String password){
        try {
           //写入数据 保存在data.txt
            FileOutputStream fos = context.openFileOutput("data.txt",Context.MODE_PRIVATE);
            //写入文件(转化为字节)
            fos.write((username+":"+password).getBytes());
            //关闭
            fos.close();
            return true;
        }
        catch (Exception e){
            e.getStackTrace();
            return false;
        }
    }

    //读取数据
    public static Map<String,String> Readdata(Context context){
        try {
            //在data.txt 中 读取数据
            FileInputStream fis = context.openFileInput("data.txt");
            //将读取的文件保存在字节数组
            byte[] bytes = new byte[fis.available()];
            //将字节数组读入缓冲区 buffer
            fis.read(bytes);
            //读取后解析数据（字节类型转化为字符串）
            String data = new String(bytes);
           //保存解析后数据
            String[] datas = data.split(":");
            Map<String,String> dataMap= new HashMap<String,String>();
            dataMap.put("username",datas[0]);
            dataMap.put("password",datas[1]);
            fis.close();
            return dataMap;
        }
        catch (Exception e){
            e.getStackTrace();
            return null;
        }

    }


}
