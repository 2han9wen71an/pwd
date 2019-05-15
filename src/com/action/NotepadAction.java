package com.action;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.CommonDao;
import com.dao.Pwd_notepadDao;
import com.entity.Pwd_notepad;
import com.entity.Pwd_user;
import com.struts.ActionForm;
import com.struts.ActionForward;
import com.struts.DispatchAction;
import com.util.GeneralUtil;
import com.util.PageUtil;

public class NotepadAction extends DispatchAction {
	public ActionForward notepad_list(HttpServletRequest request, HttpServletResponse response, ActionForm form) {
		response.setContentType("text/html;charset=utf-8");
		String currentCount = request.getParameter("page");
		String pageSize = request.getParameter("limit");
		List totallist = Pwd_notepadDao.SelectAll();
		PageUtil page = new PageUtil(totallist.size(), Integer.valueOf(currentCount), Integer.valueOf(pageSize));
		Pwd_user user = (Pwd_user) request.getSession().getAttribute("user");
		List list = Pwd_notepadDao.Select(0, user.getId(), 0, null, page);
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

	public ActionForward notepad_inc(HttpServletRequest request, HttpServletResponse response, ActionForm form) {
		response.setContentType("text/html;charset=utf-8");
		Pwd_notepad note = (Pwd_notepad) form;
		Pwd_user user = (Pwd_user) request.getSession().getAttribute("user");
		note.setUserid(user.getId());
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
		note.setStime(df.format(new Date()));
		note.setType(0);
		int i = new CommonDao().save(note);
		if (i > 0) {
			String str = GeneralUtil.EchoMsg(user.getId(), request.getRequestURL().toString(),
					GeneralUtil.getIpAddress(request), request.getHeader("User-Agent"), 200, "增加备忘录成功", 0, null);
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
		return null;
	}

	public ActionForward notepad_status(HttpServletRequest request, HttpServletResponse response, ActionForm form) {
		response.setContentType("text/html;charset=utf-8");
		Pwd_notepad note = (Pwd_notepad) form;
		Pwd_user user = (Pwd_user) request.getSession().getAttribute("user");
		note.setUserid(user.getId());
		int i = Pwd_notepadDao.update(note);
		if (i > 0) {
			request.getSession().setAttribute("count", Pwd_notepadDao.Select(0, user.getId(), 0, null, null).size());
			String str = GeneralUtil.EchoMsg(user.getId(), request.getRequestURL().toString(),
					GeneralUtil.getIpAddress(request), request.getHeader("User-Agent"), 200, "请求修改备忘录状态成功", 0, null);
			try {
				response.getWriter().print(str);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			String str = GeneralUtil.EchoMsg(user.getId(), request.getRequestURL().toString(),
					GeneralUtil.getIpAddress(request), request.getHeader("User-Agent"), 201, "数据请求错误", 0, null);
			try {
				response.getWriter().print(str);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public ActionForward notepad_del(HttpServletRequest request, HttpServletResponse response, ActionForm form) {
		response.setContentType("text/html;charset=utf-8");
		Pwd_notepad note = (Pwd_notepad) form;
		Pwd_user user = (Pwd_user) request.getSession().getAttribute("user");
		note.setUserid(user.getId());
		int i = Pwd_notepadDao.delete(note);
		if (i > 0) {
			String str = GeneralUtil.EchoMsg(user.getId(), request.getRequestURL().toString(),
					GeneralUtil.getIpAddress(request), request.getHeader("User-Agent"), 200, "删除备忘录成功", 0, null);
			try {
				response.getWriter().print(str);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			String str = GeneralUtil.EchoMsg(user.getId(), request.getRequestURL().toString(),
					GeneralUtil.getIpAddress(request), request.getHeader("User-Agent"), 201, "数据请求错误", 0, null);
			try {
				response.getWriter().print(str);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public ActionForward notepad_search(HttpServletRequest request, HttpServletResponse response, ActionForm form) {
		response.setContentType("text/html;charset=utf-8");
		Pwd_notepad note = (Pwd_notepad) form;
		Pwd_user user = (Pwd_user) request.getSession().getAttribute("user");
		note.setUserid(user.getId());
		String type = request.getParameter("type");
		String content = request.getParameter("content");
		// String currentCount = request.getParameter("page");
		// String pageSize = request.getParameter("limit");
		List totallist = Pwd_notepadDao.Select(0, note.getUserid(), Integer.valueOf(type), content, null);
		// PageUtil page = new PageUtil(totallist.size(),
		// Integer.valueOf(currentCount), Integer.valueOf(pageSize));
		// List select = Pwd_pwdDao.Select(0,pwd.getUserid(),
		// Integer.valueOf(type), content, page);
		if (totallist.size() > 0) {
			try {
				response.getWriter()
						.print(GeneralUtil.EchoMsg(user.getId(), request.getRequestURL().toString(),
								GeneralUtil.getIpAddress(request), request.getHeader("User-Agent"), 0, "请求查找备忘录成功",
								totallist.size(), totallist));
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			try {
				response.getWriter()
						.print(GeneralUtil.EchoMsg(user.getId(), request.getRequestURL().toString(),
								GeneralUtil.getIpAddress(request), request.getHeader("User-Agent"), 201, "找不到备忘录……", 0,
								null));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public ActionForward notepad_type(HttpServletRequest request, HttpServletResponse response, ActionForm form) {
		response.setContentType("text/html;charset=utf-8");
		Pwd_user user = (Pwd_user) request.getSession().getAttribute("user");
		PageUtil page = new PageUtil(5, 1);
		List select = Pwd_notepadDao.Select(0, user.getId(), 0, null, page);
		try {
			response.getWriter()
					.print(GeneralUtil.EchoMsg(user.getId(), request.getRequestURL().toString(),
							GeneralUtil.getIpAddress(request), request.getHeader("User-Agent"), 200, "请求成功",
							page.getTotalRow(), select));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
