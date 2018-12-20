package com.hasee.minibuslocalhost.transmit.Class;

import java.lang.reflect.Field;
import java.util.HashMap;

public class HAD3 extends BaseClass{
    double HAD_Dig_Ord_RegenTq;
    double HAD_Ang_Stat_MotTorque;

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
