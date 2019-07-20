package com.example.com.example.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewsFragment extends Fragment {


    public NewsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.newsfragment,null);

        Bundle bundle= NewsFragment.this.getArguments();
        if(bundle!=null){
            String msg = bundle.getString("msg");
            TextView textView = view.findViewById(R.id.news);
            textView.setText(msg);
        }

        return view;
    }

}
