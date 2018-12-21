package com.hasee.minibuslocalhost.transmit.Class;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hasee.minibuslocalhost.transmit.transmit;
import com.hasee.minibuslocalhost.util.LogUtil;

import java.util.HashMap;

import static com.hasee.minibuslocalhost.transmit.transmit.bytesToHex;
import static com.hasee.minibuslocalhost.transmit.transmit.setBit;
import static com.hasee.minibuslocalhost.transmit.transmit.viewBinary;

public class BCM1 extends BaseClass {
    //字段

    private MyPair<Boolean> BCM_Dig_Ord_HandLightCtr = new MyPair<>(false, 1, 1); // 手势灯光控制信号
    private MyPair<Boolean> BCM_Flg_Stat_LeftTurningLamp = new MyPair<>(false, 107, 1); // 左转向状态信号
    private MyPair<Boolean> BCM_Flg_Stat_RightTurningLamp = new MyPair<>(false, 3, 1); // 右转向状态信号
    private MyPair<Boolean> BCM_Flg_Stat_HandLightCtr = new MyPair<>(false, 1, 1); // 手势灯光控制状态信号
    private MyPair<Boolean> BCM_Flg_Stat_HighBeam = new MyPair<>(false, 1, 1); // 远光灯状态信号
    private MyPair<Boolean> BCM_Flg_Stat_LowBeam = new MyPair<>(false, 1, 1); // 近光灯状态信号
    private MyPair<Boolean> BCM_Flg_Stat_RearFogLamp = new MyPair<>(false, 1, 1); // 后雾灯状态信号
    private MyPair<Boolean> BCM_Flg_Stat_DangerAlarmLamp = new MyPair<>(false, 1, 1); // 危险报警灯控制（双闪）状态信号
    private MyPair<Boolean> BCM_Flg_Stat_BrakeLamp = new MyPair<>(false, 1, 1); // 制动灯状态信号
    private MyPair<Boolean> BCM_Flg_Stat_BackupLamp = new MyPair<>(false, 1, 1); // 倒车灯状态信号
    private MyPair<Boolean> BCM_Flg_Stat_SeatSensor1 = new MyPair<>(false, 1, 1); // 座椅传感器1
    private MyPair<Boolean> BCM_Flg_Stat_SeatSensor2 = new MyPair<>(false, 1, 1); // 座椅传感器2
    private MyPair<Boolean> BCM_Flg_Stat_SeatSensor3 = new MyPair<>(false, 1, 1); // 座椅传感器3
    private MyPair<Boolean> BCM_Flg_Stat_SeatSensor4 = new MyPair<>(false, 1, 1); // 座椅传感器4
    private MyPair<Boolean> BCM_Flg_Stat_SeatSensor5 = new MyPair<>(false, 1, 1); // 座椅传感器5
    private MyPair<Boolean> BCM_Flg_Stat_SeatSensor6 = new MyPair<>(false, 1, 1); // 座椅传感器6
    private MyPair<Boolean> BCM_Flg_Stat_BeltsSensor1 = new MyPair<>(false, 1, 1); // 安全带传感器1
    private MyPair<Boolean> BCM_Flg_Stat_BeltsSensor2 = new MyPair<>(false, 1, 1); // 安全带传感器2
    private MyPair<Boolean> BCM_Flg_Stat_BeltsSensor3 = new MyPair<>(false, 1, 1); // 安全带传感器3
    private MyPair<Boolean> BCM_Flg_Stat_BeltsSensor4 = new MyPair<>(false, 1, 1); // 安全带传感器4
    private MyPair<Boolean> BCM_Flg_Stat_BeltsSensor5 = new MyPair<>(false, 1, 1); // 安全带传感器5
    private MyPair<Boolean> BCM_Flg_Stat_BeltsSensor6 = new MyPair<>(false, 1, 1); // 安全带传感器6

