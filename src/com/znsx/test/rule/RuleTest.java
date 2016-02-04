package com.znsx.test.rule;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.StatelessKieSession;

import com.znsx.test.bo.User;

public class RuleTest {

	/**
	 * main方法说明
	 * 
	 * @param args
	 * @author huangbuji
	 *         <p />
	 *         Create at 2014-6-25 下午5:02:03
	 */
	public static void main(String[] args) {
		KieServices kieServices = KieServices.Factory.get();
		KieContainer kieContainer = kieServices.getKieClasspathContainer();
		
		StatelessKieSession kieSession = kieContainer.newStatelessKieSession();
		User user = new User();
		user.setId(Integer.valueOf(10));
		user.setName("张三");
		user.setAge(23);
		
		kieSession.execute(user);
		
		System.out.println(user.getName());
	}

}
