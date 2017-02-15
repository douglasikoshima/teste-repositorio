package com.indracompany.catalogo.ejb.canalvendaagrupador;

import java.util.List;

import javax.ejb.Local;

import br.com.indrasistemas.framework.service.BusinessException;

import com.indracompany.catalogo.to.CanalVendaTO;
import com.indracompany.catalogo.to.EpsTO;

@Local
public interface CanalVendaAgrupadorBeanLocal {

	public static final String JNDI_NAME = "java:comp/env/CanalVendaAgrupadorBean";
	
	public List<EpsTO> getEpsList() throws BusinessException;
	
	public List<CanalVendaTO> pesquisar(CanalVendaTO canalVendaTO) throws BusinessException;
	
	public CanalVendaTO merge(CanalVendaTO canalVendaTO) throws BusinessException;

	public void remover(CanalVendaTO canalVendaTO) throws BusinessException;
	
	public void alternarInDisponivel(CanalVendaTO canalVendaTO) throws BusinessException;
	
	public CanalVendaTO pesquisarPorId(CanalVendaTO canalVendaTO) throws BusinessException;
	
	public void associarAgrupador(List<Long> canalVendaIdList, Integer idEps) throws BusinessException;
	
	public void desassociarAgrupador(List<Long> canalVendaIdList) throws BusinessException;

	public List<EpsTO> pesquisar(EpsTO epsTO) throws BusinessException;
	
	public void remover(EpsTO epsTO) throws BusinessException;
	
	public void merge(EpsTO to) throws BusinessException;
}
