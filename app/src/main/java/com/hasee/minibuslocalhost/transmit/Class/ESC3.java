package com.hasee.minibuslocalhost.transmit.Class;

import java.lang.reflect.Field;
import java.util.HashMap;

public class ESC3 extends BaseClass{
    double EBD_Fault;
    double ABS_Fault;
    double ABS_work_Flag;
    double Wheel_Speed_Fault;
    double ABS_Function;
    double ABS_Fault_Lamp;
    double EBD_Fault_Lamp;
    double Wheel_Speed_ABS;

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
