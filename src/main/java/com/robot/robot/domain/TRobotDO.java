package com.robot.robot.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 机械人账户
 * 
 * @author yobi
 * @email ***
 * @date 2018-01-16 10:58:34
 */
public class TRobotDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Long id;
	//机械人编号
	private String robotNo;
	//密码
	private String password;
	//使用区域
	private String area;
	//
	private Date createtime;

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
	 * 设置：机械人编号
	 */
	public void setRobotNo(String robotNo) {
		this.robotNo = robotNo;
	}
	/**
	 * 获取：机械人编号
	 */
	public String getRobotNo() {
		return robotNo;
	}
	/**
	 * 设置：密码
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * 获取：密码
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * 设置：使用区域
	 */
	public void setArea(String area) {
		this.area = area;
	}
	/**
	 * 获取：使用区域
	 */
	public String getArea() {
		return area;
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
