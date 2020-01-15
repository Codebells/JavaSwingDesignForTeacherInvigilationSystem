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
	 * @param return ���ͳɹ�����trueʧ�ܷ���false
	 * �����ʼ���mail��ַ
	 */
	public boolean Sent() {
//		to = "644613263@qq.com";
        // �����˵�������
		String to=this.mail;
        String from = "1347103071@qq.com";

        // ָ�������ʼ�������Ϊ smtp.qq.com
        String host = "smtp.qq.com";  //QQ �ʼ�������

        // ��ȡϵͳ����
        Properties properties = System.getProperties();

        // �����ʼ�������
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
        // ��ȡĬ��session����
        Session session = Session.getDefaultInstance(properties,new Authenticator(){
            public PasswordAuthentication getPasswordAuthentication()
            {
                return new PasswordAuthentication("1347103071@qq.com", "zfgdwuatuvglheaj"); //�������ʼ��û���������
            }
        });

        try{
            // ����Ĭ�ϵ� MimeMessage ����
            MimeMessage message = new MimeMessage(session);

            // Set From: ͷ��ͷ�ֶ�
            message.setFrom(new InternetAddress(from));

            // Set To: ͷ��ͷ�ֶ�
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            // Set Subject: ͷ��ͷ�ֶ�
            message.setSubject("��������");

            // ������Ϣ��
            message.setText("���ļ࿼�����б䣬���¼�˺Ų鿴");

            // ������Ϣ
            Transport.send(message);
            System.out.println("Sent message successfully");
            return true;
        }catch (MessagingException mex) {
            mex.printStackTrace();
            return false;
        }
	}
}
