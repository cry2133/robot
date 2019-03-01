package com.robot.robot.domain;

import java.io.Serializable;



/**
 * 机器人编号管理表
 * 
 * @author laoGF
 * @date 2018-08-27 18:19:08
 */
public class TRobotUserDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Long id;
	//机器人编号
	private String robotNo;
	//机器人名称
	private String robotName;
	//机器人使用状态
	private int state;
	//闲聊（0 开启，1 关闭）
	private int chat;
	//默认回答
	private String defaultAnswer;
	//所属地市
	private String city;
	//负责人
	private String principal;
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

	public String getRobotName() {
		return robotName;
	}

	public void setRobotName(String robotName) {
		this.robotName = robotName;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPrincipal() {
		return principal;
	}

	public void setPrincipal(String principal) {
		this.principal = principal;
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

	public int getChat() {
		return chat;
	}

	public void setChat(int chat) {
		this.chat = chat;
	}

	public String getDefaultAnswer() {
		return defaultAnswer;
	}

	public void setDefaultAnswer(String defaultAnswer) {
		this.defaultAnswer = defaultAnswer;
	}
}
