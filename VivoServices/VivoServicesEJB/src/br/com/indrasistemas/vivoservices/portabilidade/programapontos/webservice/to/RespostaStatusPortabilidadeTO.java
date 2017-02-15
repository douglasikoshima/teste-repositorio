package br.com.indrasistemas.vivoservices.portabilidade.programapontos.webservice.to;

import br.com.indrasistemas.vivoservices.webservice.to.RespostaWSTO;

public class RespostaStatusPortabilidadeTO extends RespostaWSTO {

	public RespostaStatusPortabilidadeTO() {
	}

	private static final long serialVersionUID = -17470621978845386L;

	private int inAcao;
	private String dsAcao;
	private String dsEstado; 

	public int getInAcao() {
		return this.inAcao;
	}

	public void setInAcao(int inStatus) {
		this.inAcao = inStatus;
	}

	public String getDsAcao() {
		return this.dsAcao;
	}

	public void setDsAcao(String dsAcao) {
		this.dsAcao = dsAcao;
	}

	public String getDsEstado() {
		return dsEstado;
	}

	public void setDsEstado(String dsEstado) {
		this.dsEstado = dsEstado;
	}

}
