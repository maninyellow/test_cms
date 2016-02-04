package com.znsx.test.instrumentation;

import java.util.List;
import java.util.Properties;
import java.util.Map.Entry;

import com.sun.tools.attach.VirtualMachine;
import com.sun.tools.attach.VirtualMachineDescriptor;

/**
 * InstrumentationMain
 * @author MIKE
 * <p />
 * Create at 2016年2月3日 下午2:41:56
 */
public class InstrumentationMain {

	/**
	 * main方法说明
	 * 
	 * @param args
	 * @author MIKE
	 * <p />
	 * Create at 2016年2月3日 下午2:41:56
	 */
	public static void main(String[] args) {
		try {
			List<VirtualMachineDescriptor> vmds = VirtualMachine.list();

			for (VirtualMachineDescriptor vmd : vmds) {
				System.out.println(vmd.displayName());
				if (vmd.displayName().contains("jboss.modules.Main")) {
					VirtualMachine vm = VirtualMachine.attach(vmd);
					Properties properties = vm.getSystemProperties();
					System.out.println(properties.getProperty("jboss.home.dir"));
					vm.loadAgent(properties.getProperty("jboss.home.dir") + "/agent.jar");
					vm.detach();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

}
