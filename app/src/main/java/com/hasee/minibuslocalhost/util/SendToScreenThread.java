package com.hasee.minibuslocalhost.util;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * 接收can总线的消息，发送至前风挡、左门、侧门
 * Created by fangju on 2018/12/8
 */
public class SendToScreenThread extends Thread {
    private String[] hostNames = new String[]{"前风挡","右车门","左车门"};
    private int port = 1234;
    private DatagramSocket dSocket = null;
    private DatagramPacket dPacket = null;
    private byte[] buffer = null;//数据报大小
    private String message = null;//接收的can总线信息

    public SendToScreenThread(String message){
        this.message = message;
    }

    @Override
    public void run() {
        //处理can总线的信息
        int flag = 1;
        buffer = message.getBytes();
        //发送给特定显示屏
        try {
            //根据命令号确定hostName
            InetAddress address = InetAddress.getByName(hostNames[seekHostName(flag)]);
            dSocket = new DatagramSocket();
            dPacket = new DatagramPacket(buffer,buffer.length,address,port);
            dSocket.send(dPacket);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(dSocket != null){
                dSocket.close();
            }
        }
    }

    /**
     * 判断消息发送给哪个显示屏
     * @param flag
     * @return
     */
    public int seekHostName(int flag){
        if(flag >= 1&& flag <= 3){//前风挡
            return 0;
        }else if(flag >= 11&& flag <= 14){//右车门
            return 1;
        }else if(flag >= 21&& flag <= 24){//左车门
            return 2;
        }
        return 3;
    }
}
