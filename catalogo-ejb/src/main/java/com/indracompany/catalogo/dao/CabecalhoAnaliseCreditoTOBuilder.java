package com.indracompany.catalogo.dao;

import java.util.ArrayList;
import java.util.List;

import com.indracompany.catalogo.datalayer.CabecalhoAnaliseCredito;
import com.indracompany.catalogo.to.CabecalhoAnaliseCreditoTO;

/**
 * @author Luiz Pereira
 *
 * Classe responsável em transformar TO em Entity e Entiy em TO.
 */
public class CabecalhoAnaliseCreditoTOBuilder {
	
	private boolean criarCanalAtendimento;
	private boolean criarServicoConfiguracaoScore;
	private boolean criarPlanoConfiguracaoScore;
	private boolean criarOfServicoConfiguracaoScore;
	
	public CabecalhoAnaliseCreditoTOBuilder() {
		this(true);
	}
	
	public CabecalhoAnaliseCreditoTOBuilder(boolean criar) {
		criarCanalAtendimento = criar;
		criarServicoConfiguracaoScore = criar;
		criarPlanoConfiguracaoScore = criar;
		criarOfServicoConfiguracaoScore = criar;
	}
	
	/**
	 * @param cabecalhoAnaliseCreditoTO
	 * @return
	 * 
	 *  Método responsável em transformar um TO em um Entity.
	 */
	public CabecalhoAnaliseCredito createCabecalhoAnaliseCredito(CabecalhoAnaliseCreditoTO cabecalhoAnaliseCreditoTO) {
		
		CabecalhoAnaliseCredito cabecalhoAnaliseCredito = null;
		
		if (cabecalhoAnaliseCreditoTO != null) {
			cabecalhoAnaliseCredito = new CabecalhoAnaliseCredito();
			cabecalhoAnaliseCredito.setDtCriacao(cabecalhoAnaliseCreditoTO.getDtCriacao());
			cabecalhoAnaliseCredito.setDtUltimaAlteracao(cabecalhoAnaliseCreditoTO.getDtUltimaAlteracao());
			cabecalhoAnaliseCredito.setNmCabecalhoAnaliseCredito(cabecalhoAnaliseCreditoTO.getNmCabecalhoAnaliseCredito());
			cabecalhoAnaliseCredito.setNmUsuarioCriacao(cabecalhoAnaliseCreditoTO.getNmUsuarioCriacao());
			cabecalhoAnaliseCredito.setNmUsuarioAlteracao(cabecalhoAnaliseCreditoTO.getNmUsuarioAlteracao());
			cabecalhoAnaliseCredito.setIdCabecalhoAnaliseCredito(cabecalhoAnaliseCreditoTO.getIdCabecalhoAnaliseCredito());
			cabecalhoAnaliseCredito.setInDisponivel(cabecalhoAnaliseCreditoTO.getInDisponivel());
			
			if (cabecalhoAnaliseCreditoTO.getCanalAtendimento() != null) {
				CanalAtendimentoTOBuilder canalAtendimentoTOBuilder = new CanalAtendimentoTOBuilder();
				cabecalhoAnaliseCredito.setCanalAtendimento(canalAtendimentoTOBuilder.createCanalAtendimento(cabecalhoAnaliseCreditoTO.getCanalAtendimento()));
			}
			
		}
		return cabecalhoAnaliseCredito;
	}
	
