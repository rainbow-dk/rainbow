package com.java.user.entity;

public class User {
	
	private Integer id;
	
	private String userName;
	
	private String password;
	
	private String telephone;
	
	private String status;
	

	
	
	public User() {
		super();
	}

	public User( String userName, String password, String telephone, String status, String createTime) {
		super();
		this.userName = userName;
		this.password = password;
		this.telephone = telephone;
		this.status = status;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
