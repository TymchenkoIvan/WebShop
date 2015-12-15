package com.company.exception;

/**
 * Exception for Validator.
 * 
 * @author Ivan_Tymchenko
 *
 */
@SuppressWarnings("serial")
public class ValidationException extends Exception{
	
	public ValidationException(){
		super();
	}
	
	public ValidationException(String message){
		super(message);
	}
}