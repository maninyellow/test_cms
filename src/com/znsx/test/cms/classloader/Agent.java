package com.znsx.test.cms.classloader;

import java.lang.instrument.Instrumentation;
import java.lang.instrument.UnmodifiableClassException;

/**
 * Agent
 * 
 * @author MIKE
 *         <p />
 *         Create at 2016年1月28日 下午3:32:54
 */
public class Agent {

	@SuppressWarnings("rawtypes")
	public static void agentmain(String agentArgs, Instrumentation inst)
			throws ClassNotFoundException, UnmodifiableClassException,
			InterruptedException {
		inst.addTransformer(new Transformer(), true);
		inst.retransformClasses(BeforeModel.class);
	}
}
