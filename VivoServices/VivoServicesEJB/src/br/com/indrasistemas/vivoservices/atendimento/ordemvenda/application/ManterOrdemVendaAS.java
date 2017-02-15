package br.com.indrasistemas.vivoservices.atendimento.ordemvenda.application;

import javax.ejb.SessionContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import br.com.indrasistemas.framework.foundation.xml.XMLParserException;
import br.com.indrasistemas.framework.service.BusinessException;
import br.com.indrasistemas.framework.service.application.ApplicationServiceException;
import br.com.indrasistemas.framework.service.application.BaseApplicationService;
import br.com.indrasistemas.framework.service.dao.exceptions.DAOException;
import br.com.indrasistemas.framework.service.servicegateway.ServiceGatewayException;
import br.com.indrasistemas.framework.service.servicegateway.tuxedo.exception.TuxedoBusinessException;
import br.com.indrasistemas.framework.service.servicegateway.tuxedo.exception.TuxedoException;
import br.com.indrasistemas.framework.service.to.RequestInfoTO;
import br.com.indrasistemas.vivoservices.atendimento.ordemvenda.dao.ManterOrdemVendaDAO;
import br.com.indrasistemas.vivoservices.atendimento.ordemvenda.to.PalitagemTO;
import br.com.indrasistemas.vivoservices.atendimento.ordemvenda.tuxgateway.ManterOrdemVendaGateway;

public class ManterOrdemVendaAS extends BaseApplicationService {

    private static final Log logger = LogFactory.getLog(ManterOrdemVendaAS.class);

    public ManterOrdemVendaAS(SessionContext sessionContext) {
        super(sessionContext);
    }

    public PalitagemTO registrarPalito(RequestInfoTO requestInfo, PalitagemTO filtro) throws ApplicationServiceException, BusinessException {
        PalitagemTO ret = new PalitagemTO();
        if (logger.isDebugEnabled()) {
            logger.debug("ManterOrdemVendaAS::registrarPalito - Iniciando AS");
        }
        if (filtro == null) {
            throw new ApplicationServiceException("Parametros Obrigatórios não informados.");
        }

        //int errorCode = Integer.parseInt(filtro.getErrorCode()); 
       
        //if ( errorCode != 1 && errorCode != 2 ) {
        //    filtro.setErrorCodeDAO("2");
        //    filtro.setErrorMessageDAO("Código do erro (cdStatusRejeicao) informado deve ser '1' ou '2'.");
        //}

        ManterOrdemVendaDAO manterOrdemVendaDAO = new ManterOrdemVendaDAO();

        try {
            
            //if ( filtro.getErrorCodeDAO()==null || !filtro.getErrorCodeDAO().equals(new String("")) ) {
            //if ( filtro.getErrorCodeDAO() == new String("") ) {
                manterOrdemVendaDAO.buscarParametros(filtro);
            //}
            
            ManterOrdemVendaGateway gateway = new ManterOrdemVendaGateway();
            ret = (PalitagemTO) gateway.call(requestInfo, filtro);

        } catch (DAOException e) {
            logger.error("ManterOrdemVendaAS::registrarPalito::DAOException", e);
            throw new BusinessException(e);
            
        } catch (TuxedoBusinessException e) {
            logger.error("ManterOrdemVendaAS::registrarPalito::TuxedoBusinessException", e);
            throw new BusinessException(e);

        } catch (ServiceGatewayException e) {
            logger.error("ManterOrdemVendaAS::registrarPalito::ServiceGatewayException", e);
            throw new ApplicationServiceException(e);

        } catch (TuxedoException e) {
            logger.error("ManterOrdemVendaAS::registrarPalito::TuxedoException", e);
            throw new BusinessException(e);

        } catch (XMLParserException e) {
            logger.error("ManterOrdemVendaAS::registrarPalito::XMLParserException", e);
            throw new ApplicationServiceException(e);
        }
        return ret;
    }
}
