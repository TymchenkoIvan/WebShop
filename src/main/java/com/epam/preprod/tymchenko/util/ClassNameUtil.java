package com.epam.preprod.tymchenko.util;

/**
 * Helps to get current ClassName of every class.
 * 
 * @author tymchenkoivan
 *
 */
public final class ClassNameUtil {
	
	public static String getCurrentClassName(){
		try{
			throw new Exception();
		} catch(Exception ex){
			return ex.getStackTrace()[1].getClassName();
		}
	}
}