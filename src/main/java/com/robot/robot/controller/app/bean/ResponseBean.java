package com.robot.robot.controller.app.bean;

public class ResponseBean {

	/**
     * 请求是否成功
     */
    private boolean success;

    /**
     * 成功响应的数据
     */
    private Object data;

    /**
     * 错误提示的消息
     */
    private String errmsg;

    public ResponseBean() {
    }

    public ResponseBean(boolean success, Object data, String errmsg) {
        this.data = data;
        this.errmsg = errmsg;
        this.success = success;
    }

    public ResponseBean(String errmsg) {
        this.success = false;
        this.errmsg = errmsg;
    }

    public static ResponseBean success() {
        return new ResponseBean(true, null, null);
    }

    public static ResponseBean fail() {
        return new ResponseBean(false, null, null);
    }

    public static ResponseBean success(Object data) {
        return new ResponseBean(true, data, null);
    }

    public static ResponseBean fail(String errmsg) {
        return new ResponseBean(false, null, errmsg);
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
        this.success = true;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
        this.success = false;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    @Override
    public String toString() {
        return "ResponseBean [data=" + data + ", errmsg=" + errmsg + ", success=" + success + "]";
    }

}
