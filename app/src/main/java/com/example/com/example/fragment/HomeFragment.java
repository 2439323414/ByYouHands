package com.example.com.example.fragment;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.Main2Activity;
import com.example.R;
import com.example.com.example.aboutus.AboutUs;
import com.example.com.example.clock.Clock;
import com.example.com.example.familynumber.FamilyPhone;
import com.example.com.example.loginandreg.ChangepawActivity;
import com.example.com.example.sosphone.Sosphone;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    private View view;
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
    String name;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view =inflater.inflate(R.layout.homefragment,null);
        initView();
        return view;
    }

    private void initView() {
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
        tianjiashebei = (TextView)view.findViewById(R.id.tianjishebei);
        jiankangshebei = (TextView)view.findViewById(R.id.jiankangshebei);
        tianjiashebei.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"暂无可用设备！",Toast.LENGTH_SHORT).show();
            }
        });
        jiankangshebei.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"暂无可用设备！",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void startCaringobject() {
        caringobject = (TextView) view.findViewById(R.id.caringobject);
        caringobject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
                dialog.setTitle("关爱对象是：");
                dialog.setMessage(Main2Activity.user);
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
        changepsw = (TextView)view.findViewById(R.id.changepsw);
        changepsw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ChangepawActivity.class);
                startActivity(intent);
            }
        });
    }

    private void startClock() {
        clock = (TextView)view.findViewById(R.id.clock);
        clock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), Clock.class);
                startActivity(intent);
            }
        });
    }

    private void startMiandarao() {
        miandarao=(TextView)view.findViewById(R.id.miandarao);
        miandarao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
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
        guanyuwomen=(TextView)view.findViewById(R.id.guanyuwomen);
        guanyuwomen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), AboutUs.class);
                startActivity(intent);
            }
        });
    }

    private void startFamilyPhone() {
        qinqinghao1 =(TextView)view.findViewById(R.id.qinqinghao);
        qinqinghao1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), FamilyPhone.class);
                startActivity(intent);
            }
        });
    }

    private void startSosphone() {
        sosduanhua1 = (TextView)view.findViewById(R.id.sosdianhua);
        sosduanhua2 = (ImageView) view.findViewById(R.id.sosdianhua2);
        sosduanhua1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), Sosphone.class);
                startActivity(intent);
            }
        });
        sosduanhua2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), Sosphone.class);
                startActivity(intent);
            }
        });
    }

    private void startZhongduanhaoma() {
        zhongduanhaoma1=(TextView)view.findViewById(R.id.zhongduanhaoma);
        zhongduanhaoma2=(ImageView) view.findViewById(R.id.zhongduanhaoma2);
        zhongduanhaoma1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
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
                AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
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



}
