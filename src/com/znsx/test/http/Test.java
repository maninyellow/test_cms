package com.znsx.test.http;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import org.jdom.Document;
import org.jdom.Element;

import com.znsx.util.database.CacheUtil;

/**
 * Test
 * 
 * @author wangbinyu
 *         <p />
 *         Create at 2014 下午2:27:25
 */
public class Test extends Thread {
	static int count = 0;
	Connection conn;
	private final static String region = "cache";
	private final static String region1 = "cache1";
	private static SimpleDateFormat sdf = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");

	public static final String REGION = "standardNumberCache";

	public Connection getConnection() throws Exception {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		String user = "cms";
		String password = "cms";
		String url = "jdbc:oracle:thin:@192.168.1.5:1521:znsx";
		conn = DriverManager.getConnection(url, user, password);

		return conn;
	}

	public static void main(String[] args) throws Exception {
		// for (int i = 0; i < 10; i++) {
		// System.out.println((int) (1 * 1));
		// }
		// new Test().getConnection();
		// System.out.println(sdf.format(new Date()));
		// Document doc = new Document();
		// Element root = new Element("Response");
		// root.setAttribute("abc", null);
		// for (int i = 0; i < 10; i++) {
		// Test test = new Test();
		// test.start();
		// }
		// List<String> list = new ArrayList<String>();
		// for (int i = 1; i < 10; i++) {
		// list.add("" + i);
		// }
		// CacheUtil.putCache("1", list, REGION);
		// List<String> list1 = (List<String>) CacheUtil.getCache("1", REGION);
		// for (int n = 0; n < list1.size(); n++) {
		// System.out.println(list1.get(n));
		// }
		// list.add("10");
		// CacheUtil.putCache("1", list, REGION);
		// List<String> list2 = (List<String>) CacheUtil.getCache("1", REGION);
		// System.out.println("-----------------------------------------");
		// for (int n = 0; n < list2.size(); n++) {
		// System.out.println(list2.get(n));
		// }

		// Map<String, String> map = new HashMap<String, String>();
		// map.put("1", "1");
		// map.put("2", "2");
		// map.put("3", "3");
		// Map<String, Integer> map1 = new HashMap<String, Integer>();
		// map1.put("1", 4);
		// map1.put("2", 5);
		// CacheUtil.putCache("1", map, region);
		// CacheUtil.putCache("1", map1, region1);
		// Map<String, String> mapsss = (Map<String, String>) CacheUtil
		// .getCache("1", region);
		// System.out.println(mapsss.get("1"));
		// System.out.println(map1.get("2"));
		// String s = "1";
		// String s1 = "2";
		// CacheUtil.putCache("1", s, region);
		// CacheUtil.putCache("1", s1, region);
		// String ss = (String) CacheUtil.getCache("2", region);
		// System.out.println(ss);
		// Set<String> sn = new HashSet<String>();
		// sn.add("1");
		// sn.add("2");
		// sn.add("1");
		// Iterator<String> it = sn.iterator();
		// while (it.hasNext()) {
		// System.out.println(it.next());
		// }
		// long time = 1427178502243l;
		// System.out.println(new Timestamp(time));
		// System.out.println(getXmlInfo());
		for (int i = 0; i < 10000; i++) {
			operateOnTime();
		}
		// List<String> list = new ArrayList<String>();
		// System.out.println(list.size());
		// int n = 0;
		// int m = 0;
		// System.out.println((float) n / m);
	}

	public void run() {
		for (int i = 1; i < 10; i++) {
			synchronized (REGION) {
				CacheUtil.putCache(i, i + "", REGION);
			}

		}
		for (int n = 1; n < 10; n++) {
			System.out.println(CacheUtil.getCache(n, REGION));
		}
	}

