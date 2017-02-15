package com.indracompany.catalogo.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.indracompany.catalogo.datalayer.Servico;
import com.indracompany.catalogo.datalayer.ServicoUfRestricao;
import com.indracompany.catalogo.datalayer.Uf;
import com.indracompany.catalogo.to.PlanoServicoUfRestricaoTO;
import com.indracompany.catalogo.to.UfTO;

public class ServicoUfRestricaoTOBuilder {
	
	private static Logger logger = Logger.getLogger(ServicoUfRestricaoTOBuilder.class);
	
	public List<ServicoUfRestricao> createServicoUfRestricaoList(PlanoServicoUfRestricaoTO to) {
		
		logger.debug(">> createServicoUfRestricaoList()");
		
		List<ServicoUfRestricao> servicoUfRestricaoList = new ArrayList<ServicoUfRestricao>();
		ServicoUfRestricao servicoUfRestricao;
		
		for ( UfTO ufTO : to.getUfTOList()) {
			servicoUfRestricao = new ServicoUfRestricao();
			servicoUfRestricao.setServico(new Servico(to.getId().intValue()));
			servicoUfRestricao.setUf(new Uf(ufTO.getIdUf().intValue()));
			servicoUfRestricao.setDtCriacaoAlteracao(to.getDtCriacaoAlteracao());
			servicoUfRestricao.setNmUsuarioCriacaoAlteracao(to.getNmUsuarioCriacaoAlteracao());
			
			servicoUfRestricaoList.add(servicoUfRestricao);
			
		}
		
		logger.debug("<< createServicoUfRestricaoList()");
		
		return servicoUfRestricaoList;
	}

}
