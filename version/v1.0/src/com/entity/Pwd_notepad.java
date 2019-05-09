package com.entity;

import com.struts.ActionForm;

/**
 * PWD_NOTEPAD 实体类 Fri Apr 12 18:15:25 CST 2019 zwt
 */

public class Pwd_notepad extends ActionForm {
	private int id;
	private int userid;
	private String title;
	private String content;
	private String stime;
	private int type;
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
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
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
	public Pwd_notepad() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Pwd_notepad(int userid, String title, String content, String stime, int type) {
		super();
		this.userid = userid;
		this.title = title;
		this.content = content;
		this.stime = stime;
		this.type = type;
	}
	public Pwd_notepad(int id, int userid, String title, String content, String stime, int type) {
		super();
		this.id = id;
		this.userid = userid;
		this.title = title;
		this.content = content;
		this.stime = stime;
		this.type = type;
	}
	
}
