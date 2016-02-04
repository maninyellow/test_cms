package com.znsx.test.mq;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.apache.activemq.command.ActiveMQObjectMessage;

/**
 * MsgListener
 * 
 * @author huangbuji
 *         <p />
 *         Create at 2015-2-26 下午2:35:30
 */
public class MsgListener implements MessageListener {

	@Override
	public void onMessage(Message message) {
		try {
			if (message instanceof TextMessage) {
				System.out.println(((TextMessage)message).getText());
				message.acknowledge();
			} else if (message instanceof ActiveMQObjectMessage) {
				Object obj = ((ActiveMQObjectMessage) message).getObject();
				System.out.println(obj.getClass().getSimpleName());
				System.out.println(obj.toString());
				message.acknowledge();
			} else {
				System.out.println("Unsupport message : " + message.toString());
			}
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

}
