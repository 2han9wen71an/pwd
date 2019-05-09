package com.struts;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DispatchAction implements Action{

	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response, ActionForm form) {
		
		//申明在这里，是为了方便返回对象
		ActionForward af = null;
		
		//接收前台传递过来的方法名       method代表了方法名
		String method = request.getParameter("method");
		
		//得到类对象   （这个类对象，是DispatchAction）
		Class cls = this.getClass();
		try {
			//通过指定的方法名，反射得到该方法
			Method m = cls.getMethod(method, HttpServletRequest.class,HttpServletResponse.class,ActionForm.class);
		
			//调用方法
			af = (ActionForward)m.invoke(this, request,response,form);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return af;
	}

}
