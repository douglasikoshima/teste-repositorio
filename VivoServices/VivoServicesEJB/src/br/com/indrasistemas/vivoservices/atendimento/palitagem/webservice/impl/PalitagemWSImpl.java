package br.com.indrasistemas.vivoservices.atendimento.palitagem.webservice.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import br.com.indrasistemas.framework.service.BusinessException;
import br.com.indrasistemas.framework.service.delegate.BusinessDelegateException;
import br.com.indrasistemas.framework.service.to.RequestInfoTO;
import br.com.indrasistemas.vivoservices.atendimento.palitagem.delegate.PalitagemBD;
import br.com.indrasistemas.vivoservices.atendimento.palitagem.to.FormularioCampoTO;
import br.com.indrasistemas.vivoservices.atendimento.palitagem.to.FormularioCampoValorTO;
import br.com.indrasistemas.vivoservices.atendimento.palitagem.to.FormularioTO;
import br.com.indrasistemas.vivoservices.atendimento.palitagem.to.PalitagemTO;
import br.com.indrasistemas.vivoservices.atendimento.palitagem.webservice.PalitagemWS;
import br.com.indrasistemas.vivoservices.atendimento.palitagem.webservice.to.FormularioCampoValorWSTO;
import br.com.indrasistemas.vivoservices.atendimento.palitagem.webservice.to.FormularioCampoWSTO;
import br.com.indrasistemas.vivoservices.atendimento.palitagem.webservice.to.FormularioWSTO;
import br.com.indrasistemas.vivoservices.atendimento.palitagem.webservice.to.PalitoParamWSTO;
import br.com.indrasistemas.vivoservices.atendimento.palitagem.webservice.to.PalitoWSTO;
import br.com.indrasistemas.vivoservices.webservice.to.RespostaWSTO;

public class PalitagemWSImpl implements PalitagemWS {

    private static final Log logger = LogFactory.getLog(PalitagemWSImpl.class);

    public PalitagemWSImpl() {
        super();
    }

    private FormularioTO getFormularioTOFromFormularioWSTO(FormularioWSTO fWSTO) {
        FormularioTO fTO = new FormularioTO();

        if (fWSTO != null && fWSTO.getFormularioCampoWSTO() != null && fWSTO.getFormularioCampoWSTO().length > 0) {
            FormularioCampoTO[] fCTOArray = new FormularioCampoTO[fWSTO.getFormularioCampoWSTO().length];

            for (int i = 0; i < fWSTO.getFormularioCampoWSTO().length; i++) {
                FormularioCampoWSTO fCWSTO = fWSTO.getFormularioCampoWSTO()[i];
                FormularioCampoTO fCTO = new FormularioCampoTO();
                fCTO.setIdCampo(fCWSTO.getIdCampo());
                fCTO.setIdContatoFolhaCampo(fCWSTO.getIdContatoFolhaCampo());
                
                if (fCWSTO.getFormularioCampoValorWSTO() != null && fCWSTO.getFormularioCampoValorWSTO().length > 0) {
                    FormularioCampoValorTO[] fCVTOArray = new FormularioCampoValorTO[fCWSTO.getFormularioCampoValorWSTO().length];
                    for (int j = 0; j < fCWSTO.getFormularioCampoValorWSTO().length; j++) {
                        FormularioCampoValorTO fCVTO = new FormularioCampoValorTO();
                        FormularioCampoValorWSTO fCVWSTO = fCWSTO.getFormularioCampoValorWSTO()[j];
                        fCVTO.setIdFormularioCampoValor(fCVWSTO.getIdFormularioCampoValor());
                        fCVTO.setValor(fCVWSTO.getValor());
                        fCVTOArray[j] = fCVTO;
                    }
                    fCTO.setFormularioCampoValorTO(fCVTOArray);
                }
                fCTOArray[i] = fCTO;
            }
            fTO.setFormularioCampoTO(fCTOArray);
        }
        return fTO;
    }

    public PalitoWSTO registrarPalito(RequestInfoTO requestInfo, PalitoParamWSTO palitoParam) {

        PalitoWSTO to = new PalitoWSTO();

        try {

            PalitagemTO toPesquisa = new PalitagemTO();
            toPesquisa.setCdServico(palitoParam.getCdServico());
            toPesquisa.setDsComentario(palitoParam.getDsComentario());
            toPesquisa.setIdCanal(String.valueOf(palitoParam.getIdCanal()));
            toPesquisa.setIdGrupoAbertura(String.valueOf(palitoParam.getIdGrpAbertura()));
            toPesquisa.setIdSistema(String.valueOf(palitoParam.getIdSistema()));
            toPesquisa.setIdUsuario(String.valueOf(palitoParam.getIdUsuario()));
            toPesquisa.setNrProtocolo(String.valueOf(palitoParam.getNrProtocolo()));
            
            toPesquisa.setIdContato(palitoParam.getIdContato());
            toPesquisa.setInConsultor(palitoParam.isConsultor());
            toPesquisa.setNrDocumento(palitoParam.getNrDocumento());
            toPesquisa.setTpOperacao(palitoParam.getTpOperacao());

            toPesquisa.setFormularioTO(getFormularioTOFromFormularioWSTO(palitoParam.getFormularioWSTO()));

            PalitagemBD bd = new PalitagemBD();
            if (logger.isDebugEnabled()) {
                logger.debug("Iniciando a chamada para registrar o palito BD");
            }
            PalitagemTO toRetorno = bd.registrarPalito(requestInfo, toPesquisa);

            Long nrProtocolo = new Long(0);
            if (toRetorno != null ) {
                if (toRetorno.getNrProtocolo() != null && !"".equals(toRetorno.getNrProtocolo())) {
                    nrProtocolo = new Long(toRetorno.getNrProtocolo());
                    to.setNrProtocolo(nrProtocolo);
                }
                if (toRetorno.getIdAtendimento() != null && !"".equals(toRetorno.getIdAtendimento())) {
                    to.setIdAtendimento(toRetorno.getIdAtendimento());
                }
                to.setStatus(RespostaWSTO.OK);
            } else {
                to.setStatus(RespostaWSTO.NAO_OK);
                to.setNrProtocolo(new Long(0));
                to.setIdAtendimento("");
                to.setCodError(" JE9999");
                to.setReason("retorno invalido");
            }

        } catch (BusinessDelegateException e) {
            if (logger.isDebugEnabled()) {
                logger.debug("Exception::BusinessDelegateException - (" + e.getMessage() + ")");
            }
            to.setStatus(RespostaWSTO.NAO_OK);
            String msgRetorno = e.getMessage();
            if (e.getMessage().indexOf("|") > 0) {
                to.setCodError(msgRetorno.split("\\|")[0]);
                to.setReason(msgRetorno.split("\\|")[1]);
            } else {
                to.setCodError("");
                to.setReason(msgRetorno);
            }

        } catch (BusinessException e) {
            if (logger.isDebugEnabled()) {
                logger.debug("Exception::BusinessException - (" + e.getMessage() + ")");
            }
            to.setStatus(RespostaWSTO.NAO_OK);
            String msgRetorno = e.getMessage();
            if (e.getMessage().indexOf("|") > 0) {
                to.setCodError(msgRetorno.split("\\|")[0]);
                to.setReason(msgRetorno.split("\\|")[1]);
            } else {
                to.setCodError("");
                to.setReason(msgRetorno);
            }
        }
        return to;
    }
}