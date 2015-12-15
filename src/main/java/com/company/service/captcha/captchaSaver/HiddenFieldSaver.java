package com.company.service.captcha.captchaSaver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.company.constant.Constants;
import com.company.service.captcha.Captcha;
import com.company.service.captcha.CaptchaContainer;

/**
 * Implements Saver interface and works with hidden field.
 * 
 * @author Ivan_Tymchenko
 *
 */
public class HiddenFieldSaver implements Saver{

	@Override
	public void saveCaptcha(HttpServletRequest request, HttpServletResponse response, Captcha captcha) {
		request.setAttribute(Constants.CAPTCHA_ID_ARGS, ""+captcha.getId());
	}

	@Override
	public boolean validateCaptchaById(HttpServletRequest request, String captchaCode) {
		String captchaId = (String) request.getParameter(Constants.CAPTCHA_ID_ARGS);
		
		if(captchaId == null || captchaId.isEmpty()){
			return false;
		}
		
		return CaptchaContainer.isCaptchaCodeCorrect(captchaId, captchaCode);
	}

}
