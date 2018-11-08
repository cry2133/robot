package com.robot.robot.domain;

import java.io.Serializable;
import java.util.Date;

import com.robot.common.utils.ShiroUtils;



/**
 * 知识词库表
 */
public class TRepositoryDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//知识库id
	private Long repositoryId;
	//词库名称
	private String name;
	//词库性质 0:公用 1:私用
	private Integer property;
	//应用单位
	private String units;
	//创建者
	private String creater;
	//创建时间
	private Date createtime;
	//优先级
	private Integer priority;
	//所属角色ID
	private Long roleId;
	//所属角色名称
	private String roleName;

	
	/** default constructor */
	public	TRepositoryDO(){
	}
	
	//用于批量导入功能
	public TRepositoryDO(String[] tocp) throws Exception {
		super();
        this.name=tocp[0];
        this.property=0;
        this.units=tocp[1];
        this.createtime=new Date();
        Long userId= ShiroUtils.getUser().getUserId();		//当前系统管理员
        creater=String.valueOf(userId);	//发布人
        
	}
	
	/**
	 * 设置：知识库id
	 */
	public void setRepositoryId(Long repositoryId) {
		this.repositoryId = repositoryId;
	}
	/**
	 * 获取：知识库id
	 */
	public Long getRepositoryId() {
		return repositoryId;
	}
	/**
	 * 设置：词库名称
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：词库名称
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：词库性质 0:公用 1:私用
	 */
	public void setProperty(Integer property) {
		this.property = property;
	}
	/**
	 * 获取：词库性质 0:公用 1:私用
	 */
	public Integer getProperty() {
		return property;
	}
	/**
	 * 设置：应用单位
	 */
	public void setUnits(String units) {
		this.units = units;
	}
	/**
	 * 获取：应用单位
	 */
	public String getUnits() {
		return units;
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
	 * 设置：创建时间
	 */
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	/**
	 * 获取：创建时间
	 */
	public Date getCreatetime() {
		return createtime;
	}
	/**
	 * 设置：优先级
	 */
	public void setPriority(Integer priority) {
		this.priority = priority;
	}
	/**
	 * 获取：优先级
	 */
	public Integer getPriority() {
		return priority;
	}


	/**
	 * 设置：所属角色ID
	 */
	public void setRoleId(Long roleId){
		this.roleId = roleId;
	}

	/**
	 * 获取：所属角色ID
	 */
	public Long getRoleId(){
		return roleId;
	}

	/**
	 * 设置：所属角色名称
	 */
	public void setRoleName(String roleName){
		this.roleName = roleName;
	}
	/**
	 * 获取：所属角色名称
	 */
	public String getRoleName(){
		return roleName;
	}


}
