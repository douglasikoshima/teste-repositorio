package com.indracompany.catalogo.dao.interfaces;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.indracompany.catalogo.dao.MatrizOfertaStatusImportacaoTOBuilder;
import com.indracompany.catalogo.datalayer.SolComGrupoComArq;
import com.indracompany.catalogo.to.ImportacaoServicoFixaTO;
import com.indracompany.catalogo.to.SolComGrupoComArqItemTO;

public class SolComGrupoComArqTOBuilder {
	
	public ImportacaoServicoFixaTO createTO(SolComGrupoComArq ent){
		
		ImportacaoServicoFixaTO to = new ImportacaoServicoFixaTO();
		
		to.setId(ent.getIdSolComGrupoComArq());
		to.setDescErro(ent.getDescErro());
		to.setDtImportacao(ent.getDtImportacao());
		to.setDtProcessamento(ent.getDtProcessamento());
		to.setNmArquivo(ent.getNmArquivo());
		to.setNmUsuarioImportacao(ent.getNmUsuarioImportacao());
		to.setStatusArquivoImportacaoTO(new MatrizOfertaStatusImportacaoTOBuilder().createStatusArquivoImportacaoTO(ent.getMatrizOfertaStatusImportacao()));
		
		return to;
	}
		
	public List<ImportacaoServicoFixaTO> createTOList(List<SolComGrupoComArq> entList){
		
		List<ImportacaoServicoFixaTO> toList = new ArrayList<ImportacaoServicoFixaTO>();		
		for(SolComGrupoComArq ent: entList){
			toList.add(createTO(ent));
		}		
		return toList;
	}
	
	
	
	public SolComGrupoComArqItemTO createTONative(Object[] o ){
		
		SolComGrupoComArqItemTO to = new SolComGrupoComArqItemTO();		
		BigDecimal idArquivo = (BigDecimal) o[0];
		BigDecimal idItem = (BigDecimal) o[1];
		
		to.setIdSolComGrupoComArq(Integer.valueOf( idArquivo.intValue() ));
		to.setIdSolComGrupoComArqItem(Integer.valueOf( idItem.intValue() ));
		to.setAcao((String) o[2]);
		to.setCdSolicitacaoComercial((String) o[3]);
		to.setCdGrupoComercial((String) o[4]);
		to.setCdPlanoMinutos((String) o[5]);
		to.setCdAreaConcorrencia((String) o[6]);
		to.setErros((String) o[7]);
		return to;
	}


	public List<SolComGrupoComArqItemTO> createTOListNative(List<Object[]> entList){
		
		List<SolComGrupoComArqItemTO> toList = new ArrayList<SolComGrupoComArqItemTO>();
		if(entList != null){			
			for ( Object[] ent : entList ) {
				toList.add(createTONative(ent));				
			}
		}			
		return toList;
	}
}
