package com.indracompany.catalogo.dao;

import java.util.ArrayList;
import java.util.List;

import com.indracompany.catalogo.datalayer.CanalAtendimento;
import com.indracompany.catalogo.datalayer.FormaPagtoCanalAtndParam;
import com.indracompany.catalogo.datalayer.FormaPagtoCanalParam;
import com.indracompany.catalogo.to.CanalAtendimentoTO;
import com.indracompany.catalogo.to.FormaPagtoCanalAtndParamTO;

/**
 * @author Luiz Pereira
 *
 * Classe responsável em transformar TO em Entity e Entiy em TO.
 */
public class FormaPagtoCanalAtndParamTOBuilder {
	
	/**
	 * @param formaPagtoCanalAtndParamTO
	 * @return
	 * 
	 *  Método responsável em transformar um TO em um Entity.
	 */
	public FormaPagtoCanalAtndParam createFormaPagtoCanalAtndParam(FormaPagtoCanalAtndParamTO formaPagtoCanalAtndParamTO) {
		
		FormaPagtoCanalAtndParam formaPagtoCanalAtndParam = null;
		
		if (formaPagtoCanalAtndParamTO != null) {
			formaPagtoCanalAtndParam = new FormaPagtoCanalAtndParam();
			
			if (formaPagtoCanalAtndParamTO.getCanalAtendimentoTO() != null) {
				CanalAtendimento canalAtendimento = new CanalAtendimento();
				canalAtendimento.setIdCanalAtendimento(formaPagtoCanalAtndParamTO.getCanalAtendimentoTO().getIdCanalAtendimento());
				formaPagtoCanalAtndParam.setCanalAtendimento(canalAtendimento);
			}
			
			formaPagtoCanalAtndParam.setCdInstituicaoFinanceira(formaPagtoCanalAtndParamTO.getCdInstituicaoFinanceira());
			formaPagtoCanalAtndParam.setDtCriacao(formaPagtoCanalAtndParamTO.getDtCriacao());
			formaPagtoCanalAtndParam.setDtUltimaAlteracao(formaPagtoCanalAtndParamTO.getDtUltimaAlteracao());
			
			FormaPagtoCanalParam formaPagtoCanalParam = new FormaPagtoCanalParam();
			formaPagtoCanalParam.setIdFormaPagtoCanalParam(formaPagtoCanalAtndParamTO.getFormaPagtoCanalParamTO().getIdFormaPagtoCanalParam());
			
			formaPagtoCanalAtndParam.setFormaPagtoCanalParam(formaPagtoCanalParam);
			
			formaPagtoCanalAtndParam.setIdFormaPagtoCanalAtndParam(formaPagtoCanalAtndParamTO.getIdFormaPagtoCanalAtndParam());
			formaPagtoCanalAtndParam.setNmUsuarioCriacao(formaPagtoCanalAtndParamTO.getNmUsuarioCriacao());
			formaPagtoCanalAtndParam.setNmUsuarioUltimaAlteracao(formaPagtoCanalAtndParamTO.getNmUsuarioUltimaAlteracao());
			formaPagtoCanalAtndParam.setValorDesconto(formaPagtoCanalAtndParamTO.getValorDesconto());
			formaPagtoCanalAtndParam.setVlParcelaMinima(formaPagtoCanalAtndParamTO.getVlParcelaMinima());
			
		}
		
		return formaPagtoCanalAtndParam;
	}
	
	/**
	 * @param formaPagtoCanalAtndParam
	 * @return
	 * 
	 * Método responsável em transformar um Entity em um TO.
	 */
	public FormaPagtoCanalAtndParamTO createFormaPagtoCanalAtndParamTO(FormaPagtoCanalAtndParam formaPagtoCanalAtndParam) {
		
		FormaPagtoCanalAtndParamTO formaPagtoCanalAtndParamTO = null;
		
		if (formaPagtoCanalAtndParam != null) {
			formaPagtoCanalAtndParamTO = new FormaPagtoCanalAtndParamTO();
			
			
			if (formaPagtoCanalAtndParam.getCanalAtendimento() != null) {
				CanalAtendimentoTO canalAtendimentoTO = new CanalAtendimentoTO();
				canalAtendimentoTO.setIdCanalAtendimento(formaPagtoCanalAtndParam.getCanalAtendimento().getIdCanalAtendimento());
				canalAtendimentoTO.setNmCanal(formaPagtoCanalAtndParam.getCanalAtendimento().getNmCanal());
				formaPagtoCanalAtndParamTO.setCanalAtendimentoTO(canalAtendimentoTO);
			}
			
			formaPagtoCanalAtndParamTO.setCdInstituicaoFinanceira(formaPagtoCanalAtndParam.getCdInstituicaoFinanceira());
			formaPagtoCanalAtndParamTO.setDtCriacao(formaPagtoCanalAtndParam.getDtCriacao());
			formaPagtoCanalAtndParamTO.setDtUltimaAlteracao(formaPagtoCanalAtndParam.getDtUltimaAlteracao());
			formaPagtoCanalAtndParamTO.setIdFormaPagtoCanalAtndParam(formaPagtoCanalAtndParam.getIdFormaPagtoCanalAtndParam());
			formaPagtoCanalAtndParamTO.setNmUsuarioCriacao(formaPagtoCanalAtndParam.getNmUsuarioCriacao());
			formaPagtoCanalAtndParamTO.setNmUsuarioUltimaAlteracao(formaPagtoCanalAtndParam.getNmUsuarioUltimaAlteracao());
			formaPagtoCanalAtndParamTO.setValorDesconto(formaPagtoCanalAtndParam.getValorDesconto());
			formaPagtoCanalAtndParamTO.setVlParcelaMinima(formaPagtoCanalAtndParam.getVlParcelaMinima());
		}
		
		return formaPagtoCanalAtndParamTO;
	}
	
	/**
	 * @param formaPagtoCanalAtndParamList
	 * @return
	 * 
	 * Método responsável em transformar uma lista de Entity em uma lista de TO.
	 */
	public List<FormaPagtoCanalAtndParamTO> createFormaPagtoCanalAtndParamTOList(List<FormaPagtoCanalAtndParam> formaPagtoCanalAtndParamList) {
		
		List<FormaPagtoCanalAtndParamTO> list = new ArrayList<FormaPagtoCanalAtndParamTO>();
		
		if (formaPagtoCanalAtndParamList != null && formaPagtoCanalAtndParamList.size() > 0) {
			for (FormaPagtoCanalAtndParam formaPagtoCanalAtndParam : formaPagtoCanalAtndParamList) {
				list.add(createFormaPagtoCanalAtndParamTO(formaPagtoCanalAtndParam));
			}
		}
		
		return list;
	}
}
