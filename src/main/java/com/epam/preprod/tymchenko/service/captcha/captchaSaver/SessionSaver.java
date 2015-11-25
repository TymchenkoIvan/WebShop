package com.epam.preprod.tymchenko.service.captcha.captchaSaver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.epam.preprod.tymchenko.constant.Constants;
import com.epam.preprod.tymchenko.service.captcha.Captcha;
import com.epam.preprod.tymchenko.service.captcha.CaptchaContainer;

/**
 * Implements Saver interface and works with Session.
 * 
 * @author Ivan_Tymchenko
 *
 */
public class SessionSaver implements Saver{

	@Override
	public void saveCaptcha(HttpServletRequest request, HttpServletResponse response, Captcha captcha) {
        HttpSession session = request.getSession(true);
		session.setAttribute(Constants.CAPTCHA_ID_ARGS, ""+captcha.getId());
	}

	@Override
	public boolean validateCaptchaById(HttpServletRequest request, String captchaCode){
		HttpSession session = request.getSession(true);
		String captchaId = (String)session.getAttribute(Constants.FORM_CAPTCHA_ID);
		
		return CaptchaContainer.isCaptchaCodeCorrect(captchaId, captchaCode);
	}
}
