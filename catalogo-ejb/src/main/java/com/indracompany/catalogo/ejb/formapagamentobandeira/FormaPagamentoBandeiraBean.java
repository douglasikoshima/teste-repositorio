package com.indracompany.catalogo.ejb.formapagamentobandeira;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import org.apache.log4j.Logger;

import weblogic.ejbgen.Session;
import weblogic.ejbgen.Constants.Bool;
import weblogic.ejbgen.Session.SessionType;
import br.com.indrasistemas.framework.service.BusinessException;

import com.indracompany.catalogo.dao.interfaces.FormaPagamentoBandeiraDAO;
import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.FormaPagamentoBandeiraTO;


/**
 * @author Luiz Pereira
 * 
 * EJB responsável em realizar as funções de Forma Pagamento Bandeira
 */
@Stateless(name = "FormaPagamentoBandeiraBean", mappedName = "FormaPagamentoBandeiraBean")
@Session(ejbName = "FormaPagamentoBandeiraBean", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.NOT_SUPPORTED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class FormaPagamentoBandeiraBean implements FormaPagamentoBandeiraBeanLocal {
	
	private static Logger logger = Logger.getLogger(FormaPagamentoBandeiraBean.class);
	
	@EJB
	private FormaPagamentoBandeiraDAO formaPagamentoBandeiraDAO;
	
	/* (non-Javadoc)
	 * @see com.indracompany.catalogo.ejb.servicointeratividade.interfaces.PlataformaBeanLocal#findAll()
	 */
	public void createUpdateFormaPagamentoBandeira(List<FormaPagamentoBandeiraTO> formaPagamentoBandeiraTOList) throws BusinessException {
		logger.debug("formaPagamentoBandeiraTOList: " + formaPagamentoBandeiraTOList);
		
		try {
			formaPagamentoBandeiraDAO.removeFormaPagamentoBandeira(formaPagamentoBandeiraTOList.get(0));
			
			for (FormaPagamentoBandeiraTO formaPagamentoBandeiraTO : formaPagamentoBandeiraTOList) {
				formaPagamentoBandeiraDAO.createUpdateFormaPagamentoBandeira(formaPagamentoBandeiraTO);
			}
		} catch (DAOException e) {
			throw new EJBException(e);
		}
	}
	
}
