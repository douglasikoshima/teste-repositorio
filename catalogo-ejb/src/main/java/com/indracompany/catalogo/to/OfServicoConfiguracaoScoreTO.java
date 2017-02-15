package com.indracompany.catalogo.to;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

/**
 * @author Luiz Pereira
 *
 */
public class OfServicoConfiguracaoScoreTO extends ConfiguracaoAnaliseCreditoTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer idOfServicoConfiguracaoScore;
	private Date dtCriacao;
	private String nmUsuarioCriacao;
	private OfertaServicoCategoriaScoreTO ofertaServicoCategoriaScoreTO;
	private AnaliseCreditoTO analiseCreditoTO;
	private CabecalhoAnaliseCreditoTO cabecalhoAnaliseCreditoTO;
	private RegionalTO regionalTO;

	public OfServicoConfiguracaoScoreTO() {}
	
	public OfServicoConfiguracaoScoreTO(Integer idCabecalhoAnaliseCredito, Integer idOfertaServicoCategoriaScore, Integer IdAnaliseCredito, Integer idRegional){
		this.cabecalhoAnaliseCreditoTO = new CabecalhoAnaliseCreditoTO(idCabecalhoAnaliseCredito);
		this.ofertaServicoCategoriaScoreTO = new OfertaServicoCategoriaScoreTO(idOfertaServicoCategoriaScore, null);
		this.analiseCreditoTO = new AnaliseCreditoTO(IdAnaliseCredito);
		this.regionalTO = new RegionalTO(idRegional);
	}
	
	public String getChaveConfiguracaoScore() {
		String retorno = null;
		Integer idRegional;
		Integer idCabecalhoAnaliseCredito;
		if(this.getRegionalTO() == null || this.getRegionalTO().getIdRegional() == null || this.getRegionalTO().getIdRegional() < 0){
			idRegional = 0;
		} else {
			idRegional = this.getRegionalTO().getIdRegional();
		}
		if(this.cabecalhoAnaliseCreditoTO == null || this.cabecalhoAnaliseCreditoTO.getIdCabecalhoAnaliseCredito() == null || this.cabecalhoAnaliseCreditoTO.getIdCabecalhoAnaliseCredito() <= 0){
			idCabecalhoAnaliseCredito = 0;
		} else {
			idCabecalhoAnaliseCredito = this.getCabecalhoAnaliseCreditoTO().getIdCabecalhoAnaliseCredito();
		}
		if(this.getOfertaServicoCategoriaScoreTO() != null && this.getOfertaServicoCategoriaScoreTO().getIdOfertaServicoCategoriaScore() != null && this.getOfertaServicoCategoriaScoreTO().getIdOfertaServicoCategoriaScore() > 0
				&& this.getAnaliseCreditoTO() != null && this.getAnaliseCreditoTO().getIdAnaliseCredito() != null && this.getAnaliseCreditoTO().getIdAnaliseCredito() > 0
		) {
			retorno = "id_"+idCabecalhoAnaliseCredito + SEPARADOR_CHAVE
			+ this.ofertaServicoCategoriaScoreTO.getIdOfertaServicoCategoriaScore() + SEPARADOR_CHAVE
			+ this.analiseCreditoTO.getIdAnaliseCredito() + SEPARADOR_CHAVE
			+ idRegional.intValue();
		} else {
			throw new RuntimeException("Objeto inválido");
		}
		return retorno;
	}
	
	public ConfiguracaoAnaliseCreditoTO buildConfiguracaoAnaliseCreditoTO(String chaveConfiguracao){
		OfServicoConfiguracaoScoreTO to = new OfServicoConfiguracaoScoreTO();
		
		String[] ids = chaveConfiguracao.split(SEPARADOR_CHAVE);
		if(ids.length != 5){
			throw new RuntimeException("Chave inválida");
		} else {
			Integer idCabecalhoAnaliseCredito = ids[1].equals("") ? 0 : Integer.parseInt(ids[1]);
			to.cabecalhoAnaliseCreditoTO = new CabecalhoAnaliseCreditoTO(idCabecalhoAnaliseCredito);
			to.ofertaServicoCategoriaScoreTO = new OfertaServicoCategoriaScoreTO(Integer.parseInt(ids[2]), null);
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

	public void setDtCriacao(Date dtCriacao) {
		this.dtCriacao = dtCriacao;
	}

	public Integer getIdOfServicoConfiguracaoScore() {
		return idOfServicoConfiguracaoScore;
	}

	public void setIdOfServicoConfiguracaoScore(Integer idOfServicoConfiguracaoScore) {
		this.idOfServicoConfiguracaoScore = idOfServicoConfiguracaoScore;
	}

	public String getNmUsuarioCriacao() {
		return nmUsuarioCriacao;
	}

	public void setNmUsuarioCriacao(String nmUsuarioCriacao) {
		this.nmUsuarioCriacao = nmUsuarioCriacao;
	}

	public OfertaServicoCategoriaScoreTO getOfertaServicoCategoriaScoreTO() {
		return ofertaServicoCategoriaScoreTO;
	}

	public void setOfertaServicoCategoriaScoreTO(
			OfertaServicoCategoriaScoreTO ofertaServicoCategoriaScoreTO) {
		this.ofertaServicoCategoriaScoreTO = ofertaServicoCategoriaScoreTO;
	}

	public AnaliseCreditoTO getAnaliseCreditoTO() {
		return analiseCreditoTO;
	}

	public void setAnaliseCreditoTO(AnaliseCreditoTO analiseCreditoTO) {
		this.analiseCreditoTO = analiseCreditoTO;
	}

	@Override
	public String toString() {
		return StringUtils.join(new String[]{"idOfServicoConfiguracaoScore: " + this.idOfServicoConfiguracaoScore}, ", ");
	}

	public CabecalhoAnaliseCreditoTO getCabecalhoAnaliseCreditoTO() {
		return cabecalhoAnaliseCreditoTO;
	}

	public void setCabecalhoAnaliseCreditoTO(
			CabecalhoAnaliseCreditoTO cabecalhoAnaliseCreditoTO) {
		this.cabecalhoAnaliseCreditoTO = cabecalhoAnaliseCreditoTO;
	}

	public RegionalTO getRegionalTO() {
		return regionalTO;
	}

	public void setRegionalTO(RegionalTO regionalTO) {
		this.regionalTO = regionalTO;
	}
}
