/**
 * 
 */
package br.com.indrasistemas.vivoservices.administracaosistema.endereco.webservice.impl;

import br.com.indrasistemas.framework.service.BusinessException;
import br.com.indrasistemas.framework.service.delegate.BusinessDelegateException;
import br.com.indrasistemas.framework.service.valuehandler.ValueList;
import br.com.indrasistemas.vivoservices.administracaosistema.endereco.delegate.PesquisaEnderecoBD;
import br.com.indrasistemas.vivoservices.administracaosistema.endereco.to.PesquisaEnderecoTO;
import br.com.indrasistemas.vivoservices.administracaosistema.endereco.webservice.BuscarCEPWS;
import br.com.indrasistemas.vivoservices.administracaosistema.endereco.webservice.to.ResultadoPesquisaEnderecoTO;
import br.com.indrasistemas.vivoservices.webservice.to.RequestInfoWSTO;
import br.com.indrasistemas.vivoservices.webservice.to.RespostaWSTO;

/**
 * @author a5013566
 * 
 */
public class BuscarCEPWSImpl implements BuscarCEPWS {

	/**
	 * 
	 */
	public BuscarCEPWSImpl() {

	}

	public ResultadoPesquisaEnderecoTO buscarEnderecoPorCEP(RequestInfoWSTO requestInfo, String cep, Integer pagina, Integer qtdeRegistros) {

		ResultadoPesquisaEnderecoTO to = new ResultadoPesquisaEnderecoTO();

		try {

			PesquisaEnderecoTO toPesquisa = new PesquisaEnderecoTO();
			toPesquisa.setCep(cep);

			PesquisaEnderecoBD bd = new PesquisaEnderecoBD();
			ValueList listaEnderecos = bd.buscarEndereco(requestInfo, toPesquisa, pagina, qtdeRegistros);

			to.setStatus(RespostaWSTO.OK);
			to.setEnderecos(listaEnderecos.getList());

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
