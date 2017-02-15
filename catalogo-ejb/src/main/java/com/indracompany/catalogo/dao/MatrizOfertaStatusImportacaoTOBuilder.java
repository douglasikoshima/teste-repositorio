package com.indracompany.catalogo.dao;

import java.util.ArrayList;
import java.util.List;

import com.indracompany.catalogo.datalayer.MatrizOfertaStatusImportacao;
import com.indracompany.catalogo.to.StatusArquivoImportacaoTO;

/**
 * @author equipe Catalogo
 *
 * Classe respons�vel em transformar TO em Entity e Entiy em TO.
 */
public class MatrizOfertaStatusImportacaoTOBuilder {
	

	public StatusArquivoImportacaoTO createStatusArquivoImportacaoTO(MatrizOfertaStatusImportacao importacao) {
		
		StatusArquivoImportacaoTO to = null;
		
		if (importacao != null) {
			to = new StatusArquivoImportacaoTO(
				importacao.getIdMatrizOfertaStatusImportacao(),
				importacao.getDscStatusImportacao()
			);			
		}		
		return to;
	}
	
	/**
	 * @param analiseCreditoList
	 * @return
	 * 
	 * M�todo respons�vel em transformar uma lista de Entity em uma lista de TO.
	 */
	public List<StatusArquivoImportacaoTO> createStatusArquivoImportacaoTOList(List<MatrizOfertaStatusImportacao> importacaoList) {
		
		List<StatusArquivoImportacaoTO> list = new ArrayList<StatusArquivoImportacaoTO>();
		
		if (importacaoList != null && importacaoList.size() > 0) {
			for (MatrizOfertaStatusImportacao importacao : importacaoList) {
				list.add(createStatusArquivoImportacaoTO(importacao));
			}
		}
		
		return list;
	}
	
	
}