	/**
	 * @param cabecalhoAnaliseCredito
	 * @return
	 * 
	 * Método responsável em transformar um Entity em um TO.
	 */
	public CabecalhoAnaliseCreditoTO createCabecalhoAnaliseCreditoTO(CabecalhoAnaliseCredito cabecalhoAnaliseCredito) {
		
		CabecalhoAnaliseCreditoTO cabecalhoAnaliseCreditoTO = null;
		
		if (cabecalhoAnaliseCredito != null) {
			cabecalhoAnaliseCreditoTO = new CabecalhoAnaliseCreditoTO();
			cabecalhoAnaliseCreditoTO.setDtCriacao(cabecalhoAnaliseCredito.getDtCriacao());
			cabecalhoAnaliseCreditoTO.setDtUltimaAlteracao(cabecalhoAnaliseCredito.getDtUltimaAlteracao());
			cabecalhoAnaliseCreditoTO.setIdCabecalhoAnaliseCredito(cabecalhoAnaliseCredito.getIdCabecalhoAnaliseCredito());
			cabecalhoAnaliseCreditoTO.setNmCabecalhoAnaliseCredito(cabecalhoAnaliseCredito.getNmCabecalhoAnaliseCredito());
			cabecalhoAnaliseCreditoTO.setNmUsuarioCriacao(cabecalhoAnaliseCredito.getNmUsuarioCriacao());
			cabecalhoAnaliseCreditoTO.setNmUsuarioAlteracao(cabecalhoAnaliseCredito.getNmUsuarioAlteracao());
			cabecalhoAnaliseCreditoTO.setInDisponivel(cabecalhoAnaliseCredito.getInDisponivel());
			
			if (criarCanalAtendimento && cabecalhoAnaliseCredito.getCanalAtendimento() != null) {
				CanalAtendimentoTOBuilder canalAtendimentoTOBuilder = new CanalAtendimentoTOBuilder();
				cabecalhoAnaliseCreditoTO.setCanalAtendimento(canalAtendimentoTOBuilder.createCanalAtendimentoTO(cabecalhoAnaliseCredito.getCanalAtendimento()));
			}
			
			//Preenche lista de Plano, lista de Servicos, lista de Oferta Servicos
			if (criarServicoConfiguracaoScore && cabecalhoAnaliseCredito.getServicoConfiguracaoScoreList() != null) {
				ServicoConfiguracaoScoreTOBuilder builder = new ServicoConfiguracaoScoreTOBuilder();
				cabecalhoAnaliseCreditoTO.setServicoConfiguracaoScoreList(builder.createServicoConfiguracaoScoreTOList(cabecalhoAnaliseCredito.getServicoConfiguracaoScoreList()));
			}
			/*
			if (criarPlanoConfiguracaoScore && cabecalhoAnaliseCredito.getPlanoConfiguracaoScoreList() != null) {
				PlanoConfiguracaoScoreTOBuilder builder = new PlanoConfiguracaoScoreTOBuilder();
				cabecalhoAnaliseCreditoTO.setPlanoConfiguracaoScoreList(builder.createPlanoConfiguracaoScoreTOList(cabecalhoAnaliseCredito.getPlanoConfiguracaoScoreList()));
			}
			*/
			if (criarOfServicoConfiguracaoScore && cabecalhoAnaliseCredito.getOfServicoConfiguracaoScoreList() != null) {
				OfServicoConfiguracaoScoreTOBuilder builder = new OfServicoConfiguracaoScoreTOBuilder();
				cabecalhoAnaliseCreditoTO.setOfServicoConfiguracaoScoreList(builder.createOfServicoConfiguracaoScoreTOList(cabecalhoAnaliseCredito.getOfServicoConfiguracaoScoreList()));
			}
		}
		
		return cabecalhoAnaliseCreditoTO;
	}
	
	
	/**
	 * @param cabecalhoAnaliseCredito
	 * @return
	 * 
	 * Método responsável em transformar um Entity em um TO.
	 */
	public CabecalhoAnaliseCreditoTO createCabecalhoAnaliseCreditoTOListNoChild(CabecalhoAnaliseCredito cabecalhoAnaliseCredito) {
		
		CabecalhoAnaliseCreditoTO cabecalhoAnaliseCreditoTO = null;
		
		if (cabecalhoAnaliseCredito != null) {
			cabecalhoAnaliseCreditoTO = new CabecalhoAnaliseCreditoTO();
			cabecalhoAnaliseCreditoTO.setDtCriacao(cabecalhoAnaliseCredito.getDtCriacao());
			cabecalhoAnaliseCreditoTO.setDtUltimaAlteracao(cabecalhoAnaliseCredito.getDtUltimaAlteracao());
			cabecalhoAnaliseCreditoTO.setIdCabecalhoAnaliseCredito(cabecalhoAnaliseCredito.getIdCabecalhoAnaliseCredito());
			cabecalhoAnaliseCreditoTO.setNmCabecalhoAnaliseCredito(cabecalhoAnaliseCredito.getNmCabecalhoAnaliseCredito());
			cabecalhoAnaliseCreditoTO.setNmUsuarioCriacao(cabecalhoAnaliseCredito.getNmUsuarioCriacao());
			cabecalhoAnaliseCreditoTO.setNmUsuarioAlteracao(cabecalhoAnaliseCredito.getNmUsuarioAlteracao());
			cabecalhoAnaliseCreditoTO.setInDisponivel(cabecalhoAnaliseCredito.getInDisponivel());
			
			if (cabecalhoAnaliseCredito.getCanalAtendimento() != null) {
				CanalAtendimentoTOBuilder canalAtendimentoTOBuilder = new CanalAtendimentoTOBuilder();
				cabecalhoAnaliseCreditoTO.setCanalAtendimento(canalAtendimentoTOBuilder.createCanalAtendimentoTO(cabecalhoAnaliseCredito.getCanalAtendimento()));
			}
			
		}
		
		return cabecalhoAnaliseCreditoTO;
	}
	
