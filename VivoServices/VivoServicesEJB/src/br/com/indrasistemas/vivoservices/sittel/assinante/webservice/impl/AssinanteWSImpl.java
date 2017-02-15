package br.com.indrasistemas.vivoservices.sittel.assinante.webservice.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import br.com.indrasistemas.framework.service.BusinessException;
import br.com.indrasistemas.framework.service.delegate.BusinessDelegateException;
import br.com.indrasistemas.vivoservices.sittel.assinante.delegate.AssinanteBD;
import br.com.indrasistemas.vivoservices.sittel.assinante.to.AssinanteTO;
import br.com.indrasistemas.vivoservices.sittel.assinante.to.InvestigadoTO;
import br.com.indrasistemas.vivoservices.sittel.assinante.to.ResultadoConsultaTO;
import br.com.indrasistemas.vivoservices.sittel.assinante.webservice.AssinanteWS;
import br.com.indrasistemas.vivoservices.sittel.assinante.webservice.to.Assinante;
import br.com.indrasistemas.vivoservices.sittel.assinante.webservice.to.Investigado;
import br.com.indrasistemas.vivoservices.sittel.assinante.webservice.to.ResultadoConsulta;
import br.com.indrasistemas.vivoservices.webservice.to.RespostaWSTO;

@SuppressWarnings({ "rawtypes", "unused", "unchecked" })
public class AssinanteWSImpl implements AssinanteWS {

    private static final Log logger = LogFactory.getLog("AssinanteWSImpl");

    private AssinanteTO      to     = new AssinanteTO();

    @Override
    public ResultadoConsulta consultaAssinante(Assinante consultaAssinante) {

        if (logger.isInfoEnabled()) {
            logger.info("consultaAssinante - Método iniciado.");
            logger.info("consultaAssinante::investigados = "+ consultaAssinante.getInvestigados()!=null?consultaAssinante.getInvestigados().size():0);
        }

        ResultadoConsulta resultadoAssinante = new ResultadoConsulta();
        AssinanteTO to = getAssinanteTO(consultaAssinante);

        try {
            AssinanteBD bd = new AssinanteBD();
            ResultadoConsultaTO resultadoAssinanteTO = bd.gravarRequisicaoProcessum(to, 1);

            resultadoAssinante.setCodigoErro(resultadoAssinanteTO.getCodigoErro());
            resultadoAssinante.setDescricaoErro(resultadoAssinanteTO.getDescricaoErro());
            resultadoAssinante.setStatus(resultadoAssinanteTO.getStatus());

        } catch (BusinessDelegateException e) {
            logger.error("consultaAssinante::", e);
            resultadoAssinante.setStatus(RespostaWSTO.NAO_OK);
            resultadoAssinante.setCodigoErro(99L);
            resultadoAssinante.setDescricaoErro(e.getMessage());

        } catch (BusinessException e) {
            logger.error("consultaAssinante::", e);
            resultadoAssinante.setStatus(RespostaWSTO.NAO_OK);
            resultadoAssinante.setCodigoErro(99L);
            resultadoAssinante.setDescricaoErro(e.getMessage());
        }

        if (logger.isInfoEnabled()) {
            logger.info("consultaAssinante - Método finalizado.");
        }
        return resultadoAssinante;
    }

    @Override
    public ResultadoConsulta consultaAssinanteTerminal(Assinante consultaAssinanteTerminal) {

        if (logger.isInfoEnabled()) {
            logger.info("consultaAssinanteTerminal - Método iniciado.");
            logger.info("consultaAssinante::investigados = "+ consultaAssinanteTerminal.getInvestigados()!=null?consultaAssinanteTerminal.getInvestigados().size():0);
        }

        ResultadoConsulta resultadoAssinante = new ResultadoConsulta();
        AssinanteTO to = getAssinanteTO(consultaAssinanteTerminal);

        try {
            AssinanteBD bd = new AssinanteBD();
            ResultadoConsultaTO resultadoAssinanteTO = bd.gravarRequisicaoProcessum(to, 2);

            resultadoAssinante.setCodigoErro(resultadoAssinanteTO.getCodigoErro());
            resultadoAssinante.setDescricaoErro(resultadoAssinanteTO.getDescricaoErro());
            resultadoAssinante.setStatus(resultadoAssinanteTO.getStatus());

        } catch (BusinessDelegateException e) {
            logger.error("consultaAssinanteTerminal::", e);
            resultadoAssinante.setStatus(RespostaWSTO.NAO_OK);
            resultadoAssinante.setCodigoErro(99L);
            resultadoAssinante.setDescricaoErro(e.getMessage());

        } catch (BusinessException e) {
            logger.error("consultaAssinanteTerminal::", e);
            resultadoAssinante.setStatus(RespostaWSTO.NAO_OK);
            resultadoAssinante.setCodigoErro(99L);
            resultadoAssinante.setDescricaoErro(e.getMessage());
        }

        if (logger.isInfoEnabled()) {
            logger.info("consultaAssinanteTerminal - Método finalizado.");
        }

        return resultadoAssinante;
    }

