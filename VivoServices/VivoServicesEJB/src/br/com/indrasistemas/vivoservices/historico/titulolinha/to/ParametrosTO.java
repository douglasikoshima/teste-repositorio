package br.com.indrasistemas.vivoservices.historico.titulolinha.to;

import br.com.indrasistemas.framework.service.BaseTransferObject;

public class ParametrosTO extends BaseTransferObject {

    private static final long serialVersionUID = 6196647861993055800L;

	private long cdDDD;
	private long nrTelefone;
	private String nrCPFCNPJ;
	private String primeiroNome;
	private String ultimoNome;
	private String nrRG;
	private boolean buscaCompleta;
	private boolean enderecoPreferencial;

    public ParametrosTO() {
    }

	public long getCdDDD() {
		return cdDDD;
    }

	public void setCdDDD(long cdDDD) {
		this.cdDDD = cdDDD;
	}

	public long getNrTelefone() {
		return nrTelefone;
	}

	public void setNrTelefone(long nrTelefone) {
		this.nrTelefone = nrTelefone;
	}

	public String getNrCPFCNPJ() {
		return nrCPFCNPJ;
	}

	public void setNrCPFCNPJ(String nrCPFCNPJ) {
		this.nrCPFCNPJ = nrCPFCNPJ;
	}

	public String getPrimeiroNome() {
		return primeiroNome;
	}

	public void setPrimeiroNome(String primeiroNome) {
		this.primeiroNome = primeiroNome;
	}

	public String getUltimoNome() {
		return ultimoNome;
	}

	public void setUltimoNome(String ultimoNome) {
		this.ultimoNome = ultimoNome;
	}

	public String getNrRG() {
		return nrRG;
	}

	public void setNrRG(String nrRG) {
		this.nrRG = nrRG;
	}

	public boolean isBuscaCompleta() {
		return buscaCompleta;
	}

	public void setBuscaCompleta(boolean buscaCompleta) {
		this.buscaCompleta = buscaCompleta;
    }

	public boolean isEnderecoPreferencial() {
		return enderecoPreferencial;
	}

	public void setEnderecoPreferencial(boolean enderecoPreferencial) {
		this.enderecoPreferencial = enderecoPreferencial;
	}

}
