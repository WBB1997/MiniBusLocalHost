package com.hasee.minibuslocalhost.util;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.os.Environment;
import android.text.TextUtils;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StationPlayer {
    private static final String TAG = "StationPlayer";
    private final int CAR_START = 3;//车辆出发
    private final int ARRIVED = 2;//到站和下车 0x2
    private final int ARRIVING = 1;//即将到站 0x1
    private final int ARRIVING_TERMINAL_STATION = 5;//即将到达终点站
    private final int ARRIVED_TERMINAL_STATION = 6;//到达终点站
    private String basePath = Environment.getExternalStorageDirectory() + "/sound/";
    private Context mContext;
    private static StationPlayer instance = null;
    private MediaPlayer mediaPlayer = null;
    private AssetFileDescriptor afDescriptor = null;
    private int currentRouteNum = 0;//默认路线号
    private int index = 0;//标记File当前位置
    private static Map<Integer, Map<Integer, String>> routeMap = new HashMap<>();
    private List<File> files = null;

    private StationPlayer(Context mContext) {
        this.mContext = mContext;
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setOnCompletionListener(completionListener);
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

    public static StationPlayer getInstance(Context mContext) {
        if (instance == null) {
            instance = new StationPlayer(mContext);
        }
        return instance;
    }

    /**
     * 到站提醒语音播报
     *
     * @param playType
     * @param stationNum
     */
    public void playMusicByPath(int playType, int stationNum) {
        int currentStationNum = getStationNum();//当前路线的总站点数
        if(stationNum == currentStationNum-1){//如果为终点站
            if(playType == ARRIVED){//如果现在是已经到站
                playType = ARRIVED_TERMINAL_STATION;//设置为到达终点站
            }else if(playType == ARRIVING){
                playType = ARRIVING_TERMINAL_STATION;//设置为即将到达终点站
            }
        }
        if(mediaPlayer.isPlaying()){//如果正在播放
            index = 0;
            mediaPlayer.reset();
        }
        String path = getStaMusicInfo(stationNum);//下一站点语音路径
        String endPath = getStaMusicInfo(currentStationNum-1);//终点站语音路径
//        Log.d(TAG, "playMusicByPath: "+path+":"+endPath);
        if (!TextUtils.isEmpty(path)) {
            files = new ArrayList<>();
            if (playType == ARRIVED) {//到站和下车{XXX 到了，下车请注意}
                files.add(new File(basePath + path));
                files.add(new File(basePath + "到了.wav"));
                files.add(new File(basePath + "下车请注意.wav"));
            } else if (playType == ARRIVING) {//即将到站{前方即将到站 XXX，请做好下车准备}
                files.add(new File(basePath + "前方即将到站.wav"));
                files.add(new File(basePath + path));
                files.add(new File(basePath + "请做好下车准备.wav"));
            } else if (playType == ARRIVED_TERMINAL_STATION) {//到达终点站{终点站 XXX 到了，开门请当心，下车请注意}
                files.add(new File(basePath + "终点站.wav"));
                files.add(new File(basePath + path));
                files.add(new File(basePath + "开门请当心下车请注意.wav"));
            } else if (playType == ARRIVING_TERMINAL_STATION) {//即将到达终点站{前方即将到达终点站 XXX， 请做好下车准备}
                files.add(new File(basePath + "前方即将到达终点站.wav"));
                files.add(new File(basePath + path));
                files.add(new File(basePath + "请做好下车准备.wav"));
            } else if(playType == CAR_START){//车辆出发{欢迎乘坐东风Sharing Van无人驾驶车,本车开往XXX方向,下一站XXX}
                files.add(new File(basePath+"欢迎乘坐.wav"));
                files.add(new File(basePath+"本车开往.wav"));
                files.add(new File(basePath+endPath));
                files.add(new File(basePath+"方向.wav"));
                files.add(new File(basePath+"下一站.wav"));
                files.add(new File(basePath+path));
            }
            playMusic();
        }
    }

    /**
     * 初始化音乐文件
     */
    private void initMediaPlayerFile(int index) {
        try {
            mediaPlayer.setDataSource(files.get(index).getPath());
            mediaPlayer.prepare();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 播放音乐
     */
    private void playMusic() {
        if (index == 0) {
            initMediaPlayerFile(index);
        }
        if (!mediaPlayer.isPlaying()) {
            mediaPlayer.start();
        }
    }

    /**
     * 下一首
     */
    private void nextMusic() {
        if ((mediaPlayer != null) && (index >= 0 && index < files.size())) {
            mediaPlayer.reset();
            initMediaPlayerFile(index);
            playMusic();
        } else {
            if (mediaPlayer != null) {
                index = 0;
                mediaPlayer.reset();
            }
        }
    }

    /**
     * 关闭播放器
     */
    public void closeMusic() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
        }
    }


    /**
     * 播放结束
     */
    private MediaPlayer.OnCompletionListener completionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            index++;
            nextMusic();
        }
    };

    /**
     * 关闭资源
     */
    public void destory() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
        }
    }

    /**
     * 返回相应站点的信息
     *
     * @param stationNum
     * @return
     */
    private String getStaMusicInfo(int stationNum) {
        String info = "";
        for (Map.Entry<Integer, Map<Integer, String>> route : routeMap.entrySet()) {
            int routeNum = route.getKey();
            if (routeNum == currentRouteNum) {//为当前路线编号
                Map<Integer, String> mRoute = route.getValue();
                if (stationNum < mRoute.size() && stationNum >= 0) {//站点在范围之内
                    info = mRoute.get(stationNum);
                }
            }
        }
        return info;
    }

    /**
     * 返回当前路线的站点总数
     * @return
     */
    private int getStationNum() {
        int num = 0;
        for (Map.Entry<Integer, Map<Integer, String>> route : routeMap.entrySet()) {
            int routeNum = route.getKey();
            if(routeNum == currentRouteNum){//为当前路线编号
                Map<Integer,String> mRoute = route.getValue();
                num = mRoute.size();
            }
        }
        return num;
    }

    /**
     * 返回所有路线信息
     *
     * @return
     */
    public String printRouteInfo() {
        StringBuffer info = new StringBuffer();
        for (Map.Entry<Integer, Map<Integer, String>> route : routeMap.entrySet()) {
            info.append(route.toString() + "\n");
        }
        return info.toString();
    }

    /**
     * 解析路线XML文件
     *
     * @param in
     * @return 存取当前路线号的Map
     */
    private Map<Integer, Map<Integer, String>> parseXMLWithPull(InputStream in) {
        Map<Integer, Map<Integer, String>> routeMap = new HashMap<>();
        int routeCount = -1;//路线数
        try {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser xmlPullParser = factory.newPullParser();
            xmlPullParser.setInput(in, "UTF-8");
            int id = -1;//站点号
            String path = "";//站点相应的音乐路径
            String chsName = "";//中文站点名
            Map<Integer, String> route = null;//路线号
            int eventType = xmlPullParser.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                String nodeName = xmlPullParser.getName();
                switch (eventType) {
                    //开始解析某个节点
                    case XmlPullParser.START_TAG: {
                        if ("Route".equals(nodeName)) {//路线号
                            routeCount++;
                            id = -1;
                            route = new HashMap<>();//初始化路线
                        } else if ("item".equals(nodeName)) {//站点
                            id++;
                        } else if ("chs".equals(nodeName)) {//站点中文名

                        } else if ("en".equals(nodeName)) {//站点英文名

                        } else if ("path".equals(nodeName)) {//站点音乐路径
                            path = xmlPullParser.nextText();
                            route.put(id, path);
                        }
                        break;
                    }
                    //完成解析某个节点
                    case XmlPullParser.END_TAG: {
                        if ("Route".equals(nodeName)) {
                            routeMap.put(routeCount, route);
                        }
                        break;
                    }
                    default: {
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
