package com.znsx.test.encode;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLDecoder;
import java.net.URLEncoder;

import org.apache.commons.net.util.Base64;

import com.znsx.cms.service.exception.BusinessException;
import com.znsx.cms.service.exception.ErrorCode;
import com.znsx.cms.web.dto.BaseDTO;
import com.znsx.util.md5.MD5Util;


/**
 * Base64Test
 * 
 * @author huangbuji
 *         <p />
 *         Create at 2013 下午3:58:43
 */
public class Base64Test {
	public static void main(String[] args) throws Exception {
//		System.out.println(encode("交通厅测试平台"));
//		Base64 base64 = new Base64();
//		byte[] buffer = Base64.encodeBase64("123456".getBytes("utf8"));
//		System.out.println(new String(buffer, "utf8"));
//		
//		System.out.println(new String(Base64.encodeBase64("<A='sdf' />".getBytes("utf8")), "utf8"));
		
//		byte[] decode = base64.decode("MCwC".getBytes("utf8"));
		
//		System.out.println(new String(base64.decode("PEV4cGVuZD4NCiAgPFA9IjEyMyIgQWM9ImFiYyIgLz4NCjwvRXhwZW5kPg=="),"utf8"));
		
//		System.out.println("K2JFPOCwNqK6CS0v1qIRBungGnwTNISGcowd3gSqynTs9STEzanFwA==".length());
//		System.out.println(new String(decode, "utf8"));
//		System.out.println(new Date(1376966577028L));
//		System.out.println(new Date(1376966880024L));
		
//		long time = System.currentTimeMillis();
//		System.out.println(time);
//		long newTime = time << 12;
//		System.out.println(newTime);
//		
//		String s = "sdflskjdfieieidkkd:sldfjd/skjfkdefkdkfdkfkdsleikdkfkdd";
//		System.out.println(s.hashCode());
		
//		String s1 = 17+"";
//		String s2 = ""+17;
//		String s3 = "17";
//		String s4 = new String("17");
//		String s5 = new StringBuffer().append("17").toString();
//		String s6 = ""+17+"";
//		String s7 = "1" + "7";
//		
//		System.out.println(s1 == s2);
//		System.out.println(s1 == s3);
//		System.out.println(s1 == s4);
//		System.out.println(s1 == s5);
//		System.out.println(s1 == s6);
//		System.out.println(s1 == s7);
//		
//		Timestamp ts = new Timestamp(System.currentTimeMillis());
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		System.out.println(sdf.format(ts));
		
//		System.out.println(System.currentTimeMillis() - 7 * 24 * 3600 * 1000);
//		System.out.println(System.currentTimeMillis() + 7 * 24 * 3600 * 1000);
		
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
//		System.out.println(sdf.format(new Date(1389772320001l)));
//		
//		System.out.println(CacheUtil.getCache("check_session_task_lock", "sessionCache"));
		
//		try {
//			throw new BusinessException(ErrorCode.USER_ACCOUNT_EXIST, "just for test !!");
//		} catch (BusinessException e) {
//			System.err.println(e.toString());
//			System.err.println(e.getStackTrace()[0].toString());
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		
//		System.out.println("done");
		
//		String userName = "251000000090000100";
//		String realm = "251000000022000100";
//		String nonce = "d904d529";
//		String uri = "sip:251000000090000100@192.168.1.103:5060";
//		String passwd = "79e60b356b7520d94cc532ddc78fb69e";
//
//		// 计算密码
//		String ha1 = MD5Util.MD5(userName + ":" + realm + ":"
//				+ "e10adc3949ba59abbe56e057f20f883e");
//		String ha2 = MD5Util.MD5("REGISTER:" + uri);
//		String myPasswd = MD5Util.MD5(ha1 + ":" + nonce + ":" + ha2);
//
//		BaseDTO dto = new BaseDTO();
//		dto.setCmd("3023");
//		dto.setMethod("Authenticate");
//		// 鉴权失败
//		if (!myPasswd.equals(passwd)) {
//			dto.setCode(ErrorCode.PASSWORD_ERROR);
//			dto.setMessage("User[" + userName + "] authenticate failed ! ha1["
//					+ ha1 + "] ha2[" + ha2 + "] Response[" + passwd + "] mine["
//					+ myPasswd + "] !");
//			
//			System.out.println("sdfdsf");
//		} else {
//			System.out.println("User[" + userName + "] authenticate failed ! ha1["
//					+ ha1 + "] ha2[" + ha2 + "] Response[" + passwd + "] mine["
//					+ myPasswd + "] !");
//		}
		
//		String s = "%E6%92%AD%E6%94%BE%E8%A7%86%E9%A2%91%E3%80%90%E6%B9%98%E8%A5%BF%E5%88%86%E4%B8%AD%E5%BF%83-%E7%A0%94%E5%8F%91-%E6%9E%AA%E6%9C%BA_1%E3%80%91";
//		String s1 = URLDecoder.decode(s, "utf8");
//		System.out.println(s1);
		
		String tests = "中文信息";
		String tests1 = new String(tests.getBytes("utf8"), "iso-8859-1");
		String tests2 = new String(tests.getBytes("iso-8859-1"), "utf8");
		String tests3 = new String(tests.getBytes("utf8"), "utf8");
		String tests4 = new String(tests.getBytes("iso-8859-1"), "iso-8859-1");
		String tests5 = new String(URLEncoder.encode(tests).getBytes("iso-8859-1"), "utf8");
		System.out.println(tests);
	}
	public static String hexString="0123456789ABCDEF";
	
	public static String encode(String str)
	{
	 //根据默认编码获取字节数组
	 byte[] bytes=str.getBytes();
	 StringBuilder sb=new StringBuilder(bytes.length*2);
	 //将字节数组中每个字节拆解成2位16进制整数
	  for(int i=0;i<bytes.length;i++)
	  {
	  sb.append(hexString.charAt((bytes[i]&0xf0)>>4));
	  sb.append(hexString.charAt((bytes[i]&0x0f)>>0));
	  }
	  return sb.toString();
	}
}
