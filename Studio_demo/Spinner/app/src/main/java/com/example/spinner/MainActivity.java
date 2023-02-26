package com.example.spinner;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
        TextView select;
//        声明一个ArrayAdapter
        ArrayAdapter<CharSequence> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        select=(TextView)this.findViewById(R.id.select);
        Spinner spinner=(Spinner)this.findViewById(R.id.spinner);
//
        adapter=new MyArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,
                this.getResources().getTextArray(R.array.habit));
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        select.setText(adapter.getItem(position));
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }


}
