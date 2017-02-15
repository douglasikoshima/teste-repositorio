package com.indracompany.actions;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionMapping;

public abstract class AbstractAction extends Action implements Serializable {

	private static final long serialVersionUID = 4787677131022123171L;

	protected static final String SUCCESS = "success";
	protected static final String ERROR = "error";

	protected HttpServletRequest request;
	protected HttpServletResponse response;
	protected ActionMapping mapping;

	public static final String PREFIXO_SAIDA_XML = "<?xml version=\"1.0\" encoding=\"utf-8\" ?><ns:string xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:ns=\"http://www.openuri.org/\">";
	public static final String SUFIXO_SAIDA_XML = "</ns:string>";

}
