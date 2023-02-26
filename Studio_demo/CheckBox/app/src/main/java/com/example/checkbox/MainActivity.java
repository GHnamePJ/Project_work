package com.example.checkbox;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private CheckBox CB1,CB2,CB3;
    private TextView TV1;
    private ArrayList list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CB1=this.findViewById(R.id.CB1);
        CB1.setOnClickListener(this);

        CB2=this.findViewById(R.id.CB2);
        CB2.setOnClickListener(this);

        CB3=this.findViewById(R.id.CB3);
        CB3.setOnClickListener(this);

        TV1=findViewById(R.id.TV1);
        list = new ArrayList();
    }

    @Override
    public void onClick(View view) {
        String s="";
        if(CB1.isChecked()==true){
            list.add(CB1.getText().toString());
            System.out.println(list.get(0));
        }
        else if(CB2.isChecked()==true){
            list.add(CB2.getText().toString());
        }
        else if(CB3.isChecked()==true){
            list.add(CB3.getText().toString());
        }else {
            if(CB1.isChecked()==false){
                list.remove(list.indexOf(CB1.getText().toString()));
            }
            else if(CB2.isChecked()==false){
                list.remove(list.indexOf(CB2.getText().toString()));
            }
            else{
                list.remove(list.indexOf(CB3.getText().toString()));
            }
        }
        for(int i=0;i<list.size();i++){
            s = s + list.get(i);
        }
        System.out.println(s);
        TV1.append(s);

    }
}
