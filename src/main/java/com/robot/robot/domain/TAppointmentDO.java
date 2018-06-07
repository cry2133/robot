package com.robot.robot.domain;

import java.io.Serializable;



/**
 * 预约表
 * 
 * @author yobi
 * @email ***
 * @date 2018-01-18 15:24:03
 */
public class TAppointmentDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Long id;
	//身份证ID
	private String identityID;
	//预约时间
	private String appointmentTime;
	//预约类型
	private String type;

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
	 * 设置：身份证ID
	 */
	public void setIdentityID(String identityid) {
		this.identityID = identityid;
	}
	/**
	 * 获取：身份证ID
	 */
	public String getIdentityID() {
		return identityID;
	}
	/**
	 * 设置：预约时间
	 */
	public void setAppointmentTime(String appointmenttime) {
		this.appointmentTime = appointmenttime;
	}
	/**
	 * 获取：预约时间
	 */
	public String getAppointmentTime() {
		return appointmentTime;
	}
	/**
	 * 设置：预约类型
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * 获取：预约类型
	 */
	public String getType() {
		return type;
	}
}
