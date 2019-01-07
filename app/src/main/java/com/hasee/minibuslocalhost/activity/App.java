package com.hasee.minibuslocalhost.activity;

import android.app.Application;
import android.content.SharedPreferences;

import com.baidu.mapapi.CoordType;
import com.baidu.mapapi.SDKInitializer;

/**
 * Created by fangju on 2018/12/28
 */
public class App extends Application {
    private static App instance;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    public static App getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        preferences = getSharedPreferences("userInfo",MODE_PRIVATE);
        editor = getSharedPreferences("userInfo",MODE_PRIVATE).edit();
        // 在使用 SDK 各组间之前初始化 context 信息，传入 ApplicationContext
        SDKInitializer.initialize(this);
        //自4.3.0起，百度地图SDK所有接口均支持百度坐标和国测局坐标，用此方法设置您使用的坐标类型.
        //包括BD09LL和GCJ02两种坐标，默认是BD09LL坐标。
        SDKInitializer.setCoordType(CoordType.BD09LL);
    }

    /**
     * 返回本地密码信息
     * @return
     */
    public String getPreferences(){
        String userInfo = preferences.getString("userInfo","");
        return userInfo;
    }

    /**
     * 预存密码
     * @param s
     */
    public void setPreferences(String s){
        editor.putString("userInfo",s);
        editor.apply();
    }

}
