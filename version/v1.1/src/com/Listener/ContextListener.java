package com.Listener;

import java.util.Timer;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.util.MyTask;

public class ContextListener implements ServletContextListener {
	private static java.util.Timer timer = null;
	private static boolean isRun = true;

	public static java.util.Timer getTimer() {
		return timer;
	}

	public static void setTimer(java.util.Timer timer) {
		ContextListener.timer = timer;
	}

	public static boolean isRun() {
		return isRun;
	}

	public static void setRun(boolean isRun) {
		ContextListener.isRun = isRun;
	}

	public void contextInitialized(ServletContextEvent event) {
		if (isRun) {
			timer = new java.util.Timer(true);
			event.getServletContext().log("定时器已启动");
			timer.schedule(new MyTask(), 0, 1 * 60 * 1000);
			event.getServletContext().log("已经添加任务调度表");
		}
	}

	public void contextDestroyed(ServletContextEvent event) {
		timer.cancel();
		event.getServletContext().log("定时器销毁");
	}

	/**
	 * 启动定时任务
	 */
	public static void startTimerTask() {
		if (timer == null) {
			timer = new Timer();
		}
		timer.schedule(new MyTask(), 0, 1 * 60 * 1000);
	}

	/**
	 * 定时任务取消
	 */
	public static void stopTimerTask() {
		timer.cancel();
		timer = null;// 如果不重新new，会报异常
	}

}
