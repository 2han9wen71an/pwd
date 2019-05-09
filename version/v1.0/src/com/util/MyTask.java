package com.util;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimerTask;

import javax.servlet.ServletContext;

import com.dao.Pwd_planDao;
import com.dao.Pwd_smtpDao;
import com.entity.Pwd_plan;
import com.entity.Pwd_smtp;

public class MyTask extends TimerTask {
	private static boolean isRunning = false;
	private ServletContext context = null;

	public MyTask(ServletContext context) {
		this.context = context;
	}

	public void run() {
		Calendar cal = Calendar.getInstance();
		if (!isRunning) {
			isRunning = true;
			context.log("开始执行指定任务");
			long time = new Date().getTime();
			System.out.println("当前时间"+time);
			List selectAll = Pwd_planDao.SelectAll(String.valueOf(time));
			for (int i = 0; i < selectAll.size(); i++) {
				Pwd_plan Pwd_plan = (com.entity.Pwd_plan) selectAll.get(i);
				System.out.println(Pwd_plan.getContent());
			}
			if (selectAll.size() == 0) {
				System.out.println("当前无任务");
			} else {
				Pwd_smtp smtp = Pwd_smtpDao.Select();
				if (smtp.getHost() == null || smtp.getPort() == 0 || smtp.getUsername() == null
						|| smtp.getPassword() == null || smtp.getSub() == null) {
					System.out.println("邮件smtp未配置");
				} else {
					for (int i = 0; i < selectAll.size(); i++) {
						Pwd_plan plan = (com.entity.Pwd_plan) selectAll.get(i);
						int id = plan.getId();
						int userid = plan.getUserid();
						String email = plan.getEmail();
						String stime = plan.getStime();
						String content = plan.getContent();
						int type = plan.getType();
						String msgTitle = null;
						String msg = null;
						if (type == 1) {
							msgTitle = "系统信息";
							msg = "(预约)";
						} else if (type == 2) {
							msgTitle = "用户帐号激活";
							msg = "(注册)";
						} else if (type == 3) {
							msgTitle = "用户帐号重置";
							msg = "(重置)";
						}
						try {
							System.out.println("数据库时间"+Long.valueOf(stime));
							System.out.println("当前时间"+Long.valueOf(time));
							if (plan.getStatus()==0&&plan.getType()==3||Long.valueOf(stime)-Long.valueOf(time)<=60*1000) {
								EmailUtil.sendEmail(msgTitle, content, null, plan.getEmail());
								plan.setStatus(1);
								int j = Pwd_planDao.Update(plan);
								if (j>0) {
									System.out.println("邮件发送成功");
								}else {
									System.out.println("数据请求失败");
								}
							} else {
								System.out.println("当前任务不需要执行");
							}
						} catch (Exception e) {
							System.out.println("邮件发送失败");
						}
					}
				}
			}

			isRunning = false;
			context.log("指定任务执行结束");
		} else {
			context.log("上一次任务执行还未结束");
		}
	}
}
