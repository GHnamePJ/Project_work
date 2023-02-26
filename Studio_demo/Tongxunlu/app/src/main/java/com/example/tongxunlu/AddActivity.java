package com.example.tongxunlu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddActivity extends AppCompatActivity {
    private EditText name;
    private EditText gender;
    private EditText phone;
    private Button button;

    //数据库
    private String name_text;
    private String gender_text;
    private String phone_text;
    private ContentValues contentValues;
    private SQLiteDatabase sqLiteDatabase;
    private MainActivity.DatabaseHelp databaseHelp;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        //实例化
        name = (EditText) findViewById(R.id.editTextTextPersonName4);
        gender = (EditText) findViewById(R.id.editTextTextPersonName5);
        phone = (EditText) findViewById(R.id.editTextTextPersonName6);
        button = (Button) findViewById(R.id.button);
        databaseHelp = new MainActivity.DatabaseHelp(this);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //文本数据
                name_text = name.getText().toString().trim();
                gender_text = gender.getText().toString().trim();
                phone_text = phone.getText().toString().trim();

                //添加
                contentValues = new ContentValues();
                contentValues.put("name",name_text);
                contentValues.put("gender",gender_text);
                contentValues.put("phone",phone_text);
                sqLiteDatabase = databaseHelp.getWritableDatabase();
                sqLiteDatabase.insert("addressbook",null,contentValues);
                Toast.makeText(AddActivity.this,"添加信息",Toast.LENGTH_LONG).show();
                sqLiteDatabase.close();

                Intent intent = new Intent(AddActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }

}