package com.hasee.minibuslocalhost.transmit.Class;

import java.lang.reflect.Field;
import java.util.HashMap;

public class EPS1 extends BaseClass{
    double EPS_Dig_Alm_EPSWarning;
    @Override
    public byte[] getBytes() {
        return new byte[0];
    }

    @Override
    public void setBytes(byte[] bytes){

    }
    @Override
    public HashMap<Integer, MyPair<Boolean>> getFields() {
        return null;
    }
}
