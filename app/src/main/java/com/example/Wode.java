package com.example;


import android.content.Intent;
import android.graphics.drawable.Drawable;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import android.widget.RadioButton;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.example.com.example.electronicfence.Electronicfence;
import com.example.com.example.familynumber.FamilyPhone;
import com.example.com.example.historicaltrack.HistoricaltrackActivity;
import com.example.com.example.historicaltrack.TracingActivity;
import com.example.com.example.sosphone.Sosphone;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Random;

import cz.msebera.android.httpclient.Header;


public class Wode extends AppCompatActivity {
    private Button dingwei;
    private TextView name;
    private Button boda;
    private Button history;
    private Button jianting;
    private Button dianziweilan1;
    private TextView zhujiemian;
    private TextView adress;
    private LocationClient mlocationClient;
    private TextView  weathers;
    public static String name2;
    private RadioButton news;
    private double latitude;
    private double longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wode);

        name = (TextView) findViewById(R.id.name);
        String name1 = getIntent().getStringExtra("name");

        if(name1!=null){

            name2= name1;
        }
        name.setText(name2);
        news =(RadioButton)findViewById(R.id.news);
        news.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Wode.this, NewsActivity.class);
                finish();
                startActivity(intent);
            }
        });
        adress = (TextView)findViewById(R.id.address);
        weathers = (TextView)findViewById(R.id.weather);
        TextView  mTextView1=(TextView)findViewById(R.id.nicheng);
        TextView  mTextView2=(TextView)findViewById(R.id.dizhi);
        TextView  mTextView3=(TextView)findViewById(R.id.shijian);
        TextView  mTextView4=(TextView)findViewById(R.id.tianqi);
        Drawable icon1 = getResources().getDrawable(R.mipmap.nicheng);
        Drawable icon2 = getResources().getDrawable(R.mipmap.dizhi);
        Drawable icon3 = getResources().getDrawable(R.mipmap.shijian);
        Drawable icon4 = getResources().getDrawable(R.mipmap.tianqi);
        //setBounds(left,top,right,bottom)里的参数从左到右分别是
        //drawable的左边到textview左边缘+padding的距离，drawable的上边离textview上边缘+padding的距离
        //drawable的右边边离textview左边缘+padding的距离，drawable的下边离textview上边缘+padding的距离
        //所以right-left = drawable的宽，top - bottom = drawable的高
        icon1.setBounds(0,0,80,80);
        icon2.setBounds(0,0,80,80);
        icon3.setBounds(0,0,80,80);
        icon4.setBounds(0,0,80,80);
        mTextView1.setCompoundDrawables(icon1, null, null, null);
        mTextView2.setCompoundDrawables(icon2, null, null, null);
        mTextView3.setCompoundDrawables(icon3, null, null, null);
        mTextView4.setCompoundDrawables(icon4, null, null, null);
        Intent it = getIntent();
        String da = it.getStringExtra("1");
        adress.setText(da);
        mlocationClient = new LocationClient(getApplicationContext());
        mlocationClient.registerLocationListener(new Wode.MyLocationClickListener());
        LocationClientOption option = new LocationClientOption();
        option.setScanSpan(3000);
        option.setIsNeedAddress(true);

        mlocationClient.setLocOption(option);
        mlocationClient.start();
        huizhujiemian();
        shishidingwei();
        startFamilyPhone();
        startSosphone();
        startDianziweilan();
        startHistory();

    }



    private void startHistory() {
        history = (Button)findViewById(R.id.histor);
        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Wode.this, HistoricaltrackActivity.class);
                startActivity(intent);
            }
        });
    }

    private void startDianziweilan() {
        dianziweilan1 = (Button) findViewById(R.id.dianziweilan1);
        dianziweilan1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Wode.this, Electronicfence.class);
                startActivity(intent);
            }
        });
    }

    private JSONObject dealWithRule1(JSONObject jsonObject, String rule) throws JSONException {
        String[] rules = rule.split(",");
        for (String key :
                rules) {
            if (!key.contains("[]")){
                jsonObject = parseJson1(jsonObject,0,key);//递归
            }else {
                jsonObject = parseJson1(jsonObject,1,key.substring(0,(key.length()-2)));
            }
        }
        return jsonObject;
    }

    public JSONObject parseJson1(JSONObject jsonStr,int type,String key) throws JSONException {
        if (type==0){
            JSONObject jsonObject1= (JSONObject) jsonStr.get(key);
            return jsonObject1;
        }else {
            JSONArray jsonObject1=  jsonStr.getJSONArray(key);
            int length = jsonObject1.length();
            Random rand = new Random();
            int random = rand.nextInt(length);
            JSONObject jsonObject2 = (JSONObject) jsonObject1.get(random);
            return jsonObject2;
        }
    }

    public void Weatheracquisition(double longitude,double latitude){
        String Url ="https://free-api.heweather.com/s6/weather/now?key=11e895a6b3854f0fb49508eea65df6ca&location="+longitude+","+latitude;

        RequestParams params = new RequestParams();
        AsyncHttpClient client=new AsyncHttpClient();
        client.get(Wode.this, Url, params, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);

                String rule="HeWeather6[],now";//待提取的key值
                try {
                    JSONObject jsonObject = dealWithRule1(response,rule);
                    String weather = jsonObject.optString("cond_txt");
                    String temperature = jsonObject.optString("tmp");
                    weathers.setText(weather+"   "+temperature+"°");
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                super.onSuccess(statusCode, headers, response);
            }
            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                super.onSuccess(statusCode, headers, responseString);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
            }
        });

    }
    public class MyLocationClickListener implements BDLocationListener {
        @Override
        public void onReceiveLocation(BDLocation location) {
            StringBuilder currentPosition = new StringBuilder();
            currentPosition.append(location.getProvince());
            currentPosition.append(location.getCity());
            currentPosition.append(location.getDistrict());
            latitude = location.getLatitude();
            longitude = location.getLongitude();
            Weatheracquisition(longitude,latitude);

            adress.setText(currentPosition);
            mlocationClient.stop();
        }
    }

    private void startSosphone() {

        jianting = (Button) findViewById(R.id.jianting);
       jianting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Wode.this, Sosphone.class);
                startActivity(intent);
            }
        });

    }
    private void startFamilyPhone() {
        boda =(Button) findViewById(R.id.boda);
        boda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Wode.this, FamilyPhone.class);
                startActivity(intent);
            }
        });
    }
    private void huizhujiemian() {
        zhujiemian = (TextView)findViewById(R.id.xiaofangzi);
        zhujiemian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Wode.this,MainActivity.class);
                finish();
                startActivity(intent);
            }
        });
    }

    private void shishidingwei() {
        dingwei= (Button)findViewById(R.id.dingwei);
        dingwei.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Wode.this, TracingActivity.class);
                startActivity(intent);
            }
        });
    }




}