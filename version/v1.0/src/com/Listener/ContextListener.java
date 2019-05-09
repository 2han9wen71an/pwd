package com.Listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.util.MyTask;

public class ContextListener implements ServletContextListener {
	private java.util.Timer timer = null;
 
	public void contextInitialized(ServletContextEvent event) {
		timer = new java.util.Timer(true);
		event.getServletContext().log("定时器已启动");
		timer.schedule(new MyTask(event.getServletContext()), 0, 1 * 60 * 1000);
		event.getServletContext().log("已经添加任务调度表");
	}

	public void contextDestroyed(ServletContextEvent event) {
		timer.cancel();
		event.getServletContext().log("定时器销毁");
	}

}
