package com.company.captcha;

import static org.junit.Assert.*;

import org.junit.Test;

import com.company.service.captcha.Captcha;

public class CaptchaTest {
	
	private static int timeout = 1000000;
	
	@Test
	public void initTimeOutSets() {
		Captcha.initTimeOut(timeout);
		assertEquals(timeout, Captcha.getTimeout());
	}
}
