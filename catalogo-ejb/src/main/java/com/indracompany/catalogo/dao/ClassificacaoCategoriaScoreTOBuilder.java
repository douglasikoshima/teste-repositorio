package com.indracompany.catalogo.dao;

import java.util.ArrayList;
import java.util.List;

import com.indracompany.catalogo.datalayer.ClassificacaoCategoriaScore;
import com.indracompany.catalogo.to.ClassificacaoCategoriaScoreTO;

public class ClassificacaoCategoriaScoreTOBuilder {
	
	public ClassificacaoCategoriaScore createClassificacaoCategoriaScore(ClassificacaoCategoriaScoreTO classificacaoCategoriaScoreTO){
		
		ClassificacaoCategoriaScore classificacaoCategoriaScore = new ClassificacaoCategoriaScore();
		
		if(classificacaoCategoriaScoreTO != null) {
			classificacaoCategoriaScore.setIdClassificacaoCategoriaScore(classificacaoCategoriaScoreTO.getIdClassificacaoCategoriaScore());
			classificacaoCategoriaScore.setNmClassificacaoCategoriaScore(classificacaoCategoriaScoreTO.getNmClassificacaoCategoriaScore());
			classificacaoCategoriaScore.setCdClassificacaoCategoriaScore(classificacaoCategoriaScoreTO.getCdClassificacaoCategoriaScore());
			classificacaoCategoriaScore.setDtCriacao(classificacaoCategoriaScoreTO.getDtCriacao());
			classificacaoCategoriaScore.setDtUltimaAlteracao(classificacaoCategoriaScoreTO.getDtUltimaAlteracao());
			classificacaoCategoriaScore.setNmUsuarioAlteracao(classificacaoCategoriaScoreTO.getNmUsuarioAlteracao());
			classificacaoCategoriaScore.setNmUsuarioCriacao(classificacaoCategoriaScoreTO.getNmUsuarioCriacao());
		}
		
		return classificacaoCategoriaScore;
	}
	
	public ClassificacaoCategoriaScoreTO createClassificacaoCategoriaScoreTO(ClassificacaoCategoriaScore classificacaoCategoriaScore) {
		
		ClassificacaoCategoriaScoreTO classificacaoCategoriaScoreTO = new ClassificacaoCategoriaScoreTO();
		
		if(classificacaoCategoriaScore != null){
			classificacaoCategoriaScoreTO.setIdClassificacaoCategoriaScore(classificacaoCategoriaScore.getIdClassificacaoCategoriaScore());
			classificacaoCategoriaScoreTO.setNmClassificacaoCategoriaScore(classificacaoCategoriaScore.getNmClassificacaoCategoriaScore());
			classificacaoCategoriaScoreTO.setCdClassificacaoCategoriaScore(classificacaoCategoriaScore.getCdClassificacaoCategoriaScore());
			classificacaoCategoriaScoreTO.setDtCriacao(classificacaoCategoriaScore.getDtCriacao());
			classificacaoCategoriaScoreTO.setDtUltimaAlteracao(classificacaoCategoriaScore.getDtUltimaAlteracao());
			classificacaoCategoriaScoreTO.setNmUsuarioAlteracao(classificacaoCategoriaScore.getNmUsuarioAlteracao());
			classificacaoCategoriaScoreTO.setNmUsuarioCriacao(classificacaoCategoriaScore.getNmUsuarioCriacao());
		}
		
		return classificacaoCategoriaScoreTO;
	}
	
	public List<ClassificacaoCategoriaScoreTO> createClassificacaoCategoriaScoreTOList(List<ClassificacaoCategoriaScore> classificacaoCategoriaScoreList) {
		
		List<ClassificacaoCategoriaScoreTO> classificacaoCategoriaScoreTOList = new ArrayList<ClassificacaoCategoriaScoreTO>();
		
		for(ClassificacaoCategoriaScore classificacaoCategoriaScore : classificacaoCategoriaScoreList){
			classificacaoCategoriaScoreTOList.add(createClassificacaoCategoriaScoreTO(classificacaoCategoriaScore));
		}
		
		return classificacaoCategoriaScoreTOList;
	}
	
}
