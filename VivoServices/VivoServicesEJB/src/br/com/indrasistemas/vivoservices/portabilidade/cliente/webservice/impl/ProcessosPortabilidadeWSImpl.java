package br.com.indrasistemas.vivoservices.portabilidade.cliente.webservice.impl;

import br.com.indrasistemas.framework.service.to.RequestInfoTO;
import br.com.indrasistemas.vivoservices.portabilidade.cliente.delegate.ClientePortabilidadeBD;
import br.com.indrasistemas.vivoservices.portabilidade.cliente.to.PortabilidadeProcessosTO;
import br.com.indrasistemas.vivoservices.portabilidade.cliente.webservice.ProcessosPortabilidadeWS;
import br.com.indrasistemas.vivoservices.portabilidade.cliente.webservice.to.RespostaManutencaoProcessoTO;

public class ProcessosPortabilidadeWSImpl implements ProcessosPortabilidadeWS {

	public ProcessosPortabilidadeWSImpl() {

	}

	public RespostaManutencaoProcessoTO manutencaoProcessosPortabilidade(
			RequestInfoTO requestInfo, Integer cdAreaRegistro, Long nrLinha,
			String dtJanela, Integer cdOperacao, Integer estadoPortabilidade,
			Integer cdOperadoraSolicitante, String dsComentarioProcesso,
			Long nrProcesso, Long nrBilhetePortabilidade, Integer cdProcedencia) {

		RespostaManutencaoProcessoTO to = new RespostaManutencaoProcessoTO();

		PortabilidadeProcessosTO dados = new PortabilidadeProcessosTO();
		dados.setCdAreaRegistro(cdAreaRegistro);
		dados.setNrLinha(nrLinha);
		dados.setDtJanela(dtJanela);
		dados.setCdOperacao(cdOperacao);
		dados.setEstadoPortabilidade(estadoPortabilidade);
		dados.setCdOperadoraSolicitante(cdOperadoraSolicitante);
		dados.setDsComentarioProcesso(dsComentarioProcesso);
		dados.setNrProcesso(nrProcesso);
		dados.setNrBilhetePortabilidade(nrBilhetePortabilidade);
		dados.setCdProcedencia(cdProcedencia);

		try {
			ClientePortabilidadeBD bd = new ClientePortabilidadeBD();
			to = bd.gravarProcessoPortabilidade(requestInfo, dados);

		} catch (Exception e) {
			to.setStatus("NOK");
			to.setReason(e.getMessage());
		}

		return to;
	}

}