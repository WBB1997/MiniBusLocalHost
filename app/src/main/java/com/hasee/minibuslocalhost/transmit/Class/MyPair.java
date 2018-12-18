package com.hasee.minibuslocalhost.transmit.Class;

import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.util.Pair;

import java.util.Objects;

public class MyPair <F> {
    private F first;
    private Pair<Integer, Integer> second;

    public MyPair(F first, Integer id, Integer target) {
        this.first = first;
        this.second = new Pair<>(id, target);
    }

    public F getFirst() {
        return first;
    }

    public Pair<Integer, Integer> getSecond() {
        return second;
    }

    public void setFirst(F first) {
        this.first = first;
    }

    public void setSecond(Pair<Integer, Integer> second) {
        this.second = second;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyPair<?> myPair = (MyPair<?>) o;
        return Objects.equals(first, myPair.first) &&
                Objects.equals(second, myPair.second);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public int hashCode() {
        return Objects.hash(first, second);
    }

    @NonNull
    @Override
    public String toString() {
        return "Pair{" + String.valueOf(first) + " " + String.valueOf(second) + "}";
    }

}
