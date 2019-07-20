package com.example;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.com.example.fragment.HomeFragment;
import com.example.com.example.fragment.MyFragment;
import com.example.com.example.fragment.NewsFragment;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity implements ViewPager.OnPageChangeListener {

    private Button button_tab_news,button2_tab_home,button3_tab_My;
    private ViewPager viewPager;
    private MyAdapter myAdapter;
    private HomeFragment homeFragment;
    private MyFragment myFragment;
    private NewsFragment newsFragment;
    private ArrayList<Fragment> fragments = new ArrayList<>();
    public static String user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        initView();
    }

    private void initView() {
        button_tab_news = findViewById(R.id.button_tab_news);
        button2_tab_home= findViewById(R.id.button2_tab_home);
        button3_tab_My=findViewById(R.id.button3_tab_My);
        defaultButton();
        viewPager=findViewById(R.id.viewPager);

        homeFragment=new HomeFragment();
        myFragment=new MyFragment();
        newsFragment=new NewsFragment();

        fragments.add(newsFragment);
        fragments.add(homeFragment);
        fragments.add(myFragment);


        myAdapter=new MyAdapter(getSupportFragmentManager());
        viewPager.setAdapter(myAdapter);
        viewPager.setCurrentItem(1);
        viewPager.addOnPageChangeListener(this);

        //获得传过来的数据
        Intent intent = getIntent();
        user = intent.getStringExtra("name");

    }

    private void defaultButton() {
        button_tab_news.setSelected(false);
        button2_tab_home.setSelected(true);
        button3_tab_My.setSelected(false);
    }

    public void tabSelectClick(View view) {
        changeTab(view);
    }


    //切换标签
    private void changeTab(View view) {
        switch (view.getId()){
            case  R.id.button_tab_news:
                button_tab_news.setSelected(true);
                button2_tab_home.setSelected(false);
                button3_tab_My.setSelected(false);
                viewPager.setCurrentItem(0);
                break;
            case  R.id.button2_tab_home:
                button_tab_news.setSelected(false);
                button2_tab_home.setSelected(true);
                button3_tab_My.setSelected(false);
                viewPager.setCurrentItem(1);
                break;
            case  R.id.button3_tab_My:
                button_tab_news.setSelected(false);
                button2_tab_home.setSelected(false);
                button3_tab_My.setSelected(true);
                viewPager.setCurrentItem(2);
                break;
                default:
                    break;
        }
    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int i) {
        switch (i){
            case 0:
                changeTab(button_tab_news);
                break;
            case 1:
                changeTab(button2_tab_home);
                break;
            case 2:
                changeTab(button3_tab_My);
                break;
                default:
                    break;
        }
    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }

    class MyAdapter extends FragmentPagerAdapter{

      public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
           return fragments.get(i);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
    }
}
