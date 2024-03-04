package com.mayikt.entity;

public class StudentEntiry {
	// 学生对象实体类
	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Integer getAge() {
		return age;
	}

	public String getAddress() {
		return address;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "StudentEntiry{" +
						 "id=" + id +
						 ", name='" + name + '\'' +
						 ", age=" + age +
						 ", address='" + address + '\'' +
						 '}';
	}

	public StudentEntiry(Long id, String name, Integer age, String address) {
		this.id = id;
		this.name = name;
		this.age = age;
		this.address = address;
	}

	public StudentEntiry(String name, Integer age, String address) {
		this.name = name;
		this.age = age;
		this.address = address;
	}

	private  Long id;
	private String name;
	private Integer age;
	private  String address;
}
