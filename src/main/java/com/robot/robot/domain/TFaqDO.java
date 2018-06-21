package com.robot.robot.domain;

import java.io.Serializable;
import java.util.Date;

import com.robot.common.utils.ShiroUtils;



/**
 * 问答表
 * 
 * @author yobi
 * @email ***
 * @date 2017-11-30 19:29:57
 */
public class TFaqDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Long faqId;
	//知识库主键id
	private Long repositoryId;
	//问题
	private String question;
	//答案
	private String answer;
	//专业表id
	private Long majorId;
	//创建者
	private String creater;
	//
	private Date createtime;
	//回答次数
	private Integer amount;
	//
	private Long parentId;
	//流程标识
	private String flowIdentify;
	//是否流程 (0 不是，1 是)
	private Integer isFlow;
	//流程级别
	private Integer flowLevel;
	
	
	
	

	//非持久化字段
	
	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	//知识库名称
	private String repositoryName;
	//专业名称
	private String majorName;
	
	//关键字组合
	private String keygroup;
	//关键字组合名称
	private String keygroupName;
	//关键字优先级
	private String keygroupPriority;
	
	public String getKeygroupPriority() {
		return keygroupPriority;
	}

	public void setKeygroupPriority(String keygroupPriority) {
		this.keygroupPriority = keygroupPriority;
	}

	// Constructors
	public TFaqDO(Long faqId,Long repositoryId,String question,String answer,Long majorId,
					String creater,Date createtime,Integer amount,Long parentId,String flowIdentify,int isFlow,int flowLevel) {
		super();
		this.faqId = faqId;
		this.repositoryId = repositoryId;
		this.question = question;
		this.answer = answer;
		this.majorId = majorId;
		this.creater = creater;
		this.createtime = createtime;
		this.amount = amount;
		this.parentId = parentId;
		this.flowIdentify = flowIdentify;
		this.isFlow = isFlow;
		this.flowLevel = flowLevel;
	}

	/** default constructor */
	public TFaqDO() {
	}
	
	//用于批量导入功能
	public TFaqDO(String[] tocp) throws Exception {
		super();
        this.question=tocp[3];
        this.answer=tocp[4];
        Long userId= ShiroUtils.getUser().getUserId();		//当前系统管理员
        creater=String.valueOf(userId);	//发布人
        createtime=new Date();
        amount=0;
        parentId=0l;
	}
	
	
	/**
	 * 设置：
	 */
	public void setFaqId(Long faqId) {
		this.faqId = faqId;
	}
	/**
	 * 获取：
	 */
	public Long getFaqId() {
		return faqId;
	}
	/**
	 * 设置：知识库主键id
	 */
	public void setRepositoryId(Long repositoryId) {
		this.repositoryId = repositoryId;
	}
	/**
	 * 获取：知识库主键id
	 */
	public Long getRepositoryId() {
		return repositoryId;
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
	 * 设置：专业表id
	 */
	public void setMajorId(Long majorId) {
		this.majorId = majorId;
	}
	/**
	 * 获取：专业表id
	 */
	public Long getMajorId() {
		return majorId;
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
	/**
	 * 获取：回答次数
	 */
	public Integer getAmount() {
		return amount;
	}
	/**
	 * 设置：回答次数
	 */
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	/**
	 * 获取：知识库名称
	 */
	public String getRepositoryName() {
		return repositoryName;
	}
	public void setRepositoryName(String repositoryName) {
		this.repositoryName = repositoryName;
	}
	/**
	 * 获取：专业名称
	 */
	public String getMajorName() {
		return majorName;
	}
	public void setMajorName(String majorName) {
		this.majorName = majorName;
	}
	public String getKeygroup() {
		return keygroup;
	}
	public void setKeygroup(String keygroup) {
		this.keygroup = keygroup;
	}
	public String getKeygroupName() {
		return keygroupName;
	}
	public void setKeygroupName(String keygroupName) {
		this.keygroupName = keygroupName;
	}

	public String getFlowIdentify() {
		return flowIdentify;
	}

	public void setFlowIdentify(String flowIdentify) {
		this.flowIdentify = flowIdentify;
	}

	public Integer getIsFlow() {
		return isFlow;
	}

	public void setIsFlow(Integer isFlow) {
		this.isFlow = isFlow;
	}

	public Integer getFlowLevel() {
		return flowLevel;
	}

	public void setFlowLevel(Integer flowLevel) {
		this.flowLevel = flowLevel;
	}
	
	
}
