package com.indracompany.catalogo.ejb.disponibilidade;

import java.util.List;

import javax.ejb.Local;

import br.com.indrasistemas.framework.service.BusinessException;

import com.indracompany.catalogo.to.AreaConcorrenciaTO;
import com.indracompany.catalogo.to.AreaRegistroTO;
import com.indracompany.catalogo.to.CanalVendaTO;
import com.indracompany.catalogo.to.CidadeTO;
import com.indracompany.catalogo.to.EpsTO;
import com.indracompany.catalogo.to.LocalidadeTO;
import com.indracompany.catalogo.to.UfTO;

@Local
public interface DisponibilidadeBeanLocal {

	public static final String JNDI_NAME = "java:comp/env/DisponibilidadeBean";

	public List<EpsTO> listEpsTO() throws BusinessException;
	
	public List<UfTO> obterUfTOList() throws BusinessException;
	
	public List<CanalVendaTO> searchCanalVendaTO(CanalVendaTO canalVendaTO) throws BusinessException;
	
	public List<AreaRegistroTO> searchAreaRegistroTO(AreaRegistroTO areaRegistroTO) throws BusinessException;
	
	public List<AreaRegistroTO> findAreaRegistroByUf(Integer idUf) throws BusinessException;
	
	public List<CidadeTO> findCidadeByAreaRegistro(Integer idAreaRegistro) throws BusinessException;
	
	public List<LocalidadeTO> findLocalidadeByIdCidade(Integer idCidade) throws BusinessException;
	
	public List<AreaConcorrenciaTO> obterAreaConcorrenciaTOList() throws BusinessException;

}