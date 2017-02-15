package com.indracompany.catalogo.dao;

import java.util.ArrayList;
import java.util.List;

import com.indracompany.catalogo.datalayer.CategoriaScore;
import com.indracompany.catalogo.datalayer.Servico;
import com.indracompany.catalogo.to.CategoriaScoreTO;

public class CategoriaScoreTOBuilder {
	
	private boolean verificarAssociacoes;
	
	public CategoriaScoreTOBuilder() {
		this(true);
	}
	
	public CategoriaScoreTOBuilder(boolean verificarAssociacoes) {
		this.verificarAssociacoes = verificarAssociacoes;
	}
	
	public CategoriaScore createCategoriaScore(CategoriaScoreTO categoriaScoreTO){
		
		CategoriaScore categoriaScore = new CategoriaScore();
		
		if(categoriaScore != null){
			categoriaScore.setCdCategoriaScore(categoriaScoreTO.getCdCategoriaScore());
			categoriaScore.setClassificacaoCategoriaScore(new ClassificacaoCategoriaScoreTOBuilder().createClassificacaoCategoriaScore(categoriaScoreTO.getClassificacaoCategoriaScoreTO()));
			categoriaScore.setDtCriacao(categoriaScoreTO.getDtCriacao());
			categoriaScore.setDtUltimaAlteracao(categoriaScoreTO.getDtUltimaAlteracao());
			categoriaScore.setIdCategoriaScore(categoriaScoreTO.getIdCategoriaScore());
			categoriaScore.setNmCategoriaScore(categoriaScoreTO.getNmCategoriaScore());
			categoriaScore.setNmUsuarioAlteracao(categoriaScoreTO.getNmUsuarioAlteracao());
			categoriaScore.setNmUsuarioCriacao(categoriaScoreTO.getNmUsuarioCriacao());
		}
		
		return categoriaScore; 
	}
	
	
	public CategoriaScoreTO createCategoriaScoreTO(CategoriaScore categoriaScore){
		
		CategoriaScoreTO categoriaScoreTO = new CategoriaScoreTO();
		
		if(categoriaScore != null){
			categoriaScoreTO.setCdCategoriaScore(categoriaScore.getCdCategoriaScore());
			categoriaScoreTO.setClassificacaoCategoriaScoreTO(new ClassificacaoCategoriaScoreTOBuilder().createClassificacaoCategoriaScoreTO(categoriaScore.getClassificacaoCategoriaScore()));
			categoriaScoreTO.setDtCriacao(categoriaScore.getDtCriacao());
			categoriaScoreTO.setDtUltimaAlteracao(categoriaScore.getDtUltimaAlteracao());
			categoriaScoreTO.setIdCategoriaScore(categoriaScore.getIdCategoriaScore());
			categoriaScoreTO.setNmCategoriaScore(categoriaScore.getNmCategoriaScore());
			categoriaScoreTO.setNmUsuarioAlteracao(categoriaScore.getNmUsuarioAlteracao());
			categoriaScoreTO.setNmUsuarioCriacao(categoriaScore.getNmUsuarioCriacao());
			if (verificarAssociacoes) {
				categoriaScoreTO.setPossuiAssociacaoPlano(!categoriaScore.getPlanoCategoriaScoreList().isEmpty());
				categoriaScoreTO.setPossuiAssociacaoOfertaServico(!categoriaScore.getOfertaServicoCategoriaScoreList().isEmpty());
	            if (!categoriaScore.getServicoCategoriaScoreList().isEmpty()) {
	                Servico servico = categoriaScore.getServicoCategoriaScoreList().iterator().next().getServico();
	                if (servico != null) {
	                    categoriaScoreTO.setPossuiAssociacaoServico(servico.getInFixa().equalsIgnoreCase("N"));
	                    categoriaScoreTO.setPossuiAssociacaoServicoFixa(servico.getInFixa().equalsIgnoreCase("S"));
	                } else {
	                    categoriaScoreTO.setPossuiAssociacaoServico(false);
	                    categoriaScoreTO.setPossuiAssociacaoServicoFixa(false);
	                }
	            } else {
	                categoriaScoreTO.setPossuiAssociacaoServicoFixa(false);
	                categoriaScoreTO.setPossuiAssociacaoServico(false);
	            }
			}
		}
		
		return categoriaScoreTO;
	}
	
	public List<CategoriaScoreTO> createCategoriaScoreTOList(List<CategoriaScore> categoriaScoreList){
		
		List<CategoriaScoreTO> categoriaScoreTOList = new ArrayList<CategoriaScoreTO>();
		
		for(CategoriaScore categoriaScore : categoriaScoreList){
			categoriaScoreTOList.add(createCategoriaScoreTO(categoriaScore));
		}

		return categoriaScoreTOList;
	}
	
	public boolean isVerificarAssociacoes() {
		return verificarAssociacoes;
	}
	
	public void setVerificarAssociacoes(boolean verificarAssociacoes) {
		this.verificarAssociacoes = verificarAssociacoes;
	}
	
}