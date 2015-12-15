package com.company.web.filter.gzip;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

import org.apache.log4j.Logger;

import com.company.exception.FilterException;
import com.company.exception.Messages;
import com.company.tymchenko.util.ClassNameUtil;

public class GZipResponseWrapper extends HttpServletResponseWrapper {
	
	private static final Logger LOG = Logger.getLogger(ClassNameUtil.getCurrentClassName());
	
	private static final String ENCODING = "UTF-8";
	
	protected HttpServletResponse response = null;
	protected ServletOutputStream outputStream = null;
	protected PrintWriter printWriter = null;

	public GZipResponseWrapper(HttpServletResponse response) {
		super(response);
		this.response = response;
		
		LOG.trace("GZIPResponseWrapper(HttpServletResponse response)");
	}

	public ServletOutputStream createOutputStream() throws IOException {
		LOG.trace("GZIPResponseWrapper.createOutputStream()");
		
		return (new GZipResponseStream(response));
	}

	public void finishResponse() {
		LOG.trace("GZIPResponseWrapper.finishResponse()");
		
		try {
			if (printWriter != null) {
				printWriter.close();
			} else {
				if (outputStream != null) {
					outputStream.close();
				}
			}
		} catch (IOException e) {
		}
	}

	public void flushBuffer() throws IOException {
		LOG.trace("GZIPResponseWrapper.flushBuffer()");
		
		outputStream.flush();
	}

	public ServletOutputStream getOutputStream() throws IOException {
		LOG.trace("GZIPResponseWrapper.getOutputStream()");
		
		if (printWriter != null) {
			throw new FilterException(Messages.WRITER_HAS_BEEN_CALLED);
		}

		if (outputStream == null)
			outputStream = createOutputStream();
		
		return (outputStream);
	}

	public PrintWriter getWriter() throws IOException {
		LOG.trace("GZIPResponseWrapper.getWriter()");
		
		if (printWriter != null) {
			return (printWriter);
		}

		if (outputStream != null) {
			throw new FilterException(Messages.OUTPUT_STREAM_HAS_BEEN_CALLED);
		}

		outputStream = createOutputStream();
		printWriter = new PrintWriter(new OutputStreamWriter(outputStream, ENCODING));
		
		return (printWriter);
	}

	public void setContentLength(int length) {
		LOG.trace("GZIPResponseWrapper.setContentLength(int length)");
	}
}