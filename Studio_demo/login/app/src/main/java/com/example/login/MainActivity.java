package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private Button button;
    private EditText name;
    private EditText password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //实例化
        button = (Button) findViewById(R.id.button);
        name = (EditText) findViewById(R.id.editTextTextPersonName);
        password = (EditText) findViewById(R.id.editTextTextPassword);

        //读取数据
        Map<String,String> data = SpSaveData.Readdata(this);
        if (data!= null){
            name.setText(data.get("username"));
            password.setText(data.get("password"));
        }


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //获取文本框数据
                String name_text = name.getText().toString().trim();
                String password_text = password.getText().toString().trim();
                //判断文本框是否为空
                if (TextUtils.isEmpty(name_text)){
                    Toast.makeText(MainActivity.this,"请输入账号！",Toast.LENGTH_SHORT).show();
                }
                else if (TextUtils.isEmpty(password_text)){
                    Toast.makeText(MainActivity.this,"请输入密码！",Toast.LENGTH_SHORT).show();
                }
                boolean isSuccess = SpSaveData.Writedata(MainActivity.this,name_text,password_text);
                if(isSuccess){
                    Toast.makeText(MainActivity.this,"保存成功",Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(MainActivity.this,"保存失败",Toast.LENGTH_SHORT).show();
                }

                button.setText("登入成功");
            }
        });

    }
}