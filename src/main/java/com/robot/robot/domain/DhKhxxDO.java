package com.robot.robot.domain;

import java.io.Serializable;



/**
 * 身份证对应用户-来源回流库 (客户信息)
 * 
 * @author yobi
 * @email ***
 * @date 2018-03-28 14:05:50
 */
public class DhKhxxDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//用户编号
	private String yhbh;
	//身份证
	private String zjhm;
	//客户名称
	private String yhmc;
	//用电地址
	private String yddz;

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
	 * 设置：用户编号
	 */
	public void setYhbh(String yhbh) {
		this.yhbh = yhbh;
	}
	/**
	 * 获取：用户编号
	 */
	public String getYhbh() {
		return yhbh;
	}
	/**
	 * 设置：身份证
	 */
	public void setZjhm(String zjhm) {
		this.zjhm = zjhm;
	}
	/**
	 * 获取：身份证
	 */
	public String getZjhm() {
		return zjhm;
	}
	/**
	 * 设置：客户名称
	 */
	public void setYhmc(String yhmc) {
		this.yhmc = yhmc;
	}
	/**
	 * 获取：客户名称
	 */
	public String getYhmc() {
		return yhmc;
	}
	/**
	 * 设置：用电地址
	 */
	public void setYddz(String yddz) {
		this.yddz = yddz;
	}
	/**
	 * 获取：用电地址
	 */
	public String getYddz() {
		return yddz;
	}
}
