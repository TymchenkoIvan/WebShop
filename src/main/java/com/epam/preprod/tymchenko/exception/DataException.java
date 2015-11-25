package com.epam.preprod.tymchenko.exception;

/**
 * Exception for services and db.
 * 
 * @author Ivan_Tymchenko
 *
 */
@SuppressWarnings("serial")
public class DataException extends Exception{
	
	public DataException(){
		super();
	}
	
	public DataException(String message){
		super(message);
	}
}