package com.action;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.CommonDao;
import com.dao.Pwd_noticeDao;
import com.entity.Pwd_notice;
import com.entity.Pwd_user;
import com.struts.ActionForm;
import com.struts.ActionForward;
import com.struts.DispatchAction;
import com.util.GeneralUtil;

public class NoticeAction extends DispatchAction {
	public ActionForward notice_list(HttpServletRequest request, HttpServletResponse response, ActionForm form) {
		response.setContentType("text/html;charset=utf-8");
		Pwd_user user = (Pwd_user) request.getSession().getAttribute("user");
		List list = Pwd_noticeDao.Select();
		if (list.size() > 0) {
			try {
				response.getWriter()
						.print(GeneralUtil.EchoMsg(user.getId(), request.getRequestURL().toString(),
								GeneralUtil.getIpAddress(request), request.getHeader("User-Agent"), 0, "数据请求成功", 0,
								list));
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			try {
				response.getWriter()
						.print(GeneralUtil.EchoMsg(user.getId(), request.getRequestURL().toString(),
								GeneralUtil.getIpAddress(request), request.getHeader("User-Agent"), 201, "当前没有公告", 0,
								list));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public ActionForward notice_inc(HttpServletRequest request, HttpServletResponse response, ActionForm form) {
		response.setContentType("text/html;charset=utf-8");
		Pwd_notice notice = (Pwd_notice) form;
		Pwd_user user = (Pwd_user) request.getSession().getAttribute("user");
		if (user.getId() == 1) {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
			notice.setTime(df.format(new Date()));
			int i = new CommonDao().save(notice);
			if (i > 0) {
				String str = GeneralUtil.EchoMsg(user.getId(), request.getRequestURL().toString(),
						GeneralUtil.getIpAddress(request), request.getHeader("User-Agent"), 200, "发布公告成功", 0, null);
				try {
					response.getWriter().print(str);
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				String str = GeneralUtil.EchoMsg(user.getId(), request.getRequestURL().toString(),
						GeneralUtil.getIpAddress(request), request.getHeader("User-Agent"), 201, "数据请求失败", 0, null);
				try {
					response.getWriter().print(str);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} else {
			try {
				response.getWriter().print("<script language='javascript'>window.location.href='./404.jsp';</script>");
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		return null;
	}

	public ActionForward notice_del(HttpServletRequest request, HttpServletResponse response, ActionForm form) {
		response.setContentType("text/html;charset=utf-8");
		Pwd_notice notice = (Pwd_notice) form;
		Pwd_user user = (Pwd_user) request.getSession().getAttribute("user");
		if (user.getId() == 1) {
			int i = Pwd_noticeDao.delete(notice);
			if (i > 0) {
				String str = GeneralUtil.EchoMsg(user.getId(), request.getRequestURL().toString(),
						GeneralUtil.getIpAddress(request), request.getHeader("User-Agent"), 200, "数据请求成功", 0, null);
				try {
					response.getWriter().print(str);
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				String str = GeneralUtil.EchoMsg(user.getId(), request.getRequestURL().toString(),
						GeneralUtil.getIpAddress(request), request.getHeader("User-Agent"), 201, "当前没有内容", 0, null);
				try {
					response.getWriter().print(str);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} else {
			try {
				response.getWriter().print("<script language='javascript'>window.location.href='./404.jsp';</script>");
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		return null;
	}
}
