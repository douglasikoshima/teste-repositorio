package com.indracompany.catalogo.to;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

/**
 * @author Luiz Pereira
 *
 */
public class OrganizacaoVendaTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public OrganizacaoVendaTO() {}
	public OrganizacaoVendaTO(Integer idOrganizacaoVendas) {
		this.idOrganizacaoVendas = idOrganizacaoVendas; 
	}
	
	private Integer idOrganizacaoVendas;
	private Date dtCriacao;
	private Date dtUltimaAlteracao;
	private String inDisponivel;
	private String nmOrganizacaoVendas;
	private String nmUsuarioAlteracao;
	private String nmUsuarioCriacao;
	private String sgOrganizacaoVendas;

	public Date getDtCriacao() {
		return dtCriacao;
	}

	public void setDtCriacao(Date dtCriacao) {
		this.dtCriacao = dtCriacao;
	}

	public Date getDtUltimaAlteracao() {
		return dtUltimaAlteracao;
	}

	public void setDtUltimaAlteracao(Date dtUltimaAlteracao) {
		this.dtUltimaAlteracao = dtUltimaAlteracao;
	}

	public Integer getIdOrganizacaoVendas() {
		return idOrganizacaoVendas;
	}

	public void setIdOrganizacaoVendas(Integer idOrganizacaoVendas) {
		this.idOrganizacaoVendas = idOrganizacaoVendas;
	}

	public String getInDisponivel() {
		return inDisponivel;
	}

	public void setInDisponivel(String inDisponivel) {
		this.inDisponivel = inDisponivel;
	}

	public String getNmOrganizacaoVendas() {
		return nmOrganizacaoVendas;
	}

	public void setNmOrganizacaoVendas(String nmOrganizacaoVendas) {
		this.nmOrganizacaoVendas = nmOrganizacaoVendas;
	}

	public String getNmUsuarioAlteracao() {
		return nmUsuarioAlteracao;
	}

	public void setNmUsuarioAlteracao(String nmUsuarioAlteracao) {
		this.nmUsuarioAlteracao = nmUsuarioAlteracao;
	}

	public String getNmUsuarioCriacao() {
		return nmUsuarioCriacao;
	}

	public void setNmUsuarioCriacao(String nmUsuarioCriacao) {
		this.nmUsuarioCriacao = nmUsuarioCriacao;
	}

	public String getSgOrganizacaoVendas() {
		return sgOrganizacaoVendas;
	}

	public void setSgOrganizacaoVendas(String sgOrganizacaoVendas) {
		this.sgOrganizacaoVendas = sgOrganizacaoVendas;
	}
	
	@Override
	public String toString() {
		return StringUtils.join(new String[]{"idOrganizacaoVendas: " + this.idOrganizacaoVendas}, ", ");
	}
}
