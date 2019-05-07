package com.entity;

import com.struts.ActionForm;

/**
 * pwd_user 实体类 Fri Apr 12 10:44:09 CST 2019 zwt
 */

public class Pwd_user extends ActionForm {
	private int id;
	private String username;
	private String pass;
	private String email;
	private int qq;
	private String stime;
	private String ltime;
	private String sip;
	private String lip;
	private String token;
	private int status;
	private int error_num;
	private String email_token;
	private long token_exptime;

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

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getPass() {
		return pass;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public void setQq(int qq) {
		this.qq = qq;
	}

	public int getQq() {
		return qq;
	}

	public void setStime(String stime) {
		this.stime = stime;
	}

	public String getStime() {
		return stime;
	}

	public void setLtime(String ltime) {
		this.ltime = ltime;
	}

	public String getLtime() {
		return ltime;
	}

	public void setSip(String sip) {
		this.sip = sip;
	}

	public String getSip() {
		return sip;
	}

	public void setLip(String lip) {
		this.lip = lip;
	}

	public String getLip() {
		return lip;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getToken() {
		return token;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getStatus() {
		return status;
	}

	public void setError_num(int error_num) {
		this.error_num = error_num;
	}

	public int getError_num() {
		return error_num;
	}

	public void setEmail_token(String email_token) {
		this.email_token = email_token;
	}

	public String getEmail_token() {
		return email_token;
	}

	public void setToken_exptime(long token_exptime) {
		this.token_exptime = token_exptime;
	}

	public long getToken_exptime() {
		return token_exptime;
	}

	public Pwd_user() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Pwd_user(String username, String pass, String email, int qq, String stime, String ltime, String sip,
			String lip, String token, int status, int error_num, String email_token, long token_exptime) {
		super();
		this.username = username;
		this.pass = pass;
		this.email = email;
		this.qq = qq;
		this.stime = stime;
		this.ltime = ltime;
		this.sip = sip;
		this.lip = lip;
		this.token = token;
		this.status = status;
		this.error_num = error_num;
		this.email_token = email_token;
		this.token_exptime = token_exptime;
	}

	public Pwd_user(int id, String username, String pass, String email, int qq, String stime, String ltime, String sip,
			String lip, String token, int status, int error_num, String email_token, long token_exptime) {
		super();
		this.id = id;
		this.username = username;
		this.pass = pass;
		this.email = email;
		this.qq = qq;
		this.stime = stime;
		this.ltime = ltime;
		this.sip = sip;
		this.lip = lip;
		this.token = token;
		this.status = status;
		this.error_num = error_num;
		this.email_token = email_token;
		this.token_exptime = token_exptime;
	}

	public Pwd_user(int id, String username, String email, int qq, String stime, String ltime, String sip, String lip,
			String token, int status, int error_num, String email_token, long token_exptime) {
		super();
		this.id = id;
		this.username = username;
		this.email = email;
		this.qq = qq;
		this.stime = stime;
		this.ltime = ltime;
		this.sip = sip;
		this.lip = lip;
		this.token = token;
		this.status = status;
		this.error_num = error_num;
		this.email_token = email_token;
		this.token_exptime = token_exptime;
	}
	

}
