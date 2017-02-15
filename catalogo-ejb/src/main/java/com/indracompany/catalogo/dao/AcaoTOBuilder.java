package com.indracompany.catalogo.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.indracompany.catalogo.datalayer.Acao;
import com.indracompany.catalogo.to.AcaoTO;

/**
 * @author Luiz Pereira
 *
 * Classe responsável em transformar TO em Entity e Entiy em TO.
 */
public class AcaoTOBuilder {
	
	/**
	 * @param acaoTO
	 * @return
	 * 
	 *  Método responsável em transformar um TO em um Entity.
	 */
	public Acao createAcao(AcaoTO acaoTO) {
		
		Acao acao = null;
		
		if (acaoTO != null) {
			acao = new Acao();
			acao.setDsAcao(acaoTO.getDsAcao());
			acao.setDtAlteracao(acaoTO.getDtAlteracao());
			acao.setDtCriacao(acaoTO.getDtCriacao());
			acao.setIdAcao(acaoTO.getIdAcao());
			acao.setInDisponivel(acaoTO.getInDisponivel());
			acao.setNmAcao(acaoTO.getNmAcao());
			acao.setNmUsuarioAlteracao(acaoTO.getNmUsuarioAlteracao());
			acao.setNmUsuarioCriacao(acaoTO.getNmUsuarioCriacao());
			acao.setSgAcao(acaoTO.getSgAcao());
			acao.setDtFinal(acaoTO.getDtFinal());
			acao.setDtInicial(acaoTO.getDtInicial());
			acao.setIndAnaliseFraude(acaoTO.getIndAnaliseFraude());
			
			if (acaoTO.getCanalAtendimentoTO() != null) {
				CanalAtendimentoTOBuilder canalAtendimentoTOBuilder = new CanalAtendimentoTOBuilder();
				acao.setCanalAtendimento(canalAtendimentoTOBuilder.createCanalAtendimento(acaoTO.getCanalAtendimentoTO()));
			}
		}
		return acao;
	}
	
	/**
	 * @param acao
	 * @return
	 * 
	 * Método responsável em transformar um Entity em um TO.
	 */
	public AcaoTO createAcaoTO(Acao acao) {
		
		AcaoTO acaoTO = null;
		
		if (acao != null) {
			acaoTO = new AcaoTO();
			acaoTO.setDsAcao(acao.getDsAcao());
			acaoTO.setDtAlteracao(acao.getDtAlteracao());
			acaoTO.setDtCriacao(acao.getDtCriacao());
			acaoTO.setIdAcao(acao.getIdAcao());
			acaoTO.setInDisponivel(acao.getInDisponivel());
			acaoTO.setNmAcao(acao.getNmAcao());
			acaoTO.setNmUsuarioAlteracao(acao.getNmUsuarioAlteracao());
			acaoTO.setNmUsuarioCriacao(acao.getNmUsuarioCriacao());
			acaoTO.setSgAcao(acao.getSgAcao());
			
			acaoTO.setDtFinal(acao.getDtFinal());
			acaoTO.setDtInicial(acao.getDtInicial());
			acaoTO.setIndAnaliseFraude(acao.getIndAnaliseFraude());
			
			if (acao.getCanalAtendimento() != null) {
				CanalAtendimentoTOBuilder canalAtendimentoTOBuilder = new CanalAtendimentoTOBuilder();
				acaoTO.setCanalAtendimentoTO(canalAtendimentoTOBuilder.createCanalAtendimentoTO(acao.getCanalAtendimento()));
			}
			
			if (acao.getDtFinal() != null) {
				if (acao.getDtFinal().after(new Date()) || acao.getDtFinal().equals(new Date())) { 
					acaoTO.setIsVigente("S");
				} else {
					acaoTO.setIsVigente("N");
				}
			} else {
				acaoTO.setIsVigente("N");
			}
		}
		
		return acaoTO;
	}
	
	/**
	 * @param acaoList
	 * @return
	 * 
	 * Método responsável em transformar uma lista de Entity em uma lista de TO.
	 */
	public List<AcaoTO> createAcaoTOList(List<Acao> acaoList) {
		
		List<AcaoTO> list = new ArrayList<AcaoTO>();
		
		if (acaoList != null && acaoList.size() > 0) {
			for (Acao acao : acaoList) {
				list.add(createAcaoTO(acao));
			}
		}
		
		return list;
	}
}
