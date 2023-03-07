package com.jhooit.serial.helper;

import android.content.Context;
import android.content.Intent;
import android.util.Log;


import com.jhooit.serial.model.SConstants;
import com.jhooit.serial.util.DataUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import android_serialport_api.SerialPort;

/**
 * @author by AllenJ on 2018/4/20.
 * <p>
 * 通过串口用于接收或发送数据
 */

public class SerialPortHelper {

    private String TAG = "SerialPortHelper";

    private Context mContext;
    private int mDataLength;
    private SerialPort serialPort = null;
    private InputStream inputStream = null;
    private OutputStream outputStream = null;
    private ReceiveThread mReceiveThread = null;
    private boolean isStart = false;
    public List<String> receive = new ArrayList<String>();

    public SerialPortHelper(Context context) {
        this.mContext = context;
    }

    /**
     * 打开串口，接收数据
     * 通过串口，接收单片机发送来的数据
     */
    public void openSerialPort(String SPORT_NAME, int baudRate, int length) {
        try {
            serialPort = new SerialPort(new File(SPORT_NAME), baudRate, 0);
            //调用对象SerialPort方法，获取串口中"读和写"的数据流
            inputStream = serialPort.getInputStream();
            outputStream = serialPort.getOutputStream();
            isStart = true;
            mDataLength = length;

        } catch (IOException e) {
            e.printStackTrace();
        }
        getSerialPort();
    }

    /**
     * 关闭串口
     * 关闭串口中的输入输出流
     */
    public void closeSerialPort() {
        Log.i("test", "关闭串口");
        try {
            if (inputStream != null) {
                inputStream.close();
            }
            if (outputStream != null) {
                outputStream.close();
            }
            isStart = false;
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 发送数据
     * 通过串口，发送数据到单片机
     *
     * @param data 要发送的数据
     */
    public void sendSerialPort(String data) {
        try {
            byte[] sendData = DataUtils.HexToByteArr(data);
            outputStream.write(sendData);
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 自定义数据
     *
     * @param
     */
    public void sendDirectTxt(byte[] sendData, boolean isXT) {
        try {
            outputStream.write(sendData);
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void getSerialPort() {
        if (mReceiveThread == null) {
            mReceiveThread = new ReceiveThread();
        }
        mReceiveThread.start();
    }

    /**
     * 接收串口数据的线程
     */

    private class ReceiveThread extends Thread {
        @Override
        public void run() {
            super.run();
            //条件判断，只要条件为true，则一直执行这个线程
            while (isStart) {
                if (inputStream == null) {
                    return;
                }
                byte[] readData = new byte[1024];
                try {
                    int size = inputStream.read(readData);
                    if (size > 0) {
                        String readString = DataUtils.ByteArrToHex(readData);
                        receive(readString);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void receive(String readString) {
        String[] s = readString.split(" ");
        receive.clear();
        for (int i = 0; i < mDataLength; i++) {
            receive.add(s[i]);
        }
        if (receive.size() == mDataLength) {
//            Log.d(TAG, "接收到下位机数据 --> 内容：" + receive.toString() + " - 长度：" + receive.size());
            Intent intent = new Intent(SConstants.HY_SEND);
            intent.putExtra(SConstants.TYPE, SConstants.DT);
            intent.putExtra(SConstants.MSG, receive.toString());
            //发送广播时
            mContext.sendBroadcast(intent);
            receive.clear();
        }
    }
}
