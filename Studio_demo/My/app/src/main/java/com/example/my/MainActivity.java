package com.example.my;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        System.out.println("1.onCreate!!!");
    }
    protected void onStart(){
        super.onStart();
        System.out.println("2.onStart!!!");
    }
    protected void onResume(){
        super.onResume();
        System.out.println("3.onResume!!!");
    }
    protected void onPause(){
        super.onPause();
        System.out.println("4.onPause!!!");
    }
    protected void onStop(){
        super.onStop();
        System.out.println("5.onStop!!!");
    }
    protected void onRestart(){
        super.onRestart();
        System.out.println("6.onRestart!!!");
    }
    protected void onDestroy(){
        super.onDestroy();
        System.out.println("7.onDestroy!!!");

    }
}
