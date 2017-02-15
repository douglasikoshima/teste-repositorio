package com.indracompany.catalogo.dao.interfaces;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.indracompany.catalogo.dao.MatrizOfertaStatusImportacaoTOBuilder;
import com.indracompany.catalogo.datalayer.ServicoServicoArq;
import com.indracompany.catalogo.to.ImportacaoServicoFixaTO;
import com.indracompany.catalogo.to.ServicoServicoArqItemTO;

public class ServicoServicoArqTOBuilder {
	
	public ImportacaoServicoFixaTO createTO(ServicoServicoArq ent){
		
		ImportacaoServicoFixaTO to = new ImportacaoServicoFixaTO();
		
		to.setId(ent.getIdServicoServicoArq());
		to.setDescErro(ent.getDescErro());
		to.setDtImportacao(ent.getDtImportacao());
		to.setDtProcessamento(ent.getDtProcessamento());
		to.setNmArquivo(ent.getNmArquivo());
		to.setNmUsuarioImportacao(ent.getNmUsuarioImportacao());
		to.setStatusArquivoImportacaoTO(new MatrizOfertaStatusImportacaoTOBuilder().createStatusArquivoImportacaoTO(ent.getMatrizOfertaStatusImportacao()));
		
		return to;
	}
	
	
	public List<ImportacaoServicoFixaTO> createTOList(List<ServicoServicoArq> entList){
		
		List<ImportacaoServicoFixaTO> toList = new ArrayList<ImportacaoServicoFixaTO>();		
		for(ServicoServicoArq ent: entList){
			toList.add(createTO(ent));
		}		
		return toList;
	}
	
	public ServicoServicoArqItemTO createTONative(Object[] o ){
		
		ServicoServicoArqItemTO to = new ServicoServicoArqItemTO();
		String idArquivo = ((BigDecimal) o[0]).toString();
		BigDecimal idItem = (BigDecimal) o[1];
		
		to.setIdServicoServicoArq(Integer.valueOf( idArquivo ));
		to.setIdServicoServicoArqItem(Integer.valueOf( idItem.intValue() ));
		to.setAcao((String) o[2]);
		to.setCdServicoMestre((String) o[3]);
		to.setSgTipoRelacionamento((String) o[4]);
		to.setCdServicoSubordinado((String) o[5]);
		to.setErros((String) o[6]);
		
		return to;
	}


	public List<ServicoServicoArqItemTO> createTOListNative(List<Object[]> entList){
		
		List<ServicoServicoArqItemTO> toList = new ArrayList<ServicoServicoArqItemTO>();
		if(entList != null){			
			for ( Object[] ent : entList ) {
				toList.add(createTONative(ent));				
			}
		}			
		return toList;
	}
}
