package org.zht.framework.filter.jcaptcha;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.octo.captcha.service.CaptchaService;
import com.octo.captcha.service.CaptchaServiceException;


public class JCaptchaFilter extends OncePerRequestFilter{
	
	private static String autoPassValue;
	private static CaptchaService captchaService;
	
	private void initCaptchaService() {
		if(captchaService==null){
			FilterConfig fConfig=super.getFilterConfig();
			ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(fConfig.getServletContext());
			captchaService = (CaptchaService) context.getBean("captchaService");
		}
	}
	

	/**
	 * 生成验证码图片.
	 */
	private void genernateCaptchaImage(final HttpServletRequest request, final HttpServletResponse response)
			throws IOException {

		response.setDateHeader("Expires", 0);
		response.addHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setContentType("image/jpeg");

		ServletOutputStream out = response.getOutputStream();
		try {
			String captchaId = request.getSession(true).getId();
			BufferedImage challenge = (BufferedImage) captchaService.getChallengeForID(captchaId, request.getLocale());
			ImageIO.write(challenge, "jpg", out);
			out.flush();
		} catch (CaptchaServiceException e) {
			logger.error(e.getMessage(), e);
		} finally {
			out.close();
		}
	}
	@Override
	protected void doFilterInternal(HttpServletRequest request,HttpServletResponse response, FilterChain filterChain)throws ServletException, IOException {
		autoPassValue=super.getFilterConfig().getInitParameter("autoPassValue");	
		initCaptchaService() ;
		genernateCaptchaImage(request,response);
			
		
	}
	
	public static boolean validateCaptchaChallenge(final HttpServletRequest request,String jcaptchaCode) {
		try {
			String sessionId = request.getSession(true).getId();
			//自动通过值存在时,检验输入值是否等于自动通过值
			if (StringUtils.isNotBlank(autoPassValue) && autoPassValue.equals(jcaptchaCode)) {
				return true;
			}
			if(jcaptchaCode==null||	jcaptchaCode.length()==0){
				return false;
			}
			if(captchaService==null){
				return false;
			}
			return captchaService.validateResponseForID(""+sessionId, ""+jcaptchaCode);
		} catch (CaptchaServiceException e) {
			return false;
		}
	}
}
