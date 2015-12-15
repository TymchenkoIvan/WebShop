package com.company.util.count;

import org.apache.log4j.Logger;

import com.company.tymchenko.util.ClassNameUtil;

/**
 * Changes select query to select count. 
 * 
 * @author Ivan_Tymchenko
 *
 */
public class CountQueryGenerator {

	private static final Logger LOG = Logger.getLogger(ClassNameUtil.getCurrentClassName());
	
	private static final String SELECT_COMMAND = "*";
	private static final String COUNT_COMMAND = "COUNT(*)";
	
	public static String generate(String selectQuery){
		String res = refactor(selectQuery);
		
		LOG.info("Count query: "+res);
		
		return res;
	}
	
	private static String refactor(String selectQuery){
		String res;
		
		res = selectQuery.replace(SELECT_COMMAND, COUNT_COMMAND);
		
		if(res.contains("limit") || res.contains("LIMIT")){
			res = res.substring(0, res.indexOf("LIMIT", 1)) + ";";
		}
		
		return res;
	}
}