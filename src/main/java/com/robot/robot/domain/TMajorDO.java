package com.robot.robot.domain;

import java.io.Serializable;
import java.util.Date;

import com.robot.common.utils.ShiroUtils;



/**
 * 专业表
 * 
 * @author yobi
 * @email ***
 * @date 2017-11-30 19:29:58
 */
public class TMajorDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//专业表id
	private Long majorId;
	//专业名称
	private String name;
	//父级id，作为二级专业
	private Long parent;
	//
	private String creater;
	//
	private Date createtime;

	/** default constructor */
	public	TMajorDO(){
	}
	
	//用于批量导入功能
	public TMajorDO(String[] tocp) throws Exception {
		super();
        this.name=tocp[2];
        this.parent=0l;
        this.createtime=new Date();
        Long userId= ShiroUtils.getUser().getUserId();		//当前系统管理员
        creater=String.valueOf(userId);	//发布人
        
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
	 * 设置：专业名称
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：专业名称
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：父级id，作为二级专业
	 */
	public void setParent(Long parent) {
		this.parent = parent;
	}
	/**
	 * 获取：父级id，作为二级专业
	 */
	public Long getParent() {
		return parent;
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
