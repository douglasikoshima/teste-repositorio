package com.indracompany.catalogo.to;

import java.io.Serializable;

public class PoliticaPrecificacaoTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1867144273689476425L;

	private Long idPoliticaPrecificao;
	
	private String inClasseServicoPrincipal;
	
	private String inEmpresaInstaladora;
	
	private String inEspServicoPacote;

	public Long getIdPoliticaPrecificao() {
		return idPoliticaPrecificao;
	}

	public void setIdPoliticaPrecificao(Long idPoliticaPrecificao) {
		this.idPoliticaPrecificao = idPoliticaPrecificao;
	}

	public String getInClasseServicoPrincipal() {
		return inClasseServicoPrincipal;
	}

	public void setInClasseServicoPrincipal(String inClasseServicoPrincipal) {
		this.inClasseServicoPrincipal = inClasseServicoPrincipal;
	}

	public String getInEmpresaInstaladora() {
		return inEmpresaInstaladora;
	}

	public void setInEmpresaInstaladora(String inEmpresaInstaladora) {
		this.inEmpresaInstaladora = inEmpresaInstaladora;
	}

	public String getInEspServicoPacote() {
		return inEspServicoPacote;
	}

	public void setInEspServicoPacote(String inEspServicoPacote) {
		this.inEspServicoPacote = inEspServicoPacote;
	}	
	
	
}
