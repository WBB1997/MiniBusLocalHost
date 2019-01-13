package com.hasee.minibuslocalhost.bean;

/**
 * 消息命令
 */
public class IntegerCommand {
    //CAN发给主控屏的命令号
    public static final int can_state_GearPos = 1;//档位信息
    public static final int EPB_Dig_Sata_Status = 2;//EPB状态
    public static final int EPB_Dig_Sata_Indication = 3;//EPB状态灯显示
    public static final int can_num_MotSpeed = 4;//电机当前转速
    public static final int EPS_Dig_Alm_EPSWarning = 5;//EPS 警告灯信号
    public static final int BCM_Dig_Ord_HandLightCtr = 6;//手势灯光控制信号
    public static final int BCM_Flg_Stat_LeftTurningLamp = 7;//左转向状态信号
    public static final int BCM_Flg_Stat_RightTurningLamp = 8;//右转向状态信号
    public static final int BCM_Flg_Stat_HandLightCtr = 9;//手势灯光控制状态信号
    public static final int BCM_Flg_Stat_HighBeam = 10;//远光灯状态信号
    public static final int BCM_Flg_Stat_LowBeam = 11;//近光灯状态信号
    public static final int BCM_Flg_Stat_RearFogLamp = 12;//后雾灯状态信号
    public static final int BCM_Flg_Stat_DangerAlarmLamp = 13;//危险报警灯控制（双闪）状态信号
    public static final int BCM_Flg_Stat_BrakeLamp = 14;//制动灯状态信号
    public static final int BCM_Flg_Stat_BackupLamp = 15;//倒车灯状态信号
    public static final int BCM_Flg_Stat_SeatSensor1 = 16;//座椅传感器1
    public static final int BCM_Flg_Stat_SeatSensor2 = 17;//座椅传感器2
    public static final int BCM_Flg_Stat_SeatSensor3 = 18;//座椅传感器3
    public static final int BCM_Flg_Stat_SeatSensor4 = 19;//座椅传感器4
    public static final int BCM_Flg_Stat_SeatSensor5 = 20;//座椅传感器5
    public static final int BCM_Flg_Stat_SeatSensor6 = 21;//座椅传感器6
    public static final int BCM_Flg_Stat_BeltsSensor1 = 22;//安全带传感器1
    public static final int BCM_Flg_Stat_BeltsSensor2 = 23;//安全带传感器2
    public static final int BCM_Flg_Stat_BeltsSensor3 = 24;//安全带传感器3
    public static final int BCM_Flg_Stat_BeltsSensor4 = 25;//安全带传感器4
    public static final int BCM_Flg_Stat_BeltsSensor5 = 26;//安全带传感器5
    public static final int BCM_Flg_Stat_BeltsSensor6 = 27;//安全带传感器6
    public static final int BCM_OutsideTemp = 28;//车外温度
    public static final int BCM_InsideTemp = 29;//车内温度
    public static final int HAD_GPSLongitude = 30;//经度
    public static final int HAD_GPSLatitude = 31;//纬度
    public static final int ESC_Ang_Stat_ActStatus = 33;//ESC工作状态
    //主控屏上部分
    public static final int OBU_LocalTime = 53;//本地时间
    public static final int BMS_SOC = 59;//动力电池剩余电量SOC
    public static final int HAD_GPSPositioningStatus = 32;//GPS状态

    public static final int OBU_WeatherCondition = 58;//空气状况
    public static final int can_state_PowerReady = 60;//Ready指示灯
    public static final int can_RemainKm = 61;//剩余里程数
    public static final int can_num_HVMaxTemp = 62;//最高单体温度
    public static final int can_num_HVMinTemp = 63;//最低单体温度
    public static final int can_num_PackAverageTemp = 64;//电池包平均温度
    //左车门
    public static final int PCG_Left_Work_Sts = 34;//左门状态信息
    public static final int PCG_Left_Error_Mode = 35;//左门故障模式
    public static final int PCG_Left_Anti_Pinch_Mode = 36;//左门防夹类型
    public static final int PCG_Left_Open_Count = 37;//左门开门角度信息
    //右车门
    public static final int PCG_Right_Work_Sts = 38;//右门状态信息
    public static final int PCG_Right_Error_Mode = 39;//右门故障模式
    public static final int PCG_Right_Anti_Pinch_Mode = 40;//右门防夹类型
    public static final int PCG_Right_Open_Count = 41;//右门开门角度信息
    //主机发送给CAN的命令号
    public static final int HMI_Dig_Ord_HighBeam = 42;//远光灯控制
    public static final int HMI_Dig_Ord_LowBeam = 43;//近光灯控制
    public static final int HMI_Dig_Ord_LeftTurningLamp = 44;//左转向灯控制
    public static final int HMI_Dig_Ord_RightTurningLamp = 45;//右转向灯控制
    public static final int HMI_Dig_Ord_RearFogLamp = 46;//后雾灯控制
    public static final int HMI_Dig_Ord_DoorLock = 47;//门锁控制
    public static final int HMI_Dig_Ord_Alam = 48;//低速报警
    public static final int HMI_Dig_Ord_Driver_model = 49;//驾驶模式
    public static final int HMI_Dig_Ord_air_model = 50;//空调模式
    public static final int HMI_Dig_Ord_air_grade = 51;//空调档位
    public static final int HMI_Dig_Ord_eBooster_Warning = 52;//制动液面报警
    //
    public static final int OBU_CurrentDrivingRoadIDNum = 54;//当前行驶线路ID
    public static final int OBU_NextStationIDNumb = 55;//下一个站点ID
    public static final int OBU_DistanceForNextStation = 56;//距离下一个站点的距离
    public static final int OBU_TimeForArrivingNextStation = 57;//预计到达下一个站点所需时间
}
