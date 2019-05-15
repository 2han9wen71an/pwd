package com.entity;

import com.struts.ActionForm;

/**
 * PWD_PLAN 实体类 Fri Apr 12 18:15:47 CST 2019 zwt
 */

public class Pwd_plan extends ActionForm {
	private int id;
	private int userid;
	private String username;
	private String email;
	private String stime;
	private int type;
	private String content;
	private int status;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getStime() {
		return stime;
	}
	public void setStime(String stime) {
		this.stime = stime;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Pwd_plan() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Pwd_plan(int userid, String username, String email, String stime, int type, String content, int status) {
		super();
		this.userid = userid;
		this.username = username;
		this.email = email;
		this.stime = stime;
		this.type = type;
		this.content = content;
		this.status = status;
	}
	public Pwd_plan(int id, int userid, String username, String email, String stime, int type, String content,
			int status) {
		super();
		this.id = id;
		this.userid = userid;
		this.username = username;
		this.email = email;
		this.stime = stime;
		this.type = type;
		this.content = content;
		this.status = status;
	}

	
}
