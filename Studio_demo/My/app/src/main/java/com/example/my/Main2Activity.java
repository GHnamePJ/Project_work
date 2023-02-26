package com.example.my;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class Main2Activity extends AppCompatActivity {
    private static String log="操作日志";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Log.i(log,"1.oncreate!!!");
    }
    protected void onStart(){
        super.onStart();
        Log.i(log,"2.onStart!!!");
    }
    protected void onResume(){
        super.onResume();
        Log.i(log,"3.onResume!!!");
    }
    protected void onPause(){
        super.onPause();
        Log.i(log,"4.onPause!!!");
    }
    protected  void onStop(){
        super.onStop();
        Log.i(log,"5.onStop!!!");
    }
    protected void onRestart(){
        super.onRestart();
        Log.i(log,"6.onRestart!!!");
    }
    protected void onDestroy(){
        super.onDestroy();
        Log.i(log,"7.onDestroy!!!");
    }
}
