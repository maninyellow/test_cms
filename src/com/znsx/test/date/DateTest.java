package com.znsx.test.date;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import net.sf.json.util.NewBeanInstanceStrategy;

import org.jdom.Element;

import com.znsx.util.xml.ElementUtil;

public class DateTest {

	/**
	 * main方法说明
	 * 
	 * @param args
	 * @author huangbuji
	 *         <p />
	 *         Create at 2015-5-21 下午1:51:35
	 */
	public static void main(String[] args) throws Exception {
		// 2014-02-01, 分区表起始分区
//		final long beginDate = 1391184000000l;
//		// 2015-07-17
//		final long endDate = 1437123970791l;

		// while (true) {
		// for (int j = 10; j < 60; j++) {
		// for (int i = 10; i < 60; i++) {
		// String s = "2015-05-21 13:" + i + ":" + j;
		// final Element e = new Element("Date");
		// e.setAttribute("RecTime", s);
		// e.setAttribute("StandardNumber", i + "" + j);
		// e.setAttribute("Value", i + "");
		// e.setAttribute("Status", j + "");
		//
		// Runnable r = new Runnable() {
		//
		// @Override
		// public void run() {
		// for (int i = 0; i < 10; i++) {
		// try {
		//
		// Timestamp timestamp = ElementUtil
		// .getTimestamp(e, "RecTime");
		// if (timestamp.getTime() < beginDate
		// || timestamp.getTime() > endDate) {
		// System.out.println(timestamp);
		// }
		// } catch (Exception e) {
		// }
		// }
		// }
		// };
		//
		// Thread t = new Thread(r);
		// t.start();
		// }
		// }
		// }

//		while (true) {
//
//			Runnable r = new Runnable() {
//				public void run() {
//					for (int i = 0; i < 100; i++) {
//						Timestamp t = new Timestamp(System.currentTimeMillis());
//						if ((t.getTime() < beginDate) || t.getTime() >= endDate) {
//							System.out.println(t);
//						}
//					}
//				}
//			};
//
//			Thread t = new Thread(r);
//			t.start();
//		}
		
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//		String s = "2015-10-25 08:00:00";
//		System.out.println(sdf.parse(s).getTime());
//		
//		System.out.println(new Date(1449803859102l));
//		System.out.println(new Date(1438683560368l));
		
//		System.out.println((1443089645425l + 240000L) < System.currentTimeMillis() );
//		System.out.println(new Date(1442566698601l));
		
		int subType = 8;
		
		System.out.println(new Date(1452235843601l));
	}
}
