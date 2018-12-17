package com.hasee.minibuslocalhost.transmit.Class;


import android.os.Message;

import com.hasee.minibuslocalhost.util.MyHandler;

import java.lang.reflect.Field;

public class BCM1 extends BaseClass{
    //字段
    private boolean BCM_Dig_Ord_HandLightCtr = false; // 手势灯光控制信号
    private boolean BCM_Flg_Stat_LeftTurningLamp = false; // 左转向状态信号
    private boolean BCM_Flg_Stat_RightTurningLamp = false; // 右转向状态信号
    private boolean BCM_Flg_Stat_HandLightCtr = false; // 手势灯光控制状态信号
    private boolean BCM_Flg_Stat_HighBeam = false; // 远光灯状态信号
    private boolean BCM_Flg_Stat_LowBeam = false; // 近光灯状态信号
    private boolean BCM_Flg_Stat_RearFogLamp = false; // 后雾灯状态信号
    private boolean BCM_Flg_Stat_DangerAlarmLamp = false; // 危险报警灯控制（双闪）状态信号
    private boolean BCM_Flg_Stat_BrakeLamp = false; // 制动灯状态信号
    private boolean BCM_Flg_Stat_BackupLamp = false; // 倒车灯状态信号
    private boolean BCM_Flg_Stat_SeatSensor1 = false; // 座椅传感器1
    private boolean BCM_Flg_Stat_SeatSensor2 = false; // 座椅传感器2
    private boolean BCM_Flg_Stat_SeatSensor3 = false; // 座椅传感器3
    private boolean BCM_Flg_Stat_SeatSensor4 = false; // 座椅传感器4
    private boolean BCM_Flg_Stat_SeatSensor5 = false; // 座椅传感器5
    private boolean BCM_Flg_Stat_SeatSensor6 = false; // 座椅传感器6
    private boolean BCM_Flg_Stat_BeltsSensor1 = false; // 安全带传感器1
    private boolean BCM_Flg_Stat_BeltsSensor2 = false; // 安全带传感器2
    private boolean BCM_Flg_Stat_BeltsSensor3 = false; // 安全带传感器3
    private boolean BCM_Flg_Stat_BeltsSensor4 = false; // 安全带传感器4
    private boolean BCM_Flg_Stat_BeltsSensor5 = false; // 安全带传感器5
    private boolean BCM_Flg_Stat_BeltsSensor6 = false; // 安全带传感器6


    // 属性
    private final static int  FIELDS_LENGTH = 22;
    private Field[] fields = this.getClass().getDeclaredFields();
    private byte[] bytes = new byte[13];

    public BCM1(){
    }

