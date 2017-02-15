package com.indracompany.catalogo.ejb.segmentoplanoservico;

import java.util.List;
import java.util.Map;

import br.com.indrasistemas.framework.service.BusinessException;

import com.indracompany.catalogo.to.PlanoSegmentoTO;
import com.indracompany.catalogo.to.PlanoTO;
import com.indracompany.catalogo.to.ServicoFixaTO;
import com.indracompany.catalogo.to.ServicoSegmentoTO;

public interface SegmentoPlanoServicoBeanLocal {
	
	public static final String JNDI_NAME = "java:comp/env/SegmentoPlanoServicoBean";
	
	public List<PlanoTO> searchPlano(PlanoTO planoTO) throws BusinessException;
	
	@SuppressWarnings("rawtypes")
	public Map<String, List> loadCombos() throws BusinessException;
	
	public void savePlanoSegmento(PlanoSegmentoTO planoSegmentoTO) throws BusinessException;
	
	public void disassociatePlanoSegmento(PlanoSegmentoTO planoSegmentoTO) throws BusinessException;
	
	public List<ServicoFixaTO> searchServicoFixa(ServicoFixaTO servicoFixaTO) throws BusinessException;
	
	public void saveServicoSegmento(ServicoSegmentoTO servicoSegmentoTO) throws BusinessException;
	
	public void disassociateServicoSegmento(ServicoSegmentoTO servicoSegmentoTO) throws BusinessException;
}