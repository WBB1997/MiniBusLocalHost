package com.hasee.minibuslocalhost.util;

import android.os.Message;
import android.util.Log;

import com.alibaba.fastjson.JSONObject;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import static com.hasee.minibuslocalhost.activity.MainActivity.SEND_TO_FRONTSCREEN;
import static com.hasee.minibuslocalhost.activity.MainActivity.SEND_TO_LEFTSCREEN;
import static com.hasee.minibuslocalhost.activity.MainActivity.SEND_TO_SCREEN;
import static com.hasee.minibuslocalhost.bean.IntegerCommand.HAD_ArrivingSiteRemind;
import static com.hasee.minibuslocalhost.bean.IntegerCommand.HAD_CurrentDrivingRoadIDNum;
import static com.hasee.minibuslocalhost.bean.IntegerCommand.HAD_NextStationIDNumb;
import static com.hasee.minibuslocalhost.bean.IntegerCommand.HAD_PedestrianAvoidanceRemind;
import static com.hasee.minibuslocalhost.bean.IntegerCommand.HAD_StartingSitedepartureRemind;
import static com.hasee.minibuslocalhost.bean.IntegerCommand.PCG_Left_Work_Sts;

/**
 * Created by fangju on 2019/1/25
 * 定时发送模拟数据（只模拟）
 */
public class TimerManager {
    private static final String TAG = "TimerManager";
    private MyHandler handler = null;
    private Timer timer = null;
    private ReschedulableTimerTask timerTask = null;
    private boolean isPause = false;//默认开启
    private boolean isStop = false;//默认开启
    private int delay = 0;
    private int period = 15000;
    private TouchTimer touchTimer = null;//触摸检测
    private List<JSONObject> msgs = new ArrayList<>();
    private int index = 0;

    public TimerManager(MyHandler handler) {
        this.handler = handler;
        initMsg();
    }

    private void initMsg() {
        //1、路线ID,都发，延时15s
        JSONObject object1 = new JSONObject();//{id:"",delay:"",data:""}
        object1.put("id", SEND_TO_SCREEN);//都发
//        object1.put("delay", 15000);//
        object1.put("delay", 500);//
        JSONObject data1 = new JSONObject();
        data1.put("id", HAD_CurrentDrivingRoadIDNum);//63
        data1.put("data", 1);//第一条路线
        object1.put("data", data1);
        msgs.add(object1);

        //3、下一站站点，左车门，延时0.5s
        JSONObject object3 = new JSONObject();//{id:"",delay:"",data:""}
        object3.put("id", SEND_TO_SCREEN);
//        object3.put("delay", 500);
        object3.put("delay", 80000);
        JSONObject data3 = new JSONObject();
        data3.put("id", HAD_NextStationIDNumb);
        data3.put("data", 3);
        object3.put("data", data3);
        msgs.add(object3);

        //2、自动驾驶轮播，都发，延时80s
        JSONObject object2 = new JSONObject();//{id:"",delay:"",data:""}
        object2.put("id", SEND_TO_SCREEN);
//        object2.put("delay", 80000);
        object2.put("delay", 5000);
        JSONObject data2 = new JSONObject();
        data2.put("id", HAD_StartingSitedepartureRemind);
        data2.put("data", 2);
        object2.put("data", data2);
        msgs.add(object2);
//        //3、下一站站点，左车门，延时0.5s
//        JSONObject object3 = new JSONObject();//{id:"",delay:"",data:""}
//        object3.put("id", SEND_TO_LEFTSCREEN);
////        object3.put("delay", 500);
//        object3.put("delay", 5000);
//        JSONObject data3 = new JSONObject();
//        data3.put("id", HAD_NextStationIDNumb);
//        data3.put("data", 3);
//        object3.put("data", data3);
//        msgs.add(object3);
        //4、发送到站信号，都发，延时5s
        JSONObject object4 = new JSONObject();//{id:"",delay:"",data:""}
        object4.put("id", SEND_TO_SCREEN);//
//        object4.put("delay", 5000);
        object4.put("delay", 20000);
        JSONObject data4 = new JSONObject();
        data4.put("id", HAD_ArrivingSiteRemind);
        data4.put("data", 2);
        object4.put("data", data4);
        msgs.add(object4);
        //5、发送开门信息,都发，延时20s
        JSONObject object5 = new JSONObject();//{id:"",delay:"",data:""}
        object5.put("id", SEND_TO_SCREEN);
//        object5.put("delay", 20000);
        object5.put("delay", 5000);
        JSONObject data5 = new JSONObject();
        data5.put("id", PCG_Left_Work_Sts);
        data5.put("data", 3);
        object5.put("data", data5);
        msgs.add(object5);
        //6、发送关门信息，都发，延时5s
        JSONObject object6 = new JSONObject();//{id:"",delay:"",data:""}
        object6.put("id", SEND_TO_SCREEN);
//        object6.put("delay", 5000);
        object6.put("delay", 30000);
        JSONObject data6 = new JSONObject();
        data6.put("id", PCG_Left_Work_Sts);
        data6.put("data", 0);
        object6.put("data", data6);
        msgs.add(object6);
        //11、发送下一站（终点站），左车门，延时0.5s
        JSONObject object11 = new JSONObject();//{id:"",delay:"",data:""}
        object11.put("id", SEND_TO_LEFTSCREEN);
//        object11.put("delay", 500);
        object11.put("delay", 5000);
        JSONObject data11 = new JSONObject();
        data11.put("id", HAD_NextStationIDNumb);
        data11.put("data", 7);
        object11.put("data", data11);
        msgs.add(object11);
        //7、发送开车信息，都发，延时30s
        JSONObject object7 = new JSONObject();//{id:"",delay:"",data:""}
        object7.put("id", SEND_TO_SCREEN);
//        object7.put("delay", 30000);
        object7.put("delay", 20000);
        JSONObject data7 = new JSONObject();
        data7.put("id", HAD_ArrivingSiteRemind);
        data7.put("data", 3);
        object7.put("data", data7);
        msgs.add(object7);
        //8、发送行人避让信息，前风挡，延时20s
        JSONObject object8 = new JSONObject();//{id:"",delay:"",data:""}
        object8.put("id", SEND_TO_FRONTSCREEN);
//        object8.put("delay", 20000);
        object8.put("delay", 30000);
        JSONObject data8 = new JSONObject();
        data8.put("id", HAD_PedestrianAvoidanceRemind);
        data8.put("data", 2);
        object8.put("data", data8);
        msgs.add(object8);
        //9、发送开车信息，前风挡，延时30s
        JSONObject object9 = new JSONObject();//{id:"",delay:"",data:""}
        object9.put("id", SEND_TO_FRONTSCREEN);
//        object9.put("delay", 30000);
        object9.put("delay", 5000);
        JSONObject data9 = new JSONObject();
        data9.put("id", HAD_PedestrianAvoidanceRemind);
        data9.put("data", 3);
        object9.put("data", data9);
        msgs.add(object9);


        //12、发送到站信息（到了），都发，延时5s
        JSONObject object12 = new JSONObject();//{id:"",delay:"",data:""}
        object12.put("id", SEND_TO_SCREEN);
//        object12.put("delay", 5000);
        object12.put("delay", 20000);
        JSONObject data12 = new JSONObject();
        data12.put("id", HAD_ArrivingSiteRemind);
        data12.put("data", 2);
        object12.put("data", data12);
        msgs.add(object12);
        //13、发送开门信息，都发，延时20s
        JSONObject object13 = new JSONObject();//{id:"",delay:"",data:""}
        object13.put("id", SEND_TO_SCREEN);
//        object13.put("delay", 20000);
        object13.put("delay", 5000);
        JSONObject data13 = new JSONObject();
        data13.put("id", PCG_Left_Work_Sts);
        data13.put("data", 3);
        object13.put("data", data13);
        msgs.add(object13);
        //14、发送关门信息，都发，并等待5s
        JSONObject object14 = new JSONObject();//{id:"",delay:"",data:""}
        object14.put("id", SEND_TO_SCREEN);
//        object14.put("delay", 5000);
        object14.put("delay", 15000);
        JSONObject data14 = new JSONObject();
        data14.put("id", PCG_Left_Work_Sts);
        data14.put("data", 0);
        object14.put("data", data14);
        msgs.add(object14);
        //15、对开屏、风挡显示屏显示路线信息15s
//        JSONObject object15 = new JSONObject();//{id:"",delay:"",data:""}
//        object15.put("id",SEND_TO_SCREEN);
//        object15.put("delay",);
//        JSONObject data15 = new JSONObject();
//        data15.put("id",);
//        data15.put("data",);
//        object15.put("data",data15);
//        msgs.add(object15);
    }

