package com.indracompany.catalogo.ejb.detalheservicofixa;

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

import com.indracompany.catalogo.dao.interfaces.ServicoFixaDAO;
import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.DetalheServicoFixaTO;
import com.indracompany.catalogo.to.ServicoFixaTO;


@Stateless(name = "DetalheServicoFixaBean", mappedName = "DetalheServicoFixaBean")
@Session(ejbName = "DetalheServicoFixaBean", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.REQUIRED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class DetalheServicoFixaBean implements DetalheServicoFixaBeanLocal{

	private static Logger logger = Logger.getLogger(DetalheServicoFixaBean.class);
	
	@EJB
	ServicoFixaDAO servicoFixaDAO;
	
	public DetalheServicoFixaTO findDetalheServicoFixaById(DetalheServicoFixaTO detalheServicoFixaTO) throws BusinessException {
		logger.debug("detalheServicoFixaTO: " + detalheServicoFixaTO);
		
		DetalheServicoFixaTO result = null;
		
		try {
			result = servicoFixaDAO.findDetalheServicoFixaById(detalheServicoFixaTO);
		} catch (DAOException e) {
			throw new EJBException(e);
		}

		return result;
	}
	
	public void updateDetalheServicoFixaTO(DetalheServicoFixaTO detalheServicoFixaTO) throws BusinessException {
		logger.debug("detalheServicoFixaTO: " + detalheServicoFixaTO);
		
		ServicoFixaTO servicoFixaTO = new ServicoFixaTO();
		servicoFixaTO.setNmComercial(detalheServicoFixaTO.getNmComercialCatalogo());
		
		try {
			
			for(ServicoFixaTO to : servicoFixaDAO.search(servicoFixaTO)){
				if(to.getNmComercial().equalsIgnoreCase(detalheServicoFixaTO.getNmComercialCatalogo())
						&& to.getIdServico().intValue() != detalheServicoFixaTO.getIdServico().intValue()){
					throw new BusinessException(String.format("Nome do Servi&ccedil;o Catalogo j&aacute; existe", ""));
				}
			}
			servicoFixaDAO.updateDetalheServicoFixa(detalheServicoFixaTO);
		} catch(DAOException e){
			throw new EJBException(e);
		} 
	}
}
