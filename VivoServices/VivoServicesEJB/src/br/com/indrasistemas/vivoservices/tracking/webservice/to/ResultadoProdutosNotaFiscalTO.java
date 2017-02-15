package br.com.indrasistemas.vivoservices.tracking.webservice.to;

import java.util.List;

import br.com.indrasistemas.vivoservices.webservice.to.RespostaWSTO;

public class ResultadoProdutosNotaFiscalTO extends RespostaWSTO {

	/**
	 * 
	 */
	public ResultadoProdutosNotaFiscalTO() {

	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -1747032197896232386L;

	private List listaProdutosNotaFiscal;

	public List getListaProdutosNotaFiscal() {
		return listaProdutosNotaFiscal;
	}

	public void setProdutosNotaFiscal(List listaProdutosNotaFiscalTO) {
		this.listaProdutosNotaFiscal = listaProdutosNotaFiscalTO;
	}

}
