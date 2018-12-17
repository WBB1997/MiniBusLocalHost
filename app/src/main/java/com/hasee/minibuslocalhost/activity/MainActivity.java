package com.hasee.minibuslocalhost.activity;

import android.content.Context;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.alibaba.fastjson.JSONObject;
import com.hasee.minibuslocalhost.R;
import com.hasee.minibuslocalhost.transmit.transmit;
import com.hasee.minibuslocalhost.util.ActivityCollector;
import com.hasee.minibuslocalhost.util.MyHandler;

public class MainActivity extends BaseActivity {
    private final int SEND_TO_FRONTSCREEN = 0;//前风挡
    private final int SEND_TO_RIGHTSCREEN = 1;//右车门
    private final int SEND_TO_LEFTSCREEN = 2;//左车门
    private final int SEND_TO_LOCALHOST = 3;//主控屏
    private Context mContext;//上下文
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = MainActivity.this;
        transmit.getInstance().setHandler(handler);
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

                    break;
                }
                case SEND_TO_RIGHTSCREEN:{//右车门
                    break;
                }
                case SEND_TO_LEFTSCREEN:{//左车门
                    break;
                }
                case SEND_TO_LOCALHOST:{//主控屏
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
}
