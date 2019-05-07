package com.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.dao.Pwd_userDao;
import com.entity.Pwd_user;
import com.init.StartInit;
import com.util.McryptUtil;

/**
 * 登陆过滤 配置白名单、黑名单
 * 
 * @author zwt
 *
 */
public class LoginFilter implements Filter {
	private static final String NO_CHECK = "noCheck";
	private static final String REDIRECT_PATH = "redirectPath";
	private List<String> noCheckList = new ArrayList<String>();
	private String redirectPath = null;

	public void init(FilterConfig init) throws ServletException {
		String noChecks = init.getInitParameter(NO_CHECK);
		if (StringUtils.isNotBlank(noChecks)) {
			if (StringUtils.indexOf(noChecks, ",") != -1) {
				for (String no : noChecks.split(",")) {
					noCheckList.add(StringUtils.trimToEmpty(no));
				}
			} else {
				noCheckList.add(noChecks);
			}
		}
		String path = init.getInitParameter(REDIRECT_PATH);
		if (StringUtils.isNotBlank(path)) {
			redirectPath = path;
		}
	}

	private boolean check(String path) {
		if (noCheckList == null || noCheckList.size() <= 0)
			return false;
		for (String s : noCheckList) {
			if (path.indexOf(s) > -1) {
				return true;
			}
		}
		return false;
	}

	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) arg0;
		HttpServletResponse response = (HttpServletResponse) arg1;
		String contextpath = request.getContextPath();
		Object user = request.getSession().getAttribute("user");
		Cookie[] cookies = request.getCookies();
		String token = null;
		if (cookies != null && cookies.length > 0) {
			for (int i = 0; i < cookies.length; i++) {
				if ("token".equalsIgnoreCase(cookies[i].getName().substring(cookies[i].getName().length() - 5))) {
					token = java.net.URLDecoder.decode(cookies[i].getValue(), "UTF-8");
				}
			}
		}
		System.out.println(token);
		Pwd_user usertoken = null;
		if (token != null) {
			token = McryptUtil.decode(token, StartInit.getSYS_KEY());
			usertoken = Pwd_userDao.SelectToken(token);
		}
		if (usertoken != null) {
			System.out.println("token用户id"+usertoken.getId() );
			System.out.println("token名"+cookies[1].getName() );
			if (usertoken.getId() == 1 && cookies[1].getName().equalsIgnoreCase("admin_token")) {
				request.getSession().setAttribute("admin", usertoken);
			}
			request.getSession().setAttribute("user", usertoken);
			chain.doFilter(request, response);
		} else if (user != null) {
			chain.doFilter(request, response);
		} else {
			response.sendRedirect(response.encodeURL(contextpath + redirectPath));
		}
		/*
		 * if ("/".equals(contextpath)) { contextpath = ""; } if
		 * (check(request.getRequestURI())) { chain.doFilter(request, response);
		 * } else { response.sendRedirect(response.encodeURL(contextpath +
		 * redirectPath)); }
		 */
	}

	public void destroy() {
	}

}