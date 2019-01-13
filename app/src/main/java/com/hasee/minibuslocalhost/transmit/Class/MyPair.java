package com.hasee.minibuslocalhost.transmit.Class;

import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.util.Pair;

import com.hasee.minibuslocalhost.transmit.MsgCommand;

import java.util.Objects;

public class MyPair <F> {
    private F length;
    private Pair<MsgCommand, Integer> second;

    public MyPair(F length, MsgCommand id, Integer target) {
        this.length = length;
        this.second = new Pair<>(id, target);
    }

    public F getLength() {
        return length;
    }

    public Pair<MsgCommand, Integer> getSecond() {
        return second;
    }

    public void setLength(F length) {
        this.length = length;
    }

    public void setSecond(Pair<MsgCommand, Integer> second) {
        this.second = second;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyPair<?> myPair = (MyPair<?>) o;
        return Objects.equals(length, myPair.length) &&
                Objects.equals(second, myPair.second);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public int hashCode() {
        return Objects.hash(length, second);
    }

    @NonNull
    @Override
    public String toString() {
        return "Pair{" + String.valueOf(length) + " " + String.valueOf(second) + "}";
    }

}
