package com.indracompany.catalogo.to;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

/**
 * @author Luiz Pereira
 *
 */
public class AcaoTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public AcaoTO() {}
	
	public AcaoTO(Integer idAcao) {
		this.idAcao = idAcao;
	}
	
	private Integer idAcao;
	private String nmAcao;
	private String sgAcao;
	private String dsAcao;
	private String inDisponivel;
	private Date dtCriacao;
	private String nmUsuarioCriacao;
	private Date dtAlteracao;
	private String nmUsuarioAlteracao;
	private CanalAtendimentoTO canalAtendimentoTO;
	private Date dtInicial;
	private Date dtFinal;
	private String indAnaliseFraude;
	private Integer[] fornecedorList;
	private Integer[] perfilList;
	private String isVigente;

	public String getIsVigente() {
		return isVigente;
	}

	public void setIsVigente(String isVigente) {
		this.isVigente = isVigente;
	}

	public String getDsAcao() {
		return dsAcao;
	}

	public Integer[] getFornecedorList() {
		return fornecedorList;
	}

	public void setFornecedorList(Integer[] fornecedorList) {
		this.fornecedorList = fornecedorList;
	}

	public Integer[] getPerfilList() {
		return perfilList;
	}

	public void setPerfilList(Integer[] perfilList) {
		this.perfilList = perfilList;
	}

	public void setDsAcao(String dsAcao) {
		this.dsAcao = dsAcao;
	}

	public Date getDtAlteracao() {
		return dtAlteracao;
	}

	public void setDtAlteracao(Date dtAlteracao) {
		this.dtAlteracao = dtAlteracao;
	}

	public Date getDtCriacao() {
		return dtCriacao;
	}

	public void setDtCriacao(Date dtCriacao) {
		this.dtCriacao = dtCriacao;
	}

	public Integer getIdAcao() {
		return idAcao;
	}

	public void setIdAcao(Integer idAcao) {
		this.idAcao = idAcao;
	}

	public String getInDisponivel() {
		return inDisponivel;
	}

	public void setInDisponivel(String inDisponivel) {
		this.inDisponivel = inDisponivel;
	}

	public String getNmAcao() {
		return nmAcao;
	}

	public void setNmAcao(String nmAcao) {
		this.nmAcao = nmAcao;
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

	public String getSgAcao() {
		return sgAcao;
	}

	public void setSgAcao(String sgAcao) {
		this.sgAcao = sgAcao;
	}
	
	@Override
	public String toString() {
		return StringUtils.join(new String[]{"idAcao: " + this.idAcao, "nmAcao: " + this.nmAcao}, ", ");
	}

	public CanalAtendimentoTO getCanalAtendimentoTO() {
		return canalAtendimentoTO;
	}

	public void setCanalAtendimentoTO(CanalAtendimentoTO canalAtendimentoTO) {
		this.canalAtendimentoTO = canalAtendimentoTO;
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

	public String getIndAnaliseFraude() {
		return indAnaliseFraude;
	}

	public void setIndAnaliseFraude(String indAnaliseFraude) {
		this.indAnaliseFraude = indAnaliseFraude;
	}
}
