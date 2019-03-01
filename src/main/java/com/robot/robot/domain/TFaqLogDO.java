package com.robot.robot.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 问答记录表
 * 
 * @author laoGF
 * @date 2018-08-09 19:03:39
 */
public class TFaqLogDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//序号
	private Long id;
	//问题
	private String question;
	//答案
	private String answer;
	//应答方式（0 默认 , 1 单轮应答 ,2 场景应答 3 闲聊）
	private int way;
	//机器人编号
	private String robotNo;
	//问答时间
	private Date createtime;

	/**
	 * 设置：序号
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 获取：序号
	 */
	public Long getId() {
		return id;
	}
	/**
	 * 设置：问题
	 */
	public void setQuestion(String question) {
		this.question = question;
	}
	/**
	 * 获取：问题
	 */
	public String getQuestion() {
		return question;
	}
	/**
	 * 设置：答案
	 */
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	/**
	 * 获取：答案
	 */
	public String getAnswer() {
		return answer;
	}
	/**
	 * 设置：问答时间
	 */
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	/**
	 * 获取：问答时间
	 */
	public Date getCreatetime() {
		return createtime;
	}

	public String getRobotNo() {
		return robotNo;
	}

	public void setRobotNo(String robotNo) {
		this.robotNo = robotNo;
	}

	public int getWay() {
		return way;
	}

	public void setWay(int way) {
		this.way = way;
	}
}
