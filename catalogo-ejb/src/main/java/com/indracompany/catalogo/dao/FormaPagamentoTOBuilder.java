package com.indracompany.catalogo.dao;

import java.util.ArrayList;
import java.util.List;

import com.indracompany.catalogo.datalayer.FormaPagamento;
import com.indracompany.catalogo.to.FormaPagamentoTO;

/**
 * @author Luiz Pereira
 *
 * Classe responsável em transformar TO em Entity e Entiy em TO.
 */
public class FormaPagamentoTOBuilder {
	
	/**
	 * @param formaPagamentoTO
	 * @return
	 * 
	 *  Método responsável em transformar um TO em um Entity.
	 */
	public FormaPagamento createFormaPagamento(FormaPagamentoTO formaPagamentoTO) {
		
		FormaPagamento formaPagamento = null;
		
		if (formaPagamentoTO != null) {
			formaPagamento = new FormaPagamento();
			formaPagamento.setDtCriacao(formaPagamentoTO.getDtCriacao());
			formaPagamento.setDtUltimaAlteracao(formaPagamentoTO.getDtUltimaAlteracao());
			formaPagamento.setNmFormaPagamento(formaPagamentoTO.getNmFormaPagamento());
			formaPagamento.setNmUsuarioAlteracao(formaPagamentoTO.getNmUsuarioAlteracao());
			formaPagamento.setNmUsuarioCriacao(formaPagamentoTO.getNmUsuarioCriacao());
			
			CondicaoPagamentoTOBuilder condicaoPagamentoTOBuilder = new CondicaoPagamentoTOBuilder();
			formaPagamento.setCondicaoPagamentoList(condicaoPagamentoTOBuilder.createCondicaoPagamentoList(formaPagamentoTO.getCondicaoPagamentoTOList()));
			
			FormaPagamentoBandeiraTOBuilder formaPagamentoBandeiraTOBuilder = new FormaPagamentoBandeiraTOBuilder();
			formaPagamento.setFormaPagamentoBandeiraList(formaPagamentoBandeiraTOBuilder.createFormaPagamentoBandeiraList(formaPagamentoTO.getFormaPagamentoBandeiraTOList()));
			
			MeioPagamentoTOBuilder meioPagamentoTOBuilder = new MeioPagamentoTOBuilder();
			formaPagamento.setMeioPagamento(meioPagamentoTOBuilder.createMeioPagamento(formaPagamentoTO.getMeioPagamentoTO()));
			
			FormaPagtoCanalParamTOBuilder formaPagtoCanalParamTOBuilder = new FormaPagtoCanalParamTOBuilder();
			formaPagamento.setFormaPagtoCanalParam(formaPagtoCanalParamTOBuilder.createFormaPagtoCanalParam(formaPagamentoTO.getFormaPagtoCanalParamTO()));
            
			formaPagamento.setIdFormaPagamento(formaPagamentoTO.getIdFormaPagamento());
		}
		return formaPagamento;
	}
	
	/**
	 * @param formaPagamento
	 * @return
	 * 
	 * Método responsável em transformar um Entity em um TO.
	 */
	public FormaPagamentoTO createFormaPagamentoTO(FormaPagamento formaPagamento) {
		
		FormaPagamentoTO formaPagamentoTO = null;
		
		if (formaPagamento != null) {
			formaPagamentoTO = new FormaPagamentoTO();
			formaPagamentoTO.setDtCriacao(formaPagamento.getDtCriacao());
			formaPagamentoTO.setDtUltimaAlteracao(formaPagamento.getDtUltimaAlteracao());
			formaPagamentoTO.setNmFormaPagamento(formaPagamento.getNmFormaPagamento());
			formaPagamentoTO.setNmUsuarioAlteracao(formaPagamento.getNmUsuarioAlteracao());
			formaPagamentoTO.setNmUsuarioCriacao(formaPagamento.getNmUsuarioCriacao());
			formaPagamentoTO.setIdFormaPagamento(formaPagamento.getIdFormaPagamento());
			CondicaoPagamentoTOBuilder condicaoPagamentoTOBuilder = new CondicaoPagamentoTOBuilder();
			formaPagamentoTO.setCondicaoPagamentoTOList(condicaoPagamentoTOBuilder.createCondicaoPagamentoTOList(formaPagamento.getCondicaoPagamentoList()));
			
			FormaPagamentoBandeiraTOBuilder formaPagamentoBandeiraTOBuilder = new FormaPagamentoBandeiraTOBuilder();
			formaPagamentoTO.setFormaPagamentoBandeiraTOList(formaPagamentoBandeiraTOBuilder.createFormaPagamentoBandeiraTOList(formaPagamento.getFormaPagamentoBandeiraList()));
			
			MeioPagamentoTOBuilder meioPagamentoTOBuilder = new MeioPagamentoTOBuilder();
			formaPagamentoTO.setMeioPagamentoTO(meioPagamentoTOBuilder.createMeioPagamentoTO(formaPagamento.getMeioPagamento()));
			
			FormaPagtoCanalParamTOBuilder formaPagtoCanalParamTOBuilder = new FormaPagtoCanalParamTOBuilder();
			formaPagamentoTO.setFormaPagtoCanalParamTO(formaPagtoCanalParamTOBuilder.createFormaPagtoCanalParamTO(formaPagamento.getFormaPagtoCanalParam()));
            formaPagamentoTO.setPlataformaTOList(new PlataformaTOBuilder().createPlataformaTOList(formaPagamento.getPlataformaList()));
		}
		
		return formaPagamentoTO;
	}
	
	/**
	 * @param formaPagamentoList
	 * @return
	 * 
	 * Método responsável em transformar uma lista de Entity em uma lista de TO.
	 */
	public List<FormaPagamentoTO> createFormaPagamentoTOList(List<FormaPagamento> formaPagamentoList) {
		
		List<FormaPagamentoTO> list = new ArrayList<FormaPagamentoTO>();
		
		if (formaPagamentoList != null && formaPagamentoList.size() > 0) {
			for (FormaPagamento formaPagamento : formaPagamentoList) {
				list.add(createFormaPagamentoTO(formaPagamento));
			}
		}
		
		return list;
	}
 
}
