package com.hasee.minibuslocalhost.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.MotionEvent;
import android.view.View;

import com.alibaba.fastjson.JSONObject;
import com.hasee.minibuslocalhost.R;

import com.hasee.minibuslocalhost.fragment.MainCenterFragment;
import com.hasee.minibuslocalhost.fragment.MainLeftFragment;
import com.hasee.minibuslocalhost.fragment.MainLowBatteryFragment;
import com.hasee.minibuslocalhost.fragment.MainRightFragment1;
import com.hasee.minibuslocalhost.fragment.MainRightFragment2;
import com.hasee.minibuslocalhost.fragment.MainTopFragment;
import com.hasee.minibuslocalhost.transmit.Transmit;
import com.hasee.minibuslocalhost.util.ActivityCollector;
import com.hasee.minibuslocalhost.util.LogUtil;
import com.hasee.minibuslocalhost.util.MyHandler;
import com.hasee.minibuslocalhost.util.SendToScreenThread;
import com.hasee.minibuslocalhost.util.ToastUtil;

import android_serialport_api.SreialComm;

import static com.hasee.minibuslocalhost.bean.IntegerCommand.*;
import static com.hasee.minibuslocalhost.transmit.Class.HMI.DRIVE_MODEL_AUTO;
import static com.hasee.minibuslocalhost.transmit.Class.HMI.OFF;
import static com.hasee.minibuslocalhost.transmit.Class.HMI.ON;


public class MainActivity extends BaseActivity {
    private static final String TAG = "MainActivity";
    public final static int SEND_TO_FRONTSCREEN = 0;//前风挡
    public final static int SEND_TO_RIGHTSCREEN = 1;//右车门
    public final static int SEND_TO_LEFTSCREEN = 2;//左车门
    public final static int SEND_TO_LOCALHOST = 3;//主控屏
    public final static int LOCALHOST_SCREEN_TOP = 0;//主控屏上部分
    public final static int LOCALHOST_SCREEN_LEFT = 1;//主控屏左边部分
    public final static int LOCALHOST_SCREEN_CENTER = 2;//主控屏中间部分
    public final static int LOCALHOST_SCREEN_RIGHT = 3;//主控屏右边部分
    private final int MIN_BATTERY = 20;//低电量触发值
    private Context mContext;//上下文
    private FragmentManager fragmentManager;//Fragment管理器对象
    private FragmentTransaction transaction;//Fragment事务对象
    private boolean autoDriveModel = false;//默认关闭自动驾驶模式
    private MainTopFragment topFragment;//上部Fragment(时间、电量)
    private MainLeftFragment leftFragment;//左部Fragment（灯光、）
    private MainCenterFragment centerFragment;//中间部分Fragment(地图)
    private MainRightFragment2 rightFragment2;//右边Fragment(车速、)
    private MainLowBatteryFragment lowBatteryFragment;//低电量报警
    private FloatingActionButton floatBtn;//悬浮按钮
    private Thread canThread;//处理CAN总线的子线程
    private Thread sreialThread;//处理485的子线程
    private SreialComm sreialComm;//串口

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = MainActivity.this;
        //初始化布局
        viewInit();
        //初始化相关类
        classInit();
        //申请相关权限
        if (ContextCompat.checkSelfPermission(mContext, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{
                    Manifest.permission.READ_EXTERNAL_STORAGE
            }, 1);
        } else {
            //有权限的话什么都不做
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //中断CAN总线的子线程
        canThread.interrupt();
        //关闭485串口
        sreialComm.close();
        //中断485线程
        sreialThread.interrupt();
        LogUtil.d(TAG,"onDestroy");
    }

    /**
     * 初始化相关类
     */
    private void classInit(){
        //打开CAN监听
        canThread = new Thread(new Runnable() {
            @Override
            public void run() {
                Transmit.getInstance().setHandler(handler);
            }
        });
        canThread.start();
        //打开re485
        sreialThread = new Thread(new Runnable() {
            @Override
            public void run() {
                sreialComm = new SreialComm(handler);
                sreialComm.receive();
            }
        });
        sreialThread.start();
    }

