package br.com.indrasistemas.vivoservices.historico.titulolinha.webservice.to;

public class TitularidadeLinhaParametrosWSTO {

	private static final long serialVersionUID = -249418066595009901L;

	private int cdRetorno;
	private int inPreferencial;
	private long cdDDD;
	private long nrTelefone;
	private String primeiroNome;
	private String ultimoNome;
	private String nrRG;
	private String nrCPFCNPJ;

	public int getCdRetorno() {
		return cdRetorno;
	}

	public void setCdRetorno(int cdRetorno) {
		this.cdRetorno = cdRetorno;
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

	public String getNrCPFCNPJ() {
		return nrCPFCNPJ;
	}

	public void setNrCPFCNPJ(String nrCPFCNPJ) {
		this.nrCPFCNPJ = nrCPFCNPJ;
	}

	public int getInPreferencial() {
		return inPreferencial;
	}

	public void setInPreferencial(int inPreferencial) {
		this.inPreferencial = inPreferencial;
	}

}