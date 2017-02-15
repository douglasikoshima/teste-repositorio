package com.indracompany.catalogo.dao.interfaces;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.indracompany.catalogo.dao.MatrizOfertaStatusImportacaoTOBuilder;
import com.indracompany.catalogo.datalayer.SCEncargoGCPMACArq;
import com.indracompany.catalogo.to.ImportacaoServicoFixaTO;
import com.indracompany.catalogo.to.SCEncargoGruComArqItemTO;

public class SCEncargoGCPMACArqTOBuilder {
	
	public ImportacaoServicoFixaTO createTO(SCEncargoGCPMACArq ent){
		
		ImportacaoServicoFixaTO to = new ImportacaoServicoFixaTO();
		
		to.setId(ent.getIdSCEncargoGCPMACArq());
		to.setDescErro(ent.getDescErro());
		to.setDtImportacao(ent.getDtImportacao());
		to.setDtProcessamento(ent.getDtProcessamento());
		to.setNmArquivo(ent.getNmArquivo());
		to.setNmUsuarioImportacao(ent.getNmUsuarioImportacao());
		to.setStatusArquivoImportacaoTO(new MatrizOfertaStatusImportacaoTOBuilder().createStatusArquivoImportacaoTO(ent.getMatrizOfertaStatusImportacao()));
		
		return to;
	}
		
	public List<ImportacaoServicoFixaTO> createTOList(List<SCEncargoGCPMACArq> entList){
		
		List<ImportacaoServicoFixaTO> toList = new ArrayList<ImportacaoServicoFixaTO>();		
		for(SCEncargoGCPMACArq ent: entList){
			toList.add(createTO(ent));
		}		
		return toList;
	}
	
	
	
	public SCEncargoGruComArqItemTO createTONative(Object[] o ){
		
		SCEncargoGruComArqItemTO to = new SCEncargoGruComArqItemTO();		
		BigDecimal idArquivo = (BigDecimal) o[0];
		BigDecimal idItem = (BigDecimal) o[1];
		
		to.setIdSCEncargoGruComArq(Integer.valueOf( idArquivo.intValue() ));
		to.setIdSCEncargoGruComArqItem(Integer.valueOf( idItem.intValue() ));
		to.setAcao((String) o[2]);
		to.setCdSolicitacaoComercial((String) o[3]);
		to.setCdEncargo((String) o[4]);
		to.setCdPlanoFinanciamento((String) o[5]);
		to.setCdGrupoComercial((String) o[6]);
		to.setCdPlanoMinutos((String) o[7]);
		to.setCdAreaConcorrencia((String) o[8]);
		to.setErros((String) o[9]);
		return to;
	}


	public List<SCEncargoGruComArqItemTO> createTOListNative(List<Object[]> entList){
		
		List<SCEncargoGruComArqItemTO> toList = new ArrayList<SCEncargoGruComArqItemTO>();
		if(entList != null){			
			for ( Object[] ent : entList ) {
				toList.add(createTONative(ent));				
			}
		}			
		return toList;
	}
}
