package com.epam.preprod.tymchenko.service.captcha;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Contains map with captcha id and random integet code.  
 * 
 * @author Ivan_Tymchenko
 *
 */
public class CaptchaContainer {
	
	private static final Map<Long, Integer> CAPTCHAS = new HashMap<Long, Integer>();
	
	public static void add(Long captchaId, int captchaCode){
		CAPTCHAS.put(captchaId, captchaCode);
	}
	
	public static boolean isCaptchaCodeCorrect(String captchaId, String number){
		long id = Long.parseLong(captchaId);
		int code = Integer.parseInt(number);

		deleteOldCaptchas();
		if(CAPTCHAS.containsKey(id)){
			return CAPTCHAS.get(id) == code;
		}
		return false;
	}
	
	private static void deleteOldCaptchas(){
		long currentTime = new Date().getTime();
		
		for(Iterator<Map.Entry<Long, Integer>> it = CAPTCHAS.entrySet().iterator(); it.hasNext(); ) {
			Map.Entry<Long, Integer> entry = it.next();
			
		    if(entry.getKey() < currentTime - Captcha.getTimeout()) {
		       it.remove();
		    }
		}
	}
}

