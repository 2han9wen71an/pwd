package com.servlet;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ZXServlet extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("進來了注銷");
		HttpSession session = request.getSession();
		Cookie[] cookies = request.getCookies();
		if (cookies != null && cookies.length > 0) {
			// 遍历浏览器发送到服务器端的所有Cookie，找到自己设置的Cookie
			for (Cookie cookie : cookies) {
				// 设置Cookie立即失效
				cookie.setMaxAge(0);
				response.addCookie(cookie);
			}
		}
		session.removeAttribute("user");
		response.sendRedirect("index.html");
	}
}
