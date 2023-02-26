package com.example.ex08;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;

public class MainActivity2 extends Activity implements View.OnClickListener {
    private ImageView Iv1,Iv2;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Iv1=(ImageView)this.findViewById(R.id.Iv1);
        Iv1.setOnClickListener(this);
        Iv2=(ImageView)this.findViewById(R.id.Iv2);
        Iv2.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id =view.getId();
        if(id==R.id.Iv1){
            Intent intent=new Intent();
            intent.putExtra("result",1);
            this.setResult(Activity.RESULT_OK,intent);
        }
        else{
            Intent intent=new Intent();
            intent.putExtra("result",2);
            this.setResult(Activity.RESULT_OK,intent);
        }
        this.finish();
    }

}
