package br.com.indrasistemas.vivoservices.portabilidade.cliente.webservice.to;

public class TelefoneTO {

	public TelefoneTO() {
	}

	private static final long serialVersionUID = -25487197893232238L;

	private String dsTipoTelefone;
	private long nrTelefone;
	private String nmContato;

	public String getDsTipoTelefone() {
		return dsTipoTelefone;
	}

	public void setDsTipoTelefone(String dsTipoTelefone) {
		this.dsTipoTelefone = dsTipoTelefone;
	}

	public long getNrTelefone() {
		return nrTelefone;
	}

	public void setNrTelefone(long nrTelefone) {
		this.nrTelefone = nrTelefone;
	}

	public String getNmContato() {
		return nmContato;
	}

	public void setNmContato(String nmContato) {
		this.nmContato = nmContato;
	}

}
