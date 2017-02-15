package com.indracompany.catalogo.dao;

import java.util.LinkedList;
import java.util.List;

import com.indracompany.catalogo.datalayer.ClassificacaoMultimidia;
import com.indracompany.catalogo.to.ClassificacaoMultimidiaTO;

public class ClassificacaoMultimidiaTOBuilder {
	
	public ClassificacaoMultimidiaTO createClassificacaoMultimidiaTO(ClassificacaoMultimidia tipoMultimidia) {
		ClassificacaoMultimidiaTO tipoMultimidiaTO = new ClassificacaoMultimidiaTO();
		
		tipoMultimidiaTO.setIdClassificacao(tipoMultimidia.getIdClassificacao());
		tipoMultimidiaTO.setNomeClassificacao(tipoMultimidia.getNomeClassificacao());
		tipoMultimidiaTO.setSiglaClassificacao(tipoMultimidia.getSiglaClassificacao());
		tipoMultimidiaTO.setDataCriacao(tipoMultimidia.getDataCriacao());
		tipoMultimidiaTO.setDataUltimaAlteracao(tipoMultimidia.getDataUltimaAlteracao());
		tipoMultimidiaTO.setNomeUsuarioCriacao(tipoMultimidia.getNomeUsuarioCriacao());
		tipoMultimidiaTO.setNomeUsuarioUltimaAlteracao(tipoMultimidia.getNomeUsuarioUltimaAlteracao());
		
		return tipoMultimidiaTO;
	}
	
	public List<ClassificacaoMultimidiaTO> createClassificacaoMultimidiaTOList(List<ClassificacaoMultimidia> list){
		List<ClassificacaoMultimidiaTO> tipoMultimidiaTOList = new LinkedList<ClassificacaoMultimidiaTO>();
		for (ClassificacaoMultimidia tipoMultimidia : list) {
			tipoMultimidiaTOList.add(createClassificacaoMultimidiaTO(tipoMultimidia));
		}
		return tipoMultimidiaTOList;
	}
	
}