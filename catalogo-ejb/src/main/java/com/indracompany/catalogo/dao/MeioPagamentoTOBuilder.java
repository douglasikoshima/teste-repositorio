package com.indracompany.catalogo.dao;

import java.util.ArrayList;
import java.util.List;

import com.indracompany.catalogo.datalayer.MeioPagamento;
import com.indracompany.catalogo.to.MeioPagamentoTO;

/**
 * @author Luiz Pereira
 *
 * Classe responsável em transformar TO em Entity e Entiy em TO.
 */
public class MeioPagamentoTOBuilder {
	
	/**
	 * @param meioPagamentoTO
	 * @return
	 * 
	 *  Método responsável em transformar um TO em um Entity.
	 */
	public MeioPagamento createMeioPagamento(MeioPagamentoTO meioPagamentoTO) {
		
		MeioPagamento meioPagamento = null;
		
		if (meioPagamentoTO != null) {
			meioPagamento = new MeioPagamento();
			meioPagamento.setDtCriacao(meioPagamentoTO.getDtCriacao());
			meioPagamento.setDtUltimaAlteracao(meioPagamentoTO.getDtUltimaAlteracao());
			meioPagamento.setNmMeioPagamento(meioPagamentoTO.getNmMeioPagamento());
			meioPagamento.setNmUsuarioAlteracao(meioPagamentoTO.getNmUsuarioAlteracao());
			meioPagamento.setNmUsuarioCriacao(meioPagamentoTO.getNmUsuarioCriacao());
			meioPagamento.setSgMeioPagamento(meioPagamentoTO.getSgMeioPagamento());
			meioPagamento.setIdMeioPagamento(meioPagamentoTO.getIdMeioPagamento());
		}
		
		return meioPagamento;
	}
	
	/**
	 * @param meioPagamento
	 * @return
	 * 
	 * Método responsável em transformar um Entity em um TO.
	 */
	public MeioPagamentoTO createMeioPagamentoTO(MeioPagamento meioPagamento) {
		
		MeioPagamentoTO meioPagamentoTO = null;
		
		if (meioPagamento != null) {
			meioPagamentoTO = new MeioPagamentoTO();
			meioPagamentoTO.setDtCriacao(meioPagamento.getDtCriacao());
			meioPagamentoTO.setDtUltimaAlteracao(meioPagamento.getDtUltimaAlteracao());
			meioPagamentoTO.setNmMeioPagamento(meioPagamento.getNmMeioPagamento());
			meioPagamentoTO.setNmUsuarioAlteracao(meioPagamento.getNmUsuarioAlteracao());
			meioPagamentoTO.setNmUsuarioCriacao(meioPagamento.getNmUsuarioCriacao());
			meioPagamentoTO.setSgMeioPagamento(meioPagamento.getSgMeioPagamento());
			meioPagamentoTO.setIdMeioPagamento(meioPagamento.getIdMeioPagamento());
		}
		
		return meioPagamentoTO;
	}
	
	/**
	 * @param meioPagamentoList
	 * @return
	 * 
	 * Método responsável em transformar uma lista de Entity em uma lista de TO.
	 */
	public List<MeioPagamentoTO> createMeioPagamentoTOList(List<MeioPagamento> meioPagamentoList) {
		
		List<MeioPagamentoTO> list = new ArrayList<MeioPagamentoTO>();
		
		if (meioPagamentoList != null && meioPagamentoList.size() > 0) {
			for (MeioPagamento meioPagamento : meioPagamentoList) {
				list.add(createMeioPagamentoTO(meioPagamento));
			}
		}
		
		return list;
	}
}
