package br.com.indrasistemas.framework.foundation.servicegateway.tuxedo.ejb;

import weblogic.wtc.gwt.TuxedoConnection;
import weblogic.wtc.gwt.TuxedoConnectionFactory;
import weblogic.wtc.jatmi.Reply;
import weblogic.wtc.jatmi.TPException;
import weblogic.wtc.jatmi.TPReplyException;
import weblogic.wtc.jatmi.TypedBuffer;
import weblogic.wtc.jatmi.TypedString;

/**
 * Classe responsável pela interação com a API Weblogic (WTC) para chamada de
 * serviços escritos em TUXEDO.
 * 
 * O nome para obtenção da connection factory via JNDI é definido através da variável JNDI_TUX_CONNECTION.
 * 
 * @author C_LBraga
 * 
 */
class TuxedoServiceCaller {

	private static final String JNDI_TUX_CONNECTION = "tuxedo.services.TuxedoConnection";

	private TuxedoConnection conn = null;

	public TuxedoServiceCaller() throws TuxedoServiceCallerException {
		super();
		try {
			this.conn = getConnection();
		} catch (Exception ex) {
			throw new TuxedoServiceCallerException(ex);
		}
	}

	public void close() {
		if (this.conn == null) {
			return;
		}
		closeTuxedoConnection(this.conn);
		this.conn = null;
	}

	protected void finalize() throws Throwable {
		super.finalize();
		close();
	}

	public String call(String serviceName, String data)
			throws TuxedoServiceCallerException {
		String result = null;
		try {
			/**
			 * Os serviços do TUXEDO implementados pela INDRA precisam do encode "ISO-8859-1" e
			 * o que foi gerado é "UTF-8"
			 */
			data  = changeEncoding(data);
			TypedString typedData = new TypedString(data);
			Reply reply = this.conn.tpcall(serviceName, typedData, 0);
			result = (reply != null ? reply.getReplyBuffer().toString() : null);
		} catch (TPReplyException ex) {
			try {
				TypedBuffer typedDataErr = ex.getExceptionReply()
						.getReplyBuffer();
				result = typedDataErr.toString();
			} catch (RuntimeException rex) {
				// Não deve ocorrer.
			}
			if (result == null) {
				throw new TuxedoServiceCallerException(ex);
			}
		} catch (TPException ex) {
			throw new TuxedoServiceCallerException(ex);
		}
		/**
		 * O resultado da execução do serviço, um objeto String com um documento
		 * no formato XML, deve ser limpo para que o documento final respeite o
		 * padrão XML/XSD quanto à definição de namespaces. Verificou-se que
		 * alguns serviços incluem uma referência ao namespace ('xmlns=...') em
		 * elementos que não são a raiz do documento, fazendo com que o
		 * documento seja imcompatível com um XSD que defina o documento como um
		 * todo (header + body). O método cleanXML é usado para retirar qualquer
		 * atributo com o nome 'xmlns' do documento, fazendo com que o mesmo não
		 * possua definição de namespace. Isso implica em que o XSD utilizado em
		 * desenvolvimento, para a geração de classes JAXB por exemplo, não
		 * possa definir namespaces.
		 */
		result = this.cleanXML(result);
		return result;
	}

	private String changeEncoding(String xml) {
		return xml.replaceAll("UTF-8", "ISO-8859-1");
	}

	/**
	 * Retorna uma conexão ao Tuxedo.
	 * 
	 * @return Um objeto do tipo TuxedoConnection
	 * @throws TuxedoServiceCallerException
	 * @throws TPException
	 */
	private TuxedoConnection getConnection()
			throws TuxedoServiceCallerException, TPException {
		TuxedoConnectionFactory tuxConFactory = null;
		TuxedoServiceLocator sLocator = TuxedoServiceLocator.getInstance();
		tuxConFactory = sLocator
				.getTuxedoConnectionFactory(TuxedoServiceCaller.JNDI_TUX_CONNECTION);
		return tuxConFactory.getTuxedoConnection();
	}

	/**
	 * Fecha uma conexão para o Tuxedo.
	 * 
	 * @param conn
	 *            Um objeto do tipo TuxedoConnection
	 */
	private void closeTuxedoConnection(TuxedoConnection conn) {
		try {
			conn.tpterm();
		} catch (Exception ex) {
		}
	}

	/**
	 * 'Limpa' um objeto String que contenha um documento XML retirando todas
	 * ocorrências do atributo "xmlns" que sejam encontradas em qualquer tag.
	 * 
	 * @param xml
	 *            O objeto String com o documento no formato XML.
	 * @return
	 */
	private String cleanXML(String xml) {
		int beginXmlnsIndex = 0;
		int firstAspasIndex = 0;
		int secondAspasIndex = 0;
		int endXmlnsIndex = 0;

		try {
			do {
				beginXmlnsIndex = xml.indexOf("xmlns");
				if (beginXmlnsIndex == 0) {
					break;
				}
				firstAspasIndex = xml.indexOf("\"", beginXmlnsIndex);
				secondAspasIndex = xml.indexOf("\"", firstAspasIndex + 1);
				endXmlnsIndex = xml.length();

				xml = new StringBuffer(endXmlnsIndex).append(
						xml.substring(0, beginXmlnsIndex)).append(
						xml.substring(secondAspasIndex + 1, endXmlnsIndex))
						.toString();
			} while (true);
		} catch (RuntimeException ex) {
			// Não deve ocorrer.
		}
		return xml;
	}

}
