package com.entity;

import java.io.Serializable;

public class Mail_Auth implements Serializable {

	/**
	 * 邮件编码
	 */
	public static final String ENCODEING = "UTF-8";
	/**
	 * 服务器地址
	 */
	private String host;
	/**
	 * 服务器端口号
	 */
	private String port;
	/**
	 * 发件人的邮箱
	 */
	private String sender;
	/**
	 * 收件人的邮箱
	 */
	private String receiver;
	/**
	 * 发件人昵称
	 */
	private String name;
	/**
	 * 账号
	 */
	private String username;
	/**
	 * 密码
	 */
	private String password;
	/**
	 * 主题
	 */
	private String subject;
	/**
	 * 信息(支持HTML)
	 */
	private String message;

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Mail_Auth() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Mail_Auth(String host, String port, String username, String password, String name) {
		super();
		this.host = host;
		this.port = port;
		this.name = name;
		this.username = username;
		this.password = password;
	}

	public Mail_Auth(String receiver, String subject, String message) {
		super();
		this.receiver = receiver;
		this.subject = subject;
		this.message = message;
	}

	public Mail_Auth(String host, String port, String sender, String receiver, String name, String username,
			String password, String subject, String message) {
		super();
		this.host = host;
		this.port = port;
		this.sender = sender;
		this.receiver = receiver;
		this.name = name;
		this.username = username;
		this.password = password;
		this.subject = subject;
		this.message = message;
	}

}
