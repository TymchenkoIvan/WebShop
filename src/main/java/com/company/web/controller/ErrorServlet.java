package com.company.web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.company.tymchenko.util.ClassNameUtil;
import com.company.tymchenko.util.ViewPath;

@SuppressWarnings("serial")
public class ErrorServlet extends HttpServlet {

	private static final Logger LOG = Logger.getLogger(ClassNameUtil.getCurrentClassName());

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		LOG.trace("doGet()");

		request.getRequestDispatcher(ViewPath.ERROR_PAGE).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		LOG.trace("doPost()");

		request.getRequestDispatcher(ViewPath.ERROR_PAGE).forward(request, response);
	}
}
