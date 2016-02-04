package com.znsx.test.string;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.SortedMap;

import net.sf.json.JSONObject;


public class StringTest {
	public static void main(String[] args) throws Exception {
//		System.out.println(UUID.randomUUID().toString());
//		String json = "{'name':'张三','books':[101,102]}";
//		System.out.println(JSONObject.fromObject(json).toString());
		
//		String ids = "122,21233,222,";
//		String[] array = ids.split(",");
//		System.out.println(array.length);
//		
//		String name = null;
////		System.out.println(URLEncoder.encode(name, "utf8"));
//		System.out.println(URLDecoder.decode(name, "utf8"));
		
		String name = "隧道编辑_画板 2.jpg";
//		System.out.println(new String(name.getBytes("utf8")));
//		System.out.println(new String(name.getBytes("gbk")));
		
		// 没用，已经转成?字符，无法转换回来
//		System.out.println(new String(iso.getBytes("iso-8859-1"), "utf8"));
		SortedMap<String, Charset> map = Charset.availableCharsets();
		for (String key : map.keySet()) {
			try {
				System.out.println(new String(name.getBytes(map.get(key))) + "-----" + key);
			} catch (Exception e) {
				continue;
			}
		}
		
	}
}
