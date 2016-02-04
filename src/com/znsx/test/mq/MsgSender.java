package com.znsx.test.mq;

import java.util.UUID;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * MsgSender
 * 
 * @author huangbuji
 *         <p />
 *         Create at 2015-2-26 下午1:56:43
 */
public class MsgSender {

	/**
	 * main方法说明
	 * 
	 * @param args
	 * @author huangbuji
	 *         <p />
	 *         Create at 2015-2-26 下午1:56:45
	 */
	public static void main(String[] args) {
		// Connection ：JMS 客户端到JMS Provider 的连接ng
		Connection connection = null;
		// Session： 一个发送或接收消息的线程
		Session session;
		// Destination ：消息的目的地;消息发送给谁.
		Destination destination;
		// MessageProducer：消息发送者
		MessageProducer producer;

		ConnectionFactory factory = new ActiveMQConnectionFactory(
				"tcp://192.168.1.7:61616");

		try {
			connection = factory.createConnection();
			connection.start();
			session = connection.createSession(false,
					Session.CLIENT_ACKNOWLEDGE);
			destination = session.createQueue("QueueCMS-HBJ");
//			destination = session.createTopic("TopicEvent");
			producer = session.createProducer(destination);
			producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
			Destination replyto = session.createQueue("TestQueueCMS");

			long begin = System.currentTimeMillis();

			// Message message = session.createTextMessage("HBJ say hello !");
			// License license = new License();
			// license.setCpuidList("BFEBFBFF000306A9");
			// license.setMacList("");
			// license.setMotherBoardList("..CN7220033F015B.");
			// license.setExpireTime("2020-10-01");
			// license.setCameraAmount("5000");
			// license.setDeviceAmount("1000");
			// license.setUserAmount("9999");
			// Message message = session.createObjectMessage(license);
			// Role role1 = new Role();
			// role1.setName("互联角色A");
			// role1.setPlatformCode("2310000000000000000");
			// role1.setSn("2310000023900000901");
			//
			// Role role2 = new Role();
			// role2.setName("互联角色B");
			// role2.setPlatformCode("2310000000000000000");
			// role2.setSn("2310000023900000902");
			// TextMessage message =
			// session.createTextMessage(JSONArray.fromObject(Arrays.asList(role1,
			// role2)).toString());
//			 String request = "<Request Method=\"UserLogin\">";
			// String request = "<Request Method=\"CreateVisitRecord\">";
//			 String request = "<Request Method=\"ListTmOrganDevice\">";
			// String request = "<Request Method=\"ListHistoryAlarm\">";
			// String request = "<Request Method=\"ListDevice\">";
//			 String request = "<Request Method=\"ListDasDevice\">";
			// String request = "<Request Method=\"ListSvOrgDevice\">";
			// String request = "<Request Method=\"ListDeviceStatus\">";
//			String request = "<Request Method=\"DeleteUser\">";
//			String request = "<Request Method=\"DutyLogin\">";
//			 String request = "<Request Method=\"CreateOrgan\">";
//			 String request = "<Request Method=\"CreateAlarm\">";
			String request = "<Request Method=\"ServerConfig\">";
			
			request += "<UserId>1</UserId>";
			request += "<OrganId>100003</OrganId>";
			request += "<BeginTime>1245731200000</BeginTime>";
			request += "<EndTime>1462006400000</EndTime>";
			request += "<ClientType>SGC</ClientType>";
			request += "<IP>192.168.1.104</IP>";
			request += "<Recursion>1</Recursion>";
			request += "<LoginName>admin</LoginName>";
			request += "<Password>e10adc3949ba59abbe56e057f20f883e</Password>";
			request += "<Visitors>李强,张兵,王勇</Visitors>";
			request += "<Phone>13880448283</Phone>";
			request += "<ArriveTime>1432512000000</ArriveTime>";
			request += "<LeaveTime>1442512000000</LeaveTime>";
			request += "<Reason>长永高速K21+700交通事故调查</Reason>";
			request += "<Result>湘A4RD89左前轮爆胎失控</Result>";
			request += "<Note>张厅电话询问过此事故</Note>";
			request += "<Scope>hour</Scope>";
			request += "<DasSN>251000000920400001</DasSN>";
			request += "<SubType>7</SubType>";
			request += "<Id>3</Id>";
			request += "<Name>机构------22221</Name>";
			request += "<Type>1000</Type>";
//			request += "<StandardNumber>251000000920400001</StandardNumber>";
			
			
//			request += "<Alarm AlarmTime=\"";
//			request += System.currentTimeMillis() + "\" Level=\"1\" StandardNumber=\"2510000000100005\" SubType=\"8\" CurrentValue=\"100\" Threshold=\"90\" AlarmContent=\"CO阀值超标报警\"/>";
			request += "</Request>";

			TextMessage message = session.createTextMessage(request);
			message.setStringProperty("Method", "ServerConfig");
			// message.setStringProperty("Type", "Text");
			message.setJMSType("Text");
			message.setIntProperty("Cmd", 2001);
			message.setFloatProperty("Float", 3.001f);
			message.setJMSReplyTo(replyto);
			message.setJMSCorrelationID("Mike-test-"
					+ System.currentTimeMillis());
			message.setStringProperty("BusinessId",
					UUID.randomUUID().toString());
			// producer.setTimeToLive(60000);
			for (int i = 0; i < 1; i++) {
				producer.send(message);
			}
			System.out.println("Send use "
					+ (System.currentTimeMillis() - begin) + "ms");
		} catch (JMSException e) {
			e.printStackTrace();
		} finally {
			if (null != connection) {
				try {
					connection.close();
				} catch (JMSException e) {
					e.printStackTrace();
				}
			}
		}

	}

}
