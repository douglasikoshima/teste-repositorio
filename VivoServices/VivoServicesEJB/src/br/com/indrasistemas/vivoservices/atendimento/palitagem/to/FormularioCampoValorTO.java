package br.com.indrasistemas.vivoservices.atendimento.palitagem.to;

import br.com.indrasistemas.framework.service.BaseTransferObject;

public class FormularioCampoValorTO extends BaseTransferObject {

	private static final long serialVersionUID = -200127418937815L;

	private long idFormularioCampoValor;
	private String valor;

	public long getIdFormularioCampoValor() {
		return idFormularioCampoValor;
	}

	public void setIdFormularioCampoValor(long idFormularioCampoValor) {
		this.idFormularioCampoValor = idFormularioCampoValor;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
	    if ( valor != null )
	        this.valor = valor;
	}
}
