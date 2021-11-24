package com.yuyi.bean;

import java.util.Date;

public class User {
//	uid        varchar(32)  utf8_general_ci  NO      PRI     (NULL)           select,insert,update,references           
//	username   varchar(20)  utf8_general_ci  YES             (NULL)           select,insert,update,references           
//	password   varchar(20)  utf8_general_ci  YES             (NULL)           select,insert,update,references           
//	name       varchar(20)  utf8_general_ci  YES             (NULL)           select,insert,update,references           
//	email      varchar(30)  utf8_general_ci  YES             (NULL)           select,insert,update,references           
//	telephone  varchar(20)  utf8_general_ci  YES             (NULL)           select,insert,update,references           
//	birthday   varchar(20)  utf8_general_ci  YES             (NULL)           select,insert,update,references           
//	sex        varchar(10)  utf8_general_ci  YES             (NULL)           select,insert,update,references           
//	state      int(11)      (NULL)           YES             (NULL)           select,insert,update,references           
//	code       varchar(64)  utf8_general_ci  YES             (NULL)           select,insert,update,references           
	private String uid;
	private String username;
	private String password;
	private String name;
	private String email;
	private String telephone;
	private Date birthday;
	private String sex;
	private int state;
	private String code;

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsernamae(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
