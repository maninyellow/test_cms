package com.znsx.util.sip;

import javax.sip.DialogTerminatedEvent;
import javax.sip.IOExceptionEvent;
import javax.sip.RequestEvent;
import javax.sip.ResponseEvent;
import javax.sip.SipListener;
import javax.sip.TimeoutEvent;
import javax.sip.TransactionTerminatedEvent;
import javax.sip.message.Request;
import javax.sip.message.Response;

/**
 * UDPListener
 * @author huangbuji <p />
 * Create at 2013-8-1 下午6:15:23
 */
public class UDPListener implements SipListener {

	/* (non-Javadoc)
	 * @see javax.sip.SipListener#processDialogTerminated(javax.sip.DialogTerminatedEvent)
	 */
	@Override
	public void processDialogTerminated(DialogTerminatedEvent arg0) {
		System.out.println("Dialog terminated !");

	}

	/* (non-Javadoc)
	 * @see javax.sip.SipListener#processIOException(javax.sip.IOExceptionEvent)
	 */
	@Override
	public void processIOException(IOExceptionEvent arg0) {
		System.out.println("IOException !");

	}

	/* (non-Javadoc)
	 * @see javax.sip.SipListener#processRequest(javax.sip.RequestEvent)
	 */
	@Override
	public void processRequest(RequestEvent event) {
		Request request = event.getRequest();
		System.out.println(request.toString());

	}

	/* (non-Javadoc)
	 * @see javax.sip.SipListener#processResponse(javax.sip.ResponseEvent)
	 */
	@Override
	public void processResponse(ResponseEvent event) {
		Response res = event.getResponse();
		System.out.println(res.toString());

	}

	/* (non-Javadoc)
	 * @see javax.sip.SipListener#processTimeout(javax.sip.TimeoutEvent)
	 */
	@Override
	public void processTimeout(TimeoutEvent arg0) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see javax.sip.SipListener#processTransactionTerminated(javax.sip.TransactionTerminatedEvent)
	 */
	@Override
	public void processTransactionTerminated(TransactionTerminatedEvent arg0) {
		// TODO Auto-generated method stub

	}

}
