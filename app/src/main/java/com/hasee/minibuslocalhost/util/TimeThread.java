package com.hasee.minibuslocalhost.util;

import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by fangju on 2018/12/23
 * 时间线程
 */
public class TimeThread extends Thread {
    private final int TIME_FLAG = 1;
    private TextView timeTextView;//时间文本
    public TimeThread(TextView textView){
        this.timeTextView = textView;
    }

    //更新时间
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case TIME_FLAG:{//更新UI
                    long time = System.currentTimeMillis();
                    Date date = new Date(time);
                    SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
                    timeTextView.setText(format.format(date));
                }
            }
        }
    };

    @Override
    public void run() {
        while (true){
            try {
                sendText();
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 发送更新时间命令给UI线程
     */
    private void sendText(){
        Message msg = handler.obtainMessage();
        msg.what = TIME_FLAG;
        handler.sendMessage(msg);
    }
}
