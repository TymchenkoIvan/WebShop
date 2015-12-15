package com.company.service.captcha.captchaSaver;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.company.constant.Constants;
import com.company.service.captcha.Captcha;
import com.company.service.captcha.CaptchaContainer;

/**
 * Implements Saver interface and works with Cookie.
 * 
 * @author Ivan_Tymchenko
 *
 */
public class CookieSaver implements Saver{

	@Override
	public void saveCaptcha(HttpServletRequest request, HttpServletResponse response, Captcha captcha) {
		response.addCookie(new Cookie(Constants.CAPTCHA_ID_ARGS, ""+captcha.getId()));
	}

	@Override
	public boolean validateCaptchaById(HttpServletRequest request, String captchaCode){
		String captchaId = null;
		Cookie[] cookies = request.getCookies();
		
        for (Cookie cookie : cookies) {
            if (Constants.CAPTCHA_ID_ARGS.equals(cookie.getName())) {
                captchaId = cookie.getValue();
                break;
            }
        }
        
		if(captchaId == null || captchaId.isEmpty()){
			return false;
		}
		
        return CaptchaContainer.isCaptchaCodeCorrect(captchaId, captchaCode);
	}
}
