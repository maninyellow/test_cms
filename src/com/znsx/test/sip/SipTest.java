package com.znsx.test.sip;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.sip.ClientTransaction;
import javax.sip.ListeningPoint;
import javax.sip.SipFactory;
import javax.sip.SipProvider;
import javax.sip.SipStack;
import javax.sip.address.Address;
import javax.sip.address.AddressFactory;
import javax.sip.address.SipURI;
import javax.sip.header.CSeqHeader;
import javax.sip.header.CallIdHeader;
import javax.sip.header.ContentTypeHeader;
import javax.sip.header.FromHeader;
import javax.sip.header.Header;
import javax.sip.header.HeaderFactory;
import javax.sip.header.MaxForwardsHeader;
import javax.sip.header.ToHeader;
import javax.sip.header.ViaHeader;
import javax.sip.message.MessageFactory;
import javax.sip.message.Request;

/**
 * SipTest
 * 
 * @author huangbuji
 *         <p />
 *         Create at 2013-8-1 下午5:28:45
 */
public class SipTest {

	public static String ip = "0.0.0.0";
	public static int port = 5060;
	public static String ccsIp = "192.168.1.107";
	public static int ccsPort = 5060;
	public static String from = "251000000021000001";
	public static String to = "251000000024000001";
	public static String protocolCode = "4900";
	public static String fromTag = System.currentTimeMillis() + "";

	public static long cseq = 1L;

	/**
	 * main方法说明
	 * 
	 * @param args
	 * @author huangbuji
	 *         <p />
	 *         Create at 2013-8-1 下午5:28:45
	 */
	public static void main(String[] args) throws Exception {
		initSipStack();
	}

	public static void initSipStack() throws Exception {
		SipFactory sipFactory = SipFactory.getInstance();
		sipFactory.setPathName("gov.nist");
		Properties properties = new Properties();
		properties.setProperty("javax.sip.STACK_NAME", "TestClient");
		properties.setProperty("javax.sip.IP_ADDRESS", ip);
//		properties.setProperty("javax.sip.OUTBOUND_PROXY", ip + ":" + port
//				+ "/UDP");

		SipStack sipStack = sipFactory.createSipStack(properties);
		HeaderFactory headerFactory = sipFactory.createHeaderFactory();
		AddressFactory addressFactory = sipFactory.createAddressFactory();
		MessageFactory messageFactory = sipFactory.createMessageFactory();

		ListeningPoint udp = sipStack.createListeningPoint(ip, port, "udp");
		SipProvider sipProvider = sipStack.createSipProvider(udp);
		sipProvider.addSipListener(new UDPListener());

		// create request
		SipURI toUri = addressFactory.createSipURI(to, ccsIp + ":" + ccsPort);
		SipURI fromUri = addressFactory.createSipURI(from, ip + ":" + port);
		CallIdHeader callIdHeader = headerFactory.createCallIdHeader(from);
		CSeqHeader cSeqHeader = headerFactory.createCSeqHeader(cseq++,
				Request.MESSAGE);
		Address fromAddress = addressFactory.createAddress(fromUri);
		Address toAddress = addressFactory.createAddress(toUri);
		FromHeader fromHeader = headerFactory.createFromHeader(fromAddress,
				fromTag);
		ToHeader toHeader = headerFactory.createToHeader(toAddress, null);
		ViaHeader viaHeader = headerFactory.createViaHeader(ip, port, "udp",
				fromTag.substring(5));
		List<ViaHeader> viaList = new ArrayList<ViaHeader>();
		viaList.add(viaHeader);
		MaxForwardsHeader maxForwardsHeader = headerFactory
				.createMaxForwardsHeader(70);
		ContentTypeHeader contentTypeHeader = headerFactory
				.createContentTypeHeader("application", "xml");
		String body = "<Request Method=\"Ptz_Control\" Cmd=\"4900\">\r\n</Request>";
		byte[] content = body.getBytes("utf8");
		Request request = messageFactory.createRequest(toUri, Request.MESSAGE,
				callIdHeader, cSeqHeader, fromHeader, toHeader, viaList,
				maxForwardsHeader, contentTypeHeader, content);

		Header subject = headerFactory.createHeader("Subject","name=user;session=10050000000000002357");
		request.addHeader(subject);
		ClientTransaction client = sipProvider.getNewClientTransaction(request);
		client.sendRequest();
	}
}
