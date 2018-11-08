package com.robot.robot.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 咨询建议表
 * 
 * @author laoGF
 * @email ***
 * @date 2018-09-07 18:14:00
 */
public class TConsultationDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//ID
	private Long id;
	//标题
	private String title;
	//内容
	private String context;
	//标签
	private String tag;
	//创建时间
	private Date createtime;
	//用戶ID
	private Long userId;
	//机器人编号
	private String robotNo;

	/**
	 * 设置：ID
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 获取：ID
	 */
	public Long getId() {
		return id;
	}
	/**
	 * 设置：标题
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * 获取：标题
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * 设置：内容
	 */
	public void setContext(String context) {
		this.context = context;
	}
	/**
	 * 获取：内容
	 */
	public String getContext() {
		return context;
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
	 * 设置：创建时间
	 */
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	/**
	 * 获取：创建时间
	 */
	public Date getCreatetime() {
		return createtime;
	}
	/**
	 * 设置：用戶ID
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	/**
	 * 获取：用戶ID
	 */
	public Long getUserId() {
		return userId;
	}

	public String getRobotNo() {
		return robotNo;
	}

	public void setRobotNo(String robotNo) {
		this.robotNo = robotNo;
	}
}
