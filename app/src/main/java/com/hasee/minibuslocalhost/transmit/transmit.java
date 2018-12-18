package com.hasee.minibuslocalhost.transmit;

import android.os.Message;
import android.util.Pair;

import com.alibaba.fastjson.JSONObject;
import com.hasee.minibuslocalhost.transmit.Class.BCM1;
import com.hasee.minibuslocalhost.transmit.Class.BaseClass;
import com.hasee.minibuslocalhost.transmit.Class.EPB;
import com.hasee.minibuslocalhost.transmit.Class.EPS1;
import com.hasee.minibuslocalhost.transmit.Class.EPS2;
import com.hasee.minibuslocalhost.transmit.Class.EPS3;
import com.hasee.minibuslocalhost.transmit.Class.ESC1;
import com.hasee.minibuslocalhost.transmit.Class.ESC2;
import com.hasee.minibuslocalhost.transmit.Class.ESC3;
import com.hasee.minibuslocalhost.transmit.Class.HAD1;
import com.hasee.minibuslocalhost.transmit.Class.HAD2;
import com.hasee.minibuslocalhost.transmit.Class.HAD3;
import com.hasee.minibuslocalhost.transmit.Class.MCU1;
import com.hasee.minibuslocalhost.transmit.Class.VCU1;
import com.hasee.minibuslocalhost.transmit.Class.VCU2;
import com.hasee.minibuslocalhost.util.MyHandler;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class transmit {
    private final int MAX_LENGTH = 2048; // 最大接收字节长度
    private final int PORT = 5066;   // port号
    private final static transmit instance = new transmit();
    private MyHandler handler;

    public static void main(String[] args) {
        new transmit();
    }

    public transmit() {
        init();
    }

    // 主机发送数据给CAN总线
    public void hostToCAN(String clazz, String field, Object o) {

    }

    public void setHandler(MyHandler handler) {
        this.handler = handler;
    }

    public static transmit getInstance() {
        return instance;
    }

    // 以下为私有方法，对外部是隐藏的
    // 回调
    // jsonObject:{'id':1, 'data':[1,1,...], 'target':1}
    public void callback(JSONObject jsonObject,int target){
        //通过message 发给ui
        Message msg = handler.obtainMessage();
        msg.what = target;
        msg.obj = jsonObject;
        handler.sendMessage(msg);
    }

    // 接收CAN总线
    private void UDP_receive() {
        byte[] receMsgs = new byte[MAX_LENGTH];
        DatagramSocket datagramSocket = null;
        DatagramPacket datagramPacket;
        try {
            datagramSocket = new DatagramSocket(PORT);
            while (true) {
                datagramPacket = new DatagramPacket(receMsgs, receMsgs.length);
                datagramSocket.receive(datagramPacket);
                dispose(datagramPacket.getData());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭socket
            if (datagramSocket != null) {
                datagramSocket.close();
            }
        }
    }

    // 发到CAN总线
    private void UDP_send(Object o, String ip) {
        byte[] sendMsgs = new byte[MAX_LENGTH];
        DatagramSocket datagramSocket = null;
        DatagramPacket datagramPacket;
        try {
            datagramSocket = new DatagramSocket(PORT, InetAddress.getByName(ip));
            datagramPacket = new DatagramPacket(sendMsgs, sendMsgs.length);
            datagramSocket.send(datagramPacket);
            dispose(datagramPacket.getData());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭socket
            if (datagramSocket != null) {
                datagramSocket.close();
            }
        }
    }

    // 消息标识符
    private ArrayList<Pair<String, ? extends BaseClass>> list = new ArrayList<>(Arrays.asList(
            new Pair<>("00000222", new VCU1()),
            new Pair<>("00000220", new VCU2()),
            new Pair<>("000003E1", new EPB()),
            new Pair<>("00000300", new MCU1()),
            new Pair<>("00000373", new EPS1()),
            new Pair<>("00000228", new EPS2()),
            new Pair<>("00000225", new EPS3()),
            new Pair<>("00000226", new ESC1()),
            new Pair<>("00000227", new ESC2()),
            new Pair<>("000004C0", new ESC3()),
            new Pair<>("00000361", new BCM1()),
            new Pair<>("00000219", new HAD1()),
            new Pair<>("00000363", new HAD2()),
            new Pair<>("00000233", new HAD3())
    ));
    // 消息标识符键值对，方便查找
    private Map<String, ? super BaseClass> BUS_FLAG = new HashMap<>();

    // 初始化
    private void init() {
        for (Pair<String, ? extends BaseClass> pair : list)
            BUS_FLAG.put(pair.first, pair.second);
    }

    // 处理收到的byte数组
    private void dispose(byte[] receMsgs) {
        String key;
        if (receMsgs == null)
            return;
        for (int i = 0; i < receMsgs.length; i += 13) {
            key = bytesToHex(subBytes(receMsgs, 1, 4));
            // 如果数据更新了
            if (BUS_FLAG.containsKey(key)) {
                ((BaseClass) BUS_FLAG.get(key)).setBytes(receMsgs);
            } else
                System.err.println("消息标识符错误");
        }
    }

    //byte转16进制
    private String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(bytes[i] & 0xFF);
            if (hex.length() < 2)
                sb.append(0);
            sb.append(hex);
        }
        return sb.toString();
    }

    //16进制转byte
    private byte hexToByte(String inHex) {
        return (byte) Integer.parseInt(inHex, 16);
    }

    //16进制转byte
    private byte[] hexToByteArray(String inHex) {
        int hexlen = inHex.length();
        byte[] result;
        if (hexlen % 2 == 1) {
            //奇数
            hexlen++;
            result = new byte[(hexlen / 2)];
            inHex = "0" + inHex;
        } else {
            //偶数
            result = new byte[(hexlen / 2)];
        }
        int j = 0;
        for (int i = 0; i < hexlen; i += 2) {
            result[j] = hexToByte(inHex.substring(i, i + 2));
            j++;
        }
        return result;
    }

    // 返回子数组
    private byte[] subBytes(byte[] bytes, int start, int length) {
        byte[] sub = new byte[length];
        for (int i = 0; i < length; i++)
            sub[i] = bytes[i + start];
        return sub;
    }

    // 查看一个Byte的某位是否为1
    private boolean viewBinary(byte Byte, int position) {
        return (Byte & 0x01 << position) != 0;
    }
}
