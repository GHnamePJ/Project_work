package com.example.ex07intent01;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;

public class Activity2 extends Activity {
    @Override
    public void onCreate(@NonNull Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
