package com.hasee.minibuslocalhost.Translate;

import android.util.Pair;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.*;

public class Translate {
    private final int MAX_LENGTH = 2048; // 最大接收字节长度
    private final int PORT = 5066;   // port号

    public static void main(String[] args) {
        new Translate();
    }

    public Translate() {
//        new Thread(()->{
//            UDP_receive();
//        }).start();
        init();
    }

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
    private final static ArrayList<Pair<String, String>> list = new ArrayList<>(Arrays.asList(
            new Pair<>("00000222", "VUC1"),
            new Pair<>("00000220", "VCU2"),
            new Pair<>("000003E1", "EPB"),
            new Pair<>("00000300", "MCU1"),
            new Pair<>("00000373", "EPS1"),
            new Pair<>("00000228", "EPS2"),
            new Pair<>("00000225", "EPS3"),
            new Pair<>("00000226", "ESC1"),
            new Pair<>("00000227", "ESC2"),
            new Pair<>("000004C0", "ESC3"),
            new Pair<>("00000361", "BCM1"),
            new Pair<>("00000219", "HAD1"),
            new Pair<>("00000363", "HAD2"),
            new Pair<>("00000233", "HAD3")
    ));
    // 消息标识符键值对，方便查找
    private static Map<String, String> BUS_FLAG = new HashMap<>(50);

    // 初始化
    private void init() {
        for (Pair<String, String> pair : list)
            BUS_FLAG.put(pair.first, pair.second);
    }

    // 处理收到的byte数组
    private void dispose(byte[] receMsgs) {
        String key;
        byte[] tmp;
        if (receMsgs == null)
            return;
        for (int i = 0; i < receMsgs.length; i += 13) {
            key = bytesToHex(subBytes(receMsgs, 1, 4));
            tmp = subBytes(receMsgs, 5, 8);
            // 如果数据更新了
            if (BUS_FLAG.containsKey(key)) {
            } else
                System.err.println("消息标识符错误");
        }
    }

    // send函数
    public static void send(String clazz, String field, Object o) {

    }

    //byte转16进制
    private static String bytesToHex(byte[] bytes) {
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
    private static byte hexToByte(String inHex) {
        return (byte) Integer.parseInt(inHex, 16);
    }

    //16进制转byte
    private static byte[] hexToByteArray(String inHex) {
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
    private static byte[] subBytes(byte[] bytes, int start, int length) {
        byte[] sub = new byte[length];
        for (int i = 0; i < length; i++)
            sub[i] = bytes[i + start];
        return sub;
    }

    // 查看一个Byte的某位是否为1
    private static boolean viewBinary(byte Byte, int position) {
        return (Byte & 0x01 << position) != 0;
    }
}
