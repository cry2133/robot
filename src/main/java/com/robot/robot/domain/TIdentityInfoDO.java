package com.robot.robot.domain;

import java.io.Serializable;



/**
 * 实名采集信息
 * 
 * @author yobi
 * @email ***
 * @date 2017-12-20 18:01:32
 */
public class TIdentityInfoDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Long id;
	//性别
	private String sex;
	//姓名
	private String name;
	//身份证ID
	private String identityID;
	//民族
	private String nation;
	//地址
	private String address;
	//出生日期
	private String birth;
	//电话号码
	private String phoneNumber;
	//公司信息
	private String companyInfo;
	//公司名称
	private String companyName;
	//职位
	private String position;

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
	 * 设置：性别
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}
	/**
	 * 获取：性别
	 */
	public String getSex() {
		return sex;
	}
	/**
	 * 设置：姓名
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：姓名
	 */
	public String getName() {
		return name;
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
	 * 设置：民族
	 */
	public void setNation(String nation) {
		this.nation = nation;
	}
	/**
	 * 获取：民族
	 */
	public String getNation() {
		return nation;
	}
	/**
	 * 设置：地址
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * 获取：地址
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * 设置：出生日期
	 */
	public void setBirth(String birth) {
		this.birth = birth;
	}
	/**
	 * 获取：出生日期
	 */
	public String getBirth() {
		return birth;
	}
	/**
	 * 设置：电话号码
	 */
	public void setPhoneNumber(String phonenumber) {
		this.phoneNumber = phonenumber;
	}
	/**
	 * 获取：电话号码
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}
	/**
	 * 设置：公司信息
	 */
	public void setCompanyInfo(String companyinfo) {
		this.companyInfo = companyinfo;
	}
	/**
	 * 获取：公司信息
	 */
	public String getCompanyInfo() {
		return companyInfo;
	}
	/**
	 * 设置：公司名称
	 */
	public void setCompanyName(String companyname) {
		this.companyName = companyname;
	}
	/**
	 * 获取：公司名称
	 */
	public String getCompanyName() {
		return companyName;
	}
	/**
	 * 设置：职位
	 */
	public void setPosition(String position) {
		this.position = position;
	}
	/**
	 * 获取：职位
	 */
	public String getPosition() {
		return position;
	}
}
