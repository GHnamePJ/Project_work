package com.example.ex10multimedia01;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class Myre extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        String msg=intent.getStringExtra("msg");
        Toast.makeText(context,msg,Toast.LENGTH_LONG).show();
    }
}
