package br.com.indrasistemas.vivoservices.portabilidade.cliente.webservice.impl;

import br.com.indrasistemas.framework.service.to.RequestInfoTO;
import br.com.indrasistemas.vivoservices.portabilidade.cliente.delegate.ClientePortabilidadeBD;
import br.com.indrasistemas.vivoservices.portabilidade.cliente.exception.ParametrosInvalidosException;
import br.com.indrasistemas.vivoservices.portabilidade.cliente.to.DadosPFTO;
import br.com.indrasistemas.vivoservices.portabilidade.cliente.to.DadosPJTO;
import br.com.indrasistemas.vivoservices.portabilidade.cliente.to.DadosTO;
import br.com.indrasistemas.vivoservices.portabilidade.cliente.webservice.ClientePortabilidadeWS;
import br.com.indrasistemas.vivoservices.portabilidade.cliente.webservice.to.ClientePFTO;
import br.com.indrasistemas.vivoservices.portabilidade.cliente.webservice.to.ClientePJTO;
import br.com.indrasistemas.vivoservices.webservice.to.RespostaWSTO;

public class ClientePortabilidadeWSImpl implements ClientePortabilidadeWS {

	public ClientePortabilidadeWSImpl() {

	}

	public RespostaWSTO manutencaoClientePortabilidade(
			RequestInfoTO requestInfo, int tpOperacao, String sgTipoPessoa,
			long nrLinha, String sgTipoLinha, long idPessoa,
			ClientePFTO clientePF, ClientePJTO clientePJ) {

		RespostaWSTO to = new RespostaWSTO();

		try {

			DadosTO dados = new DadosTO();
			dados.setTpOperacao(tpOperacao);
			dados.setSgTipoPessoa(sgTipoPessoa);
			dados.setNrLinha(nrLinha);
			dados.setSgTipoLinha(sgTipoLinha);
			dados.setIdPessoa(idPessoa);

			DadosPFTO dadosPF = null;
			DadosPJTO dadosPJ = null;

			if (sgTipoPessoa.equals("PF")) {
				dadosPF = new DadosPFTO(clientePF.getNmPessoa(), clientePF
						.getSgSexo(), clientePF.getDtNascimento(), clientePF
						.getSgEstadoCivil(), clientePF.getNrCPR(), clientePF
						.getTelefone(), clientePF.getDocumento(), clientePF
						.getEndereco());

			} else if (sgTipoPessoa.equals("PJ")) {
				dadosPJ = new DadosPJTO(clientePJ.getNmRazaoSocial(), clientePJ
						.getNmFantasia(), clientePJ.getDtFundacao(), clientePJ
						.getSgClassTributaria(), clientePJ.getSgClassEmpresa(),
						clientePJ.getNrCNPJ(), clientePJ.getNrCNAE(), clientePJ
								.getNrCCM(), clientePJ.getNrInscricao(),
						clientePJ.getSgTipoInscricao(),
						clientePJ.getTelefone(), clientePJ.getEndereco());
			}

			dados.setDadosPF(dadosPF);
			dados.setDadosPJ(dadosPJ);

			ClientePortabilidadeBD bd = new ClientePortabilidadeBD();
			to = bd.gravarClientePortabilidade(requestInfo, dados);

		} catch (ParametrosInvalidosException e) {
			to.setStatus("NOK");
			to.setReason(e.getMessage());
		} catch (Exception e) {
			to.setStatus("NOK");
			to.setReason(e.getMessage());
		}

		return to;
	}
}