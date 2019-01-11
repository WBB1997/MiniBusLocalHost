package android_serialport_api;


import android.util.Log;

import com.hasee.minibuslocalhost.activity.App;
import com.hasee.minibuslocalhost.util.LogUtil;
import com.hasee.minibuslocalhost.util.MyHandler;

public class SreialComm {
    private static final String TAG = "SreialComm";
    private SerialPortUtil serialPortUtil = null;
    private int m = 0;
    private byte bt[] = new byte[3];
    private MyHandler handler;//主线程

    public SreialComm(MyHandler handler){
        this.handler = handler;
        //实例化串口
        serialPortUtil = SerialPortUtil.getInstance();
    }

    public void receive() {
        //打开串口
        serialPortUtil.openSerialPort();
        serialPortUtil.setSCMDataReceiveListener(new SCMDataReceiveListener() {

            @Override
            public void dataRecevie(byte[] data, int size) {
                m++;
                StringBuffer tString = new StringBuffer();

                for (int i = 0; i < size; i++) {
                    String s = Integer.toBinaryString((data[i] & 0xFF) + 0x100).substring(1);
                    tString.append(s);
                }
//                Log.d("接受的数据", tString.toString());
                LogUtil.d(TAG, "接受的数据:" + tString.toString());
                bt[m - 1] = data[0];
                if (m == 3) {
//                    Log.d("123", "456");
                    m = 0;
                    String message = "";

                    int ptr[] = {bt[0], bt[1], bt[2]};
                    int c = Crc.xCal_crc(ptr, 2);
                    if (c == ptr[2]) {
                        if (ptr[0] == 1) {
                            message += "音量命令";
                            message += "/";
                        } else if (ptr[1] == 2) {
                            message += "灯光命令";
                            message += "/";
                        } else {
//                            System.out.println("命令错误！");
                            LogUtil.d(TAG, "命令错误！");
                        }
                        int i = ptr[1];
                        if (i >= 0 && i <= 26) {
                            message += i;
                            message += "/";
//                            System.out.println("接收成功！");
                            LogUtil.d(TAG, "接收成功！");
                            serialPortUtil.sendDataToSerialPort(bt);
                        } else {
//                            System.out.println("数据错误！");
                            LogUtil.d(TAG, "数据错误！");
                        }
//                        Log.e("数据正确", message);
                        LogUtil.d(TAG, "数据正确:" + message);
                        final String finalMessage = message;
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                App.getInstance().setAudioVolume(finalMessage);
                            }
                        });
                    } else {
//                        Log.d("校验结果", "数据错误！");
                        LogUtil.d(TAG, "校验结果:" + "数据错误！");
                    }
                }
            }

        });
    }

    public void close(){
        serialPortUtil.closeSerialPort();
    }
}
