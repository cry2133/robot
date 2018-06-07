package com.robot.robot.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 机器人故障日志表
 * 
 * @author yobi
 * @email ***
 * @date 2018-01-23 11:17:42
 */
public class TRobotLogDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//机械人编号
	private String robotNo;
	//机械人名称
	private String robotName;
	//错误等级
	private String level;
	//错误信息
	private String message;
	//标签
	private String tag;
	//时间
	private Date time;

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
	 * 设置：机械人名称
	 */
	public void setRobotName(String robotName) {
		this.robotName = robotName;
	}
	/**
	 * 获取：机械人名称
	 */
	public String getRobotName() {
		return robotName;
	}
	/**
	 * 设置：错误等级
	 */
	public void setLevel(String level) {
		this.level = level;
	}
	/**
	 * 获取：错误等级
	 */
	public String getLevel() {
		return level;
	}
	/**
	 * 设置：错误信息
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	/**
	 * 获取：错误信息
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * 设置：标签
	 */
	public void setTag(String tag) {
		this.tag = tag;
	}
	/**
	 * 获取：标签
	 */
	public String getTag() {
		return tag;
	}
	/**
	 * 设置：时间
	 */
	public void setTime(Date time) {
		this.time = time;
	}
	/**
	 * 获取：时间
	 */
	public Date getTime() {
		return time;
	}
}
