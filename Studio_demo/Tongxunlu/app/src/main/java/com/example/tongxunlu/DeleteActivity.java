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

public class DeleteActivity extends AppCompatActivity {
    private EditText name;
    private Button button;

    private SQLiteDatabase sqLiteDatabase;
    private MainActivity.DatabaseHelp databaseHelp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);
        //实例化
        name = (EditText) findViewById(R.id.editTextTextPersonName4);
        button = (Button) findViewById(R.id.button);
        databaseHelp = new MainActivity.DatabaseHelp(this);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sqLiteDatabase = databaseHelp.getWritableDatabase();
                sqLiteDatabase.delete("addressbook","name=?",new String[]{name.getText().toString()});
                Toast.makeText(DeleteActivity.this,"删除信息",Toast.LENGTH_LONG).show();
                sqLiteDatabase.close();

                Intent intent = new Intent(DeleteActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

    }
}