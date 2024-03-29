package com.mayikt.entity;

public class UserEntity {

	private Long id;

	@Override
	public String toString() {
		return "UserEntity{" +
						 "id=" + id +
						 ", phone='" + phone + '\'' +
						 ", pwd='" + pwd + '\'' +
						 '}';
	}

	private  String phone;
	private String pwd;

	public UserEntity(Long id ,String phone, String pwd) {
		this.id = id;
		this.phone = phone;
		this.pwd = pwd;
	}

	public UserEntity(String phone, String pwd) {
		this.phone = phone;
		this.pwd = pwd;
	}

	public Long getId() {
		return id;
	}

	public String getPhone() {
		return phone;
	}

	public String getPwd() {
		return pwd;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}


}
