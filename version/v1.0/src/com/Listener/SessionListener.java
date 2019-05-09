package com.Listener;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.entity.Pwd_user;

public class SessionListener implements HttpSessionListener {

	public void sessionCreated(HttpSessionEvent httpSessionEvent) {

	}

	public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
		HttpSession session = httpSessionEvent.getSession();
		Pwd_user user = (Pwd_user) session.getAttribute("user");
		if (user != null) {
			ServletContext application = session.getServletContext();
			@SuppressWarnings("unchecked")
			Map<String, Object> loginMap = (Map<String, Object>) application.getAttribute("loginMap");
			loginMap.remove(user.getId());
			application.setAttribute("loginMap", loginMap);
			session.removeAttribute("user");
		}
	}

}
