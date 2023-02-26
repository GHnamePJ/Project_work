package com.example.myanimation;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button b1=findViewById(R.id.b1);
        b1.setOnClickListener(this);

        Button b2=findViewById(R.id.b2);
        b2.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
    int i=view.getId();
    if(i==R.id.b1){
        ImageView imageView=findViewById(R.id.iv);
        AnimationDrawable animationDrawable= (AnimationDrawable) imageView.getDrawable();
        animationDrawable.start();
    }
    else{
        ImageView imageView=findViewById(R.id.iv);
        AnimationDrawable animationDrawable= (AnimationDrawable) imageView.getDrawable();
        animationDrawable.stop();
    }
    }
}
