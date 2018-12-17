package com.hasee.minibuslocalhost.transmit.Class;

public class VCU2 extends BaseClass {
    double can_num_MotorSpeed_req;
    double can_num_MotorTq_req;

    @Override
    public byte[] getBytes() {
        return new byte[0];
    }

    @Override
    public void setBytes(byte[] bytes) {

    }
}
