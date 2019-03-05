package com.hasee.minibuslocalhost.util;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Environment;
import android.speech.tts.TextToSpeech;
import android.text.TextUtils;
import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class StationPlayer {
    private static final String TAG = "StationPlayer";
    private final int PLAY_PATH = 1;//播放站点音乐
    private final int PLAY_CHS = 2;//播放TTS站点语音
    private final int  ARRIVED= 1;//已经到达
    private final int ARRIVING = 2;//即将到达
    private Context mContext;
    private static StationPlayer instance = null;
    private MediaPlayer mediaPlayer = null;
    private TextToSpeech tts = null;
    private int currentRouteNum = 0;//默认路线号
    private int playType = 1;//播放类型，默认为根据文件名播放音乐
    private int arriveFlag = 1;//到达方式
    private static Map<Integer, Map<Integer,String>> routeMap = new HashMap<>();

    private StationPlayer(Context mContext, int playType){
        this.mContext = mContext;
        this.playType = playType;
        mediaPlayer = new MediaPlayer();
        tts = new TextToSpeech(mContext,onInitListener);
        try {
            InputStream in = mContext.getAssets().open("RouteInfo.xml");
            routeMap = parseXMLWithPull(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setRouteNum(int routeNum) {
        this.currentRouteNum = routeNum;
    }

    public void setArriveFlag(int arriveFlag) {
        this.arriveFlag = arriveFlag;
    }

    public static StationPlayer getInstance(Context mContext, int playType){
        if(instance == null){
            instance = new StationPlayer(mContext,playType);
        }
        return instance;
    }

    /**
     * 播放音乐
     * @param stationNum
     */
    public void playMusic(int stationNum){
        if(playType == PLAY_CHS){
            playMusicByChs(stationNum);
        }else if(playType == PLAY_PATH){
            playMusicByPath(stationNum);
        }
    }

    /**
     * 根据站点号播放站点中文信息
     * @param stationNum
     */
    private void playMusicByChs(int stationNum){
        String chsName = getStaMusicInfo(stationNum);
        if(!TextUtils.isEmpty(chsName.trim())){
            if(tts.isSpeaking()){
                tts.stop();
            }
            //设置音调，值越大声音越尖（女生），值越小则变成男声,1.0是常规
            tts.setPitch(1.0f);
            //设置语速
            tts.setSpeechRate(1.0f);
            //设置语言
            tts.setLanguage(Locale.CHINESE);
            //播放语音
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                String speekText = "";
                if(arriveFlag == ARRIVED){
                    speekText = chsName+"到了，下车的乘客请从后门下车";
                }else if(arriveFlag == ARRIVING){
                    speekText = chsName+"即将到达，下车的乘客请做好准备";
                }
                tts.speak(speekText, TextToSpeech.QUEUE_ADD, null,null);
            }
        }
    }

    /**
     * 根据站点号播放站点音乐
     * @param stationNum 站点号
     */
    private void playMusicByPath(int stationNum){
        String path = getStaMusicInfo(stationNum);
        if(!TextUtils.isEmpty(path)){
            try {
                File file = new File(Environment.getExternalStorageDirectory(),"/RouteMusic/"+path);
//            Log.d(TAG, "playMusicByPath: "+file.getPath());
                if(mediaPlayer.isPlaying()){
                    mediaPlayer.reset();
                }
                mediaPlayer.setDataSource(file.getPath());
                mediaPlayer.prepare();
                if(!mediaPlayer.isPlaying()){
                    mediaPlayer.start();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{

        }
    }

    /**
     * 关闭资源
     */
    public void destory(){
        if(tts != null){
            tts.stop();
            tts.shutdown();
        }
        if(mediaPlayer != null){
            mediaPlayer.stop();
            mediaPlayer.release();
        }
    }

    /**
     * TTS监听器
     */
    private TextToSpeech.OnInitListener onInitListener = new TextToSpeech.OnInitListener() {
        @Override
        public void onInit(int status) {
            if(status == TextToSpeech.SUCCESS){

            }
        }
    };

    /**
     * 返回相应站点的信息
     * @param stationNum
     * @return
     */
    private String getStaMusicInfo(int stationNum){
        String info = "";
        for (Map.Entry<Integer,Map<Integer,String>> route: routeMap.entrySet()) {
            int routeNum = route.getKey();
            if(routeNum == currentRouteNum){//为当前路线编号
                Map<Integer,String> mRoute = route.getValue();
                if(stationNum <= mRoute.size() && stationNum >0){//站点在范围之内
                    info = mRoute.get(stationNum);
                }
            }
        }
        Log.d(TAG, "getStaMusicInfo: "+info);
        return info;
    }

    /**
     * 返回所有路线信息
     * @return
     */
    public String printRouteInfo(){
        StringBuffer info = new StringBuffer();
        for (Map.Entry<Integer,Map<Integer,String>> route: routeMap.entrySet()) {
            info.append(route.toString()+"\n");
        }
        return info.toString();
    }

    /**
     * 解析路线XML文件
     * @param in
     * @return 存取当前路线号的Map
     */
    private Map<Integer, Map<Integer,String>> parseXMLWithPull(InputStream in){
        Map<Integer, Map<Integer,String>> routeMap = new HashMap<>();
        int routeCount = -1;//路线数
        try {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser xmlPullParser = factory.newPullParser();
            xmlPullParser.setInput(in,"UTF-8");
            int id = 0;//站点号
            String path = "";//站点相应的音乐路径
            String chsName = "";//中文站点名
            Map<Integer,String> route = null;//路线号
            int eventType = xmlPullParser.getEventType();
            while(eventType != XmlPullParser.END_DOCUMENT){
                String nodeName = xmlPullParser.getName();
                switch (eventType){
                    //开始解析某个节点
                    case XmlPullParser.START_TAG:{
                        if("Route".equals(nodeName)){//路线号
                            routeCount++;
                            id = 0;
                            route = new HashMap<>();//初始化路线
                        }else if("item".equals(nodeName)){//站点
                            id++;
                        }else if("chs".equals(nodeName)){//站点中文名
                            if(playType == PLAY_CHS){
                                chsName = xmlPullParser.nextText();
                                route.put(id,chsName);
                            }
                        }else if("en".equals(nodeName)){//站点英文名

                        }else if("path".equals(nodeName)){//站点音乐路径
                            if(playType == PLAY_PATH){
                                path = xmlPullParser.nextText();
                                route.put(id,path);
                            }
                        }
                        break;
                    }
                    //完成解析某个节点
                    case XmlPullParser.END_TAG:{
                        if("Route".equals(nodeName)){
                            routeMap.put(routeCount,route);
                        }
                        break;
                    }
                    default:{
                        break;
                    }
                }
                eventType = xmlPullParser.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return routeMap;
    }

}