	public static void testPost(String urlStr) {
		try {
			URL url = new URL(urlStr);
			URLConnection con = url.openConnection();
			con.setDoOutput(true);
			con.setRequestProperty("Pragma:", "no-cache");
			con.setRequestProperty("Cache-Control", "no-cache");
			con.setRequestProperty("Content-Type", "text/xml");

			OutputStreamWriter out = new OutputStreamWriter(
					con.getOutputStream());
			String xmlInfo = getXmlInfo();
			System.out.println("urlStr=" + urlStr);
			// System.out.println("xmlInfo=" + xmlInfo);
			out.write(new String(xmlInfo.getBytes("UTF-8")));
			out.flush();
			out.close();
			BufferedReader br = new BufferedReader(new InputStreamReader(
					con.getInputStream()));
			String line = "";
			for (line = br.readLine(); line != null; line = br.readLine()) {
				System.out.println(line);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static String getXmlInfo() {
		StringBuilder sb = new StringBuilder();
		sb.append("<?xml version=\"1.0\"?>\n");
		sb.append("<Request Method=\"DAS_Report\" Cmd=\"3102\">\n");
		sb.append("<SessionId>10050000000000000000</SessionId>\n");

		// sb.append("<Device Type=\"12\" StandardNumber=\"2600002800001\" RecTime=\"");
		// // 气象检测器
		// sb.append(sdf.format(new Date()));
		// sb.append("\" VisAvg=\"");
		// sb.append((int) (1 * 1000 + 1000));
		// sb.append("\" WindSpeedAvg=\"");
		// sb.append((int) (1 * 10));
		// sb.append("\" AirTempAvg=\"");
		// sb.append((int) (1 * 10));
		// sb.append("\" RoadTempAvg=\"");
		// sb.append((int) (1 * 10));
		// sb.append("\" HumiAvg=\"");
		// sb.append((int) (1 * 10));
		// sb.append("\" Status=\"");
		// sb.append("0");
		// sb.append("\" CommStatus=\"");
		// sb.append("0");
		// sb.append("\"/>\n");

		// sb.append("<Device Type=\"16\" StandardNumber=\"25150002120\" RecTime=\"");
		// // NOD
		// sb.append(sdf.format(new Date()));
		// sb.append("\" NOConct=\"");
		// sb.append((int) (1 * 10));
		// sb.append("\" NO2Conct=\"");
		// sb.append((int) (1 * 10));
		// sb.append("\" Status=\"");
		// sb.append("0");
		// sb.append("\" CommStatus=\"");
		// sb.append("0");
		// sb.append("\"/>\n");

		// sb.append("<Device Type=\"21\" StandardNumber=\"250001212000\" RecTime=\"");
		// // 水泵
		// sb.append(sdf.format(new Date()));
		// sb.append("\" WorkState=\"");
		// sb.append("0");
		// sb.append("\" Status=\"");
		// sb.append("0");
		// sb.append("\" CommStatus=\"");
		// sb.append("0");
		// sb.append("\"/>\n");
		//
		// sb.append("<Device Type=\"22\" StandardNumber=\"251000212120\" RecTime=\"");
		// // 栏杆机
		// sb.append(sdf.format(new Date()));
		// sb.append("\" WorkState=\"");
		// sb.append("0");
		// sb.append("\" Status=\"");
		// sb.append("0");
		// sb.append("\" CommStatus=\"");
		// sb.append("0");
		// sb.append("\"/>\n");
		//
		// sb.append("<Device Type=\"23\" StandardNumber=\"2510002121201\" RecTime=\"");
		// // 电光诱导标志
		// sb.append(sdf.format(new Date()));
		// sb.append("\" WorkState=\"");
		// sb.append("0");
		// sb.append("\" Status=\"");
		// sb.append("0");
		// sb.append("\" CommStatus=\"");
		// sb.append("0");
		// sb.append("\"/>\n");

		// sb.append("<Device Type=\"24\" StandardNumber=\"251000212020\" RecTime=\"");
		// // 手动报警按钮
		// sb.append(sdf.format(new Date()));
		// sb.append("\" WorkState=\"");
		// sb.append("0");
		// sb.append("\" Status=\"");
		// sb.append("0");
		// sb.append("\" CommStatus=\"");
		// sb.append("0");
		// sb.append("\"/>\n");
		setLil(sb);
		setVD(sb);
		setWS(sb);
		setLoli(sb);
		setCovi(sb);
		setCms(sb);
		setFan(sb);
		setLight(sb);
		setWp(sb);
		setFd(sb);
		setPb(sb);
		setTsl(sb);
		sb.append("</Request>");
		return sb.toString();
	}

	private static void setWp(StringBuilder sb) {
		sb.append("<Device Type=\"21\" StandardNumber=\"241600021000000001\" RecTime=\"");
		// 水泵
		sb.append(sdf.format(new Date()));
		sb.append("\" WorkState=\"");
		sb.append("0");
		sb.append("\" Status=\"");
		sb.append("0");
		sb.append("\" CommStatus=\"");
		sb.append("0");
		sb.append("\"/>\n");

		sb.append("<Device Type=\"21\" StandardNumber=\"241600021000000002\" RecTime=\"");
		// 水泵
		sb.append(sdf.format(new Date()));
		sb.append("\" WorkState=\"");
		sb.append("1");
		sb.append("\" Status=\"");
		sb.append("0");
		sb.append("\" CommStatus=\"");
		sb.append("0");
		sb.append("\"/>\n");

	}

	private static void setPb(StringBuilder sb) {
		sb.append("<Device Type=\"24\" StandardNumber=\"251000000000000002\" RecTime=\""); // 火灾报警
		sb.append(sdf.format(new Date()));
		sb.append("\" WorkState=\"");
		sb.append("0");
		sb.append("\" Status=\"");
		sb.append("9");
		sb.append("\" CommStatus=\"");
		sb.append("0");
		sb.append("\" SubType=\"");
		sb.append("1");
		sb.append("\"/>\n");

		sb.append("<Device Type=\"24\" StandardNumber=\"251000000000000003\" RecTime=\""); // 火灾报警
		sb.append(sdf.format(new Date()));
		sb.append("\" WorkState=\"");
		sb.append("0");
		sb.append("\" Status=\"");
		sb.append("0");
		sb.append("\" CommStatus=\"");
		sb.append("0");
		sb.append("\" SubType=\"");
		sb.append("1");
		sb.append("\"/>\n");

		sb.append("<Device Type=\"24\" StandardNumber=\"251000001000000001\" RecTime=\""); // 火灾报警
		sb.append(sdf.format(new Date()));
		sb.append("\" WorkState=\"");
		sb.append("0");
		sb.append("\" Status=\"");
		sb.append("0");
		sb.append("\" CommStatus=\"");
		sb.append("0");
		sb.append("\" SubType=\"");
		sb.append("2");
		sb.append("\"/>\n");

		sb.append("<Device Type=\"24\" StandardNumber=\"251000001000000002\" RecTime=\""); // 火灾报警
		sb.append(sdf.format(new Date()));
		sb.append("\" WorkState=\"");
		sb.append("0");
		sb.append("\" Status=\"");
		sb.append("9");
		sb.append("\" CommStatus=\"");
		sb.append("0");
		sb.append("\" SubType=\"");
		sb.append("2");
		sb.append("\"/>\n");

		sb.append("<Device Type=\"24\" StandardNumber=\"251000001000000003\" RecTime=\""); // 火灾报警
		sb.append(sdf.format(new Date()));
		sb.append("\" WorkState=\"");
		sb.append("1");
		sb.append("\" Status=\"");
		sb.append("0");
		sb.append("\" CommStatus=\"");
		sb.append("0");
		sb.append("\" SubType=\"");
		sb.append("2");
		sb.append("\"/>\n");

		sb.append("<Device Type=\"24\" StandardNumber=\"251000001000000004\" RecTime=\""); // 火灾报警
		sb.append(sdf.format(new Date()));
		sb.append("\" WorkState=\"");
		sb.append("0");
		sb.append("\" Status=\"");
		sb.append("0");
		sb.append("\" CommStatus=\"");
		sb.append("0");
		sb.append("\" SubType=\"");
		sb.append("2");
		sb.append("\"/>\n");

		sb.append("<Device Type=\"24\" StandardNumber=\"251000000000000005\" RecTime=\""); // 火灾报警
		sb.append(sdf.format(new Date()));
		sb.append("\" WorkState=\"");
		sb.append("0");
		sb.append("\" Status=\"");
		sb.append("0");
		sb.append("\" CommStatus=\"");
		sb.append("0");
		sb.append("\" SubType=\"");
		sb.append("1");
		sb.append("\"/>\n");

		sb.append("<Device Type=\"24\" StandardNumber=\"251000000000000006\" RecTime=\""); // 火灾报警
		sb.append(sdf.format(new Date()));
		sb.append("\" WorkState=\"");
		sb.append("0");
		sb.append("\" Status=\"");
		sb.append("0");
		sb.append("\" CommStatus=\"");
		sb.append("0");
		sb.append("\" SubType=\"");
		sb.append("1");
		sb.append("\"/>\n");

		sb.append("<Device Type=\"24\" StandardNumber=\"251000001000000005\" RecTime=\""); // 火灾报警
		sb.append(sdf.format(new Date()));
		sb.append("\" WorkState=\"");
		sb.append("0");
		sb.append("\" Status=\"");
		sb.append("0");
		sb.append("\" CommStatus=\"");
		sb.append("0");
		sb.append("\" SubType=\"");
		sb.append("2");
		sb.append("\"/>\n");

		sb.append("<Device Type=\"24\" StandardNumber=\"251000001000000006\" RecTime=\""); // 火灾报警
		sb.append(sdf.format(new Date()));
		sb.append("\" WorkState=\"");
		sb.append("0");
		sb.append("\" Status=\"");
		sb.append("9");
		sb.append("\" CommStatus=\"");
		sb.append("0");
		sb.append("\" SubType=\"");
		sb.append("2");
		sb.append("\"/>\n");

		sb.append("<Device Type=\"24\" StandardNumber=\"251000001000000007\" RecTime=\""); // 火灾报警
		sb.append(sdf.format(new Date()));
		sb.append("\" WorkState=\"");
		sb.append("0");
		sb.append("\" Status=\"");
		sb.append("0");
		sb.append("\" CommStatus=\"");
		sb.append("0");
		sb.append("\" SubType=\"");
		sb.append("2");
		sb.append("\"/>\n");

		sb.append("<Device Type=\"24\" StandardNumber=\"251000001000000008\" RecTime=\""); // 火灾报警
		sb.append(sdf.format(new Date()));
		sb.append("\" WorkState=\"");
		sb.append("0");
		sb.append("\" Status=\"");
		sb.append("0");
		sb.append("\" CommStatus=\"");
		sb.append("0");
		sb.append("\" SubType=\"");
		sb.append("2");
		sb.append("\"/>\n");

	}

	private static void setFd(StringBuilder sb) {
		sb.append("<Device Type=\"14\" StandardNumber=\"241600014000000001\" RecTime=\""); // 火灾报警
		sb.append(sdf.format(new Date()));
		sb.append("\" WorkState=\"");
		sb.append("9");
		sb.append("\" Status=\"");
		sb.append("0");
		sb.append("\" CommStatus=\"");
		sb.append("0");
		sb.append("\"/>\n");

		sb.append("<Device Type=\"14\" StandardNumber=\"241600014000000002\" RecTime=\""); // 火灾报警
		sb.append(sdf.format(new Date()));
		sb.append("\" WorkState=\"");
		sb.append("0");
		sb.append("\" Status=\"");
		sb.append("0");
		sb.append("\" CommStatus=\"");
		sb.append("0");
		sb.append("\"/>\n");

		sb.append("<Device Type=\"14\" StandardNumber=\"241600014000000003\" RecTime=\""); // 火灾报警
		sb.append(sdf.format(new Date()));
		sb.append("\" WorkState=\"");
		sb.append("9");
		sb.append("\" Status=\"");
		sb.append("0");
		sb.append("\" CommStatus=\"");
		sb.append("0");
		sb.append("\"/>\n");

		sb.append("<Device Type=\"14\" StandardNumber=\"241600014000000004\" RecTime=\""); // 火灾报警
		sb.append(sdf.format(new Date()));
		sb.append("\" WorkState=\"");
		sb.append("1");
		sb.append("\" Status=\"");
		sb.append("0");
		sb.append("\" CommStatus=\"");
		sb.append("0");
		sb.append("\"/>\n");

		sb.append("<Device Type=\"14\" StandardNumber=\"241600014000000005\" RecTime=\""); // 火灾报警
		sb.append(sdf.format(new Date()));
		sb.append("\" WorkState=\"");
		sb.append("9");
		sb.append("\" Status=\"");
		sb.append("0");
		sb.append("\" CommStatus=\"");
		sb.append("0");
		sb.append("\"/>\n");

		sb.append("<Device Type=\"14\" StandardNumber=\"241600014000000006\" RecTime=\""); // 火灾报警
		sb.append(sdf.format(new Date()));
		sb.append("\" WorkState=\"");
		sb.append("0");
		sb.append("\" Status=\"");
		sb.append("0");
		sb.append("\" CommStatus=\"");
		sb.append("0");
		sb.append("\"/>\n");

		sb.append("<Device Type=\"14\" StandardNumber=\"241600014000000007\" RecTime=\""); // 火灾报警
		sb.append(sdf.format(new Date()));
		sb.append("\" WorkState=\"");
		sb.append("0");
		sb.append("\" Status=\"");
		sb.append("0");
		sb.append("\" CommStatus=\"");
		sb.append("0");
		sb.append("\"/>\n");

		sb.append("<Device Type=\"14\" StandardNumber=\"241600014000000008\" RecTime=\""); // 火灾报警
		sb.append(sdf.format(new Date()));
		sb.append("\" WorkState=\"");
		sb.append("9");
		sb.append("\" Status=\"");
		sb.append("0");
		sb.append("\" CommStatus=\"");
		sb.append("0");
		sb.append("\"/>\n");
	}

	private static void setTsl(StringBuilder sb) {
		sb.append("<Device Type=\"25\" StandardNumber=\"241600025000000003\" RecTime=\""); // 交通信号灯
		sb.append(sdf.format(new Date()));
		sb.append("\" GreenStatus=\"");
		sb.append((int) (1 * 2));
		sb.append("\" RedStatus=\"");
		sb.append((int) (1 * 2));
		sb.append("\" YellowStatus=\"");
		sb.append((int) (1 * 2));
		sb.append("\" TurnStatus=\"");
		sb.append((int) (1 * 2));
		sb.append("\" Status=\"");
		sb.append("0");
		sb.append("\" CommStatus=\"");
		sb.append("0");
		sb.append("\"/>\n");

		sb.append("<Device Type=\"25\" StandardNumber=\"241600025000000004\" RecTime=\""); // 交通信号灯
		sb.append(sdf.format(new Date()));
		sb.append("\" GreenStatus=\"");
		sb.append((int) (1 * 2));
		sb.append("\" RedStatus=\"");
		sb.append((int) (1 * 2));
		sb.append("\" YellowStatus=\"");
		sb.append((int) (1 * 2));
		sb.append("\" TurnStatus=\"");
		sb.append((int) (1 * 2));
		sb.append("\" Status=\"");
		sb.append("0");
		sb.append("\" CommStatus=\"");
		sb.append("0");
		sb.append("\"/>\n");

		sb.append("<Device Type=\"25\" StandardNumber=\"241600025000000001\" RecTime=\""); // 交通信号灯
		sb.append(sdf.format(new Date()));
		sb.append("\" GreenStatus=\"");
		sb.append((int) (1 * 2));
		sb.append("\" RedStatus=\"");
		sb.append((int) (1 * 2));
		sb.append("\" YellowStatus=\"");
		sb.append((int) (1 * 2));
		sb.append("\" TurnStatus=\"");
		sb.append((int) (1 * 2));
		sb.append("\" Status=\"");
		sb.append("0");
		sb.append("\" CommStatus=\"");
		sb.append("0");
		sb.append("\"/>\n");

		sb.append("<Device Type=\"25\" StandardNumber=\"241600025000000002\" RecTime=\""); // 交通信号灯
		sb.append(sdf.format(new Date()));
		sb.append("\" GreenStatus=\"");
		sb.append((int) (1 * 2));
		sb.append("\" RedStatus=\"");
		sb.append((int) (1 * 2));
		sb.append("\" YellowStatus=\"");
		sb.append((int) (1 * 2));
		sb.append("\" TurnStatus=\"");
		sb.append((int) (1 * 2));
		sb.append("\" Status=\"");
		sb.append("0");
		sb.append("\" CommStatus=\"");
		sb.append("0");
		sb.append("\"/>\n");

	}

	private static void setCms(StringBuilder sb) {
		sb.append("<Device Type=\"17\" StandardNumber=\"241600017000000016\" RecTime=\""); // CMS
		sb.append(sdf.format(new Date()));
		sb.append("\" DispText=\"");
		sb.append("");
		sb.append("\" DispTime=\"");
		sb.append("6");
		sb.append("\" Color=\"");
		sb.append("6");
		sb.append("\" Size=\"");
		sb.append("6");
		sb.append("\" Status=\"");
		sb.append("0");
		sb.append("\" CommStatus=\"");
		sb.append("0");
		sb.append("\"/>\n");

		sb.append("<Device Type=\"17\" StandardNumber=\"241600017000000014\" RecTime=\""); // CMS
		sb.append(sdf.format(new Date()));
		sb.append("\" DispText=\"");
		sb.append("");
		sb.append("\" DispTime=\"");
		sb.append("6");
		sb.append("\" Color=\"");
		sb.append("6");
		sb.append("\" Size=\"");
		sb.append("6");
		sb.append("\" Status=\"");
		sb.append("0");
		sb.append("\" CommStatus=\"");
		sb.append("0");
		sb.append("\"/>\n");

		sb.append("<Device Type=\"17\" StandardNumber=\"241600017000000015\" RecTime=\""); // CMS
		sb.append(sdf.format(new Date()));
		sb.append("\" DispText=\"");
		sb.append("");
		sb.append("\" DispTime=\"");
		sb.append("6");
		sb.append("\" Color=\"");
		sb.append("6");
		sb.append("\" Size=\"");
		sb.append("6");
		sb.append("\" Status=\"");
		sb.append("0");
		sb.append("\" CommStatus=\"");
		sb.append("0");
		sb.append("\"/>\n");

		sb.append("<Device Type=\"17\" StandardNumber=\"241600017000000001\" RecTime=\""); // CMS
		sb.append(sdf.format(new Date()));
		sb.append("\" DispText=\"");
		sb.append("");
		sb.append("\" DispTime=\"");
		sb.append("6");
		sb.append("\" Color=\"");
		sb.append("6");
		sb.append("\" Size=\"");
		sb.append("6");
		sb.append("\" Status=\"");
		sb.append("0");
		sb.append("\" CommStatus=\"");
		sb.append("0");
		sb.append("\"/>\n");

		sb.append("<Device Type=\"17\" StandardNumber=\"241600017000000003\" RecTime=\""); // CMS
		sb.append(sdf.format(new Date()));
		sb.append("\" DispText=\"");
		sb.append("");
		sb.append("\" DispTime=\"");
		sb.append("6");
		sb.append("\" Color=\"");
		sb.append("6");
		sb.append("\" Size=\"");
		sb.append("6");
		sb.append("\" Status=\"");
		sb.append("0");
		sb.append("\" CommStatus=\"");
		sb.append("0");
		sb.append("\"/>\n");

		sb.append("<Device Type=\"17\" StandardNumber=\"241600017000000005\" RecTime=\""); // CMS
		sb.append(sdf.format(new Date()));
		sb.append("\" DispText=\"");
		sb.append("");
		sb.append("\" DispTime=\"");
		sb.append("6");
		sb.append("\" Color=\"");
		sb.append("6");
		sb.append("\" Size=\"");
		sb.append("6");
		sb.append("\" Status=\"");
		sb.append("0");
		sb.append("\" CommStatus=\"");
		sb.append("0");
		sb.append("\"/>\n");

		sb.append("<Device Type=\"17\" StandardNumber=\"241600017000000006\" RecTime=\""); // CMS
		sb.append(sdf.format(new Date()));
		sb.append("\" DispText=\"");
		sb.append("");
		sb.append("\" DispTime=\"");
		sb.append("6");
		sb.append("\" Color=\"");
		sb.append("6");
		sb.append("\" Size=\"");
		sb.append("6");
		sb.append("\" Status=\"");
		sb.append("0");
		sb.append("\" CommStatus=\"");
		sb.append("0");
		sb.append("\"/>\n");

		sb.append("<Device Type=\"17\" StandardNumber=\"241600017000000007\" RecTime=\""); // CMS
		sb.append(sdf.format(new Date()));
		sb.append("\" DispText=\"");
		sb.append("");
		sb.append("\" DispTime=\"");
		sb.append("6");
		sb.append("\" Color=\"");
		sb.append("6");
		sb.append("\" Size=\"");
		sb.append("6");
		sb.append("\" Status=\"");
		sb.append("0");
		sb.append("\" CommStatus=\"");
		sb.append("0");
		sb.append("\"/>\n");

		sb.append("<Device Type=\"17\" StandardNumber=\"241600017000000008\" RecTime=\""); // CMS
		sb.append(sdf.format(new Date()));
		sb.append("\" DispText=\"");
		sb.append("");
		sb.append("\" DispTime=\"");
		sb.append("6");
		sb.append("\" Color=\"");
		sb.append("6");
		sb.append("\" Size=\"");
		sb.append("6");
		sb.append("\" Status=\"");
		sb.append("0");
		sb.append("\" CommStatus=\"");
		sb.append("0");
		sb.append("\"/>\n");

		sb.append("<Device Type=\"17\" StandardNumber=\"241600017000000009\" RecTime=\""); // CMS
		sb.append(sdf.format(new Date()));
		sb.append("\" DispText=\"");
		sb.append("");
		sb.append("\" DispTime=\"");
		sb.append("6");
		sb.append("\" Color=\"");
		sb.append("6");
		sb.append("\" Size=\"");
		sb.append("6");
		sb.append("\" Status=\"");
		sb.append("0");
		sb.append("\" CommStatus=\"");
		sb.append("0");
		sb.append("\"/>\n");

		sb.append("<Device Type=\"17\" StandardNumber=\"241600017000000010\" RecTime=\""); // CMS
		sb.append(sdf.format(new Date()));
		sb.append("\" DispText=\"");
		sb.append("");
		sb.append("\" DispTime=\"");
		sb.append("6");
		sb.append("\" Color=\"");
		sb.append("6");
		sb.append("\" Size=\"");
		sb.append("6");
		sb.append("\" Status=\"");
		sb.append("0");
		sb.append("\" CommStatus=\"");
		sb.append("0");
		sb.append("\"/>\n");

		sb.append("<Device Type=\"17\" StandardNumber=\"241600017000000017\" RecTime=\""); // CMS
		sb.append(sdf.format(new Date()));
		sb.append("\" DispText=\"");
		sb.append("");
		sb.append("\" DispTime=\"");
		sb.append("6");
		sb.append("\" Color=\"");
		sb.append("6");
		sb.append("\" Size=\"");
		sb.append("6");
		sb.append("\" Status=\"");
		sb.append("0");
		sb.append("\" CommStatus=\"");
		sb.append("0");
		sb.append("\"/>\n");

	}

	private static void setLil(StringBuilder sb) {
		sb.append("<Device Type=\"26\" StandardNumber=\"241600026000000019\" RecTime=\""); // 车道指示器
		sb.append(sdf.format(new Date()));
		sb.append("\" BackArrowStatus=\"");
		sb.append((int) (1 * 2));
		sb.append("\" BackXStatus=\"");
		sb.append((int) (1 * 2));
		sb.append("\" FrontArrowStatus=\"");
		sb.append((int) (1 * 2));
		sb.append("\" FrontXStatus=\"");
		sb.append((int) (1 * 2));
		sb.append("\" Status=\"");
		sb.append("0");
		sb.append("\" CommStatus=\"");
		sb.append("0");
		sb.append("\"/>\n");

		sb.append("<Device Type=\"26\" StandardNumber=\"241600026000000020\" RecTime=\"");
		sb.append(sdf.format(new Date()));
		sb.append("\" BackArrowStatus=\"");
		sb.append((int) (1 * 2));
		sb.append("\" BackXStatus=\"");
		sb.append((int) (1 * 2));
		sb.append("\" FrontArrowStatus=\"");
		sb.append((int) (1 * 2));
		sb.append("\" FrontXStatus=\"");
		sb.append((int) (1 * 2));
		sb.append("\" Status=\"");
		sb.append("0");
		sb.append("\" CommStatus=\"");
		sb.append("0");
		sb.append("\"/>\n");

		sb.append("<Device Type=\"26\" StandardNumber=\"241600026000000021\" RecTime=\""); // 车道指示器
		sb.append(sdf.format(new Date()));
		sb.append("\" BackArrowStatus=\"");
		sb.append((int) (1 * 2));
		sb.append("\" BackXStatus=\"");
		sb.append((int) (1 * 2));
		sb.append("\" FrontArrowStatus=\"");
		sb.append((int) (1 * 2));
		sb.append("\" FrontXStatus=\"");
		sb.append((int) (1 * 2));
		sb.append("\" Status=\"");
		sb.append("0");
		sb.append("\" CommStatus=\"");
		sb.append("0");
		sb.append("\"/>\n");

		sb.append("<Device Type=\"26\" StandardNumber=\"241600026000000022\" RecTime=\"");
		sb.append(sdf.format(new Date()));
		sb.append("\" BackArrowStatus=\"");
		sb.append((int) (1 * 2));
		sb.append("\" BackXStatus=\"");
		sb.append((int) (1 * 2));
		sb.append("\" FrontArrowStatus=\"");
		sb.append((int) (1 * 2));
		sb.append("\" FrontXStatus=\"");
		sb.append((int) (1 * 2));
		sb.append("\" Status=\"");
		sb.append("0");
		sb.append("\" CommStatus=\"");
		sb.append("0");
		sb.append("\"/>\n");

		sb.append("<Device Type=\"26\" StandardNumber=\"241600026000000023\" RecTime=\""); // 车道指示器
		sb.append(sdf.format(new Date()));
		sb.append("\" BackArrowStatus=\"");
		sb.append((int) (1 * 2));
		sb.append("\" BackXStatus=\"");
		sb.append((int) (1 * 2));
		sb.append("\" FrontArrowStatus=\"");
		sb.append((int) (1 * 2));
		sb.append("\" FrontXStatus=\"");
		sb.append((int) (1 * 2));
		sb.append("\" Status=\"");
		sb.append("0");
		sb.append("\" CommStatus=\"");
		sb.append("0");
		sb.append("\"/>\n");

		sb.append("<Device Type=\"26\" StandardNumber=\"241600026000000024\" RecTime=\"");
		sb.append(sdf.format(new Date()));
		sb.append("\" BackArrowStatus=\"");
		sb.append((int) (1 * 2));
		sb.append("\" BackXStatus=\"");
		sb.append((int) (1 * 2));
		sb.append("\" FrontArrowStatus=\"");
		sb.append((int) (1 * 2));
		sb.append("\" FrontXStatus=\"");
		sb.append((int) (1 * 2));
		sb.append("\" Status=\"");
		sb.append("0");
		sb.append("\" CommStatus=\"");
		sb.append("0");
		sb.append("\"/>\n");

		sb.append("<Device Type=\"26\" StandardNumber=\"241600026000000001\" RecTime=\""); // 车道指示器
		sb.append(sdf.format(new Date()));
		sb.append("\" BackArrowStatus=\"");
		sb.append((int) (1 * 2));
		sb.append("\" BackXStatus=\"");
		sb.append((int) (1 * 2));
		sb.append("\" FrontArrowStatus=\"");
		sb.append((int) (1 * 2));
		sb.append("\" FrontXStatus=\"");
		sb.append((int) (1 * 2));
		sb.append("\" Status=\"");
		sb.append("0");
		sb.append("\" CommStatus=\"");
		sb.append("0");
		sb.append("\"/>\n");

		sb.append("<Device Type=\"26\" StandardNumber=\"241600026000000002\" RecTime=\"");
		sb.append(sdf.format(new Date()));
		sb.append("\" BackArrowStatus=\"");
		sb.append((int) (1 * 2));
		sb.append("\" BackXStatus=\"");
		sb.append((int) (1 * 2));
		sb.append("\" FrontArrowStatus=\"");
		sb.append((int) (1 * 2));
		sb.append("\" FrontXStatus=\"");
		sb.append((int) (1 * 2));
		sb.append("\" Status=\"");
		sb.append("0");
		sb.append("\" CommStatus=\"");
		sb.append("0");
		sb.append("\"/>\n");

		sb.append("<Device Type=\"26\" StandardNumber=\"241600026000000003\" RecTime=\""); // 车道指示器
		sb.append(sdf.format(new Date()));
		sb.append("\" BackArrowStatus=\"");
		sb.append((int) (1 * 2));
		sb.append("\" BackXStatus=\"");
		sb.append((int) (1 * 2));
		sb.append("\" FrontArrowStatus=\"");
		sb.append((int) (1 * 2));
		sb.append("\" FrontXStatus=\"");
		sb.append((int) (1 * 2));
		sb.append("\" Status=\"");
		sb.append("0");
		sb.append("\" CommStatus=\"");
		sb.append("0");
		sb.append("\"/>\n");

		sb.append("<Device Type=\"26\" StandardNumber=\"241600026000000004\" RecTime=\"");
		sb.append(sdf.format(new Date()));
		sb.append("\" BackArrowStatus=\"");
		sb.append((int) (1 * 2));
		sb.append("\" BackXStatus=\"");
		sb.append((int) (1 * 2));
		sb.append("\" FrontArrowStatus=\"");
		sb.append((int) (1 * 2));
		sb.append("\" FrontXStatus=\"");
		sb.append((int) (1 * 2));
		sb.append("\" Status=\"");
		sb.append("0");
		sb.append("\" CommStatus=\"");
		sb.append("0");
		sb.append("\"/>\n");

		sb.append("<Device Type=\"26\" StandardNumber=\"241600026000000005\" RecTime=\""); // 车道指示器
		sb.append(sdf.format(new Date()));
		sb.append("\" BackArrowStatus=\"");
		sb.append((int) (1 * 2));
		sb.append("\" BackXStatus=\"");
		sb.append((int) (1 * 2));
		sb.append("\" FrontArrowStatus=\"");
		sb.append((int) (1 * 2));
		sb.append("\" FrontXStatus=\"");
		sb.append((int) (1 * 2));
		sb.append("\" Status=\"");
		sb.append("0");
		sb.append("\" CommStatus=\"");
		sb.append("0");
		sb.append("\"/>\n");

		sb.append("<Device Type=\"26\" StandardNumber=\"241600026000000006\" RecTime=\"");
		sb.append(sdf.format(new Date()));
		sb.append("\" BackArrowStatus=\"");
		sb.append((int) (1 * 2));
		sb.append("\" BackXStatus=\"");
		sb.append((int) (1 * 2));
		sb.append("\" FrontArrowStatus=\"");
		sb.append((int) (1 * 2));
		sb.append("\" FrontXStatus=\"");
		sb.append((int) (1 * 2));
		sb.append("\" Status=\"");
		sb.append("0");
		sb.append("\" CommStatus=\"");
		sb.append("0");
		sb.append("\"/>\n");

		sb.append("<Device Type=\"26\" StandardNumber=\"241600026000000013\" RecTime=\""); // 车道指示器
		sb.append(sdf.format(new Date()));
		sb.append("\" BackArrowStatus=\"");
		sb.append((int) (1 * 2));
		sb.append("\" BackXStatus=\"");
		sb.append((int) (1 * 2));
		sb.append("\" FrontArrowStatus=\"");
		sb.append((int) (1 * 2));
		sb.append("\" FrontXStatus=\"");
		sb.append((int) (1 * 2));
		sb.append("\" Status=\"");
		sb.append("0");
		sb.append("\" CommStatus=\"");
		sb.append("0");
		sb.append("\"/>\n");

		sb.append("<Device Type=\"26\" StandardNumber=\"241600026000000014\" RecTime=\"");
		sb.append(sdf.format(new Date()));
		sb.append("\" BackArrowStatus=\"");
		sb.append("0");
		sb.append("\" BackXStatus=\"");
		sb.append((int) (1 * 2));
		sb.append("\" FrontArrowStatus=\"");
		sb.append((int) (1 * 2));
		sb.append("\" FrontXStatus=\"");
		sb.append((int) (1 * 2));
		sb.append("\" Status=\"");
		sb.append("0");
		sb.append("\" CommStatus=\"");
		sb.append("0");
		sb.append("\"/>\n");

		sb.append("<Device Type=\"26\" StandardNumber=\"241600026000000015\" RecTime=\""); // 车道指示器
		sb.append(sdf.format(new Date()));
		sb.append("\" BackArrowStatus=\"");
		sb.append((int) (1 * 2));
		sb.append("\" BackXStatus=\"");
		sb.append((int) (1 * 2));
		sb.append("\" FrontArrowStatus=\"");
		sb.append((int) (1 * 2));
		sb.append("\" FrontXStatus=\"");
		sb.append((int) (1 * 2));
		sb.append("\" Status=\"");
		sb.append("0");
		sb.append("\" CommStatus=\"");
		sb.append("0");
		sb.append("\"/>\n");

		sb.append("<Device Type=\"26\" StandardNumber=\"241600026000000016\" RecTime=\"");
		sb.append(sdf.format(new Date()));
		sb.append("\" BackArrowStatus=\"");
		sb.append((int) (1 * 2));
		sb.append("\" BackXStatus=\"");
		sb.append((int) (1 * 2));
		sb.append("\" FrontArrowStatus=\"");
		sb.append((int) (1 * 2));
		sb.append("\" FrontXStatus=\"");
		sb.append((int) (1 * 2));
		sb.append("\" Status=\"");
		sb.append("0");
		sb.append("\" CommStatus=\"");
		sb.append("0");
		sb.append("\"/>\n");

		sb.append("<Device Type=\"26\" StandardNumber=\"241600026000000017\" RecTime=\""); // 车道指示器
		sb.append(sdf.format(new Date()));
		sb.append("\" BackArrowStatus=\"");
		sb.append((int) (1 * 2));
		sb.append("\" BackXStatus=\"");
		sb.append((int) (1 * 2));
		sb.append("\" FrontArrowStatus=\"");
		sb.append((int) (1 * 2));
		sb.append("\" FrontXStatus=\"");
		sb.append((int) (1 * 2));
		sb.append("\" Status=\"");
		sb.append("0");
		sb.append("\" CommStatus=\"");
		sb.append("0");
		sb.append("\"/>\n");

		sb.append("<Device Type=\"26\" StandardNumber=\"241600026000000018\" RecTime=\"");
		sb.append(sdf.format(new Date()));
		sb.append("\" BackArrowStatus=\"");
		sb.append((int) (1 * 2));
		sb.append("\" BackXStatus=\"");
		sb.append((int) (1 * 2));
		sb.append("\" FrontArrowStatus=\"");
		sb.append((int) (1 * 2));
		sb.append("\" FrontXStatus=\"");
		sb.append((int) (1 * 2));
		sb.append("\" Status=\"");
		sb.append("0");
		sb.append("\" CommStatus=\"");
		sb.append("0");
		sb.append("\"/>\n");

		sb.append("<Device Type=\"26\" StandardNumber=\"241600026000000007\" RecTime=\""); // 车道指示器
		sb.append(sdf.format(new Date()));
		sb.append("\" BackArrowStatus=\"");
		sb.append((int) (1 * 2));
		sb.append("\" BackXStatus=\"");
		sb.append((int) (1 * 2));
		sb.append("\" FrontArrowStatus=\"");
		sb.append((int) (1 * 2));
		sb.append("\" FrontXStatus=\"");
		sb.append((int) (1 * 2));
		sb.append("\" Status=\"");
		sb.append("0");
		sb.append("\" CommStatus=\"");
		sb.append("0");
		sb.append("\"/>\n");

		sb.append("<Device Type=\"26\" StandardNumber=\"241600026000000008\" RecTime=\"");
		sb.append(sdf.format(new Date()));
		sb.append("\" BackArrowStatus=\"");
		sb.append((int) (1 * 2));
		sb.append("\" BackXStatus=\"");
		sb.append((int) (1 * 2));
		sb.append("\" FrontArrowStatus=\"");
		sb.append((int) (1 * 2));
		sb.append("\" FrontXStatus=\"");
		sb.append((int) (1 * 2));
		sb.append("\" Status=\"");
		sb.append("0");
		sb.append("\" CommStatus=\"");
		sb.append("0");
		sb.append("\"/>\n");

		sb.append("<Device Type=\"26\" StandardNumber=\"241600026000000009\" RecTime=\"");
		sb.append(sdf.format(new Date()));
		sb.append("\" BackArrowStatus=\"");
		sb.append((int) (1 * 2));
		sb.append("\" BackXStatus=\"");
		sb.append((int) (1 * 2));
		sb.append("\" FrontArrowStatus=\"");
		sb.append((int) (1 * 2));
		sb.append("\" FrontXStatus=\"");
		sb.append((int) (1 * 2));
		sb.append("\" Status=\"");
		sb.append("0");
		sb.append("\" CommStatus=\"");
		sb.append("0");
		sb.append("\"/>\n");

		sb.append("<Device Type=\"26\" StandardNumber=\"241600026000000010\" RecTime=\"");
		sb.append(sdf.format(new Date()));
		sb.append("\" BackArrowStatus=\"");
		sb.append((int) (1 * 2));
		sb.append("\" BackXStatus=\"");
		sb.append((int) (1 * 2));
		sb.append("\" FrontArrowStatus=\"");
		sb.append((int) (1 * 2));
		sb.append("\" FrontXStatus=\"");
		sb.append((int) (1 * 2));
		sb.append("\" Status=\"");
		sb.append("0");
		sb.append("\" CommStatus=\"");
		sb.append("0");
		sb.append("\"/>\n");

		sb.append("<Device Type=\"26\" StandardNumber=\"241600026000000011\" RecTime=\"");
		sb.append(sdf.format(new Date()));
		sb.append("\" BackArrowStatus=\"");
		sb.append((int) (1 * 2));
		sb.append("\" BackXStatus=\"");
		sb.append((int) (1 * 2));
		sb.append("\" FrontArrowStatus=\"");
		sb.append((int) (1 * 2));
		sb.append("\" FrontXStatus=\"");
		sb.append((int) (1 * 2));
		sb.append("\" Status=\"");
		sb.append("0");
		sb.append("\" CommStatus=\"");
		sb.append("0");
		sb.append("\"/>\n");

		sb.append("<Device Type=\"26\" StandardNumber=\"241600026000000012\" RecTime=\"");
		sb.append(sdf.format(new Date()));
		sb.append("\" BackArrowStatus=\"");
		sb.append((int) (1 * 2));
		sb.append("\" BackXStatus=\"");
		sb.append((int) (1 * 2));
		sb.append("\" FrontArrowStatus=\"");
		sb.append((int) (1 * 2));
		sb.append("\" FrontXStatus=\"");
		sb.append((int) (1 * 2));
		sb.append("\" Status=\"");
		sb.append("0");
		sb.append("\" CommStatus=\"");
		sb.append("0");
		sb.append("\"/>\n");
	}

	private static void setLight(StringBuilder sb) {
		sb.append("<Device Type=\"19\" StandardNumber=\"241600019000000023\" RecTime=\""); // 照明回路
		sb.append(sdf.format(new Date()));
		sb.append("\" WorkState=\"");
		sb.append("1");
		sb.append("\" Status=\"");
		sb.append("0");
		sb.append("\" CommStatus=\"");
		sb.append("0");
		sb.append("\"/>\n");

		sb.append("<Device Type=\"19\" StandardNumber=\"241600019000000024\" RecTime=\""); // 照明回路
		sb.append(sdf.format(new Date()));
		sb.append("\" WorkState=\"");
		sb.append("1");
		sb.append("\" Status=\"");
		sb.append("0");
		sb.append("\" CommStatus=\"");
		sb.append("0");
		sb.append("\"/>\n");

		sb.append("<Device Type=\"19\" StandardNumber=\"241600019000000025\" RecTime=\""); // 照明回路
		sb.append(sdf.format(new Date()));
		sb.append("\" WorkState=\"");
		sb.append("1");
		sb.append("\" Status=\"");
		sb.append("0");
		sb.append("\" CommStatus=\"");
		sb.append("0");
		sb.append("\"/>\n");

		sb.append("<Device Type=\"19\" StandardNumber=\"241600019000000026\" RecTime=\""); // 照明回路
		sb.append(sdf.format(new Date()));
		sb.append("\" WorkState=\"");
		sb.append("1");
		sb.append("\" Status=\"");
		sb.append("0");
		sb.append("\" CommStatus=\"");
		sb.append("0");
		sb.append("\"/>\n");

		sb.append("<Device Type=\"19\" StandardNumber=\"241600019000000027\" RecTime=\""); // 照明回路
		sb.append(sdf.format(new Date()));
		sb.append("\" WorkState=\"");
		sb.append("1");
		sb.append("\" Status=\"");
		sb.append("0");
		sb.append("\" CommStatus=\"");
		sb.append("0");
		sb.append("\"/>\n");

		sb.append("<Device Type=\"19\" StandardNumber=\"241600019000000028\" RecTime=\""); // 照明回路
		sb.append(sdf.format(new Date()));
		sb.append("\" WorkState=\"");
		sb.append("1");
		sb.append("\" Status=\"");
		sb.append("0");
		sb.append("\" CommStatus=\"");
		sb.append("0");
		sb.append("\"/>\n");

		sb.append("<Device Type=\"19\" StandardNumber=\"241600019000000029\" RecTime=\""); // 照明回路
		sb.append(sdf.format(new Date()));
		sb.append("\" WorkState=\"");
		sb.append("1");
		sb.append("\" Status=\"");
		sb.append("0");
		sb.append("\" CommStatus=\"");
		sb.append("0");
		sb.append("\"/>\n");

		sb.append("<Device Type=\"19\" StandardNumber=\"241600019000000030\" RecTime=\""); // 照明回路
		sb.append(sdf.format(new Date()));
		sb.append("\" WorkState=\"");
		sb.append("1");
		sb.append("\" Status=\"");
		sb.append("0");
		sb.append("\" CommStatus=\"");
		sb.append("0");
		sb.append("\"/>\n");

		sb.append("<Device Type=\"19\" StandardNumber=\"241600019000000031\" RecTime=\""); // 照明回路
		sb.append(sdf.format(new Date()));
		sb.append("\" WorkState=\"");
		sb.append("1");
		sb.append("\" Status=\"");
		sb.append("0");
		sb.append("\" CommStatus=\"");
		sb.append("0");
		sb.append("\"/>\n");

		sb.append("<Device Type=\"19\" StandardNumber=\"241600019000000032\" RecTime=\""); // 照明回路
		sb.append(sdf.format(new Date()));
		sb.append("\" WorkState=\"");
		sb.append("1");
		sb.append("\" Status=\"");
		sb.append("0");
		sb.append("\" CommStatus=\"");
		sb.append("0");
		sb.append("\"/>\n");

		sb.append("<Device Type=\"19\" StandardNumber=\"241600019000000033\" RecTime=\""); // 照明回路
		sb.append(sdf.format(new Date()));
		sb.append("\" WorkState=\"");
		sb.append("1");
		sb.append("\" Status=\"");
		sb.append("0");
		sb.append("\" CommStatus=\"");
		sb.append("0");
		sb.append("\"/>\n");

		sb.append("<Device Type=\"19\" StandardNumber=\"241600019000000034\" RecTime=\""); // 照明回路
		sb.append(sdf.format(new Date()));
		sb.append("\" WorkState=\"");
		sb.append("1");
		sb.append("\" Status=\"");
		sb.append("0");
		sb.append("\" CommStatus=\"");
		sb.append("0");
		sb.append("\"/>\n");

		sb.append("<Device Type=\"19\" StandardNumber=\"241600019000000035\" RecTime=\""); // 照明回路
		sb.append(sdf.format(new Date()));
		sb.append("\" WorkState=\"");
		sb.append("1");
		sb.append("\" Status=\"");
		sb.append("0");
		sb.append("\" CommStatus=\"");
		sb.append("0");
		sb.append("\"/>\n");

		sb.append("<Device Type=\"19\" StandardNumber=\"241600019000000036\" RecTime=\""); // 照明回路
		sb.append(sdf.format(new Date()));
		sb.append("\" WorkState=\"");
		sb.append("1");
		sb.append("\" Status=\"");
		sb.append("0");
		sb.append("\" CommStatus=\"");
		sb.append("0");
		sb.append("\"/>\n");

		sb.append("<Device Type=\"19\" StandardNumber=\"241600019000000037\" RecTime=\""); // 照明回路
		sb.append(sdf.format(new Date()));
		sb.append("\" WorkState=\"");
		sb.append("1");
		sb.append("\" Status=\"");
		sb.append("0");
		sb.append("\" CommStatus=\"");
		sb.append("0");
		sb.append("\"/>\n");

		sb.append("<Device Type=\"19\" StandardNumber=\"241600019000000038\" RecTime=\""); // 照明回路
		sb.append(sdf.format(new Date()));
		sb.append("\" WorkState=\"");
		sb.append("1");
		sb.append("\" Status=\"");
		sb.append("0");
		sb.append("\" CommStatus=\"");
		sb.append("0");
		sb.append("\"/>\n");

		sb.append("<Device Type=\"19\" StandardNumber=\"241600019000000001\" RecTime=\""); // 照明回路
		sb.append(sdf.format(new Date()));
		sb.append("\" WorkState=\"");
		sb.append("1");
		sb.append("\" Status=\"");
		sb.append("0");
		sb.append("\" CommStatus=\"");
		sb.append("0");
		sb.append("\"/>\n");

		sb.append("<Device Type=\"19\" StandardNumber=\"241600019000000002\" RecTime=\""); // 照明回路
		sb.append(sdf.format(new Date()));
		sb.append("\" WorkState=\"");
		sb.append("1");
		sb.append("\" Status=\"");
		sb.append("0");
		sb.append("\" CommStatus=\"");
		sb.append("0");
		sb.append("\"/>\n");

		sb.append("<Device Type=\"19\" StandardNumber=\"241600019000000003\" RecTime=\""); // 照明回路
		sb.append(sdf.format(new Date()));
		sb.append("\" WorkState=\"");
		sb.append("1");
		sb.append("\" Status=\"");
		sb.append("0");
		sb.append("\" CommStatus=\"");
		sb.append("0");
		sb.append("\"/>\n");

		sb.append("<Device Type=\"19\" StandardNumber=\"241600019000000004\" RecTime=\""); // 照明回路
		sb.append(sdf.format(new Date()));
		sb.append("\" WorkState=\"");
		sb.append("1");
		sb.append("\" Status=\"");
		sb.append("0");
		sb.append("\" CommStatus=\"");
		sb.append("0");
		sb.append("\"/>\n");

		sb.append("<Device Type=\"19\" StandardNumber=\"241600019000000005\" RecTime=\""); // 照明回路
		sb.append(sdf.format(new Date()));
		sb.append("\" WorkState=\"");
		sb.append("1");
		sb.append("\" Status=\"");
		sb.append("0");
		sb.append("\" CommStatus=\"");
		sb.append("0");
		sb.append("\"/>\n");

		sb.append("<Device Type=\"19\" StandardNumber=\"241600019000000006\" RecTime=\""); // 照明回路
		sb.append(sdf.format(new Date()));
		sb.append("\" WorkState=\"");
		sb.append("1");
		sb.append("\" Status=\"");
		sb.append("0");
		sb.append("\" CommStatus=\"");
		sb.append("0");
		sb.append("\"/>\n");

		sb.append("<Device Type=\"19\" StandardNumber=\"241600019000000007\" RecTime=\""); // 照明回路
		sb.append(sdf.format(new Date()));
		sb.append("\" WorkState=\"");
		sb.append("1");
		sb.append("\" Status=\"");
		sb.append("0");
		sb.append("\" CommStatus=\"");
		sb.append("0");
		sb.append("\"/>\n");

	}

	private static void setFan(StringBuilder sb) {
		sb.append("<Device Type=\"18\" StandardNumber=\"241600019000000008\" RecTime=\""); // FAN
		sb.append(sdf.format(new Date()));
		sb.append("\" WorkState=\"");
		sb.append("0");
		sb.append("\" Status=\"");
		sb.append("0");
		sb.append("\" CommStatus=\"");
		sb.append("0");
		sb.append("\"/>\n");

		sb.append("<Device Type=\"18\" StandardNumber=\"241600019000000009\" RecTime=\""); // FAN
		sb.append(sdf.format(new Date()));
		sb.append("\" WorkState=\"");
		sb.append("0");
		sb.append("\" Status=\"");
		sb.append("0");
		sb.append("\" CommStatus=\"");
		sb.append("0");
		sb.append("\"/>\n");

		sb.append("<Device Type=\"18\" StandardNumber=\"241600019000000010\" RecTime=\""); // FAN
		sb.append(sdf.format(new Date()));
		sb.append("\" WorkState=\"");
		sb.append("0");
		sb.append("\" Status=\"");
		sb.append("0");
		sb.append("\" CommStatus=\"");
		sb.append("0");
		sb.append("\"/>\n");

		sb.append("<Device Type=\"18\" StandardNumber=\"241600019000000011\" RecTime=\""); // FAN
		sb.append(sdf.format(new Date()));
		sb.append("\" WorkState=\"");
		sb.append("0");
		sb.append("\" Status=\"");
		sb.append("0");
		sb.append("\" CommStatus=\"");
		sb.append("0");
		sb.append("\"/>\n");

		sb.append("<Device Type=\"18\" StandardNumber=\"241600019000000012\" RecTime=\""); // FAN
		sb.append(sdf.format(new Date()));
		sb.append("\" WorkState=\"");
		sb.append("0");
		sb.append("\" Status=\"");
		sb.append("0");
		sb.append("\" CommStatus=\"");
		sb.append("0");
		sb.append("\"/>\n");

		sb.append("<Device Type=\"18\" StandardNumber=\"241600019000000013\" RecTime=\""); // FAN
		sb.append(sdf.format(new Date()));
		sb.append("\" WorkState=\"");
		sb.append("0");
		sb.append("\" Status=\"");
		sb.append("0");
		sb.append("\" CommStatus=\"");
		sb.append("0");
		sb.append("\"/>\n");

		sb.append("<Device Type=\"18\" StandardNumber=\"241600019000000014\" RecTime=\""); // FAN
		sb.append(sdf.format(new Date()));
		sb.append("\" WorkState=\"");
		sb.append("0");
		sb.append("\" Status=\"");
		sb.append("0");
		sb.append("\" CommStatus=\"");
		sb.append("0");
		sb.append("\"/>\n");

		sb.append("<Device Type=\"18\" StandardNumber=\"241600019000000015\" RecTime=\""); // FAN
		sb.append(sdf.format(new Date()));
		sb.append("\" WorkState=\"");
		sb.append("0");
		sb.append("\" Status=\"");
		sb.append("0");
		sb.append("\" CommStatus=\"");
		sb.append("0");
		sb.append("\"/>\n");

		sb.append("<Device Type=\"18\" StandardNumber=\"241600019000000016\" RecTime=\""); // FAN
		sb.append(sdf.format(new Date()));
		sb.append("\" WorkState=\"");
		sb.append("0");
		sb.append("\" Status=\"");
		sb.append("0");
		sb.append("\" CommStatus=\"");
		sb.append("0");
		sb.append("\"/>\n");

		sb.append("<Device Type=\"18\" StandardNumber=\"241600019000000017\" RecTime=\""); // FAN
		sb.append(sdf.format(new Date()));
		sb.append("\" WorkState=\"");
		sb.append("0");
		sb.append("\" Status=\"");
		sb.append("0");
		sb.append("\" CommStatus=\"");
		sb.append("0");
		sb.append("\"/>\n");

		sb.append("<Device Type=\"18\" StandardNumber=\"241600019000000018\" RecTime=\""); // FAN
		sb.append(sdf.format(new Date()));
		sb.append("\" WorkState=\"");
		sb.append("0");
		sb.append("\" Status=\"");
		sb.append("0");
		sb.append("\" CommStatus=\"");
		sb.append("0");
		sb.append("\"/>\n");

		sb.append("<Device Type=\"18\" StandardNumber=\"241600019000000019\" RecTime=\""); // FAN
		sb.append(sdf.format(new Date()));
		sb.append("\" WorkState=\"");
		sb.append("0");
		sb.append("\" Status=\"");
		sb.append("0");
		sb.append("\" CommStatus=\"");
		sb.append("0");
		sb.append("\"/>\n");

		sb.append("<Device Type=\"18\" StandardNumber=\"241600019000000020\" RecTime=\""); // FAN
		sb.append(sdf.format(new Date()));
		sb.append("\" WorkState=\"");
		sb.append("0");
		sb.append("\" Status=\"");
		sb.append("0");
		sb.append("\" CommStatus=\"");
		sb.append("0");
		sb.append("\"/>\n");

		sb.append("<Device Type=\"18\" StandardNumber=\"241600019000000021\" RecTime=\""); // FAN
		sb.append(sdf.format(new Date()));
		sb.append("\" WorkState=\"");
		sb.append("0");
		sb.append("\" Status=\"");
		sb.append("0");
		sb.append("\" CommStatus=\"");
		sb.append("0");
		sb.append("\"/>\n");

		sb.append("<Device Type=\"18\" StandardNumber=\"241600019000000022\" RecTime=\""); // FAN
		sb.append(sdf.format(new Date()));
		sb.append("\" WorkState=\"");
		sb.append("0");
		sb.append("\" Status=\"");
		sb.append("0");
		sb.append("\" CommStatus=\"");
		sb.append("0");
		sb.append("\"/>\n");
	}

	private static void setCovi(StringBuilder sb) {
		sb.append("<Device Type=\"15\" StandardNumber=\"241600015000000004\" RecTime=\""); // COVI
		sb.append(sdf.format(new Date()));
		sb.append("\" COConct=\"");
		sb.append((int) (1 * 10));
		sb.append("\" Visibility=\"");
		sb.append((int) (1 * 10));
		sb.append("\" Status=\"");
		sb.append("0");
		sb.append("\" CommStatus=\"");
		sb.append("0");
		sb.append("\"/>\n");

		sb.append("<Device Type=\"15\" StandardNumber=\"241600015000000003\" RecTime=\""); // COVI
		sb.append(sdf.format(new Date()));
		sb.append("\" COConct=\"");
		sb.append((int) (1 * 10));
		sb.append("\" Visibility=\"");
		sb.append((int) (1 * 10));
		sb.append("\" Status=\"");
		sb.append("0");
		sb.append("\" CommStatus=\"");
		sb.append("0");
		sb.append("\"/>\n");

		sb.append("<Device Type=\"15\" StandardNumber=\"241600015000000001\" RecTime=\""); // COVI
		sb.append(sdf.format(new Date()));
		sb.append("\" COConct=\"");
		sb.append((int) (1 * 10));
		sb.append("\" Visibility=\"");
		sb.append((int) (1 * 10));
		sb.append("\" Status=\"");
		sb.append("0");
		sb.append("\" CommStatus=\"");
		sb.append("0");
		sb.append("\"/>\n");

		sb.append("<Device Type=\"15\" StandardNumber=\"241600015000000002\" RecTime=\""); // COVI
		sb.append(sdf.format(new Date()));
		sb.append("\" COConct=\"");
		sb.append((int) (1 * 10));
		sb.append("\" Visibility=\"");
		sb.append((int) (1 * 10));
		sb.append("\" Status=\"");
		sb.append("0");
		sb.append("\" CommStatus=\"");
		sb.append("0");
		sb.append("\"/>\n");
	}

	private static void setLoli(StringBuilder sb) {
		sb.append("<Device Type=\"13\" StandardNumber=\"241600013000000001\" RecTime=\""); // 光强检测器
		sb.append(sdf.format(new Date()));
		sb.append("\" LOLumi=\"");
		sb.append((int) (1 * 500));
		sb.append("\" LILumi=\"");
		sb.append((int) (1 * 500));
		sb.append("\" Status=\"");
		sb.append("0");
		sb.append("\" CommStatus=\"");
		sb.append("0");
		sb.append("\"/>\n");

		sb.append("<Device Type=\"13\" StandardNumber=\"241600013000000003\" RecTime=\""); // 光强检测器
		sb.append(sdf.format(new Date()));
		sb.append("\" LOLumi=\"");
		sb.append((int) (1 * 500));
		sb.append("\" LILumi=\"");
		sb.append((int) (1 * 500));
		sb.append("\" Status=\"");
		sb.append("0");
		sb.append("\" CommStatus=\"");
		sb.append("0");
		sb.append("\"/>\n");

		sb.append("<Device Type=\"13\" StandardNumber=\"241600013000000005\" RecTime=\""); // 光强检测器
		sb.append(sdf.format(new Date()));
		sb.append("\" LOLumi=\"");
		sb.append((int) (1 * 500));
		sb.append("\" LILumi=\"");
		sb.append((int) (1 * 500));
		sb.append("\" Status=\"");
		sb.append("0");
		sb.append("\" CommStatus=\"");
		sb.append("0");
		sb.append("\"/>\n");

		sb.append("<Device Type=\"13\" StandardNumber=\"241600013000000006\" RecTime=\""); // 光强检测器
		sb.append(sdf.format(new Date()));
		sb.append("\" LOLumi=\"");
		sb.append((int) (1 * 500));
		sb.append("\" LILumi=\"");
		sb.append((int) (1 * 500));
		sb.append("\" Status=\"");
		sb.append("0");
		sb.append("\" CommStatus=\"");
		sb.append("0");
		sb.append("\"/>\n");

	}

	private static void setWS(StringBuilder sb) {
		sb.append("<Device Type=\"11\" StandardNumber=\"241600011000000001\" RecTime=\""); // 风速风向检测器
		sb.append(sdf.format(new Date()));
		sb.append("\" Direction=\"");
		sb.append("0");
		sb.append("\" Speed=\"");
		sb.append((int) (1 * 10));
		sb.append("\" Status=\"");
		sb.append("0");
		sb.append("\" CommStatus=\"");
		sb.append("0");
		sb.append("\"/>\n");

		sb.append("<Device Type=\"11\" StandardNumber=\"241600011000000002\" RecTime=\""); // 风速风向检测器
		sb.append(sdf.format(new Date()));
		sb.append("\" Direction=\"");
		sb.append("0");
		sb.append("\" Speed=\"");
		sb.append((int) (1 * 10));
		sb.append("\" Status=\"");
		sb.append("0");
		sb.append("\" CommStatus=\"");
		sb.append("0");
		sb.append("\"/>\n");

		sb.append("<Device Type=\"11\" StandardNumber=\"25000000008000002\" RecTime=\""); // 风速风向检测器
		sb.append(sdf.format(new Date()));
		sb.append("\" Direction=\"");
		sb.append("0");
		sb.append("\" Speed=\"");
		sb.append((int) (1 * 10));
		sb.append("\" Status=\"");
		sb.append("0");
		sb.append("\" CommStatus=\"");
		sb.append("0");
		sb.append("\"/>\n");

		sb.append("<Device Type=\"11\" StandardNumber=\"241600011000000003\" RecTime=\""); // 风速风向检测器
		sb.append(sdf.format(new Date()));
		sb.append("\" Direction=\"");
		sb.append("0");
		sb.append("\" Speed=\"");
		sb.append((int) (1 * 10));
		sb.append("\" Status=\"");
		sb.append("0");
		sb.append("\" CommStatus=\"");
		sb.append("0");
		sb.append("\"/>\n");

		sb.append("<Device Type=\"11\" StandardNumber=\"241600011000000004\" RecTime=\""); // 风速风向检测器
		sb.append(sdf.format(new Date()));
		sb.append("\" Direction=\"");
		sb.append("0");
		sb.append("\" Speed=\"");
		sb.append((int) (1 * 10));
		sb.append("\" Status=\"");
		sb.append("0");
		sb.append("\" CommStatus=\"");
		sb.append("0");
		sb.append("\"/>\n");
	}

	private static void setVD(StringBuilder sb) {
		sb.append("<Device Type=\"10\" StandardNumber=\"241600010010000008\" RecTime=\""); // 车检器
		sb.append(sdf.format(new Date()));
		sb.append("\" UpFlux=\"");
		sb.append((int) (1 * 10 + 10));
		sb.append("\" DwFlux=\"");
		sb.append((int) (1 * 10 + 10));
		sb.append("\" UpSpeed=\"");
		sb.append((int) (1 * 40 + 80));
		sb.append("\" DwSpeed=\"");
		sb.append((int) (1 * 40 + 80));
		sb.append("\" UpOcc=\"");
		sb.append((int) (1 * 10));
		sb.append("\" DwOcc=\"");
		sb.append((int) (1 * 10));
		sb.append("\" Status=\"");
		sb.append("0");
		sb.append("\" CommStatus=\"");
		sb.append("0");
		sb.append("\"/>\n");

		sb.append("<Device Type=\"10\" StandardNumber=\"241600010010000007\" RecTime=\"");
		sb.append(sdf.format(new Date()));
		sb.append("\" UpFlux=\"");
		sb.append((int) (1 * 10 + 10));
		sb.append("\" DwFlux=\"");
		sb.append((int) (1 * 10 + 10));
		sb.append("\" UpSpeed=\"");
		sb.append((int) (1 * 40 + 80));
		sb.append("\" DwSpeed=\"");
		sb.append((int) (1 * 40 + 80));
		sb.append("\" UpOcc=\"");
		sb.append((int) (1 * 10));
		sb.append("\" DwOcc=\"");
		sb.append((int) (1 * 10));
		sb.append("\" Status=\"");
		sb.append("0");
		sb.append("\" CommStatus=\"");
		sb.append("0");
		sb.append("\"/>\n");

		sb.append("<Device Type=\"10\" StandardNumber=\"241600010010000006\" RecTime=\""); // 车检器
		sb.append(sdf.format(new Date()));
		sb.append("\" UpFlux=\"");
		sb.append((int) (1 * 10 + 10));
		sb.append("\" DwFlux=\"");
		sb.append((int) (1 * 10 + 10));
		sb.append("\" UpSpeed=\"");
		sb.append((int) (1 * 40 + 80));
		sb.append("\" DwSpeed=\"");
		sb.append((int) (1 * 40 + 80));
		sb.append("\" UpOcc=\"");
		sb.append((int) (1 * 10));
		sb.append("\" DwOcc=\"");
		sb.append((int) (1 * 10));
		sb.append("\" Status=\"");
		sb.append("0");
		sb.append("\" CommStatus=\"");
		sb.append("0");
		sb.append("\"/>\n");

		sb.append("<Device Type=\"10\" StandardNumber=\"241600010010000005\" RecTime=\"");
		sb.append(sdf.format(new Date()));
		sb.append("\" UpFlux=\"");
		sb.append((int) (1 * 10 + 10));
		sb.append("\" DwFlux=\"");
		sb.append((int) (1 * 10 + 10));
		sb.append("\" UpSpeed=\"");
		sb.append((int) (1 * 40 + 80));
		sb.append("\" DwSpeed=\"");
		sb.append((int) (1 * 40 + 80));
		sb.append("\" UpOcc=\"");
		sb.append((int) (1 * 10));
		sb.append("\" DwOcc=\"");
		sb.append((int) (1 * 10));
		sb.append("\" Status=\"");
		sb.append("0");
		sb.append("\" CommStatus=\"");
		sb.append("0");
		sb.append("\"/>\n");

		sb.append("<Device Type=\"10\" StandardNumber=\"241600010010000004\" RecTime=\""); // 车检器
		sb.append(sdf.format(new Date()));
		sb.append("\" UpFlux=\"");
		sb.append((int) (1 * 10 + 10));
		sb.append("\" DwFlux=\"");
		sb.append((int) (1 * 10 + 10));
		sb.append("\" UpSpeed=\"");
		sb.append((int) (1 * 40 + 80));
		sb.append("\" DwSpeed=\"");
		sb.append((int) (1 * 40 + 80));
		sb.append("\" UpOcc=\"");
		sb.append((int) (1 * 10));
		sb.append("\" DwOcc=\"");
		sb.append((int) (1 * 10));
		sb.append("\" Status=\"");
		sb.append("0");
		sb.append("\" CommStatus=\"");
		sb.append("0");
		sb.append("\"/>\n");

		sb.append("<Device Type=\"10\" StandardNumber=\"241600010010000003\" RecTime=\"");
		sb.append(sdf.format(new Date()));
		sb.append("\" UpFlux=\"");
		sb.append((int) (1 * 10 + 10));
		sb.append("\" DwFlux=\"");
		sb.append((int) (1 * 10 + 10));
		sb.append("\" UpSpeed=\"");
		sb.append((int) (1 * 40 + 80));
		sb.append("\" DwSpeed=\"");
		sb.append((int) (1 * 40 + 80));
		sb.append("\" UpOcc=\"");
		sb.append((int) (1 * 10));
		sb.append("\" DwOcc=\"");
		sb.append((int) (1 * 10));
		sb.append("\" Status=\"");
		sb.append("0");
		sb.append("\" CommStatus=\"");
		sb.append("0");
		sb.append("\"/>\n");

		sb.append("<Device Type=\"10\" StandardNumber=\"241600010010000002\" RecTime=\""); // 车检器
		sb.append(sdf.format(new Date()));
		sb.append("\" UpFlux=\"");
		sb.append((int) (1 * 10 + 10));
		sb.append("\" DwFlux=\"");
		sb.append((int) (1 * 10 + 10));
		sb.append("\" UpSpeed=\"");
		sb.append((int) (1 * 40 + 80));
		sb.append("\" DwSpeed=\"");
		sb.append((int) (1 * 40 + 80));
		sb.append("\" UpOcc=\"");
		sb.append((int) (1 * 10));
		sb.append("\" DwOcc=\"");
		sb.append((int) (1 * 10));
		sb.append("\" Status=\"");
		sb.append("0");
		sb.append("\" CommStatus=\"");
		sb.append("0");
		sb.append("\"/>\n");

		sb.append("<Device Type=\"10\" StandardNumber=\"241600010010000001\" RecTime=\"");
		sb.append(sdf.format(new Date()));
		sb.append("\" UpFlux=\"");
		sb.append((int) (1 * 10 + 10));
		sb.append("\" DwFlux=\"");
		sb.append((int) (1 * 10 + 10));
		sb.append("\" UpSpeed=\"");
		sb.append((int) (1 * 40 + 80));
		sb.append("\" DwSpeed=\"");
		sb.append((int) (1 * 40 + 80));
		sb.append("\" UpOcc=\"");
		sb.append((int) (1 * 10));
		sb.append("\" DwOcc=\"");
		sb.append((int) (1 * 10));
		sb.append("\" Status=\"");
		sb.append("0");
		sb.append("\" CommStatus=\"");
		sb.append("0");
		sb.append("\"/>\n");

		sb.append("<Device Type=\"10\" StandardNumber=\"241600010010000009\" RecTime=\""); // 车检器
		sb.append(sdf.format(new Date()));
		sb.append("\" UpFlux=\"");
		sb.append((int) (1 * 10 + 10));
		sb.append("\" DwFlux=\"");
		sb.append((int) (1 * 10 + 10));
		sb.append("\" UpSpeed=\"");
		sb.append((int) (1 * 40 + 80));
		sb.append("\" DwSpeed=\"");
		sb.append((int) (1 * 40 + 80));
		sb.append("\" UpOcc=\"");
		sb.append((int) (1 * 10));
		sb.append("\" DwOcc=\"");
		sb.append((int) (1 * 10));
		sb.append("\" Status=\"");
		sb.append("0");
		sb.append("\" CommStatus=\"");
		sb.append("0");
		sb.append("\"/>\n");

		sb.append("<Device Type=\"10\" StandardNumber=\"241600010010000012\" RecTime=\"");
		sb.append(sdf.format(new Date()));
		sb.append("\" UpFlux=\"");
		sb.append((int) (1 * 10 + 10));
		sb.append("\" DwFlux=\"");
		sb.append((int) (1 * 10 + 10));
		sb.append("\" UpSpeed=\"");
		sb.append((int) (1 * 40 + 80));
		sb.append("\" DwSpeed=\"");
		sb.append((int) (1 * 40 + 80));
		sb.append("\" UpOcc=\"");
		sb.append((int) (1 * 10));
		sb.append("\" DwOcc=\"");
		sb.append((int) (1 * 10));
		sb.append("\" Status=\"");
		sb.append("0");
		sb.append("\" CommStatus=\"");
		sb.append("0");
		sb.append("\"/>\n");

		sb.append("<Device Type=\"10\" StandardNumber=\"241600010010000013\" RecTime=\""); // 车检器
		sb.append(sdf.format(new Date()));
		sb.append("\" UpFlux=\"");
		sb.append((int) (1 * 10 + 10));
		sb.append("\" DwFlux=\"");
		sb.append((int) (1 * 10 + 10));
		sb.append("\" UpSpeed=\"");
		sb.append((int) (1 * 40 + 80));
		sb.append("\" DwSpeed=\"");
		sb.append((int) (1 * 40 + 80));
		sb.append("\" UpOcc=\"");
		sb.append((int) (1 * 10));
		sb.append("\" DwOcc=\"");
		sb.append((int) (1 * 10));
		sb.append("\" Status=\"");
		sb.append("0");
		sb.append("\" CommStatus=\"");
		sb.append("0");
		sb.append("\"/>\n");

		sb.append("<Device Type=\"10\" StandardNumber=\"241600010010000014\" RecTime=\"");
		sb.append(sdf.format(new Date()));
		sb.append("\" UpFlux=\"");
		sb.append((int) (1 * 10 + 10));
		sb.append("\" DwFlux=\"");
		sb.append((int) (1 * 10 + 10));
		sb.append("\" UpSpeed=\"");
		sb.append((int) (1 * 40 + 80));
		sb.append("\" DwSpeed=\"");
		sb.append((int) (1 * 40 + 80));
		sb.append("\" UpOcc=\"");
		sb.append((int) (1 * 10));
		sb.append("\" DwOcc=\"");
		sb.append((int) (1 * 10));
		sb.append("\" Status=\"");
		sb.append("0");
		sb.append("\" CommStatus=\"");
		sb.append("0");
		sb.append("\"/>\n");

		sb.append("<Device Type=\"10\" StandardNumber=\"241600010010000015\" RecTime=\""); // 车检器
		sb.append(sdf.format(new Date()));
		sb.append("\" UpFlux=\"");
		sb.append((int) (1 * 10 + 10));
		sb.append("\" DwFlux=\"");
		sb.append((int) (1 * 10 + 10));
		sb.append("\" UpSpeed=\"");
		sb.append((int) (1 * 40 + 80));
		sb.append("\" DwSpeed=\"");
		sb.append((int) (1 * 40 + 80));
		sb.append("\" UpOcc=\"");
		sb.append((int) (1 * 10));
		sb.append("\" DwOcc=\"");
		sb.append((int) (1 * 10));
		sb.append("\" Status=\"");
		sb.append("0");
		sb.append("\" CommStatus=\"");
		sb.append("0");
		sb.append("\"/>\n");

		sb.append("<Device Type=\"10\" StandardNumber=\"241600010010000016\" RecTime=\"");
		sb.append(sdf.format(new Date()));
		sb.append("\" UpFlux=\"");
		sb.append((int) (1 * 10 + 10));
		sb.append("\" DwFlux=\"");
		sb.append((int) (1 * 10 + 10));
		sb.append("\" UpSpeed=\"");
		sb.append((int) (1 * 40 + 80));
		sb.append("\" DwSpeed=\"");
		sb.append((int) (1 * 40 + 80));
		sb.append("\" UpOcc=\"");
		sb.append((int) (1 * 10));
		sb.append("\" DwOcc=\"");
		sb.append((int) (1 * 10));
		sb.append("\" Status=\"");
		sb.append("0");
		sb.append("\" CommStatus=\"");
		sb.append("0");
		sb.append("\"/>\n");

		sb.append("<Device Type=\"10\" StandardNumber=\"241600010010000017\" RecTime=\""); // 车检器
		sb.append(sdf.format(new Date()));
		sb.append("\" UpFlux=\"");
		sb.append((int) (1 * 10 + 10));
		sb.append("\" DwFlux=\"");
		sb.append((int) (1 * 10 + 10));
		sb.append("\" UpSpeed=\"");
		sb.append((int) (1 * 40 + 80));
		sb.append("\" DwSpeed=\"");
		sb.append((int) (1 * 40 + 80));
		sb.append("\" UpOcc=\"");
		sb.append((int) (1 * 10));
		sb.append("\" DwOcc=\"");
		sb.append((int) (1 * 10));
		sb.append("\" Status=\"");
		sb.append("0");
		sb.append("\" CommStatus=\"");
		sb.append("0");
		sb.append("\"/>\n");

		sb.append("<Device Type=\"10\" StandardNumber=\"241600010010000018\" RecTime=\"");
		sb.append(sdf.format(new Date()));
		sb.append("\" UpFlux=\"");
		sb.append((int) (1 * 10 + 10));
		sb.append("\" DwFlux=\"");
		sb.append((int) (1 * 10 + 10));
		sb.append("\" UpSpeed=\"");
		sb.append((int) (1 * 40 + 80));
		sb.append("\" DwSpeed=\"");
		sb.append((int) (1 * 40 + 80));
		sb.append("\" UpOcc=\"");
		sb.append((int) (1 * 10));
		sb.append("\" DwOcc=\"");
		sb.append((int) (1 * 10));
		sb.append("\" Status=\"");
		sb.append("0");
		sb.append("\" CommStatus=\"");
		sb.append("0");
		sb.append("\"/>\n");
	}

	public static void operateOnTime() {

		// 定时执行的任务
		TimerTask task = new TimerTask() {

			@Override
			public void run() {

				Test.testPost("http://127.0.0.1:8080/cms/das_report.xml");
				System.out.println(" 第 " + ++count + " 次执行");

			}

		};

		// 创建一个定时器
		Timer timer = new Timer();
		// 设置在多长时间以后执行，然后每个多长时间重复执行
		// 设值 5 秒钟后开始执行第一次，以后每隔 2 秒中执行一次
		// timer.schedule(task, 5 * 1000, 2 * 1000);

		// 设置从某一时刻开始执行，然后每隔多长时间重复执行
		// 设置从当前时间开始执行，然后每间隔2秒执行一次
		timer.schedule(task, Calendar.getInstance().getTime(), 1 * 100);
	}
}
