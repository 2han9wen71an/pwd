package com.entity;

import com.struts.ActionForm;

/**
 * pwd_log 实体类 Fri Apr 12 18:14:04 CST 2019 zwt
 */

public class Pwd_log extends ActionForm {
	private int id;
	private int userid;
	private String url;
	private String ip;
	private String ua;
	private String json;
	private String time;
	private String record;

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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getUa() {
		return ua;
	}

	public void setUa(String ua) {
		this.ua = ua;
	}

	public String getJson() {
		return json;
	}

	public void setJson(String json) {
		this.json = json;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getRecord() {
		return record;
	}

	public void setRecord(String record) {
		this.record = record;
	}

	public Pwd_log() {
		super();
	}

	public Pwd_log(int userid, String url, String ip, String ua, String json, String time, String record) {
		super();
		this.userid = userid;
		this.url = url;
		this.ip = ip;
		this.ua = ua;
		this.json = json;
		this.time = time;
		this.record = record;
	}

	public Pwd_log(int id, int userid, String url, String ip, String ua, String json, String time, String record) {
		super();
		this.id = id;
		this.userid = userid;
		this.url = url;
		this.ip = ip;
		this.ua = ua;
		this.json = json;
		this.time = time;
		this.record = record;
	}

}
