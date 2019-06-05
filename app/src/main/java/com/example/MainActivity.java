package com.example;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.com.example.aboutus.AboutUs;
import com.example.com.example.clock.Clock;
import com.example.com.example.familynumber.FamilyPhone;
import com.example.com.example.loginandreg.ChangepawActivity;
import com.example.com.example.sosphone.Sosphone;

public class MainActivity extends AppCompatActivity {
    private RadioButton radioButton;
    private TextView tianjiashebei;
    private TextView jiankangshebei;
    private TextView changepsw;
    private TextView caringobject;
    private TextView miandarao;
    private TextView clock;
    private TextView guanyuwomen;
    private TextView qinqinghao1;
    private TextView zhongduanhaoma1;
    private ImageView zhongduanhaoma2;
    private TextView sosduanhua1;
    private ImageView sosduanhua2;
    private RadioButton news;
    String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name= getIntent().getStringExtra("name");
        if(name!=null){
            Wode.name2 = name;
        }

        news =(RadioButton)findViewById(R.id.news);
        news.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NewsActivity.class);
                finish();
                startActivity(intent);
            }
        });

        startWode();
        startZhongduanhaoma();
        startSosphone();
        startFamilyPhone();
        startAboutUs();
        startMiandarao();
        startClock();
        startChangepsw();
        startCaringobject();
        startTianjiashebei();
    }

    private void startTianjiashebei() {
        tianjiashebei = (TextView)findViewById(R.id.tianjishebei);
        jiankangshebei = (TextView)findViewById(R.id.jiankangshebei);
        tianjiashebei.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"暂无可用设备！",Toast.LENGTH_SHORT).show();
            }
        });
        jiankangshebei.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"暂无可用设备！",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void startCaringobject() {
        caringobject = (TextView) findViewById(R.id.caringobject);
        caringobject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                dialog.setTitle("关爱对象是：");
                dialog.setMessage(Wode.name2);
                dialog.setCancelable(false);
                dialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                dialog.show();
            }
        });
    }

    private void startChangepsw() {
        changepsw = (TextView)findViewById(R.id.changepsw);
        changepsw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ChangepawActivity.class);
                startActivity(intent);
            }
        });
    }

    private void startClock() {
        clock = (TextView)findViewById(R.id.clock);
        clock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Clock.class);
                startActivity(intent);
            }
        });
    }

    private void startMiandarao() {
        miandarao=(TextView)findViewById(R.id.miandarao);
        miandarao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                dialog.setTitle("免打扰设置成功：");
                dialog.setMessage("你已进入免打扰模式");
                dialog.setCancelable(false);
                dialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                dialog.show();
            }
        });
    }


    private void startAboutUs() {
        guanyuwomen=(TextView)findViewById(R.id.guanyuwomen);
        guanyuwomen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AboutUs.class);
                startActivity(intent);
            }
        });
    }

    private void startFamilyPhone() {
        qinqinghao1 =(TextView)findViewById(R.id.qinqinghao);
        qinqinghao1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FamilyPhone.class);
                startActivity(intent);
            }
        });
    }

    private void startSosphone() {
        sosduanhua1 = (TextView)findViewById(R.id.sosdianhua);
        sosduanhua2 = (ImageView) findViewById(R.id.sosdianhua2);
        sosduanhua1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Sosphone.class);
                startActivity(intent);
            }
        });
        sosduanhua2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Sosphone.class);
                startActivity(intent);
            }
        });
    }

    private void startZhongduanhaoma() {
        zhongduanhaoma1=(TextView)findViewById(R.id.zhongduanhaoma);
        zhongduanhaoma2=(ImageView) findViewById(R.id.zhongduanhaoma2);
        zhongduanhaoma1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                dialog.setTitle("终端号码：");
                dialog.setMessage("18865257356");
                dialog.setCancelable(false);
                dialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                dialog.show();
            }
        });
        zhongduanhaoma2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                dialog.setTitle("终端号码：");
                dialog.setMessage("18865257356");
                dialog.setCancelable(false);
                dialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                dialog.show();
            }
        });
    }

    protected void startWode(){
        radioButton = (RadioButton)findViewById(R.id.wode);
        radioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Wode.class);
                intent.putExtra("name",name);
                finish();
                startActivity(intent);
            }
        });
    }
}
