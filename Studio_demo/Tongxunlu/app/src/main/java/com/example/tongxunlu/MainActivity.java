package com.example.tongxunlu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button add;
    private Button delect;
    private Button update;
    private Button deleteall;
    private TextView show;
    private TextView count;

    private DatabaseHelp databaseHelp;
    private ContentValues contentValues;
    private SQLiteDatabase sqLiteDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //实例化
        add = (Button) findViewById(R.id.button9);
        delect = (Button) findViewById(R.id.button8);
        update = (Button) findViewById(R.id.button7);
        deleteall = (Button) findViewById(R.id.button6);
        show = (TextView) findViewById(R.id.listview);
        count = (TextView) findViewById(R.id.count);
        databaseHelp = new DatabaseHelp(this);
        //绑定点击事件
        add.setOnClickListener(this);
        delect.setOnClickListener(this);
        update.setOnClickListener(this);
        deleteall.setOnClickListener(this);

        //搜索所有数据
        selectall();

        //搜索数据的条数
        selectcount();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
           //增加
            case R.id.button9:
                Intent intent = new Intent(MainActivity.this,AddActivity.class);
                startActivity(intent);
                break;
            //删除
            case R.id.button8:
                Intent intent1 = new Intent(MainActivity.this,DeleteActivity.class);
                startActivity(intent1);
                break;
            //更新
            case R.id.button7:
                Intent intent2 = new Intent(MainActivity.this,UpdateActivity.class);
                startActivity(intent2);
                break;
            //清空
            case R.id.button6:
                sqLiteDatabase = databaseHelp.getWritableDatabase();
                sqLiteDatabase.delete("addressbook",null,null);
                Toast.makeText(this,"删除信息",Toast.LENGTH_SHORT).show();
                show.setText("");
                sqLiteDatabase.close();
                count.setText("共有0个联系人");
                break;
            default:
                break;
        }
    }

//数据库操作
static class DatabaseHelp extends SQLiteOpenHelper {
    //创建数据库
    public DatabaseHelp(Context context){
        super(context,"pj.db",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE addressbook(_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                "name VARCHAR(20)," +
                "gender VARCHAR(20)," +
                "phone VARCHAR(20))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int O, int N) {

    }
}

    //查询所有联系人
    private void selectall(){
        sqLiteDatabase = databaseHelp.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query("addressbook",null,null,null,
                null,null,null);
        if (cursor.getCount() == 0){
            Toast.makeText(this,"没有数据",Toast.LENGTH_LONG).show();
            show.setText("");
        }
        else {
            cursor.moveToFirst();
            show.setText("Name:"+cursor.getString(1)+"  \rgender:"+cursor.getString(2)+"\rPhone:"+cursor.getString(3));
            while (cursor.moveToNext()){
                show.append("\nName:"+cursor.getString(1)+" \rgender:"+cursor.getString(2)+"\rPhone:"+cursor.getString(3));
            }
        }
        cursor.close();
        sqLiteDatabase.close();
    }

    //搜索有几条数据
    private void selectcount(){
        sqLiteDatabase = databaseHelp.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query("addressbook",null,null,null,
                null,null,null);
        int counts = cursor.getCount();
        if (cursor.getCount() == 0){
            count.setText("共有0个联系人");
        }
        else {
           count.setText("共有"+counts+"个联系人");
        }
        cursor.close();
        sqLiteDatabase.close();
    }

}