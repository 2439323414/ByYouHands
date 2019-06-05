package com.example;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;

import com.example.com.example.loginandreg.LoginActivity;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes(); localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
        int version = Build.VERSION.SDK_INT;
        Window window = getWindow();
        if (version >= Build.VERSION_CODES.KITKAT) {
            window.getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
                            View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION |
                            View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |
                            View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
                            View.SYSTEM_UI_FLAG_FULLSCREEN |
                            View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
            );
        }
        else
        {
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE );
        }
        setContentView(R.layout.activity_splash);
        Thread myThread=new Thread(){
            //创建子线程
            @Override
            public void run() {
                try{
                    sleep(3000);
                    //使程序休眠五秒
                    Intent it=new Intent(getApplicationContext(),LoginActivity.class);
                    //启动MainActivity
                    startActivity(it);
                    finish();
                    //关闭当前活动
                    }catch (Exception e){
                    e.printStackTrace();
                }
            }
        };
        myThread.start();
        //启动线程
        }



}
