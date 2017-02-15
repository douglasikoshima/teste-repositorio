package br.com.vivo.catalogoPRS.pageflows.shared.form;

import org.apache.struts.validator.ValidatorActionForm;

public class AlterarRestricoesAcessoForm extends ValidatorActionForm implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private Long idAcessoPlano;
	private Long idAcessoServico;
	private Long idPerfilOrigem;
	private Long idPerfilDestino;
	private Long idSistema;
	private String inRestricaoConsulta;
	private String inRestricaoAtivacao;
	private String inRestricaoDesativacao;
	
	public Long getIdAcessoPlano() {
		return idAcessoPlano;
	}
	public void setIdAcessoPlano(Long idAcessoPlano) {
		this.idAcessoPlano = idAcessoPlano;
	}
	public Long getIdAcessoServico() {
		return idAcessoServico;
	}
	public void setIdAcessoServico(Long idAcessoServico) {
		this.idAcessoServico = idAcessoServico;
	}
	public String getInRestricaoAtivacao() {
		return inRestricaoAtivacao;
	}
	public void setInRestricaoAtivacao(String inRestricaoAtivacao) {
		this.inRestricaoAtivacao = inRestricaoAtivacao;
	}
	public String getInRestricaoConsulta() {
		return inRestricaoConsulta;
	}
	public void setInRestricaoConsulta(String inRestricaoConsulta) {
		this.inRestricaoConsulta = inRestricaoConsulta;
	}
	public String getInRestricaoDesativacao() {
		return inRestricaoDesativacao;
	}
	public void setInRestricaoDesativacao(String inRestricaoDesativacao) {
		this.inRestricaoDesativacao = inRestricaoDesativacao;
	}
	public Long getIdPerfilDestino() {
		return idPerfilDestino;
	}
	public void setIdPerfilDestino(Long idPerfilDestino) {
		this.idPerfilDestino = idPerfilDestino;
	}
	public Long getIdPerfilOrigem() {
		return idPerfilOrigem;
	}
	public void setIdPerfilOrigem(Long idPerfilOrigem) {
		this.idPerfilOrigem = idPerfilOrigem;
	}
	public Long getIdSistema() {
		return idSistema;
	}
	public void setIdSistema(Long idSistema) {
		this.idSistema = idSistema;
	}
}