package com.init;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.dao.Pwd_setDao;
import com.entity.Pwd_set;

public class StartInit implements ServletContextListener {

	private static String SYS_KEY = "Zz";

	private static Pwd_set PwdSet;

	public static Pwd_set getPwdSet() {
		return PwdSet;
	}

	public static void setPwdSet(Pwd_set pwdSet) {
		PwdSet = pwdSet;
	}

	public static String getSYS_KEY() {
		return SYS_KEY;
	}

	public static void setSYS_KEY(String sYS_KEY) {
		SYS_KEY = sYS_KEY;
	}

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {

	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		PwdSet = Pwd_setDao.SelectAll();
	}

}
