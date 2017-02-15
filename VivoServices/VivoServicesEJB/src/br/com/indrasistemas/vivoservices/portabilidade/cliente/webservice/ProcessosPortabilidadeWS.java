package br.com.indrasistemas.vivoservices.portabilidade.cliente.webservice;

import br.com.indrasistemas.framework.service.to.RequestInfoTO;
import br.com.indrasistemas.vivoservices.portabilidade.cliente.webservice.to.RespostaManutencaoProcessoTO;

public interface ProcessosPortabilidadeWS {

	public RespostaManutencaoProcessoTO manutencaoProcessosPortabilidade(
			RequestInfoTO requestInfo, Integer cdAreaRegistro, Long nrLinha,
			String dtJanela, Integer cdOperacao, Integer estadoPortabilidade,
			Integer cdOperadoraSolicitante, String dsComentarioProcesso,
			Long nrProcesso, Long nrBilhetePortabilidade, Integer cdProcedencia);

}