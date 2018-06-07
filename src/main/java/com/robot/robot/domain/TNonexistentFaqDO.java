package com.robot.robot.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 未找到的问答表
 * 
 * @author yobi
 * @email ***
 * @date 2017-12-18 11:43:24
 */
public class TNonexistentFaqDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Long id;
	//
	private String question;
	//
	private String answer;
	//
	private Date createtime;

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
	 * 设置：
	 */
	public void setQuestion(String question) {
		this.question = question;
	}
	/**
	 * 获取：
	 */
	public String getQuestion() {
		return question;
	}
	/**
	 * 设置：
	 */
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	/**
	 * 获取：
	 */
	public String getAnswer() {
		return answer;
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
