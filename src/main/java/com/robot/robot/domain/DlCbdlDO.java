package com.robot.robot.domain;

import java.io.Serializable;



/**
 * 抄表电量查询 -来源回流库 
 * 
 * @author yobi
 * @email ***
 * @date 2018-03-28 14:05:50
 */
public class DlCbdlDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//用户编号
	private String yhbh;
	//计量点编号
	private String jldbh;
	//电费年月
	private String dfny;
	//上次表示数
	private String scbss;
	//本次表示数
	private String bcbss;
	//表计电量
	private String bjdl;
	//资产编号
	private String zcbh;
	//综合倍率
	private String zhbl;

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
	 * 设置：计量点编号
	 */
	public void setJldbh(String jldbh) {
		this.jldbh = jldbh;
	}
	/**
	 * 获取：计量点编号
	 */
	public String getJldbh() {
		return jldbh;
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
	 * 设置：上次表示数
	 */
	public void setScbss(String scbss) {
		this.scbss = scbss;
	}
	/**
	 * 获取：上次表示数
	 */
	public String getScbss() {
		return scbss;
	}
	/**
	 * 设置：本次表示数
	 */
	public void setBcbss(String bcbss) {
		this.bcbss = bcbss;
	}
	/**
	 * 获取：本次表示数
	 */
	public String getBcbss() {
		return bcbss;
	}
	/**
	 * 设置：
	 */
	public void setBjdl(String bjdl) {
		this.bjdl = bjdl;
	}
	/**
	 * 获取：
	 */
	public String getBjdl() {
		return bjdl;
	}
	/**
	 * 设置：资产编号
	 */
	public void setZcbh(String zcbh) {
		this.zcbh = zcbh;
	}
	/**
	 * 获取：资产编号
	 */
	public String getZcbh() {
		return zcbh;
	}
	/**
	 * 设置：综合倍率
	 */
	public void setZhbl(String zhbl) {
		this.zhbl = zhbl;
	}
	/**
	 * 获取：综合倍率
	 */
	public String getZhbl() {
		return zhbl;
	}
}
