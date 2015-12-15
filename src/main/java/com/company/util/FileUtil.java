package com.company.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Helps to work with files.
 * 
 * @author Ivan_Tymchenko
 */
public class FileUtil {
	
    public static void copyStream(InputStream input, OutputStream output) throws IOException {	
        byte[] buffer = new byte[1024 * 4];	
        int n;	
        while ((n = input.read(buffer)) != -1) {	
            output.write(buffer, 0, n);	
        }	
        output.flush();	
    }
}
