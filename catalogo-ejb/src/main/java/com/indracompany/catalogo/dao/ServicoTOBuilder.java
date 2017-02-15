package com.indracompany.catalogo.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.indracompany.catalogo.datalayer.Plataforma;
import com.indracompany.catalogo.datalayer.Servico;
import com.indracompany.catalogo.datalayer.ServicoUfRestricao;
import com.indracompany.catalogo.to.CategorizacaoAnaliseCreditoTO;
import com.indracompany.catalogo.to.PlanoServicoUfRestricaoTO;
import com.indracompany.catalogo.to.UfTO;

/**
 * @author Luiz Pereira
 *
 */
public class ServicoTOBuilder {
	
	private static Logger logger = Logger.getLogger(ServicoTOBuilder.class); 


	/**
	 * @param servico
	 * @return
	 */
	public CategorizacaoAnaliseCreditoTO createCategorizacaoAnaliseCreditoTO(Servico servico) {
		
		CategorizacaoAnaliseCreditoTO categorizacaoAnaliseCreditoTO = null;
		
		if(servico != null) {
			categorizacaoAnaliseCreditoTO = new CategorizacaoAnaliseCreditoTO();
			categorizacaoAnaliseCreditoTO.setId(servico.getIdServico());
			categorizacaoAnaliseCreditoTO.setNome(servico.getNmComercial());
			
			Plataforma plataforma = null;
			
			if (servico.getSistemaServico() != null && servico.getSistemaServico().getSistema().getNmSistema().equals("NGIN")) {
				plataforma = new Plataforma();
				plataforma.setIdPlataforma(1);
				plataforma.setNmPlataforma("PRÉ-PAGO");
			} else {
				if (servico.getServicoPlataformaList() != null && servico.getServicoPlataformaList().size() > 0) {
					plataforma = servico.getServicoPlataformaList().get(0).getPlataforma();
					
				}
			}
			
			if (plataforma != null) {
				categorizacaoAnaliseCreditoTO.setNmPlataforma(plataforma.getNmPlataforma());
				categorizacaoAnaliseCreditoTO.setIdPlataforma(plataforma.getIdPlataforma());
			}
			
			if (servico.getServicoCategoriaScore() != null) {
				categorizacaoAnaliseCreditoTO.setNmCategoria(servico.getServicoCategoriaScore().getCategoriaScore().getNmCategoriaScore());
				categorizacaoAnaliseCreditoTO.setIdCategoria(servico.getServicoCategoriaScore().getCategoriaScore().getIdCategoriaScore());
			}
			
		}
		
		return categorizacaoAnaliseCreditoTO;
	}
	
	/**
	 * @param servicoList
	 * @return
	 */
	public List<CategorizacaoAnaliseCreditoTO> createCategorizacaoAnaliseCreditoTOList(List<Servico> servicoList) {
		
		List<CategorizacaoAnaliseCreditoTO> categorizacaoAnaliseCreditoTOList = new ArrayList<CategorizacaoAnaliseCreditoTO>();
		
		if(servicoList != null){
			for(Servico servico : servicoList) {
				categorizacaoAnaliseCreditoTOList.add(createCategorizacaoAnaliseCreditoTO(servico));
			}
		}
		return categorizacaoAnaliseCreditoTOList;
	}
	
	public List<PlanoServicoUfRestricaoTO> createPlanoServicoUfRestricaoTOList(List<Servico> servicoList) {
		
		logger.debug(">> createPlanoServicoUfRestricaoTOList()");
		
		List<PlanoServicoUfRestricaoTO> toList = new ArrayList<PlanoServicoUfRestricaoTO>();
		PlanoServicoUfRestricaoTO restricaoTO;
		UfTO ufTO;
		
		for (Servico s : servicoList) {
			restricaoTO = new PlanoServicoUfRestricaoTO();
			restricaoTO.setId(s.getIdServico().longValue());
			restricaoTO.setNome(s.getNmComercial());
			restricaoTO.setCodigo(s.getSistemaServico().getCdCodigo());
			
			for (ServicoUfRestricao sUf : s.getServicoUfRestricaoList()) {
				ufTO = new UfTO();
				ufTO.setIdUf(sUf.getUf().getIdUf().longValue());
				ufTO.setNmUf(sUf.getUf().getNmUf());
				restricaoTO.getUfTOList().add(ufTO);
			}
			toList.add(restricaoTO);
		}
		
		logger.debug("<< createPlanoServicoUfRestricaoTOList()");
		
		return toList;
	}

}
