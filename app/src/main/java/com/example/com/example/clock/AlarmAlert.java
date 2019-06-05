package com.example.com.example.clock;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.os.Bundle;

import com.example.R;


public class AlarmAlert extends Activity {
    private MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        //加入音乐
        mediaPlayer = MediaPlayer.create(this,R.raw.clockmusic2);
        mediaPlayer.start();
        new AlertDialog.Builder(AlarmAlert.this)
                .setIcon(R.drawable.clock)
                .setTitle("闹钟响了")
                .setMessage("时间到了！")
                .setPositiveButton("关掉"
                        , new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                AlarmAlert.this.finish();
                                mediaPlayer.stop();
                            }
                        }).show();
    }
}