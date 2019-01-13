package com.hasee.minibuslocalhost.fragment;


import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.hasee.minibuslocalhost.R;
import com.hasee.minibuslocalhost.activity.MainActivity;
import com.hasee.minibuslocalhost.util.LogUtil;
import com.hasee.minibuslocalhost.util.VideoSeparateUtil;

import java.io.File;
import java.io.IOException;

import static com.hasee.minibuslocalhost.bean.IntegerCommand.*;
import static com.hasee.minibuslocalhost.transmit.Class.HMI.AIR_GRADE_FIRST_GEAR;
import static com.hasee.minibuslocalhost.transmit.Class.HMI.AIR_GRADE_FIVE_GEAR;
import static com.hasee.minibuslocalhost.transmit.Class.HMI.AIR_GRADE_FOURTH_GEAR;
import static com.hasee.minibuslocalhost.transmit.Class.HMI.AIR_GRADE_OFF;
import static com.hasee.minibuslocalhost.transmit.Class.HMI.AIR_GRADE_SECOND_GEAR;
import static com.hasee.minibuslocalhost.transmit.Class.HMI.AIR_GRADE_THIRD_GEAR;
import static com.hasee.minibuslocalhost.transmit.Class.HMI.AIR_MODEL_COOL;
import static com.hasee.minibuslocalhost.transmit.Class.HMI.AIR_MODEL_DEMIST;
import static com.hasee.minibuslocalhost.transmit.Class.HMI.AIR_MODEL_HEAT;

/**
 * 左边Fragment
 */
public class MainLeftFragment extends Fragment {
    private static final String TAG = "MainLeftFragment";
    private MainActivity activity;//MainActivity
    private ImageView leftFragmentCarCloseDoor;//车门关
    private ImageView leftFragmentCarOpenDoor;//车门开
    private ImageView leftFragmentCarFoglightOpen;//雾灯开
    private ImageView leftFragmentCarLeftlightOpen;//转向灯开
    private ImageView leftFragmentCarLowbeamOpen;//近光灯开
    private ImageView leftFragmentCarHighbeamOpen;//远光灯开
    private ImageButton leftFragmentLowBeam;//近光灯
    private ImageButton leftFragmentHighBeam;//远光灯
    private ImageButton leftFragmentFrontFogLight;//前雾灯
    private ImageButton leftFragmentBackFogLight;//后雾灯
    private ImageButton leftFragmentLeftLight;//左转向灯
    private ImageButton leftFragmentRightLight;//右转向灯
    private LinearLayout leftFragmentAuto;//自动调节
    private ImageButton leftFragmentErrorLight;//警示灯
    private ImageView leftFragmentCoolAirImg;//冷气
    private ImageView leftFragmentHotAirImg;//暖气
    private ImageView leftFragmentDeFogImg;//除雾
    private LinearLayout leftFragmentCoolAir;//冷气布局
    private LinearLayout leftFragmentHotAir;//暖气布局
    private LinearLayout leftFragmentDeFog;//除雾布局
    private TextView leftFragmentConditionSize;//风扇大小
    private SeekBar leftFragmentSeekBar;//风扇滑动条
    //发送CAN总线的数据
    private String clazz = "HMI";//所属类名
    private int field = -1;//属性
    private Object o = null;//状态
    private double singleIndexNum = 100 / 5;//每一档对应的大小
    private int seekBarIndex = 0;

