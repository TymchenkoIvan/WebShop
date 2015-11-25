package com.epam.preprod.tymchenko.web.filter.gzip;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPOutputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.epam.preprod.tymchenko.exception.FilterException;
import com.epam.preprod.tymchenko.exception.Messages;
import com.epam.preprod.tymchenko.util.ClassNameUtil;

public class GZipResponseStream extends ServletOutputStream {
	
	private static final Logger LOG = Logger.getLogger(ClassNameUtil.getCurrentClassName());
	
	private static final String CONTEXT_LENGHT = "Content-Length";
	private static final String CONTEXT_ENCODING = "Content-Encoding";
	private static final String GZIP = "gzip";
	
	protected ByteArrayOutputStream baos = null;
	protected GZIPOutputStream gzipOutputStream = null;
	protected boolean closed = false;
	protected HttpServletResponse response = null;
	protected ServletOutputStream output = null;

	public GZipResponseStream(HttpServletResponse response) throws IOException {
		super();
		
		closed = false;
		
		this.response = response;
		this.output = response.getOutputStream();
		
		baos = new ByteArrayOutputStream();
		gzipOutputStream = new GZIPOutputStream(baos);

		LOG.trace("GZIPResponseStream(HttpServletResponse response)");
	}

	public void close() throws IOException {
		LOG.trace("GZIPResponseStream.close()");
		
		if (closed) {
			throw new FilterException(Messages.STREAM_WAS_CLOSED);
		}
		gzipOutputStream.finish();

		byte[] bytes = baos.toByteArray();

		response.addHeader(CONTEXT_LENGHT, Integer.toString(bytes.length));
		response.addHeader(CONTEXT_ENCODING, GZIP);
		
		output.write(bytes);
		output.flush();
		output.close();
		closed = true;
	}

	public void flush() throws IOException {
		LOG.trace("GZIPResponseStream.flush()");
		
		if (closed) {
			throw new FilterException(Messages.STREAM_WAS_CLOSED);
		}
		gzipOutputStream.flush();
	}

	public void write(int b) throws IOException {
		LOG.trace("GZIPResponseStream.write(int b)");
		
		if (closed) {
			throw new FilterException(Messages.STREAM_WAS_CLOSED);
		}
		gzipOutputStream.write((byte) b);
	}

	public void write(byte b[]) throws IOException {
		LOG.trace("GZIPResponseStream.write(byte b[]))");
		
		write(b, 0, b.length);
	}

	public void write(byte b[], int off, int len) throws IOException {
		LOG.trace("GZIPResponseStream.write(byte b[], int off, int len)");
		
		if (closed) {
			throw new FilterException(Messages.STREAM_WAS_CLOSED);
		}
		gzipOutputStream.write(b, off, len);
	}

	public boolean closed() {
		LOG.trace("GZIPResponseStream.closed()");
		
		return (this.closed);
	}

	public void reset() {
		LOG.trace("GZIPResponseStream.reset()");
	}
}
