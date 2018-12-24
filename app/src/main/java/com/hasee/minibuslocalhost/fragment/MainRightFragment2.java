package com.hasee.minibuslocalhost.fragment;


import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.res.ResourcesCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.hasee.minibuslocalhost.R;
import com.hasee.minibuslocalhost.activity.MainActivity;


/**
 * 右边Fragment（仪表盘）
 */
public class MainRightFragment2 extends Fragment {
    private MainActivity activity;
    private TextView rightFragment2BatteryTemperature;//温度
    private TextView rightFragment2Zonlic;//总里程
    private TextView rightFragment2Renwujd;//任务进度
    private TextView rightFragment2Pingjunss;//平均时速
    private TextView rightFragment2Speed;//速度

    public MainRightFragment2(){

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_right2, container, false);
        rightFragment2BatteryTemperature = (TextView)view.findViewById(R.id.rightFragment2_batteryTemperature);
//        Typeface typeface = Typeface.createFromAsset(activity.getAssets(),"fonts/font1.TTF");
//        rightFragment2BatteryTemperature.setTypeface(typeface);
        rightFragment2Speed = (TextView)view.findViewById(R.id.rightFragment2_speed);
        rightFragment2Zonlic = (TextView)view.findViewById(R.id.rightFragment2_zonlic);
        rightFragment2Renwujd = (TextView)view.findViewById(R.id.rightFragment2_renwujd);
        rightFragment2Pingjunss = (TextView)view.findViewById(R.id.rightFragment2_pingjunss);
        return view;
    }

    /**
     * 更新UI
     * @param object
     */
    public void refresh(JSONObject object){
        int id = object.getIntValue("id");
        if(id == 60){//车速
            rightFragment2Speed.setText((int) object.getDoubleValue("data"));
        }
    }
}
