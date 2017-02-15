package br.com.indrasistemas.vivoservices.portabilidade.status.webservice.to;

public class RespostaWSTO extends br.com.indrasistemas.vivoservices.webservice.to.RespostaWSTO {

	private static final long serialVersionUID = 1L;

	private String statusLinha = "";
	private String tpDocumento = "";
	private String nrDocumento = "";
	private String tpLinha = "";

	public String getStatusLinha() {
		return statusLinha;
	}

	public void setStatusLinha(String statusLinha) {
		this.statusLinha = statusLinha;
	}

	public String getTpDocumento() {
		return tpDocumento;
	}

	public void setTpDocumento(String tpDocumento) {
		this.tpDocumento = tpDocumento;
	}

	public String getNrDocumento() {
		return nrDocumento;
	}

	public void setNrDocumento(String nrDocumento) {
		this.nrDocumento = nrDocumento;
	}

	public String getTpLinha() {
		return tpLinha;
	}

	public void setTpLinha(String tpLinha) {
		this.tpLinha = tpLinha;
	}
	
}
