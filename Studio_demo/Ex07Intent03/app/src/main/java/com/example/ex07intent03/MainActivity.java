package com.example.ex07intent03;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button b1,b2,b3,b4,b5,b6,b7,b8,b9;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b1=this.findViewById(R.id.b1);
        b1.setOnClickListener(this);

        b2=this.findViewById(R.id.b2);
        b2.setOnClickListener(this);

        b3=this.findViewById(R.id.b3);
        b3.setOnClickListener(this);

        b4=this.findViewById(R.id.b4);
        b4.setOnClickListener(this);

        b5=this.findViewById(R.id.b5);
        b5.setOnClickListener(this);

        b6=this.findViewById(R.id.b6);
        b6.setOnClickListener(this);

        b7=this.findViewById(R.id.b7);
        b7.setOnClickListener(this);

        b8=this.findViewById(R.id.b8);
        b8.setOnClickListener(this);

        b9=this.findViewById(R.id.b9);
        b9.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        Uri uri;
        Intent intent;
        switch (view.getId()){
            case R.id.b1:
                uri=Uri.parse("smsto:15324");
                intent=new Intent(Intent.ACTION_SENDTO,uri);
                intent.putExtra("sms_body","请多喝热水");
                startActivity(intent);
//
                break;
            case R.id.b2:
                uri=Uri.parse("tel:15323092205");
                intent=new Intent(Intent.ACTION_DIAL,uri);
                startActivity(intent);
                break;
            case R.id.b3:
                uri=Uri.parse("mailto:pj");
                intent = new Intent(Intent.ACTION_SENDTO, uri);
                startActivity(intent);
                break;
            case R.id.b4:
                uri=Uri.parse("geo:15.34881,-33.5784");
                intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
                break;
            case R.id.b5:
                uri=Uri.parse("http:www.baidu.com");
                intent= new Intent(Intent.ACTION_VIEW,uri);
                startActivity(intent);
                break;
            case R.id.b6:
                intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivity(intent);
                break;
            case R.id.b7:
                intent= new Intent(Intent.ACTION_VIEW, ContactsContract.Contacts.CONTENT_URI);
                startActivity(intent);
                break;
            case R.id.b8:
                intent = new Intent();
                intent.setAction(MediaStore.INTENT_ACTION_STILL_IMAGE_CAMERA); // 启动相机app
                startActivity(intent);
                break;
            case R.id.b9:
                intent = new Intent();
                intent.setClassName("com.android.calculator2", "com.android.calculator2.Calculator"); // 调用setClassName指定了启动哪个应用程序
                startActivity(intent);
                break;
        }
    }
}
