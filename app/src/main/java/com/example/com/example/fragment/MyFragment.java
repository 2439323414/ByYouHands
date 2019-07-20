package com.example.com.example.fragment;


import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.example.Main2Activity;
import com.example.R;
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
import top.androidman.SuperButton;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyFragment extends Fragment {
    private  View view;
    private Button dingwei;
    private SuperButton boda;
    private Button history;
    private SuperButton jianting;
    private Button dianziweilan1;
    private TextView zhujiemian;
    private TextView adress;
    private LocationClient mlocationClient;
    private TextView  weathers;

    private double latitude;
    private double longitude;

    public MyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.myfragment,null);
        initView();
        return view;
    }

    private void initView() {
        adress = (TextView)view.findViewById(R.id.address);
        weathers = (TextView)view.findViewById(R.id.weather);
        TextView  mTextView1=(TextView)view.findViewById(R.id.nicheng);
        TextView  mTextView2=(TextView)view.findViewById(R.id.dizhi);
        TextView  mTextView3=(TextView)view.findViewById(R.id.shijian);
        TextView  mTextView4=(TextView)view.findViewById(R.id.tianqi);
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
//        Intent it = getIntent();
//        String da = it.getStringExtra("1");
//        adress.setText(da);

        mlocationClient = new LocationClient(getContext());
        mlocationClient.registerLocationListener(new MyFragment.MyLocationClickListener());
        LocationClientOption option = new LocationClientOption();
        option.setScanSpan(3000);
        option.setIsNeedAddress(true);

        TextView textView = view.findViewById(R.id.name);
        textView.setText(Main2Activity.user);
        mlocationClient.setLocOption(option);
        mlocationClient.start();
        shishidingwei();
        startFamilyPhone();
        startSosphone();
        startDianziweilan();
        startHistory();
    }
    private void startHistory() {
        history = (Button)view.findViewById(R.id.histor);
        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), HistoricaltrackActivity.class);
                startActivity(intent);
            }
        });
    }

    private void startDianziweilan() {
        dianziweilan1 = (Button) view.findViewById(R.id.dianziweilan1);
        dianziweilan1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), Electronicfence.class);
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
        client.get(getContext(), Url, params, new JsonHttpResponseHandler(){
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

        jianting = (SuperButton) view.findViewById(R.id.jianting);
        jianting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), Sosphone.class);
                startActivity(intent);
            }
        });

    }
    private void startFamilyPhone() {
        boda = (SuperButton) view.findViewById(R.id.boda);
        boda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), FamilyPhone.class);
                startActivity(intent);
            }
        });
    }

    private void shishidingwei() {
        dingwei= (Button)view.findViewById(R.id.dingwei);
        dingwei.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), TracingActivity.class);
                startActivity(intent);
            }
        });
    }


}
