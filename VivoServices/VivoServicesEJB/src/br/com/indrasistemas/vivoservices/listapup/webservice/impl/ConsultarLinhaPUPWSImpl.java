package br.com.indrasistemas.vivoservices.listapup.webservice.impl;

import br.com.indrasistemas.framework.service.BusinessException;
import br.com.indrasistemas.framework.service.delegate.BusinessDelegateException;
import br.com.indrasistemas.framework.service.to.RequestInfoTO;
import br.com.indrasistemas.vivoservices.listapup.delegate.ListaPUPBD;
import br.com.indrasistemas.vivoservices.listapup.to.LinhaPUPWSTO;
import br.com.indrasistemas.vivoservices.listapup.webservice.ConsultarLinhaPUPWS;
import br.com.indrasistemas.vivoservices.listapup.webservice.to.ResultadoLinhaPUPTO;
import br.com.indrasistemas.vivoservices.webservice.to.RespostaWSTO;

public class ConsultarLinhaPUPWSImpl implements ConsultarLinhaPUPWS {

    public ConsultarLinhaPUPWSImpl() {
        //Constructor
    }

    public ResultadoLinhaPUPTO consultarLinhaPUP(RequestInfoTO requestInfo, Integer cdCanal, Integer cdProcedencia, String sgCanal, String sgProcedencia, Integer cdDDD, Integer nrTelefone) {

        ResultadoLinhaPUPTO to = new ResultadoLinhaPUPTO();

        try {
            LinhaPUPWSTO toPesquisa = new LinhaPUPWSTO();

            toPesquisa.setCdCanal(cdCanal);
            toPesquisa.setCdProcedencia(cdProcedencia);
            toPesquisa.setSgCanal(sgCanal);
            toPesquisa.setSgProcedencia(sgProcedencia);
            toPesquisa.setCdDDD(cdDDD);
            toPesquisa.setNrTelefone(nrTelefone);

            ListaPUPBD bd = new ListaPUPBD();
            to = bd.consultarLinhaPUP(requestInfo, toPesquisa);

            to.setStatus(RespostaWSTO.OK);
            to.setCdRetorno(LinhaPUPWSTO.CD_PROSSEGUE_ATENDIMENTO);

        } catch (BusinessDelegateException e) {
            to.setStatus(RespostaWSTO.NAO_OK);
            String msgRetorno = e.getMessage();
            if (e.getMessage().indexOf("|") > 0) {
                to.setCdRetorno(msgRetorno.split("\\|")[0]);
                to.setReason(msgRetorno.split("\\|")[1]);
            } else {
                to.setCdRetorno(LinhaPUPWSTO.CD_ERRO_GENERICO);
                to.setReason(msgRetorno);
            }

        } catch (BusinessException e) {
            to.setStatus(RespostaWSTO.NAO_OK);
            String msgRetorno = e.getMessage();
            
            if (e.getMessage().indexOf("|") > 0) {
                to.setCdRetorno(msgRetorno.split("\\|")[0]);
                to.setReason(msgRetorno.split("\\|")[1]);
            
            } else {
                to.setCdRetorno(LinhaPUPWSTO.CD_ERRO_GENERICO);
                to.setReason(msgRetorno);
            }

        }

        return to;
    }

}