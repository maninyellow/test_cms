package com.znsx.test.mq;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * MsgReceiver2
 * @author MIKE
 * <p />
 * Create at 2015 下午3:36:25
 */
public class MsgReceiver2 {
	/**
	 * main方法说明
	 * 
	 * @param args
	 * @author huangbuji
	 *         <p />
	 *         Create at 2015-2-26 下午2:14:48
	 */
	public static void main(String[] args) {
		// Connection ：JMS 客户端到JMS Provider 的连接
		Connection connection = null;
		// Session： 一个发送或接收消息的线程
		Session session;
		// Destination ：消息的目的地;消息发送给谁.
		Destination destination;
		// MessageProducer：消息发送者
		MessageConsumer consumer;

		ConnectionFactory factory = new ActiveMQConnectionFactory(
				"tcp://192.168.1.7:61616");

		try {
			connection = factory.createConnection();
			connection.start();
			session = connection.createSession(false,
					Session.CLIENT_ACKNOWLEDGE);
			destination = session.createQueue("QueueCMS-HBJ");
			consumer = session.createConsumer(destination);
			consumer.setMessageListener(new MsgListener());
		} catch (JMSException e) {
			e.printStackTrace();
		}

	}
}
