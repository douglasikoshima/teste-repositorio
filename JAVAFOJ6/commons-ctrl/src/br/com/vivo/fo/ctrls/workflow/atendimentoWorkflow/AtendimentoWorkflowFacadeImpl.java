package br.com.vivo.fo.ctrls.workflow.atendimentoWorkflow;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import noNamespace.MsgDocument;
import weblogic.ejbgen.Constants.Bool;
import weblogic.ejbgen.Session;
import weblogic.ejbgen.Session.SessionType;
import br.com.vivo.fo.admsistemas.vo.AdmFolhaBaixaVODocument;
import br.com.vivo.fo.admsistemas.vo.AdmFolhaBaixaVODocument.AdmFolhaBaixaVO;
import br.com.vivo.fo.admsistemas.vo.AdmSatisfacaoContainerVODocument;
import br.com.vivo.fo.admsistemas.vo.AdmSatisfacaoContainerVODocument.AdmSatisfacaoContainerVO;
import br.com.vivo.fo.admsistemas.vo.ArvoreAtendimentoVODocument;
import br.com.vivo.fo.admsistemas.vo.ArvoreAtendimentoVODocument.ArvoreAtendimentoVO;
import br.com.vivo.fo.atmi.TuxedoServiceCall;
import br.com.vivo.fo.atmi.TuxedoServiceCallerException;
import br.com.vivo.fo.commons.utils.geral.TuxedoServiceBridge;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.exception.FacadeException;
import br.com.vivo.fo.exception.TuxedoException;
import br.com.vivo.fo.log.Logger;
import br.com.vivo.fo.usuario.vo.UsuarioVIVODocument.UsuarioVIVO;
import br.com.vivo.fo.workflow.vo.AtdWFVODocument.AtdWFVO;
import br.com.vivo.fo.workflow.vo.AtendimentoInformacaoVODocument;
import br.com.vivo.fo.workflow.vo.AtendimentoInformacaoVODocument.AtendimentoInformacaoVO;
import br.com.vivo.fo.workflow.vo.AtendimentoVODocument.AtendimentoVO;
import br.com.vivo.fo.workflow.vo.AtendimentoWorkflowComumVODocument.AtendimentoWorkflowComumVO;
import br.com.vivo.fo.workflow.vo.AtendimentoWorkflowTecnicoDocVODocument.AtendimentoWorkflowTecnicoDocVO;
import br.com.vivo.fo.workflow.vo.AtendimentoWorkflowTecnicoVODocument;
import br.com.vivo.fo.workflow.vo.AtendimentoWorkflowTecnicoVODocument.AtendimentoWorkflowTecnicoVO;
import br.com.vivo.fo.workflow.vo.AtendimentoWorkflowTestesVODocument;
import br.com.vivo.fo.workflow.vo.AtendimentoWorkflowTestesVODocument.AtendimentoWorkflowTestesVO;
import br.com.vivo.fo.workflow.vo.AtendimentoWorkflowVODocument;
import br.com.vivo.fo.workflow.vo.AtendimentoWorkflowVODocument.AtendimentoWorkflowVO;
import br.com.vivo.fo.workflow.vo.WFAcaoOrdemVendaVODocument;
import br.com.vivo.fo.workflow.vo.WFAcaoOrdemVendaVODocument.WFAcaoOrdemVendaVO;
import br.com.vivo.fo.workflow.vo.WFAcaoRetornoVODocument;
import br.com.vivo.fo.workflow.vo.WFAcaoRetornoVODocument.WFAcaoRetornoVO;
import br.com.vivo.fo.workflow.vo.WFDocumentoTecnicoPesquisaVODocument.WFDocumentoTecnicoPesquisaVO;
import br.com.vivo.fo.xml.XmlManager;

