package com.init;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.dao.Pwd_setDao;
import com.dao.Pwd_smtpDao;
import com.entity.Pwd_set;
import com.entity.Pwd_smtp;

public class StartInit implements ServletContextListener {
	private static String myEmailAccount;
	private static String myEmailPassword;
	private static String myEmailSMTPHost;
	private static String myEmailSMTPPort;
	private static String SYS_KEY="Zz";
	// private static String PublicKey;
	// private static String PrivateKey;
	private static Pwd_set PwdSet;
	private static Properties props;

	public static String getMyEmailAccount() {
		return myEmailAccount;
	}

	public static void setMyEmailAccount(String myEmailAccount) {
		StartInit.myEmailAccount = myEmailAccount;
	}

	public static String getMyEmailPassword() {
		return myEmailPassword;
	}

	public static void setMyEmailPassword(String myEmailPassword) {
		StartInit.myEmailPassword = myEmailPassword;
	}

	public static String getMyEmailSMTPHost() {
		return myEmailSMTPHost;
	}

	public static void setMyEmailSMTPHost(String myEmailSMTPHost) {
		StartInit.myEmailSMTPHost = myEmailSMTPHost;
	}

	public static String getMyEmailSMTPPort() {
		return myEmailSMTPPort;
	}

	public static void setMyEmailSMTPPort(String myEmailSMTPPort) {
		StartInit.myEmailSMTPPort = myEmailSMTPPort;
	}

	public static Properties getProps() {
		return props;
	}

	public static void setProps(Properties props) {
		StartInit.props = props;
	}

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

	public void contextDestroyed(ServletContextEvent arg0) {
		System.out.println("系统停止...");
	}

	public void contextInitialized(ServletContextEvent arg0) {
		System.out.println("系统初始化开始");
		// 查询系统设置并复制
		PwdSet = Pwd_setDao.SelectAll();
		if (PwdSet.getEmail_sign() == 1) {
			// 设置邮箱
			Pwd_smtp smtp = Pwd_smtpDao.Select();
			myEmailAccount = smtp.getUsername();
			myEmailPassword = smtp.getPassword();
			myEmailSMTPHost = smtp.getHost();
			myEmailSMTPPort = String.valueOf(smtp.getPort());
			// 1. 创建参数配置, 用于连接邮件服务器的参数配置
			props = new Properties(); // 参数配置
			props.setProperty("mail.transport.protocol", "smtp"); // 使用的协议（JavaMail规范要求）
			props.setProperty("mail.smtp.host", myEmailSMTPHost); // 发件人的邮箱的 SMTP服务器地址
			props.setProperty("mail.smtp.auth", "true"); // 需要请求认证

			// PS: 某些邮箱服务器要求 SMTP 连接需要使用 SSL 安全认证 (为了提高安全性, 邮箱支持SSL连接, 也可以自己开启),
			// 如果无法连接邮件服务器, 仔细查看控制台打印的 log, 如果有有类似 “连接失败, 要求 SSL 安全连接” 等错误,
			// 打开下面 /* ... */ 之间的注释代码, 开启 SSL 安全连接。

			// SMTP 服务器的端口 (非 SSL 连接的端口一般默认为 25, 可以不添加, 如果开启了 SSL 连接,
			// 需要改为对应邮箱的 SMTP 服务器的端口, 具体可查看对应邮箱服务的帮助,
			// QQ邮箱的SMTP(SLL)端口为465或587, 其他邮箱自行去查看)
			props.setProperty("mail.smtp.port", myEmailSMTPPort);
			props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			props.setProperty("mail.smtp.socketFactory.fallback", "false");
			props.setProperty("mail.smtp.socketFactory.port", myEmailSMTPPort);
		}

		// 设置AES加密密钥
		/*
		 * String Public = null; String Private = null; try { Public =
		 * loadPublicKeyByFile(); Private = loadPrivateKeyByFile(); } catch
		 * (Exception e) { System.out.println("密钥文件不存在"); RSAUtil.genKeyPair();
		 * try { Public = loadPublicKeyByFile(); Private =
		 * loadPrivateKeyByFile(); } catch (Exception e1) {
		 * e1.printStackTrace(); } } PublicKey=Public; PrivateKey=Private;
		 * System.out.println("公钥为"+PublicKey);
		 * System.out.println("私钥为"+PrivateKey);
		 */
	}

	/**
	 * 从文件中输入流中加载公钥
	 * 
	 * @param in
	 *            公钥输入流
	 * @throws Exception
	 *             加载公钥时产生的异常
	 */
	public static String loadPublicKeyByFile() throws Exception {
		try {
			BufferedReader br = new BufferedReader(new FileReader("G:/key/publicKey.keystore"));
			String readLine = null;
			StringBuilder sb = new StringBuilder();
			while ((readLine = br.readLine()) != null) {
				sb.append(readLine);
			}
			br.close();
			return sb.toString();
		} catch (IOException e) {
			throw new Exception("公钥数据流读取错误");
		} catch (NullPointerException e) {
			throw new Exception("公钥输入流为空");
		}
	}

	/**
	 * 从文件中加载私钥
	 * 
	 * @param keyFileName
	 *            私钥文件名
	 * @return 是否成功
	 * @throws Exception
	 */
	public static String loadPrivateKeyByFile() throws Exception {
		try {
			BufferedReader br = new BufferedReader(new FileReader("G:/key/privateKey.keystore"));
			String readLine = null;
			StringBuilder sb = new StringBuilder();
			while ((readLine = br.readLine()) != null) {
				sb.append(readLine);
			}
			br.close();
			return sb.toString();
		} catch (IOException e) {
			throw new Exception("私钥数据读取错误");
		} catch (NullPointerException e) {
			throw new Exception("私钥输入流为空");
		}
	}

}
