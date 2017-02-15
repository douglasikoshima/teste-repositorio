package com.indracompany.catalogo.dao;

import java.util.LinkedList;
import java.util.List;

import com.indracompany.catalogo.datalayer.TipoMultimidia;
import com.indracompany.catalogo.to.TipoMultimidiaTO;

public class TipoMultimidiaTOBuilder {
	
	public TipoMultimidiaTO createTipoMultimidiaTO(TipoMultimidia tipoMultimidia) {
		TipoMultimidiaTO tipoMultimidiaTO = new TipoMultimidiaTO();
		
		tipoMultimidiaTO.setIdTipoMultimidia(tipoMultimidia.getIdTipoMultimidia());
		tipoMultimidiaTO.setNomeTipoMultimidia(tipoMultimidia.getNomeTipoMultimidia());
		tipoMultimidiaTO.setSiglaTipoMultimidia(tipoMultimidia.getSiglaTipoMultimidia());
		tipoMultimidiaTO.setDataCriacao(tipoMultimidia.getDataCriacao());
		tipoMultimidiaTO.setDataUltimaAlteracao(tipoMultimidia.getDataUltimaAlteracao());
		tipoMultimidiaTO.setNomeUsuarioCriacao(tipoMultimidia.getNomeUsuarioCriacao());
		tipoMultimidiaTO.setNomeUsuarioUltimaAlteracao(tipoMultimidia.getNomeUsuarioUltimaAlteracao());
		
		return tipoMultimidiaTO;
	}
	
	public List<TipoMultimidiaTO> createTipoMultimidiaTOList(List<TipoMultimidia> list){
		List<TipoMultimidiaTO> tipoMultimidiaTOList = new LinkedList<TipoMultimidiaTO>();
		for (TipoMultimidia tipoMultimidia : list) {
			tipoMultimidiaTOList.add(createTipoMultimidiaTO(tipoMultimidia));
		}
		return tipoMultimidiaTOList;
	}
	
}