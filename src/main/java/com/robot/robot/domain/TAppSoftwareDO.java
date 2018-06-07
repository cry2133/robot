package com.robot.robot.domain;

import java.io.Serializable;



/**
 * 机器人版本号表
 * 
 * @author yobi
 * @email ***
 * @date 2018-01-23 11:17:33
 */
public class TAppSoftwareDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//版本号
	private String version;
	//类型
	private String type;
	//版本描述
	private String description;
	//下载地址(文件名，前缀地址靠后台配置）
	private String path;
	//上传用户ID
	private Long userId;
	//机械人编号
	private String robotNo;
	//提交时间
	private String submitTime;

	/**
	 * 设置：
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 设置：版本号
	 */
	public void setVersion(String version) {
		this.version = version;
	}
	/**
	 * 获取：版本号
	 */
	public String getVersion() {
		return version;
	}
	/**
	 * 设置：类型
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * 获取：类型
	 */
	public String getType() {
		return type;
	}
	/**
	 * 设置：
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * 获取：
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * 设置：下载地址(文件名，前缀地址靠后台配置）
	 */
	public void setPath(String path) {
		this.path = path;
	}
	/**
	 * 获取：下载地址(文件名，前缀地址靠后台配置）
	 */
	public String getPath() {
		return path;
	}
	/**
	 * 设置：上传用户ID
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	/**
	 * 获取：上传用户ID
	 */
	public Long getUserId() {
		return userId;
	}
	/**
	 * 设置：机械人编号
	 */
	public void setRobotNo(String robotno) {
		this.robotNo = robotno;
	}
	/**
	 * 获取：机械人编号
	 */
	public String getRobotNo() {
		return robotNo;
	}
	/**
	 * 设置：提交时间
	 */
	public void setSubmitTime(String submitTime) {
		this.submitTime = submitTime;
	}
	/**
	 * 获取：提交时间
	 */
	public String getSubmitTime() {
		return submitTime;
	}
}
