package com.hasee.minibuslocalhost.util;

public class ByteUtil {

    //byte转16进制
    public static String bytesToHex(byte[] bytes) {
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
    public static byte hexToByte(String inHex) {
        return (byte) Integer.parseInt(inHex, 16);
    }

    //16进制转byte
    public static byte[] hexToByteArray(String inHex) {
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
    public static byte[] subBytes(byte[] bytes, int start, int length) {
        byte[] sub = new byte[length];
        for (int i = 0; i < length; i++)
            sub[i] = bytes[i + start];
        return sub;
    }

    // 设置某一位
    public static void setBit(byte[] bytes, int Byte_offset, int bit_index, int changeLength, Object changed) {
        int i = Byte_offset + bit_index / 8;
        int j = bit_index % 8;
        if (changed instanceof Boolean) {
            if ((Boolean) changed)
                bytes[i] |= (0b10000000 >> j);
            else
                bytes[i] &= ~(0b10000000 >> j);
        } else if (changed instanceof Integer) {
            byte b = (byte) (int) changed;
            for (int k = 8 - changeLength; k < 8; k++)
                setBit(bytes, Byte_offset, bit_index++, 1, viewBinary(b, k));
        }
    }

    // 查看一个Byte的某位是否为1
    public static boolean viewBinary(byte Byte, int position) {
        return (Byte & 0b10000000 >> position) != 0;
    }

    // 计算值
    public static double countBits(byte[] bytes, int Byte_offset, int bit_index, int bitLength) {
        double count = 0;
        for(int i = bit_index; i < bit_index + bitLength; i++){
            if(viewBinary(bytes[Byte_offset + i / 8], i % 8)){
                count += Math.pow(2, bit_index + bitLength - i - 1);
            }
        }
        return count;
    }

    public static void setBits(byte[] TargetBytes, int SrcNum, int SrcNumLength, int Byte_offset, int start_bit_index, int bitLength) {
        byte[] SrcBytes = new byte[SrcNumLength];
        for (int i = SrcBytes.length * 8 - 1; i >= 0; i--) {
            if (SrcNum / 2 > 0) {
                setBit(SrcBytes, i / 8, i % 8, 1, SrcNum % 2);
                SrcNum /= 2;
            } else {
                setBit(SrcBytes, i / 8, i % 8, 1, SrcNum % 2);
                break;
            }
        }
        System.out.println(bytesToHex(SrcBytes));
        for (int i = start_bit_index; i < start_bit_index + bitLength; i++) {
            boolean flag = viewBinary(SrcBytes[(i - start_bit_index) / 8], (i - start_bit_index) % 8);
            setBit(TargetBytes, Byte_offset, i, 1, flag);
        }
    }
}
