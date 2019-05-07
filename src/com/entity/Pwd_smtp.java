package com.entity;

import com.struts.ActionForm;

/**
 * PWD_SMTP 实体类 Fri Apr 12 18:16:12 CST 2019 zwt
 */

public class Pwd_smtp extends ActionForm {
	private int id;
	private String host;
	private int port;
	private String username;
	private String password;
	private String sub;
	private int ssl;

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getHost() {
		return host;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public int getPort() {
		return port;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUsername() {
		return username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public void setSub(String sub) {
		this.sub = sub;
	}

	public String getSub() {
		return sub;
	}

	public void setSsl(int ssl) {
		this.ssl = ssl;
	}

	public int getSsl() {
		return ssl;
	}

	public Pwd_smtp() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Pwd_smtp(String host, int port, String username, String password, String sub, int ssl) {
		super();
		this.host = host;
		this.port = port;
		this.username = username;
		this.password = password;
		this.sub = sub;
		this.ssl = ssl;
	}

	public Pwd_smtp(int id, String host, int port, String username, String password, String sub, int ssl) {
		super();
		this.id = id;
		this.host = host;
		this.port = port;
		this.username = username;
		this.password = password;
		this.sub = sub;
		this.ssl = ssl;
	}

}
