package com.action;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.Pwd_pwdDao;
import com.entity.Pwd_pwd;
import com.entity.Pwd_user;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.struts.ActionForm;
import com.struts.ActionForward;
import com.struts.DispatchAction;
import com.util.GeneralUtil;
import com.util.MD5Util;
import com.util.McryptUtil;
import com.util.PageUtil;

public class PwdAction extends DispatchAction {
	public ActionForward pwd_list(HttpServletRequest request, HttpServletResponse response, ActionForm form) {
		response.setContentType("text/html;charset=utf-8");
		String currentCount = request.getParameter("page");
		String pageSize = request.getParameter("limit");
		List totallist = Pwd_pwdDao.Select(0, 1, 0, null, null);
		PageUtil page = new PageUtil(totallist.size(), Integer.valueOf(currentCount), Integer.valueOf(pageSize));
		Pwd_user user = (Pwd_user) request.getSession().getAttribute("user");
		List list = Pwd_pwdDao.Select(0, user.getId(), 0, null, page);
		if (page.getTotalRow() > 0) {
			try {
				response.getWriter()
						.print(GeneralUtil.EchoMsg(user.getId(), request.getRequestURL().toString(),
								GeneralUtil.getIpAddress(request), request.getHeader("User-Agent"), 0, "请求获取密码本列表",
								page.getTotalRow(), list));
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			try {
				response.getWriter().print(GeneralUtil.EchoMsg(user.getId(), request.getRequestURL().toString(),
						GeneralUtil.getIpAddress(request), request.getHeader("User-Agent"), 201, "当前没有数据", 0, null));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public ActionForward inc_pwd(HttpServletRequest request, HttpServletResponse response, ActionForm form) {
		response.setContentType("text/html;charset=utf-8");
		Pwd_pwd pwd = (Pwd_pwd) form;
		Pwd_user user = (Pwd_user) request.getSession().getAttribute("user");
		pwd.setUserid(user.getId());
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
		pwd.setIntime(df.format(new Date()));
		pwd.setLasttime(df.format(new Date()));
		String key = request.getParameter("key");
		if (key != null) {
			key = MD5Util.StringToMD5(key, null);
			pwd.setTpass(key);
			pwd.setPass(McryptUtil.encode(pwd.getPass(), key, 0));
		} else {
			pwd.setTpass(GeneralUtil.getRandom(8));
			pwd.setPass(McryptUtil.encode(pwd.getPass(), pwd.getTpass(), 0));
		}
		pwd.setStatus(1);
		int i = Pwd_pwdDao.insert(pwd);
		if (i > 0) {
			String str = GeneralUtil.EchoMsg(user.getId(), request.getRequestURL().toString(),
					GeneralUtil.getIpAddress(request), request.getHeader("User-Agent"), 200, "新增密码记录成功", 0, null);
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

	public ActionForward pwd_delete(HttpServletRequest request, HttpServletResponse response, ActionForm form) {
		response.setContentType("text/html;charset=utf-8");
		Pwd_pwd pwd = (Pwd_pwd) form;
		Pwd_user user = (Pwd_user) request.getSession().getAttribute("user");
		pwd.setUserid(user.getId());
		int i = Pwd_pwdDao.delete(pwd);
		if (i > 0) {
			String str = GeneralUtil.EchoMsg(user.getId(), request.getRequestURL().toString(),
					GeneralUtil.getIpAddress(request), request.getHeader("User-Agent"), 200, "删除密码记录成功", 0, null);
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

	public ActionForward pwd_search(HttpServletRequest request, HttpServletResponse response, ActionForm form) {
		response.setContentType("text/html;charset=utf-8");
		Pwd_pwd pwd = (Pwd_pwd) form;
		Pwd_user user = (Pwd_user) request.getSession().getAttribute("user");
		pwd.setUserid(user.getId());
		String type = request.getParameter("type");
		String content = request.getParameter("content");
		// String currentCount = request.getParameter("page");
		// String pageSize = request.getParameter("limit");
		List totallist = Pwd_pwdDao.Select(0, pwd.getUserid(), Integer.valueOf(type), content, null);
		// PageUtil page = new PageUtil(totallist.size(),
		// Integer.valueOf(currentCount), Integer.valueOf(pageSize));
		// List select = Pwd_pwdDao.Select(0,pwd.getUserid(),
		// Integer.valueOf(type), content, page);
		if (totallist.size() > 0) {
			try {
				response.getWriter()
						.print(GeneralUtil.EchoMsg(user.getId(), request.getRequestURL().toString(),
								GeneralUtil.getIpAddress(request), request.getHeader("User-Agent"), 0, "查找密码记录成功",
								totallist.size(), totallist));
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			try {
				response.getWriter().print(GeneralUtil.EchoMsg(user.getId(), request.getRequestURL().toString(),
						GeneralUtil.getIpAddress(request), request.getHeader("User-Agent"), 201, "找不到密码记录……", 0, null));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public ActionForward see_pwd(HttpServletRequest request, HttpServletResponse response, ActionForm form) {
		response.setContentType("text/html;charset=utf-8");
		Pwd_pwd pwd = (Pwd_pwd) form;
		Pwd_user user = (Pwd_user) request.getSession().getAttribute("user");
		pwd.setUserid(user.getId());
		String list_id = request.getParameter("list_id");
		String key = request.getParameter("key");
		// 判断密码Id是否属于此账号
		List select = Pwd_pwdDao.Select(Integer.valueOf(list_id), pwd.getUserid(), 0, null, null);
		if (select.size() > 0) {
			// 查询用户的所有信息
			List selectAll = Pwd_pwdDao.SelectAll(Integer.valueOf(list_id), pwd.getUserid(), 0, null, null);
			System.out.println("用户keyMD5加密后为" + MD5Util.StringToMD5(key, null));
			pwd = (Pwd_pwd) selectAll.get(0);
			String pass_decode = McryptUtil.decode(pwd.getPass(), MD5Util.StringToMD5(key, null));
			System.out.println("用户key解密后" + pass_decode);
			try {
				response.getWriter().print(GeneralUtil.EchoMsg(user.getId(), request.getRequestURL().toString(),
						GeneralUtil.getIpAddress(request), request.getHeader("User-Agent"), 200, pass_decode, 0, null));
			} catch (IOException e) {
				e.printStackTrace();
			}

		} else {
			try {
				response.getWriter().print(GeneralUtil.EchoMsg(user.getId(), request.getRequestURL().toString(),
						GeneralUtil.getIpAddress(request), request.getHeader("User-Agent"), 201, "数据请求失败", 0, null));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public ActionForward set_pwd(HttpServletRequest request, HttpServletResponse response, ActionForm form) {
		response.setContentType("text/html;charset=utf-8");
		Pwd_pwd pwd = (Pwd_pwd) form;
		Pwd_user user = (Pwd_user) request.getSession().getAttribute("user");
		pwd.setUserid(user.getId());
		// 如果用户名留空则用原来的密码
		if (pwd.getUsername().equals("")) {
			List select = Pwd_pwdDao.Select(pwd.getId(), pwd.getUserid(), 0, null, null);
			Pwd_pwd p = (Pwd_pwd) select.get(0);
			pwd.setUsername(p.getUsername());
		}
		if (pwd.getPass().equals("")) {
			List select = Pwd_pwdDao.Select(pwd.getId(), pwd.getUserid(), 0, null, null);
			Pwd_pwd p = (Pwd_pwd) select.get(0);
			pwd.setPass(McryptUtil.decode(p.getPass(), p.getTpass()));
		}
		String key = request.getParameter("key");
		pwd.setTpass(GeneralUtil.getRandom(8));
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
		pwd.setLasttime(df.format(new Date()));
		// 判断是否设置二代密码
		if (key != null) {
			// 如果有就把二代密码加密之后再次加密
			pwd.setPass(McryptUtil.encode(pwd.getPass(), MD5Util.StringToMD5(key, null), 0));
		} else {
			pwd.setPass(McryptUtil.encode(pwd.getPass(), pwd.getTpass(), 0));
		}
		int i = Pwd_pwdDao.update(pwd);
		if (i > 0) {
			String str = GeneralUtil.EchoMsg(user.getId(), request.getRequestURL().toString(),
					GeneralUtil.getIpAddress(request), request.getHeader("User-Agent"), 200, "修改密码记录成功", 0, null);
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

	public ActionForward importpwd(HttpServletRequest request, HttpServletResponse response, ActionForm form) {
		response.setContentType("text/html;charset=utf-8");
		Pwd_pwd pwd = (Pwd_pwd) form;
		Pwd_user user = (Pwd_user) request.getSession().getAttribute("user");
		pwd.setUserid(user.getId());
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
		pwd.setIntime(df.format(new Date()));
		pwd.setLasttime(df.format(new Date()));
		String strByJson = request.getParameter("json");
		try {
			if (user.getId() != 0) {
				// Json的解析类对象
				JsonParser parser = new JsonParser();
				// 将JSON的String 转成一个JsonArray对象
				JsonArray jsonArray = parser.parse(strByJson).getAsJsonArray();
				Gson gson = new Gson();
				String key = null;
				int i = 0;
				// 加强for循环遍历JsonArray
				for (JsonElement p : jsonArray) {
					// 使用GSON，直接转成Bean对象
					Pwd_pwd pjson = gson.fromJson(p, Pwd_pwd.class);
					JsonObject obj = p.getAsJsonObject();
					key = obj.get("key").getAsString();
					if (!key.isEmpty()) {
						pwd.setType(1);
						key = MD5Util.StringToMD5(key, null);
						pwd.setTpass(key);
						pwd.setPass(McryptUtil.encode(pjson.getPass(), key, 0));
					} else {
						pwd.setTpass(GeneralUtil.getRandom(8));
						pwd.setPass(McryptUtil.encode(pjson.getPass(), pwd.getTpass(), 0));
					}
					pwd.setStatus(1);
					pwd.setTitle(pjson.getTitle());
					pwd.setDescr(pjson.getDescr());
					pwd.setUsername(pjson.getUsername());
					pwd.setWeburl(pjson.getWeburl());
					int j = Pwd_pwdDao.insert(pwd);
					if (j > 0) {
						i++;
					}
				}
				if (i > 0) {
					String str = GeneralUtil.EchoMsg(user.getId(), request.getRequestURL().toString(),
							GeneralUtil.getIpAddress(request), request.getHeader("User-Agent"), 200,
							"批量导入" + i + "条密码记录成功", 0, null);
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
				response.getWriter().print(GeneralUtil.EchoMsg(user.getId(), request.getRequestURL().toString(),
						GeneralUtil.getIpAddress(request), request.getHeader("User-Agent"), 403, "权限不足", 0, null));

			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
