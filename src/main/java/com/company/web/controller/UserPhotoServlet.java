package com.company.web.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.company.constant.Constants;
import com.company.tymchenko.util.FileUtil;

/**
 * Servlet that handles photo receiving.
 * 
 * @author Ivan_Tymchenko
 *
 */
@SuppressWarnings("serial")
public class UserPhotoServlet extends HttpServlet {
	
    @Override	
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String userLogin = request.getParameter(Constants.FORM_LOGIN);
        File picture = new File(Constants.IMAGE_DIR + File.separator + userLogin + File.separator + Constants.AVATAR_FILE);
        System.out.println(picture.getCanonicalPath());
        if (!picture.isFile()) {
        	picture = new File(Constants.IMAGE_DIR + File.separator + Constants.AVATAR_DEFAULT_FILE);
        }
        System.out.println(picture.getCanonicalPath());
        sendPicture(picture, response);	
    }	

    private void sendPicture(File picture, HttpServletResponse response) {	
        try {	
            ServletOutputStream output = response.getOutputStream();	
            FileInputStream input = new FileInputStream(picture);	
            FileUtil.copyStream(input, output);
        } catch (IOException e) {
        	e.printStackTrace();
        }	
    }

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}