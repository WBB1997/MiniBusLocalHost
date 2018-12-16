package com.hasee.minibuslocalhost.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hasee.minibuslocalhost.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainCenterFragment extends Fragment {


    public MainCenterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main_center, container, false);
    }

}
