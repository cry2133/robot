package com.robot.robot.domain;

import java.io.Serializable;
import java.util.Date;

import com.robot.common.utils.ShiroUtils;



/**
 * 关键字表
 * 
 * @author yobi
 * @email ***
 * @date 2017-11-30 19:29:57
 */
public class TKeywordDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Long keywordId;
	//关键字名称
	private String name;
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
