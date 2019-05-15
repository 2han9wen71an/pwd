package com.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.Pwd_logDao;
import com.entity.Pwd_user;
import com.struts.ActionForm;
import com.struts.ActionForward;
import com.struts.DispatchAction;
import com.util.GeneralUtil;
import com.util.PageUtil;

public class LogAction extends DispatchAction {
	public ActionForward log_list(HttpServletRequest request, HttpServletResponse response, ActionForm form) {
		response.setContentType("text/html;charset=utf-8");
		String currentCount = request.getParameter("page");
		String pageSize = request.getParameter("limit");
		List totallist = Pwd_logDao.Select(1, null, null);
		PageUtil page = new PageUtil(totallist.size(), Integer.valueOf(currentCount), Integer.valueOf(pageSize));
		Pwd_user user = (Pwd_user) request.getSession().getAttribute("user");
		List list = Pwd_logDao.Select(user.getId(), user.getUsername(), page);
		if (page.getTotalRow() > 0) {
			try {
				response.getWriter()
						.print(GeneralUtil.EchoMsg(user.getId(), request.getRequestURL().toString(),
								GeneralUtil.getIpAddress(request), request.getHeader("User-Agent"), 0, "数据请求成功",
								page.getTotalRow(), list));
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			try {
				response.getWriter()
						.print(GeneralUtil.EchoMsg(user.getId(), request.getRequestURL().toString(),
								GeneralUtil.getIpAddress(request), request.getHeader("User-Agent"), 201, "当前没有数据", 0,
								list));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
}