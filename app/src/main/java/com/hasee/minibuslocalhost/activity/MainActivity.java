package com.hasee.minibuslocalhost.activity;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;

import com.alibaba.fastjson.JSONObject;
import com.hasee.minibuslocalhost.R;
import com.hasee.minibuslocalhost.fragment.MainCenterFragment;
import com.hasee.minibuslocalhost.fragment.MainLeftFragment;
import com.hasee.minibuslocalhost.fragment.MainRightFragment1;
import com.hasee.minibuslocalhost.fragment.MainRightFragment2;
import com.hasee.minibuslocalhost.fragment.MainTopFragment;
import com.hasee.minibuslocalhost.transmit.Transmit;
import com.hasee.minibuslocalhost.util.ActivityCollector;
import com.hasee.minibuslocalhost.util.LogUtil;
import com.hasee.minibuslocalhost.util.MyHandler;
import com.hasee.minibuslocalhost.util.SendToScreenThread;
import com.hasee.minibuslocalhost.util.ToastUtil;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainActivity extends BaseActivity{
    public final static int SEND_TO_FRONTSCREEN = 0;//前风挡
    public final static int SEND_TO_RIGHTSCREEN = 1;//右车门
    public final static int SEND_TO_LEFTSCREEN = 2;//左车门
    public final static int SEND_TO_LOCALHOST = 3;//主控屏
    private Context mContext;//上下文
    private FragmentManager fragmentManager;//Fragment管理器对象
    private FragmentTransaction transaction;//Fragment事务对象
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = MainActivity.this;
        viewInit();
        new Thread(new Runnable() {
            @Override
            public void run() {
                Transmit.getInstance().setHandler(handler);
            }
        }).start();
        //申请相关权限
        if(ContextCompat.checkSelfPermission(mContext,Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(MainActivity.this,new String[]{
                    Manifest.permission.READ_EXTERNAL_STORAGE
            },1);
        }else{
            //有权限的话什么都不做
        }
    }

    /**
     * 初始化控件
     */
    private void viewInit() {
        //初始化右边Fragment
        fragmentManager = getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.right_fragment,new MainRightFragment1());
        transaction.commit();
    }

    /**
     * 接收CAN总线的信息，判断处理
     */
    private MyHandler handler = new MyHandler(mContext){
        @Override
        public void handleMessage(Message msg) {
            JSONObject object = (JSONObject) msg.obj;//CAN总线的数据
            LogUtil.d("MainActivity",object.toJSONString());
            LogUtil.d("MainActivity", String.valueOf(msg.what));
            switch (msg.what){
                case SEND_TO_FRONTSCREEN:{//前风挡
                    new SendToScreenThread(object,SEND_TO_FRONTSCREEN).start();
                    break;
                }
                case SEND_TO_RIGHTSCREEN:{//右车门
                    new SendToScreenThread(object,SEND_TO_RIGHTSCREEN).start();
                    break;
                }
                case SEND_TO_LEFTSCREEN:{//左车门
                    new SendToScreenThread(object,SEND_TO_LEFTSCREEN).start();
                    break;
                }
                case SEND_TO_LOCALHOST:{//主控屏
                    //改变主控屏的控件状态
                    int id = whatFragment(object);
                    if(id == 0){//上部Fragment
                        MainTopFragment topFragment = (MainTopFragment) getSupportFragmentManager().findFragmentById(R.id.top_fragment);
                        topFragment.refresh(object);
                    }else if(id == 1){//左边Fragment
                        MainLeftFragment leftFragment = (MainLeftFragment) getSupportFragmentManager().findFragmentById(R.id.left_fragment);
                        leftFragment.refresh(object);
                    }else if(id == 2){//中间Fragment
                        MainCenterFragment centerFragment = (MainCenterFragment) getSupportFragmentManager().findFragmentById(R.id.center_fragment);
                    }else if(id == 3){//右边Fragment
                        MainRightFragment2 rightFragment2 = (MainRightFragment2) getSupportFragmentManager().findFragmentById(R.id.right_fragment);
                        rightFragment2.refresh(object);
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
        switch (requestCode){
            case 1:{
                if(grantResults.length > 0&& grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    ToastUtil.getInstance(mContext).showShortToast("权限开启成功");
                }else{
                    ActivityCollector.finshAll();
                }
            }
        }
    }

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
     * @param clazz 类名
     * @param field 字段名
     * @param o 对象
     */
    public void sendToCAN(String clazz, int field, Object o) {
        Transmit.getInstance().hostToCAN(clazz, field, o);
    }

    /**
     * 处理Fragment的消息
     * @param flag 判断哪个按钮
     */
    public void handleFragmentMsg(int flag) {
        switch (flag){
            case 1:{//自动驾驶
                replaceFragment(new MainRightFragment2());
                break;
            }
        }
    }

    /**
     * 替换Fragment
     * @param fragment
     */
    public void replaceFragment(Fragment fragment){
        fragmentManager = getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.right_fragment,fragment);
        transaction.commit();
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    /**
     * 判断CAN总线的消息显示在哪个部分
     * @param object
     * @return
     */
    private int whatFragment(JSONObject object){
        int id = object.getIntValue("id");
        if(id>100){
            return 3;
        }
        return 1;
    }
}
