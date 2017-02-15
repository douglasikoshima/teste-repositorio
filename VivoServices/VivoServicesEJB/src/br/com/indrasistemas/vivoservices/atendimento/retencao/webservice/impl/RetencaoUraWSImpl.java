package br.com.indrasistemas.vivoservices.atendimento.retencao.webservice.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import br.com.indrasistemas.framework.service.BusinessException;
import br.com.indrasistemas.framework.service.delegate.BusinessDelegateException;
import br.com.indrasistemas.framework.service.to.RequestInfoTO;
import br.com.indrasistemas.vivoservices.atendimento.retencao.delegate.RetencaoUraBD;
import br.com.indrasistemas.vivoservices.atendimento.retencao.to.RetencaoTO;
import br.com.indrasistemas.vivoservices.atendimento.retencao.webservice.RetencaoUraWS;
import br.com.indrasistemas.vivoservices.atendimento.retencao.webservice.to.ParametrosWSTO;
import br.com.indrasistemas.vivoservices.atendimento.retencao.webservice.to.RetencaoParamWSTO;
import br.com.indrasistemas.vivoservices.atendimento.retencao.webservice.to.RetencaoWSTO;

public class RetencaoUraWSImpl implements RetencaoUraWS {

    private static final Log logger = LogFactory.getLog(RetencaoUraWSImpl.class);

    private String CONSULT_OF = "1"; //1 = Consulta de Ofertas
    private String ACEITAR_OF = "2"; //2 = Aceitar Ofertas
    private String RECUSAR_OF = "3"; //3 = Recusar Ofertas

    private static final String  OK = "OK";
    private static final String NOK = "NOK";
    
    public RetencaoUraWSImpl() {
        super();
    }