    // 属性
    private final static int FIELDS_LENGTH = 22;
    private HashMap<Integer, MyPair<Boolean>> fields = new HashMap<Integer, MyPair<Boolean>>() {{
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
        for (int i = 0; i < FIELDS_LENGTH; i++)
            setBit(bytes, 5, i, 1, fields.get(i).getFirst());
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        LogUtil.d("BCM1", bytesToHex(bytes));
        LogUtil.d("BCM1", bytesToHex(this.bytes));
        for (int i = 0; i < bytes.length - 5; i++) {
            for (int j = 0; j < 8; j++) {
                if (viewBinary(this.bytes[i + 5], j) != viewBinary(bytes[i + 5], j)) {
                    JSONObject jsonObject = new JSONObject();
                    // id
                    jsonObject.put("id", fields.get(i * 8 + j).getSecond().first);
                    // data
                    JSONArray jsonArray = new JSONArray();
                    fields.get(i * 8 + j).setFirst(viewBinary(bytes[i + 5], j));
                    jsonArray.add(viewBinary(bytes[i + 5], j));
                    jsonObject.put("data", jsonArray);
                    // target
                    int target = fields.get(i * 8 + j).getSecond().second;
                    LogUtil.d("BCM1", jsonObject.toJSONString());
                    transmit.getInstance().callback(jsonObject, target);
                }
            }
        }
        System.arraycopy(bytes, 0, this.bytes, 0, bytes.length);
        LogUtil.d("BCM1", bytesToHex(this.bytes));
    }

    @Override
    public HashMap<Integer, MyPair<Boolean>> getFields() {
        return fields;
    }

    public boolean isBCM_Dig_Ord_HandLightCtr() {
        return BCM_Dig_Ord_HandLightCtr.getFirst();
    }

    public void setBCM_Dig_Ord_HandLightCtr(boolean BCM_Dig_Ord_HandLightCtr) {
        this.BCM_Dig_Ord_HandLightCtr.setFirst(BCM_Dig_Ord_HandLightCtr);
    }

    public boolean isBCM_Flg_Stat_LeftTurningLamp() {
        return BCM_Flg_Stat_LeftTurningLamp.getFirst();
    }

    public void setBCM_Flg_Stat_LeftTurningLamp(boolean BCM_Flg_Stat_LeftTurningLamp) {
        this.BCM_Flg_Stat_LeftTurningLamp.setFirst(BCM_Flg_Stat_LeftTurningLamp);
    }

    public boolean isBCM_Flg_Stat_RightTurningLamp() {
        return BCM_Flg_Stat_RightTurningLamp.getFirst();
    }

    public void setBCM_Flg_Stat_RightTurningLamp(boolean BCM_Flg_Stat_RightTurningLamp) {
        this.BCM_Flg_Stat_RightTurningLamp.setFirst(BCM_Flg_Stat_RightTurningLamp);
    }

    public boolean isBCM_Flg_Stat_HandLightCtr() {
        return BCM_Flg_Stat_HandLightCtr.getFirst();
    }

    public void setBCM_Flg_Stat_HandLightCtr(boolean BCM_Flg_Stat_HandLightCtr) {
        this.BCM_Flg_Stat_HandLightCtr.setFirst(BCM_Flg_Stat_HandLightCtr);
    }

    public boolean isBCM_Flg_Stat_HighBeam() {
        return BCM_Flg_Stat_HighBeam.getFirst();
    }

    public void setBCM_Flg_Stat_HighBeam(boolean BCM_Flg_Stat_HighBeam) {
        this.BCM_Flg_Stat_HighBeam.setFirst(BCM_Flg_Stat_HighBeam);
    }

    public boolean isBCM_Flg_Stat_LowBeam() {
        return BCM_Flg_Stat_LowBeam.getFirst();
    }

    public void setBCM_Flg_Stat_LowBeam(boolean BCM_Flg_Stat_LowBeam) {
        this.BCM_Flg_Stat_LowBeam.setFirst(BCM_Flg_Stat_LowBeam);
    }

    public boolean isBCM_Flg_Stat_RearFogLamp() {
        return BCM_Flg_Stat_RearFogLamp.getFirst();
    }

    public void setBCM_Flg_Stat_RearFogLamp(boolean BCM_Flg_Stat_RearFogLamp) {
        this.BCM_Flg_Stat_RearFogLamp.setFirst(BCM_Flg_Stat_RearFogLamp);
    }

    public boolean isBCM_Flg_Stat_DangerAlarmLamp() {
        return BCM_Flg_Stat_DangerAlarmLamp.getFirst();
    }

    public void setBCM_Flg_Stat_DangerAlarmLamp(boolean BCM_Flg_Stat_DangerAlarmLamp) {
        this.BCM_Flg_Stat_DangerAlarmLamp.setFirst(BCM_Flg_Stat_DangerAlarmLamp);
    }

    public boolean isBCM_Flg_Stat_BrakeLamp() {
        return BCM_Flg_Stat_BrakeLamp.getFirst();
    }

    public void setBCM_Flg_Stat_BrakeLamp(boolean BCM_Flg_Stat_BrakeLamp) {
        this.BCM_Flg_Stat_BrakeLamp.setFirst(BCM_Flg_Stat_BrakeLamp);
    }

    public boolean isBCM_Flg_Stat_BackupLamp() {
        return BCM_Flg_Stat_BackupLamp.getFirst();
    }

    public void setBCM_Flg_Stat_BackupLamp(boolean BCM_Flg_Stat_BackupLamp) {
        this.BCM_Flg_Stat_BackupLamp.setFirst(BCM_Flg_Stat_BackupLamp);
    }

    public boolean isBCM_Flg_Stat_SeatSensor1() {
        return BCM_Flg_Stat_SeatSensor1.getFirst();
    }

    public void setBCM_Flg_Stat_SeatSensor1(boolean BCM_Flg_Stat_SeatSensor1) {
        this.BCM_Flg_Stat_SeatSensor1.setFirst(BCM_Flg_Stat_SeatSensor1);
    }

    public boolean isBCM_Flg_Stat_SeatSensor2() {
        return BCM_Flg_Stat_SeatSensor2.getFirst();
    }

    public void setBCM_Flg_Stat_SeatSensor2(boolean BCM_Flg_Stat_SeatSensor2) {
        this.BCM_Flg_Stat_SeatSensor2.setFirst(BCM_Flg_Stat_SeatSensor2);
    }

    public boolean isBCM_Flg_Stat_SeatSensor3() {
        return BCM_Flg_Stat_SeatSensor3.getFirst();
    }

    public void setBCM_Flg_Stat_SeatSensor3(boolean BCM_Flg_Stat_SeatSensor3) {
        this.BCM_Flg_Stat_SeatSensor3.setFirst(BCM_Flg_Stat_SeatSensor3);
    }

    public boolean isBCM_Flg_Stat_SeatSensor4() {
        return BCM_Flg_Stat_SeatSensor4.getFirst();
    }

    public void setBCM_Flg_Stat_SeatSensor4(boolean BCM_Flg_Stat_SeatSensor4) {
        this.BCM_Flg_Stat_SeatSensor4.setFirst(BCM_Flg_Stat_SeatSensor4);
    }

    public boolean isBCM_Flg_Stat_SeatSensor5() {
        return BCM_Flg_Stat_SeatSensor5.getFirst();
    }

    public void setBCM_Flg_Stat_SeatSensor5(boolean BCM_Flg_Stat_SeatSensor5) {
        this.BCM_Flg_Stat_SeatSensor5.setFirst(BCM_Flg_Stat_SeatSensor5);
    }

    public boolean isBCM_Flg_Stat_SeatSensor6() {
        return BCM_Flg_Stat_SeatSensor6.getFirst();
    }

    public void setBCM_Flg_Stat_SeatSensor6(boolean BCM_Flg_Stat_SeatSensor6) {
        this.BCM_Flg_Stat_SeatSensor6.setFirst(BCM_Flg_Stat_SeatSensor6);
    }

    public boolean isBCM_Flg_Stat_BeltsSensor1() {
        return BCM_Flg_Stat_BeltsSensor1.getFirst();
    }

    public void setBCM_Flg_Stat_BeltsSensor1(boolean BCM_Flg_Stat_BeltsSensor1) {
        this.BCM_Flg_Stat_BeltsSensor1.setFirst(BCM_Flg_Stat_BeltsSensor1);
    }

    public boolean isBCM_Flg_Stat_BeltsSensor2() {
        return BCM_Flg_Stat_BeltsSensor2.getFirst();
    }

    public void setBCM_Flg_Stat_BeltsSensor2(boolean BCM_Flg_Stat_BeltsSensor2) {
        this.BCM_Flg_Stat_BeltsSensor2.setFirst(BCM_Flg_Stat_BeltsSensor2);
    }

    public boolean isBCM_Flg_Stat_BeltsSensor3() {
        return BCM_Flg_Stat_BeltsSensor3.getFirst();
    }

    public void setBCM_Flg_Stat_BeltsSensor3(boolean BCM_Flg_Stat_BeltsSensor3) {
        this.BCM_Flg_Stat_BeltsSensor3.setFirst(BCM_Flg_Stat_BeltsSensor3);
    }

    public boolean isBCM_Flg_Stat_BeltsSensor4() {
        return BCM_Flg_Stat_BeltsSensor4.getFirst();
    }

    public void setBCM_Flg_Stat_BeltsSensor4(boolean BCM_Flg_Stat_BeltsSensor4) {
        this.BCM_Flg_Stat_BeltsSensor4.setFirst(BCM_Flg_Stat_BeltsSensor4);
    }

    public boolean isBCM_Flg_Stat_BeltsSensor5() {
        return BCM_Flg_Stat_BeltsSensor5.getFirst();
    }

    public void setBCM_Flg_Stat_BeltsSensor5(boolean BCM_Flg_Stat_BeltsSensor5) {
        this.BCM_Flg_Stat_BeltsSensor5.setFirst(BCM_Flg_Stat_BeltsSensor5);
    }

    public boolean isBCM_Flg_Stat_BeltsSensor6() {
        return BCM_Flg_Stat_BeltsSensor6.getFirst();
    }

    public void setBCM_Flg_Stat_BeltsSensor6(boolean BCM_Flg_Stat_BeltsSensor6) {
        this.BCM_Flg_Stat_BeltsSensor6.setFirst(BCM_Flg_Stat_BeltsSensor6);
    }
}
