package com.indracompany.catalogo.to;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

/**
 * @author Luiz Pereira
 *
 */
public class PlanoConfiguracaoScoreTO extends ConfiguracaoAnaliseCreditoTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer idPlanoConfiguracaoScore;

	private Date dtCriacao;

	private String nmUsuarioCriacao;

	private PlanoCategoriaScoreTO planoCategoriaScoreTO;
	
	private AnaliseCreditoTO analiseCreditoTO;

	private CabecalhoAnaliseCreditoTO cabecalhoAnaliseCreditoTO;
	
	private RegionalTO regionalTO;

	public PlanoConfiguracaoScoreTO() {}
	
	public PlanoConfiguracaoScoreTO(Integer idCabecalhoAnaliseCredito, Integer idPlanoCategoriaScore, Integer IdAnaliseCredito, Integer idRegional){
		this.cabecalhoAnaliseCreditoTO = new CabecalhoAnaliseCreditoTO(idCabecalhoAnaliseCredito);
		this.planoCategoriaScoreTO = new PlanoCategoriaScoreTO(idPlanoCategoriaScore, null);
		this.analiseCreditoTO = new AnaliseCreditoTO(IdAnaliseCredito);
		this.regionalTO = new RegionalTO(idRegional);
	}

	/*
	public PlanoConfiguracaoScoreTO(String chaveConfiguracaoScore){
		String[] ids = chaveConfiguracaoScore.split(SEPARADOR_CHAVE);
		if(ids.length != 5){
			throw new RuntimeException("Chave inválida");
		} else {
			this.cabecalhoAnaliseCreditoTO = new CabecalhoAnaliseCreditoTO(Integer.parseInt(ids[1]));
			this.planoCategoriaScoreTO = new PlanoCategoriaScoreTO(Integer.parseInt(ids[2]), null);
			this.analiseCreditoTO = new AnaliseCreditoTO(Integer.parseInt(ids[3]));
			if(Integer.parseInt(ids[4]) != 0){
				this.regionalTO = new RegionalTO(Integer.parseInt(ids[4]));
			}
		}
	}
	*/
	/*
	public String getChaveConfiguracaoScore(Integer idCabecalhoAnaliseCredito, Integer idPlanoCategoriaScore, Integer IdAnaliseCredito, Integer idRegional){
		return "id_"+idCabecalhoAnaliseCredito.intValue() + SEPARADOR_CHAVE
		+ idPlanoCategoriaScore.intValue() + SEPARADOR_CHAVE
		+ IdAnaliseCredito.intValue() + SEPARADOR_CHAVE
		+ idRegional.intValue();
	}
	*/
	public String getChaveConfiguracaoScore() {
		String retorno = null;
		Integer idCabecalhoAnaliseCredito;
		Integer idRegional;
		if(this.getRegionalTO() == null || this.getRegionalTO().getIdRegional() == null || this.getRegionalTO().getIdRegional() <= 0){
			idRegional = 0;
		} else {
			idRegional = this.getRegionalTO().getIdRegional();
		}
		if(this.cabecalhoAnaliseCreditoTO == null || this.cabecalhoAnaliseCreditoTO.getIdCabecalhoAnaliseCredito() == null || this.cabecalhoAnaliseCreditoTO.getIdCabecalhoAnaliseCredito() <= 0){
			idCabecalhoAnaliseCredito = 0;
		} else {
			idCabecalhoAnaliseCredito = this.cabecalhoAnaliseCreditoTO.getIdCabecalhoAnaliseCredito();
		}
		if(this.getPlanoCategoriaScoreTO() != null && this.getPlanoCategoriaScoreTO().getIdPlanoCategoriaScore() != null && this.getPlanoCategoriaScoreTO().getIdPlanoCategoriaScore() > 0
				&& this.getAnaliseCreditoTO() != null && this.getAnaliseCreditoTO().getIdAnaliseCredito() != null && this.getAnaliseCreditoTO().getIdAnaliseCredito() > 0
		) {
			retorno = "id_"+idCabecalhoAnaliseCredito+ SEPARADOR_CHAVE
			+ this.planoCategoriaScoreTO.getIdPlanoCategoriaScore() + SEPARADOR_CHAVE
			+ this.analiseCreditoTO.getIdAnaliseCredito() + SEPARADOR_CHAVE
			+ idRegional;
		} else {
			throw new RuntimeException("Objeto inválido");
		}
		return retorno;
	}
	
	public ConfiguracaoAnaliseCreditoTO buildConfiguracaoAnaliseCreditoTO(String chaveConfiguracao){
		PlanoConfiguracaoScoreTO to = new PlanoConfiguracaoScoreTO();
		
		String[] ids = chaveConfiguracao.split(SEPARADOR_CHAVE);
		if(ids.length != 5){
			throw new RuntimeException("Chave inválida");
		} else {
			Integer idCabecalhoAnaliseCredito = ids[1].equals("") ? 0 : Integer.parseInt(ids[1]);
			to.cabecalhoAnaliseCreditoTO = new CabecalhoAnaliseCreditoTO(idCabecalhoAnaliseCredito);
			to.planoCategoriaScoreTO = new PlanoCategoriaScoreTO(Integer.parseInt(ids[2]), null);
			to.analiseCreditoTO = new AnaliseCreditoTO(Integer.parseInt(ids[3]));
			if(Integer.parseInt(ids[4]) != 0){
				to.regionalTO = new RegionalTO(Integer.parseInt(ids[4]));
			}
		}
		return to;
	}
	
	public Date getDtCriacao() {
		return dtCriacao;
	}

	public CabecalhoAnaliseCreditoTO getCabecalhoAnaliseCreditoTO() {
		return cabecalhoAnaliseCreditoTO;
	}

	public void setCabecalhoAnaliseCreditoTO(
			CabecalhoAnaliseCreditoTO cabecalhoAnaliseCreditoTO) {
		this.cabecalhoAnaliseCreditoTO = cabecalhoAnaliseCreditoTO;
	}

	public void setDtCriacao(Date dtCriacao) {
		this.dtCriacao = dtCriacao;
	}

	public Integer getIdPlanoConfiguracaoScore() {
		return idPlanoConfiguracaoScore;
	}

	public void setIdPlanoConfiguracaoScore(Integer idPlanoConfiguracaoScore) {
		this.idPlanoConfiguracaoScore = idPlanoConfiguracaoScore;
	}

	public String getNmUsuarioCriacao() {
		return nmUsuarioCriacao;
	}

	public void setNmUsuarioCriacao(String nmUsuarioCriacao) {
		this.nmUsuarioCriacao = nmUsuarioCriacao;
	}

	public PlanoCategoriaScoreTO getPlanoCategoriaScoreTO() {
		return planoCategoriaScoreTO;
	}

	public void setPlanoCategoriaScoreTO(PlanoCategoriaScoreTO planoCategoriaScoreTO) {
		this.planoCategoriaScoreTO = planoCategoriaScoreTO;
	}

	public AnaliseCreditoTO getAnaliseCreditoTO() {
		return analiseCreditoTO;
	}

	public void setAnaliseCreditoTO(AnaliseCreditoTO analiseCreditoTO) {
		this.analiseCreditoTO = analiseCreditoTO;
	}	

	public RegionalTO getRegionalTO() {
		return regionalTO;
	}

	public void setRegionalTO(RegionalTO regionalTO) {
		this.regionalTO = regionalTO;
	}	
	
	
	
	@Override
	public String toString() {
		return StringUtils.join(new String[]{"idPlanoConfiguracaoScore: " + this.idPlanoConfiguracaoScore}, ", ");
	}

}
