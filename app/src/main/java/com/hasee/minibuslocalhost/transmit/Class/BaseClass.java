package com.hasee.minibuslocalhost.transmit.Class;

import java.lang.reflect.Field;

public abstract class BaseClass {
    public abstract byte[] getBytes();
    public abstract void setBytes(byte[] bytes);
    public abstract Field[] getFields();
}
