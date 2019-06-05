package com.example.com.example.location;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.utils.CoordinateConverter;
import com.example.R;
import com.example.Wode;

import java.util.ArrayList;
import java.util.List;

public class Location extends AppCompatActivity {

   private StringBuilder currentPosition2 = new StringBuilder();
    public LocationClient mlocationClient;
    private TextView positionText;
    private MapView mapView;
    private BaiduMap baiduMap;
    private boolean isFirstLocate = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mlocationClient = new LocationClient(getApplicationContext());
        mlocationClient.registerLocationListener(new MyLocationClickListener());
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.location);
        mapView =(MapView) findViewById(R.id.bmapView);
        positionText = (TextView) findViewById(R.id.position_text_view);
        baiduMap = mapView.getMap();
        baiduMap.setMyLocationEnabled(true);
        List<String> permissionList = new ArrayList<>();
        if(ContextCompat.checkSelfPermission(Location.this,Manifest.permission.READ_PHONE_STATE)!=PackageManager.PERMISSION_GRANTED){
            permissionList.add(Manifest.permission.READ_PHONE_STATE);
        }
        if(ContextCompat.checkSelfPermission(Location.this,Manifest.permission.WRITE_EXTERNAL_STORAGE)!=PackageManager.PERMISSION_GRANTED){
            permissionList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        if(ContextCompat.checkSelfPermission(Location.this,Manifest.permission.ACCESS_FINE_LOCATION)!=PackageManager.PERMISSION_GRANTED){
            permissionList.add(Manifest.permission.ACCESS_FINE_LOCATION);
        }
        if(!permissionList.isEmpty()){
            String [] permissions = permissionList.toArray(new String[permissionList.size()]);
            ActivityCompat.requestPermissions(Location.this,permissions,1);
        }else {
            requestLocation();
        }
    }

    private void navigateTo(BDLocation location){
        if(isFirstLocate){
            CoordinateConverter converter  = new CoordinateConverter();
            converter.from(CoordinateConverter.CoordType.COMMON);
            // sourceLatLng待转换坐标
            LatLng ll = new LatLng(location.getLatitude(),location.getLongitude());
            converter.coord(ll);
            LatLng desLatLng = converter.convert();

            MapStatusUpdate update = MapStatusUpdateFactory.newLatLng(desLatLng);
            baiduMap.animateMapStatus(update);
            update=MapStatusUpdateFactory.zoomTo(19f);
            baiduMap.animateMapStatus(update);
            isFirstLocate =false;
        }
        MyLocationData.Builder locationbuilder=new MyLocationData.Builder();
        CoordinateConverter converter  = new CoordinateConverter();
        converter.from(CoordinateConverter.CoordType.COMMON);
        // sourceLatLng待转换坐标
        LatLng ll = new LatLng(location.getLatitude(),location.getLongitude());
        converter.coord(ll);
        LatLng desLatLng = converter.convert();
        locationbuilder.latitude(desLatLng.latitude);
        locationbuilder.longitude(desLatLng.longitude);

        MyLocationData locationData=locationbuilder.build();
        baiduMap.setMyLocationData(locationData);

    }

    private void requestLocation(){
        initLocation();
        mlocationClient.start();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case 1:
                if(grantResults.length>0){
                    for(int result : grantResults){
                        if(result != PackageManager.PERMISSION_GRANTED){
                            Toast.makeText(this,"必须同意所有权限才能使用",Toast.LENGTH_SHORT).show();
                            finish();
                            return;
                        }
                    }
                    requestLocation();
                }else {
                    Toast.makeText(this,"发生未知错误",Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
            default:
        }
    }

    private void initLocation(){
        LocationClientOption option = new LocationClientOption();
        option.setScanSpan(3000);
        option.setIsNeedAddress(true);
        mlocationClient.setLocOption(option);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
        Intent it = new Intent(this,Wode.class);
        String s=currentPosition2.toString();
        it.putExtra("1",s);
        startActivity(it);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mlocationClient.stop();
        mapView.onDestroy();
        baiduMap.setMyLocationEnabled(false);

    }

    public class MyLocationClickListener implements BDLocationListener {
        @Override
        public void onReceiveLocation(BDLocation location) {
            if(location.getLocType()==BDLocation.TypeGpsLocation||location.getLocType() == BDLocation.TypeNetWorkLocation){
                navigateTo(location);
            }
            StringBuilder currentPosition = new StringBuilder();
            currentPosition.append("经度：").append(location.getLatitude()).append("\n");
            currentPosition.append("纬度：").append(location.getLongitude()).append("\n");
            currentPosition.append("国家：").append(location.getCountry()).append("\n");
            currentPosition.append("省：").append(location.getProvince()).append("\n");
            currentPosition2.append(location.getProvince());
            currentPosition.append("市：").append(location.getCity()).append("\n");
            currentPosition2.append(location.getCity());
            currentPosition.append("区：").append(location.getDistrict()).append("\n");
            currentPosition2.append(location.getDistrict());
            currentPosition.append("街道：").append(location.getStreet()).append("\n");
            currentPosition.append("定位方式:");
            if(location.getLocType() == BDLocation.TypeGpsLocation){
                currentPosition.append("GPS");
            }else if(location.getLocType() == BDLocation.TypeNetWorkLocation){
                currentPosition.append("网络");
            }
            positionText.setText(currentPosition);
        }
    }

}
