package org.zht.framework.mail;

import java.io.File;
import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;
import org.apache.commons.io.FileUtils;
import org.springframework.core.task.TaskExecutor;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.zht.framework.exception.TaskFailedException;
import org.zht.framework.util.ConfigUtil;
import org.zht.framework.util.LogUtil;
/**
 * 邮件发送
* @ClassName :SpringMailManger     
* @Description :   
* @createTime :2015年5月8日  下午5:38:39   
* @author ：zhaohuatai   
* @version :1.0
 */
public class SpringMailManger {

	@Resource 
	private  JavaMailSender mailSender;
	private String maiFrom =ConfigUtil.getConfig("system.properties", "mail.from", "unKnown");
	
	@Resource 
	private TaskExecutor taskExecutor;
	
	public void sendSimpleEmail(String message, String to){
		sendSimpleEmail(message, maiFrom+"给您的新邮件", to);
	}
	/**
	 * 异步发送简单信息邮件
	 * @param message
	 * @param subject
	 * @param to
	 */
	@Async
	public void sendSimpleEmail(String message, String subject, String to){
		final SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setFrom(maiFrom);
		mailMessage.setTo(to);
		mailMessage.setSubject(subject);
		mailMessage.setText(message);
		taskExecutor.execute(new Runnable() {
			@Override
			public void run() {
				try{
					mailSender.send(mailMessage);
				} catch (MailException e) {
					LogUtil.genErrorLog(SpringMailManger.class,"sendSimpleEmail",e.getMessage(), e);
					throw new TaskFailedException("邮件发送失败!", e);
				}
			}
		});
	}
	/**
	 * 异步发送带有格式的邮件
	 * @param message
	 * @param subject
	 * @param to
	 */
	@Async
	public void sendMimeEmail(String message, String subject, String to){
		final MimeMessage msg = mailSender.createMimeMessage();
		MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(msg, "UTF-8");
		try {
			mimeMessageHelper.setFrom(maiFrom);
			mimeMessageHelper.setTo(to);
			mimeMessageHelper.setSubject(subject);
			mimeMessageHelper.setText(message, true);
			taskExecutor.execute(new Runnable() {
				@Override
				public void run() {
					mailSender.send(msg);
				}
			});
		} catch (Exception e) {
			LogUtil.genErrorLog(SpringMailManger.class,"sendMimeMessage",e.getMessage(), e);
			throw new TaskFailedException("邮件发送失败!", e);
		} 
	}
	public static String loadMailTemplate(String templateName){
	
		String templateDir=ClassLoader.getSystemResource("").toString()+File.separator+"template"+File.separator+"mail"+File.separator;
		templateDir=templateDir.replace(".", File.separator);
		templateDir=templateDir.replace("file:/", "");
		String filename = templateDir + templateName + ".tpl";
		try {
			return FileUtils.readFileToString(new File(filename), "UTF-8");
		} catch (Exception e) {
			LogUtil.genErrorLog(SpringMailManger.class,"getMailTemplate",e.getMessage(), e);
			throw new TaskFailedException(e.getMessage());
		}
	}


}
