package com.robot.robot.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 智能排队
 * 
 * @author yobi
 * @email ***
 * @date 2018-06-19 18:30:53
 */
public class TQueueInfoDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//智能排队id
	private Integer id;
	//智能排队类型
	private String queueType;
	//智能排队说明
	private String queueState;
	//
	private Date createtime;

	/**
	 * 设置：智能排队id
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：智能排队id
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 设置：智能排队类型
	 */
	public void setQueueType(String queueType) {
		this.queueType = queueType;
	}
	/**
	 * 获取：智能排队类型
	 */
	public String getQueueType() {
		return queueType;
	}
	/**
	 * 设置：智能排队说明
	 */
	public void setQueueState(String queueState) {
		this.queueState = queueState;
	}
	/**
	 * 获取：智能排队说明
	 */
	public String getQueueState() {
		return queueState;
	}
	/**
	 * 设置：
	 */
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	/**
	 * 获取：
	 */
	public Date getCreatetime() {
		return createtime;
	}
}
