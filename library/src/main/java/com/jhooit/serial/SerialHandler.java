package com.jhooit.serial;

import android.content.Context;
import android.util.Log;

import com.jhooit.serial.helper.SerialPortHelper;


/**
 * author: created by 闹闹 on 2022/10/11
 * version: v1.0.0
 */
public class SerialHandler {

    private static String TAG = "SerialHandler";
    private static SerialPortHelper serialHelper;

    /**
     * 初始化默认的 WebSocket 连接
     */
    public static SerialPortHelper init(Context context) {
        if (serialHelper == null) {
            synchronized (SerialController.class) {
                if (serialHelper == null) {
                    serialHelper = new SerialPortHelper(context);
                }
            }
        } else {
            Log.e("SerialController", "Default WebSocketManager exists!do not start again!");
        }
        return serialHelper;
    }

    public static SerialPortHelper getDefault() {
        return serialHelper;
    }

}
