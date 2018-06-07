package com.robot.robot.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 电力业务类型
 * 
 * @author yobi
 * @email ***
 * @date 2018-02-05 17:25:38
 */
public class TTaxInfoDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//电力业务id
	private Integer id;
	//电力业务类型
	private String taxType;
	//
	private Date createtime;

	/**
	 * 设置：电力业务id
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：电力业务id
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 设置：电力业务类型
	 */
	public void setTaxType(String taxtype) {
		this.taxType = taxtype;
	}
	/**
	 * 获取：电力业务类型
	 */
	public String getTaxType() {
		return taxType;
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
