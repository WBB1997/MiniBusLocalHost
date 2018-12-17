package com.hasee.minibuslocalhost.transmit.Class;

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
}