@Stateless(name = "AtendimentoWorkflowFacade", mappedName = "AtendimentoWorkflowFacade")
@Local(AtendimentoWorkflowFacade.class)
@Session(ejbName = "AtendimentoWorkflowFacade", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.NOT_SUPPORTED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class AtendimentoWorkflowFacadeImpl implements AtendimentoWorkflowFacade {

    @EJB
    private TuxedoServiceCall tuxedo;

    private static Logger     log = new Logger("workflow");

    /**
     * @common:operation
     */
    public WFAcaoOrdemVendaVO carregarOrdemVendaAtualizar(String idPessoaUsuario, String idAtendimento, String idAtividade) throws TuxedoException, FacadeException {
        try {
            log.debug("AtendimentoWorkflowFacadeImpl:carregarOrdemVendaAtualizar(" + idPessoaUsuario + ")");

            StringBuffer xmlInBuffer = new StringBuffer();
            xmlInBuffer.append("<atendimentos>");
            xmlInBuffer.append("<xml-fragment>");
            xmlInBuffer.append("<AtendimentoWorkflowVO xmlns:vo='workflow.fo.vivo.com.br/vo'>");
            xmlInBuffer.append("<AtendimentoWorkflowComumVO>");
            xmlInBuffer.append("<idPessoaUsuario>").append(idPessoaUsuario).append("</idPessoaUsuario>");
            xmlInBuffer.append("</AtendimentoWorkflowComumVO>");
            xmlInBuffer.append("<AtendimentoWorkflowVO>");
            xmlInBuffer.append("<AtdWFVO xmlns:vo='workflow.fo.vivo.com.br/vo'>");
            xmlInBuffer.append("<idAtendimento>").append(idAtendimento).append("</idAtendimento>");
            xmlInBuffer.append("</AtdWFVO>");
            xmlInBuffer.append("<WFAcaoVO xmlns:vo='workflow.fo.vivo.com.br/vo'>");
            xmlInBuffer.append("<idAtividade>").append(idAtividade).append("</idAtividade>");
            xmlInBuffer.append("<inCRI>0</inCRI>");
            xmlInBuffer.append("</WFAcaoVO>");
            xmlInBuffer.append("</AtendimentoWorkflowVO>");
            xmlInBuffer.append("</AtendimentoWorkflowVO>");
            xmlInBuffer.append("</xml-fragment>");
            xmlInBuffer.append("</atendimentos>");

            String xmlOut = tuxedo.callService("TuxConnector", TuxedoServiceBridge.getXMLRequest(idPessoaUsuario, "COREWORKFLOW", xmlInBuffer.toString()));

            log.debug("xmlOut = " + xmlOut);
            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOut);
            return WFAcaoOrdemVendaVODocument.Factory.parse(msgDocRet.getMsg().getMsgBody().xmlText()).getWFAcaoOrdemVendaVO();
        } catch (Exception ex) {
            log.error("Exception - AtendimentoWorkflowFacadeImpl:carregarOrdemVendaAtualizar(" + idPessoaUsuario + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }

    }

    /**
     * @common:operation
     */
    public WFAcaoRetornoVO atualizarOrdemVenda(String idPessoaUsuario, WFAcaoOrdemVendaVO wFAcaoOrdemVendaVO, String idAtendimento, String idAtividade) throws TuxedoException, FacadeException {
        try {

            StringBuffer xmlInBuffer = new StringBuffer();
            xmlInBuffer.append("<AtendimentoWorkflowVO xmlns:vo='workflow.fo.vivo.com.br/vo'>");
            xmlInBuffer.append("<AtendimentoWorkflowComumVO>");
            xmlInBuffer.append("<idPessoaUsuario>").append(idPessoaUsuario).append("</idPessoaUsuario>");
            xmlInBuffer.append("</AtendimentoWorkflowComumVO>");
            xmlInBuffer.append("<AtendimentoWorkflowVO>");
            xmlInBuffer.append(wFAcaoOrdemVendaVO.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO));
            xmlInBuffer.append("<AtdWFVO xmlns:vo='workflow.fo.vivo.com.br/vo'>");
            xmlInBuffer.append("<idAtendimento>").append(idAtendimento).append("</idAtendimento>");
            xmlInBuffer.append("</AtdWFVO>");
            xmlInBuffer.append("<WFAcaoVO xmlns:vo='workflow.fo.vivo.com.br/vo'>");
            xmlInBuffer.append("<idAtividade>69</idAtividade>");
            xmlInBuffer.append("<inCRI>0</inCRI>");
            xmlInBuffer.append("</WFAcaoVO>");
            xmlInBuffer.append("</AtendimentoWorkflowVO>");
            xmlInBuffer.append("</AtendimentoWorkflowVO>");

            String xmlOut = tuxedo.callService("TuxConnector", TuxedoServiceBridge.getXMLRequest(idPessoaUsuario, "COREWORKFLOW", xmlInBuffer.toString()));

            log.debug("xmlOut = " + xmlOut);
            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOut);
            return WFAcaoRetornoVODocument.Factory.parse(msgDocRet.getMsg().getMsgBody().xmlText()).getWFAcaoRetornoVO();
        } catch (Exception ex) {
            log.error("Exception - AtendimentoWorkflowFacadeImpl:listaEncaminharGravar(" + idPessoaUsuario + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public WFAcaoRetornoVO cancelarOrdemVenda(String idPessoaUsuario, String dsObservacao, String idBaixa, String dsComentario, String idAtendimento, String idAtividade) throws TuxedoException, FacadeException {
        try {

            StringBuffer xmlInBuffer = new StringBuffer();
            xmlInBuffer.append("<atendimentos>");
            xmlInBuffer.append("<xml-fragment>");
            xmlInBuffer.append("<AtendimentoWorkflowVO xmlns:ns1='workflow.fo.vivo.com.br/vo' xmlns='admsistemas.fo.vivo.com.br/vo'>");
            xmlInBuffer.append("<AtendimentoWorkflowComumVO>");
            xmlInBuffer.append("<dsObservacao>").append(dsObservacao).append("</dsObservacao>");
            xmlInBuffer.append("<WFGrupoVO>");
            xmlInBuffer.append("<idGrupo/>");
            xmlInBuffer.append("</WFGrupoVO>");
            xmlInBuffer.append("<WFMotivoVO>");
            xmlInBuffer.append("<idMotivo/>");
            xmlInBuffer.append("</WFMotivoVO>");
            xmlInBuffer.append("<UsuarioVIVO xmlns:vo='usuario.fo.vivo.com.br/vo'>");
            xmlInBuffer.append("<idPessoaUsuario/>");
            xmlInBuffer.append("</UsuarioVIVO>");
            xmlInBuffer.append("</AtendimentoWorkflowComumVO>");
            xmlInBuffer.append("<AtendimentoWorkflowEncerramentoVO>");
            xmlInBuffer.append("<ArvoreEncerramentoVO>");
            xmlInBuffer.append("<EncerramentoVO>");
            xmlInBuffer.append("<idBaixa>").append(idBaixa).append("</idBaixa>");
            xmlInBuffer.append("<idBaixaMensagem>").append("idBaixaMensagem").append("</idBaixaMensagem>");
            xmlInBuffer.append("<dsComentario>").append(dsComentario).append("</dsComentario>");
            xmlInBuffer.append("<dsDocumentoAssociado/>");
            xmlInBuffer.append("</EncerramentoVO>");
            xmlInBuffer.append("<FormularioVO/>");
            xmlInBuffer.append("</ArvoreEncerramentoVO>");
            xmlInBuffer.append("</AtendimentoWorkflowEncerramentoVO>");
            xmlInBuffer.append("</AtendimentoWorkflowVO>");
            xmlInBuffer.append("<AtdWFVO xmlns:vo='workflow.fo.vivo.com.br/vo'>");
            xmlInBuffer.append("<idAtendimento>").append(idAtendimento).append("</idAtendimento>");
            xmlInBuffer.append("</AtdWFVO>");
            xmlInBuffer.append("<WFAcaoVO xmlns:vo='workflow.fo.vivo.com.br/vo'>");
            xmlInBuffer.append("<idAtividade>").append(idAtividade).append("</idAtividade>");
            xmlInBuffer.append("<inCRI>0</inCRI>");
            xmlInBuffer.append("</WFAcaoVO>");
            xmlInBuffer.append("</xml-fragment>");
            xmlInBuffer.append("</atendimentos>");

            String xmlOut = tuxedo.callService("TuxConnector", TuxedoServiceBridge.getXMLRequest(idPessoaUsuario,"COREWORKFLOW", xmlInBuffer.toString()));

            log.debug("xmlOut = " + xmlOut);
            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOut);
            return WFAcaoRetornoVODocument.Factory.parse(msgDocRet.getMsg().getMsgBody().xmlText()).getWFAcaoRetornoVO();
        } catch (Exception ex) {
            log.error("Exception - AtendimentoWorkflowFacadeImpl:cancelarOrdemVenda(" + idPessoaUsuario + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public WFAcaoRetornoVO listaEncaminharGravar(String user, AtendimentoVO[] atendimentosVO) throws TuxedoException, FacadeException {
        try {
            log.debug("AtendimentoWorkflowFacadeImpl:listaEncaminharGravar(" + user + ", " + atendimentosVO + ")");
            String xmlIn = getXmlAtendimentosVO(atendimentosVO);
            String xmlOut = tuxedo.callService("TuxConnector", TuxedoServiceBridge.getXMLRequest(user,"COREWORKFLOW", xmlIn));

            log.debug("xmlOut = " + xmlOut);
            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOut);

            return WFAcaoRetornoVODocument.Factory.parse(msgDocRet.getMsg().getMsgBody().xmlText()).getWFAcaoRetornoVO();

        } catch (Exception ex) {
            log.error("Exception - AtendimentoWorkflowFacadeImpl:listaEncaminharGravar(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public WFAcaoRetornoVO gravaMotivoComentarioRespCliente(String user, AtendimentoVO[] atendimentosVO) throws TuxedoException, FacadeException {
        try {
            log.debug("AtendimentoWorkflowFacadeImpl:gravaMotivoComentarioRespCliente(" + user + ", " + atendimentosVO + ")");
            String xmlIn = getXmlAtendimentosVO(atendimentosVO);

            String xmlOut = tuxedo.callService("TuxConnector", TuxedoServiceBridge.getXMLRequest(user,"COREWORKFLOW", xmlIn));

            log.debug("xmlOut = " + xmlOut);
            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOut);
            return WFAcaoRetornoVODocument.Factory.parse(msgDocRet.getMsg().getMsgBody().xmlText()).getWFAcaoRetornoVO();
        } catch (Exception ex) {
            log.error("Exception - AtendimentoWorkflowFacadeImpl:gravaMotivoComentarioRespCliente(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public WFAcaoRetornoVO pausaGravar(String user, AtendimentoVO[] atendimentosVO) throws TuxedoException, FacadeException {
        try {
            log.debug("AtendimentoWorkflowFacadeImpl:pausaGravar(" + user + ")");
            String xmlIn = getXmlAtendimentosVO(atendimentosVO);

            String xmlOut = tuxedo.callService("TuxConnector", TuxedoServiceBridge.getXMLRequest(user,"COREWORKFLOW", xmlIn));

            log.debug("xmlOut = " + xmlOut);
            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOut);
            return WFAcaoRetornoVODocument.Factory.parse(msgDocRet.getMsg().getMsgBody().xmlText()).getWFAcaoRetornoVO();
        } catch (Exception ex) {
            log.error("Exception - AtendimentoWorkflowFacadeImpl:pausaGravar(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: AtendimentoWorkflowFacadeImpl:pausaGravar", ex));
        }
    }

    /**
     * @common:operation
     */
    public ArvoreAtendimentoVO obtemFormularioArvoreBaixa(String user, String idAtendimento, String idContato) throws TuxedoException, FacadeException {
        try {
            log.debug("AtendimentoWorkflowFacadeImpl:obtemFormularioArvoreBaixa(" + user + ", " + idAtendimento + ")");
            StringBuffer xmlIN = new StringBuffer();
            xmlIN.append("<idAtendimento>" + idAtendimento + "</idAtendimento>\n");
            xmlIN.append("<idContato>" + idContato + "</idContato>\n");

            String xmlOut = tuxedo.callService("TuxConnector", TuxedoServiceBridge.getXMLRequest(user,"WFOBTFRMDIN", xmlIN.toString()));

            log.debug("xmlOut = " + xmlOut);
            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOut);
            return ArvoreAtendimentoVODocument.Factory.parse(msgDocRet.getMsg().getMsgBody().xmlText()).getArvoreAtendimentoVO();
        } catch (Exception ex) {
            log.error("Exception - AtendimentoWorkflowFacadeImpl:obtemFormularioArvoreBaixa(" + user + ", " + idAtendimento + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public AdmFolhaBaixaVO obtemArvoreBaixaParte(String user, String idAtendimento, String idBaixa, String idContato, String idTipoComunicacao) throws TuxedoException, FacadeException {
        try {
            log.debug("AtendimentoWorkflowFacadeImpl:obtemArvoreBaixaParte(" + user + ", " + idAtendimento + ")");

            StringBuffer xmlIN = new StringBuffer();
            if (idAtendimento != null && idAtendimento.length()>0) {
                xmlIN.append("<idAtendimento>" + idAtendimento + "</idAtendimento>\n");
            }
            if (((idBaixa != null) && (!idBaixa.equals("")))) {
                xmlIN.append("<idBaixa>" + idBaixa + "</idBaixa>\n");
            }
            if (((idContato != null) && (!idContato.equals("")))) {
                xmlIN.append("<idContato>" + idContato + "</idContato>\n");
            }
            if (((idTipoComunicacao != null) && (!idTipoComunicacao.equals("")))) {
                xmlIN.append("<idTipoComunicacao>" + idTipoComunicacao + "</idTipoComunicacao>\n");
            }

            String xmlINAux = XmlManager.MakeXmlIn(user, "BxaListar", xmlIN.toString());
            String xmlOUT = tuxedo.callService("TuxConnector", xmlINAux);

            log.debug("xmlOut = " + xmlOUT);
            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);

            return AdmFolhaBaixaVODocument.Factory.parse(msgDocRet.getMsg().getMsgBody().xmlText()).getAdmFolhaBaixaVO();

        } catch (TuxedoServiceCallerException ex) {
            log.error("TuxedoException - AtendimentoWorkflowFacadeImpl:obtemArvoreBaixaParte(" + user + ", " + idAtendimento + ") - [" + ex.getMessage() + "]");
            throw new TuxedoException(ex);

        } catch (Exception ex) {
            log.error("Exception - AtendimentoWorkflowFacadeImpl:obtemArvoreBaixaParte(" + user + ", " + idAtendimento + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public WFAcaoRetornoVO listaSuspeitoGravar(String user, AtendimentoVO[] atendimentosVO) throws TuxedoException, FacadeException {
        try {
            log.debug("AtendimentoWorkflowFacadeImpl:listaSuspeitoGravar(" + user + ")");
            String xmlIn = getXmlAtendimentosVO(atendimentosVO);
            String xmlOut = tuxedo.callService("TuxConnector", TuxedoServiceBridge.getXMLRequest(user,"COREWORKFLOW", xmlIn));

            log.debug("xmlOut = " + xmlOut);
            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOut);
            return WFAcaoRetornoVODocument.Factory.parse(msgDocRet.getMsg().getMsgBody().xmlText()).getWFAcaoRetornoVO();
        } catch (Exception ex) {
            log.error("Exception - AtendimentoWorkflowFacadeImpl:listaSuspeitoGravar(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public WFAcaoRetornoVO listaEncerrarGravar(String user, AtendimentoVO[] atendimentosVO, String idFase, boolean fechMassa) throws TuxedoException, FacadeException {
        try {
            AtendimentoVO atdVo = atendimentosVO[0];
            AtendimentoVO tmpAtdVO = AtendimentoVO.Factory.newInstance();
            tmpAtdVO.setWFAcaoVOArray(atdVo.getWFAcaoVOArray());
            AtendimentoVO[] tmpAtdVOArray = new AtendimentoVO[1];
            tmpAtdVOArray[0] = tmpAtdVO;
            tmpAtdVO.addNewAtdWFVO();
            AtendimentoWorkflowVO atdDetFech = tmpAtdVO.addNewAtendimentoWorkflowVO();
            atdDetFech.set(atdVo.getAtendimentoWorkflowVO());
            int totAtd = atdVo.getAtdWFVOArray().length;
            String xmlOut = null;
            int i = 0;
            int c = 0;
            final int PROC_POR_BLOCO = 25;
            while (i < totAtd) {
                int tAtdDelete = tmpAtdVO.getAtdWFVOArray().length;
                for (int j = 0; j < tAtdDelete; j++) {
                    tmpAtdVO.removeAtdWFVO(0);
                }

                while (c < PROC_POR_BLOCO && i < totAtd) {
                    AtdWFVO atdWFVO = tmpAtdVO.addNewAtdWFVO();
                    atdWFVO.set(atdVo.getAtdWFVOArray()[i]);
                    i++;
                    c++;
                }
                c = 0;
                String xmlIn = getXmlAtendimentosVO(tmpAtdVOArray);

                StringBuffer strXmlIn = new StringBuffer();
                strXmlIn.append(xmlIn);

                if ((idFase != null) && (idFase.length() > 0)) {
                    strXmlIn.append("<idFase>");
                    strXmlIn.append(idFase);
                    strXmlIn.append("</idFase>");
                }
                
                xmlOut = ConstantesCRM.SVAZIO;
                
                if (fechMassa) {
                    String str = TuxedoServiceBridge.getXMLRequest(user,"CERRAMEFEC", strXmlIn.toString());
                    strXmlIn = new StringBuffer(str);
                } else {
                    String str = TuxedoServiceBridge.getXMLRequest(user,"COREWORKFLOW", strXmlIn.toString());
                    strXmlIn = new StringBuffer(str);
                }
                xmlOut = tuxedo.callService("TuxConnector", strXmlIn.toString());
            }

            log.debug("xmlOut = " + xmlOut);
            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOut);
            return WFAcaoRetornoVODocument.Factory.parse(msgDocRet.getMsg().getMsgBody().xmlText()).getWFAcaoRetornoVO();
        } catch (Exception ex) {
            log.error("Exception - AtendimentoWorkflowFacadeImpl:listaEncerrarGravar(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public WFAcaoRetornoVO listaVoltarGravar(String user, AtendimentoVO[] atendimentosVO, String idFase) throws TuxedoException, FacadeException {
        try {
            log.debug("AtendimentoWorkflowFacadeImpl:listaVoltarGravar(" + user + ")");
            String xmlIn = getXmlAtendimentosVO(atendimentosVO);
            StringBuffer strXmlIn = new StringBuffer();
            strXmlIn.append(xmlIn);
            if ((idFase != null) && (idFase.length() > 0)) {
                strXmlIn.append("<idFase>");
                strXmlIn.append(idFase);
                strXmlIn.append("</idFase>");
            }

            String xmlOut = tuxedo.callService("TuxConnector", TuxedoServiceBridge.getXMLRequest(user,"COREWORKFLOW", strXmlIn.toString()));

            log.debug("xmlOut = " + xmlOut);
            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOut);
            return WFAcaoRetornoVODocument.Factory.parse(msgDocRet.getMsg().getMsgBody().xmlText()).getWFAcaoRetornoVO();
        } catch (Exception ex) {
            log.error("Exception - AtendimentoWorkflowFacadeImpl:listaVoltarGravar(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public WFAcaoRetornoVO fechamentoGravar(String user, AtendimentoVO[] atendimentosVO) throws TuxedoException, FacadeException {
        try {
            log.debug("AtendimentoWorkflowFacadeImpl:fechamentoGravar(" + user + ")");
            String xmlIn = getXmlAtendimentosVO(atendimentosVO);
            String xmlOut = tuxedo.callService("TuxConnector", TuxedoServiceBridge.getXMLRequest(user,"COREWORKFLOW", xmlIn));

            log.debug("xmlOut = " + xmlOut);
            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOut);
            return WFAcaoRetornoVODocument.Factory.parse(msgDocRet.getMsg().getMsgBody().xmlText()).getWFAcaoRetornoVO();
        
        }catch (Exception ex) {
            log.error("Exception - AtendimentoWorkflowFacadeImpl:fechamentoGravar(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: AtendimentoWorkflowFacadeImpl:fechamentoGravar", ex));
        }
    }

    /**
     * @common:operation
     */
    public WFAcaoRetornoVO concluirGravar(String user, AtendimentoVO[] atendimentosVO) throws TuxedoException, FacadeException {
        try {
            log.debug("AtendimentoWorkflowFacadeImpl:concluirGravar(" + user + ")");
            String xmlIn = getXmlAtendimentosVO(atendimentosVO);
            String xmlOut = tuxedo.callService("TuxConnector", TuxedoServiceBridge.getXMLRequest(user,"COREWORKFLOW", xmlIn));

            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOut);
            return WFAcaoRetornoVODocument.Factory.parse(msgDocRet.getMsg().getMsgBody().xmlText()).getWFAcaoRetornoVO();
        } catch (Exception ex) {
            log.error("Exception - AtendimentoWorkflowFacadeImpl:concluirGravar(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: AtendimentoWorkflowFacadeImpl:concluirGravar", ex));
        }
    }

    /**
     * @common:operation
     */
    public WFAcaoRetornoVO concluirGravarAbaRel(String user, AtendimentoVO[] atendimentosVO) throws TuxedoException, FacadeException {
        try {
            log.debug("AtendimentoWorkflowFacadeImpl:concluirGravar(" + user + ")");
            String xmlIn = getXmlAtendimentosVO(atendimentosVO);
            xmlIn += "<inFO>0</inFO>";
            xmlIn += "<idFase>1</idFase>";

            String xmlOut = tuxedo.callService("TuxConnector", TuxedoServiceBridge.getXMLRequest(user,"COREWORKFLOW", xmlIn));

            log.debug("xmlOut = " + xmlOut);
            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOut);
            return WFAcaoRetornoVODocument.Factory.parse(msgDocRet.getMsg().getMsgBody().xmlText()).getWFAcaoRetornoVO();
        } catch (Exception ex) {
            log.error("Exception - AtendimentoWorkflowFacadeImpl:concluirGravar(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: AtendimentoWorkflowFacadeImpl:concluirGravar", ex));
        }
    }

    /**
     * @common:operation
     */
    public WFAcaoRetornoVO cancelarGravar(String user, AtendimentoVO[] atendimentosVO, String idFase) throws TuxedoException, FacadeException {
        try {
            log.debug("AtendimentoWorkflowFacadeImpl:cancelarGravar(" + user + ")");
            String xmlIn = getXmlAtendimentosVO(atendimentosVO);
            StringBuffer strXmlIn = new StringBuffer();
            strXmlIn.append(xmlIn);
            if (idFase != null) {
                strXmlIn.append("<idFase>" + idFase + "</idFase>");
            }

            String xmlOut = tuxedo.callService("TuxConnector", TuxedoServiceBridge.getXMLRequest(user,"COREWORKFLOW", strXmlIn.toString()));

            log.debug("xmlOut = " + xmlOut);
            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOut);
            return WFAcaoRetornoVODocument.Factory.parse(msgDocRet.getMsg().getMsgBody().xmlText()).getWFAcaoRetornoVO();
        } catch (Exception ex) {
            log.error("Exception - AtendimentoWorkflowFacadeImpl:cancelarGravar(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: AtendimentoWorkflowFacadeImpl:cancelarGravar", ex));
        }
    }

    /**
     * @common:operation
     */
    public WFAcaoRetornoVO cancelarGravarAbaRel(String user, AtendimentoVO[] atendimentosVO, String idFase) throws TuxedoException, FacadeException {
        try {
            log.debug("AtendimentoWorkflowFacadeImpl:cancelarGravar(" + user + ")");
            String xmlIn = getXmlAtendimentosVO(atendimentosVO);
            StringBuffer strXmlIn = new StringBuffer();
            strXmlIn.append(xmlIn);
            if (idFase != null) {
                strXmlIn.append("<idFase>" + idFase + "</idFase>");
            }
            strXmlIn.append("<inFO>0</inFO>");

            String xmlOut = tuxedo.callService("TuxConnector", TuxedoServiceBridge.getXMLRequest(user,"COREWORKFLOW", strXmlIn.toString()));

            log.debug("xmlOut = " + xmlOut);
            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOut);
            return WFAcaoRetornoVODocument.Factory.parse(msgDocRet.getMsg().getMsgBody().xmlText()).getWFAcaoRetornoVO();
        } catch (Exception ex) {
            log.error("Exception - AtendimentoWorkflowFacadeImpl:cancelarGravar(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: AtendimentoWorkflowFacadeImpl:cancelarGravar", ex));
        }
    }

    /**
     * @common:operation
     */
    public WFAcaoRetornoVO respostaCliente(String user, AtendimentoVO[] atendimentosVO, String idFase) throws TuxedoException, FacadeException {
        try {
            log.debug("AtendimentoWorkflowFacadeImpl:respostaCliente(" + user + ")");
            String xmlIn = getXmlAtendimentosVO(atendimentosVO);
            StringBuffer strXmlIn = new StringBuffer();
            strXmlIn.append(xmlIn);
            if (idFase != null) {
                strXmlIn.append("<idFase>" + idFase + "</idFase>");
            }
            strXmlIn.append("<inRC>1</inRC>");

            String xmlOut = tuxedo.callService("TuxConnector", TuxedoServiceBridge.getXMLRequest(user,"COREWORKFLOW", strXmlIn.toString()));

            log.debug("xmlOut = " + xmlOut);
            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOut);
            return WFAcaoRetornoVODocument.Factory.parse(msgDocRet.getMsg().getMsgBody().xmlText()).getWFAcaoRetornoVO();
        } catch (Exception ex) {
            log.error("Exception - AtendimentoWorkflowFacadeImpl:respostaCliente(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: AtendimentoWorkflowFacadeImpl:respostaCliente", ex));
        }
    }

    /**
     * @common:operation
     */
    public WFAcaoRetornoVO reaberturaGravar(String user, AtendimentoVO[] atendimentosVO) throws TuxedoException, FacadeException {
        try {
            log.debug("AtendimentoWorkflowFacadeImpl:reaberturaGravar(" + user + ")");
            String xmlIn = getXmlAtendimentosVO(atendimentosVO);
            StringBuffer strXmlIn = new StringBuffer();
            strXmlIn.append(xmlIn);
            strXmlIn.append("<idFase>1</idFase>");

            String xmlOut = tuxedo.callService("TuxConnector", TuxedoServiceBridge.getXMLRequest(user,"COREWORKFLOW", strXmlIn.toString()));

            log.debug("xmlOut = " + xmlOut);
            MsgDocument msgDoc = MsgDocument.Factory.parse(xmlOut);
            return WFAcaoRetornoVODocument.Factory.parse(msgDoc.getMsg().getMsgBody().xmlText()).getWFAcaoRetornoVO();
        } catch (Exception ex) {
            log.error("Exception - AtendimentoWorkflowFacadeImpl:reaberturaGravar(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: AtendimentoWorkflowFacadeImpl:reaberturaGravar", ex));
        }
    }

    /**
     * @common:operation
     */
    public WFAcaoRetornoVO reaberturaGravarAbaRel(String user, AtendimentoVO[] atendimentosVO) throws TuxedoException, FacadeException {
        try {
            log.debug("AtendimentoWorkflowFacadeImpl:reaberturaGravar(" + user + ")");
            String xmlIn = getXmlAtendimentosVO(atendimentosVO);
            StringBuffer strXmlIn = new StringBuffer();
            strXmlIn.append(xmlIn);
            strXmlIn.append("<idFase>1</idFase>");
            strXmlIn.append("<inFO>0</inFO>");

            String xmlOut = tuxedo.callService("TuxConnector", TuxedoServiceBridge.getXMLRequest(user,"COREWORKFLOW", strXmlIn.toString()));

            log.debug("xmlOut = " + xmlOut);
            MsgDocument msgDoc = MsgDocument.Factory.parse(xmlOut);
            return WFAcaoRetornoVODocument.Factory.parse(msgDoc.getMsg().getMsgBody().xmlText()).getWFAcaoRetornoVO();
        } catch (Exception ex) {
            log.error("Exception - AtendimentoWorkflowFacadeImpl:reaberturaGravar(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: AtendimentoWorkflowFacadeImpl:reaberturaGravar", ex));
        }
    }

    /**
     * @common:operation
     */
    public WFAcaoRetornoVO insistenciaGravarAbaRelacionamento(String user, AtendimentoVO[] atendimentosVO) throws TuxedoException, FacadeException {
        try {
            log.debug("AtendimentoWorkflowFacadeImpl:insistenciaGravarAbaRelacionamento(" + user + ")");
            String xmlIn = getXmlAtendimentosVO(atendimentosVO);
            StringBuffer strXmlIn = new StringBuffer();
            strXmlIn.append(xmlIn);
            strXmlIn.append("<idFase>1</idFase>");
            strXmlIn.append("<inFO>0</inFO>");

            String xmlOut = tuxedo.callService("TuxConnector", TuxedoServiceBridge.getXMLRequest(user,"COREWORKFLOW", strXmlIn.toString()));

            log.debug("xmlOut = " + xmlOut);
            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOut);
            return WFAcaoRetornoVODocument.Factory.parse(msgDocRet.getMsg().getMsgBody().xmlText()).getWFAcaoRetornoVO();
        } catch (Exception ex) {
            log.error("Exception - AtendimentoWorkflowFacadeImpl:insistenciaGravarAbaRelacionamento(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public WFAcaoRetornoVO insistenciaGravar(String user, AtendimentoVO[] atendimentosVO) throws TuxedoException, FacadeException {
        try {
            log.debug("AtendimentoWorkflowFacadeImpl:insistenciaGravar(" + user + ")");
            String xmlIn = getXmlAtendimentosVO(atendimentosVO);
            StringBuffer strXmlIn = new StringBuffer();
            strXmlIn.append(xmlIn).append("<idFase>1</idFase>");
            
            String xmlOut = tuxedo.callService("TuxConnector", TuxedoServiceBridge.getXMLRequest(user,"COREWORKFLOW", strXmlIn.toString()));

            log.debug("xmlOut = " + xmlOut);
            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOut);
            return WFAcaoRetornoVODocument.Factory.parse(msgDocRet.getMsg().getMsgBody().xmlText()).getWFAcaoRetornoVO();
        } catch (Exception ex) {
            log.error("Exception - AtendimentoWorkflowFacadeImpl:insistenciaGravar(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public WFAcaoRetornoVO solucaoTecnicaGravar(String user, AtendimentoWorkflowTestesVO atendimentoWorkflowTestesVO) throws TuxedoException, FacadeException {
        try {
            log.debug("AtendimentoWorkflowFacadeImpl:solucaoTecnicaGravar(" + user + ")");
            StringBuffer xmlIn = new StringBuffer("<idAtividade>20</idAtividade>");
            xmlIn.append(atendimentoWorkflowTestesVO.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO));

            String xmlOUT = tuxedo.callService("TuxConnector", TuxedoServiceBridge.getXMLRequest(user,"WFATDPSATGRAVA",xmlIn.toString()));

            log.debug("xmlOut = " + xmlOUT);
            MsgDocument msgDoc = MsgDocument.Factory.parse(xmlOUT);
            return WFAcaoRetornoVODocument.Factory.parse(msgDoc.getMsg().getMsgBody().xmlText()).getWFAcaoRetornoVO();
        } catch (Exception ex) {
            log.error("Exception - AtendimentoWorkflowFacadeImpl:solucaoTecnicaGravar(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: AtendimentoWorkflowFacadeImpl:solucaoTecnicaGravar", ex));
        }
    }

    /**
     * @common:operation
     */
    public AdmSatisfacaoContainerVO obtemPesquisaSatisfacao(String user, String idAtendimento) throws TuxedoException, FacadeException {
        try {
            log.debug("AtendimentoWorkflowFacadeImpl:obtemPesquisaSatisfacao(" + user + ")");
            String xmlIN = "<idAtendimento>" + idAtendimento + "</idAtendimento>";
            String xmlOut = tuxedo.callService("TuxConnector", TuxedoServiceBridge.getXMLRequest(user, "WFATDRELPESATI", xmlIN));

            log.debug("xmlOut = " + xmlOut);
            MsgDocument msgDoc = MsgDocument.Factory.parse(xmlOut);
            return AdmSatisfacaoContainerVODocument.Factory.parse(msgDoc.getMsg().getMsgBody().xmlText()).getAdmSatisfacaoContainerVO();
        } catch (Exception ex) {
            log.error("Exception - AtendimentoWorkflowFacadeImpl:obtemPesquisaSatisfacao(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: AtendimentoWorkflowFacadeImpl:obtemPesquisaSatisfacao", ex));
        }
    }

    /**
     * @common:operation
     */
    public WFAcaoRetornoVO pesquisaSatisfacaoGravar(String user, AdmSatisfacaoContainerVO admSatisfacaoContainerVO) throws TuxedoException, FacadeException {
        try {
            log.debug("AtendimentoWorkflowFacadeImpl:pesquisaSatisfacaoGravar(" + user + "," + admSatisfacaoContainerVO + ")");
            String xmlIn = admSatisfacaoContainerVO.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
            String xmlOut = tuxedo.callService("TuxConnector", TuxedoServiceBridge.getXMLRequest(user, "WFATDPSATGRAVA", xmlIn));

            log.debug("xmlOut = " + xmlOut);
            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOut);
            return WFAcaoRetornoVODocument.Factory.parse(msgDocRet.getMsg().getMsgBody().xmlText()).getWFAcaoRetornoVO();
        
        }catch (Exception ex) {
            log.error("Exception - AtendimentoWorkflowFacadeImpl:pesquisaSatisfacaoGravar(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: AtendimentoWorkflowFacadeImpl:pesquisaSatisfacaoGravar", ex));
        }
    }

    /**
     * @common:operation
     */
    public UsuarioVIVO[] obtemUsuarioVIVO(String user, String idGrupo, String status, String idAtendimento, boolean inMeuCliente) throws TuxedoException, FacadeException {
        try {
            log.debug("AtendimentoWorkflowFacadeImpl:obtemUsuarioVIVO("+user+", "+idGrupo+")");

            StringBuffer sbIN = new StringBuffer(ConstantesCRM.SVAZIO);
            int inFim = 0;
            int bloco = 0;
            int qdtRegistros = 500;
            AtendimentoInformacaoVO atdInfVO = null;
            while (inFim == 0) {
                int rInicial = bloco * qdtRegistros + 1;
                int rFinal = rInicial + qdtRegistros - 1;
                bloco++;
                sbIN.append("<idGrupo>").append(idGrupo).append("</idGrupo>");
                sbIN.append("<status>").append(status).append("</status>");
                if (idAtendimento != null && !ConstantesCRM.SZERO.equals(idAtendimento) && !ConstantesCRM.SVAZIO.equals(idAtendimento)) {
                    sbIN.append("<idAtendimento>").append(idAtendimento).append("</idAtendimento>");
                }
                if (inMeuCliente) {
                    sbIN.append("<inMeuCliente>1</inMeuCliente>");
                }
                sbIN.append("<nrRegistroInicial>").append(rInicial).append("</nrRegistroInicial>");
                sbIN.append("<nrRegistroFinal>").append(rFinal).append("</nrRegistroFinal>");
                
                String xmlOUT = tuxedo.callService("TuxConnector", TuxedoServiceBridge.getXMLRequest(user,"ATFECHMASPQUS",sbIN.toString()));
                sbIN.setLength(0);

                log.debug("xmlOut = " + xmlOUT);
                MsgDocument msgDoc = MsgDocument.Factory.parse(xmlOUT);
                if (atdInfVO == null) {
                    atdInfVO = AtendimentoInformacaoVODocument.Factory.parse(msgDoc.getMsg().getMsgBody().xmlText()).getAtendimentoInformacaoVO();
                    inFim = atdInfVO.getInFim();
                } else {
                    AtendimentoInformacaoVO tmpAtdInfVO = AtendimentoInformacaoVODocument.Factory.parse(msgDoc.getMsg().getMsgBody().xmlText()).getAtendimentoInformacaoVO();
                    inFim = tmpAtdInfVO.getInFim();
                    UsuarioVIVO[] usuarios = tmpAtdInfVO.getUsuarioVIVOArray();
                    int totalUsuarios = usuarios.length;
                    for (int i = 0; i < totalUsuarios; i++) {
                        UsuarioVIVO u = atdInfVO.addNewUsuarioVIVO();
                        u.set(usuarios[i]);
                    }
                }
            }
            return atdInfVO.getUsuarioVIVOArray();
        } catch (Exception ex) {
            log.error("Exception - AtendimentoWorkflowFacadeImpl:obtemUsuarioVIVO(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: AtendimentoWorkflowFacadeImpl:obtemUsuarioVIVO", ex));
        }
    }

    /**
     * @common:operation
     */
    public AtendimentoWorkflowComumVO obtemAtendimentoWorkflowComumVO(String user, String idAtendimento, String idAtividade, String idTabelaMotivo, String inCRI) throws TuxedoException, FacadeException {
        try {
            log.debug("AtendimentoWorkflowFacadeImpl:obtemAtendimentoWorkflowComumVO(" + user + ")");
            StringBuffer xmlIN = new StringBuffer("<idAtendimento>" + idAtendimento + "</idAtendimento>");
            xmlIN.append("<idAtividade>" + idAtividade + "</idAtividade>");
            if ((idTabelaMotivo != null) && (idTabelaMotivo.length() > 0)) {
                xmlIN.append("<idTabelaMotivo>" + idTabelaMotivo + "</idTabelaMotivo>");
            }
            if ((inCRI != null) && (inCRI.length() > 0)) {
                xmlIN.append("<inCRI>" + inCRI + "</inCRI>");
            }

            String xmlIn = XmlManager.MakeXmlIn(user, "ATDGETMOTTAB", xmlIN.toString());
            String xmlOUT = tuxedo.callService("TuxConnector", xmlIn);
            log.debug("xmlOUT = " + xmlOUT);

            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);

            return AtendimentoWorkflowVODocument.Factory.parse(msgDocRet.getMsg().getMsgBody().xmlText()).getAtendimentoWorkflowVO().getAtendimentoWorkflowComumVO();

        } catch (TuxedoServiceCallerException ex) {
            log.error("TuxedoException - AtendimentoWorkflowFacadeImpl:obtemAtendimentoWorkflowComumVO(" + user + ") - [" + ex.getMessage() + "]");
            throw new TuxedoException(ex);

        } catch (Exception ex) {
            log.error("Exception - AtendimentoWorkflowFacadeImpl:obtemAtendimentoWorkflowComumVO(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: AtendimentoWorkflowFacadeImpl:obtemAtendimentoWorkflowComumVO", ex));
        }
    }

    /**
     * @common:operation
     */
    public AtendimentoWorkflowComumVO obtemAtendimentoWorkflowComumInRCVO(String user, String idAtendimento, String idAtividade, String idTabelaMotivo, String inRC) throws TuxedoException, FacadeException {
        try {
            log.debug("AtendimentoWorkflowFacadeImpl:obtemAtendimentoWorkflowComumInRCVO(" + user + ")");
            StringBuffer xmlIN = new StringBuffer("<idAtendimento>" + idAtendimento + "</idAtendimento>");
            xmlIN.append("<idAtividade>" + idAtividade + "</idAtividade>");
            if ((idTabelaMotivo != null) && (idTabelaMotivo.length() > 0)) {
                xmlIN.append("<idTabelaMotivo>" + idTabelaMotivo + "</idTabelaMotivo>");
            }
            if ((inRC != null) && (inRC.length() > 0)) {
                xmlIN.append("<inRC>" + inRC + "</inRC>");
            }

            String xmlIn = XmlManager.MakeXmlIn(user, "ATDGETMOTTAB", xmlIN.toString());
            String xmlOUT = tuxedo.callService("TuxConnector", xmlIn);

            log.debug("xmlOut = " + xmlOUT);
            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);

            return AtendimentoWorkflowVODocument.Factory.parse(msgDocRet.getMsg().getMsgBody().xmlText()).getAtendimentoWorkflowVO().getAtendimentoWorkflowComumVO();

        } catch (TuxedoServiceCallerException ex) {
            log.error("TuxedoException - AtendimentoWorkflowFacadeImpl:obtemAtendimentoWorkflowComumInRCVO(" + user + ") - [" + ex.getMessage() + "]");
            throw new TuxedoException(ex);

        } catch (Exception ex) {
            log.error("Exception - AtendimentoWorkflowFacadeImpl:obtemAtendimentoWorkflowComumInRCVO(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: AtendimentoWorkflowFacadeImpl:obtemAtendimentoWorkflowComumInRCVO", ex));
        }
    }

    /**
     * @common:operation
     */
    public AtendimentoWorkflowComumVO obtemAtendimentoWorkflowReaberturaVO(String user, String idAtendimento, String idAtividade, String idTabelaMotivo, String inGrupo) throws TuxedoException, FacadeException {
        try {
            log.debug("AtendimentoWorkflowFacadeImpl:obtemAtendimentoWorkflowReaberturaVO(" + user + ")");
            StringBuffer xmlIN = new StringBuffer("<idAtendimento>" + idAtendimento + "</idAtendimento>");
            xmlIN.append("<idAtividade>" + idAtividade + "</idAtividade>");
            if ((idTabelaMotivo != null) && (idTabelaMotivo.length() > 0)) {
                xmlIN.append("<idTabelaMotivo>" + idTabelaMotivo + "</idTabelaMotivo>");
            }
            if ((inGrupo != null) && (inGrupo.length() > 0)) {
                xmlIN.append("<inGrupo>" + inGrupo + "</inGrupo>");
            }

            String xmlIn = XmlManager.MakeXmlIn(user, "ATDGETMOTTAB", xmlIN.toString());
            String xmlOUT = tuxedo.callService("TuxConnector", xmlIn);

            log.debug("xmlOut = " + xmlOUT);
            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);

            return AtendimentoWorkflowVODocument.Factory.parse(msgDocRet.getMsg().getMsgBody().xmlText()).getAtendimentoWorkflowVO().getAtendimentoWorkflowComumVO();

        } catch (TuxedoServiceCallerException ex) {
            log.error("TuxedoException - AtendimentoWorkflowFacadeImpl:obtemAtendimentoWorkflowReaberturaVO(" + user + ") - [" + ex.getMessage() + "]");
            throw new TuxedoException(ex);

        } catch (Exception ex) {
            log.error("Exception - AtendimentoWorkflowFacadeImpl:obtemAtendimentoWorkflowReaberturaVO(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: AtendimentoWorkflowFacadeImpl:obtemAtendimentoWorkflowReaberturaVO", ex));
        }
    }

    /**
     * @common:operation
     */
    public AtendimentoWorkflowComumVO obtemAtendimentoWorkflowComumCancelarVO(String user, String idAtendimento, String idAtividade, String idTabelaMotivo, String inFase) throws TuxedoException, FacadeException {
        try {
            log.debug("AtendimentoWorkflowFacadeImpl:obtemAtendimentoWorkflowComumCancelarVO(" + user + ")");
            StringBuffer xmlIn = new StringBuffer("<idAtendimento>" + idAtendimento + "</idAtendimento>");
            xmlIn.append("<idAtividade>" + idAtividade + "</idAtividade>");
            xmlIn.append("<inFase>" + inFase + "</inFase>");
            if ((idTabelaMotivo != null) && (idTabelaMotivo.length() > 0)) {
                xmlIn.append("<idTabelaMotivo>" + idTabelaMotivo + "</idTabelaMotivo>");
            }

            String xmlIN = XmlManager.MakeXmlIn(user, "ATDGETMOTCAN", xmlIn.toString());
            String xmlOUT = tuxedo.callService("TuxConnector", xmlIN);

            log.debug("xmlOut = " + xmlOUT);
            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);

            return AtendimentoWorkflowVODocument.Factory.parse(msgDocRet.getMsg().getMsgBody().xmlText()).getAtendimentoWorkflowVO().getAtendimentoWorkflowComumVO();

        } catch (TuxedoServiceCallerException ex) {
            log.error("TuxedoException - AtendimentoWorkflowFacadeImpl:obtemAtendimentoWorkflowComumCancelarVO(" + user + ") - [" + ex.getMessage() + "]");
            throw new TuxedoException(ex);

        } catch (Exception ex) {
            log.error("Exception - AtendimentoWorkflowFacadeImpl:obtemAtendimentoWorkflowComumCancelarVO(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: AtendimentoWorkflowFacadeImpl:obtemAtendimentoWorkflowComumCancelarVO", ex));
        }
    }

    /**
     * @common:operation
     */
    public AtendimentoWorkflowTecnicoVO obtemTiposEstadosDocTec(String user) throws TuxedoException, FacadeException {
        try {
            log.debug("AtendimentoWorkflowFacadeImpl:obtemTiposEstadosDocTec(" + user + ")");
            String xmlIn = "";
            String xmlOUT = tuxedo.callService("TuxConnector", TuxedoServiceBridge.getXMLRequest(user,"LSTESTDOCS",xmlIn));

            log.debug("xmlOut = " + xmlOUT);
            MsgDocument msgDoc = MsgDocument.Factory.parse(xmlOUT);
            return AtendimentoWorkflowTecnicoVODocument.Factory.parse(msgDoc.getMsg().getMsgBody().xmlText()).getAtendimentoWorkflowTecnicoVO();
        } catch (Exception ex) {
            log.error("Exception - AtendimentoWorkflowFacadeImpl:obtemTiposEstadosDocTec(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public AtendimentoWorkflowTecnicoDocVO[] obtemAtendimentoWorkflowTecnicoDocVOArray(String user, WFDocumentoTecnicoPesquisaVO wfDocumentoTecnicoPesquisaVO) throws TuxedoException, FacadeException {
        try {
            log.debug("AtendimentoWorkflowFacadeImpl:obtemAtendimentoWorkflowTecnicoDocVOArray(" + user + ")");
            String xmlIn = wfDocumentoTecnicoPesquisaVO.xmlText().replaceAll("vo[0-9]*:", "");
            String xmlOUT = tuxedo.callService("TuxConnector", TuxedoServiceBridge.getXMLRequest(user,"LSTDOCASSOC",xmlIn));

            log.debug("xmlOut = " + xmlOUT);
            MsgDocument msgDoc = MsgDocument.Factory.parse(xmlOUT);

            return AtendimentoWorkflowTecnicoVODocument.Factory.parse(msgDoc.getMsg().getMsgBody().xmlText()).getAtendimentoWorkflowTecnicoVO().getAtendimentoWorkflowTecnicoDocVOArray();

        } catch (Exception ex) {
            log.error("Exception - AtendimentoWorkflowFacadeImpl:obtemAtendimentoWorkflowTecnicoDocVOArray(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public WFAcaoRetornoVO documentoAssociadoAssociar(String user, AtendimentoVO[] atendimentos) throws TuxedoException, FacadeException {
        try {
            log.debug("AtendimentoWorkflowFacadeImpl:documentoAssociadoAssociar(" + user + ")");
            String xmlIn = getXmlAtendimentosVO(atendimentos);
            String xmlOUT = tuxedo.callService("TuxConnector", TuxedoServiceBridge.getXMLRequest(user,"ATDASSOCDOCTE",xmlIn));

            log.debug("xmlOut = " + xmlOUT);
            MsgDocument msgDoc = MsgDocument.Factory.parse(xmlOUT);
            return WFAcaoRetornoVODocument.Factory.parse(msgDoc.getMsg().getMsgBody().xmlText()).getWFAcaoRetornoVO();
        } catch (Exception ex) {
            log.error("Exception - AtendimentoWorkflowFacadeImpl:documentoAssociadoAssociar(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public WFAcaoRetornoVO documentoAssociadoAbertura(String user, AtendimentoWorkflowTecnicoVO atdWfTecnicoVO) throws TuxedoException, FacadeException {
        try {
            log.debug("AtendimentoWorkflowFacadeImpl:documentoAssociadoAbertura(" + user + ")");
            String xmlIn = atdWfTecnicoVO.xmlText().replaceAll("vo[0-9]*:", ConstantesCRM.SVAZIO);
            String xmlOUT = tuxedo.callService("TuxConnector", TuxedoServiceBridge.getXMLRequest(user,"DOCTECABERT",xmlIn));

            log.debug("xmlOut = " + xmlOUT);
            MsgDocument msgDoc = MsgDocument.Factory.parse(xmlOUT);
            return WFAcaoRetornoVODocument.Factory.parse(msgDoc.getMsg().getMsgBody().xmlText()).getWFAcaoRetornoVO();
        } catch (Exception ex) {
            log.error("Exception - AtendimentoWorkflowFacadeImpl:documentoAssociadoAbertura(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public WFAcaoRetornoVO documentoAssociadoFechamento(String user, AtendimentoWorkflowTecnicoVO atdWfTecnicoVO) throws TuxedoException, FacadeException {
        try {
            log.debug("AtendimentoWorkflowFacadeImpl:documentoAssociadoFechamento(" + user + ")");
            String xmlIn = atdWfTecnicoVO.xmlText().replaceAll("vo[0-9]*:", ConstantesCRM.SVAZIO);
            String xmlOUT = tuxedo.callService("TuxConnector", TuxedoServiceBridge.getXMLRequest(user,"ATDFECHDOCTEC",xmlIn));

            log.debug("xmlOut = " + xmlOUT);
            MsgDocument msgDoc = MsgDocument.Factory.parse(xmlOUT);
            return WFAcaoRetornoVODocument.Factory.parse(msgDoc.getMsg().getMsgBody().xmlText()).getWFAcaoRetornoVO();
        } catch (Exception ex) {
            log.error("Exception - AtendimentoWorkflowFacadeImpl:documentoAssociadoFechamento(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public AtendimentoWorkflowTestesVO obtemTestes(String user, String idAtendimento) throws TuxedoException, FacadeException {
        try {
            log.debug("AtendimentoWorkflowFacadeImpl:obtemTestes(" + user + ")");
            String xmlIN = "<idAtendimento>" + idAtendimento + "</idAtendimento>";
            String xmlOUT = tuxedo.callService("TuxConnector", TuxedoServiceBridge.getXMLRequest(user,"LSTDOCTECASS",xmlIN));

            log.debug("xmlOut = " + xmlOUT);
            MsgDocument msgDoc = MsgDocument.Factory.parse(xmlOUT);
            return AtendimentoWorkflowTestesVODocument.Factory.parse(msgDoc.getMsg().getMsgBody().xmlText()).getAtendimentoWorkflowTestesVO();
        } catch (Exception ex) {
            log.error("Exception - AtendimentoWorkflowFacadeImpl:obtemTestes(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: AtendimentoWorkflowFacadeImpl:obtemTestes", ex));
        }
    }

    /**
     * @common:operation
     */
    public void testesGravar(String user, AtendimentoWorkflowTestesVO atendimentoWorkflowTestesVO) throws TuxedoException, FacadeException {
        try {
            log.debug("AtendimentoWorkflowFacadeImpl:testesGravar(" + user + ", " + atendimentoWorkflowTestesVO + ")");
            StringBuffer xmlIn = new StringBuffer("<idAtividade>20</idAtividade>");
            xmlIn.append(atendimentoWorkflowTestesVO.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO));

            tuxedo.callService("TuxConnector", TuxedoServiceBridge.getXMLRequest(user,"WFATDPSATGRAVA",xmlIn.toString()));

        } catch (Exception ex) {
            log.error("Exception - AtendimentoWorkflowFacadeImpl:testesGravar(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: ", ex));
        }
    }

    private String getXmlAtendimentosVO(AtendimentoVO[] atendimentosVO) {
        String xmlIn = "<atendimentos>";
        for (int i = 0; i < atendimentosVO.length; i++) {
            xmlIn += atendimentosVO[i].xmlText().replaceAll("vo[0-9]*:", ConstantesCRM.SVAZIO);
            xmlIn = xmlIn.replaceAll("ns[1-9]+:", ConstantesCRM.SVAZIO);
        }
        xmlIn += "</atendimentos>";
        return xmlIn;
    }
}
