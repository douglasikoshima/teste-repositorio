package com.indracompany.catalogo.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.indracompany.catalogo.datalayer.OfertaServico;
import com.indracompany.catalogo.datalayer.OfertaServicoCategoriaScore;
import com.indracompany.catalogo.to.CategorizacaoAnaliseCreditoTO;

/**
 * @author Luiz Pereira
 *
 */
public class OfertaServicoTOBuilder {


	/**
	 * @param ofertaServico
	 * @return
	 */
	public CategorizacaoAnaliseCreditoTO createCategorizacaoAnaliseCreditoTO(OfertaServico ofertaServico) {
		
		CategorizacaoAnaliseCreditoTO categorizacaoAnaliseCreditoTO = null;
		
		if(ofertaServico != null) {
			categorizacaoAnaliseCreditoTO = new CategorizacaoAnaliseCreditoTO();
			categorizacaoAnaliseCreditoTO.setId(ofertaServico.getIdOfertaServico());
			categorizacaoAnaliseCreditoTO.setNome(ofertaServico.getNmOfertaServico() + " - " + ofertaServico.getCdOfertaServico());
			
			if (ofertaServico.getOfertaServicoCategoriaScore() != null) {
				OfertaServicoCategoriaScore ofertaServicoCategoriaScore = ofertaServico.getOfertaServicoCategoriaScore();
				categorizacaoAnaliseCreditoTO.setIdCategoriaScore(ofertaServicoCategoriaScore.getIdOfertaServicoCategoriaScore());
				categorizacaoAnaliseCreditoTO.setNmCategoria(ofertaServicoCategoriaScore.getCategoriaScore().getNmCategoriaScore());
				categorizacaoAnaliseCreditoTO.setIdCategoria(ofertaServicoCategoriaScore.getCategoriaScore().getIdCategoriaScore());
			}

		}
		
		return categorizacaoAnaliseCreditoTO;
	}
	
	/**
	 * @param ofertaServicoList
	 * @return
	 */
	public List<CategorizacaoAnaliseCreditoTO> createCategorizacaoAnaliseCreditoTOList(List<OfertaServico> ofertaServicoList) {
		
		List<CategorizacaoAnaliseCreditoTO> categorizacaoAnaliseCreditoTOList = new ArrayList<CategorizacaoAnaliseCreditoTO>();
		
		if(ofertaServicoList != null){
			for(OfertaServico ofertaServico : ofertaServicoList) {
				categorizacaoAnaliseCreditoTOList.add(createCategorizacaoAnaliseCreditoTO(ofertaServico));
			}
		}
		return categorizacaoAnaliseCreditoTOList;
	}

	/**
	 * @param ofertaServicoList
	 * @return
	 */
	public List<CategorizacaoAnaliseCreditoTO> createCategorizacaoAnaliseCreditoTOListNative(List<Object[]> ofertaServicoList) {
		
		List<CategorizacaoAnaliseCreditoTO> categorizacaoAnaliseCreditoTOList = new ArrayList<CategorizacaoAnaliseCreditoTO>();
		
		if(ofertaServicoList != null){
			
			for ( Object[] o : ofertaServicoList ) {
				categorizacaoAnaliseCreditoTOList.add(new CategorizacaoAnaliseCreditoTO( ((BigDecimal) o[0]).intValue(), (String) o[1], (String) o[2], ((BigDecimal) o[3]).intValue(), ((BigDecimal) o[4]).doubleValue(), ((BigDecimal) o[5]).intValue(), (String) o[6] ) );
				
			}

		}
		return categorizacaoAnaliseCreditoTOList;
	}

}