    public RetencaoWSTO retencaoURA(RequestInfoTO requestInfo, ParametrosWSTO parametros) {
        String nrOperacao = parametros.getNrOperacao();
        String nrTelefone = parametros.getNrTelefone();
        String nrTipo     = parametros.getNrTipo();
        String cdOferta   = parametros.getCdOferta();
        
        boolean         ldone = true;
        RetencaoWSTO       to = new RetencaoWSTO();
        RetencaoTO  toRetorno = new RetencaoTO();

        if(nrOperacao==null || "".equals(nrOperacao)){
            to.setCodiRet("01");
            to.setMsgError("campos obrigatórios não preenchidos");
            to.setStatus(NOK);
            to.setReason("");
            ldone = false;
        }else{
            if(nrTelefone==null || "".equals(nrTelefone) || nrTipo==null || "".equals(nrTipo)){
                to.setCodiRet("01");
                to.setMsgError("campos obrigatórios não preenchidos");
                to.setStatus(NOK);
                to.setReason("");
                ldone = false;
            }
            if( ACEITAR_OF.equals(nrOperacao) || RECUSAR_OF.equals(nrOperacao) ){
                if( cdOferta==null || "".equals(cdOferta) ){
                    to.setCodiRet("01");
                    to.setMsgError("campos obrigatórios não preenchidos");
                    to.setStatus(NOK);
                    to.setReason("");
                    ldone = false;
                }
            }
        }
        
        if(ldone){
            RetencaoTO toPesquisa = new RetencaoTO();
            toPesquisa.setNrOperacao(nrOperacao);
            toPesquisa.setNrTelefone(nrTelefone);
            toPesquisa.setNrTipo(nrTipo);
            toPesquisa.setCdOferta(cdOferta);
            
            if(CONSULT_OF.equals(nrOperacao)){
                RetencaoUraBD bd = new RetencaoUraBD();
                if(logger.isDebugEnabled()){
                    logger.debug("Iniciando a chamada para consultar Ofertas");
                }
                try{
                    toRetorno = bd.consultarOfertas(requestInfo, toPesquisa);
                    to.setStatus(OK);
                    to.setReason("");
                    if(toRetorno!=null){
                        to.setCodiRet(toRetorno.getCdRetorno());
                        to.setMsgError(toRetorno.getMsgRetorno());
                    }else{
                        to.setCodiRet("99");
                        to.setMsgError("Erro ao retornar os dados de ofertas");
                    }
                }catch(BusinessException e){
                    to.setStatus(NOK);
                    to.setReason("BusinessException::"+e.getMessage());
                    to.setCodiRet("99");
                    to.setMsgError("Ocorreu um erro na execução");
                    if(logger.isDebugEnabled()){
                        logger.debug("Problema ao consultar Ofertas", e);
                    }
                }catch(BusinessDelegateException e){
                    to.setStatus(NOK);
                    to.setReason("BusinessDelegateException::"+e.getMessage());
                    to.setCodiRet("99");
                    to.setMsgError("Ocorreu um erro na execução");
                    if(logger.isDebugEnabled()){
                        logger.debug("Problema ao consultar Ofertas", e);
                    }
                }
                
            }else if(ACEITAR_OF.equals(nrOperacao)){
                RetencaoUraBD bd = new RetencaoUraBD();
                if(logger.isDebugEnabled()){
                    logger.debug("Iniciando a chamada para consultar Ofertas");
                }
                try{
                    toRetorno = bd.aceitarOfertas(requestInfo, toPesquisa);
                    to.setStatus(OK);
                    to.setReason("");
                    if(toRetorno!=null){
                        to.setCodiRet(toRetorno.getCdRetorno());
                        to.setMsgError(toRetorno.getMsgRetorno());
                    }else{
                        to.setCodiRet("99");
                        to.setMsgError("Erro ao retornar os dados ao aceitar oferta");
                    }
                }catch(BusinessException e){
                    to.setStatus(NOK);
                    to.setReason("BusinessException::"+e.getMessage());
                    to.setCodiRet("99");
                    to.setMsgError("Ocorreu um erro na execução");
                    if(logger.isDebugEnabled()){
                        logger.debug("Problema ao consultar Ofertas", e);
                    }
                }catch(BusinessDelegateException e){
                    to.setStatus(NOK);
                    to.setReason("BusinessDelegateException::"+e.getMessage());
                    to.setCodiRet("99");
                    to.setMsgError("Ocorreu um erro na execução");
                    if(logger.isDebugEnabled()){
                        logger.debug("Problema ao consultar Ofertas", e);
                    }
                }
                
            }else if(RECUSAR_OF.equals(nrOperacao)){
                RetencaoUraBD bd = new RetencaoUraBD();
                if(logger.isDebugEnabled()){
                    logger.debug("Iniciando a chamada para consultar Ofertas");
                }
                try{
                    toRetorno = bd.recusarOfertas(requestInfo, toPesquisa);
                    to.setStatus(OK);
                    to.setReason("");
                    if(toRetorno!=null){
                        to.setCodiRet(toRetorno.getCdRetorno());
                        to.setMsgError(toRetorno.getMsgRetorno());
                    }else{
                        to.setCodiRet("99");
                        to.setMsgError("Erro ao retornar os dados da recusa de oferta");
                    }
                }catch(BusinessException e){
                    to.setStatus(NOK);
                    to.setCodiRet("1");
                    to.setMsgError(e.getMessage());
                    if(logger.isDebugEnabled()){
                        logger.debug("Problema ao consultar Ofertas", e);
                    }
                }catch(BusinessDelegateException e){
                    to.setStatus(NOK);
                    to.setReason("BusinessDelegateException::"+e.getMessage());
                    to.setCodiRet("99");
                    to.setMsgError("Ocorreu um erro na execução");
                    if(logger.isDebugEnabled()){
                        logger.debug("Problema ao consultar Ofertas", e);
                    }
                }
                
            }
            
            to.setNrRetencao(toRetorno.getNrRetencao());
    
            RetencaoParamWSTO[] out = null;
            if(toRetorno.getOut()!=null && toRetorno.getOut().length>0){
                out = new RetencaoParamWSTO[toRetorno.getOut().length];
                for(int i=0;i<toRetorno.getOut().length;i++){
                    out[i] = new RetencaoParamWSTO();
                    out[i].setCdBonus(toRetorno.getOut()[i].getCdBonus());
                    out[i].setDtValidade(toRetorno.getOut()[i].getDtValidade());
                    out[i].setNmGrupo(toRetorno.getOut()[i].getNmGrupo());
                    out[i].setVlTarifa(toRetorno.getOut()[i].getVlTarifa());
                }
            }
            
            to.setOut(out);
        }
        return to;
    }
}