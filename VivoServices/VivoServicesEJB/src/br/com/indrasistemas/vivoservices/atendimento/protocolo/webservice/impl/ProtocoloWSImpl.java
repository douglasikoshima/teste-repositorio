package br.com.indrasistemas.vivoservices.atendimento.protocolo.webservice.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import br.com.indrasistemas.framework.service.BusinessException;
import br.com.indrasistemas.framework.service.delegate.BusinessDelegateException;
import br.com.indrasistemas.framework.service.to.RequestInfoTO;
import br.com.indrasistemas.vivoservices.atendimento.protocolo.delegate.ProtocoloBD;
import br.com.indrasistemas.vivoservices.atendimento.protocolo.to.ProtocoloTO;
import br.com.indrasistemas.vivoservices.atendimento.protocolo.webservice.ProtocoloWS;
import br.com.indrasistemas.vivoservices.atendimento.protocolo.webservice.to.ProtocoloParamWSTO;
import br.com.indrasistemas.vivoservices.atendimento.protocolo.webservice.to.ProtocoloWSTO;
import br.com.indrasistemas.vivoservices.webservice.to.RespostaWSTO;

public class ProtocoloWSImpl implements ProtocoloWS {

    private static final Log logger = LogFactory.getLog(ProtocoloWSImpl.class);

    public ProtocoloWSImpl() {
    }

    public ProtocoloWSTO aberturaProtocolo(RequestInfoTO requestInfo, ProtocoloParamWSTO param) {

        ProtocoloTO toPesquisa = new ProtocoloTO();
        ProtocoloWSTO wsTO = new ProtocoloWSTO();

        try{
            if(param.getCdConta()!=null)    toPesquisa.setCdConta(param.getCdConta());
            if(param.getIdSistema()!=null)  toPesquisa.setIdSistema(param.getIdSistema());
            if(param.getNrLinha()!=null)    toPesquisa.setNrLinha(param.getNrLinha());
            if(param.getNrLinhaSMS()!=null) toPesquisa.setNrLinhaSMS(param.getNrLinhaSMS());
            if(param.getTpAbertura()!=null) toPesquisa.setTpAbertura(param.getTpAbertura());
            if(param.getIdPessoa()!=null)   toPesquisa.setIdPessoa(param.getIdPessoa());

            ProtocoloBD bd = new ProtocoloBD();
            if(logger.isDebugEnabled()){
                logger.debug("Iniciando a chamada para registrar o palito BD");
            }

            ProtocoloTO toRetorno = bd.aberturaProtocolo(requestInfo, toPesquisa);

            wsTO.setCodError(toRetorno.getErrorCode());
            wsTO.setMsgError(toRetorno.getErrorDescription());
            wsTO.setNrProtocolo(toRetorno.getNrProtocolo());
            wsTO.setStatus(RespostaWSTO.OK);

        }catch(BusinessException e){
            logger.error("ProtocoloWSImpl::aberturaProtocolo::BusinessException ", e);
            wsTO.setStatus(RespostaWSTO.NAO_OK);
            String msgRetorno = e.getMessage();
            if(e.getMessage().indexOf("|") > 0){
                wsTO.setCodError(msgRetorno.split("\\|")[0]);
                wsTO.setReason(msgRetorno.split("\\|")[1]);
            }else{
                wsTO.setCodError("");
                wsTO.setReason(msgRetorno);
            }

        }catch(BusinessDelegateException e){
            logger.error("ProtocoloWSImpl::aberturaProtocolo::BusinessDelegateException ", e);
            wsTO.setStatus(RespostaWSTO.NAO_OK);
            String msgRetorno = e.getMessage();
            if(e.getMessage().indexOf("|") > 0){
                wsTO.setCodError(msgRetorno.split("\\|")[0]);
                wsTO.setReason(msgRetorno.split("\\|")[1]);
            }else{
                wsTO.setCodError("");
                wsTO.setReason(msgRetorno);
            }
        }
        return wsTO;
    }

    public ProtocoloWSTO fechamentoProtocolo(RequestInfoTO requestInfo, Long nrProtocolo, Long idSistema) {
        ProtocoloWSTO wsTO = new ProtocoloWSTO();
        ProtocoloTO toPesquisa = new ProtocoloTO();
        try{
            toPesquisa.setNrProtocolo(nrProtocolo);
            toPesquisa.setIdSistema(idSistema);

            ProtocoloBD bd = new ProtocoloBD();
            if(logger.isDebugEnabled()){
                logger.debug("Iniciando a chamada para fechamento do Protocolo BD");
            }

            ProtocoloTO toRetorno = bd.fechamentoProtocolo(requestInfo, toPesquisa);

            wsTO.setCodError(toRetorno.getErrorCode());
            wsTO.setMsgError(toRetorno.getErrorDescription());
            wsTO.setStatus(RespostaWSTO.OK);
        
        }catch(BusinessException e){
            logger.error("ProtocoloWSImpl::fechamentoProtocolo::BusinessException ", e);
            wsTO.setStatus(RespostaWSTO.NAO_OK);
            String msgRetorno = e.getMessage();
            if(e.getMessage().indexOf("|") > 0){
                wsTO.setCodError(msgRetorno.split("\\|")[0]);
                wsTO.setReason(msgRetorno.split("\\|")[1]);
            }else{
                wsTO.setCodError("");
                wsTO.setReason(msgRetorno);
            }

        }catch(BusinessDelegateException e){
            logger.error("ProtocoloWSImpl::fechamentoProtocolo::BusinessDelegateException ", e);
            wsTO.setStatus(RespostaWSTO.NAO_OK);
            String msgRetorno = e.getMessage();
            if(e.getMessage().indexOf("|") > 0){
                wsTO.setCodError(msgRetorno.split("\\|")[0]);
                wsTO.setReason(msgRetorno.split("\\|")[1]);
            }else{
                wsTO.setCodError("");
                wsTO.setReason(msgRetorno);
            }
        }
        return wsTO;
    }
}