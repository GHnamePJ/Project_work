package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView lv1=this.findViewById(R.id.lv1);
        myAdapter mad=new myAdapter(this,R.layout.item);
        lv1.setAdapter(mad);
        lv1.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        TextView textView=view.findViewById(R.id.book_name);
        String name= (String) textView.getText();
        Toast.makeText(this,name,Toast.LENGTH_LONG).show();
    }
}
