package com.example;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

public class NewsActivity extends AppCompatActivity {
    private TextView news;
    private TextView zhujiemian;
    private RadioButton radioButton;
    String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        news =(TextView)findViewById(R.id.news);
        Intent it = getIntent();
        String id = it.getStringExtra("id");
        if(id!=null){

            if(id.equals("1"))
            {
                news.setText("你已经进入围栏了！");
            }else if(id.equals("2")){
                news.setText("你已经离开围栏了！");
            }

        }
        huizhujiemian();
        startWode();
    }
    private void huizhujiemian() {
        zhujiemian = (TextView)findViewById(R.id.xiaofangzi);
        zhujiemian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NewsActivity.this,MainActivity.class);
                finish();
                startActivity(intent);
            }
        });
    }
    protected void startWode(){
        radioButton = (RadioButton)findViewById(R.id.wode);
        radioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NewsActivity.this,Wode.class);
                intent.putExtra("name",name);
                finish();
                startActivity(intent);
            }
        });
    }
}
