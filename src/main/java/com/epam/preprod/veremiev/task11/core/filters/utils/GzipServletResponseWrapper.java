package com.epam.preprod.veremiev.task11.core.filters.utils;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

/**
 * Overrides HttpServletResponse output methods. Set on output stream Gzip
 * decorator
 * 
 * @author Illia_Veremiev
 *
 */
public class GzipServletResponseWrapper extends HttpServletResponseWrapper {

	private GzipServletOutputStream gzipOutputStream;

	private PrintWriter printWriter;

	public GzipServletResponseWrapper(HttpServletResponse response) {
		super(response);
		response.addHeader("Content-Encoding", "gzip");
	}

	public void close() throws IOException {
		if (this.printWriter != null) {
			this.printWriter.close();
		}
		if (this.gzipOutputStream != null) {
			this.gzipOutputStream.close();
		}
	}

	@Override
	public ServletOutputStream getOutputStream() throws IOException {
		if (printWriter != null) {
			throw new IllegalStateException();
		}
		if (gzipOutputStream == null) {
			gzipOutputStream = new GzipServletOutputStream(getResponse().getOutputStream());
		}
		return gzipOutputStream;
	}

	@Override
	public PrintWriter getWriter() throws IOException {
		if (gzipOutputStream != null) {
			throw new IllegalStateException();
		}
		if (printWriter == null) {
			printWriter = new PrintWriter(new OutputStreamWriter(getOutputStream()));
		}
		return printWriter;
	}
}