    public byte[] getBytes() {
        try {
            for (int i = 0; i < FIELDS_LENGTH; i++)
                setByte(i, fields[i].getBoolean(this));
            return bytes;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void setBytes(byte[] bytes) {
        for(int i = 0; i < bytes.length - 5; i++){
            for(int j = 0; i * 8 + j < FIELDS_LENGTH; j++){
                if(viewBinary(this.bytes[i + 5], j) != viewBinary(bytes[i + 5], j)){

                }
            }
        }
        this.bytes = bytes;
    }

    // 设置某一位
    private void setByte(int offset, boolean flag) {
        int i = offset / 8;
        int j = offset % 8;
        if (flag)
            bytes[5 + i] |= (0x01 << j);
        else
            bytes[5 + i] |= ~(0x01 << j);
    }

    // 查看一个Byte的某位是否为1
    private boolean viewBinary(byte Byte, int position) {
        return (Byte & 0x01 << position) != 0;
    }

    public boolean isBCM_Dig_Ord_HandLightCtr() {
        return BCM_Dig_Ord_HandLightCtr;
    }

    public void setBCM_Dig_Ord_HandLightCtr(boolean BCM_Dig_Ord_HandLightCtr) {
        this.BCM_Dig_Ord_HandLightCtr = BCM_Dig_Ord_HandLightCtr;
    }

    public boolean isBCM_Flg_Stat_LeftTurningLamp() {
        return BCM_Flg_Stat_LeftTurningLamp;
    }

    public void setBCM_Flg_Stat_LeftTurningLamp(boolean BCM_Flg_Stat_LeftTurningLamp) {
        this.BCM_Flg_Stat_LeftTurningLamp = BCM_Flg_Stat_LeftTurningLamp;
    }

    public boolean isBCM_Flg_Stat_RightTurningLamp() {
        return BCM_Flg_Stat_RightTurningLamp;
    }

    public void setBCM_Flg_Stat_RightTurningLamp(boolean BCM_Flg_Stat_RightTurningLamp) {
        this.BCM_Flg_Stat_RightTurningLamp = BCM_Flg_Stat_RightTurningLamp;
    }

    public boolean isBCM_Flg_Stat_HandLightCtr() {
        return BCM_Flg_Stat_HandLightCtr;
    }

    public void setBCM_Flg_Stat_HandLightCtr(boolean BCM_Flg_Stat_HandLightCtr) {
        this.BCM_Flg_Stat_HandLightCtr = BCM_Flg_Stat_HandLightCtr;
    }

    public boolean isBCM_Flg_Stat_HighBeam() {
        return BCM_Flg_Stat_HighBeam;
    }

    public void setBCM_Flg_Stat_HighBeam(boolean BCM_Flg_Stat_HighBeam) {
        this.BCM_Flg_Stat_HighBeam = BCM_Flg_Stat_HighBeam;
    }

    public boolean isBCM_Flg_Stat_LowBeam() {
        return BCM_Flg_Stat_LowBeam;
    }

    public void setBCM_Flg_Stat_LowBeam(boolean BCM_Flg_Stat_LowBeam) {
        this.BCM_Flg_Stat_LowBeam = BCM_Flg_Stat_LowBeam;
    }

    public boolean isBCM_Flg_Stat_RearFogLamp() {
        return BCM_Flg_Stat_RearFogLamp;
    }

    public void setBCM_Flg_Stat_RearFogLamp(boolean BCM_Flg_Stat_RearFogLamp) {
        this.BCM_Flg_Stat_RearFogLamp = BCM_Flg_Stat_RearFogLamp;
    }

    public boolean isBCM_Flg_Stat_DangerAlarmLamp() {
        return BCM_Flg_Stat_DangerAlarmLamp;
    }

    public void setBCM_Flg_Stat_DangerAlarmLamp(boolean BCM_Flg_Stat_DangerAlarmLamp) {
        this.BCM_Flg_Stat_DangerAlarmLamp = BCM_Flg_Stat_DangerAlarmLamp;
    }

    public boolean isBCM_Flg_Stat_BrakeLamp() {
        return BCM_Flg_Stat_BrakeLamp;
    }

    public void setBCM_Flg_Stat_BrakeLamp(boolean BCM_Flg_Stat_BrakeLamp) {
        this.BCM_Flg_Stat_BrakeLamp = BCM_Flg_Stat_BrakeLamp;
    }

    public boolean isBCM_Flg_Stat_BackupLamp() {
        return BCM_Flg_Stat_BackupLamp;
    }

    public void setBCM_Flg_Stat_BackupLamp(boolean BCM_Flg_Stat_BackupLamp) {
        this.BCM_Flg_Stat_BackupLamp = BCM_Flg_Stat_BackupLamp;
    }

    public boolean isBCM_Flg_Stat_SeatSensor1() {
        return BCM_Flg_Stat_SeatSensor1;
    }

    public void setBCM_Flg_Stat_SeatSensor1(boolean BCM_Flg_Stat_SeatSensor1) {
        this.BCM_Flg_Stat_SeatSensor1 = BCM_Flg_Stat_SeatSensor1;
    }

    public boolean isBCM_Flg_Stat_SeatSensor2() {
        return BCM_Flg_Stat_SeatSensor2;
    }

    public void setBCM_Flg_Stat_SeatSensor2(boolean BCM_Flg_Stat_SeatSensor2) {
        this.BCM_Flg_Stat_SeatSensor2 = BCM_Flg_Stat_SeatSensor2;
    }

    public boolean isBCM_Flg_Stat_SeatSensor3() {
        return BCM_Flg_Stat_SeatSensor3;
    }

    public void setBCM_Flg_Stat_SeatSensor3(boolean BCM_Flg_Stat_SeatSensor3) {
        this.BCM_Flg_Stat_SeatSensor3 = BCM_Flg_Stat_SeatSensor3;
    }

    public boolean isBCM_Flg_Stat_SeatSensor4() {
        return BCM_Flg_Stat_SeatSensor4;
    }

    public void setBCM_Flg_Stat_SeatSensor4(boolean BCM_Flg_Stat_SeatSensor4) {
        this.BCM_Flg_Stat_SeatSensor4 = BCM_Flg_Stat_SeatSensor4;
    }

    public boolean isBCM_Flg_Stat_SeatSensor5() {
        return BCM_Flg_Stat_SeatSensor5;
    }

    public void setBCM_Flg_Stat_SeatSensor5(boolean BCM_Flg_Stat_SeatSensor5) {
        this.BCM_Flg_Stat_SeatSensor5 = BCM_Flg_Stat_SeatSensor5;
    }

    public boolean isBCM_Flg_Stat_SeatSensor6() {
        return BCM_Flg_Stat_SeatSensor6;
    }

    public void setBCM_Flg_Stat_SeatSensor6(boolean BCM_Flg_Stat_SeatSensor6) {
        this.BCM_Flg_Stat_SeatSensor6 = BCM_Flg_Stat_SeatSensor6;
    }

    public boolean isBCM_Flg_Stat_BeltsSensor1() {
        return BCM_Flg_Stat_BeltsSensor1;
    }

    public void setBCM_Flg_Stat_BeltsSensor1(boolean BCM_Flg_Stat_BeltsSensor1) {
        this.BCM_Flg_Stat_BeltsSensor1 = BCM_Flg_Stat_BeltsSensor1;
    }

    public boolean isBCM_Flg_Stat_BeltsSensor2() {
        return BCM_Flg_Stat_BeltsSensor2;
    }

    public void setBCM_Flg_Stat_BeltsSensor2(boolean BCM_Flg_Stat_BeltsSensor2) {
        this.BCM_Flg_Stat_BeltsSensor2 = BCM_Flg_Stat_BeltsSensor2;
    }

    public boolean isBCM_Flg_Stat_BeltsSensor3() {
        return BCM_Flg_Stat_BeltsSensor3;
    }

    public void setBCM_Flg_Stat_BeltsSensor3(boolean BCM_Flg_Stat_BeltsSensor3) {
        this.BCM_Flg_Stat_BeltsSensor3 = BCM_Flg_Stat_BeltsSensor3;
    }

    public boolean isBCM_Flg_Stat_BeltsSensor4() {
        return BCM_Flg_Stat_BeltsSensor4;
    }

    public void setBCM_Flg_Stat_BeltsSensor4(boolean BCM_Flg_Stat_BeltsSensor4) {
        this.BCM_Flg_Stat_BeltsSensor4 = BCM_Flg_Stat_BeltsSensor4;
    }

    public boolean isBCM_Flg_Stat_BeltsSensor5() {
        return BCM_Flg_Stat_BeltsSensor5;
    }

    public void setBCM_Flg_Stat_BeltsSensor5(boolean BCM_Flg_Stat_BeltsSensor5) {
        this.BCM_Flg_Stat_BeltsSensor5 = BCM_Flg_Stat_BeltsSensor5;
    }

    public boolean isBCM_Flg_Stat_BeltsSensor6() {
        return BCM_Flg_Stat_BeltsSensor6;
    }

    public void setBCM_Flg_Stat_BeltsSensor6(boolean BCM_Flg_Stat_BeltsSensor6) {
        this.BCM_Flg_Stat_BeltsSensor6 = BCM_Flg_Stat_BeltsSensor6;
    }
}
