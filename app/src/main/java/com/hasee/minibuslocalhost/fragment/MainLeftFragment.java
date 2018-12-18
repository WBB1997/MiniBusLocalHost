package com.hasee.minibuslocalhost.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.hasee.minibuslocalhost.R;
import com.hasee.minibuslocalhost.activity.MainActivity;


/**
 * 左边Fragment
 */
public class MainLeftFragment extends Fragment {
    private Context mContext;//上下文
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
    private ImageButton leftFragmentCoolAir;//冷气
    private ImageButton leftFragmentHotAir;//冷气
    private ImageButton leftFragmentDeFog;//除雾
    private TextView leftFragmentConditionSize;//风扇大小
    private SeekBar leftFragmentSeekBar;//风扇滑动条


    public MainLeftFragment(){
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (MainActivity) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_left,container,false);
        leftFragmentCarCloseDoor = (ImageView) view.findViewById(R.id.leftFragment_car_close_door);
        leftFragmentCarOpenDoor = (ImageView) view.findViewById(R.id.leftFragment_car_open_door);
        leftFragmentCarFoglightOpen = (ImageView) view.findViewById(R.id.leftFragment_car_foglight_open);
        leftFragmentCarLeftlightOpen = (ImageView) view.findViewById(R.id.leftFragment_car_leftlight_open);
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
        leftFragmentLeftLight = (ImageButton) view.findViewById(R.id.leftFragment_leftLight);
        leftFragmentLeftLight.setOnClickListener(onClickListener);
        leftFragmentRightLight = (ImageButton) view.findViewById(R.id.leftFragment_rightLight);
        leftFragmentRightLight.setOnClickListener(onClickListener);
        leftFragmentAuto = (LinearLayout) view.findViewById(R.id.leftFragment_auto);
        leftFragmentErrorLight = (ImageButton) view.findViewById(R.id.leftFragment_errorLight);
        leftFragmentErrorLight.setOnClickListener(onClickListener);
        leftFragmentCoolAir = (ImageButton) view.findViewById(R.id.leftFragment_coolAir);
        leftFragmentCoolAir.setOnClickListener(onClickListener);
        leftFragmentHotAir = (ImageButton) view.findViewById(R.id.leftFragment_hotAir);
        leftFragmentHotAir.setOnClickListener(onClickListener);
        leftFragmentDeFog = (ImageButton) view.findViewById(R.id.leftFragment_deFog);
        leftFragmentConditionSize = (TextView) view.findViewById(R.id.leftFragment_condition_size);
        leftFragmentSeekBar = (SeekBar) view.findViewById(R.id.leftFragment_seekBar);

        return view;
    }


    /**
     * 事件点击监听器
     */
    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.leftFragment_lowBeam:{//近光灯
                    leftFragmentLowBeam.setActivated(!leftFragmentLowBeam.isActivated());
                    if(leftFragmentLowBeam.isActivated()){//近光灯开启
                        if(leftFragmentHighBeam.isActivated()){//远光灯是开的
                            leftFragmentHighBeam.setActivated(false);//关闭远光灯
                            leftFragmentCarHighbeamOpen.setVisibility(View.INVISIBLE);
                        }
                        leftFragmentCarLowbeamOpen.setVisibility(View.VISIBLE);
                    }else{
                        leftFragmentCarLowbeamOpen.setVisibility(View.INVISIBLE);
                    }
                    break;
                }
                case R.id.leftFragment_highBeam:{//远光灯
                    leftFragmentHighBeam.setActivated(!leftFragmentHighBeam.isActivated());
                    if(leftFragmentHighBeam.isActivated()){//远光灯开启
                        if(leftFragmentLowBeam.isActivated()){//近光灯是开的
                            leftFragmentLowBeam.setActivated(false);//关闭近光灯
                            leftFragmentCarLowbeamOpen.setVisibility(View.INVISIBLE);
                        }
                        leftFragmentCarHighbeamOpen.setVisibility(View.VISIBLE);
                    }else{
                        leftFragmentCarHighbeamOpen.setVisibility(View.INVISIBLE);
                    }
                    break;
                }
                case R.id.leftFragment_front_fogLight:{//前雾灯
                    leftFragmentFrontFogLight.setActivated(!leftFragmentFrontFogLight.isActivated());
                    break;
                }
                case R.id.leftFragment_back_fogLight:{//后雾灯
                    leftFragmentBackFogLight.setActivated(!leftFragmentBackFogLight.isActivated());
                    if(leftFragmentBackFogLight.isActivated()){
                        leftFragmentCarFoglightOpen.setVisibility(View.VISIBLE);
                    }else{
                        leftFragmentCarFoglightOpen.setVisibility(View.INVISIBLE);
                    }
                    break;
                }
                case R.id.leftFragment_leftLight:{//左转向灯
                    leftFragmentLeftLight.setActivated(!leftFragmentLeftLight.isActivated());
                    if(leftFragmentLeftLight.isActivated()){//左转向灯开启
                        if(leftFragmentRightLight.isActivated()){//右转向灯是开的
                            leftFragmentRightLight.setActivated(false);//关闭右转向灯
                        }
                        leftFragmentCarLeftlightOpen.setVisibility(View.VISIBLE);
                    }else{
                        leftFragmentCarLeftlightOpen.setVisibility(View.INVISIBLE);
                    }
                    break;
                }
                case R.id.leftFragment_rightLight:{//右转向灯
                    leftFragmentRightLight.setActivated(!leftFragmentRightLight.isActivated());
                    if(leftFragmentRightLight.isActivated()){//右转向灯开启
                        if(leftFragmentRightLight.isActivated()){//左转向灯是开的
                            leftFragmentLeftLight.setActivated(false);//关闭左转向灯
                            leftFragmentCarLeftlightOpen.setVisibility(View.INVISIBLE);
                        }
                    }else{
                    }
                    break;
                }
                case R.id.leftFragment_errorLight:{//警示灯
                    leftFragmentErrorLight.setActivated(!leftFragmentErrorLight.isActivated());
                    if(leftFragmentErrorLight.isActivated()){//警示灯开启
                        leftFragmentRightLight.setActivated(true);//左转向灯开启
                        leftFragmentLeftLight.setActivated(true);//右转向灯开启
                    }else{//警示灯关闭
                        leftFragmentRightLight.setActivated(false);//左转向灯关闭
                        leftFragmentLeftLight.setActivated(false);//右转向灯关闭
                    }
                    break;
                }
            }
        }
    };

}
