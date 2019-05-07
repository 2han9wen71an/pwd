package com.entity;

import com.struts.ActionForm;

/**
 * pwd_set 实体类 Fri Apr 12 18:13:26 CST 2019 zwt
 */

public class Pwd_set extends ActionForm {
	private int id;
	private String username;
	private String pass;
	private int qq;
	private String email;
	private String title;
	private String describe;
	private String keywords;
	private int sign;
	private int email_sign;
	private String ipadmin;

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUsername() {
		return username;
	}

	public void setPassw(String pass) {
		this.pass = pass;
	}

	public String getPass() {
		return pass;
	}

	public void setQq(int qq) {
		this.qq = qq;
	}

	public int getQq() {
		return qq;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}

	public String getDescribe() {
		return describe;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setSign(int sign) {
		this.sign = sign;
	}

	public int getSign() {
		return sign;
	}

	public void setEmail_sign(int email_sign) {
		this.email_sign = email_sign;
	}

	public int getEmail_sign() {
		return email_sign;
	}

	public void setIpadmin(String ipadmin) {
		this.ipadmin = ipadmin;
	}

	public String getIpadmin() {
		return ipadmin;
	}

	public Pwd_set() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Pwd_set(int id, String username, String pass, int qq, String email) {
		super();
		this.id = id;
		this.username = username;
		this.pass = pass;
		this.qq = qq;
		this.email = email;
	}

	public Pwd_set(String username, String pass, int qq, String email, String title, String describe, String keywords,
			int sign, int email_sign, String ipadmin) {
		super();
		this.username = username;
		this.pass = pass;
		this.qq = qq;
		this.email = email;
		this.title = title;
		this.describe = describe;
		this.keywords = keywords;
		this.sign = sign;
		this.email_sign = email_sign;
		this.ipadmin = ipadmin;
	}

	public Pwd_set(int id, String username, String pass, int qq, String email, String title, String describe,
			String keywords, int sign, int email_sign, String ipadmin) {
		super();
		this.id = id;
		this.username = username;
		this.pass = pass;
		this.qq = qq;
		this.email = email;
		this.title = title;
		this.describe = describe;
		this.keywords = keywords;
		this.sign = sign;
		this.email_sign = email_sign;
		this.ipadmin = ipadmin;
	}

}
