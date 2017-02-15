package com.indracompany.catalogo.dao;

import java.util.ArrayList;
import java.util.List;

import com.indracompany.catalogo.datalayer.Funcionalidade;
import com.indracompany.catalogo.to.FuncionalidadeTO;

/**
 * @author Luiz Pereira
 *
 * Classe responsável em transformar TO em Entity e Entiy em TO.
 */
public class FuncionalidadeTOBuilder {
	
	/**
	 * @param funcionalidadeTO
	 * @return
	 * 
	 *  Método responsável em transformar um TO em um Entity.
	 */
	public Funcionalidade createFuncionalidade(FuncionalidadeTO funcionalidadeTO) {
		
		Funcionalidade funcionalidade = null;
		
		if (funcionalidadeTO != null) {
			funcionalidade = new Funcionalidade();
			funcionalidade.setCdFuncionalidade(funcionalidadeTO.getCdFuncionalidade());
			funcionalidade.setDtCriacao(funcionalidadeTO.getDtCriacao());
			funcionalidade.setNmFuncionalidade(funcionalidadeTO.getNmFuncionalidade());
		}
		return funcionalidade;
	}
	
	/**
	 * @param funcionalidade
	 * @return
	 * 
	 * Método responsável em transformar um Entity em um TO.
	 */
	public FuncionalidadeTO createFuncionalidadeTO(Funcionalidade funcionalidade) {
		
		FuncionalidadeTO funcionalidadeTO = null;
		
		if (funcionalidade != null) {
			funcionalidadeTO = new FuncionalidadeTO();
			funcionalidadeTO.setCdFuncionalidade(funcionalidade.getCdFuncionalidade());
			funcionalidadeTO.setDtCriacao(funcionalidade.getDtCriacao());
			funcionalidadeTO.setNmFuncionalidade(funcionalidade.getNmFuncionalidade());
		}
		
		return funcionalidadeTO;
	}
	
	/**
	 * @param funcionalidadeList
	 * @return
	 * 
	 * Método responsável em transformar uma lista de Entity em uma lista de TO.
	 */
	public List<FuncionalidadeTO> createFuncionalidadeTOList(List<Funcionalidade> funcionalidadeList) {
		
		List<FuncionalidadeTO> list = new ArrayList<FuncionalidadeTO>();
		
		if (funcionalidadeList != null && funcionalidadeList.size() > 0) {
			for (Funcionalidade funcionalidade : funcionalidadeList) {
				list.add(createFuncionalidadeTO(funcionalidade));
			}
		}
		
		return list;
	}
}
