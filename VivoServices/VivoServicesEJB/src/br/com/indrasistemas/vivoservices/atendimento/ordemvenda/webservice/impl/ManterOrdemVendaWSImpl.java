package br.com.indrasistemas.vivoservices.atendimento.ordemvenda.webservice.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import br.com.indrasistemas.framework.service.BusinessException;
import br.com.indrasistemas.framework.service.delegate.BusinessDelegateException;
import br.com.indrasistemas.framework.service.to.RequestInfoTO;
import br.com.indrasistemas.vivoservices.atendimento.ordemvenda.delegate.ManterOrdemVendaBD;
import br.com.indrasistemas.vivoservices.atendimento.ordemvenda.to.PalitagemTO;
import br.com.indrasistemas.vivoservices.atendimento.ordemvenda.webservice.ManterOrdemVendaWS;
import br.com.indrasistemas.vivoservices.atendimento.ordemvenda.webservice.to.manterOrdemVenda;
import br.com.indrasistemas.vivoservices.atendimento.ordemvenda.webservice.to.manterOrdemVendaResponse;
import br.com.indrasistemas.vivoservices.webservice.to.RespostaWSTO;

public class ManterOrdemVendaWSImpl implements ManterOrdemVendaWS {

    private static final Log logger = LogFactory.getLog(ManterOrdemVendaWSImpl.class);

    public ManterOrdemVendaWSImpl() {
        super();
    }

    public manterOrdemVendaResponse registrarPalito(RequestInfoTO requestInfo, manterOrdemVenda manterOrdemVendaInParam) {

        manterOrdemVendaResponse to = new manterOrdemVendaResponse();

        try {

            PalitagemTO toPesquisa = new PalitagemTO();

            toPesquisa.setErrorCode(manterOrdemVendaInParam.getCdStatusRejeicao());
            toPesquisa.setErrorDescription(manterOrdemVendaInParam.getDsMotivoRejeicao());
            toPesquisa.setDsComentario(manterOrdemVendaInParam.getDsMotivoRejeicao());
            toPesquisa.setNrOrdemVenda(manterOrdemVendaInParam.getCdOrdemVenda());
            toPesquisa.setCdStatusRejeicao(manterOrdemVendaInParam.getCdStatusRejeicao());
            
            toPesquisa.setIdUsuario(new String("1"));
            toPesquisa.setTpOperacao(new Integer(2));

            ManterOrdemVendaBD bd = new ManterOrdemVendaBD();
            if (logger.isDebugEnabled()) {
                logger.debug("Iniciando a chamada para registrar o palito BD");
            }
            
            PalitagemTO toRetorno = new PalitagemTO();
            
            if ( toPesquisa.getErrorCodeDAO().equals("") ) {                
                toRetorno = bd.registrarPalito(requestInfo, toPesquisa);
                //Long nrProtocolo = new Long(0);
                if (toRetorno != null ) {
                    to.getOutManterOrdemVenda().setCdRetorno(RespostaWSTO.OK); //setStatus(RespostaWSTO.OK);
                } else {
                    to.getOutManterOrdemVenda().setCdRetorno(RespostaWSTO.NAO_OK);  //to.setStatus(RespostaWSTO.NAO_OK);
                    to.getOutManterOrdemVenda().setDsRetorno(" JE9999"); //to.setCodError(" JE9999");
                    to.setReason("retorno invalido");
                }
            }
            else {
                to.getOutManterOrdemVenda().setCdRetorno(RespostaWSTO.NAO_OK);
                to.getOutManterOrdemVenda().setDsRetorno(toPesquisa.getErrorMessageDAO());
                to.setReason(toPesquisa.getErrorMessageDAO());
            }

        } catch (BusinessDelegateException e) {
            if (logger.isDebugEnabled()) {
                logger.debug("Exception::BusinessDelegateException - (" + e.getMessage() + ")");
            }
            to.setStatus(RespostaWSTO.NAO_OK);
            
            String msgRetorno = e.getMessage();
            
            int pos;
            int posAnt=-1;
            
            do {
                pos = msgRetorno.indexOf("Exception: ", posAnt+1);
                if ( pos>=0 ) { posAnt = pos; }
            } while ( pos >= 0 );
            
            int it = new String("Exception: ").length()+posAnt;

            if  ( posAnt >= 0 ) {
                if (logger.isDebugEnabled()) {
                    logger.debug("ERRO:(" + msgRetorno.substring(it) + ")");
                }
                
                to.getOutManterOrdemVenda().setCdRetorno("09E0000");
                to.getOutManterOrdemVenda().setDsRetorno(msgRetorno.substring(it));
            }
            else {
                to.getOutManterOrdemVenda().setCdRetorno("09E0001");
                to.getOutManterOrdemVenda().setDsRetorno(msgRetorno);
            }
            
            //if (e.getMessage().indexOf("|") > 0) {
            //    to.getOutManterOrdemVenda().setCdRetorno(msgRetorno.split("\\|")[0]);
            //    to.setReason(msgRetorno.split("\\|")[1]);
            //} else {
            //    to.getOutManterOrdemVenda().setCdRetorno("");
            //    to.setReason(msgRetorno);
            //}

        } catch (BusinessException e) {
            if (logger.isDebugEnabled()) {
                logger.debug("Exception::BusinessException - (" + e.getMessage() + ")");
            }
            to.setStatus(RespostaWSTO.NAO_OK);
            String msgRetorno = e.getMessage();
            if (e.getMessage().indexOf("|") > 0) {
                to.getOutManterOrdemVenda().setCdRetorno(msgRetorno.split("\\|")[0]);
                to.setReason(msgRetorno.split("\\|")[1]);
            } else {
                to.getOutManterOrdemVenda().setCdRetorno("");
                to.setReason(msgRetorno);
            }
        }
        return to;
    }
}