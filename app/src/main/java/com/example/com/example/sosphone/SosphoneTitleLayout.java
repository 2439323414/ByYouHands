package com.example.com.example.sosphone;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.example.R;

public class SosphoneTitleLayout extends LinearLayout {
    public SosphoneTitleLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.sosphonetitlelayout,this);

    }
}
