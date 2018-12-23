package com.hasee.minibuslocalhost.transmit.Class;

import com.hasee.minibuslocalhost.util.LogUtil;

import java.util.HashMap;
import java.util.Map;

import static com.hasee.minibuslocalhost.util.ByteUtil.bytesToHex;
import static com.hasee.minibuslocalhost.util.ByteUtil.setBit;

public class HMI extends BaseClass{
    private final static String TAG = "HMI";

    // flag
    public static final int HMI_Dig_Ord_HighBeam = 0;
    public static final int HMI_Dig_Ord_LowBeam = 1;
    public static final int HMI_Dig_Ord_LeftTurningLamp = 2;
    public static final int HMI_Dig_Ord_RightTurningLamp = 3;
    public static final int HMI_Dig_Ord_RearFogLamp = 4;
    public static final int HMI_Dig_Ord_DoorLock = 6;
    public static final int HMI_Dig_Ord_Alarm = 7;
    public static final int HMI_Dig_Ord_drive_model = 8;
    public static final int HMI_Dig_Ord_air_model = 10;
    public static final int HMI_Dig_Ord_air_grade = 12;
    // status
    // 灯光和门
    public static final boolean OFF = false; // 开
    public static final boolean ON = true; // 关
    // 驾驶控制
    public static final int DRIVE_MODEL_AUTO = 0; // 自动
    public static final int DRIVE_MODEL_REMOTE = 1; // 远程
    // 空调
    public static final int AIR_MODEL_COOL = 0; // 制冷
    public static final int AIR_MODEL_HEAT = 1; // 制热
    public static final int AIR_MODEL_DEMIST = 2; // 除雾
    public static final int AIR_GRADE_OFF = 0; // 关闭
    public static final int AIR_GRADE_FIRST_GEAR  = 1; // 1挡
    public static final int AIR_GRADE_SECOND_GEAR  = 2; // 2挡
    public static final int AIR_GRADE_THIRD_GEAR  = 3; // 3挡
    public static final int AIR_GRADE_FOURTH_GEAR  = 4; // 4挡
    public static final int AIR_GRADE_FIVE_GEAR  = 5; // 5挡


    public HMI(){}

    private byte[] bytes = {0x00, 0x00, 0x00, 0x03, (byte) 0x83, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00};

    public void changeStatus(int flag, Object status){
        switch (flag){
            case HMI_Dig_Ord_HighBeam:
            case HMI_Dig_Ord_LowBeam:
            case HMI_Dig_Ord_LeftTurningLamp:
            case HMI_Dig_Ord_RightTurningLamp:
            case HMI_Dig_Ord_RearFogLamp:
            case HMI_Dig_Ord_DoorLock:
            case HMI_Dig_Ord_Alarm:
                setBit(bytes, 5, flag,1, status);
                break;
            case HMI_Dig_Ord_drive_model:
                setBit(bytes, 5, flag,2, status);
                break;
            case HMI_Dig_Ord_air_model:
                setBit(bytes, 5, flag,2, status);
                break;
            case HMI_Dig_Ord_air_grade:
                setBit(bytes, 5, flag,3, status);
                break;
            default:
                LogUtil.d(TAG, "消息转换错误");
                break;
        }
        LogUtil.d(TAG, bytesToHex(bytes));
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
    public Object getValue(Map.Entry<Integer, MyPair<Integer>> entry, byte[] bytes) {
        return null;
    }

    @Override
    public HashMap<Integer, MyPair<Integer>> getFields() {
        return null;
    }
}
