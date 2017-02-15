package br.com.vivo.catalogoPRS.exception;

import javax.xml.rpc.ServiceException;


public class ExecutionServiceException extends ServiceException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private StringBuffer messageRequest;
	private StringBuffer messageResponse;
	
	public ExecutionServiceException(String message, Throwable e, StringBuffer messageRequest, StringBuffer messageResponse) {
		super(message, e);
		this.messageRequest = messageRequest;
		this.messageResponse = messageResponse;
	}

	public ExecutionServiceException(String message, Throwable e) {
		super(message, e);
	}

	public ExecutionServiceException(String message) {
		super(message);
	}

	public StringBuffer getMessageRequest() {
		return messageRequest;
	}

	public void setMessageRequest(StringBuffer messageRequest) {
		this.messageRequest = messageRequest;
	}

	public StringBuffer getMessageResponse() {
		return messageResponse;
	}

	public void setMessageResponse(StringBuffer messageResponse) {
		this.messageResponse = messageResponse;
	}
	
}
