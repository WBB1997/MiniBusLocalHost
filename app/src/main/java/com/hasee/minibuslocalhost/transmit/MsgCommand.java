package com.hasee.minibuslocalhost.transmit;

/**
 * 消息命令
 */
public enum MsgCommand {
    Can_state_GearPos,//档位信息
    EPB_Dig_Sata_Status,//EPB状态
    EPB_Dig_Sata_Indication,//EPB状态灯显示
    Can_num_MotSpeed,//电机当前转速
    EPS_Dig_Alm_EPSWarning,//EPS 警告灯信号
    BCM_Dig_Ord_HandLightCtr,//手势灯光控制信号
    BCM_Flg_Stat_LeftTurningLamp,//左转向状态信号
    BCM_Flg_Stat_RightTurningLamp,//右转向状态信号
    BCM_Flg_Stat_HandLightCtr,//手势灯光控制状态信号
    BCM_Flg_Stat_HighBeam,//远光灯状态信号
    BCM_Flg_Stat_LowBeam,//近光灯状态信号
    BCM_Flg_Stat_RearFogLamp,//后雾灯状态信号
    BCM_Flg_Stat_DangerAlarmLamp,//危险报警灯控制（双闪）状态信号
    BCM_Flg_Stat_BrakeLamp,//制动灯状态信号
    BCM_Flg_Stat_BackupLamp,//倒车灯状态信号
    BCM_Flg_Stat_SeatSensor1,//座椅传感器1
    BCM_Flg_Stat_SeatSensor2,//座椅传感器2
    BCM_Flg_Stat_SeatSensor3,//座椅传感器3
    BCM_Flg_Stat_SeatSensor4,//座椅传感器4
    BCM_Flg_Stat_SeatSensor5,//座椅传感器5
    BCM_Flg_Stat_SeatSensor6,//座椅传感器6
    BCM_Flg_Stat_BeltsSensor1,//安全带传感器1
    BCM_Flg_Stat_BeltsSensor2,//安全带传感器2
    BCM_Flg_Stat_BeltsSensor3,//安全带传感器3
    BCM_Flg_Stat_BeltsSensor4,//安全带传感器4
    BCM_Flg_Stat_BeltsSensor5,//安全带传感器5
    BCM_Flg_Stat_BeltsSensor6,//安全带传感器6
    BCM_OutsideTemp,//车外温度
    BCM_InsideTemp, //车内温度
    HAD_Dig_Ord_SystemStatus, // HAD系统运行状态信号
    HAD_GPSLongitud, //经度
    HAD_GPSLatitude, //纬度
    HAD_GPSPositioningStatus,//GPS状态
    ESC_Ang_Stat_ActStatus,//ESC工作状态
    PCG_Left_Work_Sts,//左门状态信息
    PCG_Left_Error_Mode,//左门故障模式
    PCG_Left_Anti_Pinch_Mode,//左门防夹类型
    PCG_Left_Open_Count,//左门开门角度信息
    PCG_Right_Work_Sts,//右门状态信息
    PCG_Right_Error_Mode,//右门故障模式
    PCG_Right_Anti_Pinch_Mode,//右门防夹类型
    PCG_Right_Open_Count,//右门开门角度信息
    HMI_Dig_Ord_HighBeam, //远光灯控制
    HMI_Dig_Ord_LoWBeam,  //近光灯控制
    HMI_Dig_Ord_LeftTurningLamp, // 左转向灯控制
    HMI_Dig_Ord_RightTurningLamp, // 右转向灯控制
    HMI_Dig_Ord_RearFogLamp, // 后雾灯控制
    HMI_Dig_Ord_DoorLock, // 门锁控制
    HMI_Dig_Ord_Alam, // 低速报警
    HMI_Dig_Ord_Driver_model, // 驾驶模式
    HMI_Dig_Ord_air_model, // 空调模式
    HMI_Dig_Ord_air_grade, // 空调档位
    HMI_Dig_Ord_eBooster_Warning, // 制动液面报警
    HMI_Dig_Ord_FANPWM_Control, //风扇PWM占空比控制信号
    OBU_LocalTime, // 本地时间
    OBU_CurrentDrivingRoadIDNum, // 当前行驶路线id
    OBC_NextStationIDNumb, // 下一个站点id
    OBU_DistanceForNextStation, // 距离下一个站点的距离
    OBU_TimeForArrivingNextStation, // 预计到达下一个站点所需距离
    OBU_WeatherCondition, // 空气状况
    BMS_SOC, // 动力电池剩余电量
    Can_state_PowerReady, // Ready指示灯
    Can_RemainKm, // 剩余里程数
    Can_num_HVMaxTemp, // 最高单体温度
    Can_num_HVMinTemp, // 最低单体温度
    Can_num_PackAverageTemp // 电池包平均温度
}
