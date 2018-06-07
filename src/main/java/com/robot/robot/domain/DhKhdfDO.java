package com.robot.robot.domain;

import java.io.Serializable;



/**
 * 电费查询-来源回流库  (客户电费)
 * 
 * @author yobi
 * @email ***
 * @date 2018-03-28 14:05:50
 */
public class DhKhdfDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//供电单位编码
	private String gddwbm;
	//供电局组织名称
	private String gdjzzmc;
	//供电所组织名称
	private String gdszzmc;
	//客户编号
	private String yhbh;
	//客户名称
	private String yhmc;
	//用电地址
	private String yddz;
	//用电类型代码
	private String ydlbdm;
	//代码编码名称-对应xt_dmbm.dmbmmc
	private String ydlbdmmc;
	//电费年月
	private String dfny;
	//计费电量
	private String jfdl;
	//应收电费
	private String ysdf;
	//抄表周期
	private String cbzq;
	//抄表周期名称-对应xt_dmbm.dmbmmc
	private String cbzqmc;
	//上次抄表日期
	private String sccbrq;
	//本次抄表日期
	private String bccbrq;

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
	 * 设置：供电单位编码
	 */
	public void setGddwbm(String gddwbm) {
		this.gddwbm = gddwbm;
	}
	/**
	 * 获取：供电单位编码
	 */
	public String getGddwbm() {
		return gddwbm;
	}
	/**
	 * 设置：供电局组织名称
	 */
	public void setGdjzzmc(String gdjzzmc) {
		this.gdjzzmc = gdjzzmc;
	}
	/**
	 * 获取：供电局组织名称
	 */
	public String getGdjzzmc() {
		return gdjzzmc;
	}
	/**
	 * 设置：供电所组织名称
	 */
	public void setGdszzmc(String gdszzmc) {
		this.gdszzmc = gdszzmc;
	}
	/**
	 * 获取：供电所组织名称
	 */
	public String getGdszzmc() {
		return gdszzmc;
	}
	/**
	 * 设置：客户编号
	 */
	public void setYhbh(String yhbh) {
		this.yhbh = yhbh;
	}
	/**
	 * 获取：客户编号
	 */
	public String getYhbh() {
		return yhbh;
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
	/**
	 * 设置：用电类型代码
	 */
	public void setYdlbdm(String ydlbdm) {
		this.ydlbdm = ydlbdm;
	}
	/**
	 * 获取：用电类型代码
	 */
	public String getYdlbdm() {
		return ydlbdm;
	}
	/**
	 * 设置：代码编码名称-对应xt_dmbm.dmbmmc
	 */
	public void setYdlbdmmc(String ydlbdmmc) {
		this.ydlbdmmc = ydlbdmmc;
	}
	/**
	 * 获取：代码编码名称-对应xt_dmbm.dmbmmc
	 */
	public String getYdlbdmmc() {
		return ydlbdmmc;
	}
	/**
	 * 设置：电费年月
	 */
	public void setDfny(String dfny) {
		this.dfny = dfny;
	}
	/**
	 * 获取：电费年月
	 */
	public String getDfny() {
		return dfny;
	}
	/**
	 * 设置：计费电量
	 */
	public void setJfdl(String jfdl) {
		this.jfdl = jfdl;
	}
	/**
	 * 获取：计费电量
	 */
	public String getJfdl() {
		return jfdl;
	}
	/**
	 * 设置：应收电费
	 */
	public void setYsdf(String ysdf) {
		this.ysdf = ysdf;
	}
	/**
	 * 获取：应收电费
	 */
	public String getYsdf() {
		return ysdf;
	}
	/**
	 * 设置：抄表周期
	 */
	public void setCbzq(String cbzq) {
		this.cbzq = cbzq;
	}
	/**
	 * 获取：抄表周期
	 */
	public String getCbzq() {
		return cbzq;
	}
	/**
	 * 设置：抄表周期名称-对应xt_dmbm.dmbmmc
	 */
	public void setCbzqmc(String cbzqmc) {
		this.cbzqmc = cbzqmc;
	}
	/**
	 * 获取：抄表周期名称-对应xt_dmbm.dmbmmc
	 */
	public String getCbzqmc() {
		return cbzqmc;
	}
	/**
	 * 设置：上次抄表日期
	 */
	public void setSccbrq(String sccbrq) {
		this.sccbrq = sccbrq;
	}
	/**
	 * 获取：上次抄表日期
	 */
	public String getSccbrq() {
		return sccbrq;
	}
	/**
	 * 设置：本次抄表日期
	 */
	public void setBccbrq(String bccbrq) {
		this.bccbrq = bccbrq;
	}
	/**
	 * 获取：本次抄表日期
	 */
	public String getBccbrq() {
		return bccbrq;
	}
}
