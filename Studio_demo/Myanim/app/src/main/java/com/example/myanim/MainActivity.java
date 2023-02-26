package com.example.myanim;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
        ImageView imageView;
        Animation animation;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button b1=findViewById(R.id.b1);
        b1.setOnClickListener(this);
        Button b2=findViewById(R.id.b2);
        b2.setOnClickListener(this);
        Button b3=findViewById(R.id.b3);
        b3.setOnClickListener(this);
        Button b4=findViewById(R.id.b4);
        b4.setOnClickListener(this);
        Button b5=findViewById(R.id.b5);
        b5.setOnClickListener(this);
        imageView=findViewById(R.id.iv);
    }
    @Override
    public void onClick(View view) {
        int i=view.getId();
        switch (i){
            case R.id.b1:
                animation= AnimationUtils.loadAnimation(this,R.anim.alpha);
                imageView.startAnimation(animation);
                break;
            case R.id.b2:
                animation= AnimationUtils.loadAnimation(this,R.anim.rotate);
                imageView.startAnimation(animation);
                break;
            case R.id.b3:
                animation= AnimationUtils.loadAnimation(this,R.anim.scale);
                imageView.startAnimation(animation);
                break;
            case R.id.b4:
                animation= AnimationUtils.loadAnimation(this,R.anim.translate);
                imageView.startAnimation(animation);
                break;
            case R.id.b5:
                animation= AnimationUtils.loadAnimation(this,R.anim.translate);
                imageView.clearAnimation();
                break;
        }
    }
}
