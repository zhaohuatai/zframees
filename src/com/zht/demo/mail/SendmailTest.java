package com.zht.demo.mail;

import javax.annotation.Resource;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.zht.framework.mail.SpringMailManger;
import org.zht.framework.spring.SpringUtils;


public class SendmailTest {
	
	 public static void main( String[] args ) { 
		 ConfigurableApplicationContext context= new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		  SpringMailManger springMailManger = context.getBean(SpringMailManger.class);
		  String userName="972855736@qq.com";
		  String url= "http://wwww.zhaohuatai.com/account/forgotPassword.html";
		  String siteName="zhaohuatai";
		  String siteUrl="http://www.zhaohuatai.com";
		  String activeTime="3";
		  String activeTimeUnit="天";
		String mailContent = SpringMailManger.loadMailTemplate("forgotPassword");
		mailContent = mailContent.replace("$userAccount", userName);
		mailContent = mailContent.replace("$url", url);
		mailContent = mailContent.replace("$activeTime", activeTime);
		mailContent = mailContent.replace("$timeUnit", activeTimeUnit);
		mailContent = mailContent.replace("$siteName",siteName );
		mailContent = mailContent.replace("$siteUrl", siteUrl);
			// 发送修改密码邮件
		springMailManger.sendMimeEmail(mailContent, "重置密码", "972855736@qq.com");
		System.out.println("forgotPassword---over"); 
		//-----------------------------------------------------------------------------
		mailContent="";
		mailContent = SpringMailManger.loadMailTemplate("accountActive");
		mailContent = mailContent.replace("$userAccount", userName);
		mailContent = mailContent.replace("$activeUrl", url);
		mailContent = mailContent.replace("$activeTime", activeTime);
		mailContent = mailContent.replace("$timeUnit", activeTimeUnit);
		mailContent = mailContent.replace("$siteName", siteName );
		mailContent = mailContent.replace("$siteUrl", siteUrl);
		springMailManger.sendMimeEmail(mailContent, "账户激活", "972855736@qq.com");
		System.out.println("accountActive---over"); 
	 }
}
