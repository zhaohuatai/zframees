package com.zht.demo.mail;

import javax.annotation.Resource;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.zht.framework.mail.SpringMailManger;
import org.zht.framework.spring.SpringUtils;


public class SendmailTest {
	@Resource 
	private SpringMailManger  springMailManger;
	
	 public static void main( String[] args ) { 
		  ConfigurableApplicationContext context= new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		  SpringMailManger springMailManger = context.getBean(SpringMailManger.class);
//		  SpringMailManger springMailManger = SpringUtils.getBean(SpringMailManger.class);
		  String userName="972855736@qq.com";
		  String url= "account/forgotPassword.html?act=confirm&key=" 
							+ "KEY" + "&account=" + "972855736@qq.com" + "824069438@qq.com";
		  String siteName="zhaohuatai";
		  String siteUrl="http://www.zhaohuatai.com";
		  String activeDays="3";
		  String mailContent = SpringMailManger.loadMailTemplate("forgotPassword");
		  //E:\workspace\myeclipse2015\zframees\WebRoot\WEB-INF\classes\template\mail
		 mailContent=mailContent.replaceAll("\\#\\{siteName\\}",userName )
					.replaceAll("\\#\\{url\\}", siteUrl 
							+ "account/forgotPassword.html?act=confirm&key=" 
							+ "KEY" + "&account=" + "972855736@qq.com")
							.replace("\\#\\{activeDays\\}", activeDays)
							.replace("\\#\\{siteUrl\\}", siteUrl)
							.replace("\\#\\{siteName\\}", siteName);
			
			// 发送修改密码邮件
		  springMailManger.sendMimeEmail(mailContent, "重置密码", "972855736@qq.com");;
			
	 }
}
