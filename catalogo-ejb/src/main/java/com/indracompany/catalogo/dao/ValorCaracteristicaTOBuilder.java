package com.indracompany.catalogo.dao;

import java.util.ArrayList;
import java.util.List;

import com.indracompany.catalogo.datalayer.ValorCaracteristica;
import com.indracompany.catalogo.to.ValorCaracteristicaTO;

public class ValorCaracteristicaTOBuilder {

	
	public ValorCaracteristica createValorCaracteristica(ValorCaracteristicaTO valorCaracteristicaTO) {
		ValorCaracteristica valorCaracteristica = null;
		
		CaracteristicaTOBuilder caracteristicaTOBuilder = new CaracteristicaTOBuilder();
		
		if (valorCaracteristicaTO != null) {
			valorCaracteristica = new ValorCaracteristica();
			valorCaracteristica.setIdValorCaracteristica(valorCaracteristicaTO.getIdValorCaracteristica());
			valorCaracteristica.setDtUltimaAlteracao(valorCaracteristicaTO.getDtUltimaAlteracao());
			valorCaracteristica.setNmUsuarioCriacao(valorCaracteristicaTO.getNmUsuarioCriacao());
			valorCaracteristica.setDtCriacao(valorCaracteristicaTO.getDtCriacao());
			valorCaracteristica.setNmUsuarioCriacao(valorCaracteristicaTO.getNmUsuarioCriacao());
			valorCaracteristica.setValor(valorCaracteristicaTO.getValor());
			valorCaracteristica.setCaracteristica(caracteristicaTOBuilder.createCaracteristica(valorCaracteristicaTO.getCaracteristicaTO()));
		}
		
		return valorCaracteristica;
	}
		
	public ValorCaracteristicaTO createValorCaracteristicaTO(ValorCaracteristica valorCaracteristica) {
		ValorCaracteristicaTO valorCaracteristicaTO = null;
		CaracteristicaTOBuilder caracteristicaTOBuilder = new CaracteristicaTOBuilder();
		
		if (valorCaracteristica != null) {
			valorCaracteristicaTO = new ValorCaracteristicaTO();
			valorCaracteristicaTO.setIdValorCaracteristica(valorCaracteristica.getIdValorCaracteristica());
			valorCaracteristicaTO.setDtUltimaAlteracao(valorCaracteristica.getDtUltimaAlteracao());
			valorCaracteristicaTO.setNmUsuarioCriacao(valorCaracteristica.getNmUsuarioCriacao());
			valorCaracteristicaTO.setDtCriacao(valorCaracteristica.getDtCriacao());
			valorCaracteristicaTO.setNmUsuarioCriacao(valorCaracteristica.getNmUsuarioCriacao());
			valorCaracteristicaTO.setValor(valorCaracteristica.getValor());
			valorCaracteristicaTO.setCaracteristicaTO(caracteristicaTOBuilder.createCaracteristicaTO(valorCaracteristica.getCaracteristica()));
			valorCaracteristicaTO.setExistGrupoProdutoCaracteristica((valorCaracteristica.getGrupoProdutoCaracteristicaList() != null && valorCaracteristica.getGrupoProdutoCaracteristicaList().size() > 0));
		}
		
		return valorCaracteristicaTO;
		
	}

	public ValorCaracteristicaTO createBasicValorCaracteristicaTO(ValorCaracteristica valorCaracteristica) {
		ValorCaracteristicaTO valorCaracteristicaTO = null;
		
		if (valorCaracteristica != null) {
			valorCaracteristicaTO = new ValorCaracteristicaTO();
			valorCaracteristicaTO.setIdValorCaracteristica(valorCaracteristica.getIdValorCaracteristica());
			valorCaracteristicaTO.setDtUltimaAlteracao(valorCaracteristica.getDtUltimaAlteracao());
			valorCaracteristicaTO.setNmUsuarioCriacao(valorCaracteristica.getNmUsuarioCriacao());
			valorCaracteristicaTO.setDtCriacao(valorCaracteristica.getDtCriacao());
			valorCaracteristicaTO.setNmUsuarioCriacao(valorCaracteristica.getNmUsuarioCriacao());
			valorCaracteristicaTO.setValor(valorCaracteristica.getValor());
		}
		
		return valorCaracteristicaTO;
		
	}
	
	public List<ValorCaracteristicaTO> createValorCaracteristicaTOList(List<ValorCaracteristica> valorCaracteristicaList) {
		
		ArrayList<ValorCaracteristicaTO> valorCaracteristicaTOList = new ArrayList<ValorCaracteristicaTO>(); 
		
		if (valorCaracteristicaList != null && !valorCaracteristicaList.isEmpty())
		for( ValorCaracteristica valorCaracteristica : valorCaracteristicaList) {
			valorCaracteristicaTOList.add(createValorCaracteristicaTO(valorCaracteristica));
		}
		
		return valorCaracteristicaTOList;
		
	}

	public List<ValorCaracteristicaTO> createBasicValorCaracteristicaTOList(List<ValorCaracteristica> valorCaracteristicaList) {
		
		ArrayList<ValorCaracteristicaTO> valorCaracteristicaTOList = new ArrayList<ValorCaracteristicaTO>(); 
		
		if (valorCaracteristicaList != null && !valorCaracteristicaList.isEmpty())
		for( ValorCaracteristica valorCaracteristica : valorCaracteristicaList) {
			valorCaracteristicaTOList.add(createBasicValorCaracteristicaTO(valorCaracteristica));
		}
		
		return valorCaracteristicaTOList;
		
	}
}
