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
	private int taxID;

	
	//非持久化字段
	
	//业务类型名称
	private String taxType;
	
	/**
	 * 获取：业务类型名称
	 */
	public String getTaxType() {
		return taxType;
	}
	/**
	 * 设置：业务类型名称
	 */
	public void setTaxType(String taxType) {
		this.taxType = taxType;
	}
	
	
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
	 * 设置：预约业务类型ID
	 */
	public void setTaxID(int taxID) {
		this.taxID = taxID;
	}
	/**
	 * 获取：预约业务类型ID
	 */
	public int getTaxID() {
		return taxID;
	}
}
