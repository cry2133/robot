package com.robot.robot.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 机器人编号管理表
 * 
 * @author laoGF
 * @email ***
 * @date 2018-08-27 18:19:08
 */
public class TRobotUserDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Long id;
	//机器人编号
	private String robotNo;
	//所属用户ID
	private Long userId;

	/**
	 * 设置：
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 获取：
	 */
	public Long getId() {
		return id;
	}
	/**
	 * 设置：机器人编号
	 */
	public void setRobotNo(String robotNo) {
		this.robotNo = robotNo;
	}
	/**
	 * 获取：机器人编号
	 */
	public String getRobotNo() {
		return robotNo;
	}
	/**
	 * 设置：所属用户ID
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	/**
	 * 获取：所属用户ID
	 */
	public Long getUserId() {
		return userId;
	}
}
