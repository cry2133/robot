package com.robot.robot.domain;

import java.io.Serializable;
import java.util.Date;

import com.robot.common.utils.ShiroUtils;

/**
 * 关键字表
 */
public class TKeywordDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Long keywordId;
	//关键字名称
	private String name;
	//优先级
	private int priority;
	//闸值（默认1，正值为业务关键字，负值为闲聊关键字）
	private int value;
	//查询次数
	private int amount;
	//
	private String creater;
	//
	private Date createtime;
	
	/** default constructor */
	public	TKeywordDO(){
	}
	
	//用于批量导入功能
	public TKeywordDO(String name) throws Exception {
		super();
        this.name=name;
        this.priority=0;
        this.value=1;
        this.amount=0;
        this.createtime=new Date();
        Long userId= ShiroUtils.getUser().getUserId();		//当前系统管理员
        creater=String.valueOf(userId);	//发布人
        
	}
	
	/**
	 * 设置：
	 */
	public void setKeywordId(Long keywordId) {
		this.keywordId = keywordId;
	}
	/**
	 * 获取：
	 */
	public Long getKeywordId() {
		return keywordId;
	}
	/**
	 * 设置：关键字名称
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：关键字名称
	 */
	public String getName() {
		return name;
	}
	/**
	 * 获取：优先级
	 */
	public int getPriority() {
		return priority;
	}
	/**
	 * 设置：优先级
	 */
	public void setPriority(int priority) {
		this.priority = priority;
	}
	/**
	 * 获取：闸值（默认1，正值为业务关键字，负值为闲聊关键字）
	 */
	public int getValue() {
		return value;
	}
	/**
	 * 设置：闸值（默认1，正值为业务关键字，负值为闲聊关键字）
	 */
	public void setValue(int value) {
		this.value = value;
	}

	/**
	 * 设置：查询次数
	 */
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	/**
	 * 获取：查询次数
	 */
	public Integer getAmount() {
		return amount;
	}
	/**
	 * 设置：
	 */
	public void setCreater(String creater) {
		this.creater = creater;
	}
	/**
	 * 获取：
	 */
	public String getCreater() {
		return creater;
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
