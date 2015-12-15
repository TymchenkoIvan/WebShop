package com.company.util;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.company.exception.ValidationException;
import com.company.service.bean.RegistrationFormBean;
import com.company.tymchenko.util.Validator;

public class ValidatorTest {

	private static RegistrationFormBean nullBean = new RegistrationFormBean();
	private static RegistrationFormBean validBean = new RegistrationFormBean();
	private static RegistrationFormBean notValidBean = new RegistrationFormBean();
	
    @Before
    public void init() {
    	validBean.setFirstName("Name");
    	validBean.setLastName("Lastname");
    	validBean.setLogin("Login");
    	validBean.setMail("timchenko@gmail.com");
    	validBean.setPassword("lolipop89");
    	validBean.setRePassword("lolipop89");
    	validBean.setCaptchaCode("123456789");
    	
    	notValidBean.setFirstName("2");
    }

	@Test(expected = ValidationException.class)
	public void validateNullBean() throws ValidationException {
		Validator.validate(nullBean);
	}

	@Test
	public void validateValidBean() throws ValidationException {
		assertTrue(Validator.validate(validBean));
	}
	
	@Test(expected = ValidationException.class)
	public void validateNotValidBean() throws ValidationException {
		Validator.validate(notValidBean);
	}
}
