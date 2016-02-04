package com.znsx.test.license;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.commons.net.util.Base64;

import com.znsx.cms.service.exception.BusinessException;
import com.znsx.cms.service.exception.ErrorCode;

/**
 * License制作简要工具，运行后生成D:/znsx.license
 * 
 * @author huangbuji
 *         <p />
 *         Create at 2013 下午8:13:05
 */
public class LicenseTest {
	public static void main(String[] args) throws Exception {
		byte[] publicBinKey = getPublicBinKey();
		
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

		File privateFile = new File(path + "/private.ky");
		fr = new FileReader(privateFile);
		br = new BufferedReader(fr);
		sb = new StringBuffer();
		temp = null;
		while ((temp = br.readLine()) != null) {
			sb.append(temp);
		}
		br.close();
		fr.close();
		String privateKeyString = sb.toString();

		License license = new License();
		// 192.168.1.2
//		 license.setCpuidList("BFEBFBFF000306A9,D7 06 02 00 FF FB EB BF,BFEBFBFF000306A9,BFEBFBFF000206A7");
//		 license.setMacList("B8-CA-3A-7C-08-19,90:B1:1C:37:A4:25");
//		 license.setMotherBoardList("..CN7220033F015B.,..CN1374031H00BW.,..CN7220033F014R.,..CN7220033F00JK.,1ZP9Z29965M,..CN1374031H0069.");
		// 192.168.1.5
//		 license.setCpuidList("D7 06 02 00 FF FB EB BF");
//		 license.setMacList("90:B1:1C:37:A4:25");
//		 license.setMotherBoardList("..CN1374031H0069.");
		// 192.168.1.7
//		license.setCpuidList("D7 06 02 00 FF FB EB BF");
//		license.setMacList("90:B1:1C:37:A0:94");
//		license.setMotherBoardList("..CN1374031H003V.");
		 // 192.168.1.6
//		license.setCpuidList("BFEBFBFF000206D7");
//		license.setMacList("");
//		license.setMotherBoardList("..CN1374031H006D.");
		// 湘潭
//		license.setCpuidList("BFEBFBFF000206D7");
//		license.setMacList("");
//		license.setMotherBoardList("36T1F3");
		// 郴州,耒宜分中心,耒宜高速管理站
//		license.setCpuidList("178BFBFF00600F12");
//		license.setMacList("");
//		license.setMotherBoardList("");
		// 郴州，耒宜分中心，耒宜高速管理站2
//		license.setCpuidList("BFEBFBFF000206D7");
//		license.setMacList("");
//		license.setMotherBoardList("");
		// 内部一天
		license.setCpuidList("BFEBFBFF000306A9");
		license.setMacList("");
		license.setMotherBoardList("..CN7220033F015B.");
		
		license.setExpireTime("2020-10-01");
		license.setCameraAmount("5000");
		// 老版本的License制作，修改License类把deviceAmount改为sessionAmount，还需要修改toString()方法
//		license.setSessionAmount("100");
		license.setDeviceAmount("1000");
		license.setUserAmount("9999");
		System.out.println(license.toString());
		String signature = LicenceUtil.sign(license.toString(),
				privateKeyString);
		
//		FileOutputStream fo = new FileOutputStream("D:/sig");
//		byte[] sig = new Base64().decode(signature.getBytes("utf8"));
//		fo.write(sig);
		
		System.out.println("signature: " + signature);
		// 保存到文件中
		File licenseFile = new File("D:/znsx.license");
		FileWriter fw = new FileWriter(licenseFile);
		
		String out = "";
		// 老版本的License制作注释掉下面这行
		out += "project_name: 内部测试\r\nlink_man: 张某某\r\ncontact: 13888888888\r\n";
		out += license.toString() + "signature: " + signature;
		fw.write(out);
		fw.flush();
		fw.close();

//		System.out.println(LicenceUtil.verify(license.toString(),
//				publicKeyString, signature));
		System.out.println(LicenceUtil.verifyBinKey(license.toString(),
				publicBinKey, signature));
		
		license.setCameraAmount("50000");
//		System.out.println(LicenceUtil.verify(license.toString(),
//				publicKeyString, signature));
		System.out.println(LicenceUtil.verifyBinKey(license.toString(),
				publicBinKey, signature));
	}
	
	/**
	 * 获取数字认证的公钥,二进制方式
	 * 
	 * @return 数字认证的公钥
	 * @throws BusinessException
	 * @author huangbuji
	 *         <p />
	 *         Create at 2014-2-12 下午5:42:36
	 */
	private static byte[] getPublicBinKey() throws BusinessException {
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
}
