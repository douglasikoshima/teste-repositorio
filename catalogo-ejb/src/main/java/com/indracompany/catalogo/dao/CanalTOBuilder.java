package com.indracompany.catalogo.dao;

import java.util.ArrayList;
import java.util.List;

import com.indracompany.catalogo.datalayer.Canal;
import com.indracompany.catalogo.to.CanalTO;

/**
 * @author Luiz Pereira
 *
 * Classe responsável em transformar TO em Entity e Entiy em TO.
 */
public class CanalTOBuilder {
	
	/**
	 * @param canalTO
	 * @return
	 * 
	 *  Método responsável em transformar um TO em um Entity.
	 */
	public Canal createCanal(CanalTO canalTO) {
		
		Canal canal = null;
		
		if (canalTO != null) {
			canal = new Canal();
			canal.setDtCriacao(canalTO.getDtCriacao());
			canal.setDtFinal(canalTO.getDtFinal());
			canal.setDtInicial(canalTO.getDtInicial());
			canal.setDtUltimaAlteracao(canalTO.getDtUltimaAlteracao());
			canal.setInDisponivel(canalTO.getInDisponivel());
			canal.setNmCanal(canalTO.getNmCanal());
			canal.setNmUsuarioAlteracao(canalTO.getNmUsuarioAlteracao());
			canal.setNmUsuarioCriacao(canalTO.getNmUsuarioCriacao());
			canal.setSgCanal(canalTO.getSgCanal());
			canal.setSgVivoNet(canalTO.getSgVivoNet());
			canal.setIdCanal(canalTO.getIdCanal());
		}
		
		return canal;
	}
	
	/**
	 * @param canal
	 * @return
	 * 
	 * Método responsável em transformar um Entity em um TO.
	 */
	public CanalTO createCanalTO(Canal canal) {
		
		CanalTO canalTO = null;
		
		if (canal != null) {
			canalTO = new CanalTO();
			canalTO.setDtCriacao(canal.getDtCriacao());
			canalTO.setDtFinal(canal.getDtFinal());
			canalTO.setDtInicial(canal.getDtInicial());
			canalTO.setDtUltimaAlteracao(canal.getDtUltimaAlteracao());
			canalTO.setInDisponivel(canal.getInDisponivel());
			canalTO.setNmCanal(canal.getNmCanal());
			canalTO.setNmUsuarioAlteracao(canal.getNmUsuarioAlteracao());
			canalTO.setNmUsuarioCriacao(canal.getNmUsuarioCriacao());
			canalTO.setSgCanal(canal.getSgCanal());
			canalTO.setSgVivoNet(canal.getSgVivoNet());
			canalTO.setIdCanal(canal.getIdCanal());
		}
		
		return canalTO;
	}
	
	/**
	 * @param canalList
	 * @return
	 * 
	 * Método responsável em transformar uma lista de Entity em uma lista de TO.
	 */
	public List<CanalTO> createCanalTOList(List<Canal> canalList) {
		
		List<CanalTO> list = new ArrayList<CanalTO>();
		
		if (canalList != null && canalList.size() > 0) {
			for (Canal canal : canalList) {
				list.add(createCanalTO(canal));
			}
		}
		
		return list;
	}
}
