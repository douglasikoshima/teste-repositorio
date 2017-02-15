package com.indracompany.catalogo.dao;

import java.util.ArrayList;
import java.util.List;

import com.indracompany.catalogo.datalayer.CanalAtendimento;
import com.indracompany.catalogo.to.CanalAtendimentoTO;

/**
 * @author Luiz Pereira
 *
 * Classe responsável em transformar TO em Entity e Entiy em TO.
 */
public class CanalAtendimentoTOBuilder {
	
	/**
	 * @param canalAtendimentoTO
	 * @return
	 * 
	 *  Método responsável em transformar um TO em um Entity.
	 */
	public CanalAtendimento createCanalAtendimento(CanalAtendimentoTO canalAtendimentoTO) {
		
		CanalAtendimento canalAtendimento = null;
		
		if (canalAtendimentoTO != null) {
			canalAtendimento = new CanalAtendimento();
			canalAtendimento.setDtCriacao(canalAtendimentoTO.getDtCriacao());
			canalAtendimento.setDtUltimaAlteracao(canalAtendimentoTO.getDtUltimaAlteracao());
			canalAtendimento.setIdCanalAtendimento(canalAtendimentoTO.getIdCanalAtendimento());
			canalAtendimento.setInDisponivel(canalAtendimentoTO.getInDisponivel());
			canalAtendimento.setNmCanal(canalAtendimentoTO.getNmCanal());
			canalAtendimento.setNmUsuarioAlteracao(canalAtendimentoTO.getNmUsuarioAlteracao());
			canalAtendimento.setNmUsuarioCriacao(canalAtendimentoTO.getNmUsuarioCriacao());
			canalAtendimento.setSgVivoNet(canalAtendimentoTO.getSgVivoNet());
		}
		
		return canalAtendimento;
	}
	
	/**
	 * @param canalAtendimento
	 * @return
	 * 
	 * Método responsável em transformar um Entity em um TO.
	 */
	public CanalAtendimentoTO createCanalAtendimentoTO(CanalAtendimento canalAtendimento) {
		
		CanalAtendimentoTO canalAtendimentoTO = null;
		
		if (canalAtendimento != null) {
			canalAtendimentoTO = new CanalAtendimentoTO();
			canalAtendimentoTO.setDtCriacao(canalAtendimento.getDtCriacao());
			canalAtendimentoTO.setDtUltimaAlteracao(canalAtendimento.getDtUltimaAlteracao());
			canalAtendimentoTO.setIdCanalAtendimento(canalAtendimento.getIdCanalAtendimento());
			canalAtendimentoTO.setInDisponivel(canalAtendimento.getInDisponivel());
			canalAtendimentoTO.setNmCanal(canalAtendimento.getNmCanal());
			canalAtendimentoTO.setNmUsuarioAlteracao(canalAtendimento.getNmUsuarioAlteracao());
			canalAtendimentoTO.setNmUsuarioCriacao(canalAtendimento.getNmUsuarioCriacao());
			canalAtendimentoTO.setSgVivoNet(canalAtendimento.getSgVivoNet());
		}
		
		return canalAtendimentoTO;
	}
	
	/**
	 * @param canalAtendimentoList
	 * @return
	 * 
	 * Método responsável em transformar uma lista de Entity em uma lista de TO.
	 */
	public List<CanalAtendimentoTO> createCanalAtendimentoTOList(List<CanalAtendimento> canalAtendimentoList) {
		
		List<CanalAtendimentoTO> list = new ArrayList<CanalAtendimentoTO>();
		
		if (canalAtendimentoList != null && canalAtendimentoList.size() > 0) {
			for (CanalAtendimento canalAtendimento : canalAtendimentoList) {
				list.add(createCanalAtendimentoTO(canalAtendimento));
			}
		}
		
		return list;
	}
}
