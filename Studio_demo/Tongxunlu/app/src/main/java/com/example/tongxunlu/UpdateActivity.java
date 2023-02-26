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

public class UpdateActivity extends AppCompatActivity {
    private EditText name;
    private EditText gender;
    private EditText phone;
    private Button button;

    private ContentValues contentValues;
    private SQLiteDatabase sqLiteDatabase;
    private MainActivity.DatabaseHelp databaseHelp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        //实例化
        name = (EditText) findViewById(R.id.editTextTextPersonName4);
        gender = (EditText) findViewById(R.id.editTextTextPersonName5);
        phone = (EditText) findViewById(R.id.editTextTextPersonName6);
        button = (Button) findViewById(R.id.button);
        databaseHelp = new MainActivity.DatabaseHelp(this);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sqLiteDatabase = databaseHelp.getWritableDatabase();
                contentValues = new ContentValues();
                contentValues.put("gender",gender.getText().toString());
                contentValues.put("phone",phone.getText().toString());
                sqLiteDatabase.update("addressbook",contentValues,"name=?",new String[]{name.getText().toString()});
                Toast.makeText(UpdateActivity.this,"修改信息",Toast.LENGTH_SHORT).show();
                sqLiteDatabase.close();

                Intent intent = new Intent(UpdateActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}