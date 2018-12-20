package com.hasee.minibuslocalhost.transmit.Class;

import java.lang.reflect.Field;
import java.util.HashMap;

public class ESC1 extends BaseClass{
    double ESC_Ang_Stat_FLWHeelPulseCounter;
    double ESC_Ang_Stat_FRWHeelPulseCounter;
    double ESC_Ang_Stat_RLWHeelPulseCounter;
    double ESC_Ang_Stat_RRWHeelPulseCounter;
    double ESC_Ang_Stat_FLWHeelPulseCoutVal;
    double ESC_Ang_Stat_FRWHeelPulseCoutVal;
    double ESC_Ang_Stat_RLWHeelPulseCoutVal;
    double ESC_Ang_Stat_RRWHeelPulseCoutVal;
    double ESC_Flg_Stat_AccelerationID;
    double ESC_Ang_Stat_Acceleration;

    @Override
    public byte[] getBytes() {
        return new byte[0];
    }

    @Override
    public void setBytes(byte[] bytes) {

    }
    @Override
    public HashMap<Integer, MyPair<Boolean>> getFields() {
        return null;
    }

}
