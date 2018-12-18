package com.hasee.minibuslocalhost.transmit.Class;

import java.lang.reflect.Field;

public class HAD2 extends BaseClass{
    double HAD_Dig_Ord_HighBeam;
    double HAD_Dig_Ord_LowBeam;
    double HAD_Dig_Ord_LeftTurningLamp;
    double HAD_Dig_Ord_RightTurningLamp;
    double HAD_Dig_Ord_DangerAlarmLamp;
    double HAD_Dig_Ord_BrakeLamp;
    double HAD_Dig_Ord_BackupLamp;
    double HAD_Dig_Ord_RearFogLamp;
    double HAD_Dig_Ord_WindowProjection;
    double HAD_Dig_Ord_Horn;
    double HAD_Dig_Ord_DoorLock;

    @Override
    public byte[] getBytes() {
        return new byte[0];
    }

    @Override
    public void setBytes(byte[] bytes) {

    }
    @Override
    public Field[] getFields() {
        return new Field[0];
    }
}
