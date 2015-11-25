package com.epam.preprod.tymchenko.util;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.epam.preprod.tymchenko.service.bean.LoginFormBean;
import com.epam.preprod.tymchenko.service.bean.OrderDetailsFormBean;
import com.epam.preprod.tymchenko.service.bean.OrderInfoBean;
import com.epam.preprod.tymchenko.service.bean.ProductBean;
import com.epam.preprod.tymchenko.service.bean.RegistrationFormBean;
import com.epam.preprod.tymchenko.service.bean.SortFormBean;
import com.epam.preprod.tymchenko.service.captcha.captchaSaver.Saver;
import com.epam.preprod.tymchenko.entity.Delivery;
import com.epam.preprod.tymchenko.entity.Payment;
import com.epam.preprod.tymchenko.exception.ValidationException;
import com.epam.preprod.tymchenko.exception.Messages;

import com.epam.preprod.tymchenko.constant.Constants;

/**
 * Helps to validate different information.
 * 
 * @author Ivan_Tymchenko
 *
 */
public class Validator {
	
	private static final Logger LOG = Logger.getLogger(ClassNameUtil.getCurrentClassName());
	
	public static boolean validate(OrderInfoBean bean) throws ValidationException{
		LOG.info("validate(OrderInfoBean bean)");
		
		Map<String, String> exceptions = new HashMap<>();

		if (bean.getUserId() <= 0 ){
			exceptions.put(Constants.ORDER_DETAILS_FORM_DELIVERY, Messages.VALIDATION_ORDER_DETAILS_WRONG);
			throw new ValidationException(Messages.VALIDATION_ORDER_WRONG);
		}
		if (bean.getDeliveryId() <= 0 ){
			throw new ValidationException(Messages.VALIDATION_ORDER_WRONG);
		}
		if (bean.getPaymentId() <= 0 ){
			throw new ValidationException(Messages.VALIDATION_ORDER_WRONG);
		}
		if (!validateMap(bean.getMap())){
			throw new ValidationException(Messages.VALIDATION_ORDER_WRONG_EMPTY_BASKET);
		}

		return true;
	}
	
	public static Map<String, String> validate(OrderDetailsFormBean bean){
		LOG.info("validate(OrderDetailsFormBean bean)");
		LOG.info("Bean: "+bean.getDelivery()+" "+bean.getPayment());
		
		Map<String, String> exceptions = new HashMap<>();

		if (!validateDelivery(bean.getDelivery())){
			LOG.warn(Messages.VALIDATION_ORDER_DETAILS_WRONG);
			exceptions.put(Constants.ORDER_DETAILS_FORM_DELIVERY, Messages.VALIDATION_ORDER_DETAILS_WRONG);
		}
		if (!validatePayment(bean.getPayment())){
			LOG.warn(Messages.VALIDATION_ORDER_DETAILS_WRONG);
			exceptions.put(Constants.ORDER_DETAILS_FORM_PAYMENT, Messages.VALIDATION_ORDER_DETAILS_WRONG);
		}

		LOG.info("Exceptions map size: " + exceptions.size());
		return exceptions;
	}
	
	public static boolean validate(RegistrationFormBean bean) throws ValidationException{
		
		if (!validate(Constants.PATTERN_NAME, bean.getFirstName())){
			throw new ValidationException(Messages.VALIDATION_LAST_NAME_WRONG);
		}
		if (!validate(Constants.PATTERN_NAME, bean.getLogin())){
			throw new ValidationException(Messages.VALIDATION_LOGIN_WRONG);
		}
		if (!validate(Constants.PATTERN_NAME, bean.getLastName())){
			throw new ValidationException(Messages.VALIDATION_LAST_NAME_WRONG);
		}
		if (!validate(Constants.PATTERN_MAIL, bean.getMail())){
			throw new ValidationException(Messages.VALIDATION_MAIL_WRONG);
		}
		if (!validate(Constants.PATTERN_PASSWORD, bean.getPassword())){
			throw new ValidationException(Messages.VALIDATION_PASS_WRONG);
		}
		if (!bean.getPassword().equals(bean.getRePassword())){
			throw new ValidationException(Messages.VALIDATION_PASS_CONFIRM_WRONG);
		}
		if (!validate(Constants.PATTERN_NUMBER, bean.getCaptchaCode())){
			throw new ValidationException(Messages.VALIDATION_CAPTCHA_WRONG);
		}
		return true;
	}
	
	public static boolean validate(LoginFormBean bean) throws ValidationException{
		
		if (!validate(Constants.PATTERN_NAME, bean.getLogin())){
			throw new ValidationException(Messages.VALIDATION_LOGIN_WRONG);
		}
		if (!validate(Constants.PATTERN_PASSWORD, bean.getPassword())){
			throw new ValidationException(Messages.VALIDATION_PASS_WRONG);
		}

		return true;
	}
	
	public static boolean validate(SortFormBean bean) throws ValidationException{

		if (!validatePrice(bean.getMinPrice())){
			throw new ValidationException();
		}
		
		if (!validatePrice(bean.getMaxPrice())){
			throw new ValidationException();
		}
		
		if (!validateOnPage(bean.getOnPage())){
			throw new ValidationException();
		}
		
		return true;
	}

	public static boolean validatePositiveNumberOrEmpty(String number) throws ValidationException{
		try{
			if(number == null || number.isEmpty() || "".equals(number)){
				return false;
			} else{
				int temp = Integer.parseInt(number);
				
				return temp > 0;
			}
		} catch(Exception e){
			LOG.warn(e.getMessage());
			throw new ValidationException(e.getMessage());
		}
	}
	
	public static boolean validateCaptcha(HttpServletRequest request, RegistrationFormBean bean, Saver captchaSaver) throws ValidationException{
		if(!captchaSaver.validateCaptchaById(request, bean.getCaptchaCode())){
			throw new ValidationException(Messages.VALIDATION_CAPTCHA_WRONG);
		}
		return true;
	}
	
	private static boolean validate(String pattern, String line){
		if(line == null || line.isEmpty()){
			return false;
		}
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(line);
        return m.matches();
	}
	
	private static boolean validatePrice(String price){
		
		if(price == null || price.isEmpty() || "".equals(price)){
			return true;
		}

		if(!validate(Constants.PATTERN_NUMBER, price)){
			return false;
		}
		
		if (Integer.parseInt(price) < 0 || Integer.parseInt(price) > Integer.MAX_VALUE){
			return false;
		}
		
		return true;
	}
	
	private static boolean validateOnPage(String page){
		
		if(page == null || page.isEmpty() || "".equals(page)){
			return true;
		}

		if(!validate(Constants.PATTERN_NUMBER, page)){
			return false;
		}
		
		if (Integer.parseInt(page) < 1 || Integer.parseInt(page) > Constants.MAX_ITEMS_ON_PAGE){
			return false;
		}
		
		return true;
	}
	
	private static boolean validateDelivery(String line){
		
		if(line == null || line.isEmpty() || "".equals(line)){
			return false;
		}
		
		return Delivery.getKeyByName(line) != -1;
	}
	
	
	private static boolean validatePayment(String line){
		
		if(line == null || line.isEmpty() || "".equals(line)){
			return false;
		}
		
		return Payment.getKeyByName(line) != -1;
	}
	
	private static boolean validateMap(Map<ProductBean, Integer> map){
		
		return !(map == null || map.size() == 0);
	}
}
