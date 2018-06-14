package com.robot.robot.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 电力业务类型
 * 
 * @author yobi
 * @email ***
 * @date 2018-06-11 10:28:48
 */
public class TTaxInfoDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//电力业务id
	private Integer id;
	//电力业务类型
	private String taxtype;
	//业务所需资料
	private String taxmaterials;
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
	public void setTaxtype(String taxtype) {
		this.taxtype = taxtype;
	}
	/**
	 * 获取：电力业务类型
	 */
	public String getTaxtype() {
		return taxtype;
	}
	/**
	 * 设置：业务所需资料
	 */
	public void setTaxmaterials(String taxmaterials) {
		this.taxmaterials = taxmaterials;
	}
	/**
	 * 获取：业务所需资料
	 */
	public String getTaxmaterials() {
		return taxmaterials;
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