    public MainLeftFragment() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (MainActivity) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_left, container, false);
        leftFragmentCarCloseDoor = view.findViewById(R.id.leftFragment_car_close_door);
        leftFragmentCarOpenDoor = view.findViewById(R.id.leftFragment_car_open_door);
        leftFragmentCarFoglightOpen = view.findViewById(R.id.leftFragment_car_foglight_open);
        leftFragmentCarLeftlightOpen = view.findViewById(R.id.leftFragment_car_leftlight_open);
        leftFragmentCarLowbeamOpen = (ImageView) view.findViewById(R.id.leftFragment_car_lowbeam_open);
        leftFragmentCarHighbeamOpen = (ImageView) view.findViewById(R.id.leftFragment_car_highbeam_open);
        leftFragmentLowBeam = (ImageButton) view.findViewById(R.id.leftFragment_lowBeam);
        leftFragmentLowBeam.setOnClickListener(onClickListener);
        leftFragmentHighBeam = (ImageButton) view.findViewById(R.id.leftFragment_highBeam);
        leftFragmentHighBeam.setOnClickListener(onClickListener);
        leftFragmentFrontFogLight = (ImageButton) view.findViewById(R.id.leftFragment_front_fogLight);
        leftFragmentFrontFogLight.setOnClickListener(onClickListener);
        leftFragmentBackFogLight = (ImageButton) view.findViewById(R.id.leftFragment_back_fogLight);
        leftFragmentBackFogLight.setOnClickListener(onClickListener);
        leftFragmentLeftLight = view.findViewById(R.id.leftFragment_leftLight);
        leftFragmentLeftLight.setOnClickListener(onClickListener);
        leftFragmentRightLight = (ImageButton) view.findViewById(R.id.leftFragment_rightLight);
        leftFragmentRightLight.setOnClickListener(onClickListener);
        leftFragmentAuto = (LinearLayout) view.findViewById(R.id.leftFragment_auto);
        leftFragmentErrorLight = (ImageButton) view.findViewById(R.id.leftFragment_errorLight);
        leftFragmentErrorLight.setOnClickListener(onClickListener);
        leftFragmentCoolAirImg = (ImageView) view.findViewById(R.id.leftFragment_coolAir_img);
        leftFragmentHotAirImg = (ImageView) view.findViewById(R.id.leftFragment_hotAir_img);
        leftFragmentDeFogImg = (ImageView) view.findViewById(R.id.leftFragment_deFog_img);
        leftFragmentCoolAir = (LinearLayout) view.findViewById(R.id.leftFragment_coolAir);
        leftFragmentCoolAir.setOnClickListener(onClickListener);
        leftFragmentHotAir = (LinearLayout) view.findViewById(R.id.leftFragment_hotAir);
        leftFragmentHotAir.setOnClickListener(onClickListener);
        leftFragmentDeFog = (LinearLayout) view.findViewById(R.id.leftFragment_deFog);
        leftFragmentDeFog.setOnClickListener(onClickListener);
        leftFragmentConditionSize = (TextView) view.findViewById(R.id.leftFragment_condition_size);
        leftFragmentSeekBar = (SeekBar) view.findViewById(R.id.leftFragment_seekBar);
        leftFragmentSeekBar.setOnSeekBarChangeListener(onSeekBarChangeListener);
        //设置滑块颜色
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            leftFragmentSeekBar.getThumb().setColorFilter(Color.parseColor("#9cf8f8"), PorterDuff.Mode.SRC_ATOP);
        }
        return view;
    }

    /**
     * 滑动条事件监听
     */
    private SeekBar.OnSeekBarChangeListener onSeekBarChangeListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
