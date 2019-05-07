package com.util;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.dao.Pwd_smtpDao;
import com.entity.Pwd_smtp;
import com.init.StartInit;

public class EmailUtil {

	public static void sendEmail(String title, String content, Date date, String receiveMailAccount) {
		Properties props = StartInit.getProps();
		Session session = Session.getDefaultInstance(props);
		session.setDebug(true); // 设置为debug模式, 可以查看详细的发送 log
		MimeMessage message = null;
		try {
			message = createMimeMessage(session, StartInit.getMyEmailAccount(), receiveMailAccount, title, content,
					date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 1. 根据 Session 获取邮件传输对象
		Transport transport = null;
		try {
			transport = session.getTransport();
		} catch (NoSuchProviderException e) {
			e.printStackTrace();
		}

		// 2. 使用 邮箱账号 和 密码 连接邮件服务器, 这里认证的邮箱必须与 message 中的发件人邮箱一致, 否则报错
		//
		// PS_01: 成败的判断关键在此一句, 如果连接服务器失败, 都会在控制台输出相应失败原因的 log,
		// 仔细查看失败原因, 有些邮箱服务器会返回错误码或查看错误类型的链接, 根据给出的错误
		// 类型到对应邮件服务器的帮助网站上查看具体失败原因。
		//
		// PS_02: 连接失败的原因通常为以下几点, 仔细检查代码:
		// (1) 邮箱没有开启 SMTP 服务;
		// (2) 邮箱密码错误, 例如某些邮箱开启了独立密码;
		// (3) 邮箱服务器要求必须要使用 SSL 安全连接;
		// (4) 请求过于频繁或其他原因, 被邮件服务器拒绝服务;
		// (5) 如果以上几点都确定无误, 到邮件服务器网站查找帮助。
		//
		// PS_03: 仔细看log, 认真看log, 看懂log, 错误原因都在log已说明。
		try {
			transport.connect(StartInit.getMyEmailAccount(), StartInit.getMyEmailPassword());
		} catch (MessagingException e) {
			e.printStackTrace();
		}

		// 3. 发送邮件, 发到所有的收件地址, message.getAllRecipients()
		// 获取到的是在创建邮件对象时添加的所有收件人,
		// 抄送人, 密送人
		try {
			transport.sendMessage(message, message.getAllRecipients());
		} catch (MessagingException e) {
			e.printStackTrace();
		}

		// 4. 关闭连接
		try {
			transport.close();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 创建一封只包含文本的简单邮件
	 *
	 * @param session
	 *            和服务器交互的会话
	 * @param sendMail
	 *            发件人邮箱
	 * @param receiveMail
	 *            收件人邮箱
	 * @return
	 * @throws Exception
	 */
	public static MimeMessage createMimeMessage(Session session, String sendMail, String receiveMail, String title,
			String content, Date date) {
		// 1. 创建一封邮件
		MimeMessage message = new MimeMessage(session);

		// 2. From: 发件人（昵称有广告嫌疑，避免被邮件服务器误认为是滥发广告以至返回失败，请修改昵称）
		try {
			Pwd_smtp smtp = Pwd_smtpDao.Select();
			message.setFrom(new InternetAddress(sendMail, smtp.getSub(), "UTF-8"));

			// 3. To: 收件人（可以增加多个收件人、抄送、密送）new
			// InternetAddress("1740698170@qq.com",
			// "Y用户", "utf-8")
			InternetAddress[] a = new InternetAddress[] { new InternetAddress(receiveMail, "用户", "UTF-8") };
			message.setRecipients(MimeMessage.RecipientType.TO, a);

			// 4. Subject: 邮件主题（标题有广告嫌疑，避免被邮件服务器误认为是滥发广告以至返回失败，请修改标题）
			message.setSubject(title, "UTF-8");

			// 5. Content:
			// 邮件正文（可以使用html标签）（内容有广告嫌疑，避免被邮件服务器误认为是滥发广告以至返回失败，请修改发送内容）
			message.setContent(content, "text/html;charset=UTF-8");

			// 6. 设置发件时间
			if (date != null) {
				message.setSentDate(date);
			} else {
				message.setSentDate(new Date());
			}

			// 7. 保存设置
			message.saveChanges();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return message;
	}
}
