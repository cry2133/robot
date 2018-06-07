package com.robot.robot.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 打印机设置表
 * 
 * @author yobi
 * @email ***
 * @date 2017-12-26 09:55:51
 */
public class TPrinterSettingDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Long id;
	//打印机名称
	private String printerName;
	//创建者
	private String creater;
	//创建者
	private String createName;
	//更新时间
	private Date updatetime;

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
	 * 设置：打印机名称
	 */
	public void setPrinterName(String printerName) {
		this.printerName = printerName;
	}
	/**
	 * 获取：打印机名称
	 */
	public String getPrinterName() {
		return printerName;
	}
	/**
	 * 设置：创建者
	 */
	public void setCreater(String creater) {
		this.creater = creater;
	}
	/**
	 * 获取：创建者
	 */
	public String getCreater() {
		return creater;
	}
	/**
	 * 设置：更新时间
	 */
	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}
	/**
	 * 获取：更新时间
	 */
	public Date getUpdatetime() {
		return updatetime;
	}
	public String getCreateName() {
		return createName;
	}
	public void setCreateName(String createName) {
		this.createName = createName;
	}
	
}
