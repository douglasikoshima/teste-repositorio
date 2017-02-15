package br.com.indrasistemas.vivoservices.listapup.to;

import br.com.indrasistemas.framework.service.BaseTransferObject;

public class PermissaoTO extends BaseTransferObject {

	public PermissaoTO() {
	}

	private static final long serialVersionUID = -330122197890032385L;

	private String cdPermissao;

	private String cdPath;

	private String nmPath;

	private Integer inAceitacao;

	public Integer getInAceitacao() {
		return inAceitacao;
	}

	public void setInAceitacao(Integer inAceitacao) {
		this.inAceitacao = inAceitacao;
	}

	public String getNmPath() {
		return nmPath;
	}

	public void setNmPath(String nmPath) {
		this.nmPath = nmPath;
	}

	public String getCdPermissao() {
		return cdPermissao;
	}

	public void setCdPermissao(String cdPermissao) {
		this.cdPermissao = cdPermissao;
	}

	public String getCdPath() {
		return cdPath;
	}

	public void setCdPath(String cdPath) {
		this.cdPath = cdPath;
	}
	
	public String toString() {
		return cdPermissao != null ? cdPermissao : "";
	}

}