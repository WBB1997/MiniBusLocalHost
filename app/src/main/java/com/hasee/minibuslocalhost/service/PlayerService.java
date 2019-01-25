package com.hasee.minibuslocalhost.service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.Environment;
import android.os.IBinder;

import com.alibaba.fastjson.JSONObject;
import com.hasee.minibuslocalhost.activity.App;
import com.hasee.minibuslocalhost.util.LogUtil;

import java.io.File;

/**
 * Created by fangju on 2019/1/24
 */
public class PlayerService extends Service {
    private static final String TAG = "PlayerService";
    private MusicBinder binder;
    private MediaPlayer mediaPlayer;
    private int index;//标记当前歌曲的位置
    private File[] filesPath = new File[]{
            new File(Environment.getExternalStorageDirectory(),"1.mp3"),//音乐1
            new File(Environment.getExternalStorageDirectory(),"2.mp3"),//音乐2
            new File(Environment.getExternalStorageDirectory(),"3.mp3")//音乐3
    };//文件路径

    public PlayerService(){
        index = 0;
        binder = new MusicBinder();
        mediaPlayer = new MediaPlayer();
        initMediaPlayerFile(index);
        LogUtil.d(TAG,"PlayerService初始化");
    }


    @Override
    public void onCreate() {
        super.onCreate();
        LogUtil.d(TAG,"onCreate");
        mediaPlayer.setOnCompletionListener(onCompletionListener);
        mediaPlayer.setOnErrorListener(onErrorListener);
        JSONObject object = new JSONObject();
        object.put("id",1);
        object.put("data",10);
        App.getInstance().setAudioVolume(object);
        binder.playMusic();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    /**
     * 播放完毕监听
     */
    private MediaPlayer.OnCompletionListener onCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            index++;
            binder.nextMusic();
        }
    };

    /**
     * 播放出错监听
     */
    private MediaPlayer.OnErrorListener onErrorListener = new MediaPlayer.OnErrorListener() {
        @Override
        public boolean onError(MediaPlayer mp, int what, int extra) {
            mp.reset();
            return false;
        }
    };

    public class MusicBinder extends Binder {
        /**
         * 播放音乐
         */
        public void playMusic() {
            if (!mediaPlayer.isPlaying()) {
                mediaPlayer.start();
            }
        }

        /**
         * 暂停音乐
         */
        public void pauseMusic() {
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.pause();
            }
        }

        /**
         * 下一首
         */
        public void nextMusic() {
            if ((mediaPlayer != null) && (index >= 0 && index < filesPath.length)) {
                mediaPlayer.reset();
                initMediaPlayerFile(index);
                if (index + 1 >= filesPath.length) {//索引值越界
                    index = 0;
                }
                playMusic();
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
         * 获取歌曲长度
         *
         * @return
         */
        public int getProgress() {
            return mediaPlayer.getDuration();
        }

        /**
         * 获取播放位置
         *
         * @return
         */
        public int getPlayPosition() {
            return mediaPlayer.getCurrentPosition();
        }

        /**
         * 播放指定位置
         *
         * @param msec
         */
        public void seekToPosition(int msec) {
            mediaPlayer.seekTo(msec);
        }
    }

    /**
     * 初始化音乐文件
     */
    private void initMediaPlayerFile(int index) {
        try {
            mediaPlayer.setDataSource(filesPath[index].getPath());
            mediaPlayer.prepare();
        } catch (Exception e) {
            LogUtil.d(TAG,"设置资源，准备阶段出错");
            e.printStackTrace();
        }
    }
}
