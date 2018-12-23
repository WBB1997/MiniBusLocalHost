package com.hasee.minibuslocalhost.transmit.Class;

import com.hasee.minibuslocalhost.activity.MainActivity;
import com.hasee.minibuslocalhost.util.LogUtil;

import java.util.HashMap;
import java.util.Map;

import static com.hasee.minibuslocalhost.util.ByteUtil.countBits;
import static com.hasee.minibuslocalhost.util.ByteUtil.viewBinary;

public class ESC3 extends BaseClass{
    private final static String TAG = "ESC3";

    private MyPair<Integer> EBD_Fault = new MyPair<>(1, 53, MainActivity.SEND_TO_LOCALHOST); // EBD故障信号
    private MyPair<Integer> ABS_Fault = new MyPair<>(1, 54, MainActivity.SEND_TO_LOCALHOST); // ABS故障信号
    private MyPair<Integer> ABS_work_Flag = new MyPair<>(1, 55, MainActivity.SEND_TO_LOCALHOST); // ABS动作标志
    private MyPair<Integer> Wheel_Speed_Fault = new MyPair<>(1, 56, MainActivity.SEND_TO_LOCALHOST); // 轮速故障标志
    private MyPair<Integer> ABS_Function = new MyPair<>(1, 57, MainActivity.SEND_TO_LOCALHOST); // ABS功能
    private MyPair<Integer> ABS_Fault_Lamp = new MyPair<>(1, 58, MainActivity.SEND_TO_LOCALHOST); // ABS故障状态指示灯
    private MyPair<Integer> EBD_Fault_Lamp = new MyPair<>(1, 59, MainActivity.SEND_TO_LOCALHOST); // EBD故障指示灯
    private MyPair<Integer> Wheel_Speed_ABS = new MyPair<>(16, 60, MainActivity.SEND_TO_LOCALHOST); // 车速信号

    // 属性
    private HashMap<Integer, MyPair<Integer>> fields = new HashMap<Integer, MyPair<Integer>>() {{
        put(0, EBD_Fault);
        put(1, ABS_Fault);
        put(2, ABS_work_Flag);
        put(3, Wheel_Speed_Fault);
        put(4, ABS_Function);
        put(5, ABS_Fault_Lamp);
        put(6, EBD_Fault_Lamp);
        put(15, Wheel_Speed_ABS);

    }};
    private byte[] bytes = {0x00, 0x00, 0x00, 0x04, (byte) 0xC0, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00};

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
        int length = entry.getValue().getFirst();
        switch (index) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
                return viewBinary(bytes[index / 8 + 5], index % 8);
            case 15:
                return countBits(bytes, 5, 15, 16);
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
