package com.entity;

import com.struts.ActionForm;

/**
 * PWD_PWD 实体类 Fri Apr 12 18:15:58 CST 2019 zwt
 */

public class Pwd_pwd extends ActionForm {
	private int id;
	private int userid;
	private String title;
	private String descr;
	private String username;
	private String pass;
	private String weburl;
	private String intime;
	private String lasttime;
	private String tpass;
	private int status;
	private int type;
	private String pwd_decode;

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

	public String getDescr() {
		return descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getWeburl() {
		return weburl;
	}

	public void setWeburl(String weburl) {
		this.weburl = weburl;
	}

	public String getIntime() {
		return intime;
	}

	public void setIntime(String intime) {
		this.intime = intime;
	}

	public String getLasttime() {
		return lasttime;
	}

	public void setLasttime(String lasttime) {
		this.lasttime = lasttime;
	}

	public String getTpass() {
		return tpass;
	}

	public void setTpass(String tpass) {
		this.tpass = tpass;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getPwd_decode() {
		return pwd_decode;
	}

	public void setPwd_decode(String pwd_decode) {
		this.pwd_decode = pwd_decode;
	}

	public Pwd_pwd() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Pwd_pwd(int userid, String title, String descr, String username, String pass, String weburl, String intime,
			String lasttime, String tpass, int status, int type) {
		super();
		this.userid = userid;
		this.title = title;
		this.descr = descr;
		this.username = username;
		this.pass = pass;
		this.weburl = weburl;
		this.intime = intime;
		this.lasttime = lasttime;
		this.tpass = tpass;
		this.status = status;
		this.type = type;
	}

	public Pwd_pwd(int id, int userid, String title, String descr, String username, String pass, String weburl,
			String intime, String lasttime, String tpass, int status, int type) {
		super();
		this.id = id;
		this.userid = userid;
		this.title = title;
		this.descr = descr;
		this.username = username;
		this.pass = pass;
		this.weburl = weburl;
		this.intime = intime;
		this.lasttime = lasttime;
		this.tpass = tpass;
		this.status = status;
		this.type = type;
	}

	public Pwd_pwd(int id, int userid, String title, String descr, String username, String pass, String weburl,
			String intime, String lasttime, int status, int type) {
		super();
		this.id = id;
		this.userid = userid;
		this.title = title;
		this.descr = descr;
		this.username = username;
		this.pass = pass;
		this.weburl = weburl;
		this.intime = intime;
		this.lasttime = lasttime;
		this.status = status;
		this.type = type;
	}

	public Pwd_pwd(int id, int userid, String title, String descr, String username, String pass, String weburl,
			String intime, String lasttime, int status, int type, String pwd_decode) {
		super();
		this.id = id;
		this.userid = userid;
		this.title = title;
		this.descr = descr;
		this.username = username;
		this.pass = pass;
		this.weburl = weburl;
		this.intime = intime;
		this.lasttime = lasttime;
		this.status = status;
		this.type = type;
		this.pwd_decode = pwd_decode;
	}

	public Pwd_pwd(int id, int userid, String title, String descr, String username, String pass, String weburl,
			String intime, String lasttime, String tpass, int status, int type, String pwd_decode) {
		super();
		this.id = id;
		this.userid = userid;
		this.title = title;
		this.descr = descr;
		this.username = username;
		this.pass = pass;
		this.weburl = weburl;
		this.intime = intime;
		this.lasttime = lasttime;
		this.tpass = tpass;
		this.status = status;
		this.type = type;
		this.pwd_decode = pwd_decode;
	}

	public Pwd_pwd(int id, int userid, String title, String descr, String username, String weburl, String intime,
			String lasttime, int status, int type, String pwd_decode) {
		super();
		this.id = id;
		this.userid = userid;
		this.title = title;
		this.descr = descr;
		this.username = username;
		this.weburl = weburl;
		this.intime = intime;
		this.lasttime = lasttime;
		this.status = status;
		this.type = type;
		this.pwd_decode = pwd_decode;
	}

	public Pwd_pwd(int id, int userid, String title, String descr, String username, String weburl, String intime,
			String lasttime, int status, int type) {
		super();
		this.id = id;
		this.userid = userid;
		this.title = title;
		this.descr = descr;
		this.username = username;
		this.weburl = weburl;
		this.intime = intime;
		this.lasttime = lasttime;
		this.status = status;
		this.type = type;
	}
	
}
