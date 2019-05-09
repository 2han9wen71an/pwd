package com.struts;

public class ActionMapping {
	
	private String path;  //请求路径
	private String type;  //路径指向的action所在类的地址     【包名.类名】
	private String name;  //指明哪个实体类接收
	
	
	public ActionMapping(String path, String type, String name) {
		super();
		this.path = path;
		this.type = type;
		this.name = name;
	}
	
	
	
	@Override
	public String toString() {
		return "ActionMapping [name=" + name + ", path=" + path + ", type="
				+ type + "]";
	}



	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	

}
