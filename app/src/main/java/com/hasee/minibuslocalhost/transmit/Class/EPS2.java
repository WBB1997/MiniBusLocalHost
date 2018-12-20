package com.hasee.minibuslocalhost.transmit.Class;

import java.lang.reflect.Field;
import java.util.HashMap;

public class EPS2 extends BaseClass{
    double EPS_Flg_Stat_Steering_IfrDet;
    double EPS_Flg_Stat_Steering_IfrDetVal;
    double EPS_Dig_Stat_EPSAvailablity;
    double EPS_Ang_Stat_SteeringTorque;
    double EPS_Ang_Stat_AvailStatuProtValue;

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
