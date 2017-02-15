package com.indracompany.catalogo.dao;

import java.util.ArrayList;
import java.util.List;

import com.indracompany.catalogo.datalayer.Canal;
import com.indracompany.catalogo.datalayer.FormaPagamento;
import com.indracompany.catalogo.datalayer.FormaPagtoCanalParam;
import com.indracompany.catalogo.to.CanalTO;
import com.indracompany.catalogo.to.FormaPagtoCanalParamTO;

/**
 * @author Luiz Pereira
 *
 * Classe responsável em transformar TO em Entity e Entiy em TO.
 */
public class FormaPagtoCanalParamTOBuilder {
	
	/**
	 * @param formaPagtoCanalParamTO
	 * @return
	 * 
	 *  Método responsável em transformar um TO em um Entity.
	 */
	public FormaPagtoCanalParam createFormaPagtoCanalParam(FormaPagtoCanalParamTO formaPagtoCanalParamTO) {
		
		FormaPagtoCanalParam formaPagtoCanalParam = null;
		
		if (formaPagtoCanalParamTO != null) {
			formaPagtoCanalParam = new FormaPagtoCanalParam();
			formaPagtoCanalParam.setIdFormaPagtoCanalParam(formaPagtoCanalParamTO.getIdFormaPagtoCanalParam());
			formaPagtoCanalParam.setNrMaxParcSemJuros(formaPagtoCanalParamTO.getNrMaxParcSemJuros());
			formaPagtoCanalParam.setNrParcelasMax(formaPagtoCanalParamTO.getNrParcelasMax());
			formaPagtoCanalParam.setTaxaJuros(formaPagtoCanalParamTO.getTaxaJuros());
			
			Canal canal = new Canal();
			canal.setIdCanal(formaPagtoCanalParamTO.getCanalTO().getIdCanal());
			
			formaPagtoCanalParam.setCanal(canal);
			
			if (formaPagtoCanalParamTO.getFormaPagamentoTO() != null) {
				FormaPagamento formaPagamento = new FormaPagamento();
				formaPagamento.setIdFormaPagamento(formaPagtoCanalParamTO.getFormaPagamentoTO().getIdFormaPagamento());
				formaPagtoCanalParam.setFormaPagamento(formaPagamento);
			}
			
			FormaPagtoCanalAtndParamTOBuilder formaPagtoCanalAtndParamTOBuilder = new FormaPagtoCanalAtndParamTOBuilder();
			formaPagtoCanalParam.setFormaPagtoCanalAtndParam(formaPagtoCanalAtndParamTOBuilder.createFormaPagtoCanalAtndParam(formaPagtoCanalParamTO.getFormaPagtoCanalAtndParamTO()));
			
		}
		
		return formaPagtoCanalParam;
	}
	
	/**
	 * @param formaPagtoCanalParam
	 * @return
	 * 
	 * Método responsável em transformar um Entity em um TO.
	 */
	public FormaPagtoCanalParamTO createFormaPagtoCanalParamTO(FormaPagtoCanalParam formaPagtoCanalParam) {
		
		FormaPagtoCanalParamTO formaPagtoCanalParamTO = null;
		
		if (formaPagtoCanalParam != null) {
			formaPagtoCanalParamTO = new FormaPagtoCanalParamTO();
			
			if (formaPagtoCanalParam.getCanal() != null) {
				CanalTO canalTO = new CanalTO();
				canalTO.setIdCanal(formaPagtoCanalParam.getCanal().getIdCanal());
				canalTO.setNmCanal(formaPagtoCanalParam.getCanal().getNmCanal());
				formaPagtoCanalParamTO.setCanalTO(canalTO);
			}
			
			FormaPagtoCanalAtndParamTOBuilder formaPagtoCanalAtndParamTOBuilder = new FormaPagtoCanalAtndParamTOBuilder();
			formaPagtoCanalParamTO.setFormaPagtoCanalAtndParamTO(formaPagtoCanalAtndParamTOBuilder.createFormaPagtoCanalAtndParamTO(formaPagtoCanalParam.getFormaPagtoCanalAtndParam()));
			
			formaPagtoCanalParamTO.setIdFormaPagtoCanalParam(formaPagtoCanalParam.getIdFormaPagtoCanalParam());
			formaPagtoCanalParamTO.setNrMaxParcSemJuros(formaPagtoCanalParam.getNrMaxParcSemJuros());
			formaPagtoCanalParamTO.setNrParcelasMax(formaPagtoCanalParam.getNrParcelasMax());
			formaPagtoCanalParamTO.setTaxaJuros(formaPagtoCanalParam.getTaxaJuros());
		}
		
		return formaPagtoCanalParamTO;
	}
	
	/**
	 * @param formaPagtoCanalParamList
	 * @return
	 * 
	 * Método responsável em transformar uma lista de Entity em uma lista de TO.
	 */
	public List<FormaPagtoCanalParamTO> createFormaPagtoCanalParamTOList(List<FormaPagtoCanalParam> formaPagtoCanalParamList) {
		
		List<FormaPagtoCanalParamTO> list = new ArrayList<FormaPagtoCanalParamTO>();
		
		if (formaPagtoCanalParamList != null && formaPagtoCanalParamList.size() > 0) {
			for (FormaPagtoCanalParam formaPagtoCanalParam : formaPagtoCanalParamList) {
				list.add(createFormaPagtoCanalParamTO(formaPagtoCanalParam));
			}
		}
		
		return list;
	}
}
