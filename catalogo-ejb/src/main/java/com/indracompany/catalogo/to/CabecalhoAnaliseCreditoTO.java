package com.indracompany.catalogo.to;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

/**
 * @author Equipe Catalogo
 *
 */
public class CabecalhoAnaliseCreditoTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public CabecalhoAnaliseCreditoTO() {}
	
	public CabecalhoAnaliseCreditoTO(Integer idCabecalhoAnaliseCredito) {
		this.idCabecalhoAnaliseCredito = idCabecalhoAnaliseCredito;
	}

	private Integer idCabecalhoAnaliseCredito;
	private Date dtCriacao;
	private Date dtUltimaAlteracao;
	private String inDisponivel;
	private String nmCabecalhoAnaliseCredito;
	private String nmUsuarioAlteracao;
	private String nmUsuarioCriacao;
	private boolean isRegional;
	private CanalAtendimentoTO canalAtendimento;
	private List<OfServicoConfiguracaoScoreTO> ofServicoConfiguracaoScoreList;
	private List<PlanoConfiguracaoScoreTO> planoConfiguracaoScoreList;
	private List<ServicoConfiguracaoScoreTO> servicoConfiguracaoScoreList;	
	private List<OfServicoConfiguracaoScoreTO> ofServicoConfiguracaoScoreListRemove;
	private List<PlanoConfiguracaoScoreTO> planoConfiguracaoScoreListRemove;
	private List<ServicoConfiguracaoScoreTO> servicoConfiguracaoScoreListRemove;	
	
	public CanalAtendimentoTO getCanalAtendimento() {
		return canalAtendimento;
	}

	public void setCanalAtendimento(CanalAtendimentoTO canalAtendimento) {
		this.canalAtendimento = canalAtendimento;
	}

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

	public Integer getIdCabecalhoAnaliseCredito() {
		return idCabecalhoAnaliseCredito;
	}

	public void setIdCabecalhoAnaliseCredito(Integer idCabecalhoAnaliseCredito) {
		this.idCabecalhoAnaliseCredito = idCabecalhoAnaliseCredito;
	}

	public String getInDisponivel() {
		return inDisponivel;
	}

	public void setInDisponivel(String inDisponivel) {
		this.inDisponivel = inDisponivel;
	}

	public String getNmCabecalhoAnaliseCredito() {
		return nmCabecalhoAnaliseCredito;
	}

	public void setNmCabecalhoAnaliseCredito(String nmCabecalhoAnaliseCredito) {
		this.nmCabecalhoAnaliseCredito = nmCabecalhoAnaliseCredito;
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

	public List<OfServicoConfiguracaoScoreTO> getOfServicoConfiguracaoScoreList() {
		return ofServicoConfiguracaoScoreList;
	}

	public void setOfServicoConfiguracaoScoreList(
			List<OfServicoConfiguracaoScoreTO> ofServicoConfiguracaoScoreList) {
		this.ofServicoConfiguracaoScoreList = ofServicoConfiguracaoScoreList;
	}

	public List<PlanoConfiguracaoScoreTO> getPlanoConfiguracaoScoreList() {
		return planoConfiguracaoScoreList;
	}

	public void setPlanoConfiguracaoScoreList(
			List<PlanoConfiguracaoScoreTO> planoConfiguracaoScoreList) {
		this.planoConfiguracaoScoreList = planoConfiguracaoScoreList;
	}

	public List<ServicoConfiguracaoScoreTO> getServicoConfiguracaoScoreList() {
		return servicoConfiguracaoScoreList;
	}

	public void setServicoConfiguracaoScoreList(
			List<ServicoConfiguracaoScoreTO> servicoConfiguracaoScoreList) {
		this.servicoConfiguracaoScoreList = servicoConfiguracaoScoreList;
	}
	
	public List<OfServicoConfiguracaoScoreTO> getOfServicoConfiguracaoScoreListRemove() {
		return ofServicoConfiguracaoScoreListRemove;
	}

	public void setOfServicoConfiguracaoScoreListRemove(
			List<OfServicoConfiguracaoScoreTO> ofServicoConfiguracaoScoreListRemove) {
		this.ofServicoConfiguracaoScoreListRemove = ofServicoConfiguracaoScoreListRemove;
	}

	public List<PlanoConfiguracaoScoreTO> getPlanoConfiguracaoScoreListRemove() {
		return planoConfiguracaoScoreListRemove;
	}

	public void setPlanoConfiguracaoScoreListRemove(
			List<PlanoConfiguracaoScoreTO> planoConfiguracaoScoreListRemove) {
		this.planoConfiguracaoScoreListRemove = planoConfiguracaoScoreListRemove;
	}

	public List<ServicoConfiguracaoScoreTO> getServicoConfiguracaoScoreListRemove() {
		return servicoConfiguracaoScoreListRemove;
	}

	public void setServicoConfiguracaoScoreListRemove(
			List<ServicoConfiguracaoScoreTO> servicoConfiguracaoScoreListRemove) {
		this.servicoConfiguracaoScoreListRemove = servicoConfiguracaoScoreListRemove;
	}
	
	
	@Override
	public String toString() {	
		return StringUtils.join(new String[]{
				"idCabecalhoAnaliseCredito: " + this.idCabecalhoAnaliseCredito, 
				"nmCabecalhoAnaliseCredito: " + this.nmCabecalhoAnaliseCredito				
				}, ", ");
	}

	public boolean isRegional() {
		return isRegional;
	}

	public void setRegional(boolean isRegional) {
		this.isRegional = isRegional;
	}
}
