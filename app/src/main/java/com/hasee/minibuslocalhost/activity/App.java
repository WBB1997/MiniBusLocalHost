package com.hasee.minibuslocalhost.activity;

import android.app.Application;
import android.content.SharedPreferences;
import android.media.AudioManager;

import com.alibaba.fastjson.JSONObject;
import com.baidu.mapapi.CoordType;
import com.baidu.mapapi.SDKInitializer;
import com.hasee.minibuslocalhost.util.LogUtil;

import static android.support.constraint.Constraints.TAG;
import static android_serialport_api.SreialComm.AUDIO_VOLUME;
import static android_serialport_api.SreialComm.LIGHT_NUM;

/**
 * Created by fangju on 2018/12/28
 */
public class App extends Application {
    private static final String TAG = "App";
    private static App instance;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private AudioManager audioManager;

    public static App getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        audioManager = (AudioManager) this.getSystemService(AUDIO_SERVICE);
        preferences = getSharedPreferences("userInfo", MODE_PRIVATE);
        editor = getSharedPreferences("userInfo", MODE_PRIVATE).edit();
        // 在使用 SDK 各组间之前初始化 context 信息，传入 ApplicationContext
        SDKInitializer.initialize(this);
        //自4.3.0起，百度地图SDK所有接口均支持百度坐标和国测局坐标，用此方法设置您使用的坐标类型.
        //包括BD09LL和GCJ02两种坐标，默认是BD09LL坐标。
        SDKInitializer.setCoordType(CoordType.BD09LL);
    }

    /**
     * 返回本地密码信息
     *
     * @return
     */
    public String getPreferences() {
        String userInfo = preferences.getString("userInfo", "");
        return userInfo;
    }

    /**
     * 预存密码
     *
     * @param s
     */
    public void setPreferences(String s) {
        editor.putString("userInfo", s);
        editor.apply();
    }

    /**
     * 设置系统音量(来自485的信息)
     */
    public void setAudioVolume(JSONObject object) {
        int id = object.getIntValue("id");
        int data = object.getIntValue("data");
        if (id == AUDIO_VOLUME) {//音量
            if (data >= 0) {//音量值大于等于零
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, data, AudioManager.FLAG_PLAY_SOUND);
                LogUtil.d(TAG,"data:"+data);
            }
        }
        if (id == LIGHT_NUM) {//灯光

        }
    }
}
