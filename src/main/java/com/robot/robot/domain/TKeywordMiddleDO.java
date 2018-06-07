package com.robot.robot.domain;

import java.io.Serializable;


/**
 * 问答与关键字关系表
 * 
 * @author yobi
 * @email ***
 * @date 2017-12-12 14:26:26
 */
public class TKeywordMiddleDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Long middleId;
	//关键字id组
	private String keygroup;
	//关键字组合名称
	private String keygroupName;
	//问答主键id
	private Long faqId;

	
	/** default constructor */
	public	TKeywordMiddleDO(){
	}
	
	//用于批量导入功能
	public TKeywordMiddleDO(String keygroupName,String keygroup,Long faqId) throws Exception {
		super();
        this.keygroup=keygroup;
        this.keygroupName=keygroupName;
        this.faqId=faqId;
	}
	
	/**
	 * 设置：
	 */
	public void setMiddleId(Long middleId) {
		this.middleId = middleId;
	}
	/**
	 * 获取：
	 */
	public Long getMiddleId() {
		return middleId;
	}
	/**
	 * 设置：关键字id组
	 */
	public void setKeygroup(String keygroup) {
		this.keygroup = keygroup;
	}
	/**
	 * 获取：关键字id组
	 */
	public String getKeygroup() {
		return keygroup;
	}
	/**
	 * 设置：关键字组合名称
	 */
	public void setKeygroupName(String keygroupName) {
		this.keygroupName = keygroupName;
	}
	/**
	 * 获取：关键字组合名称
	 */
	public String getKeygroupName() {
		return keygroupName;
	}
	/**
	 * 设置：问答主键id
	 */
	public void setFaqId(Long faqId) {
		this.faqId = faqId;
	}
	/**
	 * 获取：问答主键id
	 */
	public Long getFaqId() {
		return faqId;
	}
}
