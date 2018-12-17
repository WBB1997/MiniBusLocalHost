package com.hasee.minibuslocalhost.transmit.Class;

public class VCU1 extends BaseClass{
    double VCU_Ang_Stat_PhysicsAccPedal;
    double VCU_Ang_Stat_RealAccPedal;
    double VCU_Dig_Stat_AutoDriveStatus;
    double VCU_Dig_Ord_AccPedalInterventEn;
    double VCU_Flg_Stat_BrakePedal;
    double VCU_Flg_Stat_AccPedalFaultBit;
    double VCU_Dig_Stat_PhysicsGear;
    double VCU_Dig_Stat_RealGear;
    double VCU_Flg_Stat_ShiftLeverPosValid;

    @Override
    public byte[] getBytes() {
        return new byte[0];
    }

    @Override
    public void setBytes(byte[] bytes) {

    }
}