    public void setPause(boolean pause) {
        isPause = pause;
        Log.d(TAG, "setPause: ");
    }

    public boolean isPause() {
        return isPause;
    }

    public void setStop(boolean stop) {
        isStop = stop;
    }

    /**
     * 定时任务开启
     */
    public void startTimer() {
        //
        touchTimer = new TouchTimer(this);
        touchTimer.startTimer();
        //
        isPause = true;
        if (timer == null) {
            timer = new Timer();
        }
        if (timerTask == null) {
            timerTask = new ReschedulableTimerTask() {
                @Override
                public void run() {
                    if (index >= msgs.size()) {//索引值超过总大小
                        index = 0;
                    }
                    if (isPause) {//发送模拟数据
                        JSONObject object = msgs.get(index);
                        period = object.getIntValue("delay");//延时多长时间
                        int id = object.getIntValue("id");//发送给哪个;
                        JSONObject data = object.getJSONObject("data");//发送的数据
                        sendVirtualData(id, data);
                        timerTask.setPeriod(period);
                        Log.d(TAG, "发送命令后延时：" + period);
                        index++;
                    } else {//检测30s
//                        isPause = true;
//                        if(touchTimer.isStop()){
//                            touchTimer.startTimer();
//                            Log.d(TAG, "run: "+"jiance");
//                        }
                    }
                }
            };
        }
        if (timer != null && timerTask != null) {
            Log.d(TAG, "startTimer: ");
            timer.schedule(timerTask, delay, period);
        }
    }

    /**
     * 关闭定时器
     */
    public void stopTimer() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
        if (timerTask != null) {
            timerTask.cancel();
            timerTask = null;
        }
        if (touchTimer != null) {
            touchTimer.stopTimer();
        }
    }

    /**
     * 发送虚拟数据
     */
    private void sendVirtualData(int id, JSONObject data) {
//        Log.d(TAG, "sendVirtualData: ");
        //发送数据
        Message msg = handler.obtainMessage();
        msg.what = id;//发送给哪个
        msg.obj = data;//发送的数据
        handler.sendMessage(msg);

    }

    public abstract class ReschedulableTimerTask extends TimerTask {
        public void setPeriod(long period) {
            //缩短周期，执行频率就提高
            setDeclaredField(TimerTask.class, this, "period", period);
        }

        //通过反射修改字段的值
        boolean setDeclaredField(Class<?> clazz, Object obj,
                                 String name, Object value) {
            try {
                Field field = clazz.getDeclaredField(name);
                field.setAccessible(true);
                field.set(obj, value);
                return true;
            } catch (Exception ex) {
                ex.printStackTrace();
                return false;
            }
        }
    }

}
