package com.company.util;

import java.util.ArrayList;
import java.util.List;

/**
 * Helps to find static content.
 * 
 * @author Ivan_Tymchenko
 *
 */
public class FilterUtil {
	
    @SuppressWarnings("serial")
	private static List<String> staticTypes = new ArrayList<String>() {{	
            add("js");	
            add("css");		
            add("jpg");		
            add("png");
        }};	
	
    public static boolean isStaticElement(String url){	
    	
        for(String staticType : staticTypes){	
            if(url.endsWith(staticType)){	
                return true;	
            }	
        }	
        return false;	
    }
}
