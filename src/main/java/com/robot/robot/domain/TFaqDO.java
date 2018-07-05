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
	//意图实体
	private String intentionEntity;
	//补全意图语料
	private String intentionText;
	//意图实体2
	private String intentionEntity2;
	//补全意图语料2
	private String intentionText2;	
	//意图实体3
	private String intentionEntity3;
	//补全意图语料3
	private String intentionText3;
	
	
	
	

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
					String creater,Date createtime,Integer amount,Long parentId,
					String intentionEntity, String intentionText,String intentionEntity2, String intentionText2, String intentionEntity3,  String intentionText3) {
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
		this.intentionEntity = intentionEntity;
		this.intentionText = intentionText;
		this.intentionEntity2 = intentionEntity2;
		this.intentionText2 = intentionText2;
		this.intentionEntity3 = intentionEntity3;
		this.intentionText3 = intentionText3;
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

	public String getIntentionEntity() {
		return intentionEntity;
	}

	public void setIntentionEntity(String intentionEntity) {
		this.intentionEntity = intentionEntity;
	}

	public String getIntentionText() {
		return intentionText;
	}

	public void setIntentionText(String intentionText) {
		this.intentionText = intentionText;
	}

	public String getIntentionEntity2() {
		return intentionEntity2;
	}

	public void setIntentionEntity2(String intentionEntity2) {
		this.intentionEntity2 = intentionEntity2;
	}

	public String getIntentionText2() {
		return intentionText2;
	}

	public void setIntentionText2(String intentionText2) {
		this.intentionText2 = intentionText2;
	}

	public String getIntentionEntity3() {
		return intentionEntity3;
	}

	public void setIntentionEntity3(String intentionEntity3) {
		this.intentionEntity3 = intentionEntity3;
	}

	public String getIntentionText3() {
		return intentionText3;
	}

	public void setIntentionText3(String intentionText3) {
		this.intentionText3 = intentionText3;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
