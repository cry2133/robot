package com.robot.robot.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 词槽实体表
 * 
 * @author laoGF
 * @date 2018-09-03 14:09:43
 */
public class TEntityDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//ID
	private Long id;
	//实体名称
	private String name;
	//实体描述
	private String description;
	//澄清语句
	private String clarification;
	//词槽类型
	private int type;
	//肯定词
	private String yes;
	//否定词
	private String no;
	//角色ID
	private Long roleId;


	private boolean check;

	/**
	 * 设置：ID
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 获取：ID
	 */
	public Long getId() {
		return id;
	}
	/**
	 * 设置：实体名称
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：实体名称
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：实体描述
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * 获取：实体描述
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * 设置：澄清语句
	 */
	public void setClarification(String clarification) {
		this.clarification = clarification;
	}
	/**
	 * 获取：澄清语句
	 */
	public String getClarification() {
		return clarification;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getYes() {
		return yes;
	}

	public void setYes(String yes) {
		this.yes = yes;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public boolean isCheck() {
		return check;
	}

	public void setCheck(boolean check) {
		this.check = check;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
}
