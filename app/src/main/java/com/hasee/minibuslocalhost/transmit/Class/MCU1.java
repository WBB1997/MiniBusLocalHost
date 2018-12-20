package com.hasee.minibuslocalhost.transmit.Class;

import java.lang.reflect.Field;
import java.util.HashMap;

public class MCU1 extends BaseClass{
    double can_num_MotTemp;
    double can_num_MCUTemp;
    double can_num_MotTorque;
    double can_num_MotSpeed;

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