    /**
     * 初始化控件
     */
    private void viewInit() {
        floatBtn = (FloatingActionButton)findViewById(R.id.floatBtn);
        floatBtn.setOnClickListener(onClickListener);
        //初始化右边Fragment
        fragmentManager = getSupportFragmentManager();
        topFragment = (MainTopFragment) fragmentManager.findFragmentById(R.id.top_fragment);
        leftFragment = (MainLeftFragment) fragmentManager.findFragmentById(R.id.left_fragment);
        centerFragment = (MainCenterFragment) fragmentManager.findFragmentById(R.id.center_fragment);
//        rightFragment2 = (MainRightFragment2) fragmentManager.findFragmentById(R.id.right_fragment);
        lowBatteryFragment = new MainLowBatteryFragment();
        //
        transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.right_fragment, new MainRightFragment1());//右边
        transaction.add(R.id.lowBattery_fragment, lowBatteryFragment).hide(lowBatteryFragment);//加入低速报警并隐藏
        transaction.commit();
    }

    /**
     * 接收CAN总线的信息，判断处理
     */
    private MyHandler handler = new MyHandler(mContext) {
        @Override
        public void handleMessage(Message msg) {
            JSONObject object = (JSONObject) msg.obj;//CAN总线的数据
            LogUtil.d("MainActivity", object.toJSONString());
            LogUtil.d("MainActivity", String.valueOf(msg.what));
            switch (msg.what) {
                case SEND_TO_FRONTSCREEN: {//前风挡
                    new SendToScreenThread(object, SEND_TO_FRONTSCREEN).start();
                    break;
                }
                case SEND_TO_RIGHTSCREEN: {//右车门
                    new SendToScreenThread(object, SEND_TO_RIGHTSCREEN).start();
                    break;
                }
                case SEND_TO_LEFTSCREEN: {//左车门
                    new SendToScreenThread(object, SEND_TO_LEFTSCREEN).start();
                    break;
                }
                case SEND_TO_LOCALHOST: {//主控屏
                    //改变主控屏的控件状态
                    int screenId = whatFragment(object);
                    if (screenId == LOCALHOST_SCREEN_TOP) {//上部Fragment
                        int battery = object.getIntValue("data");
                        if (battery <= MIN_BATTERY) {//低电量报警
                            showLowBatteryFragment(true);
                        } else {
                            showLowBatteryFragment(false);
                        }
                        topFragment.refresh(object);
                    } else if (screenId == LOCALHOST_SCREEN_LEFT) {//左边Fragment
                        leftFragment.refresh(object);
                    } else if (screenId == LOCALHOST_SCREEN_CENTER) {//中间Fragment
                        centerFragment.refresh(object);
                    } else if (screenId == LOCALHOST_SCREEN_RIGHT) {//右边Fragment
                        if (autoDriveModel) {//自动驾驶模式开启即处理数据
                            rightFragment2 = (MainRightFragment2) fragmentManager.findFragmentById(R.id.right_fragment);
                            int id = object.getIntValue("id");
                            if (id == 60) {//速度
                                int speed = (int) object.getDoubleValue("data");
                                if (speed <= 5) {//低速
                                    //发送低速报警消息
                                    sendToCAN("HMI", HMI_Dig_Ord_Alam, ON);
                                } else {
                                    sendToCAN("HMI", HMI_Dig_Ord_Alam, OFF);
                                }
                            }
                            rightFragment2.refresh(object);
                        }
                    }
                    break;
                }
                default:
                    break;
            }
        }
    };

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    ToastUtil.getInstance(mContext).showShortToast("权限开启成功");
                } else {
                    ActivityCollector.finshAll();
                }
            }
        }
    }

    /**
     *点击事件监听器
     */
    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @SuppressLint("RestrictedApi")
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.floatBtn:{//悬浮按钮
                    replaceFragment(new MainRightFragment1());
                    autoDriveModel = false;
                    floatBtn.setVisibility(View.INVISIBLE);
                    break;
                }
            }
        }
    };

    /**
     * 触摸事件
     */
    private View.OnTouchListener onTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            return false;
        }
    };

    /**
     * 返回键监听
     */
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        ActivityCollector.finshAll();
    }

    /**
     * 处理各个Fragment传过来的数据
     *
     * @param clazz 类名
     * @param field 字段名
     * @param o     对象
     */
    public void sendToCAN(String clazz, int field, Object o) {
        Transmit.getInstance().hostToCAN(clazz, field, o);
    }

    /**
     * 处理Fragment的消息
     *
     * @param flag 判断哪个按钮
     */
    @SuppressLint("RestrictedApi")
    public void handleFragmentMsg(int flag) {
        switch (flag) {
            case DRIVE_MODEL_AUTO: {//自动驾驶
                replaceFragment(new MainRightFragment2());
                autoDriveModel = true;
                floatBtn.setVisibility(View.VISIBLE);
                break;
            }
        }
    }

    /**
     * 替换Fragment
     *
     * @param fragment
     */
    private void replaceFragment(Fragment fragment) {
        fragmentManager = getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.right_fragment, fragment);
        transaction.commit();
    }

    /**
     * 显示和隐藏低电量报警Fragment
     */
    private void showLowBatteryFragment(boolean show) {
        fragmentManager = getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();
        if (show) {
            transaction.show(lowBatteryFragment);
        } else {
            transaction.hide(lowBatteryFragment);
        }
        transaction.commit();
    }


    /**
     * 判断CAN总线的消息显示在哪个部分
     *
     * @param object
     * @return
     */
    private int whatFragment(JSONObject object) {
        int id = object.getIntValue("id");
        LogUtil.d(TAG,"id:"+id);
        switch (id){
            //上部Fragment
            case OBU_LocalTime://本地时间
            case BMS_SOC://动力电池剩余电量SOC
            case HAD_GPSPositioningStatus://GPS状态
                return LOCALHOST_SCREEN_TOP;
            //左边Fragment
            case BCM_Flg_Stat_LeftTurningLamp://左转向状态信号
            case BCM_Flg_Stat_RightTurningLamp://右转向状态信号
            case BCM_Flg_Stat_HighBeam://远光灯状态信号
            case BCM_Flg_Stat_LowBeam://近光灯状态信号
            case BCM_Flg_Stat_RearFogLamp://后雾灯状态信号
            case BCM_Flg_Stat_DangerAlarmLamp://危险报警灯控制（双闪）状态信号
                return LOCALHOST_SCREEN_LEFT;
            //右边Fragment
            case BCM_InsideTemp://车内温度
            case BCM_OutsideTemp://车外温度
            case can_RemainKm://剩余里程数
                return LOCALHOST_SCREEN_RIGHT;
            //中间Fragment
            case HAD_GPSLongitude://经度
                return LOCALHOST_SCREEN_CENTER;
            default:
                return -1;
        }
    }
}
