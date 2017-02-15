package com.indracompany.catalogo.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.indracompany.catalogo.datalayer.CondicaoPagamento;
import com.indracompany.catalogo.to.CondicaoPagamentoTO;

/**
 * @author Luiz Pereira
 *
 * Classe responsável em transformar TO em Entity e Entiy em TO.
 */
public class CondicaoPagamentoTOBuilder {
	
	/**
	 * @param condicaoPagamentoTO
	 * @return
	 * 
	 *  Método responsável em transformar um TO em um Entity.
	 */
	public CondicaoPagamento createCondicaoPagamento(CondicaoPagamentoTO condicaoPagamentoTO) {
		
		CondicaoPagamento condicaoPagamento = null;
		
		if (condicaoPagamentoTO != null) {
			condicaoPagamento = new CondicaoPagamento();
			condicaoPagamento.setDtCriacao(condicaoPagamentoTO.getDtCriacao());
			condicaoPagamento.setDtUltimaAlteracao(condicaoPagamentoTO.getDtUltimaAlteracao());
			condicaoPagamento.setIdCondicaoPagamento(condicaoPagamentoTO.getIdCondicaoPagamento());
			condicaoPagamento.setNmCondicaoPagamento(condicaoPagamentoTO.getNmCondicaoPagamento());
			condicaoPagamento.setNmUsuarioAlteracao(condicaoPagamentoTO.getNmUsuarioAlteracao());
			condicaoPagamento.setNmUsuarioCriacao(condicaoPagamentoTO.getNmUsuarioCriacao());
			condicaoPagamento.setQtParcelas(condicaoPagamentoTO.getQtParcelas());
			condicaoPagamento.setSgCondicaoPagamento(condicaoPagamentoTO.getSgCondicaoPagamento());
			condicaoPagamento.setInCriacaoCatalogo(condicaoPagamentoTO.getInCriacaoCatalogo() != null && condicaoPagamentoTO.getInCriacaoCatalogo() ? "S" : "N");
			condicaoPagamento.setInDisponivel(condicaoPagamentoTO.getInDisponivel() != null && condicaoPagamentoTO.getInDisponivel() ? "S" : "N");
			condicaoPagamento.setInFixa(condicaoPagamentoTO.getInFixa() != null && condicaoPagamentoTO.getInFixa() ? "S" : "N");
			condicaoPagamento.setTxJuroParcela(condicaoPagamentoTO.getTxJuroParcela());
            condicaoPagamento.setCdCondicaoPagamento(condicaoPagamentoTO.getCdCondicaoPagamento());
			condicaoPagamento.setFormaPagamento(new FormaPagamentoTOBuilder().createFormaPagamento(condicaoPagamentoTO.getFormaPagamentoTO()));
		}
		
		return condicaoPagamento;
	}
	
	/**
	 * @param condicaoPagamento
	 * @return
	 * 
	 * Método responsável em transformar um Entity em um TO.
	 */
	public CondicaoPagamentoTO createCondicaoPagamentoTO(CondicaoPagamento condicaoPagamento) {
		
		CondicaoPagamentoTO condicaoPagamentoTO = null;
		
		if (condicaoPagamento != null) {
			condicaoPagamentoTO = new CondicaoPagamentoTO();
			condicaoPagamentoTO.setDtCriacao(condicaoPagamento.getDtCriacao());
			condicaoPagamentoTO.setDtUltimaAlteracao(condicaoPagamento.getDtUltimaAlteracao());
			condicaoPagamentoTO.setIdCondicaoPagamento(condicaoPagamento.getIdCondicaoPagamento());
			condicaoPagamentoTO.setNmCondicaoPagamento(condicaoPagamento.getNmCondicaoPagamento());
			condicaoPagamentoTO.setNmUsuarioAlteracao(condicaoPagamento.getNmUsuarioAlteracao());
			condicaoPagamentoTO.setNmUsuarioCriacao(condicaoPagamento.getNmUsuarioCriacao());
			condicaoPagamentoTO.setQtParcelas(condicaoPagamento.getQtParcelas());
			condicaoPagamentoTO.setSgCondicaoPagamento(condicaoPagamento.getSgCondicaoPagamento());

			if (StringUtils.isBlank(condicaoPagamento.getInFixa())) {
				condicaoPagamentoTO.setInFixa(false);
			} else {
				condicaoPagamentoTO.setInFixa(condicaoPagamento.getInFixa().equalsIgnoreCase("S"));
			}			
			
			if (StringUtils.isBlank(condicaoPagamento.getInCriacaoCatalogo())) {
				condicaoPagamentoTO.setInCriacaoCatalogo(false);
			} else {
				condicaoPagamentoTO.setInCriacaoCatalogo(condicaoPagamento.getInCriacaoCatalogo().equalsIgnoreCase("S"));
			}
			condicaoPagamentoTO.setInDisponivel(condicaoPagamento.getInDisponivel().equalsIgnoreCase("S"));
            condicaoPagamentoTO.setTxJuroParcela(condicaoPagamento.getTxJuroParcela());
            condicaoPagamentoTO.setCdCondicaoPagamento(condicaoPagamento.getCdCondicaoPagamento());
		}
		return condicaoPagamentoTO;
	}
	
	/**
	 * @param condicaoPagamentoList
	 * @return
	 * 
	 * Método responsável em transformar uma lista de Entity em uma lista de TO.
	 */
	public List<CondicaoPagamentoTO> createCondicaoPagamentoTOList(List<CondicaoPagamento> condicaoPagamentoList) {
		
		List<CondicaoPagamentoTO> list = new ArrayList<CondicaoPagamentoTO>();
		
		if (condicaoPagamentoList != null && condicaoPagamentoList.size() > 0) {
			for (CondicaoPagamento condicaoPagamento : condicaoPagamentoList) {
				list.add(createCondicaoPagamentoTO(condicaoPagamento));
			}
		}
		
		return list;
	}
	
	/**
	 * @param condicaoPagamentoList
	 * @return
	 */
	public List<CondicaoPagamento> createCondicaoPagamentoList(List<CondicaoPagamentoTO> condicaoPagamentoList) {
		
		List<CondicaoPagamento> list = new ArrayList<CondicaoPagamento>();
		
		if (condicaoPagamentoList != null && condicaoPagamentoList.size() > 0) {
			for (CondicaoPagamentoTO condicaoPagamentoTO : condicaoPagamentoList) {
				list.add(createCondicaoPagamento(condicaoPagamentoTO));
			}
		}
		
		return list;
	}
}
