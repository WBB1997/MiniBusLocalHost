package com.hasee.minibuslocalhost.activity;

import android.content.Context;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;

import com.alibaba.fastjson.JSONObject;
import com.hasee.minibuslocalhost.R;
import com.hasee.minibuslocalhost.fragment.MainRightFragment1;
import com.hasee.minibuslocalhost.fragment.MainRightFragment2;
import com.hasee.minibuslocalhost.transmit.transmit;
import com.hasee.minibuslocalhost.util.ActivityCollector;
import com.hasee.minibuslocalhost.util.MyHandler;
import com.hasee.minibuslocalhost.util.SendToScreenThread;

public class MainActivity extends BaseActivity{
    private final int SEND_TO_FRONTSCREEN = 0;//前风挡
    private final int SEND_TO_RIGHTSCREEN = 1;//右车门
    private final int SEND_TO_LEFTSCREEN = 2;//左车门
    private final int SEND_TO_LOCALHOST = 3;//主控屏
    private Context mContext;//上下文
    private FragmentManager fragmentManager;//Fragment管理器对象
    private FragmentTransaction transaction;//Fragment事务对象
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = MainActivity.this;

        new Thread(new Runnable() {
            @Override
            public void run() {
                transmit.getInstance().setHandler(handler);
            }
        }).start();
        viewInit();
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
                    break;
                }
                default:
                    break;
            }
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
     * @param clazz 类名
     * @param field 字段名
     * @param o 对象
     */
    public void sendToCAN(String clazz, String field, Object o) {
//        transmit.getInstance().hostToCAN();
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
}
