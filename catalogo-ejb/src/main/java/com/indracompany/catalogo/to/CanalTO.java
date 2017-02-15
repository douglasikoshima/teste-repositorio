package com.indracompany.catalogo.to;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

/**
 * @author Luiz Pereira
 *
 */
public class CanalTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public CanalTO() {}
	
	public CanalTO(Integer idCanal) {
		this.idCanal = idCanal;
	}
	
	private Integer idCanal;
	private Date dtCriacao;
	private Date dtFinal;
	private Date dtInicial;
	private Date dtUltimaAlteracao;
	private String inDisponivel;
	private String nmCanal;
	private String nmUsuarioAlteracao;
	private String nmUsuarioCriacao;
	private String sgCanal;
	private String sgVivoNet;

	public Date getDtCriacao() {
		return dtCriacao;
	}

	public void setDtCriacao(Date dtCriacao) {
		this.dtCriacao = dtCriacao;
	}

	public Date getDtFinal() {
		return dtFinal;
	}

	public void setDtFinal(Date dtFinal) {
		this.dtFinal = dtFinal;
	}

	public Date getDtInicial() {
		return dtInicial;
	}

	public void setDtInicial(Date dtInicial) {
		this.dtInicial = dtInicial;
	}

	public Date getDtUltimaAlteracao() {
		return dtUltimaAlteracao;
	}

	public void setDtUltimaAlteracao(Date dtUltimaAlteracao) {
		this.dtUltimaAlteracao = dtUltimaAlteracao;
	}

	public Integer getIdCanal() {
		return idCanal;
	}

	public void setIdCanal(Integer idCanal) {
		this.idCanal = idCanal;
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

	public String getSgCanal() {
		return sgCanal;
	}

	public void setSgCanal(String sgCanal) {
		this.sgCanal = sgCanal;
	}

	public String getSgVivoNet() {
		return sgVivoNet;
	}

	public void setSgVivoNet(String sgVivoNet) {
		this.sgVivoNet = sgVivoNet;
	}

	@Override
	public String toString() {
		return StringUtils.join(new String[]{"idCanal: " + this.idCanal, "nmCanal: " + this.nmCanal}, ", ");
	}
}
