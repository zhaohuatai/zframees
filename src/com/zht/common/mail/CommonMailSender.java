package com.zht.common.mail;

import javax.annotation.Resource;

import org.zht.framework.mail.SpringMailManger;

public class CommonMailSender {
	@Resource 
	private SpringMailManger  springMailManger;
	/**
	 * 
	 * @param title 邮件标题
	 * @param mailTo 邮件接受者
	 * @param userAccount 用户account
	 * @param resetUrl 重置url
	 * @param siteName 服务器站点名称
	 * @param activeTime 邮件验证时间限制 
	 * @param timeUnit 邮件验证时间限制单位
	 * @param siteUrl 服务器站点链接地址
	 */
	public void sendForgetPasswordMail(	String title,
										String mailTo,
										String userAccount,
										String resetUrl,
										String siteName,
										String activeTime,
										String timeUnit,
										String siteUrl){
		  String mailContent = SpringMailManger.loadMailTemplate("forgotPassword");
			mailContent = mailContent.replace("$userAccount", userAccount);
			mailContent = mailContent.replace("$url", resetUrl);
			mailContent = mailContent.replace("$activeTime", activeTime);
			mailContent = mailContent.replace("$timeUnit", timeUnit);
			mailContent = mailContent.replace("$siteName", siteUrl);
			mailContent = mailContent.replace("$siteUrl", siteName);
				// 发送修改密码邮件
			springMailManger.sendMimeEmail(mailContent, title, mailTo);
	}
	/**
	 * 
	 * @param title 邮件标题
	 * @param mailTo 邮件接受者
	 * @param userAccount 用户account
	 * @param activeUrl 激活url
	 * @param siteName 服务器站点名称
	 * @param activeTime 邮件验证时间限制 
	 * @param timeUnit 邮件验证时间限制单位
	 * @param siteUrl 服务器站点链接地址
	 */
	public void sendaccountActiveMail(	String title,
										String mailTo,
										String userAccount,
										String activeUrl,
										String siteName,
										String activeTime,
										String timeUnit,
										String siteUrl){
		String mailContent = SpringMailManger.loadMailTemplate("accountActive");
		mailContent = mailContent.replace("$userAccount", userAccount);
		mailContent = mailContent.replace("$activeUrl", activeUrl);
		mailContent = mailContent.replace("$activeTime", activeTime);
		mailContent = mailContent.replace("$timeUnit", timeUnit);
		mailContent = mailContent.replace("$siteName", siteName );
		mailContent = mailContent.replace("$siteUrl", siteUrl);
		springMailManger.sendMimeEmail(mailContent, title, mailTo);
			
	}
}
