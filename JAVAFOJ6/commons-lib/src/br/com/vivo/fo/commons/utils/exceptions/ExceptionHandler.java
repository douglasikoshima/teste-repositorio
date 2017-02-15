package br.com.vivo.fo.commons.utils.exceptions;

import br.com.vivo.fo.exception.FOException;
import br.com.vivo.fo.exception.FacadeException;
import br.com.vivo.fo.exception.FrameworkTraceException;
import br.com.vivo.fo.exception.TuxedoException;
import br.com.vivo.fo.exception.TuxedoWarningException;
import br.com.vivo.fo.log.Logger;
import br.com.vivo.fo.xml.XmlHeader;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.Serializable;

public class ExceptionHandler implements Serializable{

	private static final long serialVersionUID = 660382897732221756L;

	private static Logger log = new Logger("start");

	private static final String strController    = "/begin.do";
	private static final String strErroInterno   = "Erro interno. Chame o helpdesk.";
	private static final String strNullException = "Null Exception: ";
	private static final String strNullMessage   = "Null Message: ";


	/**
	 * Constroi um objeto ExceptionContainer contendo informações extraidas de uma Exception.<br/>
	 * Também contém a URI a ser acionada após a visualização da tela de erro.
	 * 
	 * @param Exception e é a exceção a ser exibida na tela de erro.
	 * @param String uri é a URI a ser acionada ao ser pressionado o botão "Fechar" da tela de erro.
	 * 
	 * @see ExceptionContainer
	 * @see /error.jsp
	 *
	 */
	public static ExceptionContainer buildExceptionContainer(Exception e, String uri) {
		String tmp;

		if ((uri == null) || (uri.trim().length() == 0)) {
			tmp = strController;
		} else {
			tmp = uri;
		}
		return getExceptionContainer(e, tmp);
	}

	/**
	 * Método helper chamado por <code>buildExceptionContainer</code>
	 */
	private static ExceptionContainer getExceptionContainer(Exception e, String uri) {

		if (e == null) {
			return null;
		}

		Throwable trace;
		if (e instanceof FrameworkTraceException) {
			trace = e;
		} else {
			trace = new FrameworkTraceException(e);
		}

		log.error("GlobalApp.buildExceptionContainer: [" + getStatusCode(e) + "] ", trace);

		ExceptionContainer ExceptionContainer = new ExceptionContainer();
		ExceptionContainer.setErrorIcon(getErrorIcon(trace));
		ExceptionContainer.setErrorCode(getErrorCode(trace));
		ExceptionContainer.setErrorMessage(getErrorMessage(trace));
		ExceptionContainer.setErrorStack(getErrorStack(trace));
		ExceptionContainer.setUrlToGo(uri);

		return ExceptionContainer;
	}


	/**
	 * Avalia o tipo de Exception e retorna um nome de icone adequado.
	 * 
	 * @param Exception
	 * @return String contendo o icone. Caso a Exception seja null, retorna "erroGlobal.gif".
	 */
	public static String getErrorIcon(Throwable t) {
		Throwable tmp = FrameworkTraceException.findCause(t);
		if (tmp != null) {
			if (tmp instanceof TuxedoWarningException) {
				return "avisoGlobal.gif";
			} else if (tmp instanceof TuxedoException) {
				return "erroTuxedo.gif";
			} else if (tmp instanceof FacadeException) {
				return "erroFacade.gif";
			} else if (tmp instanceof FOException) {
				return "erroFO.gif";
			}
		}

		return "erroGlobal.gif";
	}


	/**
	 * Avalia o tipo de Exception e retorna seu errorCode.
	 * 
	 * @param Exception
	 * @return String contendo o <i>erroCode</i> de seu <i>XmlHeader</i>, caso a exceção
	 *         seja TuxedoException. Em caso contrário, retorna "0000".
	 *         Caso a Exception seja null, retorna "0000".
	 */
	public static String getErrorCode(Throwable t) {
		String code  = ExceptionContainer.DEFAULT_ERROR_CODE;

		Throwable tmp = FrameworkTraceException.findCause(t);
		if (tmp == null) {
			return code;
		}

		if (tmp instanceof TuxedoException) {
			XmlHeader header = ((TuxedoException)tmp).getXmlHeader();
			if (header != null) {
				return header.getError();
			}
		}

		return code;
	}


	/**
	 * Avalia o tipo de Exception e retorna seu statusCode.
	 * 
	 * @param Exception
	 * @return String contendo o <code>statusCode</code> de seu <code>XmlHeader</code>, caso a exceção
	 *         seja TuxedoException. Em caso contrário, retorna "00E0000".
	 *         Caso a Exception seja null, retorna "0000".
	 */
	public static String getStatusCode(Throwable t) {
		String code  = ExceptionContainer.DEFAULT_STATUS_CODE;

		Throwable tmp = FrameworkTraceException.findCause(t);
		if (tmp == null) {
			return code;
		}

		if (tmp instanceof TuxedoException) {
			XmlHeader header = ((TuxedoException)tmp).getXmlHeader();
			if (header != null) {
				return header.getStatusCode();
			}
		}

		return code;
	}


	/**
	 * Retorna a mensagem de uma Exception.
	 * 
	 * @param Exception
	 * @return String contendo a mensagem. Caso a Exception seja null, retorna uma mensagem de erro interno.
	 * 
	 */
	public static String getErrorMessage(Throwable t) {
		Throwable tmp = FrameworkTraceException.findCause(t);
		if (tmp == null) {
			return strNullException+strErroInterno;
		}
		if (tmp.getMessage() != null) {
			return tmp.getMessage();
		} else {
			return strNullMessage+strErroInterno;
		}
	}


	/**
	 * Retorna o stack trace de uma Exception, formatado com newlines.
	 * 
	 * @param Exception
	 * @return String contendo o stack trace formatado. Caso a Exception seja null, retorna um String vazio.
	 * 
	 */
	public static String getErrorStack(Throwable t) {
		Throwable tmp = FrameworkTraceException.findCause(t);
		if (tmp == null) {
			return strNullException+strErroInterno;
		}

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PrintStream ps = new PrintStream(baos);
		tmp.printStackTrace(ps);
		return baos.toString();
	}

}
