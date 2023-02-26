package com.example.ex07intent01;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import androidx.annotation.NonNull;

public class Activity1 extends Activity implements View.OnClickListener{
    @Override
    public void onCreate(@NonNull Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);
        Button button=this.findViewById(R.id.button1);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
//        隐式意图进行跳转
        Intent i=new Intent(this,MainActivity.class);
        this.startActivity(i);
    }
}
