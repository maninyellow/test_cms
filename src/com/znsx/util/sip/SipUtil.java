package com.znsx.util.sip;

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
 * SipUtil
 * 
 * @author huangbuji
 *         <p />
 *         Create at 2013-8-2 上午10:15:21
 */
public class SipUtil {
	private String ip = "192.168.1.104";
	private int port = 5060;
	private String ccsIp = "192.168.1.107";
	private int ccsPort = 5060;
	public String from = "251000000021000001";
	public String to = "251000000024000001";
	public String protocolCode = "4900";

	private SipFactory sipFactory;
	private SipStack sipStack;
	private HeaderFactory headerFactory;
	private AddressFactory addressFactory;
	private MessageFactory messageFactory;
	private SipProvider sipProvider;

	private static long cseq = 1L;

	public SipUtil() {
		init();
	}

	public SipUtil(String ip, int port, String targetIp, int targetPort) {
		this.ip = ip;
		this.port = port;
		this.ccsIp = targetIp;
		this.ccsPort = targetPort;
		init();
	}

	private void init() {
		try {
			sipFactory = SipFactory.getInstance();
			sipFactory.setPathName("gov.nist");
			Properties properties = new Properties();
			properties.setProperty("javax.sip.STACK_NAME", "TestClient" + port);
			properties.setProperty("javax.sip.IP_ADDRESS", ip);

			sipStack = sipFactory.createSipStack(properties);
			headerFactory = sipFactory.createHeaderFactory();
			addressFactory = sipFactory.createAddressFactory();
			messageFactory = sipFactory.createMessageFactory();

			ListeningPoint udp = sipStack.createListeningPoint(ip, port, "udp");
			sipProvider = sipStack.createSipProvider(udp);
			sipProvider.addSipListener(new UDPListener());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void sendMessage(byte[] content) throws Exception {
		String fromTag = System.currentTimeMillis() + "";

		SipURI toUri = addressFactory.createSipURI(to, ccsIp + ":" + ccsPort);
		SipURI fromUri = addressFactory.createSipURI(from, ip + ":" + port);
		CallIdHeader callIdHeader = headerFactory.createCallIdHeader(from);
		CSeqHeader cSeqHeader = headerFactory.createCSeqHeader(getNextCseq(),
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
		
		Request request = messageFactory.createRequest(toUri, Request.MESSAGE,
				callIdHeader, cSeqHeader, fromHeader, toHeader, viaList,
				maxForwardsHeader, contentTypeHeader, content);

		Header subject = headerFactory.createHeader("Subject",
				"name=user;session=10050000000000000000");
		request.addHeader(subject);
		ClientTransaction client = sipProvider.getNewClientTransaction(request);
		client.sendRequest();
	}

	synchronized public static long getNextCseq() {
		cseq++;
		return cseq;
	}
}
