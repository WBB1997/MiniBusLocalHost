package com.hasee.minibuslocalhost.transmit.Class;

import java.lang.reflect.Field;
import java.util.HashMap;

public class ESC2 extends BaseClass{
    double ESC_Ang_Stat_YawRate;
    double ESC_Flg_Stat_YawRateValidity;
    double ESC_Flg_Stat_AyvSensorValidity;
    double ESC_Ang_Stat_LateralAccelaration;
    double ESC_Ang_Stat_ActStatus;

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