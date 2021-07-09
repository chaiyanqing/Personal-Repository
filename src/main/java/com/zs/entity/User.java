/**
 * 
 */
package com.zs.entity;

import java.io.Serializable;

/**
 * @ProjectName om
 * @File com.zs.entity.User.java
 * @Author Yanqing
 * @Date 2018年4月16日 下午4:10:33
 * @Version V1.0
 */
public class User implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String id;
	private String account;
	private String name;
	private String password;
	private String phone;
	private String createTime;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	
}
