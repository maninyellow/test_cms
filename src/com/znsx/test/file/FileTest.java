package com.znsx.test.file;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.stream.FileImageInputStream;

public class FileTest {

	public static void main(String[] args) {
		FileImageInputStream in = null;
		try {
			File file = new File("E:/agentTest/BeforeModel.java");
			long length = file.length();
			in = new FileImageInputStream(file);
			byte[] bytes = new byte[(int) length];
			int off = 0;
			int len = 1024;
			int count = 0;
			while (off < length) {
				count = in.read(bytes, off, (int)length);
				System.out.println("read " + count + " bytes !");
				if (count > 0) {
					off += count;
				} else {
					break;
				}
			}
			if (off < length) {
				throw new IOException("Could not completely read file "
						+ file.getName());
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (null != in) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

}
