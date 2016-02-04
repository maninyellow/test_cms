package com.znsx.test.license;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.znsx.cms.service.exception.BusinessException;
import com.znsx.cms.service.exception.ErrorCode;

/**
 * FileLicenseTest
 * 
 * @author huangbuji
 *         <p />
 *         Create at 2014-2-13 上午11:00:12
 */
public class FileLicenseTest {

	/**
	 * 获取数字认证的公钥,二进制方式
	 * 
	 * @return 数字认证的公钥
	 * @throws BusinessException
	 * @author huangbuji
	 *         <p />
	 *         Create at 2014-2-12 下午5:42:36
	 */
	public static byte[] getPublicBinKey() throws BusinessException {
		try {
			String path = LicenceUtil.class.getResource("").getPath();
			File publicFile = new File(path + "/binPublic.ky");
			FileInputStream fi = new FileInputStream(publicFile);
			byte[] publicKey = new byte[fi.available()];
			fi.read(publicKey);
			fi.close();
			return publicKey;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new BusinessException(ErrorCode.ERROR,
					"binPublic.ky not found !");
		} catch (IOException e) {
			e.printStackTrace();
			throw new BusinessException(ErrorCode.ERROR,
					"binPublic.ky read IOException !");
		}
	}

	private static String getPath() {
		return Main.class.getResource("").getPath();
	}

	/**
	 * main方法说明
	 * 
	 * @param args
	 * @author huangbuji
	 *         <p />
	 *         Create at 2014-2-13 上午11:00:12
	 */
	public static void main(String[] args) throws Exception {
		FileInputStream mis = new FileInputStream(getPath() + "/message.txt");
		byte[] message = new byte[mis.available()];
		mis.read(message);
		mis.close();
		String data = new String(message, "utf8");

		FileInputStream sis = new FileInputStream(getPath() + "/signature.txt");
		byte[] sig = new byte[sis.available()];
		sis.read(sig);
		sis.close();
		String signature = new String(sig, "utf8");

		boolean flag = LicenceUtil.verifyBinKey(data, getPublicBinKey(),
				signature);
		
		String path = LicenceUtil.class.getResource("").getPath();
		File publicFile = new File(path + "/public.ky");
		FileReader fr = new FileReader(publicFile);
		BufferedReader br = new BufferedReader(fr);
		StringBuffer sb = new StringBuffer();
		String temp = null;
		while ((temp = br.readLine()) != null) {
			sb.append(temp);
		}
		br.close();
		fr.close();
		String publicKeyString = sb.toString();
		
		boolean flag1 = LicenceUtil.verify(data, publicKeyString, signature);

		System.out.println("binKeyVerify : " + flag);
		System.out.println("StringKeyVerify : " + flag1);
	}

}
