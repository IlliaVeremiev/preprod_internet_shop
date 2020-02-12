package com.epam.preprod.veremiev.task11.core.filters.utils;

import java.io.IOException;
import java.io.OutputStream;
import java.util.zip.GZIPOutputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;

/**
 * {@link ServletOutputStream} implementation uses {@link GZIPOutputStream}
 * 
 * @author Illia_Veremiev
 *
 */
class GzipServletOutputStream extends ServletOutputStream {

	private GZIPOutputStream gzipOutputStream;

	public GzipServletOutputStream(OutputStream output) throws IOException {
		super();
		gzipOutputStream = new GZIPOutputStream(output);
	}

	@Override
	public void close() throws IOException {
		gzipOutputStream.close();
	}

	@Override
	public void flush() throws IOException {
		gzipOutputStream.flush();
	}

	@Override
	public void write(byte b[]) throws IOException {
		gzipOutputStream.write(b);
	}

	@Override
	public void write(byte b[], int off, int len) throws IOException {
		gzipOutputStream.write(b, off, len);
	}

	@Override
	public void write(int b) throws IOException {
		gzipOutputStream.write(b);
	}

	@Override
	public boolean isReady() {
		return false;
	}

	@Override
	public void setWriteListener(WriteListener listener) {
	}
}
