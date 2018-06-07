package com.robot.robot.bean;

import com.alibaba.fastjson.JSON;

/**
 * Created by Administrator on 2017/8/21.
 */
public class MessageModel {

    /**
     * 数据
     */
    private String data;

    /**
     * 签名
     */
    private String sign;

    public MessageModel(String data, String sign) {
        this.data = data;
        this.sign = sign;
    }

    public String getData() {
        return data;
    }

    public String getSign() {
        return sign;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

}