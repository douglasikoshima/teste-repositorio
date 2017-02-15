package br.com.indrasistemas.vivoservices.atendimento.gestor.webservice.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import br.com.indrasistemas.framework.service.BusinessException;
import br.com.indrasistemas.framework.service.delegate.BusinessDelegateException;
import br.com.indrasistemas.framework.service.to.RequestInfoTO;
import br.com.indrasistemas.vivoservices.atendimento.gestor.delegate.AlertaSaidaBD;
import br.com.indrasistemas.vivoservices.atendimento.gestor.to.AlertaSaidaTO;
import br.com.indrasistemas.vivoservices.atendimento.gestor.webservice.AlertaSaidaWS;
import br.com.indrasistemas.vivoservices.atendimento.gestor.webservice.to.AlertaSaidaWSTO;

public class AlertaSaidaWSImpl implements AlertaSaidaWS {

    private static final Log logger = LogFactory.getLog(AlertaSaidaWSImpl.class);
    private static final String  OK = "OK";
    private static final String NOK = "NOK";

    public AlertaSaidaWSImpl() {
        super();
    }

    public AlertaSaidaWSTO consultarLinha(RequestInfoTO requestInfo, Long cdAreaRegistro, Long nrLinha) {

        AlertaSaidaWSTO to = new AlertaSaidaWSTO();

        try{
            AlertaSaidaBD bd = new AlertaSaidaBD();
            AlertaSaidaTO toRetorno = bd.consultar(requestInfo, cdAreaRegistro, nrLinha);
            to.setSgSegmentacao(toRetorno.getSgSegmentacao());
            to.setSgTipoCarteira(toRetorno.getSgTipoCarteira());

            to.setNomeGestor(toRetorno.getNomeGestor());
            to.setNrLinha(toRetorno.getNrLinha());
            to.setEmailGestor(toRetorno.getEmailGestor());
            
            to.setNomeGerente(toRetorno.getNomeGerente());
            to.setNrLinhaGr(toRetorno.getNrLinhaGr());
            to.setEmailGerente(toRetorno.getEmailGerente());
            
            to.setNmFantasia(toRetorno.getNmFantasia());
            to.setRazaoSocial(toRetorno.getRazaoSocial());
            to.setOperadora(toRetorno.getOperadora());
            
            to.setStatus(OK);
            to.setReason("");
            to.setCodError(toRetorno.getCodError());
            to.setMsgError(toRetorno.getMsgError());
        }catch(BusinessException e){
            to.setStatus(NOK);
            to.setReason("BusinessException::"+e.getMessage());
            to.setCodError("99");
            to.setMsgError("Ocorreu um erro na execução");
            if(logger.isDebugEnabled()){
                logger.debug("Problema ao consultar linha", e);
            }
        }catch(BusinessDelegateException e){
            to.setStatus(NOK);
            to.setReason("BusinessDelegateException::"+e.getMessage());
            to.setCodError("99");
            to.setMsgError("Ocorreu um erro na execução");
            if(logger.isDebugEnabled()){
                logger.debug("Problema ao consultar linha", e);
            }
        }        
        
        return to;
    }
}