package com.robot.robot.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 新问答表
 * 
 * @author laoGF
 * @date 2018-08-09 19:03:40
 */
public class TNewFaqDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//序号
	private Long id;
	//知识库主键id
	private Integer repositoryId;
	//专业表id
	private Integer majorId;
	//代表问题
	private String question;
	//问题集
	private String questions;
	//答案
	private String answer;


	//非持久化字段

	//知识库名称
	private String repositoryName;
	//专业名称
	private String majorName;



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
	 * 设置：知识库主键id
	 */
	public void setRepositoryId(Integer repositoryId) {
		this.repositoryId = repositoryId;
	}
	/**
	 * 获取：知识库主键id
	 */
	public Integer getRepositoryId() {
		return repositoryId;
	}
	/**
	 * 设置：专业表id
	 */
	public void setMajorId(Integer majorId) {
		this.majorId = majorId;
	}
	/**
	 * 获取：专业表id
	 */
	public Integer getMajorId() {
		return majorId;
	}
	/**
	 * 设置：代表问题
	 */
	public void setQuestion(String question) {
		this.question = question;
	}
	/**
	 * 获取：代表问题
	 */
	public String getQuestion() {
		return question;
	}
	/**
	 * 设置：问题集
	 */
	public void setQuestions(String questions) {
		this.questions = questions;
	}
	/**
	 * 获取：问题集
	 */
	public String getQuestions() {
		return questions;
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



	public void setRepositoryName(String repositoryName){
		this.repositoryName = repositoryName;
	}
	public String getRepositoryName(){
		return repositoryName;
	}

	public void setMajorName(String majorName){
		this.majorName = majorName;
	}
	public String getMajorName(){
		return majorName;
	}

}
