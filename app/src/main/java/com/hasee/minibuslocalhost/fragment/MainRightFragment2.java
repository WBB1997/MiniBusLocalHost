package com.hasee.minibuslocalhost.fragment;


import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hasee.minibuslocalhost.R;


/**
 * 右边Fragment（仪表盘）
 */
public class MainRightFragment2 extends Fragment {
    private Context mContext;

    public MainRightFragment2(){

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_right2, container, false);
        return view;
    }

}
