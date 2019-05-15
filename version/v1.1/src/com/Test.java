package com;

import com.entity.Mail_Auth;
import com.util.MailUtil;

public class Test {
	public static void main(String[] args) {
		Mail_Auth mail = new Mail_Auth("smtp.mxhichina.com", "465", "pwd@xtboke.cn", "zhangwentian@vip.qq.com", "密码本",
				"pwd@xtboke.cn", "to00i0toi9.", "用户帐号重置", "亲爱的zzzwt：<br/>您正在进行找回密码操作。<br/>请点击链接重新设置您的密码。<br/><a href='http://localhost:8080/pwd/user.do?method=resetpwd&reset_pwd_verify=b41ffe6246bd18d71551fb73e3c9ddd4' target='_blank'>http://localhost:8080/pwd/user.do?method=resetpwd&reset_pwd_verify=b41ffe6246bd18d71551fb73e3c9ddd4</a><br/>如果以上链接无法点击，请将它复制到你的浏览器地址栏中进入访问，该链接24小时内有效。<br/>如果此次密码找回请求非你本人所发，说明有人盯上你的账号了。<br/>");
		if (new MailUtil().send(mail)) {
			System.out.println("发送成功");
		} else {
			System.out.println("发送失败");
		}
	}
	/*public static void main(String[] args) {
		 HtmlEmail email = new HtmlEmail();
	        try {
	            // 这里是SMTP发送服务器的名字：163的如下："smtp.163.com"
	            email.setHostName("smtp.163.com");
	            // 字符编码集的设置
	            email.setCharset("utf-8");
	            // 收件人的邮箱
	            email.addTo("862163687@qq.com");
	            // 发送人的邮箱2
	            email.setFrom("18674367434@163.com", "18674367434@163.com");
	            // 如果需要认证信息的话，设置认证：用户名-密码     ***是你开启POP3服务时的授权码，不是登录密码
	            email.setAuthentication("18674367434@163.com", "to00i0toi9");
	            // 要发送的邮件主题
	            email.setSubject("Test");
	            // 要发送的信息，由于使用了HtmlEmail，可以在邮件内容中使用HTML标签
	            email.setMsg("测试发送邮件");
	            // 发送
	            email.send();
	            System.out.println("发送成功");
	        } catch (EmailException e) {
	            e.printStackTrace();
	            System.out.println("发送失败");
	        }
	}*/
}