    @Override
    public ResultadoConsulta consultaInstalacao(Assinante consultaInstalacao) {

        if (logger.isInfoEnabled()) {
            logger.info("consultaInstalacao - Método iniciado.");
            logger.info("consultaInstalacao::investigados = "+ consultaInstalacao.getInvestigados()!=null?consultaInstalacao.getInvestigados().size():0);
        }

        ResultadoConsulta resultadoAssinante = new ResultadoConsulta();
        AssinanteTO to = getAssinanteTO(consultaInstalacao);

        try {
            AssinanteBD bd = new AssinanteBD();
            ResultadoConsultaTO resultadoAssinanteTO = bd.gravarRequisicaoProcessum(to, 3);

            resultadoAssinante.setCodigoErro(resultadoAssinanteTO.getCodigoErro());
            resultadoAssinante.setDescricaoErro(resultadoAssinanteTO.getDescricaoErro());
            resultadoAssinante.setStatus(resultadoAssinanteTO.getStatus());

        } catch (BusinessDelegateException e) {
            logger.error("consultaInstalacao::", e);
            resultadoAssinante.setStatus(RespostaWSTO.NAO_OK);
            resultadoAssinante.setCodigoErro(99L);
            resultadoAssinante.setDescricaoErro(e.getMessage());

        } catch (BusinessException e) {
            logger.error("consultaInstalacao::", e);
            resultadoAssinante.setStatus(RespostaWSTO.NAO_OK);
            resultadoAssinante.setCodigoErro(99L);
            resultadoAssinante.setDescricaoErro(e.getMessage());
        }

        if (logger.isInfoEnabled()) {
            logger.info("consultaInstalacao - Método finalizado.");
        }

        return resultadoAssinante;
    }

    private AssinanteTO getAssinanteTO(Assinante assinante) {
        AssinanteTO assinanteTO = new AssinanteTO();
        try {
            assinanteTO.setCodigoOrdem(assinante.getCodigoOrdem());
            logger.info("getCodigoOrdem = "+assinanteTO.getCodigoOrdem());
            assinanteTO.setCodigoRequisicao(assinante.getCodigoRequisicao());
            logger.info("getCodigoRequisicao = "+assinanteTO.getCodigoRequisicao());
            assinanteTO.setDadosCadastraisInterlocutor(assinante.getDadosCadastraisInterlocutor());
            logger.info("getDadosCadastraisInterlocutor = "+assinanteTO.getDadosCadastraisInterlocutor());
            assinanteTO.setNumeroSolicitacao(assinante.getNumeroSolicitacao());
            logger.info("getNumeroSolicitacao = "+assinanteTO.getNumeroSolicitacao());
            assinanteTO.setTipoServico(assinante.getTipoServico());
            logger.info("getTipoServico = "+assinanteTO.getTipoServico());
            assinanteTO.setDirecao(assinante.getDirecao());
            logger.info("getDirecao = "+assinanteTO.getDirecao());

            if(assinante.getInvestigados()!=null){
                logger.info("investigados = "+assinante.getInvestigados().size());
            }
            
            List investigadosTO = new ArrayList();
            List investigados = assinante.getInvestigados();
            for (Iterator iterator = investigados.iterator(); iterator.hasNext();) {
                Investigado investigado = (Investigado) iterator.next();
                InvestigadoTO investigadoTO = new InvestigadoTO();
                investigadoTO.setCpf(investigado.getCpf());
                logger.info("getCpf = "+investigadoTO.getCpf());
                investigadoTO.setCnpj(investigado.getCnpj());
                logger.info("getCnpj = "+investigadoTO.getCnpj());
                investigadoTO.setDocumentoAssinante(investigado.getDocumentoAssinante());
                logger.info("getDocumentoAssinante = "+investigadoTO.getDocumentoAssinante());
                investigadoTO.setNomeAssinante(investigado.getNomeAssinante());
                logger.info("getNomeAssinante = "+investigadoTO.getNomeAssinante());
                investigadoTO.setNumeroTerminalAssinante(investigado.getNumeroTerminalAssinante());
                logger.info("getNumeroTerminalAssinante = "+investigadoTO.getNumeroTerminalAssinante());
                investigadoTO.setImei(investigado.getImei());
                logger.info("getImei = "+investigadoTO.getImei());
                investigadoTO.setCgiErb(investigado.getCgiErb());
                logger.info("getCgiErb = "+investigadoTO.getCgiErb());
                investigadoTO.setEnderecoIp(investigado.getEnderecoIp());
                logger.info("getEnderecoIp = "+investigadoTO.getEnderecoIp());
                investigadoTO.setNumeroPortaIp(investigado.getNumeroPortaIp());
                logger.info("getNumeroPortaIp = "+investigadoTO.getNumeroPortaIp());
                investigadoTO.setInicioPeriodoQuebra(investigado.getInicioPeriodoQuebra());
                logger.info("getInicioPeriodoQuebra = "+investigadoTO.getInicioPeriodoQuebra());
                investigadoTO.setFimPeriodoQuebra(investigado.getFimPeriodoQuebra());
                logger.info("getFimPeriodoQuebra = "+investigadoTO.getFimPeriodoQuebra());
                investigadosTO.add(investigadoTO);
            }
            assinanteTO.setInvestigados(investigadosTO);

        } catch (Exception e) {
            logger.error("getAssinanteTO::", e);
        }
        return assinanteTO;
    }

}
