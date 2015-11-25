package com.epam.preprod.tymchenko.web.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.preprod.tymchenko.constant.AppScopeConstants;
import com.epam.preprod.tymchenko.constant.Constants;
import com.epam.preprod.tymchenko.service.captcha.Captcha;
import com.epam.preprod.tymchenko.service.captcha.CaptchaContainer;
import com.epam.preprod.tymchenko.service.captcha.captchaSaver.Saver;
import com.epam.preprod.tymchenko.util.ImageGenerator;
import com.sun.image.codec.jpeg.JPEGCodec;

/**
 * HttpServlet creates and returns captcha.
 * 
 * @author Ivan_Tymchenko
 */
public class CaptchaCreator extends HttpServlet {
	
    private static final long serialVersionUID = -1761346889117186607L;
    
    private Saver captchaSaver;
    
    public void init(ServletConfig servletConfig) throws ServletException {
    	captchaSaver = (Saver) servletConfig.getServletContext().getAttribute(AppScopeConstants.CONTEXT_CAPTCHA_SAVER);
	}
 
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	Captcha captcha = new Captcha();
        captchaSaver.saveCaptcha(request, response, captcha);
        
    	int captchaCode = Captcha.generateCode();
    	
    	response.setContentType(Constants.CONTENT_TYPE_IMAGE);
        ServletOutputStream out = response.getOutputStream();
        
        CaptchaContainer.add(captcha.getId(), captchaCode);
        BufferedImage image = ImageGenerator.createImage(captchaCode);
        
        JPEGCodec.createJPEGEncoder(out).encode(image);
        out.close();
    }
}