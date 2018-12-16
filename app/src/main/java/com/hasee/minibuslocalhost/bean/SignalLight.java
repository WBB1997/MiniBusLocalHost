package com.hasee.minibuslocalhost.bean;

import java.io.Serializable;

public class SignalLight implements Serializable {
    private boolean foglight = false; // 雾灯
    private boolean sideMarkerLights = false; // 轮廓灯
    private boolean highBeam = false; // 远光灯
    private boolean lowBeam = false; // 近光灯

    public boolean isFoglight() {
        return foglight;
    }

    public void setFoglight(boolean foglight) {
        this.foglight = foglight;
    }

    public boolean isSideMarkerLights() {
        return sideMarkerLights;
    }

    public void setSideMarkerLights(boolean sideMarkerLights) {
        this.sideMarkerLights = sideMarkerLights;
    }

    public boolean isHighBeam() {
        return highBeam;
    }

    public void setHighBeam(boolean highBeam) {
        this.highBeam = highBeam;
    }

    public boolean isLowBeam() {
        return lowBeam;
    }

    public void setLowBeam(boolean lowBeam) {
        this.lowBeam = lowBeam;
    }
}
