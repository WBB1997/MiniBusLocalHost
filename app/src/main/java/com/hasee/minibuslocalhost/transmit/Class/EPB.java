package com.hasee.minibuslocalhost.transmit.Class;

import java.lang.reflect.Field;
import java.util.HashMap;

public class EPB extends BaseClass{
    double EPB_Dig_Sata_Status;
    double EPB_Dig_Sata_Indication;

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