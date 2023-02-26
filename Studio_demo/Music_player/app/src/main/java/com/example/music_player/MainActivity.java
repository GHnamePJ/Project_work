package com.example.music_player;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    private static final String TAG="MainActicity";
    private TextView textView1;
    private Timer timer; //创建定时器
    private TimerTask timerTask; //创建定时器任务
    private int count=5;
    private Handler handler; //消息处理器，专门发送和接收消息
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView(); //控件初始化
        initDate(); //数据初始化
//        initStatus(); //页面状态初始化
    }
    private void initView() {
        textView1=findViewById(R.id.text1);
    }
    private void initDate() {
        timer=new Timer();
        timerTask=new TimerTask() {
            @Override
//run()中的代码是定时器要完成的任务，都是耗时的操作，在 android中，耗时的操作都放在子线程中进行
            public void run() {
                // count++;
                Log.d(TAG, "run: "+count);
                //让子线程给主线程发送消息信号，主线程接收到消息信号后就可以更新主界面的数字显示信息
                if(count!=0){
                    //给主线程发送消息信息 1
                    Message msg=new Message();
                    msg.what=1; //1 表示消息信号
                    handler.sendMessage(msg);
                }else {
                    //给主线程发送消息信息 0
                    Message msg=new Message();
                    msg.what=0; //0 表示消息信号
                    handler.sendMessage(msg);

                }
            }
        };
        //开启定时器 参数 1：定时器任务 参数 2：延迟 参数 3：变化的周期
        timer.schedule(timerTask,0,1000);
        //主线程接受到消息信号对主界面数字显示进行更新
        handler=new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                //主线程根据接收到的消息进行判断
                switch (msg.what){
                    case 1:
                        //让数字递减
                        count--;
                        textView1.setText(count+""); //让变化的数字显示在主界面上
                        break;
                    case 0:
                        //倒计时结束，跳转到主界面
                        Intent intent=new Intent(MainActivity.this,MusicActivity.class);
                        startActivity(intent);
                        finish();
                        timer.cancel(); //关闭定时器
                        timerTask.cancel(); //关闭定时器任务
                        break;
                    default:
                        break;
                }
            }
        };
    }
}
