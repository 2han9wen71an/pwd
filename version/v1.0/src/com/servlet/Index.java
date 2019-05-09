package com.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.Pwd_pwdDao;
import com.dao.Pwd_userDao;
import com.entity.Pwd_user;

public class Index extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Pwd_user user = (Pwd_user) request.getSession().getAttribute("user");
		if (user.getId() == 1) {
			int i = Pwd_pwdDao.Select(1);
			List list = Pwd_pwdDao.SelectAll(0, 1, 0, null, null);
			List userSelect = Pwd_userDao.SelectAll(1,null);
			request.setAttribute("count_day", i);
			request.setAttribute("count_pwd", list.size());
			request.setAttribute("count_user", userSelect.size());
			request.getRequestDispatcher("/admin/index.jsp").forward(request, response);
		} else {
			int i = Pwd_pwdDao.Select(user.getId());
			List list = Pwd_pwdDao.Select(0, user.getId(), 0, null, null);
			List userSelect = Pwd_userDao.SelectAll(user.getId(),null);
			request.setAttribute("count_day", i);
			request.setAttribute("count_pwd", list.size());
			request.setAttribute("count_user", userSelect.size());
			request.getRequestDispatcher("/admin/index.jsp").forward(request, response);
		}
	}
}
