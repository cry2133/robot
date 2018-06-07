package com.robot.robot.bean;

/**
 * 文件下载模型
 * Created by yao on 2017/8/21.
 */
public class DeliveryModel {

    /**
     * 交易号
     */
    private String downloadUrl;

    private String area;

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }


}