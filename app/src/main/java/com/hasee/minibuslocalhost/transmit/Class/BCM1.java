package com.hasee.minibuslocalhost.transmit.Class;


import android.util.Pair;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hasee.minibuslocalhost.activity.MainActivity;
import com.hasee.minibuslocalhost.transmit.Transmit;
import com.hasee.minibuslocalhost.util.LogUtil;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static com.hasee.minibuslocalhost.transmit.Transmit.bytesToHex;
import static com.hasee.minibuslocalhost.transmit.Transmit.viewBinary;

public class BCM1 extends BaseClass {
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

    }};
    private byte[] bytes = {0x00, 0x00, 0x00, 0x03, 0x61, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00};

    public BCM1() {
    }

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        LogUtil.d("BCM1", "bytes:" + bytesToHex(bytes));
        LogUtil.d("BCM1", "this.bytes:" + bytesToHex(this.bytes));
        // 如果相同则直接返回
        if(!Arrays.equals(bytes,this.bytes)) {
            LogUtil.d("BCM1", "数据相同");
            return;
        }
//        for (int i = 0; i < bytes.length - 5; i++) {
//            for (int j = 0; j < 8; j++) {
//                if (viewBinary(this.bytes[i + 5], j) != viewBinary(bytes[i + 5], j)) {
//                    JSONObject jsonObject = new JSONObject();
//                    // id
//                    jsonObject.put("id", fields.get(i * 8 + j).getSecond().first);
//                    // data
//                    JSONArray jsonArray = new JSONArray();
//                    jsonArray.add(viewBinary(bytes[i + 5], j));
//                    jsonObject.put("data", jsonArray);
//                    // target
//                    int target = fields.get(i * 8 + j).getSecond().second;
//                    LogUtil.d("BCM1", "jsonObject:" + jsonObject.toJSONString());
//                    Transmit.getInstance().callback(jsonObject, target);
//                }
//            }
//        }
//        System.arraycopy(bytes, 0, this.bytes, 0, bytes.length);
        for (Map.Entry<Integer, MyPair<Integer>> entry : fields.entrySet()) {
            int index = entry.getKey();
            int length = entry.getValue().getFirst();
            boolean flag = true;
            for(int i = index; i < index + length; i++) {
                if (viewBinary(this.bytes[i / 8 + 5], i % 8) != viewBinary(bytes[i / 8 + 5], i % 8)) {
                    flag = false;
                    break;
                }
            }
            if(flag){
                Object data = getValue(entry.getKey());
                JSONObject jsonObject = new JSONObject();
                // id
                jsonObject.put("id", entry.getValue().getSecond().first);
                // data
                jsonObject.put("data", new JSONArray().add(data));
                // target
                int target = entry.getValue().getSecond().second;
                // 发回主函数
                Transmit.getInstance().callback(jsonObject, target);
                // debug
                LogUtil.d("BCM1", "jsonObject:" + jsonObject.toJSONString());
            }
        }
        System.arraycopy(bytes, 0, this.bytes, 0, bytes.length);
        LogUtil.d("BCM1", "this.bytes:" + bytesToHex(this.bytes));
    }

    private Object getValue(int index) {
        return null;
    }

    @Override
    public HashMap<Integer, MyPair<Integer>> getFields() {
        return fields;
    }
}
