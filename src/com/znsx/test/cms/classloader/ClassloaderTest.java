package com.znsx.test.cms.classloader;

import java.lang.instrument.Instrumentation;
import java.net.URL;
import java.util.List;
import java.util.Map.Entry;
import java.util.Properties;

import com.sun.jndi.url.corbaname.corbanameURLContextFactory;
import com.sun.tools.attach.VirtualMachine;
import com.sun.tools.attach.VirtualMachineDescriptor;

/**
 * ClassloaderTest
 * 
 * @author MIKE
 *         <p />
 *         Create at 2016年1月27日 下午2:24:44
 */
public class ClassloaderTest {

	/**
	 * main方法说明
	 * 
	 * @param args
	 * @author MIKE
	 *         <p />
	 *         Create at 2016年1月27日 下午2:24:45
	 */
	public static void main(String[] args) throws Exception {
		URL[] urls = sun.misc.Launcher.getBootstrapClassPath().getURLs();
		for (URL url : urls) {
			System.out.println(url.getPath());
		}

		System.out.println(System.class.getClassLoader());
		System.out.println(ClassLoader.getSystemClassLoader());
		System.out.println(System.getProperty("java.class.path"));
		System.out.println(System.getProperty("java.ext.dirs"));

		List<VirtualMachineDescriptor> vmds = VirtualMachine.list();

		for (VirtualMachineDescriptor vmd : vmds) {
			System.out.println(vmd.displayName());
			if (vmd.displayName().endsWith("ApplicationMain")) {
				VirtualMachine vm = VirtualMachine.attach(vmd);
				Properties properties = vm.getSystemProperties();
//				for (Entry<Object, Object> entry : properties.entrySet()) {
//					System.out.println(entry.getKey() + "=" + entry.getValue());
//				}
				vm.loadAgent("E:/agentTest/lib/agent.jar");
				vm.detach();
			}
		}
		
//		VirtualMachine vm = VirtualMachine.attach("7548");
	}

}
