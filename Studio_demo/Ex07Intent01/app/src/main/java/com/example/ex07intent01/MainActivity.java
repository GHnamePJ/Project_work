package com.example.ex07intent01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button=this.findViewById(R.id.button);
        button.setOnClickListener(this);
    }
    @Override
    public void onClick(View v){
        Intent intent=new Intent(".A1");
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        this.startActivity(intent);
    }
}
