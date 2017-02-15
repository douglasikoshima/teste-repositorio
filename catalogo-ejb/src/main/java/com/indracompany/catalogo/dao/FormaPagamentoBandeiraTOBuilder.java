package com.indracompany.catalogo.dao;

import java.util.ArrayList;
import java.util.List;

import com.indracompany.catalogo.datalayer.FormaPagamento;
import com.indracompany.catalogo.datalayer.FormaPagamentoBandeira;
import com.indracompany.catalogo.to.BandeiraTO;
import com.indracompany.catalogo.to.FormaPagamentoBandeiraTO;

public class FormaPagamentoBandeiraTOBuilder {
	
	public FormaPagamentoBandeira createFormaPagamentoBandeira(FormaPagamentoBandeiraTO formaPagamentoBandeiraTO) {
		
		FormaPagamentoBandeira formaPagamentoBandeira = null;
		
		if(formaPagamentoBandeiraTO != null) {
			formaPagamentoBandeira = new FormaPagamentoBandeira();
			formaPagamentoBandeira.setFormaPagamento(new FormaPagamento(formaPagamentoBandeiraTO.getIdFormaPagamento()));
			
			BandeiraTOBuilder bandeiraTOBuilder = new BandeiraTOBuilder();
			formaPagamentoBandeira.setBandeira(bandeiraTOBuilder.createBandeira(formaPagamentoBandeiraTO.getBandeiraTO()));
			
			formaPagamentoBandeira.setDtCriacao(formaPagamentoBandeiraTO.getDtCriacao());
			formaPagamentoBandeira.setNmUsuarioCriacao(formaPagamentoBandeiraTO.getNmUsuarioCriacao());
		}
		
		return formaPagamentoBandeira;
	}
	
	public FormaPagamentoBandeiraTO createFormaPagamentoBandeiraTO(FormaPagamentoBandeira formaPagamentoBandeira) {
		
		FormaPagamentoBandeiraTO formaPagamentoBandeiraTO = null;
		
		if(formaPagamentoBandeira != null) {
			formaPagamentoBandeiraTO = new FormaPagamentoBandeiraTO();
			if (formaPagamentoBandeira.getFormaPagamento() != null) {
				formaPagamentoBandeiraTO.setIdFormaPagamento(formaPagamentoBandeira.getFormaPagamento().getIdFormaPagamento());
			}
			formaPagamentoBandeiraTO.setIdFormaPagamentoBandeira(formaPagamentoBandeira.getIdFormaPagamentoBandeira());
			
			BandeiraTO bandeiraTO = new BandeiraTO();
			bandeiraTO.setIdBandeira(formaPagamentoBandeira.getBandeira().getIdBandeira());
			bandeiraTO.setNmBandeira(formaPagamentoBandeira.getBandeira().getNmBandeira());
			bandeiraTO.setUrlImagem(formaPagamentoBandeira.getBandeira().getUrlImagem());
			formaPagamentoBandeiraTO.setBandeiraTO(bandeiraTO);
		}
		
		return formaPagamentoBandeiraTO;
	}
	
	public List<FormaPagamentoBandeiraTO> createFormaPagamentoBandeiraTOList(List<FormaPagamentoBandeira> formaPagamentoBandeiraList) {
		
		List<FormaPagamentoBandeiraTO> formaPagamentoBandeiraTOList = new ArrayList<FormaPagamentoBandeiraTO>();
		
		if(formaPagamentoBandeiraList != null){
			for(FormaPagamentoBandeira formaPagamentoBandeira : formaPagamentoBandeiraList) {
				formaPagamentoBandeiraTOList.add(createFormaPagamentoBandeiraTO(formaPagamentoBandeira));
			}
		}
		return formaPagamentoBandeiraTOList;
	}
	
	/**
	 * @param formaPagamentoBandeiraList
	 * @return
	 */
	public List<FormaPagamentoBandeira> createFormaPagamentoBandeiraList(List<FormaPagamentoBandeiraTO> formaPagamentoBandeiraList) {
		
		List<FormaPagamentoBandeira> formaPagamentoBandeiraTOList = new ArrayList<FormaPagamentoBandeira>();
		
		if(formaPagamentoBandeiraList != null){
			for(FormaPagamentoBandeiraTO formaPagamentoBandeiraTO : formaPagamentoBandeiraList) {
				formaPagamentoBandeiraTOList.add(createFormaPagamentoBandeira(formaPagamentoBandeiraTO));
			}
		}
		return formaPagamentoBandeiraTOList;
	}

}