	/**
	 * @param cabecalhoAnaliseCreditoList
	 * @return
	 * 
	 * Método responsável em transformar uma lista de Entity em uma lista de TO.
	 */
	public List<CabecalhoAnaliseCreditoTO> createCabecalhoAnaliseCreditoTOList(List<CabecalhoAnaliseCredito> cabecalhoAnaliseCreditoList) {
		
		List<CabecalhoAnaliseCreditoTO> list = new ArrayList<CabecalhoAnaliseCreditoTO>();
		
		if (cabecalhoAnaliseCreditoList != null && cabecalhoAnaliseCreditoList.size() > 0) {
			for (CabecalhoAnaliseCredito cabecalhoAnaliseCredito : cabecalhoAnaliseCreditoList) {
				list.add(createCabecalhoAnaliseCreditoTO(cabecalhoAnaliseCredito));
			}
		}
		
		return list;
	}
	
	
	/**
	 * @param cabecalhoAnaliseCreditoList
	 * @return
	 * 
	 * Método responsável em transformar uma lista de Entity em uma lista de TO.
	 */
	public List<CabecalhoAnaliseCreditoTO> createCabecalhoAnaliseCreditoTOListNoChild(List<CabecalhoAnaliseCredito> cabecalhoAnaliseCreditoList) {
		
		List<CabecalhoAnaliseCreditoTO> list = new ArrayList<CabecalhoAnaliseCreditoTO>();
		
		if (cabecalhoAnaliseCreditoList != null && cabecalhoAnaliseCreditoList.size() > 0) {
			for (CabecalhoAnaliseCredito cabecalhoAnaliseCredito : cabecalhoAnaliseCreditoList) {
				list.add(createCabecalhoAnaliseCreditoTOListNoChild(cabecalhoAnaliseCredito));
			}
		}
		
		return list;
	}
	
	public boolean isCriarCanalAtendimento() {
		return criarCanalAtendimento;
	}
	
	public void setCriarCanalAtendimento(boolean criarCanalAtendimento) {
		this.criarCanalAtendimento = criarCanalAtendimento;
	}
	
	public boolean isCriarOfServicoConfiguracaoScore() {
		return criarOfServicoConfiguracaoScore;
	}
	
	public void setCriarOfServicoConfiguracaoScore(
			boolean criarOfServicoConfiguracaoScore) {
		this.criarOfServicoConfiguracaoScore = criarOfServicoConfiguracaoScore;
	}
	
	public boolean isCriarPlanoConfiguracaoScore() {
		return criarPlanoConfiguracaoScore;
	}
	
	public void setCriarPlanoConfiguracaoScore(boolean criarPlanoConfiguracaoScore) {
		this.criarPlanoConfiguracaoScore = criarPlanoConfiguracaoScore;
	}
	
	public boolean isCriarServicoConfiguracaoScore() {
		return criarServicoConfiguracaoScore;
	}
	
	public void setCriarServicoConfiguracaoScore(
			boolean criarServicoConfiguracaoScore) {
		this.criarServicoConfiguracaoScore = criarServicoConfiguracaoScore;
	}
	
}