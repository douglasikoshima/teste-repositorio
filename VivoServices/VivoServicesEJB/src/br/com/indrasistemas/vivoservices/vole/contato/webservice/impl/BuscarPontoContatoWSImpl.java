/**
 * 
 */
package br.com.indrasistemas.vivoservices.vole.contato.webservice.impl;

import java.util.List;

import br.com.indrasistemas.framework.service.BusinessException;
import br.com.indrasistemas.framework.service.delegate.BusinessDelegateException;
import br.com.indrasistemas.vivoservices.vole.contato.delegate.PesquisaPontoContatoBD;
import br.com.indrasistemas.vivoservices.vole.contato.to.PontoContatoTO;
import br.com.indrasistemas.vivoservices.vole.contato.webservice.BuscarPontoContatoWS;
import br.com.indrasistemas.vivoservices.vole.contato.webservice.to.ResultadoPontoContatoTO;
import br.com.indrasistemas.vivoservices.webservice.to.RequestInfoWSTO;
import br.com.indrasistemas.vivoservices.webservice.to.RespostaWSTO;

/**
 * @author a5019660
 * 
 */
public class BuscarPontoContatoWSImpl implements BuscarPontoContatoWS {

	/**
	 * 
	 */
	public BuscarPontoContatoWSImpl() {

	}

	public ResultadoPontoContatoTO buscarPontoContatoPorCNPJ(
			RequestInfoWSTO requestInfo, String cnpj, String cdConta,
			Integer pagina, Integer qtdeRegistros) {

		ResultadoPontoContatoTO to = new ResultadoPontoContatoTO();

		try {

			PontoContatoTO toPesquisa = new PontoContatoTO();
			toPesquisa.setNrCnpj(cnpj);
			toPesquisa.setCdConta(cdConta);

			PesquisaPontoContatoBD bd = new PesquisaPontoContatoBD();
			List listaPontoContatos = bd.buscarPontoContato(requestInfo,
					toPesquisa, pagina, qtdeRegistros);
			to.setStatus(RespostaWSTO.OK);
			to.setPontoContato(listaPontoContatos);

		} catch (BusinessDelegateException e) {
			to.setStatus(RespostaWSTO.NAO_OK);
			to.setReason(e.getMessage());

		} catch (BusinessException e) {
			to.setStatus(RespostaWSTO.NAO_OK);
			to.setReason(e.getMessage());
		}

		return to;
	}

}
