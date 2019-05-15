package com.struts;

public class ActionForward {
	
	private String path;  //跳转路径
	
	private boolean redirect = false;   //是否重定向        默认转发

	
	//如果以后创建该对象，只放路径，那就是转发
	public ActionForward(String path) {
		super();
		this.path = path;
	}
	//如果以后创建该对象，不仅有路径，还有true    那么就是重定向
	public ActionForward(String path, boolean redirect) {
		super();
		this.path = path;
		this.redirect = redirect;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public boolean isRedirect() {
		return redirect;
	}

	public void setRedirect(boolean redirect) {
		this.redirect = redirect;
	}
	
	

}
