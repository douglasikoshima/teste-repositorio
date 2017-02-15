package com.indracompany.catalogo.ejb.tipoencargo;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import weblogic.ejbgen.Session;
import weblogic.ejbgen.Constants.Bool;
import weblogic.ejbgen.Session.SessionType;

import br.com.indrasistemas.framework.service.BusinessException;

import com.indracompany.catalogo.dao.interfaces.TipoEncargoDAO;
import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.TipoEncargoTO;

@Stateless(name="TipoEncargoBean", mappedName="TipoEncargoBean")
@Session(ejbName = "TipoEncargoBean", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.REQUIRED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class TipoEncargoBean implements TipoEncargoBeanLocal{

	@EJB
	private TipoEncargoDAO tipoEncargoDAO;
	
	public List<TipoEncargoTO> findAll() throws BusinessException {
		try {
			return tipoEncargoDAO.findAll();
		} catch (DAOException e) {
			throw new BusinessException(e);
		}
	}
}
