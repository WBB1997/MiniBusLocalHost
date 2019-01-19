package com.hasee.minibuslocalhost.fragment;


import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.hasee.minibuslocalhost.R;
import com.hasee.minibuslocalhost.activity.MainActivity;

import static com.hasee.minibuslocalhost.bean.IntegerCommand.BCM_InsideTemp;
import static com.hasee.minibuslocalhost.bean.IntegerCommand.BCM_OutsideTemp;
import static com.hasee.minibuslocalhost.bean.IntegerCommand.Wheel_Speed_ABS;
import static com.hasee.minibuslocalhost.bean.IntegerCommand.can_RemainKm;
import static com.hasee.minibuslocalhost.bean.IntegerCommand.can_num_PackAverageTemp;


/**
 * 右边Fragment（仪表盘）
 */
public class MainRightFragment2 extends Fragment {
    private static final String TAG = "MainRightFragment2";
    private MainActivity activity;
    private TextView rightFragment2BatteryTemperature;//温度
    private TextView rightFragment2BatteryTemperature_e;//温度
    private TextView rightFragment2Zonlic;//总里程
    private TextView rightFragment2Renwujd;//任务进度
    private TextView rightFragment2Pingjunss;//平均时速
    private TextView rightFragment2Speed;//速度
    private TextView rightFragment2DriveInfo;//行驶参数
    private double avgSpeed = 0;//平均速度
    private int speedCount = 0;//统计速度次数
    public MainRightFragment2(){

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (MainActivity) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_right2, container, false);
        rightFragment2BatteryTemperature = (TextView)view.findViewById(R.id.rightFragment2_batteryTemperature);
        Typeface typeface = Typeface.createFromAsset(activity.getAssets(),"font1.ttf");
        rightFragment2BatteryTemperature.setTypeface(typeface);
        rightFragment2BatteryTemperature_e = (TextView)view.findViewById(R.id.rightFragment2_batteryTemperature_e);
        rightFragment2Speed = (TextView)view.findViewById(R.id.rightFragment2_speed);
        rightFragment2Zonlic = (TextView)view.findViewById(R.id.rightFragment2_zonlic);
        rightFragment2Renwujd = (TextView)view.findViewById(R.id.rightFragment2_renwujd);
        rightFragment2Pingjunss = (TextView)view.findViewById(R.id.rightFragment2_pingjunss);
        rightFragment2DriveInfo = (TextView)view.findViewById(R.id.driveInfo_tv);
        showDriveInfo("");
        return view;
    }

    /**
     * 更新UI
     * @param object
     */
    public void refresh(JSONObject object){
        int id = object.getIntValue("id");
        if(id == Wheel_Speed_ABS){//车速
            int speed = (int) object.getDoubleValue("data");
//            speedCount++;
            rightFragment2Speed.setText(String.valueOf(speed));
        }
        if(id == BCM_InsideTemp){//车内温度

        }
        if(id == BCM_OutsideTemp){//车外温度

        }
        if(id == can_RemainKm){//剩余里程数
//            int data = (int) object.getDoubleValue("data");

        }
        if(id == can_num_PackAverageTemp){//电池包平均温度
            int data = object.getIntValue("data");
            if(data > 40){
                rightFragment2BatteryTemperature.setTextColor(
                        getResources().getColor(R.color.main_battery_color));
                rightFragment2BatteryTemperature_e.setTextColor(
                        getResources().getColor(R.color.main_battery_color));
            }else{
                rightFragment2BatteryTemperature.setTextColor(
                        getResources().getColor(R.color.right_fragment1_text_color));
                rightFragment2BatteryTemperature_e.setTextColor(
                        getResources().getColor(R.color.right_fragment1_text_color));
            }
            rightFragment2BatteryTemperature.setText(data);
        }
    }


    /**
     * 计算平均速度
     * @param speed
     * @param count
     * @return
     */
    private double calculate(int speed,int count){
        double newAvgSpeed = (avgSpeed*(count-1)+speed)/count;
        return newAvgSpeed;
    }

    /**
     * 显示行驶参数
     */
    private void showDriveInfo(String message){
        rightFragment2DriveInfo.append("</script><script>");
        rightFragment2DriveInfo.append("\n");
        rightFragment2DriveInfo.append("$.getJSON(\"//ajax.ibaotu.com/?");
        rightFragment2DriveInfo.append("\n");
        rightFragment2DriveInfo.append("m=wenjuan&a=statusjson&name");
        rightFragment2DriveInfo.append("\n");
        rightFragment2DriveInfo.append("=rjjc&callback=?\", function(e) {");
    }
}
