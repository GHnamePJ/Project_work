package com.example.ex10multimedia01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText editText;
    private Myre myre;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText= this.findViewById(R.id.Et1);
        Button button=this.findViewById(R.id.b1);
        button.setOnClickListener(this);

        myre=new Myre();

    }
    @Override
    public void onClick(View view) {
        String msg=editText.getText().toString();

        Intent intent= new Intent("pj");
        intent.putExtra("msg",msg);
        this.sendBroadcast(intent);
    }
    @Override
    protected void onResume() {
        super.onResume();

        IntentFilter filter=new IntentFilter();
        filter.addAction("pj");
        registerReceiver(myre,filter);
    }
    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(myre);
    }
}
