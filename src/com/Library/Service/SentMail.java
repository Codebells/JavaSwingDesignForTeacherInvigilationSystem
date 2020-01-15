package com.Library.Service;

import java.security.GeneralSecurityException;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import com.sun.mail.util.MailSSLSocketFactory;

public class SentMail {
	String mail;
	public SentMail(String mail) {
		// TODO Auto-generated constructor stub
		this.mail=mail;
	}
	/**
	 * @param return 发送成功返回true失败返回false
	 * 发送邮件给mail地址
	 */
	public boolean Sent() {
//		to = "644613263@qq.com";
        // 发件人电子邮箱
		String to=this.mail;
        String from = "1347103071@qq.com";

        // 指定发送邮件的主机为 smtp.qq.com
        String host = "smtp.qq.com";  //QQ 邮件服务器

        // 获取系统属性
        Properties properties = System.getProperties();

        // 设置邮件服务器
        properties.setProperty("mail.smtp.host", host);

        properties.put("mail.smtp.auth", "true");
        MailSSLSocketFactory sf = null;
		try {
			sf = new MailSSLSocketFactory();
		} catch (GeneralSecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        sf.setTrustAllHosts(true);
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.ssl.socketFactory", sf);
        // 获取默认session对象
        Session session = Session.getDefaultInstance(properties,new Authenticator(){
            public PasswordAuthentication getPasswordAuthentication()
            {
                return new PasswordAuthentication("1347103071@qq.com", "zfgdwuatuvglheaj"); //发件人邮件用户名、密码
            }
        });

        try{
            // 创建默认的 MimeMessage 对象
            MimeMessage message = new MimeMessage(session);

            // Set From: 头部头字段
            message.setFrom(new InternetAddress(from));

            // Set To: 头部头字段
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            // Set Subject: 头部头字段
            message.setSubject("教务在线");

            // 设置消息体
            message.setText("您的监考任务有变，请登录账号查看");

            // 发送消息
            Transport.send(message);
            System.out.println("Sent message successfully");
            return true;
        }catch (MessagingException mex) {
            mex.printStackTrace();
            return false;
        }
	}
}
