package com.hasee.minibuslocalhost.transmit.Class;

import java.util.HashMap;

public abstract class BaseClass {
    public abstract byte[] getBytes();
    public abstract void setBytes(byte[] bytes);
    public abstract HashMap<Integer, MyPair<Integer>> getFields();
}
