package com.action;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.CommonDao;
import com.dao.Pwd_planDao;
import com.entity.Pwd_plan;
import com.entity.Pwd_user;
import com.struts.ActionForm;
import com.struts.ActionForward;
import com.struts.DispatchAction;
import com.util.GeneralUtil;

public class PlanAction extends DispatchAction {
	public ActionForward remind(HttpServletRequest request, HttpServletResponse response, ActionForm form) {
		response.setContentType("text/html;charset=utf-8");
		String stime = request.getParameter("stime");
		try {
			int s = Integer.parseInt(stime);
			long time = new Date().getTime();
			time = time + s * 60 * 60 * 24 * 1000;
			Pwd_user user = (Pwd_user) request.getSession().getAttribute("user");
			Pwd_plan select = Pwd_planDao.Select(user.getId());
			if (select != null) {
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
				Date date = new Date(new Long(select.getStime()));
				String time_ = df.format(date);
				try {
					response.getWriter().print(
							GeneralUtil.EchoMsg("201", "已存在预约计划,请过期后重新预约，您预约的提醒将在" + time_ + "发送到您的邮箱", 0, null));
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				String content = "亲爱的" + user.getUsername()
						+ "，您好<br/>您在本章托管保存的账号密码已经达到你设置的保管时间，为了安全起见请尽快到本站修改所保存的账号密码<br/><a href='"
						+ request.getRequestURL() + "'>" + request.getRequestURL()
						+ "</a><br/>温馨提示：账号密码都不要长时间不修改。也不要使用同样的密码.<br/>如果以上链接无法点击，请将它复制到你的浏览器地址栏中进入访问<br/>如果此次请求非你本人所发，请忽略本邮件。";
				Pwd_plan plan = new Pwd_plan(user.getId(), user.getUsername(), user.getEmail(), String.valueOf(time), 1,
						content, 0);
				int i = new CommonDao().save(plan);
				if (i > 0) {
					try {
						response.getWriter().print(GeneralUtil.EchoMsg("200", "添加预约提醒服务成功", 0, null));
					} catch (IOException e) {
						e.printStackTrace();
					}
				} else {
					try {
						response.getWriter().print(GeneralUtil.EchoMsg("201", "数据请求失败", 0, null));
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		} catch (NumberFormatException e) {
			System.out.println("异常：\"" + stime + "\"不是数字/整数...");
			try {
				response.getWriter().print(GeneralUtil.EchoMsg("0", "数据请求格式错误", 0, null));
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		return null;
	}
}
