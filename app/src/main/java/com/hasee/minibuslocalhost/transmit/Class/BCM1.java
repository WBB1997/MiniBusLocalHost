package com.hasee.minibuslocalhost.transmit.Class;


import com.alibaba.fastjson.JSONArray;
import com.hasee.minibuslocalhost.activity.MainActivity;
import com.hasee.minibuslocalhost.util.LogUtil;

import java.util.HashMap;
import java.util.Map;

import static com.hasee.minibuslocalhost.util.ByteUtil.countBits;
import static com.hasee.minibuslocalhost.util.ByteUtil.viewBinary;

public class BCM1 extends BaseClass {
    private final static int offset = 0;
    private final String TAG = "BCM1";

    //字段
    private MyPair<Integer> BCM_Dig_Ord_HandLightCtr = new MyPair<>(1, 62, MainActivity.SEND_TO_LOCALHOST); // 手势灯光控制信号
    private MyPair<Integer> BCM_Flg_Stat_LeftTurningLamp = new MyPair<>(1, 63, MainActivity.SEND_TO_LOCALHOST); // 左转向状态信号
    private MyPair<Integer> BCM_Flg_Stat_RightTurningLamp = new MyPair<>(1, 64, MainActivity.SEND_TO_LOCALHOST); // 右转向状态信号
    private MyPair<Integer> BCM_Flg_Stat_HandLightCtr = new MyPair<>(1, 65, MainActivity.SEND_TO_LOCALHOST); // 手势灯光控制状态信号
    private MyPair<Integer> BCM_Flg_Stat_HighBeam = new MyPair<>(1, 66, MainActivity.SEND_TO_LOCALHOST); // 远光灯状态信号
    private MyPair<Integer> BCM_Flg_Stat_LowBeam = new MyPair<>(1, 67, MainActivity.SEND_TO_LOCALHOST); // 近光灯状态信号
    private MyPair<Integer> BCM_Flg_Stat_RearFogLamp = new MyPair<>(1, 68, MainActivity.SEND_TO_LOCALHOST); // 后雾灯状态信号
    private MyPair<Integer> BCM_Flg_Stat_DangerAlarmLamp = new MyPair<>(1, 69, MainActivity.SEND_TO_LOCALHOST); // 危险报警灯控制（双闪）状态信号
    private MyPair<Integer> BCM_Flg_Stat_BrakeLamp = new MyPair<>(1, 70, MainActivity.SEND_TO_LOCALHOST); // 制动灯状态信号
    private MyPair<Integer> BCM_Flg_Stat_BackupLamp = new MyPair<>(1, 71, MainActivity.SEND_TO_LOCALHOST); // 倒车灯状态信号
    private MyPair<Integer> BCM_Flg_Stat_SeatSensor1 = new MyPair<>(1, 72, MainActivity.SEND_TO_LOCALHOST); // 座椅传感器1
    private MyPair<Integer> BCM_Flg_Stat_SeatSensor2 = new MyPair<>(1, 73, MainActivity.SEND_TO_LOCALHOST); // 座椅传感器2
    private MyPair<Integer> BCM_Flg_Stat_SeatSensor3 = new MyPair<>(1, 74, MainActivity.SEND_TO_LOCALHOST); // 座椅传感器3
    private MyPair<Integer> BCM_Flg_Stat_SeatSensor4 = new MyPair<>(1, 75, MainActivity.SEND_TO_LOCALHOST); // 座椅传感器4
    private MyPair<Integer> BCM_Flg_Stat_SeatSensor5 = new MyPair<>(1, 76, MainActivity.SEND_TO_LOCALHOST); // 座椅传感器5
    private MyPair<Integer> BCM_Flg_Stat_SeatSensor6 = new MyPair<>(1, 77, MainActivity.SEND_TO_LOCALHOST); // 座椅传感器6
    private MyPair<Integer> BCM_Flg_Stat_BeltsSensor1 = new MyPair<>(1, 78, MainActivity.SEND_TO_LOCALHOST); // 安全带传感器1
    private MyPair<Integer> BCM_Flg_Stat_BeltsSensor2 = new MyPair<>(1, 79, MainActivity.SEND_TO_LOCALHOST); // 安全带传感器2
    private MyPair<Integer> BCM_Flg_Stat_BeltsSensor3 = new MyPair<>(1, 80, MainActivity.SEND_TO_LOCALHOST); // 安全带传感器3
    private MyPair<Integer> BCM_Flg_Stat_BeltsSensor4 = new MyPair<>(1, 81, MainActivity.SEND_TO_LOCALHOST); // 安全带传感器4
    private MyPair<Integer> BCM_Flg_Stat_BeltsSensor5 = new MyPair<>(1, 82, MainActivity.SEND_TO_LOCALHOST); // 安全带传感器5
    private MyPair<Integer> BCM_Flg_Stat_BeltsSensor6 = new MyPair<>(1, 83, MainActivity.SEND_TO_LOCALHOST); // 安全带传感器6
    private MyPair<Integer> BCM_Flg_Stat_Temperature= new MyPair<>(8, 84, MainActivity.SEND_TO_RIGHTSCREEN); // 温度
//    private MyPair<Integer> FRONTSCREEN_1 = new MyPair<>(1, 111, MainActivity.SEND_TO_FRONTSCREEN); // 头显示器
//    private MyPair<Integer> FRONTSCREEN_2 = new MyPair<>(1, 112, MainActivity.SEND_TO_FRONTSCREEN); // 头显示器
//    private MyPair<Integer> FRONTSCREEN_3 = new MyPair<>(1, 113, MainActivity.SEND_TO_FRONTSCREEN); // 头显示器
//    private MyPair<Integer> LEFTSCREEN_1 = new MyPair<>(8, 114, MainActivity.SEND_TO_LEFTSCREEN); // 左显示器
//    private MyPair<Integer> LEFTSCREEN_2 = new MyPair<>(5, 115, MainActivity.SEND_TO_LEFTSCREEN); // 左显示器
//    private MyPair<Integer> LEFTSCREEN_3 = new MyPair<>(8, 116, MainActivity.SEND_TO_LEFTSCREEN); // 左显示器
//    private MyPair<Integer> RIGHTSCREEN = new MyPair<>(5, 117, MainActivity.SEND_TO_RIGHTSCREEN); // 右显示器

