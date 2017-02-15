package com.indracompany.catalogo.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.indracompany.catalogo.datalayer.Plano;
import com.indracompany.catalogo.datalayer.PlanoUfRestricao;
import com.indracompany.catalogo.datalayer.Uf;
import com.indracompany.catalogo.to.PlanoServicoUfRestricaoTO;
import com.indracompany.catalogo.to.UfTO;

public class PlanoUfRestricaoTOBuilder {
	
	private static Logger logger = Logger.getLogger(PlanoUfRestricaoTOBuilder.class);
	
	public List<PlanoUfRestricao> createPlanoUfRestricaoList(PlanoServicoUfRestricaoTO to) {
		
		logger.debug(">> createPlanoUfRestricaoList()");
		
		List<PlanoUfRestricao> planoUfRestricaoList = new ArrayList<PlanoUfRestricao>();
		PlanoUfRestricao planoUfRestricao;
		
		for ( UfTO ufTO : to.getUfTOList()) {
			planoUfRestricao = new PlanoUfRestricao();
			planoUfRestricao.setPlano(new Plano(to.getId().intValue()));
			planoUfRestricao.setUf(new Uf(ufTO.getIdUf().intValue()));
			planoUfRestricao.setDtCriacaoAlteracao(to.getDtCriacaoAlteracao());
			planoUfRestricao.setNmUsuarioCriacaoAlteracao(to.getNmUsuarioCriacaoAlteracao());
			
			planoUfRestricaoList.add(planoUfRestricao);
			
		}
		
		logger.debug("<< createPlanoUfRestricaoList()");
		
		return planoUfRestricaoList;
	}

}
