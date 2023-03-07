package com.jhooit.serial.model;


/**
 * 常量配置
 */
public class SConstants {

    //串口 立式3 台式2
    public static final String SPORT_NAME_0 = "/dev/ttyS0";
    public static final String SPORT_NAME_1 = "/dev/ttyS1";
    public static final String SPORT_NAME_2 = "/dev/ttyS2";
    public static final String SPORT_NAME_3 = "/dev/ttyS3";
    public static final String SPORT_NAME_4 = "/dev/ttyS4";
    public static final String SPORT_NAME_5 = "/dev/ttyS5";
    public static final String SPORT_NAME_7 = "/dev/ttyS7";
    public static final String SPORT_NAME_8 = "/dev/ttyS8";
    public static final String SPORT_NAME_9 = "/dev/ttyS9";

    //波特率
    public static final int BAUD_RATE_9600 = 9600;
    public static final int BAUD_RATE_115200 = 115200;


    //位数（长度）
    public static final String HY_SEND = "com.jhooit.serial.send";

    //
    public static final String TYPE = "type";
    public static final String MSG = "msg";

    //心跳
    public static final String XT = "XT";
    public static final String DT = "DT";
    //错误
    public static final String SC = "SC";
    //正确
    public static final String ZC = "ZC";
    //关闭
    public static final String GC = "GC";
    //开启
    public static final String KS = "KS";
    //异常
    public static final String ER = "ER";

}
