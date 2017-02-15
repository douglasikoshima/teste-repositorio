package com.indracompany.catalogo.ejb.grupocaracteristica;

import java.util.List;

import javax.ejb.Local;

import br.com.indrasistemas.framework.service.BusinessException;

import com.indracompany.catalogo.to.GrupoCaracteristicaTO;

@Local
public interface GrupoCaracteristicaBeanLocal {
	public static final String JNDI_NAME = "java:comp/env/GrupoCaracteristicaBean";
	
	public List<GrupoCaracteristicaTO> listarGrupoCaracteristica() throws BusinessException;
	public void createUpdateGrupoCaracteristica(GrupoCaracteristicaTO grupoCaracteristicaTO) throws BusinessException;
	public void deleteGrupoCaracteristica(GrupoCaracteristicaTO grupoCaracteristicaTO) throws BusinessException;
	public GrupoCaracteristicaTO getGrupoCaracteristica(GrupoCaracteristicaTO grupoCaracteristicaTO) throws BusinessException;
}
