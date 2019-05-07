package com.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.Pwd_userDao;
import com.entity.Pwd_user;
import com.init.StartInit;
import com.util.McryptUtil;

/**
 * Servlet Filter implementation class LoginCheckFilter
 */
public class LoginCheckFilter implements Filter {

	/**
	 * Default constructor.
	 */
	public LoginCheckFilter() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletResponse resp = (HttpServletResponse) response;
		HttpServletRequest req = (HttpServletRequest) request;
		String contextpath = req.getContextPath();
		Object user = req.getSession().getAttribute("user");
		Cookie[] cookies = req.getCookies();
		String token = null;
		if (cookies != null && cookies.length > 0) {
			for (int i = 0; i < cookies.length; i++) {
				if ("token".equalsIgnoreCase(cookies[i].getName().substring(cookies[i].getName().length() - 5))) {
					token = cookies[i].getValue();
				}
			}
		}
		Pwd_user usertoken = null;
		if (token != null) {
			token = McryptUtil.decode(token, StartInit.getSYS_KEY());
			usertoken = Pwd_userDao.SelectToken(token);
		}
		if (usertoken != null || user != null && !"".equals(user)) {
			resp.setContentType("text/html;charset=utf-8");
			PrintWriter out = resp.getWriter();
			out.print("<script>");
			out.print("alert('你已经登陆了');location.href='admin/index.jsp';");
			out.print("</script>");
			out.flush();
			out.close();
		} else {
			chain.doFilter(request, response);
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
