package br.com.indrasistemas.vivoservices.listapup.webservice.impl;

import java.util.List;
import br.com.indrasistemas.framework.service.BusinessException;
import br.com.indrasistemas.framework.service.delegate.BusinessDelegateException;
import br.com.indrasistemas.framework.service.to.RequestInfoTO;
import br.com.indrasistemas.vivoservices.listapup.delegate.ListaPUPBD;
import br.com.indrasistemas.vivoservices.listapup.to.LinhaPUPWSTO;
import br.com.indrasistemas.vivoservices.listapup.webservice.GravarLinhaPUPWS;
import br.com.indrasistemas.vivoservices.listapup.webservice.to.ResultadoLinhaPUPTO;
import br.com.indrasistemas.vivoservices.webservice.to.RespostaWSTO;

public class GravarLinhaPUPWSImpl implements GravarLinhaPUPWS {

    public GravarLinhaPUPWSImpl() {
        // Constructor
    }

    public ResultadoLinhaPUPTO gravarLinhaPUP(RequestInfoTO requestInfo, Integer cdCanal, Integer cdProcedencia, String sgCanal, String sgProcedencia, List nrMIN, Integer cdOperacao,
            Integer inSMSProtocolos, Integer inSMSPromocoes, Integer inSMSProdutos, Integer inSMSParceiros, Integer inIVRPromocoes, Integer inIVRProdutos, Integer inIVRParceiros,
            Integer inTelmktMsgVoz) {

        ResultadoLinhaPUPTO to = new ResultadoLinhaPUPTO();

        try {
            LinhaPUPWSTO toPesquisa = new LinhaPUPWSTO();
            toPesquisa.setCdCanal(cdCanal);
            toPesquisa.setCdProcedencia(cdProcedencia);
            toPesquisa.setSgCanal(sgCanal);
            toPesquisa.setSgProcedencia(sgProcedencia);
            toPesquisa.setNrMIN(nrMIN);
            toPesquisa.setCdOperacao(cdOperacao);
            toPesquisa.setInSMSProtocolos(inSMSProtocolos);
            toPesquisa.setInSMSPromocoes(inSMSPromocoes);
            toPesquisa.setInSMSProdutos(inSMSProdutos);
            toPesquisa.setInSMSParceiros(inSMSParceiros);
            toPesquisa.setInIVRPromocoes(inIVRPromocoes);
            toPesquisa.setInIVRProdutos(inIVRProdutos);
            toPesquisa.setInIVRParceiros(inIVRParceiros);
            toPesquisa.setInTelmktMsgVoz(inTelmktMsgVoz);

            ListaPUPBD bd = new ListaPUPBD();
            to = bd.gravarLinhaPUP(requestInfo, toPesquisa);

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