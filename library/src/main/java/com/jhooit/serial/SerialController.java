package com.jhooit.serial;

import android.content.Context;

import com.jhooit.serial.helper.SerialPortHelper;


/**
 * 串口控制器
 * author: created by 闹闹 on 2022/5/9
 * version: v1.0.0
 */
public class SerialController {

    /**
     * 初始化与主板连接
     *
     * @param context
     * @param sportName 窗口名称
     * @param baudRate  比特率
     * @param length    返回值长度
     */
    public static void initControl(Context context, String sportName, int baudRate, int length) {
        SerialPortHelper init = SerialHandler.init(context);
        init.openSerialPort(sportName, baudRate, length);
    }
}
