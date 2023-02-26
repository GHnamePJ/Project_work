package com.example.ex08;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public static int a=0;
    public Button b1;
    private TextView Tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b1=this.findViewById(R.id.b1);
        b1.setOnClickListener(this);

        Tv=this.findViewById(R.id.Tv);
    }

    @Override
    public void onClick(View view) {
        Intent intent=new Intent(this,MainActivity2.class);
//        this.startActivity(intent);
        this.startActivityForResult(intent,MainActivity.a);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if((requestCode==MainActivity.a)&&(resultCode==Activity.RESULT_OK)){
            int num=data.getIntExtra("result",-1);
            Tv.setText(num+"");
        }
    }
}
