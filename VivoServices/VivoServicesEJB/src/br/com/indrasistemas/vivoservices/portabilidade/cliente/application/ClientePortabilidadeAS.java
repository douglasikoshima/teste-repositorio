package br.com.indrasistemas.vivoservices.portabilidade.cliente.application;

import javax.ejb.SessionContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import br.com.indrasistemas.framework.foundation.xml.XMLParserException;
import br.com.indrasistemas.framework.service.BusinessException;
import br.com.indrasistemas.framework.service.application.ApplicationServiceException;
import br.com.indrasistemas.framework.service.application.BaseApplicationService;
import br.com.indrasistemas.framework.service.servicegateway.ServiceGatewayException;
import br.com.indrasistemas.framework.service.servicegateway.tuxedo.exception.TuxedoException;
import br.com.indrasistemas.framework.service.to.RequestInfoTO;
import br.com.indrasistemas.vivoservices.portabilidade.cliente.exception.ParametrosInvalidosException;
import br.com.indrasistemas.vivoservices.portabilidade.cliente.servicegateway.tuxedo.portCliente.PortClienteGateway;
import br.com.indrasistemas.vivoservices.portabilidade.cliente.servicegateway.tuxedo.portProcessos.PortProcessosGateway;
import br.com.indrasistemas.vivoservices.portabilidade.cliente.to.DadosTO;
import br.com.indrasistemas.vivoservices.portabilidade.cliente.to.PortabilidadeProcessosTO;
import br.com.indrasistemas.vivoservices.portabilidade.cliente.webservice.to.RespostaManutencaoProcessoTO;
import br.com.indrasistemas.vivoservices.webservice.to.RespostaWSTO;

public class ClientePortabilidadeAS extends BaseApplicationService {

	private static final Log logger = LogFactory
			.getLog(ClientePortabilidadeAS.class);

	public ClientePortabilidadeAS(SessionContext sessionContext) {
		super(sessionContext);
	}

	public RespostaWSTO gravarClientePortabilidade(RequestInfoTO requestInfo,
			DadosTO dados) throws ApplicationServiceException,
			BusinessException {

		if (logger.isInfoEnabled()) {
			logger.info("gravarClientePortabilidade - Método iniciado.");
		}
		RespostaWSTO r = new RespostaWSTO();

		PortClienteGateway portClienteGateway = new PortClienteGateway();
		try {
			r = (RespostaWSTO) portClienteGateway.call(requestInfo, dados);
		} catch (TuxedoException ex) {
			throw new ParametrosInvalidosException(ex);
		} catch (ServiceGatewayException ex) {
			throw new ApplicationServiceException(ex);
		} catch (XMLParserException ex) {
			throw new ApplicationServiceException(ex);
		}

		// r.setReason("Success...");
		// r.setStatus("OK");

		return r;
	}

	// public RespostaWSTO gravarProcessoPortabilidade(RequestInfoTO
	// requestInfo,
	// PortabilidadeProcessosTO dados) throws ApplicationServiceException,
	// BusinessException {
	public RespostaManutencaoProcessoTO gravarProcessoPortabilidade(
			RequestInfoTO requestInfo, PortabilidadeProcessosTO dados)
			throws ApplicationServiceException, BusinessException {

		if (logger.isInfoEnabled()) {
			logger.info("gravarProcessosPortabilidade - Método iniciado.");
		}
		// RespostaWSTO r = new RespostaWSTO();
		RespostaManutencaoProcessoTO r = new RespostaManutencaoProcessoTO();

		// 1 - portin
		// 2 - portout
		// 3 - fraud
		// 4 - winback

		PortProcessosGateway portProcessosGateway = new PortProcessosGateway();
		try {
			// r = (RespostaWSTO) portClienteGateway.call(requestInfo, dados);
			r = (RespostaManutencaoProcessoTO) portProcessosGateway.call(
					requestInfo, dados);
		} catch (TuxedoException ex) {
			throw new ParametrosInvalidosException(ex);
		} catch (ServiceGatewayException ex) {
			throw new ApplicationServiceException(ex);
		} catch (XMLParserException ex) {
			throw new ApplicationServiceException(ex);
		}

		/*
		 * r.setReason("Success..."); r.setStatus("OK");
		 * 
		 * RespostaManutencaoProcessoTO r = new RespostaManutencaoProcessoTO();
		 * 
		 * if (dados.getCdOperacao().intValue() == 1) { if
		 * (dados.getCdEstadoPortabilidade().intValue() == 6 ||
		 * dados.getCdEstadoPortabilidade().intValue() == 16 ||
		 * dados.getCdEstadoPortabilidade().intValue() == 19) {
		 * 
		 * r.setNrProtocolo(new Long("1313131").longValue()); } } else if
		 * (dados.getCdOperacao().intValue() == 2) { if
		 * (dados.getCdEstadoPortabilidade().intValue() == 21) {
		 * r.setNrProtocolo(new Long("1313132").longValue()); } } else if
		 * (dados.getCdOperacao().intValue() == 3) { if
		 * (dados.getCdEstadoPortabilidade().intValue() == 6 ||
		 * dados.getCdEstadoPortabilidade().intValue() == 16 ||
		 * dados.getCdEstadoPortabilidade().intValue() == 19) {
		 * r.setNrProtocolo(new Long("1313133").longValue()); } } else if
		 * (dados.getCdOperacao().intValue() == 4) { if
		 * (dados.getCdEstadoPortabilidade().intValue() == 15 ||
		 * dados.getCdEstadoPortabilidade().intValue() == 19) {
		 * r.setNrProtocolo(new Long("1313134").longValue()); } }
		 */

		return r;
	}
}
