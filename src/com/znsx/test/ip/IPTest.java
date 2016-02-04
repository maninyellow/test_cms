package com.znsx.test.ip;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.util.Comparator;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;

import org.apache.commons.net.util.SubnetUtils;
import org.apache.commons.net.util.SubnetUtils.SubnetInfo;

import com.znsx.util.network.NetworkUtil;

/**
 * IPTest
 * 
 * @author huangbuji
 *         <p />
 *         Create at 2014-1-6 下午4:28:47
 */
public class IPTest {

	/**
	 * main方法说明
	 * 
	 * @param args
	 * @author huangbuji
	 *         <p />
	 *         Create at 2014-1-6 下午4:28:47
	 */
	public static void main(String[] args) throws Exception {
//		System.setProperty("java.net.preferIPv4Stack", "true");
		
//		Enumeration<NetworkInterface> nis = NetworkInterface
//				.getNetworkInterfaces();
//		while (nis.hasMoreElements()) {
//			NetworkInterface ni = nis.nextElement();
//
//			List<InterfaceAddress> list = ni.getInterfaceAddresses();
//			for (InterfaceAddress ia : list) {
//				if (null != ia) {
//					InetAddress inetAddress = ia.getAddress();
//					if (inetAddress instanceof Inet4Address) {
//						short maskLength = ia.getNetworkPrefixLength();
//						String cidrNotation = inetAddress.getHostAddress() + "/" + maskLength;
//
//						System.out.println(cidrNotation);
////						SubnetUtils subnet = new SubnetUtils(cidrNotation);
////						System.out.println(subnet.getInfo().isInRange("192.168.1.2"));
//					}
//				}
//			}
//		}
		
//		System.out.println(System.currentTimeMillis());
//		
//		System.out.println(new Date(1426176000000l));
		System.out.println(new Date(1433151069815l));
		
		String localIp = InetAddress.getLocalHost().getHostAddress();
		System.out.println(NetworkUtil.getAllCidrIP());
		System.out.println(localIp);
	}

}
