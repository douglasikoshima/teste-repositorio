package com.indracompany.catalogo.to;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

/**
 * @author Luiz Pereira
 *
 */
public class CanalAtendimentoTO implements Serializable, Comparable<CanalAtendimentoTO> {

	private static final long serialVersionUID = 1L;
	
	public CanalAtendimentoTO() {}
	
	public CanalAtendimentoTO(Integer idCanalAtendimento) {
		this.idCanalAtendimento = idCanalAtendimento;
	}
	
	private Integer idCanalAtendimento;
	private Date dtCriacao;
	private Date dtUltimaAlteracao;
	private String inDisponivel;
	private String nmCanal;
	private String nmUsuarioAlteracao;
	private String nmUsuarioCriacao;
	private String sgVivoNet;
	
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
	public Integer getIdCanalAtendimento() {
		return idCanalAtendimento;
	}
	public void setIdCanalAtendimento(Integer idCanalAtendimento) {
		this.idCanalAtendimento = idCanalAtendimento;
	}
	public String getInDisponivel() {
		return inDisponivel;
	}
	public void setInDisponivel(String inDisponivel) {
		this.inDisponivel = inDisponivel;
	}
	public String getNmCanal() {
		return nmCanal;
	}
	public void setNmCanal(String nmCanal) {
		this.nmCanal = nmCanal;
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
	public String getSgVivoNet() {
		return sgVivoNet;
	}
	public void setSgVivoNet(String sgVivoNet) {
		this.sgVivoNet = sgVivoNet;
	}
	
	@Override
	public String toString() {
		return StringUtils.join(new String[]{"idCanalAtendimento: " + this.idCanalAtendimento, "nmCanal: " + this.nmCanal}, ", ");
	}

	@Override
	public int compareTo(CanalAtendimentoTO o) {
		return this.getNmCanal().compareToIgnoreCase(o.getNmCanal());
	}
}
