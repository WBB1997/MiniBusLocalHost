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
 * A simple {@link Fragment} subclass.
 */
@SuppressLint("ValidFragment")
public class MainLeftFragment extends Fragment {
    private Context mContext;

    public MainLeftFragment(){
    }

    public MainLeftFragment(Context mContext) {
        this.mContext = mContext;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_left,container,false);
        return view;
    }

}
