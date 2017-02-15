package com.indracompany.catalogo.ejb.financiamento;

import java.util.List;

import javax.ejb.Local;

import br.com.indrasistemas.framework.service.BusinessException;

import com.indracompany.catalogo.to.CondicaoPagamentoTO;

@Local
public interface FinanciamentoBeanLocal {
	
	public static final String JNDI_NAME = "java:comp/env/FinanciamentoBean";
	
	public List<CondicaoPagamentoTO> search(CondicaoPagamentoTO condicaoPagamentoTO) throws BusinessException;
	public CondicaoPagamentoTO insertUpdate(CondicaoPagamentoTO condicaoPagamentoTO) throws BusinessException;
	public void remove(Integer id) throws BusinessException;
	public CondicaoPagamentoTO findById(Integer id) throws BusinessException;
    public CondicaoPagamentoTO finByName(String nmCondicaoPagamento) throws BusinessException;
}
