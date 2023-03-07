package com.jhooit.serial.model;

import java.util.List;

/**
 * 消息
 * author: created by 闹闹 on 2022/10/11
 * version: v1.0.0
 */
public class MessageVo {

    private List<String> message;

    public MessageVo(List<String> message) {
        this.message = message;
    }

    public List<String> getMessage() {
        return message;
    }

    public void setMessage(List<String> message) {
        this.message = message;
    }
}
