package com.robot.robot.common;

/**
 * 
 * 发票上传下载地址
 *
 * @Author 	Laogf
 * @Version 	1.0 
 * @Data 	2018年5月17日
 */
public interface UrlManagement {
	 /**
     *	下载地址
     */
    String downloadPath = "http://192.168.0.102:8080/uploadfile/";

    /**
     *	保存地址
     */
    String savaPath = "D:\\toolbox\\apache-tomcat-8.5.14-windows-x64\\webapps\\uploadfile\\";
    
    /**
     *	上传地址
     */
    String uploadPath = "http://192.168.0.102:8080/tax4-springmvc/ticket/fileUpload";

    /**
     *	更新APK地址
     */
    String updatePath = "http://192.168.0.102:8080/tax4updata/";
    //String updatePath = "http://10.130.242.71:80/robot/software/";

}
