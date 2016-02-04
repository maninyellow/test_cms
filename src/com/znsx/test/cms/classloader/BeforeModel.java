package com.znsx.test.cms.classloader;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.persistence.Column;

/**
 * BeforeModel
 * 
 * @author MIKE
 *         <p />
 *         Create at 2016年1月28日 下午2:24:03
 */
@MessageDriven(activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
		@ActivationConfigProperty(propertyName="messageSelector", propertyValue="Platform=2510000000000"),
		@ActivationConfigProperty(propertyName = "destination", propertyValue = "QueueCMS") })
public class BeforeModel {

	private final static String CLASS_NAME = "Before";

	private String instanceName = "before_instance";

	public static String getClassName() {
		return BeforeModel.CLASS_NAME;
	}

	@Column(name = "before")
	public String getInstanceName() {
		return this.instanceName;
	}

	public void setInstanceName(String instanceName) {
		this.instanceName = instanceName;
	}

}
