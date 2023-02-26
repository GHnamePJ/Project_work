package com.example.mymeu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import android.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menulist,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int i=item.getItemId();
        switch (i){
            case R.id.p1:
                Toast.makeText(this,"打电话",Toast.LENGTH_SHORT).show();
                break;
            case R.id.p2:
                Toast.makeText(this,"剪切",Toast.LENGTH_SHORT).show();
                break;
            case R.id.p3:
                Toast.makeText(this,"音量",Toast.LENGTH_SHORT).show();
                break;
            case R.id.p4:
                Toast.makeText(this,"浏览记录",Toast.LENGTH_SHORT).show();
                break;
            case R.id.p5:
                Toast.makeText(this,"保存",Toast.LENGTH_SHORT).show();
                break;
            case R.id.p6:
                Toast.makeText(this,"邮箱",Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
