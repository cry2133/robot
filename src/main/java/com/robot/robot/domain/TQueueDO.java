package com.robot.robot.domain;

import java.io.Serializable;


/**
 * 智能排队
 * 
 */
public class TQueueDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//智能排队id
	private Long id;
	//身份证ID
	private String identityID;
	//智能排队时间
	private String queueTime;
	//排队业务ID
	private int taxID;

	/**
	 * 设置：智能排队id
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 获取：智能排队id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * 设置：身份证ID
	 */
	public void setIdentityID(String identityID) {
		this.identityID = identityID;
	}
	/**
	 * 获取：身份证ID
	 */
	public String getIdentityID() {
		return identityID;
	}
	/**
	 * 设置：智能排队时间
	 */
	public void setQueueTime(String queueTime) {
		this.queueTime = queueTime;
	}
	/**
	 * 获取：智能排队时间
	 */
	public String getQueueTime() {
		return queueTime;
	}
	/**
	 * 设置：排队业务ID
	 */
	public void setTaxID(int taxID) {
		this.taxID = taxID;
	}
	/**
	 * 获取：排队业务ID
	 */
	public int getTaxID() {
		return taxID;
	}
}
