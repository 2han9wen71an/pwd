package com.entity;

import com.struts.ActionForm;

/**
 * PWD_NOTICE 实体类 Fri Apr 12 18:15:36 CST 2019 zwt
 */

public class Pwd_notice extends ActionForm {
	private int id;
	private String content;
	private String time;
	private int level;

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getContent() {
		return content;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getTime() {
		return time;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getLevel() {
		return level;
	}

	public Pwd_notice() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Pwd_notice(String content, String time, int level) {
		super();
		this.content = content;
		this.time = time;
		this.level = level;
	}

	public Pwd_notice(int id, String content, String time, int level) {
		super();
		this.id = id;
		this.content = content;
		this.time = time;
		this.level = level;
	}

}
