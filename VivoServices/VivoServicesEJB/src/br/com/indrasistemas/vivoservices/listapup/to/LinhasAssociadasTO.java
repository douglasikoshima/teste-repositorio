package br.com.indrasistemas.vivoservices.listapup.to;

import br.com.indrasistemas.framework.service.BaseTransferObject;

public class LinhasAssociadasTO extends BaseTransferObject {

	public LinhasAssociadasTO() {
	}

	private static final long serialVersionUID = -1307122197890032386L;

	private String nrTelefone;

	private String cdConta;

	public String getCdConta() {
		return cdConta;
	}

	public void setCdConta(String cdConta) {
		this.cdConta = cdConta;
	}

	public String getNrTelefone() {
		return nrTelefone;
	}

	public void setNrTelefone(String nrTelefone) {
		this.nrTelefone = nrTelefone;
	}

}
