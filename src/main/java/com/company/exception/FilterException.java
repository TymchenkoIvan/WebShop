package com.company.exception;

/**
 * Exception for application filters.
 * 
 * @author Ivan_Tymchenko
 *
 */
@SuppressWarnings("serial")
public class FilterException extends RuntimeException{
	
	public FilterException(){
		super();
	}
	
	public FilterException(String message){
		super(message);
	}
}