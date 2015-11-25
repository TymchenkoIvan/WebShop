package com.epam.preprod.tymchenko.captcha;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import com.epam.preprod.tymchenko.service.captcha.CaptchaContainer;

public class CaptchaContainerTest {
	
	private static int captchaCode = 12345;
	private static Long timeout = 1000000L;
	private static Long captchaId;
	private static Long captchaOldId;
	private static Long captchaIdIncorrect = 0L;
	
	@Before
	public void init(){
		captchaId = new Date().getTime();
		captchaOldId = new Date().getTime() - timeout;
		
		CaptchaContainer.add(captchaId, captchaCode);
		CaptchaContainer.add(captchaOldId, captchaCode);
	}

	@Test
	public void isCaptchaCodeCorrectWithCorrectInputs() {
		assertTrue(CaptchaContainer.isCaptchaCodeCorrect(""+captchaId, ""+captchaCode));
	}

	@Test
	public void isCaptchaCodeCorrectWithIncorrectInputs() {
		assertFalse(CaptchaContainer.isCaptchaCodeCorrect(""+captchaIdIncorrect, ""+captchaCode));
	}
	
	@Test
	public void isCaptchaCodeCorrectOldId() {
		assertFalse(CaptchaContainer.isCaptchaCodeCorrect(""+captchaOldId, ""+captchaCode));
	}
}