    // 属性
    private HashMap<Integer, MyPair<Integer>> fields = new HashMap<Integer, MyPair<Integer>>() {{
        put(0, BCM_Dig_Ord_HandLightCtr);
        put(1, BCM_Flg_Stat_LeftTurningLamp);
        put(2, BCM_Flg_Stat_RightTurningLamp);
        put(3, BCM_Flg_Stat_HandLightCtr);
        put(4, BCM_Flg_Stat_HighBeam);
        put(5, BCM_Flg_Stat_LowBeam);
        put(6, BCM_Flg_Stat_RearFogLamp);
        put(7, BCM_Flg_Stat_DangerAlarmLamp);
        put(8, BCM_Flg_Stat_BrakeLamp);
        put(9, BCM_Flg_Stat_BackupLamp);
        put(10, BCM_Flg_Stat_SeatSensor1);
        put(11, BCM_Flg_Stat_SeatSensor2);
        put(12, BCM_Flg_Stat_SeatSensor3);
        put(13, BCM_Flg_Stat_SeatSensor4);
        put(14, BCM_Flg_Stat_SeatSensor5);
        put(15, BCM_Flg_Stat_SeatSensor6);
        put(16, BCM_Flg_Stat_BeltsSensor1);
        put(17, BCM_Flg_Stat_BeltsSensor2);
        put(18, BCM_Flg_Stat_BeltsSensor3);
        put(19, BCM_Flg_Stat_BeltsSensor4);
        put(20, BCM_Flg_Stat_BeltsSensor5);
        put(21, BCM_Flg_Stat_BeltsSensor6);
        put(22, BCM_Flg_Stat_Temperature);
//        put(22, FRONTSCREEN_1);
//        put(23, FRONTSCREEN_2);
//        put(24, FRONTSCREEN_3);
//        put(25, LEFTSCREEN_1);
//        put(33, LEFTSCREEN_2);
//        put(38, LEFTSCREEN_3);
//        put(46, RIGHTSCREEN);
    }};
    private byte[] bytes = {0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00};

    public BCM1() {
    }

    @Override
    public byte[] getBytes() {
        return bytes;
    }

    @Override
    public String getTAG() {
        return TAG;
    }

    @Override
    public void setBytes(byte[] bytes) {
        super.setBytes(bytes);
    }

    @Override
    public Object getValue(Map.Entry<Integer, MyPair<Integer>> entry, byte[] bytes) {
        int index = entry.getKey();
        JSONArray jsonArray;
        switch (index) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
            case 19:
            case 20:
            case 21:
                return viewBinary(bytes[index / 8 + offset], index % 8);
            case 22:
                return countBits(bytes, offset, index, 8);
//            case 22:
//            case 23:
//            case 24:
//                return viewBinary(bytes[index / 8 + offset], index % 8);
//            case 25:
//                jsonArray = new JSONArray();
//                jsonArray.add(countBits(bytes, offset, index, 4));
//                jsonArray.add(countBits(bytes, offset, index + 4, 4));
//                return jsonArray;
//            case 33:
//                jsonArray = new JSONArray();
//                jsonArray.add(countBits(bytes, offset, index, 4));
//                jsonArray.add(viewBinary(bytes[(index + 4)  / 8 + offset], (index + 4)  % 8));
//                return jsonArray;
//            case 38:
//                jsonArray = new JSONArray();
//                jsonArray.add(countBits(bytes, offset, index, 4));
//                jsonArray.add(countBits(bytes, offset, index + 4, 4));
//                return jsonArray;
//            case 46:
//                jsonArray = new JSONArray();
//                jsonArray.add(countBits(bytes, offset, index, 4));
//                jsonArray.add(viewBinary(bytes[(index + 4)  / 8 + offset], (index + 4)  % 8));
//                return jsonArray;
            default:
                LogUtil.d(TAG, "数据下标错误");
                break;
        }
        return null;
    }

    @Override
    public HashMap<Integer, MyPair<Integer>> getFields() {
        return fields;
    }
}
