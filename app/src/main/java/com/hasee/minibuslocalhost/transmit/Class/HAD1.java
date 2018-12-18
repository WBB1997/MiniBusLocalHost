package com.hasee.minibuslocalhost.transmit.Class;

import java.lang.reflect.Field;

public class HAD1 extends BaseClass{
    double HAD_Dig_Ord_Brake;
    double HAD_Dig_Ord_VCUAutoDriveEnable;
    double HAD_Dig_Ord_ESCAutoDriveEnable;
    double HAD_Dig_Ord_EPSAutoDriveEnable;
    double HAD_Dig_Ord_EPBAutoDriveEnable;
    double HAD_Ang_Stat_ElectricAccPedal;
    double HAD_Ang_Stat_WhellAngle;
    double HAD_Ang_Stat_WhellAngleAccleration;
    double HAD_Ang_Stat_BrakeAccleration;
    double HAD_Dig_Ord_Gear;
    double HAD_Dig_Ord_BCMAutoDriveEnable;

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
