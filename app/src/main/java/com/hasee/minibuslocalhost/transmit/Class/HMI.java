package com.hasee.minibuslocalhost.transmit.Class;

import com.hasee.minibuslocalhost.util.LogUtil;

import java.util.HashMap;
import java.util.Map;

import static com.hasee.minibuslocalhost.util.ByteUtil.bytesToHex;
import static com.hasee.minibuslocalhost.util.ByteUtil.setBit;

public class HMI extends BaseClass {
    private final static String TAG = "HMI";
    private final static int offset = 2;

    //leftFragment
    public static final int HMI_leftFragmentHighBeam = 0;//远光灯
    public static final int HMI_leftFragmentLowBeam = 1;//近光灯
    public static final int HMI_leftFragmentLeftLight = 2;//左转向灯
    public static final int HMI_leftFragmentRightLight = 3;//右转向灯
    public static final int HMI_leftFragmentBackFogLight = 4;//后雾灯
    public static final int HMI_leftFragmentFrontFogLight = 5;//前雾灯
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
    public static final int AIR_GRADE_FIRST_GEAR = 1; // 1挡
    public static final int AIR_GRADE_SECOND_GEAR = 2; // 2挡
    public static final int AIR_GRADE_THIRD_GEAR = 3; // 3挡
    public static final int AIR_GRADE_FOURTH_GEAR = 4; // 4挡
    public static final int AIR_GRADE_FIVE_GEAR = 5; // 5挡


    public HMI() {
    }

    private byte[] bytes = {0x03, (byte) 0x83, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x02, 0x02};

    public void changeStatus(int flag, Object status) {
        switch (flag) {
            case HMI_leftFragmentHighBeam:
                setBit(bytes, offset, flag, 1, status);
                setBit(bytes, offset, HMI_leftFragmentLowBeam, 1, !((Boolean)status));
                break;
            case HMI_leftFragmentLowBeam:
                setBit(bytes, offset, flag, 1, status);
                setBit(bytes, offset, HMI_leftFragmentHighBeam, 1, !((Boolean)status));
                break;
            case HMI_leftFragmentLeftLight:
                setBit(bytes, offset, flag, 1, status);
                setBit(bytes, offset, HMI_leftFragmentRightLight, 1, !((Boolean)status));
                break;
            case HMI_leftFragmentRightLight:
                setBit(bytes, offset, flag, 1, status);
                setBit(bytes, offset, HMI_leftFragmentLeftLight, 1, !((Boolean)status));
                break;
            case HMI_leftFragmentBackFogLight:
                setBit(bytes, offset, flag, 1, status);
//                setBit(bytes, offset, HMI_leftFragmentFrontFogLight, 1, !((Boolean)status));
                break;
            case HMI_leftFragmentFrontFogLight:
                setBit(bytes, offset, flag, 1, status);
//                setBit(bytes, offset, HMI_leftFragmentBackFogLight, 1, !((Boolean)status));
                break;
            case HMI_Dig_Ord_DoorLock:
            case HMI_Dig_Ord_Alarm:
                setBit(bytes, offset, flag, 1, status);
                break;
            case HMI_Dig_Ord_drive_model:
                setBit(bytes, offset, flag, 2, status);
                break;
            case HMI_Dig_Ord_air_model:
                setBit(bytes, offset, flag, 2, status);
                break;
            case HMI_Dig_Ord_air_grade:
                setBit(bytes, offset, flag, 3, status);
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
