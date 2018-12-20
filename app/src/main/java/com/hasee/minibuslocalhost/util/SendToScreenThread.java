package com.hasee.minibuslocalhost.util;

import com.alibaba.fastjson.JSONObject;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * 接收can总线的消息，发送至前风挡、左门、侧门
 * Created by fangju on 2018/12/8
 */
public class SendToScreenThread extends Thread {
    private String[] hostNames = new String[]{"10.13.235.3","10.13.234.3","左车门"};
    private int port = 3000;
    private DatagramSocket dSocket = null;
    private DatagramPacket dPacket = null;
    private byte[] buffer = null;//数据报大小
    private JSONObject object = null;//接收的can总线信息
    private int target = -1;//屏幕编号

    public SendToScreenThread(JSONObject object,int target){
        this.object = object;
        this.target = target;
    }

    @Override
    public void run() {
        String message = object.toJSONString();
        buffer = message.getBytes();
        //发送给特定显示屏
        try {
            //根据命令号确定hostName
            InetAddress address = InetAddress.getByName(hostNames[target]);
            dSocket = new DatagramSocket();
            dPacket = new DatagramPacket(buffer,buffer.length,address,port);
            dSocket.send(dPacket);
            LogUtil.d("SendToScreenThread","发送成功");
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(dSocket != null){
                dSocket.close();
            }
        }
    }

}
