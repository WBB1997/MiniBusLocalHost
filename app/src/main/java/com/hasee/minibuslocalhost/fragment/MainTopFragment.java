package com.hasee.minibuslocalhost.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hasee.minibuslocalhost.R;


/**
 * 上部分Fragment
 */
public class MainTopFragment extends Fragment {
    private TextView topFragmentTime;//时间
    private TextView topFragmentBatteryTv;//电池文字信息

    public MainTopFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main_top, container, false);
    }

}
