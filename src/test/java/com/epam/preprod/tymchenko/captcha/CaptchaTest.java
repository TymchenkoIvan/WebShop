package com.epam.preprod.tymchenko.captcha;

import static org.junit.Assert.*;

import org.junit.Test;

import com.epam.preprod.tymchenko.service.captcha.Captcha;

public class CaptchaTest {
	
	private static int timeout = 1000000;
	
	@Test
	public void initTimeOutSets() {
		Captcha.initTimeOut(timeout);
		assertEquals(timeout, Captcha.getTimeout());
	}
}