//            LogUtil.d(TAG,String.valueOf(progress));
            if(fromUser){
                seekBarIndex = (int) (progress / singleIndexNum);//当前档
                if (seekBarIndex == 0) {
                    seekBarIndex = AIR_GRADE_OFF;
                } else if (seekBarIndex == 1) {
                    seekBarIndex = AIR_GRADE_FIRST_GEAR;
                } else if (seekBarIndex == 2) {
                    seekBarIndex = AIR_GRADE_SECOND_GEAR;
                } else if (seekBarIndex == 3) {
                    seekBarIndex = AIR_GRADE_THIRD_GEAR;
                } else if (seekBarIndex == 4) {
                    seekBarIndex = AIR_GRADE_FOURTH_GEAR;
                } else if (seekBarIndex == 5) {
                    seekBarIndex = AIR_GRADE_FIVE_GEAR;
                }
                leftFragmentConditionSize.setText(String.valueOf(seekBarIndex));
            }
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            //发送最终数据至CAN(1-7档)
            activity.sendToCAN(clazz, HMI_Dig_Ord_air_grade, seekBarIndex);
        }
    };

    /**
     * 事件点击监听器
     */
    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.leftFragment_lowBeam: {//近光灯
                    leftFragmentLowBeam.setActivated(!leftFragmentLowBeam.isActivated());
                    if (leftFragmentLowBeam.isActivated()) {//近光灯开启
                        if (leftFragmentHighBeam.isActivated()) {//远光灯是开的
                            leftFragmentHighBeam.setActivated(false);//关闭远光灯
                            leftFragmentCarHighbeamOpen.setVisibility(View.INVISIBLE);
                        }
                        leftFragmentCarLowbeamOpen.setVisibility(View.VISIBLE);
                    } else {
                        leftFragmentCarLowbeamOpen.setVisibility(View.INVISIBLE);
                    }
                    field = HMI_Dig_Ord_LowBeam;
                    o = leftFragmentLowBeam.isActivated();
                    break;
                }
                case R.id.leftFragment_highBeam: {//远光灯
                    leftFragmentHighBeam.setActivated(!leftFragmentHighBeam.isActivated());
                    if (leftFragmentHighBeam.isActivated()) {//远光灯开启
                        if (leftFragmentLowBeam.isActivated()) {//近光灯是开的
                            leftFragmentLowBeam.setActivated(false);//关闭近光灯
                            leftFragmentCarLowbeamOpen.setVisibility(View.INVISIBLE);
                        }
                        leftFragmentCarHighbeamOpen.setVisibility(View.VISIBLE);
                    } else {
                        leftFragmentCarHighbeamOpen.setVisibility(View.INVISIBLE);
                    }
                    field = HMI_Dig_Ord_HighBeam;
                    o = leftFragmentHighBeam.isActivated();
                    break;
                }
                case R.id.leftFragment_front_fogLight: {//前雾灯
                    leftFragmentFrontFogLight.setActivated(!leftFragmentFrontFogLight.isActivated());
//                    field = HMI.HMI_leftFragmentFrontFogLight;
//                    o = leftFragmentFrontFogLight.isActivated();
                    break;
                }
                case R.id.leftFragment_back_fogLight: {//后雾灯
                    leftFragmentBackFogLight.setActivated(!leftFragmentBackFogLight.isActivated());
                    if (leftFragmentBackFogLight.isActivated()) {
                        leftFragmentCarFoglightOpen.setVisibility(View.VISIBLE);
                    } else {
                        leftFragmentCarFoglightOpen.setVisibility(View.INVISIBLE);
                    }
                    field = HMI_Dig_Ord_RearFogLamp;
                    o = leftFragmentBackFogLight.isActivated();
                    break;
                }
                case R.id.leftFragment_leftLight: {//左转向灯
                    leftFragmentLeftLight.setActivated(!leftFragmentLeftLight.isActivated());
                    if (leftFragmentLeftLight.isActivated()) {//左转向灯开启
                        if (leftFragmentRightLight.isActivated()) {//右转向灯是开的
                            leftFragmentRightLight.setActivated(false);//关闭右转向灯
                        }
                        leftFragmentCarLeftlightOpen.setVisibility(View.VISIBLE);
                    } else {
                        leftFragmentCarLeftlightOpen.setVisibility(View.INVISIBLE);
                    }
//                    if(leftFragmentErrorLight.isActivated()){//如果警示灯是开的关闭它
//                        leftFragmentErrorLight.setActivated(false);
//                        leftFragmentLeftLight.setActivated(true);
//                    }
                    field = HMI_Dig_Ord_LeftTurningLamp;
                    o = leftFragmentLeftLight.isActivated();
                    break;
                }
                case R.id.leftFragment_rightLight: {//右转向灯
                    leftFragmentRightLight.setActivated(!leftFragmentRightLight.isActivated());
                    if (leftFragmentRightLight.isActivated()) {//右转向灯开启
                        if (leftFragmentRightLight.isActivated()) {//左转向灯是开的
                            leftFragmentLeftLight.setActivated(false);//关闭左转向灯
                            leftFragmentCarLeftlightOpen.setVisibility(View.INVISIBLE);
                        }
                    } else {
                    }
//                    if(leftFragmentErrorLight.isActivated()){//如果警示灯是开的关闭它
//                        leftFragmentErrorLight.setActivated(false);
//                    }
                    field = HMI_Dig_Ord_RightTurningLamp;
                    o = leftFragmentRightLight.isActivated();
                    break;
                }
                case R.id.leftFragment_errorLight: {//警示灯
                    leftFragmentErrorLight.setActivated(!leftFragmentErrorLight.isActivated());
//                    if(leftFragmentErrorLight.isActivated()){//警示灯开启
//                        leftFragmentRightLight.setActivated(true);//左转向灯开启
//                        leftFragmentLeftLight.setActivated(true);//右转向灯开启
//                    }else{//警示灯关闭
//                        leftFragmentRightLight.setActivated(false);//左转向灯关闭
//                        leftFragmentLeftLight.setActivated(false);//右转向灯关闭
//                    }
                    break;
                }
                case R.id.leftFragment_coolAir: {//冷气
                    leftFragmentCoolAir.setActivated(!leftFragmentCoolAir.isActivated());
                    if (leftFragmentCoolAir.isActivated()) {//如果冷气是开的
                        leftFragmentCoolAirImg.setActivated(true);
                        //关闭暖气
                        leftFragmentHotAirImg.setActivated(false);
                        leftFragmentHotAir.setActivated(false);
                    } else {
                        leftFragmentCoolAirImg.setActivated(false);
                    }
                    field = HMI_Dig_Ord_air_model;//空调模式
                    o = AIR_MODEL_COOL;//制冷模式
                    break;
                }
                case R.id.leftFragment_hotAir: {//暖气
                    leftFragmentHotAir.setActivated(!leftFragmentHotAir.isActivated());
                    if (leftFragmentHotAir.isActivated()) {//如果暖气是开的
                        leftFragmentHotAirImg.setActivated(true);
                        //关闭冷气
                        leftFragmentCoolAirImg.setActivated(false);
                        leftFragmentCoolAir.setActivated(false);
                    } else {
                        leftFragmentHotAirImg.setActivated(false);
                    }
                    field = HMI_Dig_Ord_air_model;//空调模式
                    o = AIR_MODEL_HEAT;//制热模式
                    break;
                }
                case R.id.leftFragment_deFog: {//除雾
                    leftFragmentDeFog.setActivated(!leftFragmentDeFog.isActivated());
                    if (leftFragmentDeFog.isActivated()) {//如果除雾是开的
                        leftFragmentDeFogImg.setActivated(true);
                    } else {
                        leftFragmentDeFogImg.setActivated(false);
                    }
                    field = HMI_Dig_Ord_air_model;//空调模式
                    o = AIR_MODEL_DEMIST;//除雾模式
                    break;
                }
            }
            activity.sendToCAN(clazz, field, o);
            if(field == HMI_Dig_Ord_air_model){//如果当前是空调模式
                activity.sendToCAN(clazz, HMI_Dig_Ord_air_grade, seekBarIndex);//档位
            }
        }
    };

    /**
     * 更新布局
     */
    public void refresh(JSONObject object) {
        boolean data = object.getBoolean("data");
        int id= object.getIntValue("id");
        LogUtil.d(TAG,"id:"+id);
        switch (id) {
            case BCM_Flg_Stat_LeftTurningLamp: {// 左转
                leftFragmentLeftLight.setActivated(data);
                if (data) {//要求左转开
                    leftFragmentRightLight.setActivated(false);//右转向灯关
                    leftFragmentErrorLight.setActivated(false);//双闪关
                    leftFragmentCarLeftlightOpen.setVisibility(View.VISIBLE);
                } else {
                    leftFragmentCarLeftlightOpen.setVisibility(View.INVISIBLE);
                }
                break;
            }
            case BCM_Flg_Stat_RightTurningLamp: {// 右转
                leftFragmentRightLight.setActivated(data);
                if (data) {
                    leftFragmentLeftLight.setActivated(false);//左转向灯关
                    leftFragmentErrorLight.setActivated(false);//双闪关
                } else {
//
                }
                break;
            }
            case BCM_Flg_Stat_HighBeam: {// 远光灯
                leftFragmentHighBeam.setActivated(data);
                if (data) {
                    leftFragmentLowBeam.setActivated(false);//近光灯关
                    leftFragmentCarLowbeamOpen.setVisibility(View.INVISIBLE);
                    leftFragmentCarHighbeamOpen.setVisibility(View.VISIBLE);
                } else {
                    leftFragmentCarHighbeamOpen.setVisibility(View.INVISIBLE);
                }
                break;
            }
            case BCM_Flg_Stat_LowBeam: {// 近光灯
                leftFragmentLowBeam.setActivated(data);
                if (data) {
                    leftFragmentHighBeam.setActivated(false);//远光灯关
                    leftFragmentCarHighbeamOpen.setVisibility(View.INVISIBLE);
                    leftFragmentCarLowbeamOpen.setVisibility(View.VISIBLE);
                } else {
                    leftFragmentCarLowbeamOpen.setVisibility(View.INVISIBLE);
                }
                break;
            }
            case BCM_Flg_Stat_RearFogLamp: {// 后雾灯
                leftFragmentBackFogLight.setActivated(data);
                if (data) {
                    leftFragmentCarFoglightOpen.setVisibility(View.VISIBLE);
                } else {
                    leftFragmentCarFoglightOpen.setVisibility(View.INVISIBLE);
                }
                break;
            }
            case BCM_Flg_Stat_DangerAlarmLamp: {// 双闪
                leftFragmentErrorLight.setActivated(data);
//                if (data) {//要求双闪开
//                    leftFragmentLeftLight.setActivated(true);
//                    leftFragmentRightLight.setActivated(true);
//                    leftFragmentCarLeftlightOpen.setVisibility(View.VISIBLE);
//                } else {
//                    leftFragmentLeftLight.setActivated(false);
//                    leftFragmentRightLight.setActivated(false);
//                    leftFragmentCarLeftlightOpen.setVisibility(View.INVISIBLE);
//                }
                break;
            }
        }
    }

    /**
     * 播放音频
     */
    private void playAudio() {
        File video = new File(Environment.getExternalStorageDirectory(), "video.mp4");
        File out = new File(Environment.getExternalStorageDirectory(), "audio.mp3");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            VideoSeparateUtil.videoToAudio(video.getPath(), out);
        }
        MediaPlayer mediaPlayer = new MediaPlayer();
        try {
            mediaPlayer.setDataSource(out.getPath());
            mediaPlayer.prepare();
            if (!mediaPlayer.isPlaying()) {
                mediaPlayer.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
