package com.hasee.minibuslocalhost.transmit.Class;

import com.alibaba.fastjson.JSONObject;
import com.hasee.minibuslocalhost.transmit.Transmit;
import com.hasee.minibuslocalhost.util.LogUtil;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static com.hasee.minibuslocalhost.util.ByteUtil.bytesToHex;
import static com.hasee.minibuslocalhost.util.ByteUtil.viewBinary;

public abstract class BaseClass {
    public abstract byte[] getBytes();
    public abstract String getTAG();
    public void setBytes(byte[] bytes){
        int offset = 0;
        String TAG = getTAG();
        byte[] Local_bytes = getBytes();

        LogUtil.d(TAG, "bytes:" + bytesToHex(bytes));
        LogUtil.d(TAG, "this.bytes:" + bytesToHex(Local_bytes));
        // 如果相同则直接返回
        if(Arrays.equals(bytes,Local_bytes)) {
            LogUtil.d(TAG, "数据相同");
            return;
        }
        int index;
        int length;
        boolean flag;
        for (Map.Entry<Integer, MyPair<Integer>> entry : getFields().entrySet()) {
            index = entry.getKey();
            length = entry.getValue().getFirst();
            flag = false;
            for(int i = index; i < index + length; i++) {
                if (viewBinary(Local_bytes[i / 8 + offset], i % 8) != viewBinary(bytes[i / 8 + offset], i % 8))
                    flag = true;
            }
            if(flag){
                JSONObject jsonObject = new JSONObject();
                // id
                jsonObject.put("id", entry.getValue().getSecond().first);
                // data
                jsonObject.put("data", getValue(entry, bytes));
                // target
                int target = entry.getValue().getSecond().second;
                // 发回主函数
                Transmit.getInstance().callback(jsonObject, target);
                // debug
                LogUtil.d(TAG, jsonObject.toJSONString());
            }
        }
        System.arraycopy(bytes, 0, getBytes(), 0, bytes.length);
        LogUtil.d(TAG, "this.bytes:" + bytesToHex(getBytes()));
    }
    public abstract Object getValue(Map.Entry<Integer, MyPair<Integer>> entry, byte[] bytes);
    public abstract HashMap<Integer, MyPair<Integer>> getFields();
}
