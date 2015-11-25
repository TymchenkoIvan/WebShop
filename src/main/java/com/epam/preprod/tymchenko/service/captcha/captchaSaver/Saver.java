package com.epam.preprod.tymchenko.service.captcha.captchaSaver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.preprod.tymchenko.service.captcha.Captcha;

/**
 * Interface validates captcha and sends its to client.
 * 
 * @author Ivan_Tymchenko
 */
public interface Saver {
	
	/**
	 * Checks is captcha.code correct to captcha.id.
	 * 
	 * @param request HttpServletRequest
	 * @param captchaCode String
	 * @return true if captchaCode correct
	 */
	boolean validateCaptchaById(HttpServletRequest request, String captchaCode);

	/**
	 * Saves captcha.id to request or response for client side.
	 * 
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @param captcha Captcha
	 */
	void saveCaptcha(HttpServletRequest request, HttpServletResponse response, Captcha captcha);
}