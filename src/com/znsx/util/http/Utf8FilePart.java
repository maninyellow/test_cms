package com.znsx.util.http;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.util.EncodingUtil;

/**
 * Utf8FilePart
 * 
 * @author MIKE
 *         <p />
 *         Create at 2016年1月26日 下午5:06:52
 */
public class Utf8FilePart extends FilePart {

	public Utf8FilePart(String filename, File file)
			throws FileNotFoundException {
		super(filename, file);
	}

	protected void sendDispositionHeader(OutputStream out) throws IOException {
		super.sendDispositionHeader(out);
		String filename = getSource().getFileName();
		if (filename != null) {
			out.write(EncodingUtil.getAsciiBytes(FILE_NAME));
			out.write(QUOTE_BYTES);
			out.write(EncodingUtil.getBytes(filename, "utf-8"));
			out.write(QUOTE_BYTES);
		}
	}
}
