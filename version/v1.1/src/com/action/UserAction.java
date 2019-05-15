package com.action;

import java.io.IOException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.CommonDao;
import com.dao.Pwd_notepadDao;
import com.dao.Pwd_setDao;
import com.dao.Pwd_userDao;
import com.entity.Pwd_plan;
import com.entity.Pwd_set;
import com.entity.Pwd_user;
import com.init.StartInit;
import com.struts.ActionForm;
import com.struts.ActionForward;
import com.struts.DispatchAction;
import com.util.GeneralUtil;
import com.util.MD5Util;
import com.util.McryptUtil;
import com.util.PageUtil;

public class UserAction extends DispatchAction {
	public ActionForward sign_up(HttpServletRequest request, HttpServletResponse response, ActionForm form) {
		Pwd_user user = (Pwd_user) form;
		// 把pwd用md5加密
		user.setPass(MD5Util.StringToMD5(user.getPass(), StartInit.getSYS_KEY()));
		Pwd_user pwduser = Pwd_userDao.UserSelect(user.getUsername(), null, user.getEmail());
		String verifycode = request.getParameter("verifycode");
		response.setContentType("text/html;charset=utf-8");
		if (user.getEmail() == null) {
			try {
				response.getWriter().print(GeneralUtil.EchoMsg("201", "邮件必填的，不填你怎么享受VIP级别的待遇呢", 0, null));
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if (!verifycode.equals(request.getSession().getAttribute("syscode"))) {
			try {
				response.getWriter().print(GeneralUtil.EchoMsg("203", "验证码不正确", 0, null));
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if (pwduser != null) {
			try {
				response.getWriter().print(GeneralUtil.EchoMsg("202", "该账号已存在", 0, null));
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			user.setSip(GeneralUtil.getIpAddress(request));
			user.setLip(GeneralUtil.getIpAddress(request));
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
			user.setStime(df.format(new Date()));
			user.setLtime(df.format(new Date()));
			user.setToken(GeneralUtil.getRandom(8));
			user.setEmail_token(GeneralUtil.getRandom(4));
			user.setToken_exptime(new Date().getTime() + 60 * 60 * 24 * 1000);
			// 如果开启邮箱注册
			if (StartInit.getPwdSet().getEmail_sign() == 1) {
				user.setStatus(3);
			} else {
				user.setStatus(1);
			}
			int j = new CommonDao().save(user);
			String str = null;
			if (j > 0) {
				Pwd_user pwd_user = Pwd_userDao.UserSelect(user.getUsername(), user.getPass(), null);
				if (StartInit.getPwdSet().getEmail_sign() > 0) {
					StringBuffer tempContextUrl = request.getRequestURL();
					String content = "亲爱的" + user.getUsername() + "：<br/>感谢您在我站注册了新帐号。<br/>请点击链接激活您的帐号。<br/><a href='"
							+ tempContextUrl + "?method=verify&id=" + pwd_user.getId() + "&verify="
							+ user.getEmail_token() + "' target='_blank'>" + tempContextUrl + "?method=verify&id="
							+ pwd_user.getId() + "&verify=" + user.getEmail_token()
							+ "</a><br/>如果以上链接无法点击，请将它复制到你的浏览器地址栏中进入访问，该链接24小时内有效。<br/>如果此次激活请求非你本人所发，请忽略本邮件。<br/>";
					long token_exptime = new Date().getTime() + 60 * 60 * 24 * 1000;
					int i = new CommonDao().save(new Pwd_plan(pwd_user.getId(), pwd_user.getUsername(),
							pwd_user.getEmail(), String.valueOf(token_exptime), 1, content, 0));
					if (i > 0) {
						str = GeneralUtil.EchoMsg("200", "注册成功,稍后就会收到激活邮件", 0, null);
					} else {
						str = GeneralUtil.EchoMsg("201", "注册成功,激活邮件发送失败，请联系管理员", 0, null);
					}

				} else {
					str = GeneralUtil.EchoMsg("200", "注册成功", 0, null);
				}
				try {
					response.getWriter().print(str);
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				try {
					response.getWriter().print(GeneralUtil.EchoMsg("201", "数据请求错误", 0, null));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}

	public ActionForward login(HttpServletRequest request, HttpServletResponse response, ActionForm form) {
		Pwd_user user = (Pwd_user) form;
		user.setPass(MD5Util.StringToMD5(user.getPass(), StartInit.getSYS_KEY()));
		String verifycode = request.getParameter("verifycode");
		String admin = request.getParameter("admin");
		response.setContentType("text/html;charset=utf-8");
		if (!verifycode.equals(request.getSession().getAttribute("syscode"))) {
			String str = GeneralUtil.EchoMsg("203", "验证码不正确", 0, null);
			try {
				response.getWriter().print(str);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if (admin.equals("1")) {
			Pwd_set setUser = Pwd_setDao.SelectUser(user.getUsername(), user.getPass());
			if (setUser != null) {
				Pwd_user user_ = Pwd_userDao.UserSelect(setUser.getUsername(), setUser.getPass(), setUser.getEmail());
				user_.setLip(GeneralUtil.getIpAddress(request));
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
				user_.setLtime(df.format(new Date()));
				user_.setToken(GeneralUtil.getRandom(4));
				String token = McryptUtil.encode(user_.getToken(), StartInit.getSYS_KEY(), 0);
				int count = Pwd_notepadDao.Select(0, setUser.getId(), 0, null, null).size();
				try {
					request.getSession().setAttribute("user", user_);
					request.getSession().setMaxInactiveInterval(60 * 30);
					request.getSession().setAttribute("count", count);
					// 新建自动登录的凭证Cookie(会员卡) //编码
					Cookie cookie = new Cookie("admin_token", URLEncoder.encode(token, "utf-8"));
					// 设置cookie在浏览器中存活时间
					cookie.setMaxAge(604800);
					// 把cookie添加在响应对象中, 届时会随着响应对象一起返给浏览器
					response.addCookie(cookie);
					Pwd_userDao.update(user_);
					response.getWriter().print(GeneralUtil.EchoMsg("200", "登陆后台管理成功！", 0, null));
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				String str = GeneralUtil.EchoMsg("204", "用户名或密码不正确！", 0, null);
				try {
					response.getWriter().print(str);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} else {
			String str = null;
			Pwd_user user_ = Pwd_userDao.UserSelect(user.getUsername(), user.getPass(), user.getEmail());
			if (user_ != null) {
				switch (user_.getStatus()) {
				case 0:
					str = GeneralUtil.EchoMsg("204", "账号已锁定", 0, null);
					try {
						response.getWriter().print(str);
					} catch (IOException e) {
						e.printStackTrace();
					}
					break;
				case 1:
					user_.setLip(GeneralUtil.getIpAddress(request));
					SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
					user_.setLtime(df.format(new Date()));
					user_.setToken(GeneralUtil.getRandom(4));
					String token = McryptUtil.encode(user_.getToken(), StartInit.getSYS_KEY(), 0);
					int count = Pwd_notepadDao.Select(0, user_.getId(), 0, null, null).size();
					try {
						request.getSession().setAttribute("user", user_);
						request.getSession().setMaxInactiveInterval(60 * 30);
						request.getSession().setAttribute("count", count);
						// 新建自动登录的凭证Cookie(会员卡) //编码
						Cookie cookie = new Cookie("user_token", URLEncoder.encode(token, "utf-8"));
						// 设置cookie在浏览器中存活时间
						cookie.setMaxAge(604800);
						// 把cookie添加在响应对象中, 届时会随着响应对象一起返给浏览器
						response.addCookie(cookie);
						Pwd_userDao.update(user_);
						response.getWriter().print(GeneralUtil.EchoMsg("200", "登录成功", 0, null));
					} catch (IOException e) {
						e.printStackTrace();
					}
					break;
				case 3:
					str = GeneralUtil.EchoMsg("204", "账号未激活", 0, null);
					try {
						response.getWriter().print(str);
					} catch (IOException e) {
						e.printStackTrace();
					}
					break;
				default:
					str = GeneralUtil.EchoMsg("204", "账号已锁定", 0, null);
					try {
						response.getWriter().print(str);
					} catch (IOException e) {
						e.printStackTrace();
					}
					break;
				}
			} else {
				Pwd_userDao.UserErrUpdate(user.getUsername());
				if (user.getError_num() > 5) {
					try {
						response.getWriter().print(GeneralUtil.EchoMsg("204", "密码错误五次,账号锁定", 0, null));
					} catch (IOException e) {
						e.printStackTrace();
					}
				} else {
					try {
						response.getWriter().print(GeneralUtil.EchoMsg("204", "密码错误", 0, null));
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}

		return null;
	}

	public ActionForward setuser(HttpServletRequest request, HttpServletResponse response, ActionForm form) {
		response.setContentType("text/html;charset=utf-8");
		Pwd_user user = (Pwd_user) form;
		Pwd_user user_ = (Pwd_user) request.getSession().getAttribute("user");
		if (user.getId() == 0) {
			user.setId(user_.getId());
		}
		if (!user.getPass().isEmpty()) {
			user.setPass(MD5Util.StringToMD5(user.getPass(), StartInit.getSYS_KEY()));
		} else {
			Pwd_user puser = (Pwd_user) Pwd_userDao.SelectAll(user_.getId(), null).get(0);
			user.setPass(puser.getPass());
		}
		int i = 0;
		if (user_.getId() == 1) {
			Pwd_setDao.UserUpdate(new Pwd_set(1, user.getUsername(), user.getPass(), user.getQq(), user.getEmail()));
		}
		i = Pwd_userDao.UserUpdate(user);
		if (i > 0) {
			try {
				Pwd_user puser = Pwd_userDao.UserSelect(user_.getUsername(), null, null);
				request.getSession().setAttribute("user", puser);
				response.getWriter().print(GeneralUtil.EchoMsg("200", "请求修改用户信息成功", 0, null));
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			try {
				response.getWriter().print(GeneralUtil.EchoMsg("201", "数据请求错误", 0, null));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public ActionForward get_list(HttpServletRequest request, HttpServletResponse response, ActionForm form) {
		response.setContentType("text/html;charset=utf-8");
		Pwd_user user = (Pwd_user) request.getSession().getAttribute("user");
		if (user.getId() == 1) {
			String currentCount = request.getParameter("page");
			String pageSize = request.getParameter("limit");
			List selectAll = Pwd_userDao.SelectAll(0, null);
			PageUtil page = new PageUtil(selectAll.size(), Integer.valueOf(currentCount), Integer.valueOf(pageSize));
			List all = Pwd_userDao.SelectAll(0, page);
			if (all.size() > 0) {
				try {
					response.getWriter().print(GeneralUtil.EchoMsg("0", "请求获取用户列表成功", page.getTotalRow(), all));
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				try {
					response.getWriter().print(GeneralUtil.EchoMsg("201", "当前没有用户", page.getTotalRow(), all));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} else {
			try {
				response.getWriter().print(GeneralUtil.EchoMsg("201", "你没有权限访问", 0, null));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return null;
	}

	public ActionForward delete(HttpServletRequest request, HttpServletResponse response, ActionForm form) {
		response.setContentType("text/html;charset=utf-8");
		Pwd_user user = (Pwd_user) form;
		Pwd_user puser = (Pwd_user) request.getSession().getAttribute("user");
		if (puser.getId() == 1) {
			int i = Pwd_userDao.delete(user);
			if (i > 0) {
				String str = GeneralUtil.EchoMsg("200", "删除密码记录成功", 0, null);
				try {
					response.getWriter().print(str);
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				String str = GeneralUtil.EchoMsg("201", "数据请求错误", 0, null);
				try {
					response.getWriter().print(str);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} else {
			try {
				response.getWriter().print(GeneralUtil.EchoMsg("201", "你没有权限访问", 0, null));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public ActionForward search(HttpServletRequest request, HttpServletResponse response, ActionForm form) {
		response.setContentType("text/html;charset=utf-8");
		Pwd_user user = (Pwd_user) form;
		Pwd_user puser = (Pwd_user) request.getSession().getAttribute("user");
		String type = request.getParameter("type");
		String content = request.getParameter("content");
		List list = Pwd_userDao.SelectBySearch(Integer.valueOf(type), content);
		if (puser.getId() == 1) {
			if (list.size() > 0) {
				try {
					response.getWriter().print(GeneralUtil.EchoMsg("0", "请求查找用户成功", list.size(), list));
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				try {
					response.getWriter().print(GeneralUtil.EchoMsg("201", "找不到用户……", 0, null));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} else {
			try {
				response.getWriter().print(GeneralUtil.EchoMsg("201", "你没有权限访问", 0, null));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public ActionForward verify(HttpServletRequest request, HttpServletResponse response, ActionForm form) {
		response.setContentType("text/html;charset=utf-8");
		Pwd_user user = (Pwd_user) form;
		String verify = request.getParameter("verify");
		// 判断是否需要激活
		Pwd_user user2 = Pwd_userDao.SelectStatu(user.getId(), null);
		if (user2.getStatus() == 3) {
			// 判断验证码是否为id所有
			Pwd_user pwd_user = Pwd_userDao.SelectStatu(user.getId(), verify);
			if (pwd_user != null) {
				if (new Date().getTime() >= pwd_user.getToken_exptime()) {
					try {
						response.getWriter().print("您的激活有效期已过，请登录您的帐号重新发送激活邮件");
					} catch (IOException e) {
						e.printStackTrace();
					}
				} else {
					pwd_user.setStatus(1);
					Pwd_userDao.updateStatu(pwd_user);
					try {
						response.getWriter().print("激活成功");
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			} else {
				try {
					response.getWriter().print("激活失败,请重试");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} else {
			try {
				response.getWriter().print("你的账号不需要激活");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return null;
	}

	public ActionForward resetpwd(HttpServletRequest request, HttpServletResponse response, ActionForm form) {
		response.setContentType("text/html;charset=utf-8");
		request.setAttribute("title", "密码找回");
		request.setAttribute("type", "reset_pwd");
		String verify = request.getParameter("reset_pwd_verify");
		if (verify != null) {
			long nowtime = new Date().getTime();
			Pwd_user user = Pwd_userDao.SelectEmailToken(verify);
			if (user != null) {
				if (nowtime > user.getToken_exptime()) {
					try {
						response.getWriter().print(
								"<script language='javascript'>alert('您的链接有效期已过，请重新找回密码');window.location.href='./reset_pwd.jsp';</script>");
					} catch (IOException e) {
						e.printStackTrace();
					}
				} else {
					request.setAttribute("type", "reset_pwd_verify");
				}
			} else {
				try {
					response.getWriter()
							.print("<script language='javascript'>window.location.href='./reset_pwd.jsp';</script>");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return new ActionForward("reset_pwd.jsp");
	}

	public ActionForward reset_pwd(HttpServletRequest request, HttpServletResponse response, ActionForm form) {
		response.setContentType("text/html;charset=utf-8");
		Pwd_user user = (Pwd_user) form;
		String verifycode = request.getParameter("verifycode");
		response.setContentType("text/html;charset=utf-8");
		if (!verifycode.equals(request.getSession().getAttribute("syscode"))) {
			try {
				response.getWriter().print(GeneralUtil.EchoMsg("203", "验证码不正确", 0, null));
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			Pwd_user pwd_user = Pwd_userDao.UserSelect(user.getUsername(), null, null);
			if (pwd_user.getEmail() != null) {
				long regtime = new Date().getTime();
				String email_token = MD5Util.StringToMD5(GeneralUtil.getRandom(8) + user.getUsername() + regtime,
						StartInit.getSYS_KEY());
				long token_exptime = new Date().getTime() + 60 * 60 * 24 * 1000;
				String email = pwd_user.getEmail();
				String url = GeneralUtil.GetWebUrl(request.getRequestURL().toString());
				String content = "亲爱的" + pwd_user.getUsername() + "：<br/>您正在进行找回密码操作。<br/>请点击链接重新设置您的密码。<br/><a href='"
						+ request.getRequestURL() + "?method=resetpwd&reset_pwd_verify=" + email_token
						+ "' target='_blank'>" + request.getRequestURL() + "?method=resetpwd&reset_pwd_verify="
						+ email_token
						+ "</a><br/>如果以上链接无法点击，请将它复制到你的浏览器地址栏中进入访问，该链接24小时内有效。<br/>如果此次密码找回请求非你本人所发，说明有人盯上你的账号了。<br/>";
				int type = 3;
				int i = new CommonDao().save(new Pwd_plan(pwd_user.getId(), pwd_user.getUsername(), pwd_user.getEmail(),
						String.valueOf(token_exptime), type, content, 0));
				if (i > 0) {
					pwd_user.setStatus(0);
					pwd_user.setEmail_token(email_token);
					pwd_user.setToken_exptime(token_exptime);
					int j = Pwd_userDao.updateStatu(pwd_user);
					if (j > 0) {
						try {
							response.getWriter().print(GeneralUtil.EchoMsg("200", "找回密码链接稍后发送到您的邮箱", 0, null));
						} catch (IOException e) {
							e.printStackTrace();
						}
					} else {
						try {
							response.getWriter().print(GeneralUtil.EchoMsg("201", "邮件发送成功,但数据请求错误", 0, null));
						} catch (IOException e) {
							e.printStackTrace();
						}
					}

				}
			} else {
				try {
					response.getWriter().print(GeneralUtil.EchoMsg("201", "账号或邮箱不存在", 0, null));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return null;
	}

	public ActionForward reset_pwd_verify(HttpServletRequest request, HttpServletResponse response, ActionForm form) {
		response.setContentType("text/html;charset=utf-8");
		Pwd_user user = (Pwd_user) form;
		String verifycode = request.getParameter("verifycode");
		String verify = request.getParameter("reset_pwd_verify");
		if (!verifycode.equals(request.getSession().getAttribute("syscode"))) {
			try {
				response.getWriter().print(GeneralUtil.EchoMsg("203", "验证码不正确", 0, null));
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			if (user.getPass() != null && verify != null) {
				user.setPass(MD5Util.StringToMD5(user.getPass(), StartInit.getSYS_KEY()));
				long nowtime = new Date().getTime();
				Pwd_user pwd_user = Pwd_userDao.SelectEmailToken(verify);
				if (pwd_user != null && pwd_user.getStatus() == 0) {
					try {
						if (nowtime > Long.valueOf(pwd_user.getToken_exptime())) {
							response.getWriter()
									.print(GeneralUtil.EchoMsg("201", "您的激活有效期已过，请登录您的帐号重新发送激活邮件", 0, null));
						} else {
							user.setStatus(1);
							user.setId(pwd_user.getId());
							int i = Pwd_userDao.UserPwdUpdate(user);
							if (i > 0) {
								response.getWriter().print(GeneralUtil.EchoMsg("200", "密码重置成功", 0, null));
							} else {
								response.getWriter().print(GeneralUtil.EchoMsg("201", "数据请求错误", 0, null));
							}
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
				} else {
					try {
						response.getWriter().print(GeneralUtil.EchoMsg("201", "数据请求错误", 0, null));
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			} else {
				try {
					response.getWriter().print(GeneralUtil.EchoMsg("201", "数据请求错误", 0, null));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}

	public ActionForward zxUser(HttpServletRequest request, HttpServletResponse response, ActionForm form) {
		HttpSession session = request.getSession();
		Pwd_user user = (Pwd_user) session.getAttribute("user");
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
		return new ActionForward("index.jsp", true);
	}
}
