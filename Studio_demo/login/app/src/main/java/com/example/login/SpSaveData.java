package com.example.login;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;
import java.util.Map;

public class SpSaveData {
    public static boolean Writedata(Context context, String username, String password){
        SharedPreferences sp = context.getSharedPreferences("data",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("username",username);
        editor.putString("password",password);
        editor.commit();
        return true;
    }
    public static Map<String,String> Readdata(Context context) {
        SharedPreferences sp = context.getSharedPreferences("data", Context.MODE_PRIVATE);
        String username = sp.getString("username",null);
        String password = sp.getString("password",null);
        Map<String,String> user = new HashMap<String,String>();
        user.put("username",username);
        user.put("password",password);
        return user;
    }

}