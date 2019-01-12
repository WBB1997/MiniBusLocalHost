package com.hasee.minibuslocalhost.transmit.Class;

import com.hasee.minibuslocalhost.bean.MsgCommand;
import com.hasee.minibuslocalhost.util.LogUtil;

import java.util.HashMap;
import java.util.Map;

import static com.hasee.minibuslocalhost.util.ByteUtil.bytesToHex;
import static com.hasee.minibuslocalhost.util.ByteUtil.setBit;

public class HMI extends BaseClass {
    private final static String TAG = "HMI";
    private final static int offset = 2;

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

    //
    private Map<String, ? super BaseClass> NAME_AND_CLASS;


    public HMI() {
    }

    public void setNAME_AND_CLASS(Map<String, ? super BaseClass> NAME_AND_CLASS) {
        this.NAME_AND_CLASS = NAME_AND_CLASS;
    }

    private byte[] bytes = {(byte) 0xFF, (byte) 0xAA, 0x03, (byte) 0x83, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x02, 0x02};

    public void changeStatus(int flag, Object status) {
        switch (flag) {
            case MsgCommand.HMI_Dig_Ord_HighBeam:
                setBit(bytes, offset, 0, 1, status);
                setBit(bytes, offset, 1, 1, (Boolean) status ? false : false);
                ((BCM1) NAME_AND_CLASS.get("BCM1")).setBytes(0, 4, 1, status);
                ((BCM1) NAME_AND_CLASS.get("BCM1")).setBytes(0, 5, 1, (Boolean) status ? false : false);
                break;
            case MsgCommand.HMI_Dig_Ord_LowBeam:
                setBit(bytes, offset, 1, 1, status);
                setBit(bytes, offset, 0, 1, (Boolean) status ? false : false);
                ((BCM1) NAME_AND_CLASS.get("BCM1")).setBytes(0, 5, 1, status);
                ((BCM1) NAME_AND_CLASS.get("BCM1")).setBytes(0, 4, 1, (Boolean) status ? false : false);
                break;
            case MsgCommand.HMI_Dig_Ord_LeftTurningLamp:
                setBit(bytes, offset, 2, 1, status);
                setBit(bytes, offset, 3, 1, (Boolean) status ? false : false);
                ((BCM1) NAME_AND_CLASS.get("BCM1")).setBytes(0, 1, 1, status);
                ((BCM1) NAME_AND_CLASS.get("BCM1")).setBytes(0, 2, 1, (Boolean) status ? false : false);
                break;
            case MsgCommand.HMI_Dig_Ord_RightTurningLamp:
                setBit(bytes, offset, 3, 1, status);
                setBit(bytes, offset, 2, 1, (Boolean) status ? false : false);
                ((BCM1) NAME_AND_CLASS.get("BCM1")).setBytes(0, 2, 1, status);
                ((BCM1) NAME_AND_CLASS.get("BCM1")).setBytes(0, 1, 1, (Boolean) status ? false : false);
                break;
            case MsgCommand.HMI_Dig_Ord_RearFogLamp:
                ((BCM1) NAME_AND_CLASS.get("BCM1")).setBytes(0, 6, 1, status);
                setBit(bytes, offset, 4, 1, status);
//                setBit(bytes, offset, HMI_leftFragmentFrontFogLight, 1, !((Boolean)status));
                break;
//            case MsgCommand.HMIF:
//                setBit(bytes, offset, flag, 1, status);
////                setBit(bytes, offset, HMI_leftFragmentBackFogLight, 1, !((Boolean)status));
//                break;
            case MsgCommand.HMI_Dig_Ord_DoorLock:
                setBit(bytes, offset, 6, 1, status);
                break;
            case MsgCommand.HMI_Dig_Ord_Alam:
                setBit(bytes, offset, 7, 1, status);
                break;
            case MsgCommand.HMI_Dig_Ord_Driver_model:
                setBit(bytes, offset, 8, 2, status);
                break;
            case MsgCommand.HMI_Dig_Ord_air_model:
                setBit(bytes, offset, 10, 2, status);
                break;
            case MsgCommand.HMI_Dig_Ord_air_grade:
                setBit(bytes, offset, 12, 3, status);
                break;
            case MsgCommand.HMI_Dig_Ord_eBooster_Warning:
                setBit(bytes, offset, 15, 1, status);
                break;
            default:
                LogUtil.d(TAG, "消息转换错误");
                break;
        }
        LogUtil.d(TAG, bytesToHex(bytes));
    }

    @Override
    public int getOffset() {
        return offset;
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
